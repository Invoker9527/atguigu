package com.atguigu.se;

/**
 * @author YangRuiHong
 * @create 2022-07-21 16:29
 * @email Yangrhd@dcits.com
 * @description:
 */
public class SeasonTest1 {
    public static void main(String[] args) {
        System.out.println(Season1.SUMMER);
    }
}

//自定义枚举类
enum Season1 {
    //3.提供当前枚举类的多个对象,多个对象之间用逗号隔开
    SPRING("春天", "春暖花开"),
    SUMMER("夏天", "夏日炎炎"),
    AUTUMN("秋天", "秋高气爽"),
    WINTER("冬天", "冰天雪地");

    //1.声明season对象的属性
    private final String seasonName;

    private final String seasonDesc;

    //2.私有化构造器
    private Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;

    }


    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }


}