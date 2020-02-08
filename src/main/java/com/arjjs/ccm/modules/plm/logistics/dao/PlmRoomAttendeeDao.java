/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.logistics.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.logistics.entity.PlmRoomAttendee;

/**
 * 参会人员DAO接口
 * @author fu
 * @version 2018-06-27
 */
@MyBatisDao
public interface PlmRoomAttendeeDao extends CrudDao<PlmRoomAttendee> {
	
	//根据会议id删除接受人记录
	public int deleteByPlmRoomApplyId(String plmRoomApplyId);
	//添加接受人记录
	public int insertAll(List<PlmRoomAttendee> roomAttendeeList);
		
}