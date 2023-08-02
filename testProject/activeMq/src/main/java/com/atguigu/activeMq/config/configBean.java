package com.atguigu.activeMq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author YangRuiHong
 * @create 2022-10-07 12:03
 * @email Yangrhd@dcits.com
 * @description:
 */
@EnableScheduling
@EnableJms
@Configuration
public class configBean {
    @Value("${topic}")
    private String topic;
    @Value("${queue}")
    private String queue;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(queue);
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic(topic);
    }
}
