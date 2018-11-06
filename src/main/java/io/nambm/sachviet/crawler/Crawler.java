package io.nambm.sachviet.crawler;

import io.nambm.sachviet.crawler.rule.*;
import io.nambm.sachviet.utils.DomUtils;
import io.nambm.sachviet.utils.FileUtils;
import io.nambm.sachviet.utils.JAXBUtils;
import io.nambm.sachviet.utils.TextUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class Crawler<T extends CrawlerResultProcessor> {
    //crawlRules
    //-----
    //List<RawBook>

    public static boolean STOP = false;

    private Rules rules;
    private List<Map<String, String>> results;
    private T resultProcessor;

    public Crawler() {
    }

    public Crawler(Rules rules) {
        this.rules = rules;
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }

    public void setResultProcessor(T resultProcessor) {
        this.resultProcessor = resultProcessor;
    }

    public List<Map<String, String>> getResults() {
        if (results == null) {
            results = new LinkedList<>();
        }
        return results;
    }

    public Rules getRules() {
        return rules;
    }

    public T getResultProcessor() {
        return resultProcessor;
    }

    public void crawl() {
        results = new LinkedList<>();

        if (rules == null || rules.getRule().size() == 0) {
            System.out.println("Nothing to do");
            return;
        }

        System.out.println("===== Start crawling... =====");

        //ITERATE ALL RULES
        master:
        for (Rule rule : rules.getRule()) {

            String baseUrl = rule.getBasedUrl();
            String siteName = rule.getSiteName();
            String collectionXPath = rule.getCollectionXpath();
            Item itemRule = rule.getItem();

            System.out.println(siteName);
            System.out.println(baseUrl);

            //ITERATE ALL URLs (Topics)
            for (TopicType topic : rule.getTopics().getTopic()) {

                String topicName = topic.getTopicName();
                String topicCode = topic.getTopicCode();

                UrlType topicURL = topic.getUrl();

                //The XPath to locate the position of main content
                String fragmentXPath = topic.getFragmentXpath();

                System.out.println();
                System.out.println(topicName);

                int from = Integer.parseInt(topicURL.getFrom());
                int to = Integer.parseInt(topicURL.getTo());
                int step = Integer.parseInt(topicURL.getStep());

                //ITERATE ALL PAGES IN ONE URL
                for (int pageNo = from; pageNo <= to; pageNo += step) {

                    //FETCHING HTML
                    String textContent;
                    try {
                        String incrementParam = "";
                        if (!topicURL.getIncrementParam().trim().equals("")) {
                            incrementParam = topicURL.getIncrementParam().replaceAll("\\{i}", String.valueOf(pageNo));
                        }

                        URL url = new URL(topicURL.getValue() + incrementParam);
                        URLConnection connection = url.openConnection();
                        connection.setReadTimeout(8 * 1000);
                        connection.setConnectTimeout(8 * 1000);

                        textContent = FileUtils.getString(connection.getInputStream());

                        textContent = TextUtils.refineHtml(textContent);
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println("IO Error: " + e);
                        break;//exit pages loop - go to next Topic
                    }

                    //PARSE text into DOM
                    Document document = DomUtils.parseStringIntoDOM(textContent);

                    //Get target fragment (to eliminate missed evaluations)
                    Node fragment = DomUtils.evaluateNode(document, fragmentXPath, Node.class);

                    NodeList collection = DomUtils.evaluateNode(fragment, collectionXPath, NodeList.class);

                    if (collection == null || collection.getLength() == 0) {
                        System.out.println("empty collection");
                        break;//exit pages loop - go to next Topic
                    }

                    List<Map<String, String>> batchObj = new ArrayList<>();
                    collectionLoop:
                    for (int j = 0; j < collection.getLength(); j++) {
                        Map<String, String> obj = new HashMap<>();
                        Node item = collection.item(j);

                        obj.put("siteName", siteName);
                        obj.put("topicCode", topicCode);

                        for (ItemDetail detailXPath : itemRule.getDetailXpath()) {
                            String name = detailXPath.getDetailName();
                            String value = DomUtils.evaluateNode(item, detailXPath.getValue(), String.class);

                            if (detailXPath.isIsRequired()) {
                                if (value != null && value.trim().equals("")) {
                                    continue collectionLoop;//ignore any item (obj) in the collection that contains empty attribute(s)
                                }
                            }

                            value = detailXPath.getPrefix() + value + detailXPath.getPostfix();

                            obj.put(name, value);
                        }//End one item

                        batchObj.add(obj);
                        results.add(obj);

                        //Process after one object is crawled
                        //System.out.println(obj);
                        if (resultProcessor != null && resultProcessor.isNeededToProcessObject()) {
                            resultProcessor.processResultObject(obj);
                        }

                    }//End items in one topic

                    //Process after one page is crawled
                    //System.out.println(batchObj.size());
                    if (resultProcessor != null && resultProcessor.isNeededToProcessFragmentList()) {
                        resultProcessor.processResultFragmentList(batchObj);
                    }
                    //Stop if demand
                    if (Crawler.STOP) {
                        break master;
                    }

                }//End topic

            }//End all topics for one rule

        }//End all rules

        //System.out.println(results.size());
        if (resultProcessor != null && resultProcessor.isNeededToProcessList()) {
            resultProcessor.processResultList(results);
        }
    }
}
