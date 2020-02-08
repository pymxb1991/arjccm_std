/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.grabber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.iot.grabber.entity.CcmGrabberManage;
import com.arjjs.ccm.modules.iot.grabber.dao.CcmGrabberManageDao;

/**
 * 抓拍机管理Service
 * @author cby
 * @version 2019-07-25
 */
@Service
@Transactional(readOnly = true)
public class CcmGrabberManageService extends CrudService<CcmGrabberManageDao, CcmGrabberManage> {

	@Autowired
	private CcmGrabberManageDao ccmGrabberManageDao;

	@Transactional(readOnly = false)
	public void updateState(CcmGrabberManage ccmGrabberManage) {
		ccmGrabberManageDao.updateState(ccmGrabberManage);
	}
	
	public CcmGrabberManage get(String id) {
		return super.get(id);
	}
	
	public List<CcmGrabberManage> findList(CcmGrabberManage ccmGrabberManage) {
		return super.findList(ccmGrabberManage);
	}
	
	public Page<CcmGrabberManage> findPage(Page<CcmGrabberManage> page, CcmGrabberManage ccmGrabberManage) {
		return super.findPage(page, ccmGrabberManage);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmGrabberManage ccmGrabberManage) {
		super.save(ccmGrabberManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmGrabberManage ccmGrabberManage) {
		super.delete(ccmGrabberManage);
	}

	public int getCount(CcmGrabberManage ccmGrabberManage) {
		return ccmGrabberManageDao.getCount(ccmGrabberManage);
	}
}