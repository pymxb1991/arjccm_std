/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.cpp.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.cpp.entity.CppPopPop;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.tool.EchartForce;

import java.util.List;

/**
 * 人际关系DAO接口
 * @author liuxue
 * @version 2018-11-03
 */
@MyBatisDao
public interface CppPopPopDao extends CrudDao<CppPopPop> {
	
	List<CppPopPop> getByIdCard(String idCard1);
	
	List<CcmPeople> getIdCard(CcmPeople ccmPeople);
	
	List<CppPopPop> findListAddTime (CppPopPop cppPopPop);

	EchartForce findListForForce(CppPopPop cppPopPop);
}