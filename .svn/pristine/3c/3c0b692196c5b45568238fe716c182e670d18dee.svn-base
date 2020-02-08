/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowKeyFile;
import com.arjjs.ccm.modules.ccm.know.dao.CcmKnowKeyFileDao;

/**
 * 重要文件Service
 * @author liang
 * @version 2018-03-23
 */
@Service
@Transactional(readOnly = true)
public class CcmKnowKeyFileService extends CrudService<CcmKnowKeyFileDao, CcmKnowKeyFile> {

	public CcmKnowKeyFile get(String id) {
		return super.get(id);
	}
	
	public List<CcmKnowKeyFile> findList(CcmKnowKeyFile ccmKnowKeyFile) {
		return super.findList(ccmKnowKeyFile);
	}
	
	public Page<CcmKnowKeyFile> findPage(Page<CcmKnowKeyFile> page, CcmKnowKeyFile ccmKnowKeyFile) {
		return super.findPage(page, ccmKnowKeyFile);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmKnowKeyFile ccmKnowKeyFile) {
		super.save(ccmKnowKeyFile);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmKnowKeyFile ccmKnowKeyFile) {
		super.delete(ccmKnowKeyFile);
	}
	
}