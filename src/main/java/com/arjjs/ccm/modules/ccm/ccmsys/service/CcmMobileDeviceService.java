/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.ccmsys.dao.CcmMobileDeviceDao;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmMobileDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 移动设备管理Service
 * @author fu
 * @version 2018-04-20
 */
@Service
@Transactional(readOnly = true)
public class CcmMobileDeviceService extends CrudService<CcmMobileDeviceDao, CcmMobileDevice> {
	@Autowired
	private CcmMobileDeviceDao dao;


	public CcmMobileDevice get(String id) {
		return super.get(id);
	}
	
	public List<CcmMobileDevice> findList(CcmMobileDevice ccmMobileDevice) {
		return super.findList(ccmMobileDevice);
	}
	
	public Page<CcmMobileDevice> findPage(Page<CcmMobileDevice> page, CcmMobileDevice ccmMobileDevice) {
		return super.findPage(page, ccmMobileDevice);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmMobileDevice ccmMobileDevice) {
		super.save(ccmMobileDevice);


	}
	
	@Transactional(readOnly = false)
	public void delete(CcmMobileDevice ccmMobileDevice) {
		super.delete(ccmMobileDevice);
	}

	public CcmMobileDevice findByDeviceId(String deviceId) {
		return dao.findByDeviceId(deviceId);
	}
	public CcmMobileDevice findByDeviceIdAndUserId(String deviceId,String userId) {
		return dao.findByDeviceIdAndUserId(deviceId, userId);
	}

	//App电子围栏
	@Transactional(readOnly = false)
	public boolean updateCoordinates(CcmMobileDevice ccmMobileDevice) {
		return dao.updateCoordinates(ccmMobileDevice) > 0;
	}

	//查询待上传的数据
	public List<CcmMobileDevice> findListApp(CcmMobileDevice ccmMobileDevice) {
		return dao.findListApp(ccmMobileDevice);
	}
	
}