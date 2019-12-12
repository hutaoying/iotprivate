package com.hty.iotprivate.cron.timer;

import lombok.extern.slf4j.Slf4j;
import java.util.TimerTask;

@Slf4j
public class MyTask  extends TimerTask {
    @Override
    public void run(){
        log.info("timer start test..");
    }
}
