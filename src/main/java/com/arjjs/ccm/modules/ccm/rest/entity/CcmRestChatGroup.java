package com.arjjs.ccm.modules.ccm.rest.entity;




public class CcmRestChatGroup {
	private String groupname; //群组名
	private String id;   //群组id
	private String avatar;	//群组头像
	
	public CcmRestChatGroup() {
		super();
	}
	public CcmRestChatGroup(String groupname, String id, String avatar) {
		super();
		this.groupname = groupname;
		this.id = id;
		this.avatar = avatar;
	}
	
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
	
}
