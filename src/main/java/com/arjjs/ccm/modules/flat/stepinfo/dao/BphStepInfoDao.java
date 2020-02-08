/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.stepinfo.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.flat.planinfo.entity.BphPlanInfo;
import com.arjjs.ccm.modules.flat.stepinfo.entity.BphStepInfo;

/**
 * 预案过程DAO接口
 * @author zhanghao
 * @version 2018-11-14
 */
@MyBatisDao
public interface BphStepInfoDao extends CrudDao<BphStepInfo> {
	BphStepInfo checkStepName(BphStepInfo bphStepInfo);
	List<BphStepInfo> findListByPlanInfoId(BphPlanInfo bphPlanInfo);
	List<BphStepInfo> findListBySteId(BphStepInfo bphStepInfo);
	List<BphStepInfo> getListStep();
	List<BphStepInfo> getStepData(BphStepInfo bphStepInfo);
}