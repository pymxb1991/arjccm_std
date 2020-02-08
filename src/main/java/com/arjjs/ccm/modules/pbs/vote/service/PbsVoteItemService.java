/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.vote.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteItem;
import com.arjjs.ccm.modules.pbs.vote.dao.PbsVoteItemDao;

/**
 * 投票题目选项信息Service
 * @author lc
 * @version 2018-03-27
 */
@Service
@Transactional(readOnly = true)
public class PbsVoteItemService extends CrudService<PbsVoteItemDao, PbsVoteItem> {

	public PbsVoteItem get(String id) {
		return super.get(id);
	}
	
	public List<PbsVoteItem> findList(PbsVoteItem pbsVoteItem) {
		return super.findList(pbsVoteItem);
	}
	
	public Page<PbsVoteItem> findPage(Page<PbsVoteItem> page, PbsVoteItem pbsVoteItem) {
		return super.findPage(page, pbsVoteItem);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsVoteItem pbsVoteItem) {
		super.save(pbsVoteItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsVoteItem pbsVoteItem) {
		super.delete(pbsVoteItem);
	}
	
}