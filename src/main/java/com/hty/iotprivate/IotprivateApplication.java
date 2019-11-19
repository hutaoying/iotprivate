package com.hty.iotprivate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = "com.hty.iotprivate.vali_aspc")
@SpringBootApplication
public class IotprivateApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotprivateApplication.class, args);
    }

}
