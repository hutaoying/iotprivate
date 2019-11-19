package com.hty.iotprivate.rabbitmq.utils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;



import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnUtils {
    public static Connection getConn() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        // 设置rabbitmq的服务器地址
        factory.setHost("192.168.0.200");
        // 设置rabbitmq的用户名和密码，默认都是guest，
        // 但是在前面的[rabbitmq1-概述及其使用docker安装]
        // (https://blog.csdn.net/weixin_42849915/article/details/81977968)
        // 中有截图讲如何设置这个东西，不设置也是可以的，但是还是建议大家设并设置权限
        factory.setUsername("fkxuexi");
        factory.setPassword("fkxuexi");
        factory.setVirtualHost("spring_cloud");
        factory.setPort(AMQP.PROTOCOL.PORT);
        Connection connection = factory.newConnection();
        return connection;
    }
}
