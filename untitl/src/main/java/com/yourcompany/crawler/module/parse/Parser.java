package main.java.com.yourcompany.crawler.module.parse;

import main.java.com.yourcompany.crawler.module.store.Storage;


public class Parser {
    private Storage storage;
    private ParserStrategy parserStrategy;

    public Parser(Storage storage, ParserStrategy parserStrategy) {
        this.storage = storage;
        this.parserStrategy = parserStrategy;
    }

    public void parseHtml(String url, String html) {
        try {
            String data = parserStrategy.parse(html);
            storage.storeData(url, data);
        } catch (Exception e) {
            logger.error("解析 HTML 异常: {}", e.getMessage(), e);
        }
    }
}