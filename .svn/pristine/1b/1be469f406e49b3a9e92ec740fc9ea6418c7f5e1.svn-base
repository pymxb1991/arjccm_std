/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.cpp.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.cpp.entity.CppPopVehile;
import com.arjjs.ccm.tool.EchartForce;

import java.util.List;

/**
 * 人车/联系/网络关系表DAO接口
 * @author liuxue
 * @version 2018-10-30
 */
@MyBatisDao
public interface CppPopVehileDao extends CrudDao<CppPopVehile> {
	List<CppPopVehile> getByRelation(String idCard);
	EchartForce getVehileForForce(CppPopVehile cppPopVehile);
}