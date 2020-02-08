/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlownode;

/**
 * 流程节点DAO接口
 * 
 * @author lc
 * @version 2018-04-20
 */
@MyBatisDao
public interface PbsFlownodeDao extends CrudDao<PbsFlownode> {

	// 删除 相关的 同一个模板下的 节点信息
	public void deleteBysFlowid(PbsFlownode pbsFlownode);

	// 获取当前节点的最大顺位值
	public String findMaxSort(String id);
	
	// 更新 节点的顺序
	public void updatesSort(List<PbsFlownode> pbsFlownode);
}