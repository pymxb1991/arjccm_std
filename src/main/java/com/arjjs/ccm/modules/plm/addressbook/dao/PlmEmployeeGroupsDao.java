/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.addressbook.dao;

import com.arjjs.ccm.common.persistence.TreeDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.addressbook.entity.PlmEmployeeGroups;

/**
 * 个人通讯录分组DAO接口
 * @author liucong
 * @version 2018-07-16
 */
@MyBatisDao
public interface PlmEmployeeGroupsDao extends TreeDao<PlmEmployeeGroups> {
	
}