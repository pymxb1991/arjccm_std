/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventKacc;
import com.arjjs.ccm.tool.EchartType;

/**
 * 重点地区排查整治DAO接口
 * @author arj
 * @version 2018-01-04
 */
@MyBatisDao
public interface CcmEventKaccDao extends CrudDao<CcmEventKacc> {

	//治安突出问题统计
	List<EchartType> findSafePage(CcmEventKacc ccmEventKacc);

	//涉及区域类型统计
	List<EchartType> findAreaPage(CcmEventKacc ccmEventKacc);

	//效果评估统计
	List<EchartType> findAssessPage(CcmEventKacc ccmEventKacc);

	//总数统计
	List<EchartType> findLinePage(CcmEventKacc ccmEventKacc);
	
}