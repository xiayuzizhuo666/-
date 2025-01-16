package main.java.com.yourcompany.crawler.module.url;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UrlManager {
    private ConcurrentLinkedQueue<String> newUrls = new ConcurrentLinkedQueue<>();
    private ConcurrentHashMap<String, Boolean> oldUrls = new ConcurrentHashMap<>();

    public boolean addNewUrl(String url) {
        if (url != null && !oldUrls.containsKey(url) && !newUrls.contains(url)) {
            newUrls.add(url);
            return true;
        }
        return false;
    }

    public String getNewUrl() {
        String url = newUrls.poll();
        if (url != null) {
            oldUrls.put(url, true);
        }
        return url;
    }

    public boolean hasNewUrl() {
        return !newUrls.isEmpty();
    }
}