package com.arjjs.ccm.modules.flat.flow.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.flat.flow.entity.PlanFlowManage;

@MyBatisDao
public interface PlanFlowManageDao {

	List<PlanFlowManage> getAllList(PlanFlowManage planFlowManage);
	List<PlanFlowManage> getActionStep();
	List<PlanFlowManage> planTree();
}
