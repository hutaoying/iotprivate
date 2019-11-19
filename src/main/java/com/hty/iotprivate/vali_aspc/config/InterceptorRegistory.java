package com.hty.iotprivate.vali_aspc.config;

import com.hty.iotprivate.vali_aspc.interceptor.FirstInterceptor;
import com.hty.iotprivate.vali_aspc.interceptor.SecondInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

//拦截器注册
@Configuration
public class InterceptorRegistory implements WebMvcConfigurer {

    @Bean   //把我们的token拦截器注入为bean
    public HandlerInterceptor getFirstInterceptor(){
        return new FirstInterceptor();
    }
    @Bean   //把我们的token拦截器注入为bean
    public HandlerInterceptor getSecondInterceptor(){
        return new SecondInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
        List<String> includePathPatterns = new ArrayList<String>();
        includePathPatterns.add("/**");
        // excludePathPatterns 用户排除拦截
        List<String> excludePathPatterns = new ArrayList<String>();
        excludePathPatterns.add("/login");
        registry.addInterceptor(getFirstInterceptor()).addPathPatterns(includePathPatterns).excludePathPatterns(excludePathPatterns);
        registry.addInterceptor(getSecondInterceptor()).addPathPatterns("/model/**");

    }


}
