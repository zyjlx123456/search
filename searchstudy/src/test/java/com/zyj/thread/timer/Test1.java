package com.zyj.thread.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * Created by yunjie.zyj on 2018/5/19.
 */
public class Test1 {
    public static void main(String[] args) throws Exception{
        System.out.println("time:"+new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,10);
        Date runDate = calendar.getTime();
        MyTask task = new MyTask();
        Timer timer = new Timer(true);
        timer.schedule(task,runDate);
        Thread.sleep(11000l);
        System.out.println("time:"+new Date());
    }
}
