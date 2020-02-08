/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowworknode;

/**
 * 工作节点记录DAO接口
 * 
 * @author lc
 * @version 2018-04-23
 */
@MyBatisDao
public interface PbsFlowworknodeDao extends CrudDao<PbsFlowworknode> {
	// 更新 状态
	public void updateStatval(PbsFlowworknode pbsFlowworknode);
	// 删除 同一个工作流的节点
	public void deleteByflowworkid(PbsFlowworknode pbsFlowworknode);
}