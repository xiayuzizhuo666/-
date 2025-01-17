package com.example.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomProxy {
    private List<String> proxies = new ArrayList<>();

    public void addProxy(String proxy) {
        proxies.add(proxy);
    }

    public String getRandomProxy() {
        if (proxies.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return proxies.get(random.nextInt(proxies.size()));
    }
}