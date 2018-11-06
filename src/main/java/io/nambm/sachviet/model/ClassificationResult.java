package io.nambm.sachviet.model;

import io.nambm.sachviet.entity.CompareGroup;
import io.nambm.sachviet.entity.RawBook;
import io.nambm.sachviet.entity.SuggestGroup;

import java.util.List;

public class ClassificationResult {

    private List<RawBook> rawBooks;
    private List<CompareGroup> compareGroups;
    private List<SuggestGroup> suggestGroups;
    private long totalMillis;

    public ClassificationResult(List<RawBook> rawBooks, List<CompareGroup> compareGroups, List<SuggestGroup> suggestGroups, long totalMillis) {
        this.rawBooks = rawBooks;
        this.compareGroups = compareGroups;
        this.suggestGroups = suggestGroups;
        this.totalMillis = totalMillis;
    }

    public ClassificationResult() {
    }

    public List<RawBook> getRawBooks() {
        return rawBooks;
    }

    public void setRawBooks(List<RawBook> rawBooks) {
        this.rawBooks = rawBooks;
    }

    public List<CompareGroup> getCompareGroups() {
        return compareGroups;
    }

    public void setCompareGroups(List<CompareGroup> compareGroups) {
        this.compareGroups = compareGroups;
    }

    public List<SuggestGroup> getSuggestGroups() {
        return suggestGroups;
    }

    public void setSuggestGroups(List<SuggestGroup> suggestGroups) {
        this.suggestGroups = suggestGroups;
    }

    public long getTotalMillis() {
        return totalMillis;
    }

    public void setTotalMillis(long totalMillis) {
        this.totalMillis = totalMillis;
    }
}
