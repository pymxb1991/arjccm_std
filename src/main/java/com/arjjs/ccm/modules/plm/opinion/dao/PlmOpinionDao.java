/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.opinion.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.equapply.entity.PlmEquApply;
import com.arjjs.ccm.modules.plm.opinion.entity.PlmOpinion;

/**
 * 建议意见箱DAO接口
 * @author liucong
 * @version 2018-07-30
 */
@MyBatisDao
public interface PlmOpinionDao extends CrudDao<PlmOpinion> {
	public PlmEquApply getByProcInsId(String procInsId);

	public int updateProcInsId(PlmOpinion plmOpinion);
}