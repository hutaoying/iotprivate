package com.hty.iotprivate.cron.quartz;

/**
 * Created by hutaoying on 2019/12/14
 */

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.Date;

@Slf4j
@Data
public class Job1 implements Job {
    //属性传递参数  set方法
    private String name;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMapf = jobExecutionContext.getJobDetail().getJobDataMap();
//        log.info("jobMap:{}",jobDataMap);
        log.info("jobMap:{}",jobDataMapf.keySet());
        log.info("JobName:{}",name);
        // 执行响应的任务.
        System.out.println("HelloJob.execute,"+new Date());
    }
}