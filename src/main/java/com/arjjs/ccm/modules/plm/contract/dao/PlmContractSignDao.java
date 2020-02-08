/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.contract.dao;

import java.util.List;
import java.util.Map;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.contract.entity.PlmContractSign;

/**
 * 采购合同会签DAO接口
 * 
 * @author liuxue
 * @version 2018-07-07
 */
@MyBatisDao
public interface PlmContractSignDao extends CrudDao<PlmContractSign> {
	public PlmContractSign getByProcInsId(String procInsId);

	public int updateProcInsId(PlmContractSign PlmContractSign);

	List<Map<String, Object>> contractTypeStatistic(PlmContractSign PlmContractSign);
}