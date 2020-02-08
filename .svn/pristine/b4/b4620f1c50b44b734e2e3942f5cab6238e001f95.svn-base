package com.arjjs.ccm.tool;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.modules.ccm.rest.web.CcmRestEvent;
import com.google.common.collect.Maps;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;

/**
 * 工具类中的基础方法： 1、创建rabbitMQ连接 2、向rabbitMQ发送数据
 */
public class RabbitMQTools {

	public static Map<String, OrderRabbitMQInfo> orderRabbitMQInfoMap = new ConcurrentHashMap<String, OrderRabbitMQInfo>();
	public static boolean connectStatus;
	private static Channel channel;
	private static Connection connection;

	/**
	 * @方法描述：初始化连接rabbitMQ
	 * @throws Exception
	 * @throws IOException
	 */
	public void initRabbitMQ() {

		try {
			List<String> list = CcmRestEvent.clearTimeoutClient1();
			if (list != null && list.size() != 0) {
				for (String queue : list) {
					channel.queueDelete(queue);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(Global.getConfig("RABBIT_MQ_HOST"));
		factory.setPort(Integer.parseInt(Global.getConfig("RABBIT_MQ_PORT")));
		factory.setUsername(Global.getConfig("RABBIT_MQ_USERNAME"));
		factory.setPassword(Global.getConfig("RABBIT_MQ_PASSWORD"));
		if (!connectStatus) {
			try {
				connection = factory.newConnection();
				channel = connection.createChannel();// 创建通道
				connectStatus = true;
				channel.queueDeclare(Global.getConfig("DATABASE_BOOK_QUEUE_NAME"), true, false, false, null);  
				System.out.println("创建rabbitMQ连接成功!");
			} catch (IOException e) {
				System.out.println("创建rabbitMQ连接失败!");
				connectStatus = false;
				e.printStackTrace();
			} catch (TimeoutException e) {
				System.out.println("创建rabbitMQ连接失败!");
				connectStatus = false;
				e.printStackTrace();
			}
		}
	}

	/**
	 * @方法描述：往rabbitMQ发送数据
	 */
	public static void sendMessage(String clientId, String message) {
		OrderRabbitMQInfo orderRabbitMQInfo = orderRabbitMQInfoMap.get(clientId);
		if(orderRabbitMQInfo != null) {			
			channel = orderRabbitMQInfo.getChannel();
		}
		try {
			if (channel == null) {
				if(connection == null) {
					try {
						connection = RabbitMQTools.getConnection();
						connectStatus = true;
						System.out.println("创建rabbitMQ连接成功!");
					} catch (IOException e) {
						System.out.println("创建rabbitMQ连接失败!");
						connectStatus = false;
						e.printStackTrace();
					} catch (TimeoutException e) {
						System.out.println("创建rabbitMQ连接失败!");
						connectStatus = false;
						e.printStackTrace();
					}
				}
				channel = connection.createChannel();// 创建通道
				channel.queueDeclare(Global.getConfig("DATABASE_BOOK_QUEUE_NAME"), true, false, false, null);
				if (orderRabbitMQInfoMap.get(clientId) != null) {// 只订阅了设备 防止报空指针异常
					orderRabbitMQInfoMap.get(clientId).setChannel(channel);// 将通道放入全局Map中,方便取消订阅时关闭通道
				}
			}
			channel.basicPublish("", clientId, null, message.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws IOException,TimeoutException {
        //定义一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(Global.getConfig("RABBIT_MQ_HOST"));
		factory.setPort(Integer.parseInt(Global.getConfig("RABBIT_MQ_PORT")));
		factory.setUsername(Global.getConfig("RABBIT_MQ_USERNAME"));
		factory.setPassword(Global.getConfig("RABBIT_MQ_PASSWORD"));
        return factory.newConnection();
    }
	
	public static String getRmqUrl(){
		String host = Global.getConfig("RABBIT_MQ_HOST");
		return "ws://"+host+":15674/ws";
	}

	public static void sendMessageToAll(String message){
		try {
			if (channel == null) {
				if(connection == null) {
					try {
						connection = RabbitMQTools.getConnection();
						System.out.println("创建rabbitMQ连接成功!");
					} catch (IOException e) {
						System.out.println("创建rabbitMQ连接失败!");
						e.printStackTrace();
					} catch (TimeoutException e) {
						System.out.println("创建rabbitMQ连接失败!");
						e.printStackTrace();
					}
				}
				channel = connection.createChannel();// 创建通道
				channel.basicPublish("amq.direct", "arjapp_receive", null, message.getBytes("UTF-8"));
			}
			channel.basicPublish("amq.direct", "arjapp_receive", null, message.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
