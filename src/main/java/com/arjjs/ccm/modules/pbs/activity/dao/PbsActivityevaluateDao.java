/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityevaluate;

/**
 * 活动评分DAO接口
 * @author lc
 * @version 2018-05-15
 */
@MyBatisDao
public interface PbsActivityevaluateDao extends CrudDao<PbsActivityevaluate> {
	
	// 插入全部
 	public void insertAll(List<PbsActivityevaluate> list);
	
}