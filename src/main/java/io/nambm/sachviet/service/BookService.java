package io.nambm.sachviet.service;

import io.nambm.sachviet.crawler.rule.Rules;
import io.nambm.sachviet.entity.CompareGroup;
import io.nambm.sachviet.entity.RawBook;
import io.nambm.sachviet.entity.SuggestGroup;
import io.nambm.sachviet.model.ClassificationResult;

import java.util.List;

public interface BookService {

    List<RawBook> searchBook(String searchValue);

    List<RawBook> searchBookByIds(List<String> ids);

    List<CompareGroup> searchBookCompare(String searchValue);

    List<CompareGroup> searchBookCompareByIds(List<String> ids);

    List<CompareGroup> getSuggestedBooks(String suggestId);

    //Classification from here
    ClassificationResult classifyBooks(List<RawBook> rawBooks, List<CompareGroup> compareGroups, List<SuggestGroup> suggestGroups);

    ClassificationResult reClassifyAllBooks();

    ClassificationResult classifyNewRawBooks(List<RawBook> rawBooks);

    //Crawling from here
    ClassificationResult crawlNewRawBooks(Rules rules);

    void stopCrawling();
}
