/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.dao;

import java.util.List;
import java.util.Map;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.storage.entity.PlmMinusandAddDetail;

/**
 * 出库、入库进出明细DAO接口
 * @author dongqikai
 * @version 2018-06-30
 */
@MyBatisDao
public interface PlmMinusandAddDetailDao extends CrudDao<PlmMinusandAddDetail> {
	
	/**
	 * 修改入库状态
	 * @param id
	 * @return
	 */
	public int updateFlag(String id);
	
	/**
	 * 根据父id统计物资数量
	 * @param pId
	 * @return
	 */
	public int countDetails(String pId);
	/**
	 * 借用超期装备 
	 * @return
	 */
	public List<Map<String, Object>> findDeadLineCount();
	
	public List<PlmMinusandAddDetail> findIndexList(PlmMinusandAddDetail plmMinusandAddDetail);
	//查询归还信息
	public List<PlmMinusandAddDetail> findGiveBack(PlmMinusandAddDetail plmMinusandAddDetail);
	
	//修改归还状态
	public int updateId(String id);
	
	/**
	 * 修改出库单下的出库物资的使用有效期
	 * @param id 出库单id
	 * @return
	 */
	public int updateDeadlineDate(String id, String deadlineDate);
}