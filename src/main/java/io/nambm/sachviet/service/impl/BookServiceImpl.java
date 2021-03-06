package io.nambm.sachviet.service.impl;

import io.nambm.sachviet.crawler.Crawler;
import io.nambm.sachviet.crawler.impl.BookProcessorImpl;
import io.nambm.sachviet.crawler.rule.Rules;
import io.nambm.sachviet.entity.*;
import io.nambm.sachviet.model.ClassificationResult;
import io.nambm.sachviet.repository.*;
import io.nambm.sachviet.service.BookService;
import io.nambm.sachviet.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
import java.util.stream.Collectors;

@Service
@SessionScope
public class BookServiceImpl implements BookService {

    private final RawBookRepository bookRepository;

    private final CompareGroupRepository compareGroupRepository;

    private final SuggestGroupRepository suggestGroupRepository;

    private final TrafficRepository trafficRepository;

    private final CategoryRepository categoryRepository;
    private final CateRelationRepository cateRelationRepository;

    @Autowired
    public BookServiceImpl(RawBookRepository bookRepository, CompareGroupRepository compareGroupRepository, SuggestGroupRepository suggestGroupRepository, TrafficRepository trafficRepository, CategoryRepository categoryRepository, CateRelationRepository cateRelationRepository) {
        this.bookRepository = bookRepository;
        this.compareGroupRepository = compareGroupRepository;
        this.suggestGroupRepository = suggestGroupRepository;
        this.trafficRepository = trafficRepository;
        this.categoryRepository = categoryRepository;
        this.cateRelationRepository = cateRelationRepository;
    }

    @Override
    public List<RawBook> searchBook(String searchValue) {
        List<RawBook> books;

        if (searchValue.trim().equals("")) {
            books = bookRepository.searchAll();
        } else {
            books = bookRepository.searchByNameOrAuthor(searchValue);
        }

        return books;
    }

    @Override
    public List<RawBook> searchBookByIds(List<String> ids) {
        List<RawBook> books;

        books = bookRepository.searchByIds(ids);

        return books;
    }

    @Override
    public List<CompareGroup> searchBookCompare(String searchValue) {
        List<CompareGroup> groups;

        groups = compareGroupRepository.searchByNameOrAuthor(searchValue);

        return groups;
    }

    @Override
    public List<CompareGroup> searchBookCompareByIds(List<String> ids) {
        List<CompareGroup> groups;

        groups = compareGroupRepository.searchByIds(ids);

        return groups;
    }

    @Override
    public List<CompareGroup> searchTopFiveBooks() {
        List<String> idList = trafficRepository.getTopFiveCompareGroup();

        List<CompareGroup> result = new ArrayList<>();
        List<CompareGroup> top5Groups;

        top5Groups = compareGroupRepository.searchByIds(idList);

        for (String id : idList) {
            result.add(top5Groups.stream().filter(compareGroup -> compareGroup.getId().equals(id)).findFirst().get());
        }

        return result;
    }

    @Override
    public List<CompareGroup> getSuggestedBooks(String suggestId) {
        List<CompareGroup> result = new ArrayList<>();

        List<SuggestGroup> suggestGroups = suggestGroupRepository.searchExactColumn(Collections.singletonList(suggestId), "groupId");
        if (suggestGroups.isEmpty()) {
            return result;
        }

        SuggestGroup suggestGroup = suggestGroups.get(0);
        result = compareGroupRepository.searchByIds(suggestGroup.getMemberList());

        return result;
    }

    @Override
    public List<CompareGroup> getBooksByCategory(String cateId) {
        List<CompareGroup> result;

        List<String> bookIds = cateRelationRepository.searchBookIdByCategory(cateId);

        result = compareGroupRepository.searchByIds(bookIds);

        return result;
    }

