package com.arjjs.ccm.modules.websocket.bean;

import java.util.Date;
public class Message {

	//发送者 状态
	public String from;
	//发送者 id
	public String fromid;
	//发送者名称
	public String fromName;
	//接收者
	public String to;
	//发送的文本
	public String text;
	//发送日期
	public Date date;
	// 房间id
	public String roomid;
	// 房间id
	public boolean goflag;
		

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFromid() {
		return fromid;
	}

	public void setFromid(String fromid) {
		this.fromid = fromid;
	}

	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public boolean isGoflag() {
		return goflag;
	}

	public void setGoflag(boolean goflag) {
		this.goflag = goflag;
	}
	
	
	
}
