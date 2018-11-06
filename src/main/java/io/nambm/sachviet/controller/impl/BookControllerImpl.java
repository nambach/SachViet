package io.nambm.sachviet.controller.impl;

import io.nambm.sachviet.controller.BookController;
import io.nambm.sachviet.crawler.rule.Rules;
import io.nambm.sachviet.entity.CompareGroup;
import io.nambm.sachviet.entity.RawBook;
import io.nambm.sachviet.model.ClassificationResult;
import io.nambm.sachviet.model.book.CompareModel;
import io.nambm.sachviet.service.BookService;
import io.nambm.sachviet.utils.FileUtils;
import io.nambm.sachviet.utils.JAXBUtils;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Controller
@SessionScope
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @Autowired
    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/v-1/search")
    public ResponseEntity<List<RawBook>> searchBook(@RequestParam String searchValue) {
        List<RawBook> books;

        //pre-process search value

        books = bookService.searchBook(searchValue);

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/search")
    public ResponseEntity<List<CompareGroup>> searchBooksForCompare(@RequestParam String searchValue) {
        List<CompareGroup> books = new LinkedList<>();

        if (!searchValue.trim().equals("")) {
            books = bookService.searchBookCompare(searchValue);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/suggest")
    public ResponseEntity<List<CompareGroup>> getSuggestedBooks(@RequestParam String suggestId) {
        List<CompareGroup> books = new LinkedList<>();

        suggestId = suggestId.trim();

        if (!suggestId.equals("")) {
            books = bookService.getSuggestedBooks(suggestId);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/compare")
    public ResponseEntity<CompareModel> getCompareDetail(@RequestParam String compareGroupId) {
        CompareModel model = null;
        HttpStatus status = HttpStatus.NOT_FOUND;

        List<CompareGroup> groups = bookService.searchBookCompareByIds(Collections.singletonList(compareGroupId));

        if (!groups.isEmpty()) {
            CompareGroup group = groups.get(0);

            List<RawBook> books = bookService.searchBookByIds(group.getMemberList());

            model = new CompareModel(
                    group.getTitle(), group.getAuthors(), group.getImage(),
                    group.getMinPrice(), group.getMaxPrice(), group.getSuggestGroupId());
            model.setMembers(books);

            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(model, status);
    }

    @GetMapping("/compare/{id}")
    public ModelAndView viewDetail(@PathVariable("id") String compareId) {
        ModelAndView modelAndView = new ModelAndView("error/404");

        if (compareId != null && !compareId.trim().equals("")) {
            ResponseEntity<CompareModel> compareModelResponse = getCompareDetail(compareId);
            if (compareModelResponse.getStatusCode().is2xxSuccessful()) {
                modelAndView.setViewName("home/detail");
                modelAndView.addObject("compareId", compareId);
                modelAndView.addObject("compareGroup", compareModelResponse.getBody());
                modelAndView.addObject("suggestId", compareModelResponse.getBody().getSuggestGroupId());
            }
        }

        return modelAndView;
    }

    @PostMapping("/books/crawl")
    public ResponseEntity<ClassificationResult> crawl() {
        ClassificationResult result = null;
        HttpStatus status = HttpStatus.OK;

        try {
            File ruleFile = ResourceUtils.getFile("classpath:static/xml/book-rule.xml");
            Rules rules = JAXBUtils.unmarshalling(ruleFile, null, Rules.class);
            result = bookService.crawlNewRawBooks(rules);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(result, status);
    }

    @PostMapping("/books/stop-crawling")
    public ResponseEntity<String> stopCrawl() {
        bookService.stopCrawling();
        return new ResponseEntity<>("nothing", HttpStatus.OK);
    }
}
