package com.venvo.zookeeperstudy.config;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @author venvo
 * @date 2021-08-30 15:33
 * @description
 * @modified By
 * @version: jdk1.8
 */
public class ZKUtils {

    private static ZooKeeper zk;
    private static String address = "172.16.81.125:2181,172.16.81.126:2181,172.16.81.127:2181,172.16.81.128:2181/testConf";

    private static DefaultWatch watch = new DefaultWatch();

    private static CountDownLatch init = new CountDownLatch(1);

    public static ZooKeeper getZk() {

        try {
            zk = new ZooKeeper(address, 1000, watch);
            watch.setCc(init);
            init.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        return zk;
    }

}
