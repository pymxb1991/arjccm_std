/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkTiming;

/**
 * 定时提醒DAO接口
 * @author liang
 * @version 2018-08-02
 */
@MyBatisDao
public interface CcmWorkTimingDao extends CrudDao<CcmWorkTiming> {

	//定时提醒
	List<CcmWorkTiming> findTiming();
	
}