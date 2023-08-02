package com.atguigu.polymorphic;

/**
 * @Author YangRuiHong
 * @Create 2022-02-11 18:13
 * @Description
 */
public class Teacher implements People {
    @Override
    public void say() {
        System.out.println("我是老师");
    }
}
