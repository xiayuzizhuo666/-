package com.example.service;

import com.example.model.CrawlResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DataParser {
    public Map<String, String> parse(CrawlResult result) {
        Document doc = Jsoup.parse(result.getContent());
        Map<String, String> data = new HashMap<>();
        data.put("title", doc.title());

        // 根据不同的网页结构进行解析
        if (result.getUrl().contains("example.com")) {
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                data.put(link.attr("abs:href"), link.text());
            }
        } else if (result.getUrl().contains("another-example.com")) {
            // 其他解析逻辑
        }

        return data;
    }
}