/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.proposal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.proposal.entity.PbsProposalareaHis;
import com.arjjs.ccm.modules.pbs.proposal.dao.PbsProposalareaHisDao;

/**
 * 建议分区历史信息Service
 * @author lc
 * @version 2018-05-31
 */
@Service
@Transactional(readOnly = true)
public class PbsProposalareaHisService extends CrudService<PbsProposalareaHisDao, PbsProposalareaHis> {

	public PbsProposalareaHis get(String id) {
		return super.get(id);
	}
	
	public List<PbsProposalareaHis> findList(PbsProposalareaHis pbsProposalareaHis) {
		return super.findList(pbsProposalareaHis);
	}
	
	public Page<PbsProposalareaHis> findPage(Page<PbsProposalareaHis> page, PbsProposalareaHis pbsProposalareaHis) {
		return super.findPage(page, pbsProposalareaHis);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsProposalareaHis pbsProposalareaHis) {
		super.save(pbsProposalareaHis);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsProposalareaHis pbsProposalareaHis) {
		super.delete(pbsProposalareaHis);
	}
	
}