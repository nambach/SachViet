package io.nambm.sachviet.controller.impl;

import io.nambm.sachviet.controller.BookController;
import io.nambm.sachviet.entity.RawBook;
import io.nambm.sachviet.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Controller
@SessionScope
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @Autowired
    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/search")
    public ResponseEntity<List<RawBook>> searchBook(@RequestParam String searchValue) {
        List<RawBook> books;

        //pre-process search value

        books = bookService.searchBook(searchValue);

        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
