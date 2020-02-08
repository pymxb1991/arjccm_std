/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.task.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.task.entity.PbsTasktype;
import com.arjjs.ccm.modules.pbs.task.dao.PbsTasktypeDao;

/**
 * 工作安排类型定义Service
 * @author lc
 * @version 2018-05-03
 */
@Service
@Transactional(readOnly = true)
public class PbsTasktypeService extends CrudService<PbsTasktypeDao, PbsTasktype> {

	public PbsTasktype get(String id) {
		return super.get(id);
	}
	
	public List<PbsTasktype> findList(PbsTasktype pbsTasktype) {
		return super.findList(pbsTasktype);
	}
	
	public Page<PbsTasktype> findPage(Page<PbsTasktype> page, PbsTasktype pbsTasktype) {
		return super.findPage(page, pbsTasktype);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsTasktype pbsTasktype) {
		super.save(pbsTasktype);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsTasktype pbsTasktype) {
		super.delete(pbsTasktype);
	}
	
}