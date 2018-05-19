package com.zyj.searchstudy.multisearch;

/**
 * Created by yunjie.zyj on 2018/5/15.
 */
public class CountOperate extends Thread {
    public CountOperate() {
        System.out.println("begin");
        System.out.println("Thread.getName" + Thread.currentThread().getName());
        System.out.println("this.getName" + this.getName());
        System.out.println("end");
    }


    public void run() {
        System.out.println("run -begin");
        System.out.println("Thread.getName" + Thread.currentThread().getName());
        System.out.println("this.getName" + this.getName());
        System.out.println("run -end");
    }
}
