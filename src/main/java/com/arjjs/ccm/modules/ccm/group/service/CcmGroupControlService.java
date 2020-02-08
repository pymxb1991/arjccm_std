/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.group.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.group.entity.CcmGroupControl;
import com.arjjs.ccm.modules.ccm.group.dao.CcmGroupControlDao;

/**
 * 自治群管理Service
 * @author liuyongjian
 * @version 2019-08-06
 */
@Service
@Transactional(readOnly = true)
public class CcmGroupControlService extends CrudService<CcmGroupControlDao, CcmGroupControl> {

	public CcmGroupControl get(String id) {
		return super.get(id);
	}
	
	public List<CcmGroupControl> findList(CcmGroupControl ccmGroupControl) {
		return super.findList(ccmGroupControl);
	}
	
	public Page<CcmGroupControl> findPage(Page<CcmGroupControl> page, CcmGroupControl ccmGroupControl) {
		return super.findPage(page, ccmGroupControl);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmGroupControl ccmGroupControl) {
		super.save(ccmGroupControl);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmGroupControl ccmGroupControl) {
		super.delete(ccmGroupControl);
	}
	
}