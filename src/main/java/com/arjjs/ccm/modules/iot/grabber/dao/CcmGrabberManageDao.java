/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.grabber.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.iot.grabber.entity.CcmGrabberManage;

/**
 * 抓拍机管理DAO接口
 * @author cby
 * @version 2019-07-25
 */
@MyBatisDao
public interface CcmGrabberManageDao extends CrudDao<CcmGrabberManage> {
	
	public void updateState(CcmGrabberManage ccmGrabberManage);

	public int getCount(CcmGrabberManage ccmGrabberManage);
}