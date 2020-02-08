/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.equapply.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.equapply.entity.PlmEquApply;
import com.arjjs.ccm.tool.EchartType;

/**
 * 物资申请DAO接口
 * @author liucong
 * @version 2018-06-30
 */
@MyBatisDao
public interface PlmEquApplyDao extends CrudDao<PlmEquApply> {

	public PlmEquApply getByProcInsId(String procInsId);

	public int updateProcInsId(PlmEquApply plmEquApply);
	
	//
	public List<EchartType> selectNumEquApply();
	
	
}