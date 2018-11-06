package io.nambm.sachviet.service.impl;

import io.nambm.sachviet.entity.CompareGroup;
import io.nambm.sachviet.entity.RawBook;
import io.nambm.sachviet.entity.SuggestGroup;
import io.nambm.sachviet.repository.CompareGroupRepository;
import io.nambm.sachviet.repository.RawBookRepository;
import io.nambm.sachviet.repository.SuggestGroupRepository;
import io.nambm.sachviet.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@SessionScope
public class BookServiceImpl implements BookService {

    private final RawBookRepository bookRepository;

    private final CompareGroupRepository compareGroupRepository;

    private final SuggestGroupRepository suggestGroupRepository;

    @Autowired
    public BookServiceImpl(RawBookRepository bookRepository, CompareGroupRepository compareGroupRepository, SuggestGroupRepository suggestGroupRepository) {
        this.bookRepository = bookRepository;
        this.compareGroupRepository = compareGroupRepository;
        this.suggestGroupRepository = suggestGroupRepository;
    }

    @Override
    public List<RawBook> searchBook(String searchValue) {
        List<RawBook> books;

        books = bookRepository.searchByNameOrAuthor(searchValue);

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
}
