/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowwork;

/**
 * 运行工作流DAO接口
 * 
 * @author lc
 * @version 2018-04-23
 */
@MyBatisDao
public interface PbsFlowworkDao extends CrudDao<PbsFlowwork> {
	// 变更 工作流状态
	public void updateSbindstat(PbsFlowwork pbsflowwork);

	// 查询当前 需要被审核工作流
	public List<PbsFlowwork> findListByApprover(PbsFlowwork PbsFlowwork);
	
	// 查看当前的 已完成的 工作流内容
	public List<PbsFlowwork> findListFinish(PbsFlowwork PbsFlowwork);

}