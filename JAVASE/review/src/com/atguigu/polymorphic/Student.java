package com.atguigu.polymorphic;

/**
 * @Author YangRuiHong
 * @Create 2022-02-11 18:12
 * @Description
 */
public class Student implements People {
    @Override
    public void say() {
        System.out.println("我是学生");
    }
}
