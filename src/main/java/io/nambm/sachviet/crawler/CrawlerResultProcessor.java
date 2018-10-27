package io.nambm.sachviet.crawler;

import java.util.List;
import java.util.Map;

public interface CrawlerResultProcessor {

    boolean isNeededToProcessObject();

    boolean isNeededToProcessList();

    void processResultObject(Map<String, String> object);

    void processResultList(List<Map<String, String>> list);
}
