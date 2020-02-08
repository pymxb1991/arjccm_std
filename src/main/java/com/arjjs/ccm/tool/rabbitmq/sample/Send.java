package com.arjjs.ccm.tool.rabbitmq.sample;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static final String QUEUE_NAME = "arj_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        /*//获取连接
        Connection connection = ConnectionUtils.getConnection();
        //从连接中获取一个通道
        Channel channel = connection.createChannel();
        //创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String msg = "你好,sample队列22222";
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());

        System.out.println("send msg" + msg);
        channel.close();
        connection.close();*/
    }
}
