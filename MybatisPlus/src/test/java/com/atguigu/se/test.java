package com.atguigu.se;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YangRuiHong
 * @create 2022-09-16 9:46
 * @email Yangrhd@dcits.com
 * @description:
 */
public class test {
    public static void main(String[] args) throws ParseException {
        String str = "2022/7/12";
        SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd");
        Date parse = s.parse(str);
        System.out.println("parse = " + parse);
        System.out.println(parse.toString());
    }
}
