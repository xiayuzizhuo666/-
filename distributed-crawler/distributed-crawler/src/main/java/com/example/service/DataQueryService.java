package com.example.service;

import com.example.model.CrawlResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataQueryService {
    @Autowired
    private MongoDatabase mongoDatabase;

    public List<CrawlResult> query(String keyword) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("crawl_results");
        List<CrawlResult> results = new ArrayList<>();
        for (Document doc : collection.find()) {
            String content = doc.getString("content");
            if (content.contains(keyword)) {
                results.add(new CrawlResult(doc.getString("url"), content));
            }
        }
        return results;
    }
}