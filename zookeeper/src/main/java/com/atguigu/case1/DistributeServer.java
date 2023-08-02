package com.atguigu.case1;

import com.atguigu.constant.Constant;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @Author YangRuiHong
 * @Create 2023-02-04 15:16
 * @Description 服务器动态上下线
 */
public class DistributeServer {

    private ZooKeeper client;

    public static void main(String[] args) throws Exception {
        //1.获取zk连接
        DistributeServer server = new DistributeServer();
        server.getConnect();
        //2.注册服务器到zk集群

        server.regist(args[0]);
        //3.启动业务逻辑（睡觉）
        server.business();
    }

    private void business() throws Exception {
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 注册
     *
     * @param hostName
     * @throws Exception
     */
    private void regist(String hostName) throws Exception {
        //CreateMode.EPHEMERAL_SEQUENTIAL临时节点带序号
        String s = client.create("/servers" + hostName, hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostName + " is online");
    }

    /**
     * 获取连接
     *
     * @throws Exception
     */
    private void getConnect() throws Exception {
        client = new ZooKeeper(Constant.connectionString, Constant.sessionTimeOut, i -> {

        });
    }
}
