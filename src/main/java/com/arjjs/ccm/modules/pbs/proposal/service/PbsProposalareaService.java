/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.proposal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.proposal.entity.PbsProposalarea;
import com.arjjs.ccm.modules.pbs.proposal.dao.PbsProposalareaDao;

/**
 * 建议分区Service
 * @author lc
 * @version 2018-05-31
 */
@Service
@Transactional(readOnly = true)
public class PbsProposalareaService extends CrudService<PbsProposalareaDao, PbsProposalarea> {

	public PbsProposalarea get(String id) {
		return super.get(id);
	}
	
	public List<PbsProposalarea> findList(PbsProposalarea pbsProposalarea) {
		return super.findList(pbsProposalarea);
	}
	
	public Page<PbsProposalarea> findPage(Page<PbsProposalarea> page, PbsProposalarea pbsProposalarea) {
		return super.findPage(page, pbsProposalarea);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsProposalarea pbsProposalarea) {
		super.save(pbsProposalarea);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsProposalarea pbsProposalarea) {
		super.delete(pbsProposalarea);
	}
	
}