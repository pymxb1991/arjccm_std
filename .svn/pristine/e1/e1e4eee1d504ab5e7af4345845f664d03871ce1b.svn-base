/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsDepartmentbind;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.dao.PbsDepartmentbindDao;

/**
 * 党员部门关系Service
 * 
 * @author lc
 * @version 2018-04-03
 */
@Service
@Transactional(readOnly = true)
public class PbsDepartmentbindService extends CrudService<PbsDepartmentbindDao, PbsDepartmentbind> {

	@Autowired
	private PbsDepartmentbindDao pbsDepartmentbindDao;

	public PbsDepartmentbind get(String id) {
		return super.get(id);
	}

	public List<PbsDepartmentbind> findList(PbsDepartmentbind pbsDepartmentbind) {
		return super.findList(pbsDepartmentbind);
	}

	public Page<PbsDepartmentbind> findPage(Page<PbsDepartmentbind> page, PbsDepartmentbind pbsDepartmentbind) {
		return super.findPage(page, pbsDepartmentbind);
	}

	@Transactional(readOnly = false)
	public void save(PbsDepartmentbind pbsDepartmentbind) {
		super.save(pbsDepartmentbind);
	}

	@Transactional(readOnly = false)
	public void delete(PbsDepartmentbind pbsDepartmentbind) {
		super.delete(pbsDepartmentbind);
	}

	@Transactional(readOnly = false)
	public boolean checkExist(PbsDepartmentbind pbsDepartmentbind) {
		return pbsDepartmentbindDao.checkExist(pbsDepartmentbind) == 0 ? false : true;
	}

	// 根据申请条件 删除 当前的关系
	@Transactional(readOnly = false)
	public void deleteByRelationship(PbsDepartmentbind pbsDepartmentbind) {
		dao.deleteByRelationship(pbsDepartmentbind);
	}
	
	public List<PbsDepartmentbind> findRecordBypbsPartymemId(PbsPartymem pbsPartymem) {
		return dao.findRecordBypbsPartymemId(pbsPartymem);
	}
}