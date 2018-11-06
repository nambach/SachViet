package io.nambm.sachviet.crawler.impl;

import io.nambm.sachviet.crawler.CrawlerResultProcessor;
import io.nambm.sachviet.entity.RawBook;
import io.nambm.sachviet.model.book.BookList;
import io.nambm.sachviet.repository.RawBookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookProcessorImpl implements CrawlerResultProcessor {

    private boolean processObject = false;
    private boolean processFragmentList = false;
    private boolean processList = false;

    private List<RawBook> bookList;

    private RawBookRepository rawBookRepository;

    public BookProcessorImpl() {
    }

    public void setRawBookRepository(RawBookRepository rawBookRepository) {
        this.rawBookRepository = rawBookRepository;
    }

    public void setProcessObject(boolean processObject) {
        this.processObject = processObject;
    }

    public void setProcessFragmentList(boolean processFragmentList) {
        this.processFragmentList = processFragmentList;
    }

    public void setProcessList(boolean processList) {
        this.processList = processList;
    }

    public List<RawBook> getBookList() {
        return bookList;
    }

    @Override
    public boolean isNeededToProcessObject() {
        return processObject;
    }

    @Override
    public boolean isNeededToProcessFragmentList() {
        return processFragmentList;
    }

    @Override
    public boolean isNeededToProcessList() {
        return processList;
    }

    @Override
    public void processResultObject(Map<String, String> object) {
        //System.out.println(object);
        rawBookRepository.insertOrReplace(RawBook.convert(object));
    }

    @Override
    public void processResultFragmentList(List<Map<String, String>> list) {
        List<RawBook> fragmentRawBooks = RawBook.convert(list);

        //fragmentRawBooks.forEach(System.out::println);

        rawBookRepository.updateBatch(fragmentRawBooks);
    }

    @Override
    public void processResultList(List<Map<String, String>> list) {
        System.out.print("Total books: ");
        System.out.println(list.size());

        bookList = RawBook.convert(list);
    }
}
