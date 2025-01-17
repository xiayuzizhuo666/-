package com.example.service;

import com.example.model.CrawlTask;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UrlManager {
    private final Set<String> seenUrls = new HashSet<>();

    public boolean isNewUrl(String url) {
        return seenUrls.add(url);
    }

    public CrawlTask getNextUrl() {
        // 从待爬取URL队列中获取下一个URL
        // 这里可以使用RabbitMQ的队列来实现
        return null;
    }

    public void addUrl(String url) {
        if (isNewUrl(url)) {
            // 将新URL添加到待爬取URL队列中
            // 这里可以使用RabbitMQ的队列来实现
        }
    }
}