/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmTracingpoint;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmMobileDevice;
import com.arjjs.ccm.modules.ccm.patrol.dao.CcmTracingpointDao;

/**
 * 实时轨迹点Service
 * @author arj
 * @version 2018-03-13
 */
@Service
@Transactional(readOnly = true)
public class CcmTracingpointService extends CrudService<CcmTracingpointDao, CcmTracingpoint> {
	@Autowired
	private CcmTracingpointDao ccmTracingpointDao;
	
	public CcmTracingpoint get(String id) {
		return super.get(id);
	}
	
	public List<CcmTracingpoint> findList(CcmTracingpoint ccmTracingpoint) {
		return super.findList(ccmTracingpoint);
	}
	
	public Page<CcmTracingpoint> findPage(Page<CcmTracingpoint> page, CcmTracingpoint ccmTracingpoint) {
		return super.findPage(page, ccmTracingpoint);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmTracingpoint ccmTracingpoint) {
		if(ccmTracingpoint.getUploadStatus() == null || "".equals(ccmTracingpoint.getUploadStatus())) {
			ccmTracingpoint.setUploadStatus("1");
		}
		super.save(ccmTracingpoint);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmTracingpoint ccmTracingpoint) {
		super.delete(ccmTracingpoint);
	}

	public List<CcmMobileDevice> findDeptDeviceList(CcmMobileDevice ccmMobileDevice) {
		return ccmTracingpointDao.findDeptDeviceList(ccmMobileDevice);
	}

	/**
	 * 真删数据按时间
	 * @param date
	 */
	public void deleteTrue(Date date){
		ccmTracingpointDao.deleteTrue(date);
	}

	public Integer getUserStatus(String id){
		return ccmTracingpointDao.getUserStatus(id);
	}


}