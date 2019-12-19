package com.hty.iotprivate.cron.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by hutaoying on 2019/12/14
 */
@Component
@Slf4j
//@Configurable
@EnableScheduling
public class SendMail {
    @Scheduled(cron="*/15 * * * * *")
    public void reportCurrent(){
        log.info("email send");

    }
}
