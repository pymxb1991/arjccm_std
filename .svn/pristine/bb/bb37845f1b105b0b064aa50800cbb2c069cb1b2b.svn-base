/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.car.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.car.entity.CcmCarControl;
import com.arjjs.ccm.modules.ccm.car.dao.CcmCarControlDao;

/**
 * 车辆布控实体类Service
 * @author lgh
 * @version 2019-06-03
 */
@Service
@Transactional(readOnly = true)
public class CcmCarControlService extends CrudService<CcmCarControlDao, CcmCarControl> {

	@Autowired
	private CcmCarControlDao ccmCarControlDao;

	public CcmCarControl get(String id) {
		return super.get(id);
	}
	
	public List<CcmCarControl> findList(CcmCarControl ccmCarControl) {
		return super.findList(ccmCarControl);
	}
	
	public Page<CcmCarControl> findPage(Page<CcmCarControl> page, CcmCarControl ccmCarControl) {
		return super.findPage(page, ccmCarControl);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmCarControl ccmCarControl) {
		super.save(ccmCarControl);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmCarControl ccmCarControl) {
		super.delete(ccmCarControl);
	}

	@Transactional(readOnly = false)
	public void deleteByIdent(CcmCarControl ccmCarControl) {
		this.ccmCarControlDao.deleteByIdent(ccmCarControl);
	}

    public int getCount(CcmCarControl ccmCarControl) {
		return ccmCarControlDao.getCount(ccmCarControl);
    }
}