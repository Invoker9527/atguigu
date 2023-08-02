package com.atguigu.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @author YangRuiHong
 * @create 2022-05-24 21:13
 * @description:
 */
public class JedisDemo1 {


    @Test
    public void demo1() {
        //创建jedis对象
        Jedis jedis = new Jedis("192.168.198.129", 6379);

        System.out.println(jedis.keys("*").size());
        for (String key : jedis.keys("*")) {
//            获取所有的key
            System.out.println(key);
        }
        //获取key为string的value值
        System.out.println(jedis.get("string"));
    }

    @Test
    public void stringDemo() {
        //创建jedis对象
        Jedis jedis = new Jedis("192.168.198.129", 6379);
        //设置值
        jedis.set("son", "曹嘉骏");
        //获取值
        String son = jedis.get("son");
        System.out.println(son);
        //查看过期时间(-1永不过期)
        Long son1 = jedis.ttl("son");
        System.out.println("son1 = " + son1);
        //查看key是否存在
        Boolean son2 = jedis.exists("son");
        System.out.println("son2 = " + son2);
        //查看key为father的存不存在
        Boolean father = jedis.exists("father");
        System.out.println("father = " + father);
        //设置过期时间
        //jedis.expire("son",10);
        //设置多个值
        jedis.mset("car", "兰博基尼", "room", "汤臣一品");
        //获取值
        List<String> car = jedis.mget("car", "room");
        for (String s : car) {
            System.out.println(s);
        }

    }

    @Test
    public void listDemo() {
        //创建jedis对象
        Jedis jedis = new Jedis("192.168.198.129", 6379);
        jedis.lpush("key1", "lucy", "marry", "jack");
        //取出所有值
        List<String> list = jedis.lrange("key1", 0, -1);
        System.out.println("list = " + list);
    }

    @Test
    public void setDemo() {
        //创建jedis对象
        Jedis jedis = new Jedis("192.168.198.129", 6379);
        //其不能存在重复元素，会被覆盖
        jedis.sadd("name", "lucy", "jack");
        Set<String> name = jedis.smembers("name");
        for (String s : name) {
            System.out.println(s);
        }
        //移除元素
        Long srem = jedis.srem("name", "lucy");

        for (String s : jedis.smembers("name")) {
            System.out.println(s);
        }
    }

    @Test
    public void hashDemo() {
        //创建jedis对象
        Jedis jedis = new Jedis("192.168.198.129", 6379);
        jedis.hset("users", "name", "lucy");
        String hget = jedis.hget("users", "name");
        System.out.println("hget = " + hget);
        Map map = new HashMap();
        map.put("id", "1");
        map.put("name", "Jack");
        map.put("age", "19");
        jedis.hmset("stus", map);
        List<String> stus = jedis.hvals("stus");
        System.out.println("stus = " + stus);
    }

    @Test
    public void test() {
        Jedis jedis = new Jedis("192.168.198.129", 6379);
        List<String> users = jedis.hvals("users");
        jedis.close();
        System.out.println(users);

    }

    public String VerificationCode(String tel) {
        String code = "";
        Random r = new Random();
        for (int i = 0; i < 6; i++) {

            code = code + r.nextInt(10);
        }
        Jedis jedis = new Jedis("192.168.198.129", 6379);
        jedis.set(tel, code);
        jedis.expire(tel, 120);
        jedis.close();
        return code;
    }

    public String getCode(String tel) {
        Jedis jedis = new Jedis("192.168.198.129", 6379);
        jedis.close();
        return jedis.get(tel);

    }

    public static void main(String[] args) {
        String scode = new JedisDemo1().VerificationCode("18187894451");
        System.out.println("scode = " + scode);
        Jedis jedis = new Jedis("192.168.191.131", 6379);
        String gcode = jedis.get("18187894451");
        System.out.println("gcode = " + gcode);
        System.out.println(getCode());
    }

    /**
     * 生成验证码
     *
     * @return
     */
    public static String getCode() {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += random.nextInt(10);
        }
        return code;
    }

    //每个手机每天只能发送三次，验证码放到Redis中，设置过期时间
    public static void verifyCode(String tel) {
        Jedis jedis = new Jedis("192.168.198.131", 6379);
        //拼接key
        //手机发送验证码
        String countKey = "verifyCode" + tel + "count";
        String codeKey = "verifyCode" + tel + "code";
        //每个手机每天只能发送三次
        String count = jedis.get(countKey);
        //没有发送次数（第一次发送），发送次数设置为1
        if (count == null) {

            jedis.setex(countKey, 60 * 60 * 24, "1");
        } else if (Integer.parseInt(count) <= 2) {
            //发送次数加一
            jedis.incr(countKey);
        } else if (Integer.parseInt(countKey) > 2) {
            System.out.println("今天的发送次数已经超过三次");
            jedis.close();
        }
        //发送验证码到Redis里
        String Vcode = getCode();
        jedis.setex(codeKey, 120, Vcode);
        jedis.close();
    }

    public static void getRedisCode(String tel, String code) {
        //从Redis中获取验证码
        Jedis jedis = new Jedis("192.168.191.131", 6379);
        String codeKey = "verifyCode" + tel + "code";
        String redisKey = jedis.get(codeKey);
        if (redisKey.equals(code)) {
            System.out.println("成功");
        } else {
            System.out.println("失敗");
        }
        jedis.close();
    }

    @Test
    public void process() {
        //模拟验证码发送
        verifyCode("18187894451");
    }
}
