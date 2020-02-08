/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.proposal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.proposal.entity.PbsProposalopt;
import com.arjjs.ccm.modules.pbs.proposal.dao.PbsProposaloptDao;

/**
 * 建议操作信息Service
 * @author lc
 * @version 2018-05-31
 */
@Service
@Transactional(readOnly = true)
public class PbsProposaloptService extends CrudService<PbsProposaloptDao, PbsProposalopt> {
	
	@Autowired
	private PbsProposalService pbsProposalService;
	

	public PbsProposalopt get(String id) {
		return super.get(id);
	}
	
	public List<PbsProposalopt> findList(PbsProposalopt pbsProposalopt) {
		return super.findList(pbsProposalopt);
	}
	
	public Page<PbsProposalopt> findPage(Page<PbsProposalopt> page, PbsProposalopt pbsProposalopt) {
		return super.findPage(page, pbsProposalopt);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsProposalopt pbsProposalopt) {
		super.save(pbsProposalopt);
		// 更新 当前的 建议 的人数
		pbsProposalService.updateiCntById(pbsProposalopt.getsProposalid().getId());
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsProposalopt pbsProposalopt) {
		super.delete(pbsProposalopt);
		// 更新当前的 操作人数 
		pbsProposalService.updateiCntById(pbsProposalopt.getsProposalid().getId());
	}
	
}