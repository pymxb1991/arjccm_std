/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.dao;

import java.util.Date;
import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmMobileDevice;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmTracingpoint;
import org.apache.ibatis.annotations.Param;

/**
 * 实时轨迹点DAO接口
 * @author arj
 * @version 2018-03-13
 */
@MyBatisDao
public interface CcmTracingpointDao extends CrudDao<CcmTracingpoint> {

	List<CcmMobileDevice> findDeptDeviceList(CcmMobileDevice ccmMobileDevice);

	/**
	 * 真删数据按时间
	 * @param date
	 */
	void deleteTrue(Date date);

	Integer getUserStatus(@Param("id") String id);
	
}