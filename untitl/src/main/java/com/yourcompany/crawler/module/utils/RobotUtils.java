package main.java.com.yourcompany.crawler.module.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RobotUtils {
    private static final Logger logger = LoggerFactory.getLogger(RobotUtils.class);

    public static boolean isAllowed(String url) {
        try {
            URL urlObj = new URL(url);
            String host = urlObj.getHost();
            URL robotUrl = new URL("http://" + host + "/robots.txt");
            HttpURLConnection connection = (HttpURLConnection) robotUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Disallow:")) {
                        String disallowedPath = line.split(" ")[1];
                        if (url.contains(disallowedPath)) {
                            return false;
                        }
                    }
                }
                reader.close();
            } else {
                logger.error("HTTP 请求失败，响应码: {}", responseCode);
            }
        } catch (Exception e) {
            logger.error("HTTP 请求异常: {}", e.getMessage(), e);
        }
        return true;
    }
}