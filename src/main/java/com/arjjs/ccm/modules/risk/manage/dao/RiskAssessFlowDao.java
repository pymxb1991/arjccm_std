/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.manage.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.risk.manage.entity.RiskAssessFlow;

/**
 * 评估流程管理DAO接口
 * @author liang
 * @version 2018-04-04
 */
@MyBatisDao
public interface RiskAssessFlowDao extends CrudDao<RiskAssessFlow> {
	
}