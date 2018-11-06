package io.nambm.sachviet.crawler;

import java.util.List;
import java.util.Map;

public interface CrawlerResultProcessor {

    boolean isNeededToProcessObject();

    boolean isNeededToProcessFragmentList();

    boolean isNeededToProcessList();

    void processResultObject(Map<String, String> object);

    void processResultFragmentList(List<Map<String, String>> list);

    void processResultList(List<Map<String, String>> list);
}
