package com.hty.iotprivate.cron.timer;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
@Slf4j
public class MyTimer {
    public static void main(String[] args) {
//        Timer timer = new Timer();
        ////在3秒后执行MyTask类中的run方法,后面每10秒跑一次
//        timer.schedule(new MyTask(),3000,1000);
        fun1();
    }
    //fun 指定时间执行
    public static void fun1() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,11);
        calendar.set(Calendar.MINUTE,19);
        calendar.set(Calendar.SECOND,10);
        Timer timer = new Timer();
        timer.schedule(new MyTask(),calendar.getTime(),2000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("add task..");
            }
        },4000,5000);

    }
}
