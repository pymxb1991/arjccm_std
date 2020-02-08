package com.arjjs.ccm.tool.rabbitmq.fadeout;

import com.arjjs.ccm.tool.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private static final String EXCHANGE_NAME = "arj_test";

    public static void main(String[] args) {
        try {
            //1、获取连接
            Connection con = ConnectionUtils.getConnection();
            //2、创建channel
            Channel channel = con.createChannel();
            //3、声明交换机名字
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            //4、发送消息
            String message = "测试消息23434";
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            System.out.println("send!");
            //5、关闭通道
            channel.close();
            con.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
