/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.car.entity.PlmCar;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.Select2Type;

/**
 * 车辆DAO接口
 * @author fu
 * @version 2018-06-30
 */
@MyBatisDao
public interface PlmCarDao extends CrudDao<PlmCar> {

	List<Select2Type> findSelect2Type(PlmCar plmCar);

	List<EchartType> selectNumByVtype(Office office);
	
	Integer count();
	
	
	List<EchartType> countByStatusAjax();
}