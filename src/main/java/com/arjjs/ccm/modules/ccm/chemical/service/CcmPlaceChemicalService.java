/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.chemical.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.chemical.entity.CcmPlaceChemical;
import com.arjjs.ccm.modules.ccm.chemical.dao.CcmPlaceChemicalDao;

/**
 * 危化品经营Service
 * @author ljd
 * @version 2019-04-29
 */
@Service
@Transactional(readOnly = true)
public class CcmPlaceChemicalService extends CrudService<CcmPlaceChemicalDao, CcmPlaceChemical> {

	public CcmPlaceChemical get(String id) {
		return super.get(id);
	}
	
	public List<CcmPlaceChemical> findList(CcmPlaceChemical ccmPlaceChemical) {
		return super.findList(ccmPlaceChemical);
	}
	
	public Page<CcmPlaceChemical> findPage(Page<CcmPlaceChemical> page, CcmPlaceChemical ccmPlaceChemical) {
		return super.findPage(page, ccmPlaceChemical);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPlaceChemical ccmPlaceChemical) {
		super.save(ccmPlaceChemical);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPlaceChemical ccmPlaceChemical) {
		super.delete(ccmPlaceChemical);
	}
	
}