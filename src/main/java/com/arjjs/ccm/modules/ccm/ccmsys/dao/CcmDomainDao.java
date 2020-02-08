/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmDomain;

/**
 * 下级域服务器管理DAO接口
 * @author pengjianqiang
 * @version 2018-05-09
 */
@MyBatisDao
public interface CcmDomainDao extends CrudDao<CcmDomain> {
	
}