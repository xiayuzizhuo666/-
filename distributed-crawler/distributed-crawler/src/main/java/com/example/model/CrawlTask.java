package com.example.model;

import lombok.Data;
import lombok.Getter;

@Data
public class CrawlTask {
    private String url;
    private int priority; // 任务优先级
    // Getter 方法
    @Getter
    private String queueName; // 任务关联的队列名称

    // 构造函数
    public CrawlTask(String url, int priority, String queueName) {
        this.url = url;
        this.priority = priority;
        this.queueName = queueName;
    }

}