/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.live.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.live.entity.CcmPlaceLive;
import com.arjjs.ccm.modules.ccm.live.dao.CcmPlaceLiveDao;

/**
 * 生活配套场所表Service
 * @author lgh
 * @version 2019-04-23
 */
@Service
@Transactional(readOnly = true)
public class CcmPlaceLiveService extends CrudService<CcmPlaceLiveDao, CcmPlaceLive> {

	public CcmPlaceLive get(String id) {
		return super.get(id);
	}
	
	public List<CcmPlaceLive> findList(CcmPlaceLive ccmPlaceLive) {
		return super.findList(ccmPlaceLive);
	}
	
	public Page<CcmPlaceLive> findPage(Page<CcmPlaceLive> page, CcmPlaceLive ccmPlaceLive) {
		return super.findPage(page, ccmPlaceLive);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPlaceLive ccmPlaceLive) {
		super.save(ccmPlaceLive);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPlaceLive ccmPlaceLive) {
		super.delete(ccmPlaceLive);
	}
	
}