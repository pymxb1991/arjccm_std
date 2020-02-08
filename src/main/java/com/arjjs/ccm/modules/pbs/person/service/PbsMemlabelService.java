/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsMemlabel;
import com.arjjs.ccm.modules.pbs.person.dao.PbsMemlabelDao;

/**
 * 人物标签Service
 * @author lc
 * @version 2018-08-03
 */
@Service
@Transactional(readOnly = true)
public class PbsMemlabelService extends CrudService<PbsMemlabelDao, PbsMemlabel> {

	public PbsMemlabel get(String id) {
		return super.get(id);
	}
	
	public List<PbsMemlabel> findList(PbsMemlabel pbsMemlabel) {
		return super.findList(pbsMemlabel);
	}
	
	public Page<PbsMemlabel> findPage(Page<PbsMemlabel> page, PbsMemlabel pbsMemlabel) {
		return super.findPage(page, pbsMemlabel);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsMemlabel pbsMemlabel) {
		super.save(pbsMemlabel);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsMemlabel pbsMemlabel) {
		super.delete(pbsMemlabel);
	}
	
	@Transactional(readOnly = false)
	public void deleteBymemId(String pbspartymemId) {
		dao.deleteBymemId(pbspartymemId);
	}
	
}