    @Override
    public ClassificationResult classifyBooks(List<RawBook> rawBooks, List<CompareGroup> compareGroups, List<SuggestGroup> suggestGroups) {
        long start = System.currentTimeMillis();

        //Classify books for comparing
        for (RawBook book : rawBooks) {
            boolean pass = false;

            CompareGroup candidateGroup = null;
            double candidateSimilarRate = 0;

            for (CompareGroup compareGroup : compareGroups) {
                if (compareGroup.checkMemberSource(book.getId()))
                    continue; // Two books with same source can not be in a same group

                String groupTitle = compareGroup.getTitle();
                String bookTitle = book.getTitle();

                // Remove authors name in title (if any)
                if (compareGroup.getAuthors() != null && !compareGroup.getAuthors().equals("")) {
                    bookTitle = bookTitle.replace(compareGroup.getAuthors(), "");
                }

                double similarRate = StringUtils.calculateLCSubstring(bookTitle, groupTitle).calculateIdentity();
                if (similarRate >= 0.8 && similarRate > candidateSimilarRate) {
                    candidateGroup = compareGroup;
                    candidateSimilarRate = similarRate;
                    pass = true;
                }
            }

            if (pass) {
                candidateGroup.addMember(book.getId());
                candidateGroup.updateBookInfo(book);

                book.setCompareGroupId(candidateGroup.getId());
            }

            if (!pass) {
                CompareGroup newGroup = new CompareGroup();
                newGroup.setId(book.getId());
                newGroup.setCoreMember(book.getId());
                newGroup.addMember(book.getId());
                newGroup.importCoreMember(book);

                book.setCompareGroupId(newGroup.getId());

                compareGroups.add(newGroup);
            }
        }


        // Classify books for suggestion
        for (CompareGroup compareGroup : compareGroups) {
            if (compareGroup.getSuggestGroupId() == null) {
                boolean pass = false;

                for (SuggestGroup suggestGroup : suggestGroups) {

                    String guestTitle = compareGroup.getTitle();
                    String hostTitle = suggestGroup.getTitle();

                    double lcsSeqRate = StringUtils.calculateLCSubsequence(hostTitle, guestTitle).calculateSimilarity(6);
                    double lcsStrRate = StringUtils.calculateLCSubstring(hostTitle, guestTitle).calculateSimilarity();
                    if (lcsSeqRate >= 0.8 || lcsStrRate >= 0.7) {
                        compareGroup.setSuggestGroupId(suggestGroup.getId());
                        suggestGroup.addMember(compareGroup.getId());

                        pass = true;
                        break;
                    }
                }

                if (!pass) {
                    SuggestGroup newSuggestGroup = new SuggestGroup();
                    newSuggestGroup.setId(compareGroup.getId());
                    newSuggestGroup.setCoreMember(compareGroup.getCoreMember());
                    newSuggestGroup.setTitle(compareGroup.getTitle());
                    newSuggestGroup.addMember(compareGroup.getCoreMember());

                    compareGroup.setSuggestGroupId(newSuggestGroup.getId());

                    suggestGroups.add(newSuggestGroup);
                }
            }
        }

        long end = System.currentTimeMillis();

        return new ClassificationResult(rawBooks, compareGroups, suggestGroups, end - start);
    }

    @Override
    public ClassificationResult reClassifyAllBooks() {
        long start = System.currentTimeMillis();

        List<RawBook> books = bookRepository.searchAvailableBooks(true);

        compareGroupRepository.clearData();
        suggestGroupRepository.clearData();

        List<CompareGroup> compareGroups = new ArrayList<>();
        List<SuggestGroup> suggestGroups = new ArrayList<>();

        ClassificationResult result = classifyBooks(books, compareGroups, suggestGroups);

        suggestGroupRepository.insertBatch(result.getSuggestGroups());
        compareGroupRepository.insertBatch(result.getCompareGroups());

        long end = System.currentTimeMillis();
        result.setTotalMillis(end - start);

        System.out.println("Finish classifying after " + result.getTotalMillis() + " millis");

        return result;
    }

    @Override
    public ClassificationResult classifyNewRawBooks(List<RawBook> rawBooks) {
        long start = System.currentTimeMillis();

        //List<CompareGroup> compareGroups = compareGroupRepository.searchAll();
        //List<SuggestGroup> suggestGroups = suggestGroupRepository.searchAll();

        ClassificationResult result = reClassifyAllBooks();

        suggestGroupRepository.updateBatch(result.getSuggestGroups());
        compareGroupRepository.updateBatch(result.getCompareGroups());

        long end = System.currentTimeMillis();
        result.setTotalMillis(end - start);

        return result;
    }

    @Autowired
    private Crawler<BookProcessorImpl> crawler;

    @Override
    public ClassificationResult crawlNewRawBooks(Rules rules) {
        long start = System.currentTimeMillis();

        BookProcessorImpl bookProcessor = new BookProcessorImpl();
        bookProcessor.setProcessFragmentList(true);
        bookProcessor.setProcessList(true);
        bookProcessor.setRawBookRepository(bookRepository);

        Crawler.STOP = false;
        crawler.setRules(rules);
        crawler.setResultProcessor(bookProcessor);
        crawler.crawl();

        //Process categories
        processCategoryAfterCrawling();

        //Classify books for comparing
        ClassificationResult result = classifyNewRawBooks(bookProcessor.getBookList());

        long end = System.currentTimeMillis();
        result.setTotalMillis(end - start);

        //rawBooks are books that have just been crawled
        result.setRawBooks(RawBook.convert(crawler.getResults()));

        return result;
    }

    private void processCategoryAfterCrawling() {
        Map<String, String> categoryNames = crawler.getResultProcessor().getCategoryNames();
        Map<String, Set<String>> categoryMapping = crawler.getResultProcessor().getCategoryMapping();

        //Convert categoryNames into list of Category Entity
        List<Category> categories = categoryNames.entrySet()
                .stream()
                .map(entry -> new Category(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        //Convert categoryMapping into list of CateRelation Entity
        List<CateRelation> cateRelations = new ArrayList<>();
        categoryMapping.forEach((cateId, bookIds) -> {
            List<CateRelation> list = bookIds.stream()
                    .map(bookId -> new CateRelation(cateId, bookId))
                    .collect(Collectors.toList());

            cateRelations.addAll(list);
        });

        categoryRepository.updateBatch(categories);
        cateRelationRepository.updateBatch(cateRelations);
    }

    @Override
    public void stopCrawling() {
        Crawler.STOP = true;
    }
}
