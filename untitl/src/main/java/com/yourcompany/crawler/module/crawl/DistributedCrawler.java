package main.java.com.yourcompany.crawler.module.crawl;

import main.java.com.yourcompany.crawler.module.url.UrlDispatcher;

public class DistributedCrawler extends Crawler {
    public DistributedCrawler(UrlDispatcher urlDispatcher) {
        super(urlDispatcher);
    }
}