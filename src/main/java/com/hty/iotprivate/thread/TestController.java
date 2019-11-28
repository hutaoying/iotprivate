package com.hty.iotprivate.thread;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.nio.channels.ServerSocketChannel;

@Slf4j
@RestController
public class TestController {
    @PostMapping("/modelInfo")
    public String getModelInfo(String user,String modId){
        log.info("user:{}",user);
        log.info("modId:{}",modId);
        return "sss";
    }
    @PostMapping("/service/invoke/{svcindex}/{back_In}")
    public String northCalling(@Validated @NotBlank String user, String modId, @RequestBody JSONObject jsonObject, @PathVariable String svcindex, @PathVariable String back_In){
        log.info("user:{}",user);
        log.info("modId:{}",modId);
        log.info("svcindex:{}",svcindex);
        log.info("back_In:{}",back_In);
        log.info("jsonObject:{}",jsonObject);
        return "sss";
    }

}
