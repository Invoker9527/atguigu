package com.atguigu.debug;

/**
 * @Author YangRuiHong
 * @Create 2022-02-11 17:51
 * @Description 调试程序的两种方法：system.out.println()或者debug
 */
public class DebugTest1 {
    public static void main(String[] args) {
        int i = 10;
        int j = 20;
        System.out.println("i=" + i + ", j=" + j);
        DebugTest1 test1 = new DebugTest1();
        int max = test1.getMax(i, j);
        System.out.println("max =" + max);
    }

    private int getMax(int k, int m) {
        int max = 0;
        if (k < m) {
            max = k;
        } else {
            max = m;
        }
        return max;
    }
}
