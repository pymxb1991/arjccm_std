/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.line.dao;


import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.line.entity.CcmLineProtect;
import com.arjjs.ccm.tool.EchartType;

/**
 * 护路护线DAO接口
 * @author arj
 * @version 2018-01-23
 */
@MyBatisDao
public interface CcmLineProtectDao extends CrudDao<CcmLineProtect> {
	
	List<CcmLineProtect> findCountLine();
	
	List<EchartType> getLineByType();
	
	List<EchartType> getLineByGrade();
	
}