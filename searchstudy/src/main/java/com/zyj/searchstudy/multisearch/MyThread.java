package com.zyj.searchstudy.multisearch;

/**
 * Created by yunjie.zyj on 2018/5/16.
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5000; i++) {
            System.out.println("i=" + (i + 1));
        }
    }
}
