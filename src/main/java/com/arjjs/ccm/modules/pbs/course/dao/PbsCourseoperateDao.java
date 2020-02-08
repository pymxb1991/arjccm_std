/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.course.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.course.entity.PbsCourseoperate;

/**
 * 课程操作信息DAO接口
 * 
 * @author lc
 * @version 2018-04-14
 */
@MyBatisDao
public interface PbsCourseoperateDao extends CrudDao<PbsCourseoperate> {
	// 用户数
	public int getSumByUser(String sParentid);

	// 评论数
	public int getCounts(String sParentid);
	
	//根据党员id查询课程数
	public List<PbsCourseoperate> getSumByMemberId(String sBindmemeber);
	//按部门统计学时
	List<PbsCourseoperate> getDepartmentPeriod(PbsCourseoperate pbsCourseoperate);
	//按人员统计学时
	List<PbsCourseoperate> getPersonnelPeriod(PbsCourseoperate pbsCourseoperate);
}