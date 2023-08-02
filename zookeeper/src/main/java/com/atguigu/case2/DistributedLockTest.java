package com.atguigu.case2;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

/**
 * @Author YangRuiHong
 * @Create 2023-02-04 17:24
 * @Description
 */
public class DistributedLockTest {
    public static void main(String[] args) throws Exception {
        final DistributedLock lock1 = new DistributedLock();
        final DistributedLock lock2 = new DistributedLock();
        new Thread(() -> {
            try {
                lock1.zkLock();
                System.out.println("线程1启动，获取到锁");
                Thread.sleep(5000);
                lock1.unZkLock();
                System.out.println("线程1释放锁");
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                lock2.zkLock();
                System.out.println("线程2启动，获取到锁");
                Thread.sleep(5000);
                lock2.unZkLock();
                System.out.println("线程2释放锁");
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
