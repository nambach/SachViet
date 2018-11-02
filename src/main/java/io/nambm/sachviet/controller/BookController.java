package io.nambm.sachviet.controller;

import io.nambm.sachviet.entity.CompareGroup;
import io.nambm.sachviet.entity.RawBook;
import io.nambm.sachviet.model.book.CompareModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookController {

    ResponseEntity<List<RawBook>> searchBook(String searchValue);

    ResponseEntity<List<CompareGroup>> searchBooksForCompare(String searchValue);

    ResponseEntity<CompareModel> getCompareDetail(String compareGroupId);
}
