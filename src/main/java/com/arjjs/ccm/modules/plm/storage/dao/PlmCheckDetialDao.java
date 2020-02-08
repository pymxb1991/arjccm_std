/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.storage.entity.PlmCheckDetial;

/**
 * 盘点详细DAO接口
 * @author dongqikai
 * @version 2018-07-10
 */
@MyBatisDao
public interface PlmCheckDetialDao extends CrudDao<PlmCheckDetial> {
	
	/**
	 * 根据编号获取记录
	 * @param codes
	 * @return
	 */
	public List<PlmCheckDetial> getDetails(String[] codes);
	
	/**
	 * 根据编号更新状态为正常
	 * @param codes
	 * @return
	 */
	public int updateAllStatus(String[] codes);
	
}