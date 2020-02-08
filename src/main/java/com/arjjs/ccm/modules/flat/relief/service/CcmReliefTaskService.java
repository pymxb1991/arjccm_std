/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.relief.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.flat.relief.entity.CcmReliefTask;
import com.arjjs.ccm.modules.flat.relief.dao.CcmReliefTaskDao;

/**
 * 备勤任务实体类Service
 * @author lgh
 * @version 2019-07-25
 */
@Service
@Transactional(readOnly = true)
public class CcmReliefTaskService extends CrudService<CcmReliefTaskDao, CcmReliefTask> {

	public CcmReliefTask get(String id) {
		return super.get(id);
	}
	
	public List<CcmReliefTask> findList(CcmReliefTask ccmReliefTask) {
		return super.findList(ccmReliefTask);
	}
	
	public Page<CcmReliefTask> findPage(Page<CcmReliefTask> page, CcmReliefTask ccmReliefTask) {
		return super.findPage(page, ccmReliefTask);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmReliefTask ccmReliefTask) {
		super.save(ccmReliefTask);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmReliefTask ccmReliefTask) {
		super.delete(ccmReliefTask);
	}

	public Page<CcmReliefTask> findApp(Page<CcmReliefTask> page, CcmReliefTask ccmReliefTask) {
		ccmReliefTask.setPage(page);
		page.setList(dao.findList(ccmReliefTask));
		return page;
	}
}