package com.hty.iotprivate.vali_aspc.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.hty.iotprivate.vali_aspc.aspect.AuthCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Slf4j
public class SecondInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("SecondInterceptor");
        /**
         *  拦截器实现自定义注解拦截
         *   AuthCheck authCheck = ((HandlerMethod)handler).getMethodAnnotation(AuthCheck.class);
         */
//        AuthCheck authCheck = ((HandlerMethod)handler).getMethodAnnotation(AuthCheck.class);

        log.info(handler.getClass().getSimpleName());// HandlerMethod

        /**
         * handler  () 可以获取方法的信息  如获取方法上的注解（这一点区别于Filter）
         * 返回json  1 response
         *  2  重定向
         */
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        PrintWriter out = response.getWriter();
//        JSONObject res = new JSONObject();
//        res.put("code", 1);
//        res.put("message", "false");
//        out.append(res.toString());

//        request.getRequestDispatcher("/error").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    log.info("second  postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
     log.info("second  afterCompletion");
    }
}
