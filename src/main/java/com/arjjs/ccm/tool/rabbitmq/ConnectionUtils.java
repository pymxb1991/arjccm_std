package com.arjjs.ccm.tool.rabbitmq;

import com.arjjs.ccm.common.config.Global;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtils {
    /**
     * 获取MQ的连接
     * @return
     *
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //设置地址
        factory.setHost(Global.getConfig("RABBIT_MQ_HOST"));
        factory.setPort(Integer.parseInt(Global.getConfig("RABBIT_MQ_PORT")));
        factory.setUsername(Global.getConfig("RABBIT_MQ_USERNAME"));
        factory.setPassword(Global.getConfig("RABBIT_MQ_PASSWORD"));

        //设置vhost
        factory.setVirtualHost("/");

        return factory.newConnection();

    }

}
