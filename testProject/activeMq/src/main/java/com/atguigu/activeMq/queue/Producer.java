package com.atguigu.activeMq.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.UUID;

/**
 * @author YangRuiHong
 * @create 2022-10-07 12:11
 * @email Yangrhd@dcits.com
 * @description:
 */
@Component
public class Producer {
    @Resource
    private JmsMessagingTemplate template;
    @Autowired
    private Queue queue;

    public void produce() {
        template.convertAndSend(queue, "练习队列产生的消息：" + UUID.randomUUID().toString().substring(0, 6));
        System.out.println("消息生产完毕");
    }
}
