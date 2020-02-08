package com.arjjs.ccm.modules.ccm.rest.entity;


import java.util.List;


public class CcmRestChatFriend {
	private String groupname; //好友分组名
	private String id;   //分组id
	private List<CcmRestChatUser> list;  //分组下的好友
	
	public CcmRestChatFriend() {
		super();
	}
	public CcmRestChatFriend(String groupname, String id) {
		super();
		this.groupname = groupname;
		this.id = id;
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
	public List<CcmRestChatUser> getList() {
		return list;
	}
	public void setList(List<CcmRestChatUser> list) {
		this.list = list;
	}
	
	
}
