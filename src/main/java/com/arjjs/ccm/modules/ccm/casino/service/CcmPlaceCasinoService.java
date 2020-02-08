/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.casino.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.casino.entity.CcmPlaceCasino;
import com.arjjs.ccm.modules.ccm.casino.dao.CcmPlaceCasinoDao;

/**
 * 娱乐场所Service
 * @author ljd
 * @version 2019-04-25
 */
@Service
@Transactional(readOnly = true)
public class CcmPlaceCasinoService extends CrudService<CcmPlaceCasinoDao, CcmPlaceCasino> {

	public CcmPlaceCasino get(String id) {
		return super.get(id);
	}
	
	public List<CcmPlaceCasino> findList(CcmPlaceCasino ccmPlaceCasino) {
		return super.findList(ccmPlaceCasino);
	}
	
	public Page<CcmPlaceCasino> findPage(Page<CcmPlaceCasino> page, CcmPlaceCasino ccmPlaceCasino) {
		return super.findPage(page, ccmPlaceCasino);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPlaceCasino ccmPlaceCasino) {
		super.save(ccmPlaceCasino);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPlaceCasino ccmPlaceCasino) {
		super.delete(ccmPlaceCasino);
	}
	
}