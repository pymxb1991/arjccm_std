/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.exam.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExamaction;

/**
 * 考试信息DAO接口
 * @author lx
 * @version 2018-06-11
 */
@MyBatisDao
public interface PbsExamactionDao extends CrudDao<PbsExamaction> {
	// 获取 考试分数
	List<PbsExamaction> findListByIReport(PbsExamaction pbsExamaction);
	// 获取按部门 平均分
	List<PbsExamaction> findIReportByOfc(PbsExamaction pbsExamaction);
	// 获取每一个人的 考试次数
	List<PbsExamaction> findIntegrallist(PbsExamaction pbsExamaction);
	// 获取部门的考试次数
	List<PbsExamaction> findIntegralByOfc(PbsExamaction pbsExamaction);
	//根据试卷信息，获取考试人员的内容
	List<PbsExamaction> paperDetail(String paperId);
}