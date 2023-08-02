package com.atguigu.se;

/**
 * @author YangRuiHong
 * @create 2022-07-21 16:07
 * @email Yangrhd@dcits.com
 * @description: -、枚举类的使用
 * 1.枚举类的理解：类的对象只有有限个，确定的，我们称为其为枚举类
 * 2当需要定义一组常量时，强烈建议使用枚举类
 * 3.如果枚举类中只有一个对象，则可以作为单例模式的实现方式
 */
public class SeasonTest {
    public static void main(String[] args) {
        System.out.println(Season.SPRING.getSeasonDesc());
        System.out.println(Season.AUTUMN);
    }
}

//自定义枚举类
class Season {
    //1.声明season对象的属性
    private final String seasonName;

    private final String seasonDesc;

    //2.私有化构造器
    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;

    }

    //3.提供当前枚举类的多个对象
    public static final Season SPRING = new Season("春天", "春暖花开");
    public static final Season SUMMER = new Season("夏天", "夏日炎炎");
    public static final Season AUTUMN = new Season("秋天", "秋高气爽");
    public static final Season WINTER = new Season("冬天", "冰天雪地");

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }

}