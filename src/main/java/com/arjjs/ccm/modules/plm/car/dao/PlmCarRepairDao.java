/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.car.entity.PlmCarRepair;
import com.arjjs.ccm.tool.Select2Type;

/**
 * 维保单位DAO接口
 * @author fu
 * @version 2018-07-02
 */
@MyBatisDao
public interface PlmCarRepairDao extends CrudDao<PlmCarRepair> {

	List<Select2Type> findSelect2Type(PlmCarRepair plmCarRepair);
	
}