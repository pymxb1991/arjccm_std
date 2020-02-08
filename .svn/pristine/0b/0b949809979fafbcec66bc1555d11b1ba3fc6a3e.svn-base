/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.place.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.iot.place.entity.CcmPlaceControl;
import com.arjjs.ccm.modules.iot.place.dao.CcmPlaceControlDao;

/**
 * 场所布控Service
 * @author yiqingxuan
 * @version 2019-07-25
 */
@Service
@Transactional(readOnly = true)
public class CcmPlaceControlService extends CrudService<CcmPlaceControlDao, CcmPlaceControl> {

	public CcmPlaceControl get(String id) {
		return super.get(id);
	}
	
	public List<CcmPlaceControl> findList(CcmPlaceControl ccmPlaceControl) {
		return super.findList(ccmPlaceControl);
	}
	
	public Page<CcmPlaceControl> findPage(Page<CcmPlaceControl> page, CcmPlaceControl ccmPlaceControl) {
		return super.findPage(page, ccmPlaceControl);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPlaceControl ccmPlaceControl) {
		super.save(ccmPlaceControl);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPlaceControl ccmPlaceControl) {
		super.delete(ccmPlaceControl);
	}
	
}