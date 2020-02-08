/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.stepaction.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.flat.action.entity.BphActionInfo;
import com.arjjs.ccm.modules.flat.stepaction.entity.BphStepAction;

/**
 * 过程动作关联表DAO接口
 * @author liu
 * @version 2018-11-17
 */
@MyBatisDao
public interface BphStepActionDao extends CrudDao<BphStepAction> {
	List<BphActionInfo> getActionByStepId(BphStepAction bphStepAction);
	int deleteByStepIdAndActionId(BphStepAction bphStepAction);
	BphStepAction findLastSort(BphStepAction bphStepAction);
}