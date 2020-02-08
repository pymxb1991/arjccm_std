/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.dao.apply;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.car.entity.apply.PlmCarApplyBuy;

/**
 * 购车申请DAO接口
 * @author fu
 * @version 2018-07-07
 */
@MyBatisDao
public interface PlmCarApplyBuyDao extends CrudDao<PlmCarApplyBuy> {
	
	public PlmCarApplyBuy getByProcInsId(String procInsId);
	public int updateProcInsId(PlmCarApplyBuy plmCarApplyBuy);
	public void updateIsEnd(PlmCarApplyBuy plmCarApplyBuy);
}