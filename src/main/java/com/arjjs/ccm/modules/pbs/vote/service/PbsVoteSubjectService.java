/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.vote.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteSubject;
import com.arjjs.ccm.modules.pbs.vote.dao.PbsVoteSubjectDao;

/**
 * 投票题目Service
 * @author lc
 * @version 2018-03-27
 */
@Service
@Transactional(readOnly = true)
public class PbsVoteSubjectService extends CrudService<PbsVoteSubjectDao, PbsVoteSubject> {

	public PbsVoteSubject get(String id) {
		return super.get(id);
	}
	
	public List<PbsVoteSubject> findList(PbsVoteSubject pbsVoteSubject) {
		return super.findList(pbsVoteSubject);
	}
	
	public Page<PbsVoteSubject> findPage(Page<PbsVoteSubject> page, PbsVoteSubject pbsVoteSubject) {
		return super.findPage(page, pbsVoteSubject);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsVoteSubject pbsVoteSubject) {
		super.save(pbsVoteSubject);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsVoteSubject pbsVoteSubject) {
		super.delete(pbsVoteSubject);
	}
	
	// 查询列表结果
	public List<PbsVoteSubject> findListVer(PbsVoteSubject pbsVoteSubject) {
		return dao.findListVer(pbsVoteSubject);
	}
}