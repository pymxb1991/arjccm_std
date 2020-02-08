/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.act.dao;

import java.util.List;
import java.util.Map;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.tool.EchartType;

/**
 * 业务申请单主表DAO接口
 * @author fu
 * @version 2018-07-20
 */
@MyBatisDao
public interface PlmActDao extends CrudDao<PlmAct> {

	PlmAct getByTable(PlmAct plmAct);

	void updateStatus(PlmAct plmAct);

	List<PlmAct> findSupFinishList(PlmAct plmAct);

	void updateSup(PlmAct plmAct);
	
   /**
    * 流程趋势  以天为时间轴
    * @param map
    * @return
    */
	 List<EchartType> countActByDate(Map<String, Object> map);
	
	  /**
	    * 流程申请数量
	    * 
	    * @return
	    */
	 Integer countAct();
	 
	 
	 List<EchartType> countActByType(Map<String, Object> map);
	/**
	 * 流程各状态数量
	 * @param map(map: type)
	 * @return
	 */
	List<EchartType> countActByStatus(Map<String, Object> map);
	/**
	 * 流程督办数量
	 * @param map(map: type)
	 * @return
	 */
	Integer countActBySup(Map<String, Object> map);
	/**
	 * 根据流程表示查找最新流程定义ID
	 * @param procDefKey
	 * @return
	 */
	String findProcDefIdByKey(String procDefKey);
}