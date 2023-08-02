package com.atguigu.staticDemo;

import lombok.Data;

/**
 * @author YangRuiHong
 * @create 2022-05-27 11:30
 * @description:
 */
public class StaticTest {
    public static void main(String[] args) {
        Chinese c1 = new Chinese();
        c1.setName("张三");
        c1.setAge(18);
        c1.nation = "中国";
        System.out.println("c1 = " + c1);

        Chinese c2 = new Chinese();
        c2.setName("码农");
        c2.setAge(35);
        System.out.println("c2 = " + c2);
        System.out.println(Chinese.nation);

    }
}


class Chinese {
    private String name;
    private int age;
    static String nation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Chinese{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}