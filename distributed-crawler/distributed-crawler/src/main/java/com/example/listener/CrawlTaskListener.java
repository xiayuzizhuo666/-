package com.example.listener;

import com.example.config.RabbitMQConfig;
import com.example.model.CrawlResult;
import com.example.model.CrawlTask;
import com.example.model.CrawlTaskStatus;
import com.example.service.WebCrawler;
import com.example.service.DataParser;
import com.example.service.DataStorage;
import com.example.service.CrawlerMonitor;
import com.example.repository.CrawlTaskStatusRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Map;

@Component
public class CrawlTaskListener {
    @Autowired
    private WebCrawler webCrawler;
    @Autowired
    private DataParser dataParser;
    @Autowired
    private DataStorage dataStorage;
    @Autowired
    private CrawlerMonitor crawlerMonitor;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private CrawlTaskStatusRepository statusRepository;

    @RabbitListener(queues = {RabbitMQConfig.QUEUE_NAME_0, RabbitMQConfig.QUEUE_NAME_1, RabbitMQConfig.QUEUE_NAME_2})
    public void receiveMessage(CrawlTask task) {
        CrawlTaskStatus status = new CrawlTaskStatus(task.getUrl(), "SUCCESS", 0);
        try {
            CrawlResult result = webCrawler.crawl(task);
            if (result != null) {
                Map<String, String> parsedData = dataParser.parse(result);
                dataStorage.store(result);
                crawlerMonitor.reportCrawledUrl();
            }
        } catch (Exception e) {
            e.printStackTrace();
            status.setStatus("FAILURE");
            status.setRetryCount(status.getRetryCount() + 1);
            crawlerMonitor.reportError();
            // 重试机制
            if (status.getRetryCount() < 3) { // 最多重试3次
                rabbitTemplate.convertAndSend(task.getQueueName(), task);
                status.setStatus("RETRY");
            }
        } finally {
            // 记录任务状态
            statusRepository.save(status);
            // 检查系统状态，确保高可用性
            checkSystemStatus();
        }
    }

    private void checkSystemStatus() {
        // 检查系统状态，确保高可用性
        // 可以使用健康检查、故障转移等机制
    }
}