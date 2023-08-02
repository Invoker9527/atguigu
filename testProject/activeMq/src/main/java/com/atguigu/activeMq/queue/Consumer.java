package com.atguigu.activeMq.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.TextMessage;

/**
 * @author YangRuiHong
 * @create 2022-10-07 12:15
 * @email Yangrhd@dcits.com
 * @description:
 */
@Component
public class Consumer {
    @Autowired
    private Queue queue;

    @JmsListener(destination = "${queue}")
    public void receiver(TextMessage message) throws Exception {
        System.out.println(message);
    }
}
