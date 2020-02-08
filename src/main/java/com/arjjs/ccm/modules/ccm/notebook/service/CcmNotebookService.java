/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.notebook.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.notebook.entity.CcmNotebook;
import com.arjjs.ccm.modules.ccm.notebook.dao.CcmNotebookDao;

/**
 * 记事本Service
 * @author liuyongjian
 * @version 2019-06-18
 */
@Service
@Transactional(readOnly = true)
public class CcmNotebookService extends CrudService<CcmNotebookDao, CcmNotebook> {

	public CcmNotebook get(String id) {
		return super.get(id);
	}
	
	public List<CcmNotebook> findList(CcmNotebook ccmNotebook) {
		return super.findList(ccmNotebook);
	}
	
	public Page<CcmNotebook> findPage(Page<CcmNotebook> page, CcmNotebook ccmNotebook) {
		return super.findPage(page, ccmNotebook);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmNotebook ccmNotebook) {
		super.save(ccmNotebook);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmNotebook ccmNotebook) {
		super.delete(ccmNotebook);
	}
	
}