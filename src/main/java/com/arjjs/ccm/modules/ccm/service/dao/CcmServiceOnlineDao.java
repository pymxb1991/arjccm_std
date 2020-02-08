/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.service.entity.CcmServiceOnline;
import com.arjjs.ccm.tool.EchartType;

/**
 * 在线办事DAO接口
 * @author fuxinshuang
 * @version 2018-03-14
 */
@MyBatisDao
public interface CcmServiceOnlineDao extends CrudDao<CcmServiceOnline> {
	//在线办事事项分类分析
	List<EchartType> getServiceType();
	//在线办事-处理进度
	List<EchartType> getServiceStatus();
	
}