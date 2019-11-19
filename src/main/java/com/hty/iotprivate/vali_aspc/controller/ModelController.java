package com.hty.iotprivate.vali_aspc.controller;

import com.alibaba.fastjson.JSONObject;
import com.hty.iotprivate.vali_aspc.entity.ModelReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/model")
public class ModelController {
    @PostMapping("/addModel")
    public void addModel(@RequestBody JSONObject jsonObject
    ){
//        log.info("modId:{}",modId);
//        log.info("username:{}",username);
        log.info("jsonObject:{}",jsonObject);
    }
    @GetMapping("/getModelInfo")
    public void getModelInfo(String modId,String username){
//        log.info("modelReq:{}",modelReq);
        log.info("modId:{}",modId);
        log.info("username:{}",username);
    }

    @GetMapping("/getModelInfo/{modId}")
    public void getModelOne(@PathVariable String modId,String username){
//        log.info("modelReq:{}",modelReq);
        log.info("modId:{}",modId);
        log.info("username:{}",username);
    }

}
