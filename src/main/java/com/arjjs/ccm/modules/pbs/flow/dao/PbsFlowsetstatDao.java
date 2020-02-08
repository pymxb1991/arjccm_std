/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowsetstat;

/**
 * 节点操作后修改DAO接口
 * 
 * @author lc
 * @version 2018-04-20
 */
@MyBatisDao
public interface PbsFlowsetstatDao extends CrudDao<PbsFlowsetstat> {

	public void updateOperation(PbsFlowsetstat PbsFlowsetstat);
}