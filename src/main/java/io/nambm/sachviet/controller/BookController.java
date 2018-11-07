package io.nambm.sachviet.controller;

import io.nambm.sachviet.entity.CompareGroup;
import io.nambm.sachviet.model.ClassificationResult;
import io.nambm.sachviet.model.book.BookList;
import io.nambm.sachviet.model.book.CompareModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface BookController {

    ResponseEntity<BookList> searchBook(String searchValue);

    ResponseEntity<List<CompareGroup>> searchBooksForCompare(String searchValue);

    ResponseEntity<List<CompareGroup>> getBooksByCategory(String cateId);

    ResponseEntity<List<CompareGroup>> getSuggestedBooks(String suggestId);

    ResponseEntity<CompareModel> getCompareDetail(String compareGroupId);

    ModelAndView viewDetail(String compareId);

    ResponseEntity<ClassificationResult> crawl();

    ResponseEntity<String> stopCrawl();
}
