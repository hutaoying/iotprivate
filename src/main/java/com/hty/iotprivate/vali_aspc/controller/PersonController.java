package com.hty.iotprivate.vali_aspc.controller;

import com.alibaba.fastjson.JSONObject;
import com.hty.iotprivate.vali_aspc.aspect.AuthCheck;
import com.hty.iotprivate.vali_aspc.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 三种拦截的区别
 *
 * filter 可以得到 请求和响应 但是拿不到 方法的信息
 * interceptor 可以得到 请求和响应 也能拿到方法的信息 但是拿不到参数
 * aspect     可以拿到方法的信息和参数 但是拿不到请求和响应
 *
 * 顺序  
 * 正常时过滤器 拦截器 切片（aspect）
 * 异常时  切片（如果抛出）->ControllerAdvice->interceptor->filter
 */
@RestController("/person")
@Slf4j
public class PersonController {
    @Autowired
    PersonService personService;
//    @AuthCheck("A")
    @GetMapping("/addPerson")
    public void addPerson(@RequestParam String userName){
        log.info("userName:{}",userName);
//        personService.addPerson();
        log.info("addperson");
    }

    @PostMapping("/updatePerson")
    public void updatePerson(
            String username, String password
//                             @RequestBody JSONObject jsonObject
 )
    {
        log.info("username:{}",username);
        log.info("password:{}",password);
//        log.info("jsonObject:{}",jsonObject);
    }

}
