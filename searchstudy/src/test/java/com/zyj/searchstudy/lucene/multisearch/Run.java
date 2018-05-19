package com.zyj.searchstudy.lucene.multisearch;

import com.zyj.searchstudy.multisearch.MyThread;

/**
 * Created by yunjie.zyj on 2018/5/16.
 */
public class Run {
    public static void main(String[] args) {
        try{
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
            System.out.println(thread.currentThread().getName());
            System.out.println("1 = "+thread.interrupted());
            System.out.println("2 = "+thread.interrupted());
        }catch (InterruptedException e){

        }
    }
}
