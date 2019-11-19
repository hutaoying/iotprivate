package com.hty.iotprivate.vali_aspc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ErrorController {
    @GetMapping("/error")
    public String error(){
        log.info("error");
        return "FAILED";
    }
}
