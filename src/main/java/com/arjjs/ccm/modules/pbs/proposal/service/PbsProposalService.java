/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.proposal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.proposal.entity.PbsProposal;
import com.arjjs.ccm.modules.pbs.proposal.dao.PbsProposalDao;

/**
 * 建议信息Service
 * 
 * @author lc
 * @version 2018-05-31
 */
@Service
@Transactional(readOnly = true)
public class PbsProposalService extends CrudService<PbsProposalDao, PbsProposal> {

	public PbsProposal get(String id) {
		return super.get(id);
	}

	public List<PbsProposal> findList(PbsProposal pbsProposal) {
		return super.findList(pbsProposal);
	}

	public Page<PbsProposal> findPage(Page<PbsProposal> page, PbsProposal pbsProposal) {
		return super.findPage(page, pbsProposal);
	}

	@Transactional(readOnly = false)
	public void save(PbsProposal pbsProposal) {
		super.save(pbsProposal);
	}

	@Transactional(readOnly = false)
	public void delete(PbsProposal pbsProposal) {
		super.delete(pbsProposal);
	}

	// 更新当前 意见 的 回复人数
	@Transactional(readOnly = false)
	public void updateiCntById(String id) {
		dao.updateiCntById(id);
	}
}