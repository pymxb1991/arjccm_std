/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFnodeapprover;
import com.arjjs.ccm.modules.pbs.flow.dao.PbsFnodeapproverDao;

/**
 * 流程审核人信息Service
 * @author lc
 * @version 2018-04-20
 */
@Service
@Transactional(readOnly = true)
public class PbsFnodeapproverService extends CrudService<PbsFnodeapproverDao, PbsFnodeapprover> {

	public PbsFnodeapprover get(String id) {
		return super.get(id);
	}
	
	public List<PbsFnodeapprover> findList(PbsFnodeapprover pbsFnodeapprover) {
		return super.findList(pbsFnodeapprover);
	}
	
	public Page<PbsFnodeapprover> findPage(Page<PbsFnodeapprover> page, PbsFnodeapprover pbsFnodeapprover) {
		return super.findPage(page, pbsFnodeapprover);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsFnodeapprover pbsFnodeapprover) {
		super.save(pbsFnodeapprover);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsFnodeapprover pbsFnodeapprover) {
		super.delete(pbsFnodeapprover);
	}
	
}