/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.vote.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteTopic;
import com.arjjs.ccm.modules.pbs.vote.dao.PbsVoteTopicDao;

/**
 * 投票主题信息Service
 * @author lc
 * @version 2018-03-27
 */
@Service
@Transactional(readOnly = true)
public class PbsVoteTopicService extends CrudService<PbsVoteTopicDao, PbsVoteTopic> {

	public PbsVoteTopic get(String id) {
		return super.get(id);
	}
	
	public List<PbsVoteTopic> findList(PbsVoteTopic pbsVoteTopic) {
		return super.findList(pbsVoteTopic);
	}
	
	public Page<PbsVoteTopic> findPage(Page<PbsVoteTopic> page, PbsVoteTopic pbsVoteTopic) {
		return super.findPage(page, pbsVoteTopic);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsVoteTopic pbsVoteTopic) {
		super.save(pbsVoteTopic);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsVoteTopic pbsVoteTopic) {
		super.delete(pbsVoteTopic);
	}
	
	@Transactional(readOnly = false)
	public void updateStat(PbsVoteTopic pbsVoteTopic) {
		dao.updateStat(pbsVoteTopic);
	}
	
}