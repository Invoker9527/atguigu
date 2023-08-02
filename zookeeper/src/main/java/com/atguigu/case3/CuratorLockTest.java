package com.atguigu.case3;

import com.atguigu.constant.Constant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Author YangRuiHong
 * @Create 2023-02-04 17:37
 * @Description 框架的方式实现分布式锁
 */
public class CuratorLockTest {
    public static void main(String[] args) {
        //创建分布式锁1
        InterProcessMutex lock1 = new InterProcessMutex(getCuratorFramework(), "/locks");
        //创建分布式锁2
        InterProcessMutex lock2 = new InterProcessMutex(getCuratorFramework(), "/locks");
        new Thread(() -> {
            try {
                lock1.acquire();
                System.out.println("线程1获取到锁");
                lock1.acquire();
                System.out.println("线程1再次获得到锁");
                Thread.sleep(5 * 1000);
                lock1.release();
                System.out.println("线程1释放锁");
                lock1.release();
                System.out.println("线程1再次释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                lock2.acquire();
                System.out.println("线程2获取到锁");
                lock2.acquire();
                System.out.println("线程2再次获得到锁");
                Thread.sleep(5 * 1000);
                lock2.release();
                System.out.println("线程2释放锁");
                lock2.release();
                System.out.println("线程2再次释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static CuratorFramework getCuratorFramework() {
        ExponentialBackoffRetry policy = new ExponentialBackoffRetry(3000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(Constant.connectionString).connectionTimeoutMs(Constant.sessionTimeOut).sessionTimeoutMs(Constant.sessionTimeOut).retryPolicy(policy).build();
        client.start();
        System.out.println("zookeeper  启动成功");
        return client;
    }

}
