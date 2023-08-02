package com.atguigu.zk;

import com.atguigu.constant.Constant;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author YangRuiHong
 * @Create 2023-02-04 14:21
 * @Description
 */

public class ZkClient {
    private ZooKeeper zkCli;

    /**
     * 初始化,注解需为before，不然没有在其他方法执行前初始化会报空指针
     */
    @Before
    public void init() throws Exception {
        zkCli = new ZooKeeper(Constant.connectionString, Constant.sessionTimeOut, i -> {
            System.out.println("***************************");
            List<String> children = null;
            try {
                children = zkCli.getChildren("/", true);
                children.stream().forEach(System.out::println);


            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("****************************");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    //创建节点
    @Test
    public void create() throws KeeperException, InterruptedException {
        //"/atguigu" ：数据存储路径  "ss.avi".getBytes()：因为其存储的是字节类型，所以将其转化为字节  ZooDefs.Ids.OPEN_ACL_UNSAFE:权限：所有人都可以访问；  CreateMode.PERSISTENT：节点类型：持久节点
        String s = zkCli.create("/atguigu3", "ss.avi".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 获取某个节点下的节点名称
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        //若第二个参数为true，则开启观察，其会执行init里的监听器方法
        List<String> children = zkCli.getChildren("/", true);
        children.stream().forEach(System.out::println);
    }

    /**
     * 判断节点是否存在
     *
     * @throws Exception
     */
    @Test
    public void exist() throws Exception {
        Stat exists = zkCli.exists("/atguigu", false);
        System.out.println(exists == null ? "not exist" : "exist");

    }
}

