package io.nambm.sachviet.service;

import io.nambm.sachviet.entity.CompareGroup;
import io.nambm.sachviet.entity.RawBook;

import java.util.List;

public interface BookService {

    List<RawBook> searchBook(String searchValue);

    List<CompareGroup> searchBookCompare(String searchValue);
}
