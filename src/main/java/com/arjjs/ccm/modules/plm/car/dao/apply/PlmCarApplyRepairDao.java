/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.dao.apply;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.car.entity.apply.PlmCarApplyRepair;
import com.arjjs.ccm.tool.Select2Type;

/**
 * 维修申请DAO接口
 * @author fu
 * @version 2018-07-07
 */
@MyBatisDao
public interface PlmCarApplyRepairDao extends CrudDao<PlmCarApplyRepair> {
	
	public PlmCarApplyRepair getByProcInsId(String procInsId);
	public int updateProcInsId(PlmCarApplyRepair plmCarApplyRepair);
	public void updateIsEnd(PlmCarApplyRepair plmCarApplyRepair);
	public List<Select2Type> findSelect2Type();
}