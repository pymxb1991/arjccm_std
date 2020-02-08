/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.religion.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.religion.entity.CcmPlaceReligion;
import com.arjjs.ccm.modules.ccm.religion.dao.CcmPlaceReligionDao;

/**
 * 宗教组织Service
 * @author ljd
 * @version 2019-04-29
 */
@Service
@Transactional(readOnly = true)
public class CcmPlaceReligionService extends CrudService<CcmPlaceReligionDao, CcmPlaceReligion> {

	public CcmPlaceReligion get(String id) {
		return super.get(id);
	}
	
	public List<CcmPlaceReligion> findList(CcmPlaceReligion ccmPlaceReligion) {
		return super.findList(ccmPlaceReligion);
	}
	
	public Page<CcmPlaceReligion> findPage(Page<CcmPlaceReligion> page, CcmPlaceReligion ccmPlaceReligion) {
		return super.findPage(page, ccmPlaceReligion);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPlaceReligion ccmPlaceReligion) {
		super.save(ccmPlaceReligion);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPlaceReligion ccmPlaceReligion) {
		super.delete(ccmPlaceReligion);
	}
	
}