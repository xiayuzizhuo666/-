package com.example.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupUtil {
    public static String fetchContent(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            return document.body().html();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}