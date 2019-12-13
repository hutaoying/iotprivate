package com.hty.iotprivate.cron.schedule;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@Slf4j
public class MyScheduled {
    public static void main(String[] args) {
        //DelegatedScheduledExecutorService
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                log.info("start  scheduleWithFixedDelay");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                log.info("kkkk---run");
//                System.out.println("run "+ System.currentTimeMillis());
            }
        }, 1000, 2500, TimeUnit.MILLISECONDS);

        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("start scheduleAtFixedRate");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                log.info("kkkk---run");
//                System.out.println("run "+ System.currentTimeMillis());
            }
        }, 1000, 2500, TimeUnit.MILLISECONDS);
    }
}
