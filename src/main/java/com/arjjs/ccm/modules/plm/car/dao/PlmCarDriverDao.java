/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.car.entity.PlmCarDriver;
import com.arjjs.ccm.tool.Select2Type;

/**
 * 驾驶员DAO接口
 * @author fu
 * @version 2018-06-30
 */
@MyBatisDao
public interface PlmCarDriverDao extends CrudDao<PlmCarDriver> {

	List<Select2Type> findSelect2Type(PlmCarDriver plmCarDriver);
	
}