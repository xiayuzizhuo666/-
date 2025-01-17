package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CrawlerMonitor {
    private int crawledUrlCount = 0;
    private int errorCount = 0;
    private long startTime = System.currentTimeMillis();

    public void reportCrawledUrl() {
        crawledUrlCount++;
    }

    public void reportError() {
        errorCount++;
    }

    @Scheduled(fixedRate = 60000) // 每分钟打印一次状态
    public void printStatus() {
        long currentTime = System.currentTimeMillis();
        System.out.println("Crawled URLs: " + crawledUrlCount);
        System.out.println("Errors: " + errorCount);
        System.out.println("Elapsed Time: " + (currentTime - startTime) + " ms");
    }
}