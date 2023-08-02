package com.atguigu.test;

/**
 * @Author YangRuiHong
 * @Create 2022-02-02 21:41
 * @Description
 */
public class test {


    public static void main(String[] args) {
        new thread1().start();
        new thread2().start();
    }
}

class thread1 extends Thread {

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {

            if (i % 2 == 0)
                System.out.println(i);
        }
    }

}

class thread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            if (i % 2 != 0) {
                System.out.println(i);
            }
        }
    }
}