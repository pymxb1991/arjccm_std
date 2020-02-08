/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.planinfo.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.flat.planinfo.entity.BphPlanInfo;


/**
 * 数字化预案DAO接口
 * @author zhanghao
 * @version 2018-11-14
 */
@MyBatisDao
public interface BphPlanInfoDao extends CrudDao<BphPlanInfo> {
	BphPlanInfo checkPlanName(BphPlanInfo bphPlanInfo);
	List<BphPlanInfo> findByIsImportantAndTypeCode(BphPlanInfo bphPlanInfo);
	//门户预案调用的次数统计
	public List<BphPlanInfo> findPlanInvokeCount();
}