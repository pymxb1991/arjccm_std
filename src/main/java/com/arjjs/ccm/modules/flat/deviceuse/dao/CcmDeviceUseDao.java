/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.deviceuse.dao;

import java.util.List;

import com.arjjs.ccm.modules.flat.analyst.entity.Count;
import org.apache.ibatis.annotations.Param;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
//import com.arjjs.ccm.modules.flat.analyst.entity.Count;
import com.arjjs.ccm.modules.flat.deviceuse.entity.CcmDeviceUse;

/**
 * 移动设备使用记录实体类DAO接口
 * 
 * @author lgh
 * @version 2019-07-12
 */
@MyBatisDao
public interface CcmDeviceUseDao extends CrudDao<CcmDeviceUse> {

	List<Count> countUseTime(@Param(value = "userId") String userId, @Param(value = "beginDate") String beginDate, @Param(value = "endDate") String endDate);
	
	// 获取离当前时间最近的一次登录情况
	CcmDeviceUse getByDeviceId(@Param(value = "deviceId") String deviceId);

	List<Count> getDataByDept(String[] ids);


}