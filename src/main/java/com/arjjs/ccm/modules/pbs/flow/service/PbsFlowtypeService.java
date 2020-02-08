/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowtype;
import com.arjjs.ccm.modules.pbs.flow.dao.PbsFlowtypeDao;

/**
 * 流程类型Service
 * @author lc
 * @version 2018-04-19
 */
@Service
@Transactional(readOnly = true)
public class PbsFlowtypeService extends CrudService<PbsFlowtypeDao, PbsFlowtype> {

	public PbsFlowtype get(String id) {
		return super.get(id);
	}
	
	public List<PbsFlowtype> findList(PbsFlowtype pbsFlowtype) {
		return super.findList(pbsFlowtype);
	}
	
	public Page<PbsFlowtype> findPage(Page<PbsFlowtype> page, PbsFlowtype pbsFlowtype) {
		return super.findPage(page, pbsFlowtype);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsFlowtype pbsFlowtype) {
		super.save(pbsFlowtype);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsFlowtype pbsFlowtype) {
		super.delete(pbsFlowtype);
	}
	
}