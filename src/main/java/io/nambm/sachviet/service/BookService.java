package io.nambm.sachviet.service;

import io.nambm.sachviet.entity.CompareGroup;
import io.nambm.sachviet.entity.RawBook;

import java.util.List;

public interface BookService {

    List<RawBook> searchBook(String searchValue);

    List<RawBook> searchBookByIds(List<String> ids);

    List<CompareGroup> searchBookCompare(String searchValue);

    List<CompareGroup> searchBookCompareByIds(List<String> ids);

    List<CompareGroup> getSuggestedBooks(String suggestId);

    void classifyBooks();
}
