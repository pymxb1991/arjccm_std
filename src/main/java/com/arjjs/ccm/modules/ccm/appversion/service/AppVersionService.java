/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.appversion.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.appversion.entity.AppVersion;
import com.arjjs.ccm.modules.ccm.appversion.dao.AppVersionDao;

/**
 * app版本Service
 * @author lijiupeng
 * @version 2019-08-13
 */
@Service
@Transactional(readOnly = true)
public class AppVersionService extends CrudService<AppVersionDao, AppVersion> {

	public AppVersion get(String id) {
		return super.get(id);
	}
	
	public List<AppVersion> findList(AppVersion appVersion) {
		return super.findList(appVersion);
	}
	
	public Page<AppVersion> findPage(Page<AppVersion> page, AppVersion appVersion) {
		return super.findPage(page, appVersion);
	}
	
	@Transactional(readOnly = false)
	public void save(AppVersion appVersion) {
		super.save(appVersion);
	}
	
	@Transactional(readOnly = false)
	public void delete(AppVersion appVersion) {
		super.delete(appVersion);
	}
	
}