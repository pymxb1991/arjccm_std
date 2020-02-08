/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.audit.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.risk.audit.entity.RiskAudit;

/**
 * 重大事项审核DAO接口
 * @author liang
 * @version 2018-04-02
 */
@MyBatisDao
public interface RiskAuditDao extends CrudDao<RiskAudit> {

	//审核上报表单提交，保存audit
	public int saveInsertAll(List<RiskAudit> riskAuditList);
	
}