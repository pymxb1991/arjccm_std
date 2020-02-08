/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.vote.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteTopic;

/**
 * 投票主题信息DAO接口
 * @author lc
 * @version 2018-03-27
 */
@MyBatisDao
public interface PbsVoteTopicDao extends CrudDao<PbsVoteTopic> {
	
	public void updateStat(PbsVoteTopic pbsVoteTopic);
}