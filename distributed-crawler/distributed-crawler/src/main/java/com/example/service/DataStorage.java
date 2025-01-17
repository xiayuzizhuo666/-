package com.example.service;

import com.example.model.CrawlResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataStorage {
    @Autowired
    private MongoDatabase mongoDatabase;

    public void store(CrawlResult result) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("crawl_results");
        Document document = new Document("url", result.getUrl())
                .append("content", result.getContent());
        collection.insertOne(document);
    }
}