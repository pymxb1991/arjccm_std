/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.dao.apply;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.car.entity.apply.PlmCarApplyUse;
import com.arjjs.ccm.tool.Select2Type;

/**
 * 用车申请DAO接口
 * @author fu
 * @version 2018-07-06
 */
@MyBatisDao
public interface PlmCarApplyUseDao extends CrudDao<PlmCarApplyUse> {
	public PlmCarApplyUse getByProcInsId(String procInsId);
	
	public int updateProcInsId(PlmCarApplyUse plmCarApplyUse);
	public void updateIsEnd(PlmCarApplyUse plmCarApplyUse);

	public List<Select2Type> findSelect2Type();

	
	
}