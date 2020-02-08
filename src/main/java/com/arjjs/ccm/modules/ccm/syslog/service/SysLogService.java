/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.syslog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.syslog.entity.SysLog;
import com.arjjs.ccm.modules.ccm.syslog.dao.SysLogDao;

/**
 * 用户登录查询Service
 * @author liujindan
 * @version 2019-07-09
 */
@Service
@Transactional(readOnly = true)
public class SysLogService extends CrudService<SysLogDao, SysLog> {

	public SysLog get(String id) {
		return super.get(id);
	}
	
	public List<SysLog> findList(SysLog sysLog) {
		return super.findList(sysLog);
	}
	
	public Page<SysLog> findPage(Page<SysLog> page, SysLog sysLog) {
		return super.findPage(page, sysLog);
	}
	
	@Transactional(readOnly = false)
	public void save(SysLog sysLog) {
		super.save(sysLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysLog sysLog) {
		super.delete(sysLog);
	}
	
}