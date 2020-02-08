/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.dao;


import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventCasedeal;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventIncident;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.EchartType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 事件处理DAO接口
 * @author arj
 * @version 2018-01-10
 */
@MyBatisDao
public interface CcmEventCasedealDao extends CrudDao<CcmEventCasedeal> {

    /**
     * 今日事件待签收，已签收，已反馈数量
     * @return
     */
	List<EchartType> getNumByHandelStatus();

	List<CcmEventCasedeal> listWithPID(String id);
	
	List<CcmEventCasedeal> findLogTailByRelevanceId(String id);

	//定时请求事件处理状态
	CcmEventCasedeal getEventCasedealMap(CcmEventCasedeal ccmEventCasedeal);

	//事件处理未处理数量
	int getNumEventCasedeal(CcmEventCasedeal ccmEventCasedeal);

    List<User> findAssignUser(@Param("areaId") String areaId,@Param("parentIds") List<String> parentIds);

	List<CcmLogTail> findEventProcessLogTail(String id);

	List<CcmEventCasedeal> findListByEventId(CcmEventIncident ccmEventIncident);

    List<CcmEventCasedeal> detectDeadline();
}