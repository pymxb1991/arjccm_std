/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseAreainfor;
import com.arjjs.ccm.modules.ccm.house.dao.CcmHouseAreainforDao;

/**
 * 辖区信息Service
 * @author wwh
 * @version 2018-01-23
 */
@Service
@Transactional(readOnly = true)
public class CcmHouseAreainforService extends CrudService<CcmHouseAreainforDao, CcmHouseAreainfor> {

	public CcmHouseAreainfor get(String id) {
		return super.get(id);
	}
	
	public List<CcmHouseAreainfor> findList(CcmHouseAreainfor ccmHouseAreainfor) {
		return super.findList(ccmHouseAreainfor);
	}
	
	public Page<CcmHouseAreainfor> findPage(Page<CcmHouseAreainfor> page, CcmHouseAreainfor ccmHouseAreainfor) {
		return super.findPage(page, ccmHouseAreainfor);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmHouseAreainfor ccmHouseAreainfor) {
		super.save(ccmHouseAreainfor);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmHouseAreainfor ccmHouseAreainfor) {
		super.delete(ccmHouseAreainfor);
	}
	
}