package main.java.com.yourcompany.crawler.module.utils;

public class HtmlUtils {
    public static String extractData(String html) {
        // 这里可以使用正则表达式或 HTML 解析库（如 Jsoup）来提取数据
        // 例如：return Jsoup.parse(html).select("div.content").text();
        return html; // 示例中直接返回 HTML
    }
}