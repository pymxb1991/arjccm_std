/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.catering.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.catering.entity.CcmPlaceCatering;
import com.arjjs.ccm.modules.ccm.catering.dao.CcmPlaceCateringDao;

/**
 * 餐饮场所Service
 * @author ljd
 * @version 2019-04-29
 */
@Service
@Transactional(readOnly = true)
public class CcmPlaceCateringService extends CrudService<CcmPlaceCateringDao, CcmPlaceCatering> {

	public CcmPlaceCatering get(String id) {
		return super.get(id);
	}
	
	public List<CcmPlaceCatering> findList(CcmPlaceCatering ccmPlaceCatering) {
		return super.findList(ccmPlaceCatering);
	}
	
	public Page<CcmPlaceCatering> findPage(Page<CcmPlaceCatering> page, CcmPlaceCatering ccmPlaceCatering) {
		return super.findPage(page, ccmPlaceCatering);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPlaceCatering ccmPlaceCatering) {
		super.save(ccmPlaceCatering);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPlaceCatering ccmPlaceCatering) {
		super.delete(ccmPlaceCatering);
	}
	
}