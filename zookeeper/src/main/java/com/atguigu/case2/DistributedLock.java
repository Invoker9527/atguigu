package com.atguigu.case2;

import com.atguigu.constant.Constant;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author YangRuiHong
 * @Create 2023-02-04 16:41
 * @Description 分布式锁案例
 */
public class DistributedLock {

    private final ZooKeeper zooKeeper;
    private String waitPath;
    /**
     *
     */
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private CountDownLatch waitLatch = new CountDownLatch(1);
    private String currentMode;

    public DistributedLock() throws IOException, InterruptedException, KeeperException {
        //1.获取连接
        zooKeeper = new ZooKeeper(Constant.connectionString, Constant.sessionTimeOut, i -> {
            //countDownLatch   如果连接上zk 可以释放
            if (i.getState() == Watcher.Event.KeeperState.SyncConnected) {
                countDownLatch.countDown();
            }
            //waitLatch  需要释放
            if (i.getType() == Watcher.Event.EventType.NodeDeleted && i.getPath().equals(waitPath)) {
                waitLatch.countDown();
            }
            ;
        });
        //等待zk正常链接后，往下走程序
        countDownLatch.await();
        //2判断根节点/locks存不存在
        Stat exists = zooKeeper.exists("/locks", false);
        if (exists == null) {
            zooKeeper.create("/locks", "locks".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    //对zk加锁
    public void zkLock() throws KeeperException, InterruptedException {
        //创建对应得临时带序号节点
        currentMode = zooKeeper.create("/locks/" + "seq-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        //判断创建的节点是否是最小的序号节点，如果是，获得锁，如果不是，监听他序号前一个节点
        List<String> children = zooKeeper.getChildren("/locks", false);
        //如果有多个节点，需要判断谁最小，如果只有一个值，那就直接获得锁
        if (children.size() == 1) {
            return;
        } else {
            //排序
            Collections.sort(children);
            //获取节点名称 seq-00000000
            String thisNode = currentMode.substring("/locks/".length());
            //通过seq-00000000获取该节点在children集合里的位置
            int index = children.indexOf(thisNode);
            //判断
            if (index == -1) {
                System.out.println("见鬼嘞，数据异常撒");

            } else if (index == 0) {
                //只有一个节点，可以获取锁了
                return;
            } else {
                //需要监听 他前一个节点的变化
                waitPath = "/locks/" + children.get(index - 1);
                zooKeeper.getData(waitPath, true, null);
                //等待监听
                waitLatch.await();
            }
        }
    }

    //解锁
    public void unZkLock() throws KeeperException, InterruptedException {
        //删除节点
        zooKeeper.delete(currentMode, -1);
    }
}
