/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowInspect;
import com.arjjs.ccm.tool.EchartType;

/**
 * 检查记录DAO接口
 * @author liang
 * @version 2018-05-31
 */
@MyBatisDao
public interface CcmKnowInspectDao extends CrudDao<CcmKnowInspect> {
	//安全生产防范检查
	List<EchartType> getTypeSafeData();

	List<EchartType> getTypeSafeDataecharts();
}