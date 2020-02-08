/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.person.entity.PbsMemlabel;

/**
 * 人物标签DAO接口
 * @author lc
 * @version 2018-08-03
 */
@MyBatisDao
public interface PbsMemlabelDao extends CrudDao<PbsMemlabel> {
	public void deleteBymemId(String pbspartymemId);
}