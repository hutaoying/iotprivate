package com.hty.iotprivate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan(basePackages = "com.hty.iotprivate.vali_aspc")
@SpringBootApplication
@EnableScheduling//启用定时任务的配置
public class IotprivateApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotprivateApplication.class, args);
    }

}
