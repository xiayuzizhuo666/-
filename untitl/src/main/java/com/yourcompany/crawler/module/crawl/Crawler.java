package main.java.com.yourcompany.crawler.module.crawl;

import main.java.com.yourcompany.crawler.module.parse.Parser;
import main.java.com.yourcompany.crawler.module.url.UrlDispatcher;
import main.java.com.yourcompany.crawler.utils.ConfigUtils;
import main.java.com.yourcompany.crawler.utils.HttpUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Crawler {
    private UrlDispatcher urlDispatcher;
    private Parser parser;
    private ExecutorService executorService;
    private int crawlInterval;

    public Crawler(UrlDispatcher urlDispatcher, Parser parser) {
        this.urlDispatcher = urlDispatcher;
        this.parser = parser;
        this.executorService = Executors.newFixedThreadPool(ConfigUtils.getCrawlerThreadCount());
        this.crawlInterval = ConfigUtils.getCrawlerCrawlInterval();
    }

    public Crawler(UrlDispatcher urlDispatcher) {
    }

    public void startCrawling() {
        while (true) {
            String url = urlDispatcher.dispatchUrl();
            if (url == null) {
                break;
            }
            executorService.submit(() -> {
                try {
                    Thread.sleep(crawlInterval);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                String html = HttpUtils.getHtml(url);
                parser.parseHtml(url, html);
            });
        }
        executorService.shutdown();
    }
}