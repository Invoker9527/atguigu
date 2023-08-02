package com.atguigu.se;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YangRuiHong
 * @create 2022-07-21 16:40
 * @email Yangrhd@dcits.com
 * @description:
 */
public enum Time {
    MORING("早上", "6-11"),
    NOON("中午", "11-14"),
    AFTERNOON("下午", "14-18"),
    NIGHT("晚上", "18-24"),
    WEE("凌晨", "24-6");
    private final String TIME;
    private final String DES;

    private Time(String TIME, String DES) {
        this.TIME = TIME;
        this.DES = DES;

    }

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("1", "1");
        map.put("3", "3");
        map.put("2", "2");
        System.out.println("map = " + map);

    }
}
