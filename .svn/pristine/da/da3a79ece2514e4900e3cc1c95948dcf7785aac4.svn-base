/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.hotel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.hotel.entity.CcmPlaceHotel;
import com.arjjs.ccm.modules.ccm.hotel.dao.CcmPlaceHotelDao;

/**
 * 酒店住宿Service
 * @author ljd
 * @version 2019-04-29
 */
@Service
@Transactional(readOnly = true)
public class CcmPlaceHotelService extends CrudService<CcmPlaceHotelDao, CcmPlaceHotel> {

	public CcmPlaceHotel get(String id) {
		return super.get(id);
	}
	
	public List<CcmPlaceHotel> findList(CcmPlaceHotel ccmPlaceHotel) {
		return super.findList(ccmPlaceHotel);
	}
	
	public Page<CcmPlaceHotel> findPage(Page<CcmPlaceHotel> page, CcmPlaceHotel ccmPlaceHotel) {
		return super.findPage(page, ccmPlaceHotel);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPlaceHotel ccmPlaceHotel) {
		super.save(ccmPlaceHotel);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPlaceHotel ccmPlaceHotel) {
		super.delete(ccmPlaceHotel);
	}
	
}