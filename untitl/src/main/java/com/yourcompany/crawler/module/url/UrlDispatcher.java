package main.java.com.yourcompany.crawler.module.url;

public class UrlDispatcher {
    private UrlManager urlManager;

    public UrlDispatcher(UrlManager urlManager) {
        this.urlManager = urlManager;
    }

    public String dispatchUrl() {
        return urlManager.getNewUrl();
    }
}