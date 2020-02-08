/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;

/**
 * 党员信息DAO接口
 * @author lc
 * @version 2018-04-03
 */
@MyBatisDao
public interface PbsPartymemDao extends CrudDao<PbsPartymem> {
	
	// 判断 当前的 党员是否存在
	public int checkExist(PbsPartymem pbsPartymem);
	
	// 查询所有的 党员
	public List<PbsPartymem> findListByOffice(PbsPartymem pbsPartymem);
	
	// 查询 所有 部门列表的党员
	public List<PbsPartymem> findListByOfficeList(List<String> ids);
	
	//根据姓名查询所有党员
	public List<PbsPartymem> getListByName(String name);
	
	//根据党员id查询党员所在部门名称
	public List<String> findDepartName(String departId);
}