/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgPerson;

/**
 * 自治组织人员管理DAO接口
 * @author liuyongjian
 * @version 2019-08-13
 */
@MyBatisDao
public interface CcmOrgPersonDao extends CrudDao<CcmOrgPerson> {
	
}