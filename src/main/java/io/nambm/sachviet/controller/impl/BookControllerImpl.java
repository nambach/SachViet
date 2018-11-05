package io.nambm.sachviet.controller.impl;

import io.nambm.sachviet.controller.BookController;
import io.nambm.sachviet.entity.CompareGroup;
import io.nambm.sachviet.entity.RawBook;
import io.nambm.sachviet.model.book.CompareModel;
import io.nambm.sachviet.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

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

        searchValue = searchValue.trim();

        if (!searchValue.equals("")) {
            books = bookService.searchBookCompare(searchValue);
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
}
