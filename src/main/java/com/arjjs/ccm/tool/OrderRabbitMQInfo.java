package com.arjjs.ccm.tool;

import com.rabbitmq.client.Channel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OrderRabbitMQInfo {

	private JSONArray jsonArray;//数据
	private String heartBeatTime;//心跳时间
	private JSONObject jsonoOject;
	private String clientId;// queueName
	private Channel channel;// 通道
	private String userId;//用户ID
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHeartBeatTime() {
		return heartBeatTime;
	}

	public void setHeartBeatTime(String heartBeatTime) {
		this.heartBeatTime = heartBeatTime;
	}

	public JSONObject getJsonoOject() {
		return jsonoOject;
	}

	public void setJsonoOject(JSONObject jsonoOject) {
		this.jsonoOject = jsonoOject;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

}
