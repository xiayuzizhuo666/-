package com.example.repository;

import com.example.model.CrawlTaskStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CrawlTaskStatusRepository extends MongoRepository<CrawlTaskStatus, String> {
}