/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.carpass.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.carpass.entity.CcmCarPass;
import com.arjjs.ccm.modules.ccm.carpass.dao.CcmCarPassDao;

/**
 * 过车查询Service
 * @author liuyongjian
 * @version 2019-07-24
 */
@Service
@Transactional(readOnly = true)
public class CcmCarPassService extends CrudService<CcmCarPassDao, CcmCarPass> {

	public CcmCarPass get(String id) {
		return super.get(id);
	}
	
	public List<CcmCarPass> findList(CcmCarPass ccmCarPass) {
		return super.findList(ccmCarPass);
	}
	
	public Page<CcmCarPass> findPage(Page<CcmCarPass> page, CcmCarPass ccmCarPass) {
		return super.findPage(page, ccmCarPass);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmCarPass ccmCarPass) {
		super.save(ccmCarPass);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmCarPass ccmCarPass) {
		super.delete(ccmCarPass);
	}
	
}