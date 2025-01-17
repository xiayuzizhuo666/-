package com.example.service;

import com.example.config.RabbitMQConfig;
import com.example.model.CrawlTask;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UrlDistributor {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UrlManager urlManager;

    private final AtomicInteger counter = new AtomicInteger(0);

    public void distributeUrl(CrawlTask task) {
        if (urlManager.isNewUrl(task.getUrl())) {
            int nodeIndex = selectNodeBasedOnLoad();
            String queueName = "crawl_tasks_" + nodeIndex;
            rabbitTemplate.convertAndSend(queueName, task, m -> {
                m.getMessageProperties().setPriority(task.getPriority());
                return m;
            });
        }
    }

    private int selectNodeBasedOnLoad() {
        // 根据系统负载和任务队列的长度选择节点
        // 这里可以使用RabbitMQ的API获取队列长度
        // 选择负载最小的节点
        return counter.getAndIncrement() % 3; // 假设有3个爬虫节点
    }
}