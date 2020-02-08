/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowsetstat;
import com.arjjs.ccm.modules.pbs.flow.dao.PbsFlowsetstatDao;

/**
 * 节点操作后修改Service
 * @author lc
 * @version 2018-04-20
 */
@Service
@Transactional(readOnly = true)
public class PbsFlowsetstatService extends CrudService<PbsFlowsetstatDao, PbsFlowsetstat> {

	public PbsFlowsetstat get(String id) {
		return super.get(id);
	}
	
	public List<PbsFlowsetstat> findList(PbsFlowsetstat pbsFlowsetstat) {
		return super.findList(pbsFlowsetstat);
	}
	
	public Page<PbsFlowsetstat> findPage(Page<PbsFlowsetstat> page, PbsFlowsetstat pbsFlowsetstat) {
		return super.findPage(page, pbsFlowsetstat);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsFlowsetstat pbsFlowsetstat) {
		super.save(pbsFlowsetstat);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsFlowsetstat pbsFlowsetstat) {
		super.delete(pbsFlowsetstat);
	}
	
	@Transactional(readOnly = false)
	public void updateOperation(PbsFlowsetstat pbsFlowsetstat){
		dao.updateOperation(pbsFlowsetstat);
	}
}