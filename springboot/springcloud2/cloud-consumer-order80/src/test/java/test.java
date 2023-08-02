import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author YangRuiHong
 * @Create 2022-02-04 14:29
 * @Description
 */
public class test {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String sex;
        System.out.println("请输入你的性别：");
        sex = scan.next();
        System.out.println("请输入您的年龄：");
        int age = scan.nextInt();
        System.out.println("您是否饮酒：");
        String drinking = scan.next();
        System.out.println("您是否抽烟：");
        String smoking = scan.next();
        System.out.println("是否进行体育锻炼：");
        String sport = scan.next();
        System.out.println("请输入您的身高(m)：");
        double height = scan.nextDouble();
        System.out.println("请输入您的体重（kg）：");
        double weight = scan.nextDouble();
        double BMI = weight / (height * height);
        double num1 = 0;
        if (sex.equals("男")) {
            num1 = 1.1;
        } else {
            num1 = 0.8;
        }
        double num2 = 0;
        if (age >= 18 && age < 30) {
            num2 = 0.4;
        } else if (age >= 30 && age < 45) {
            num2 = 0.8;

        } else if (age >= 45 && age < 60) {
            num2 = 1.6;
        } else if (age >= 60) {
            num2 = 1.8;
        }


        double num3 = 0;
        if (BMI < 18.5) {
            num3 = 0.9;

        } else if (BMI >= 18.5 && BMI <= 23.9) {
            num3 = 1.1;

        } else if (BMI > 23.9 && BMI < 28) {
            num3 = 1.3;
        } else if (BMI >= 28) {
            num3 = 1.8;
        }
        //大于一减一作为相加项，小于1相乘，，作为另一个相加项
        ArrayList<Double> big = new ArrayList<Double>();
        ArrayList<Double> smal = new ArrayList<Double>();
        if (num1 > 1) {
            big.add(num1);
        } else {
            smal.add(num1);
        }
        if (num2 > 1) {
            big.add(num2);
        } else {
            smal.add(num2);
        }
        if (num3 > 1) {
            big.add(num3);
        } else {
            smal.add(num3);
        }
        double sum = 0;
        for (Double aDouble : big) {
            sum += aDouble - 1;
        }
        double take = 1;
        for (Double aDouble : smal) {
            take *= aDouble;
        }

        System.out.println("您的健康危险分数为：" + (sum + take));

    }

    @Test
    public void test() {
        System.out.println(60 / (1.78 * 1.78));
    }

    @Test
    public void test2() {

        System.out.println((68 / (1.79 * 1.79)));
    }


}
