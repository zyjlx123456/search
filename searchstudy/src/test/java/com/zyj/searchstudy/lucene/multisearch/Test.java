package com.zyj.searchstudy.lucene.multisearch;

import com.zyj.searchstudy.multisearch.CountOperate;

/**
 * Created by yunjie.zyj on 2018/5/15.
 */
public class Test {
    public static void main(String[] args) {
        CountOperate c = new CountOperate();
        Thread t1 = new Thread(c);
        t1.setName("zyj");
        c.setName("zyj2");
        t1.start();
    }
}
