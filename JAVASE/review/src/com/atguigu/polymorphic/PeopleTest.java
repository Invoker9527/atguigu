package com.atguigu.polymorphic;

/**
 * @Author YangRuiHong
 * @Create 2022-02-11 18:13
 * @Description
 */
public class PeopleTest {
    public static void main(String[] args) {
        People student = new Student();
        student.say();
        People teacher = new Teacher();
        teacher.say();
    }
}
