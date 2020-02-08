package com.arjjs.ccm.modules.ccm.rest.entity;

import java.util.List;


public class CcmRestChatResult {
	private CcmRestChatUser mine; //我的信息
	private List<CcmRestChatFriend> friend;   //我的分组好友
	private List<CcmUserGroup> group;   //我的群组
	
	
	public CcmRestChatUser getMine() {
		return mine;
	}
	public void setMine(CcmRestChatUser mine) {
		this.mine = mine;
	}
	public List<CcmRestChatFriend> getFriend() {
		return friend;
	}
	public void setFriend(List<CcmRestChatFriend> friend) {
		this.friend = friend;
	}
	public List<CcmUserGroup> getGroup() {
		return group;
	}
	public void setGroup(List<CcmUserGroup> group) {
		this.group = group;
	}
	
	
	
	
	
}
