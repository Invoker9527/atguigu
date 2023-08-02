package test;


import bean.people;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author YangRuiHong
 * @create 2022-01-06 19:27
 * @description
 */
public class test {
    @Test
    public void test1() {

    }

    public static void main(String[] args) {
        String resources = "bean1.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        people people = context.getBean("people", people.class);
        System.out.println(people.getName());
    }
}
