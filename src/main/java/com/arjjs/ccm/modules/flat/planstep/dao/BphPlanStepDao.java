/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.planstep.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.flat.planstep.entity.BphPlanStep;
import com.arjjs.ccm.modules.flat.stepinfo.entity.BphStepInfo;

/**
 * 预案过程关联表DAO接口
 * @author liu
 * @version 2018-11-17
 */
@MyBatisDao
public interface BphPlanStepDao extends CrudDao<BphPlanStep> {
	List<BphStepInfo> getStepByPlanId(BphPlanStep bphPlanStep);
	int deleteByStepIdAndPlanId(BphPlanStep bphPlanStep);
	BphPlanStep findLastSort(BphPlanStep bphPlanStep);
}