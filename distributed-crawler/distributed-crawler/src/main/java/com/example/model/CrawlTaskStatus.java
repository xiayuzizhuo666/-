package com.example.model;

import lombok.Data;

@Data
public class CrawlTaskStatus {
    private String url;
    private String status; // SUCCESS, FAILURE, RETRY
    private int retryCount; // 重试次数

    // 无参构造函数
    public CrawlTaskStatus() {
    }

    // 三参构造函数
    public CrawlTaskStatus(String url, String status, int retryCount) {
        this.url = url;
        this.status = status;
        this.retryCount = retryCount;
    }
}