/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.traffic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.traffic.entity.CcmPlaceTraffic;
import com.arjjs.ccm.modules.ccm.traffic.dao.CcmPlaceTrafficDao;

/**
 * 交通出行场所Service
 * @author ljd
 * @version 2019-04-29
 */
@Service
@Transactional(readOnly = true)
public class CcmPlaceTrafficService extends CrudService<CcmPlaceTrafficDao, CcmPlaceTraffic> {

	public CcmPlaceTraffic get(String id) {
		return super.get(id);
	}
	
	public List<CcmPlaceTraffic> findList(CcmPlaceTraffic ccmPlaceTraffic) {
		return super.findList(ccmPlaceTraffic);
	}
	
	public Page<CcmPlaceTraffic> findPage(Page<CcmPlaceTraffic> page, CcmPlaceTraffic ccmPlaceTraffic) {
		return super.findPage(page, ccmPlaceTraffic);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPlaceTraffic ccmPlaceTraffic) {
		super.save(ccmPlaceTraffic);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPlaceTraffic ccmPlaceTraffic) {
		super.delete(ccmPlaceTraffic);
	}
	
}