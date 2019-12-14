package com.hty.iotprivate.cron.quartz.utils;

import com.hty.iotprivate.cron.quartz.Job1;
import lombok.Data;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hutaoying on 2019/12/14
 */
@Component
public class ScheduleUtils {

    @Autowired
    private Scheduler scheduler;
    /**
     * 创建定时任务
     */
    public  void createScheduleJob(String modId,String svcIndex) {
        try {
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(Job1.class).withIdentity(JobKey.jobKey(modId+":"+svcIndex))
                    .usingJobData("JobName",modId+":"+svcIndex)
                    .usingJobData("name","hty")
                    .build();
            String cron = "0/5 * * * * ? ";

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron)
                    .withMisfireHandlingInstructionDoNothing();

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(TriggerKey.triggerKey(modId+":"+svcIndex)).withSchedule(scheduleBuilder).build();

            //放入参数，运行时的方法可以获取
//            jobDetail.getJobDataMap().put("JobName", modId+":"+svcIndex);

            scheduler.scheduleJob(jobDetail, trigger);

//            //暂停任务
//            if (scheduleJob.getStatus() == Constant.ScheduleStatus.PAUSE.getValue()) {
//                pauseJob(scheduler, scheduleJob.getJobId());
//            }
        } catch (SchedulerException e) {
            throw new RuntimeException("创建定时任务失败", e);
        }
    }
}
