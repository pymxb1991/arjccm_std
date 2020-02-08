/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.home.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.home.entity.PlmHome;

/**
 * 个人门户DAO接口
 * @author liuxue
 * @version 2018-06-29
 */
@MyBatisDao
public interface PlmHomeDao extends CrudDao<PlmHome> {
	
	 int deleteUser(PlmHome plmHome) ;
	 
	 int zdelete(PlmHome plmHome) ;
	
   int undelete(PlmHome plmHome) ;
	 
	 int deleteType(PlmHome plmHome) ;
	 
	 int undeleteType(PlmHome plmHome) ;

	 List<PlmHome> findList2(PlmHome plmHome);
	 
	 int deleteData(PlmHome plmHome);
}