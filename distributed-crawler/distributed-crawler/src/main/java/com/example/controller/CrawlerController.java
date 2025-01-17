package com.example.controller;

import com.example.model.CrawlResult;
import com.example.model.CrawlTask;
import com.example.service.UrlDistributor;
import com.example.service.DataQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CrawlerController {
    @Autowired
    private UrlDistributor urlDistributor;
    @Autowired
    private DataQueryService dataQueryService;

    @PostMapping("/crawl")
    public void crawl(@RequestBody CrawlTask task) {
        urlDistributor.distributeUrl(task);
    }

    @GetMapping("/query")
    public List<CrawlResult> query(@RequestParam String keyword) {
        return dataQueryService.query(keyword);
    }
}