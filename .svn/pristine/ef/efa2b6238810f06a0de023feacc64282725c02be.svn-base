package com.arjjs.ccm.tool;

import com.arjjs.ccm.tool.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitMQ 生产者
 * 目前平台发送消息都是制定人员发送 所以用dirct模式即可
 */
public class RabbitMQServerTools {

    /**
     *
     * @param queuename 队列名称一般会用用户id作为队列名称
     * @param msg 发布的消息 应为json串
     */
    public static void sendSampleMessage(String queuename, String msg) {
        try {
            //1、获得连接
            Connection connection = ConnectionUtils.getConnection();
            //2、创建通道
            Channel channel = connection.createChannel();
            //3、创建队列声明
            channel.queueDeclare(queuename, false, false, false, null);
            //4、发布消息
//            channel.basicPublish("", queuename, null, msg.getBytes());
//            msg=new String(msg.getBytes("UTF-8"), "UTF-8");
            channel.basicPublish("", queuename, null, msg.getBytes("UTF-8"));
            //5、关闭连接
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
