/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.car.entity.PlmCarUse;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.tool.EchartType;

/**
 * 领用记录DAO接口
 * @author fu
 * @version 2018-07-02
 */
@MyBatisDao
public interface PlmCarUseDao extends CrudDao<PlmCarUse> {

	List<EchartType> selectUseNumByCar(Office office);

	List<EchartType> selectUseNumByOffice();

	List<EchartType> selectNumByDriver();

	List<EchartType> selectUseNumAllByOffice();

	List<EchartType> selectUseAndVioNumByMonth();

	
}