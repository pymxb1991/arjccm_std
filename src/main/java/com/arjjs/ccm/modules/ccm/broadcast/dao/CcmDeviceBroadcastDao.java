/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.broadcast.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.broadcast.entity.CcmDeviceBroadcast;

/**
 * 广播站DAO接口
 * @author maoxb
 * @version 2019-08-28
 */
@MyBatisDao
public interface CcmDeviceBroadcastDao extends CrudDao<CcmDeviceBroadcast> {

    public int updateCoordinates(CcmDeviceBroadcast broadcast);

	public List<CcmDeviceBroadcast> getByCode(CcmDeviceBroadcast deviceBroadcast);
}