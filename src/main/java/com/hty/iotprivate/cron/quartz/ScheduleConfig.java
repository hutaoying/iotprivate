//package com.hty.iotprivate.cron.quartz;
//
//import org.quartz.Scheduler;
//import org.quartz.spi.JobFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//
///**
// * Created by hutaoying on 2019/12/14
// */
//@Configuration
//public class ScheduleConfig {
//
//    @Autowired(required = true)
//    private JobFactory jobFactory;
//
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean() {
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        schedulerFactoryBean.setJobFactory(jobFactory);
//        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
//        schedulerFactoryBean.setOverwriteExistingJobs(true);
//        schedulerFactoryBean.setStartupDelay(1);
//        return schedulerFactoryBean;
//    }
//
//    @Bean
//    public Scheduler scheduler() {
//        return schedulerFactoryBean().getScheduler();
//    }
//
//
//}
