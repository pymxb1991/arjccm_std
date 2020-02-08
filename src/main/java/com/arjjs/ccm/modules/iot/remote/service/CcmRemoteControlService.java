/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.remote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.iot.remote.entity.CcmRemoteControl;
import com.arjjs.ccm.modules.iot.remote.dao.CcmRemoteControlDao;

/**
 * 远程控制Service
 * @author cby
 * @version 2019-07-24
 */
@Service
@Transactional(readOnly = true)
public class CcmRemoteControlService extends CrudService<CcmRemoteControlDao, CcmRemoteControl> {
	@Autowired
	private CcmRemoteControlDao ccmRemoteControlDao;

	public CcmRemoteControl get(String id) {
		return super.get(id);
	}
	
	public List<CcmRemoteControl> findList(CcmRemoteControl ccmRemoteControl) {
		return super.findList(ccmRemoteControl);
	}
	
	public Page<CcmRemoteControl> findPage(Page<CcmRemoteControl> page, CcmRemoteControl ccmRemoteControl) {
		return super.findPage(page, ccmRemoteControl);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmRemoteControl ccmRemoteControl) {
		super.save(ccmRemoteControl);
	}
	
	@Transactional(readOnly = false)
	public void updatestate(CcmRemoteControl ccmRemoteControl) {
		ccmRemoteControlDao.updatestate(ccmRemoteControl);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmRemoteControl ccmRemoteControl) {
		super.delete(ccmRemoteControl);
	}
	
}