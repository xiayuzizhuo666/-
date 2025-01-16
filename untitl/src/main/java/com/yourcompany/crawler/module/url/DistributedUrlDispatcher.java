package main.java.com.yourcompany.crawler.module.url;

import main.java.com.yourcompany.crawler.utils.HttpUtils;

public class DistributedUrlDispatcher extends UrlDispatcher {
    public DistributedUrlDispatcher(UrlManager urlManager) {
        super(urlManager);
    }

    @Override
    public String dispatchUrl() {
        String url = super.dispatchUrl();
        if (url != null) {
            notifyOtherNodes(url);
        }
        return url;
    }

    private void notifyOtherNodes(String url) {
        // 通过 HTTP 请求或其他方式通知其他节点
        HttpUtils.post("http://other-node/notify", url);
    }
}