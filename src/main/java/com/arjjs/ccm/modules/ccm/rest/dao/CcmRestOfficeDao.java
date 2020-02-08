/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.rest.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.TreeDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * @author admin001
 * @version 2014-05-16
 */
@MyBatisDao
public interface CcmRestOfficeDao extends TreeDao<Office> {

	List<Office> findOfficeUser(Office office);

	
	List<Office> findByParentIdsLike(Office office);

	
}
