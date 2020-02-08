/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgInfovideo;
import com.arjjs.ccm.modules.ccm.org.dao.CcmOrgInfovideoDao;

/**
 * 综治视联网信息中心Service
 * @author fu
 * @version 2018-01-26
 */
@Service
@Transactional(readOnly = true)
public class CcmOrgInfovideoService extends CrudService<CcmOrgInfovideoDao, CcmOrgInfovideo> {

	public CcmOrgInfovideo get(String id) {
		return super.get(id);
	}
	
	public List<CcmOrgInfovideo> findList(CcmOrgInfovideo ccmOrgInfovideo) {
		return super.findList(ccmOrgInfovideo);
	}
	
	public Page<CcmOrgInfovideo> findPage(Page<CcmOrgInfovideo> page, CcmOrgInfovideo ccmOrgInfovideo) {
		return super.findPage(page, ccmOrgInfovideo);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmOrgInfovideo ccmOrgInfovideo) {
		super.save(ccmOrgInfovideo);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmOrgInfovideo ccmOrgInfovideo) {
		super.delete(ccmOrgInfovideo);
	}
	
}