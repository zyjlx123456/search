package com.zyj.thread.timer;

import java.util.Date;
import java.util.TimerTask;

/**
 * Created by yunjie.zyj on 2018/5/19.
 */
public class MyTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("time is :"+new Date());
    }
}
