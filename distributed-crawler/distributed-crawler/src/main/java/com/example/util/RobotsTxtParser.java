package com.example.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class RobotsTxtParser {
    public static boolean isAllowed(String url) {
        try {
            String robotsUrl = url.replaceFirst("https?://", "https://") + "/robots.txt";
            Document document = Jsoup.connect(robotsUrl).get();
            String content = document.body().html();
            // 解析robots.txt内容，判断是否允许爬取
            // 这里可以使用正则表达式或其他方法解析
            return true; // 示例，实际需要解析robots.txt内容
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}