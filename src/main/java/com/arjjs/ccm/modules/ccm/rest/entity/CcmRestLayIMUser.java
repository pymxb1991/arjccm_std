package com.arjjs.ccm.modules.ccm.rest.entity;


public class CcmRestLayIMUser {
	private String username; //我的昵称
	private String id;   //我的id
	private String status; //在线状态 online：在线    hide：隐身
	private String sign;  //我的签名
	private String avatar;  //我的头像
	
	public CcmRestLayIMUser(){
		
	}
	public CcmRestLayIMUser(String username, String id, String status, String sign, String avatar) {
		super();
		this.username = username;
		this.id = id;
		this.status = status;
		this.sign = sign;
		this.avatar = avatar;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
	
	
}
