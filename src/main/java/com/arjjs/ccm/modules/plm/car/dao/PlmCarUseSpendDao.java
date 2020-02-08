/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.car.entity.PlmCarUseSpend;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.tool.EchartType;

/**
 * 用车费用记录DAO接口
 * @author fu
 * @version 2018-07-04
 */
@MyBatisDao
public interface PlmCarUseSpendDao extends CrudDao<PlmCarUseSpend> {

	PlmCarUseSpend getByCarUseId(String id);

	List<EchartType> selectSpendByType(Office office);

	List<EchartType> selectSpendNumAllByOffice();

	List<EchartType> selectDistanceNumAllByOffice();

	List<EchartType> selectDamagedAndDisNumByMonth();
	
}