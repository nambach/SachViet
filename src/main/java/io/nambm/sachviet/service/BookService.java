package io.nambm.sachviet.service;

import io.nambm.sachviet.model.RawBook;

import java.util.List;

public interface BookService {

    List<RawBook> searchBook(String searchValue);
}
