package main.java.com.yourcompany.crawler.module.query;

import main.java.com.yourcompany.crawler.module.store.Storage;

public class Query {
    private Storage storage;

    public Query(Storage storage) {
        this.storage = storage;
    }

    public String queryData(String url) {
        return storage.getData(url);
    }
}