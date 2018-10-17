package io.nambm.sachviet.service.impl;

import io.nambm.sachviet.model.RawBook;
import io.nambm.sachviet.repository.RawBookRepository;
import io.nambm.sachviet.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Service
@SessionScope
public class BookServiceImpl implements BookService {

    private final RawBookRepository bookRepository;

    @Autowired
    public BookServiceImpl(RawBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<RawBook> searchBook(String searchValue) {
        List<RawBook> books;

        books = bookRepository.searchByNameOrAuthor(searchValue);

        return books;
    }

}
