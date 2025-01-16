package main.java.com.yourcompany.crawler.module.parse;

import main.java.com.yourcompany.crawler.utils.HtmlUtils;

public class DefaultParserStrategy implements ParserStrategy {
    @Override
    public String parse(String html) {
        return HtmlUtils.extractData(html);
    }
}