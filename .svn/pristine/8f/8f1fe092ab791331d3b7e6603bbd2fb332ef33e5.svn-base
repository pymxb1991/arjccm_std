/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowentercond;
import com.arjjs.ccm.modules.pbs.flow.dao.PbsFlowentercondDao;

/**
 * 进入节点条件信息Service
 * @author lc
 * @version 2018-04-20
 */
@Service
@Transactional(readOnly = true)
public class PbsFlowentercondService extends CrudService<PbsFlowentercondDao, PbsFlowentercond> {

	public PbsFlowentercond get(String id) {
		return super.get(id);
	}
	
	public List<PbsFlowentercond> findList(PbsFlowentercond pbsFlowentercond) {
		return super.findList(pbsFlowentercond);
	}
	
	public Page<PbsFlowentercond> findPage(Page<PbsFlowentercond> page, PbsFlowentercond pbsFlowentercond) {
		return super.findPage(page, pbsFlowentercond);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsFlowentercond pbsFlowentercond) {
		super.save(pbsFlowentercond);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsFlowentercond pbsFlowentercond) {
		super.delete(pbsFlowentercond);
	}
	
}