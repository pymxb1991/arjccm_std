/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.logistics.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.ActEntity;
import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.IdGen;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;

/**
 * 会议申请Entity
 * @author fu
 * @version 2018-06-26
 */
public class PlmRoomApply extends ActEntity<PlmRoomApply> {
	
	private static final long serialVersionUID = 1L;
	private String procInsId;		// 流程实例ID
	private String category;		//流程类别
	private String subject;		// 会议名称
	private String type;		// 会议类型
	private User initiator;		// 会议发起人
	private String initiatorTel;		// 发起人联系方式
	private PlmRoom room;		// 会议地点(会议室)
	private User presider;		// 会议主持人
	private User recorder;		// 会议记录人
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String details;		//详情
	private String files;		//附件
	private Integer number;		// 与会总人数
	private String isEnd;		//流程结束标志
	private PlmAct plmAct;		//业务流程总表
	private String code;		//申请编号
	private String cancelFlag;   //是否可撤销
	
	private List<PlmRoomAttendee> roomAttendeeList = Lists.newArrayList();
	
	public PlmRoomApply() {
		super();
	}

	public PlmRoomApply(String id){
		super(id);
	}

	@Length(min=0, max=64, message="流程实例ID长度必须介于 0 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	
	@Length(min=0, max=100, message="会议名称长度必须介于 0 和 100 之间")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Length(min=0, max=2, message="会议类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public User getInitiator() {
		return initiator;
	}

	public void setInitiator(User initiator) {
		this.initiator = initiator;
	}
	
	@Length(min=0, max=20, message="发起人联系方式长度必须介于 0 和 20 之间")
	public String getInitiatorTel() {
		return initiatorTel;
	}

	public void setInitiatorTel(String initiatorTel) {
		this.initiatorTel = initiatorTel;
	}
	
	public PlmRoom getRoom() {
		return room;
	}

	public void setRoom(PlmRoom room) {
		this.room = room;
	}
	
	public User getPresider() {
		return presider;
	}

	public void setPresider(User presider) {
		this.presider = presider;
	}
	
	public User getRecorder() {
		return recorder;
	}

	public void setRecorder(User recorder) {
		this.recorder = recorder;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public List<PlmRoomAttendee> getRoomAttendeeList() {
		return roomAttendeeList;
	}

	public void setRoomAttendeeList(List<PlmRoomAttendee> roomAttendeeList) {
		this.roomAttendeeList = roomAttendeeList;
	}
	/**
	 * 获取会议接受人用户ID
	 * @return
	 */
	public String getRoomAttendeeIds() {
		return Collections3.extractToString(roomAttendeeList, "user.id", ",") ;
	}
	
	/**
	 * 设置会议接受人用户ID
	 * @return
	 */
	public void setRoomAttendeeIds(String roomAttendee) {
		this.roomAttendeeList = Lists.newArrayList();
		for (String id : StringUtils.split(roomAttendee, ",")){
			PlmRoomAttendee entity = new PlmRoomAttendee();
			entity.setId(IdGen.uuid());
			entity.setRoomApply(this);
			entity.setUser(new User(id));
			this.roomAttendeeList.add(entity);
		}
		this.number = roomAttendeeList.size();
	}
	/**
	 * 获取会议接受人用户Name
	 * @return
	 */
	public String getRoomAttendeeNames() {
		return Collections3.extractToString(roomAttendeeList, "user.name", ",") ;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}

	public PlmAct getPlmAct() {
		return plmAct;
	}

	public void setPlmAct(PlmAct plmAct) {
		this.plmAct = plmAct;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	
	
}