package com.venvo.zookeeperstudy.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.client.ZooKeeperSaslClient;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.ZooKeeperSaslServer;

import java.util.concurrent.CountDownLatch;

/**
 * @author venvo
 * @date 2021-08-29 16:29
 * @description
 * @modified By
 * @version: jdk1.8
 */
public class ZK {

    public static void main(String[] args) throws Exception {

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zk = new ZooKeeper("172.16.81.125:2181,172.16.81.126:2181,172.16.81.127:2181,172.16.81.128:2181", 3000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                Event.KeeperState state = event.getState();
                final Event.EventType type = event.getType();
                final String path = event.getPath();
                System.out.println("new zk Watch:" + event.toString());

                switch (state) {
                    case Unknown:
                        break;
                    case Disconnected:
                        break;
                    case NoSyncConnected:
                        break;
                    case SyncConnected:
                        System.out.println("connected");
                        countDownLatch.countDown();
                        break;
                    case AuthFailed:
                        break;
                    case ConnectedReadOnly:
                        break;
                    case SaslAuthenticated:
                        break;
                    case Expired:
                        break;
                    case Closed:
                        break;
                }
            }
        });

        countDownLatch.await();
        ZooKeeper.States state = zk.getState();
        switch (state) {
            case CONNECTING:
                System.out.println("ing......");
                break;
            case ASSOCIATING:
                break;
            case CONNECTED:
                System.out.println("ed........");

                break;
            case CONNECTEDREADONLY:
                break;
            case CLOSED:
                break;
            case AUTH_FAILED:
                break;
            case NOT_CONNECTED:
                break;
        }

        //sessionId250949652166279168
//        System.out.println("sessionId" + zk.getSessionId());

        String pathName = zk.create("/ooxx", "olddata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);



        final Stat stat=new Stat();
        byte[] node = zk.getData("/ooxx", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("getData watch: "+event.toString());
                try {
                    //true   default Watch  被重新注册   new zk的那个watch
                    zk.getData("/ooxx",this  ,stat);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, stat);

        System.out.println(new String(node));

        //触发回调
        Stat stat1 = zk.setData("/ooxx", "newdata".getBytes(), 0);
        //还会触发吗？
        Stat stat2 = zk.setData("/ooxx", "newdata01".getBytes(), stat1.getVersion());

        System.out.println("-------async start----------");
        zk.getData("/ooxx", false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("-------async call back----------");
                System.out.println(ctx.toString());
                System.out.println(new String(data));

            }

        },"abc");
        System.out.println("-------async over----------");



        Thread.sleep(2222222);
    }
}
