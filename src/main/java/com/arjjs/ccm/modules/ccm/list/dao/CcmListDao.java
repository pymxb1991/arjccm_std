/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.list.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.list.entity.CcmList;

/**
 * 名单库实体类DAO接口
 * @author jpy
 * @version 2019-06-04
 */
@MyBatisDao
public interface CcmListDao extends CrudDao<CcmList> {
	
	public List<CcmList> getList(CcmList ccmList);
	
	public Integer getPeopleCount(CcmList ccmList);
	
}