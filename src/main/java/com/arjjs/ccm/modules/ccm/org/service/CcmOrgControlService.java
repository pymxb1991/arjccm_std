/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgControl;
import com.arjjs.ccm.modules.ccm.org.dao.CcmOrgControlDao;

/**
 * 自治组织管理Service
 * @author liuyongjian
 * @version 2019-08-13
 */
@Service
@Transactional(readOnly = true)
public class CcmOrgControlService extends CrudService<CcmOrgControlDao, CcmOrgControl> {

	public CcmOrgControl get(String id) {
		return super.get(id);
	}
	
	public List<CcmOrgControl> findList(CcmOrgControl ccmOrgControl) {
		return super.findList(ccmOrgControl);
	}
	
	public Page<CcmOrgControl> findPage(Page<CcmOrgControl> page, CcmOrgControl ccmOrgControl) {
		return super.findPage(page, ccmOrgControl);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmOrgControl ccmOrgControl) {
		super.save(ccmOrgControl);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmOrgControl ccmOrgControl) {
		super.delete(ccmOrgControl);
	}
	
}