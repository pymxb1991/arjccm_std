/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.logistics.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.logistics.entity.PlmRoom;
import com.arjjs.ccm.tool.Select2Type;

/**
 * 会议室管理DAO接口
 * @author fu
 * @version 2018-06-26
 */
@MyBatisDao
public interface PlmRoomDao extends CrudDao<PlmRoom> {

	List<Select2Type> findSelect2Type(PlmRoom plmRoom);
	
}