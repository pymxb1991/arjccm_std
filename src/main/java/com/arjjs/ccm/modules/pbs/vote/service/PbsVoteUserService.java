/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.vote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteUser;
import com.arjjs.ccm.modules.pbs.vote.dao.PbsVoteUserDao;

/**
 * 投票主题用户信息Service
 * 
 * @author lc
 * @version 2018-03-27
 */
@Service
@Transactional(readOnly = true)
public class PbsVoteUserService extends CrudService<PbsVoteUserDao, PbsVoteUser> {

	@Autowired
	private PbsVoteUserDao pbsVoteUserDao;

	public PbsVoteUser get(String id) {
		return super.get(id);
	}

	public List<PbsVoteUser> findList(PbsVoteUser pbsVoteUser) {
		return super.findList(pbsVoteUser);
	}

	public Page<PbsVoteUser> findPage(Page<PbsVoteUser> page, PbsVoteUser pbsVoteUser) {
		return super.findPage(page, pbsVoteUser);
	}

	@Transactional(readOnly = false)
	public void save(PbsVoteUser pbsVoteUser) {
		super.save(pbsVoteUser);
	}

	@Transactional(readOnly = false)
	public void delete(PbsVoteUser pbsVoteUser) {
		super.delete(pbsVoteUser);
	}

	public int getsum(PbsVoteUser pbsVoteUser) {
		return pbsVoteUserDao.getSum(pbsVoteUser);
	}

}