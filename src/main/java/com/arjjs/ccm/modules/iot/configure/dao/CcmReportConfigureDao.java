/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.configure.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.iot.configure.entity.CcmReportConfigure;

/**
 * 报警配置DAO接口
 * @author cby
 * @version 2019-07-25
 */
@MyBatisDao
public interface CcmReportConfigureDao extends CrudDao<CcmReportConfigure> {
	
}