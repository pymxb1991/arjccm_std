/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.deviceonline.dao;

import org.apache.ibatis.annotations.Param;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.flat.deviceonline.entity.CcmDeviceOnline;

/**
 * 设备在线实体类DAO接口
 * 
 * @author lgh
 * @version 2019-07-13
 */
@MyBatisDao
public interface CcmDeviceOnlineDao extends CrudDao<CcmDeviceOnline> {

	CcmDeviceOnline getByDeviceId(@Param(value = "deviceId") String deviceId);

}