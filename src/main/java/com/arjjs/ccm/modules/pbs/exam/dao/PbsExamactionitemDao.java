/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.exam.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExamactionitem;

/**
 * 考试题目信息DAO接口
 * 
 * @author lc
 * @version 2018-06-11
 */
@MyBatisDao
public interface PbsExamactionitemDao extends CrudDao<PbsExamactionitem> {
	// 插入多条考试信息
	public void insertAll(List<PbsExamactionitem> list);
	
	//查询错题信息
	public List<PbsExamactionitem> findErrorList(PbsExamactionitem pbsExamactionitem);
}