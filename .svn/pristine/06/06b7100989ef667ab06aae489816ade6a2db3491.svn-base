/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.logistics.entity;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 参会人员Entity
 * @author fu
 * @version 2018-06-27
 */
public class PlmRoomAttendee extends DataEntity<PlmRoomAttendee> {
	
	private static final long serialVersionUID = 1L;
	private PlmRoomApply roomApply;		// 会议ID
	private User user;		// 参会人员ID
	
	public PlmRoomAttendee() {
		super();
	}

	public PlmRoomAttendee(String id){
		super(id);
	}

	public PlmRoomAttendee(PlmRoomApply plmRoomApply) {
		super();
		this.roomApply = plmRoomApply;
	}

	public PlmRoomApply getRoomApply() {
		return roomApply;
	}

	public void setRoomApply(PlmRoomApply roomApply) {
		this.roomApply = roomApply;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}