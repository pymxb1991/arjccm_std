/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.event.entity.CcmAlarmLog;
import com.arjjs.ccm.modules.ccm.event.dao.CcmAlarmLogDao;

/**
 * 告警日志查询Service
 * @author pengjianqiang
 * @version 2018-05-03
 */
@Service
@Transactional(readOnly = true)
public class CcmAlarmLogService extends CrudService<CcmAlarmLogDao, CcmAlarmLog> {

	@Autowired
	private CcmAlarmLogDao ccmAlarmLogDao;
	
	
	public CcmAlarmLog get(String id) {
		return super.get(id);
	}
	
	public List<CcmAlarmLog> findList(CcmAlarmLog ccmAlarmLog) {
		return super.findList(ccmAlarmLog);
	}
	
	public Page<CcmAlarmLog> findPage(Page<CcmAlarmLog> page, CcmAlarmLog ccmAlarmLog) {
		return super.findPage(page, ccmAlarmLog);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmAlarmLog ccmAlarmLog) {
		super.save(ccmAlarmLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmAlarmLog ccmAlarmLog) {
		super.delete(ccmAlarmLog);
	}

	//查找判断是否越界告警
	public List<CcmAlarmLog> findListOverstep(CcmAlarmLog ccmAlarmLog) {
		return ccmAlarmLogDao.findListOverstep(ccmAlarmLog);
	}
	
}