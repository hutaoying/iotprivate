package com.hty.iotprivate.cron.quartz.controller;

import com.alibaba.fastjson.JSONObject;
import com.hty.iotprivate.cron.quartz.utils.ScheduleUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hutaoying on 2019/12/14
 */
@Slf4j
@RestController
@RequestMapping("/schedule")
public class ScheduledController {
    @Autowired
    ScheduleUtils scheduleUtils;
    @GetMapping("/addJob")
    public void insert(@RequestParam String modId,@RequestParam String svcIndex){
//        String modId =jsonObject.getString("modId");
//        String svcIndex =jsonObject.getString("svcIndex");
        log.info(modId);
        log.info(svcIndex);
        scheduleUtils.createScheduleJob(modId,svcIndex);

    }
}
