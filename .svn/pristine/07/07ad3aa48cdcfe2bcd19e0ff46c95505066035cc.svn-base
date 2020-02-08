/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.resource.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.resource.entity.PlmResource;

/**
 * 资源共享DAO接口
 * @author liucong
 * @version 2018-07-20
 */
@MyBatisDao
public interface PlmResourceDao extends CrudDao<PlmResource> {
	public List<PlmResource> findListAllById(String uId);
	public PlmResource findListById(PlmResource plmResource);
	public int  updateType(String rId);
}