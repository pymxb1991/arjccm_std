/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.apply.dao;

import org.apache.ibatis.annotations.Param;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.apply.entity.PbsApplyrec;

/**
 * 申请记录DAO接口
 * @author lc
 * @version 2018-04-25
 */
@MyBatisDao
public interface PbsApplyrecDao extends CrudDao<PbsApplyrec> {
	public void updateTypeById(@Param("applyId")String applyId, @Param("typeId")String typeId);
}