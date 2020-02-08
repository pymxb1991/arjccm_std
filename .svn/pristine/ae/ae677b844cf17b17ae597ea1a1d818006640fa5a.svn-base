package com.arjjs.ccm.tool.rabbitmq.fadeout;

import com.arjjs.ccm.tool.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv2 {
    private static final String EXCHANGE_NAME = "arj_test";

    public static void main(String[] args) {
        try {
            //1、建立连接
            Connection connection = ConnectionUtils.getConnection();
            //2、创建通道
            Channel channel = connection.createChannel();
            //3、声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            //4、获得queueName?(这个获取的是什么)
            String queueName = channel.queueDeclare().getQueue();
            //5、队列绑定交换机
            channel.queueBind(queueName, EXCHANGE_NAME, "");

            System.out.println("等待接受消息22");
            //6、监听

            DeliverCallback deliverCallback = (consumerTag, delivery)->{
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("delivery222" + message + "");
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag->{});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
