package io.nambm.sachviet.crawler;

import io.nambm.sachviet.crawler.rule.Item;
import io.nambm.sachviet.crawler.rule.ItemDetail;
import io.nambm.sachviet.crawler.rule.Rule;
import io.nambm.sachviet.crawler.rule.Rules;
import io.nambm.sachviet.model.RawBook;
import io.nambm.sachviet.repository.generic.impl.GenericRepositoryImpl;
import io.nambm.sachviet.repository.impl.RawBookRepositoryImpl;
import io.nambm.sachviet.utils.DomUtils;
import io.nambm.sachviet.utils.FileUtils;
import io.nambm.sachviet.utils.JAXBUtils;
import io.nambm.sachviet.utils.TextUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Crawler {
    //crawlRules
    //-----
    //List<RawBook>

    private Rules rules;

    private List<Map<String, String>> results;

    private RawBookRepositoryImpl repository;

    public Crawler(String crawlRulePath) {
        //get crawling rules
        rules = JAXBUtils.unmarshalling(crawlRulePath, null, Rules.class);
        repository = new RawBookRepositoryImpl(GenericRepositoryImpl.getFactory());
    }

    public List<Map<String, String>> getResults() {
        if (results == null) {
            results = new LinkedList<>();
        }
        return results;
    }

    public RawBookRepositoryImpl getRepository() {
        return repository;
    }

    public void crawl() {
        results = new LinkedList<>();

        if (rules == null || rules.getRule().size() == 0) {
            System.out.println("Nothing to do");
            return;
        }

        //ITERATE ALL URLs
        for (int i = 0; i < rules.getRule().size(); i++) {
            Rule rule = rules.getRule().get(i);
            System.out.println();
//            System.out.println(rule.getSiteName());
//            System.out.println(rule.getName());

            //ITERATE ALL PAGES IN ONE URL
            for (int pageNo = Integer.parseInt(rule.getIncrementFrom()); pageNo <= Integer.parseInt(rule.getIncrementTo()); pageNo++) {

                //FETCHING HTML
                String textContent = "";
                try {
                    String incrementParam = rule.getIncrementParam().replaceAll("\\{i}", "" + pageNo);

                    URL url = new URL(rule.getUrl() + incrementParam);
                    URLConnection connection = url.openConnection();

                    textContent = FileUtils.getString(connection.getInputStream());

                    textContent = TextUtils.refineHtml(textContent);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("IO Error: " + e);
                    continue;
                }

                //PARSE text into DOM
                Document document = DomUtils.parseStringIntoDOM(textContent);

                Node fragment = DomUtils.evaluateNode(document, rule.getFragmentXpath(), Node.class);

                NodeList collection = DomUtils.evaluateNode(fragment, rule.getCollectionXpath(), NodeList.class);

                Item itemRule = rule.getItem();

                List<Map<String, String>> batch = new LinkedList<>();
                collectionLoop:
                for (int j = 0; j < (collection != null ? collection.getLength() : 0); j++) {
                    Node item = collection.item(j);

                    Map<String, String> obj = new HashMap<>();

                    obj.put("siteName", rule.getSiteName());

                    for (ItemDetail detailRule : itemRule.getDetailXpath()) {
                        String name = detailRule.getDetailName();
                        String value = DomUtils.evaluateNode(item, detailRule.getValue(), String.class);

                        if ("id".equals(name) && (value != null && value.trim().equals(""))) {
                            continue collectionLoop;
                        }

                        value = detailRule.getPrefix() + value + detailRule.getPostfix();

                        obj.put(name, value);
                    }

                    results.add(obj);
                    batch.add(obj);
//                    System.out.println(obj);
                }

                repository.insertBatch(RawBook.convert(batch));

                //End of some code for iterate pages
            }
        }

//        System.out.println(results.size());
    }
}
