import com.atguigu.springboot.HelloWorldMain;
import com.atguigu.springboot.bean.User;
import com.atguigu.springboot.mapper.UserMapper;
import com.atguigu.springboot.mapper.testMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author YangRuiHong
 * @create 2022-07-18 21:41
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloWorldMain.class)
public class MybatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {

        System.out.println(userMapper);
        //参数为一个条件构造器，通过条件构造器查询一个list集合，如没有条件，则可以设置null为参数
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    public static void main(String[] args) {
        new MybatisPlusTest().testSelectList();
    }
}
