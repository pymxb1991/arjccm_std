/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.ncount.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcount;

/**
 * 统计计数DAO接口
 * 
 * @author lc
 * @version 2018-07-12
 */
@MyBatisDao
public interface PbsNcountDao extends CrudDao<PbsNcount> {

	public void clearData(PbsNcount pbsNcount);
}