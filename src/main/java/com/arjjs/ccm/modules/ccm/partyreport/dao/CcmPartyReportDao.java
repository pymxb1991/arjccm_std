/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyreport.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.partyreport.entity.CcmPartyReport;

/**
 * 双报道情况管理DAO接口
 * @author maoxb
 * @version 2019-08-14
 */
@MyBatisDao
public interface CcmPartyReportDao extends CrudDao<CcmPartyReport> {
	
}