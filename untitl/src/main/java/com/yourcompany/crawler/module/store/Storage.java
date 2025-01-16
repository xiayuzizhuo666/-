package main.java.com.yourcompany.crawler.module.store;

import java.util.concurrent.ConcurrentHashMap;

public class Storage {
    private ConcurrentHashMap<String, String> dataMap = new ConcurrentHashMap<>();

    public void storeData(String url, String data) {
        dataMap.put(url, data);
    }

    public String getData(String url) {
        return dataMap.get(url);
    }
}