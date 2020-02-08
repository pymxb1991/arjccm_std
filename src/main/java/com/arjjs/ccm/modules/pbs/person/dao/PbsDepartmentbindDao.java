/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.person.entity.PbsDepartmentbind;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;

/**
 * 党员部门关系DAO接口
 * @author lc
 * @version 2018-04-03
 */
@MyBatisDao
public interface PbsDepartmentbindDao extends CrudDao<PbsDepartmentbind> {
	
	// 检测 当前的党员部门关系 是否存在
	public int checkExist(PbsDepartmentbind pbsDepartmentbind);
	
	// 删除 当前的关系
	public void deleteByRelationship(PbsDepartmentbind pbsDepartmentbind);
	
	//根据党员的关系获取部门信息
	public List<PbsDepartmentbind> findRecordBypbsPartymemId(PbsPartymem pbsPartymem);
}