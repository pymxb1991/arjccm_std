/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.resource.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.resource.entity.PlmResourceUser;

/**
 * 资源权限DAO接口
 * @author liucong
 * @version 2018-07-20
 */
@MyBatisDao
public interface PlmResourceUserDao extends CrudDao<PlmResourceUser> {
	public int findCount(@Param("uId") String uId,@Param("rId") String rId);
	public List<PlmResourceUser> findAllIds();
	public int deleteRId(String rId);
}