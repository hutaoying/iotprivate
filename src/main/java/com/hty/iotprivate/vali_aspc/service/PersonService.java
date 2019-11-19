package com.hty.iotprivate.vali_aspc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class PersonService {
    @PostConstruct
    public void init(){

        log.info("init!!!!!!!!!");
    }

    public void addPerson(){


    }

}
