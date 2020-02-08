/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.exam.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExampaper;

/**
 * 试卷信息DAO接口
 * 
 * @author lc
 * @version 2018-06-11
 */
@MyBatisDao
public interface PbsExampaperDao extends CrudDao<PbsExampaper> {
	// 更新试卷的发布状态
	public void updateStat(PbsExampaper pbsExampaper);
	// 获取最后的试卷信息
	public PbsExampaper findListByLast(PbsExampaper pbsExampaper);
}