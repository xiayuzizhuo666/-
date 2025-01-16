package main.java.com.yourcompany.crawler;

import main.java.com.yourcompany.crawler.module.crawl.Crawler;
import main.java.com.yourcompany.crawler.module.parse.Parser;
import main.java.com.yourcompany.crawler.module.parse.ParserStrategy;
import main.java.com.yourcompany.crawler.module.parse.DefaultParserStrategy;
import main.java.com.yourcompany.crawler.module.query.Query;
import main.java.com.yourcompany.crawler.module.store.Storage;
import main.java.com.yourcompany.crawler.module.url.UrlDispatcher;
import main.java.com.yourcompany.crawler.module.url.UrlManager;
import main.java.com.yourcompany.crawler.utils.RobotUtils;

import java.util.Arrays;
import java.util.List;

public class CrawlerApplication {
    public static void main(String[] args) {
        // 初始化各个模块
        UrlManager urlManager = new UrlManager();
        UrlDispatcher urlDispatcher = new UrlDispatcher(urlManager);
        ParserStrategy parserStrategy = new DefaultParserStrategy();
        Parser parser = new Parser(new Storage(), parserStrategy);
        Query query = new Query(new Storage());
        Crawler crawler = new Crawler(urlDispatcher, parser);

        // 初始化种子 URL
        List<String> seedUrls = Arrays.asList(
                "https://example.com",
                "https://anotherexample.com"
        );

        // 添加种子 URL 到 URL 管理器
        for (String url : seedUrls) {
            if (RobotUtils.isAllowed(url)) {
                urlManager.addNewUrl(url);
            }
        }

        // 启动爬虫
        crawler.startCrawling();

        // 示例：查询存储的数据
        String queryUrl = "https://example.com";
        String data = query.queryData(queryUrl);
        System.out.println("查询结果: " + data);
    }
}