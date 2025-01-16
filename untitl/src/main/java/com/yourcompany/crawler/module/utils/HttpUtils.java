package main.java.com.yourcompany.crawler.module.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    private static final String USER_AGENT = ConfigUtils.getCrawlerUserAgent();

    public static String getHtml(String url) {
        StringBuilder html = new StringBuilder();
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("User-Agent", USER_AGENT);
            connection.connect();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    html.append(line);
                }
            } catch (Exception e) {
                logger.error("读取 HTTP 响应异常: {}", e.getMessage(), e);
            }
        } catch (Exception e) {
            logger.error("HTTP 请求异常: {}", e.getMessage(), e);
        }
        return html.toString();
    }

    public static void post(String url, String data) {
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.addRequestProperty("User-Agent", USER_AGENT);
            connection.getOutputStream().write(data.getBytes());
            connection.connect();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    logger.info("HTTP POST 响应: {}", line);
                }
            } catch (Exception e) {
                logger.error("读取 HTTP POST 响应异常: {}", e.getMessage(), e);
            }
        } catch (Exception e) {
            logger.error("HTTP POST 请求异常: {}", e.getMessage(), e);
        }
    }
}