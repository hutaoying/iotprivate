package com.hty.iotprivate.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExecuteScript {
    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        String url = "http://lvzhou-at.h3c.com:8000/iot/findall?";
        JSONObject jsonObject = new JSONObject();
        List list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        JSONArray jj = JSONArray.parseArray(JSON.toJSONString(list));
        log.info(jj.toJSONString());
        jsonObject.put("tagName",jj);
//        jsonObject.put("class",23);
        log.info(jsonObject.toString());

        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath(); // 获取targe路径
//            System.out.println(path);
            path = "D:/MyDownloads/Demojava/iotprivate/src/main/resources/static/InitScript.js";
//            File file = new File(path);
//            File[] files= file.listFiles();
//            for (File fil: files
//                 ) {
//                System.out.println(fil.getName());
//            }

            // FileReader的参数为所要执行的js文件的路径
            engine.eval(new FileReader(path));
            Invocable inv = (Invocable) engine;
            Object res = (Object) inv.invokeFunction("merge",url,jsonObject);
            log.info("res:{}", res.toString());
            JSONObject jsonObject1 = (JSONObject) JSONObject.parse(res.toString());
            if (jsonObject1.getJSONObject("body").isEmpty()){
                log.info("empty");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
