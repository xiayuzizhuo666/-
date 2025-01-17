package com.example.service;

import com.example.model.CrawlTask;
import com.example.model.CrawlResult;
import com.example.util.JsoupUtil;
import com.example.util.RobotsTxtParser;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class WebCrawler {
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public CrawlResult crawl(CrawlTask task) {
        if (!RobotsTxtParser.isAllowed(task.getUrl())) {
            return null; // 如果不允许爬取，返回null
        }
        CompletableFuture<CrawlResult> future = CompletableFuture.supplyAsync(() -> {
            try {
                String content = JsoupUtil.fetchContent(task.getUrl());
                return new CrawlResult(task.getUrl(), content);
            } catch (Exception e) {
                // 处理异常情况
                // 如网页HTML编码不规范、爬虫陷阱、被爬取服务器宕机等
                e.printStackTrace();
                return null;
            }
        }, executorService);
        return future.join(); // 使用join方法等待任务完成并获取结果
    }
}