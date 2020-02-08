/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.dao.apply;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.car.entity.apply.PlmCarApplyScrap;
import com.arjjs.ccm.tool.Select2Type;

/**
 * 报废申请DAO接口
 * @author fu
 * @version 2018-07-07
 */
@MyBatisDao
public interface PlmCarApplyScrapDao extends CrudDao<PlmCarApplyScrap> {
	
	public PlmCarApplyScrap getByProcInsId(String procInsId);
	public int updateProcInsId(PlmCarApplyScrap plmCarApplyScrap);
	public void updateIsEnd(PlmCarApplyScrap plmCarApplyScrap);
	public List<Select2Type> findSelect2Type();
}