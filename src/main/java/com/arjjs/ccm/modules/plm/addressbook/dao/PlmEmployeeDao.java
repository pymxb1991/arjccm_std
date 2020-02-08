/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.addressbook.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.addressbook.entity.PlmEmployee;

/**
 * 通讯录DAO接口
 * @author liucong
 * @version 2018-07-14
 */
@MyBatisDao
public interface PlmEmployeeDao extends CrudDao<PlmEmployee> {
	public List<PlmEmployee> findView(PlmEmployee plmEmployee);
}