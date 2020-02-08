/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivitytype;
import com.arjjs.ccm.modules.pbs.activity.dao.PbsActivitytypeDao;

/**
 * 活动定义Service
 * @author lc
 * @version 2018-05-14
 */
@Service
@Transactional(readOnly = true)
public class PbsActivitytypeService extends CrudService<PbsActivitytypeDao, PbsActivitytype> {

	public PbsActivitytype get(String id) {
		return super.get(id);
	}
	
	public List<PbsActivitytype> findList(PbsActivitytype pbsActivitytype) {
		return super.findList(pbsActivitytype);
	}
	
	public Page<PbsActivitytype> findPage(Page<PbsActivitytype> page, PbsActivitytype pbsActivitytype) {
		return super.findPage(page, pbsActivitytype);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsActivitytype pbsActivitytype) {
		super.save(pbsActivitytype);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsActivitytype pbsActivitytype) {
		super.delete(pbsActivitytype);
	}
	
	public String findGroupById(String id) {
		return dao.findGroupById(id);
	}
	
}