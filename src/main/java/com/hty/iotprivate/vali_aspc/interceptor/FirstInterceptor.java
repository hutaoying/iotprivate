package com.hty.iotprivate.vali_aspc.interceptor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * interceptor 可以得到 请求和响应 也能拿到方法的信息 但是拿不到参数
 * 基于java反射
 */

@Slf4j
public class FirstInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("FirstInterceptor");
        log.info("url:{}",request.getRequestURI());
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        PrintWriter out = response.getWriter();
//        JSONObject res = new JSONObject();
//        res.put("code", 1);
//        res.put("message", "false");
//        out.append(res.toString());
        return true;
//        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    log.info("first postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    log.info("first afterCompletion");
    }
}
