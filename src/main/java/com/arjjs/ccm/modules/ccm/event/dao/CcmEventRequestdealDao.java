/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventRequestdeal;

/**
 * 请求处理DAO接口
 * @author arj
 * @version 2018-01-18
 */
@MyBatisDao
public interface CcmEventRequestdealDao extends CrudDao<CcmEventRequestdeal> {
	List<CcmEventRequestdeal> listWithPID(String id);
}