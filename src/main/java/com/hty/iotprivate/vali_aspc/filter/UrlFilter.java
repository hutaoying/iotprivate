package com.hty.iotprivate.vali_aspc.filter;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Enumeration;

/**
 * 拦截器中如果读取了body的数据 在controller中获取不到数据 如何解决？
 * 利用HttpServletRequestWrapper + Filter  解决 body数据（流数据）读取一次的问题
 * 过滤器 基于函数回调
 */
//filter 可以得到 请求和响应 但是拿不到 方法的信息
@Slf4j
//@WebFilter(filterName = "urlFilter", urlPatterns = "/*", initParams = @WebInitParam(name = "keya", value = "bbb"))
public class UrlFilter implements Filter {
    private FilterConfig filterConfig = null;
    String paramValue = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        filterConfig.
        this.filterConfig = filterConfig;
        Enumeration enumeration = filterConfig.getInitParameterNames();
        if (enumeration.hasMoreElements()) {
            Object o = enumeration.nextElement();
            log.info(o.toString());
            log.info(filterConfig.getInitParameter((String) o));
        }
//        filterConfig.
//       log.info(filterConfig.getServletContext().getContextPath());

        log.info("----------------------->过滤器被创建:{}", filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        boolean flag = false;
        log.info("method:{}", req.getMethod());
        if ("GET".equals(req.getMethod())) {
            Enumeration enumeration = req.getParameterNames();
            if (enumeration.hasMoreElements()) {
                String o = (String) enumeration.nextElement();
                log.info(o);
                log.info(req.getParameter(o));
            }
        } else if ("POST".equals(req.getMethod())) {
            //post 通过流方式读取数据   流只能被读取一次
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = null;
            try {
                InputStream inputStream = req.getInputStream();
                if (inputStream != null) {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    char[] charBuffer = new char[128];
                    int bytesRead = -1;
                    while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                        stringBuilder.append(charBuffer, 0, bytesRead);
                    }
                } else {
                    stringBuilder.append("");
                }
            } catch (IOException ex) {
                throw ex;
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException ex) {
                        throw ex;
                    }
                }
            }
            log.info("body:{}", stringBuilder.toString());
        }
        //不通过  filter 返回json
        if (!flag) {
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.setContentType("application/json; charset=utf-8");
            PrintWriter out = servletResponse.getWriter();
            JSONObject res = new JSONObject();
            res.put("code", 1);
            res.put("message", "false");
            out.append(res.toString());
        } else {
            //通过
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

        System.out.println("----------------------->过滤器被销毁");
    }
}

