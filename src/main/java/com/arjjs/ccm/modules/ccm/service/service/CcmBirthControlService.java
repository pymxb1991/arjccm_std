/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.service.entity.CcmBirthControl;
import com.arjjs.ccm.modules.ccm.service.dao.CcmBirthControlDao;

/**
 * 计生管理Service
 * @author pengjianqiang
 * @version 2019-02-25
 */
@Service
@Transactional(readOnly = true)
public class CcmBirthControlService extends CrudService<CcmBirthControlDao, CcmBirthControl> {

	public CcmBirthControl get(String id) {
		return super.get(id);
	}
	
	public List<CcmBirthControl> findList(CcmBirthControl ccmBirthControl) {
		return super.findList(ccmBirthControl);
	}
	
	public Page<CcmBirthControl> findPage(Page<CcmBirthControl> page, CcmBirthControl ccmBirthControl) {
		return super.findPage(page, ccmBirthControl);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmBirthControl ccmBirthControl) {
		super.save(ccmBirthControl);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmBirthControl ccmBirthControl) {
		super.delete(ccmBirthControl);
	}
	
}