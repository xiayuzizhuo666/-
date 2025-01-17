package com.example.model;

import lombok.Data;

@Data
public class CrawlResult {
    private String url;
    private String content;

    // 无参构造函数
    public CrawlResult() {
    }

    // 两参构造函数
    public CrawlResult(String url, String content) {
        this.url = url;
        this.content = content;
    }
}