/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.device.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.iot.device.entity.CcmDeviceControl;
import com.arjjs.ccm.modules.iot.device.dao.CcmDeviceControlDao;

/**
 * 探针布控Service
 * @author lhf
 * @version 2019-07-23
 */
@Service
@Transactional(readOnly = true)
public class CcmDeviceControlService extends CrudService<CcmDeviceControlDao, CcmDeviceControl> {

	@Autowired
	private CcmDeviceControlDao ccmDeviceControlDao;

	public CcmDeviceControl get(String id) {
		return super.get(id);
	}
	
	public List<CcmDeviceControl> findList(CcmDeviceControl ccmDeviceControl) {
		return super.findList(ccmDeviceControl);
	}
	
	public Page<CcmDeviceControl> findPage(Page<CcmDeviceControl> page, CcmDeviceControl ccmDeviceControl) {
		return super.findPage(page, ccmDeviceControl);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmDeviceControl ccmDeviceControl) {
		super.save(ccmDeviceControl);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmDeviceControl ccmDeviceControl) {
		super.delete(ccmDeviceControl);
	}

	@Transactional(readOnly = false)
	public void deleteByIdent(CcmDeviceControl ccmDeviceControl) {
		this.ccmDeviceControlDao.deleteByIdent(ccmDeviceControl);
	}

    public List<Map<String, String>> getCount(CcmDeviceControl ccmDeviceControl) {
		return ccmDeviceControlDao.getCount(ccmDeviceControl);
    }
}