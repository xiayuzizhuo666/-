package main.java.com.yourcompany.crawler.module.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigUtils.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("加载配置文件失败", e);
        }
    }

    public static int getCrawlerThreadCount() {
        return Integer.parseInt(properties.getProperty("crawler.threadCount", "10"));
    }

    public static int getCrawlerCrawlInterval() {
        return Integer.parseInt(properties.getProperty("crawler.crawlInterval", "1000"));
    }
}