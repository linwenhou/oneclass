package com.venvo.zookeeperstudy.config;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author venvo
 * @date 2021-08-30 15:45
 * @description
 * @modified By
 * @version: jdk1.8
 */
public class TestConfig {

    ZooKeeper zk;

    @Before
    public void conn() {
        zk = ZKUtils.getZk();
    }

    @After
    public void close() {
        try {
            zk.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getConf() {

        WatchCallBack watchCallBack = new WatchCallBack();
        watchCallBack.setZk(zk);

        MyConf myConf = new MyConf();
        watchCallBack.setConf(myConf);


        watchCallBack.aWait();

        //节点不存在
        //节点存在

        while (true) {
            if (myConf.getConf().equals("")) {
                System.out.println("conf diu le....");
                watchCallBack.aWait();
            } else {
                System.out.println("myconf : "+myConf.getConf());
            }

            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
