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
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteOpdetail;
import com.arjjs.ccm.modules.pbs.vote.dao.PbsVoteOpdetailDao;

/**
 * 投票个人选项信息Service
 * 
 * @author lc
 * @version 2018-03-27
 */
@Service
@Transactional(readOnly = true)
public class PbsVoteOpdetailService extends CrudService<PbsVoteOpdetailDao, PbsVoteOpdetail> {

	@Autowired
	private PbsVoteOpdetailDao pbsVoteOpdetailDao;

	public PbsVoteOpdetail get(String id) {
		return super.get(id);
	}

	public List<PbsVoteOpdetail> findList(PbsVoteOpdetail pbsVoteOpdetail) {
		return super.findList(pbsVoteOpdetail);
	}

	public Page<PbsVoteOpdetail> findPage(Page<PbsVoteOpdetail> page, PbsVoteOpdetail pbsVoteOpdetail) {
		return super.findPage(page, pbsVoteOpdetail);
	}

	@Transactional(readOnly = false)
	public void save(PbsVoteOpdetail pbsVoteOpdetail) {
		super.save(pbsVoteOpdetail);
	}

	@Transactional(readOnly = false)
	public void delete(PbsVoteOpdetail pbsVoteOpdetail) {
		super.delete(pbsVoteOpdetail);
	}

	@Transactional(readOnly = false)
	public void insertAll(List<PbsVoteOpdetail> pbsVoteOpdetails) {
		//  赋予基本变量
		for (PbsVoteOpdetail PbsVoteOpdetail : pbsVoteOpdetails) {
			PbsVoteOpdetail.preInsert();
		}
		pbsVoteOpdetailDao.insertAll(pbsVoteOpdetails);
	}

}