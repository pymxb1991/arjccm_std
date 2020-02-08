/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionLevel;

/**
 * 考试难度级别DAO接口
 * @author lc
 * @version 2018-06-06
 */
@MyBatisDao
public interface PbsQuestionLevelDao extends CrudDao<PbsQuestionLevel> {
	
}