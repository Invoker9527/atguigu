package com.atguigu.case1;

import com.atguigu.constant.Constant;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author YangRuiHong
 * @Create 2023-02-04 15:33
 * @Description 服务器动态上下线
 */
public class DistributeClient {


    private ZooKeeper zk;

    public static void main(String[] args) throws Exception {
        //1.获取zk连接
        DistributeClient client = new DistributeClient();
        client.getConnect();
        //2.监听/servers下面子节点的增加和删除
        client.getServersList();
        client.business();


    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private void getServersList() throws Exception {
        List<String> childrens = zk.getChildren("/servers", true);
        ArrayList<String> servers = new ArrayList<>();
        childrens.stream().forEach(i -> {
            try {
                byte[] data = zk.getData("/servers/" + i, false, null);
                servers.add(new String(data));
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        System.out.println(servers);
    }

    private void getConnect() throws Exception {
        zk = new ZooKeeper(Constant.connectionString, Constant.sessionTimeOut, i -> {
            try {
                getServersList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
