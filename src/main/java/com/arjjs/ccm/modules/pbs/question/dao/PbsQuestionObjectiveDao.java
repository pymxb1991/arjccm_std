/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionObjective;

/**
 * 客观题信息DAO接口
 * 
 * @author lc
 * @version 2018-06-09
 */
@MyBatisDao
public interface PbsQuestionObjectiveDao extends CrudDao<PbsQuestionObjective> {

	// 获根据考试的类型获取题目
	List<PbsQuestionObjective> findListByExam(PbsQuestionObjective pbsQuestionObjective);

}