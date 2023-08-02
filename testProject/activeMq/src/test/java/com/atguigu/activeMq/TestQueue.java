package com.atguigu.activeMq;

import com.atguigu.activeMq.queue.Consumer;
import com.atguigu.activeMq.queue.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author YangRuiHong
 * @create 2022-10-07 12:17
 * @email Yangrhd@dcits.com
 * @description:
 */
@SpringBootTest(classes = AppMain.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestQueue {
    @Autowired
    private Producer producer;

    @Test
    public void test() {
        producer.produce();
    }
}
