/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.configure.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.iot.configure.entity.CcmReportConfigure;
import com.arjjs.ccm.modules.iot.configure.dao.CcmReportConfigureDao;

/**
 * 报警配置Service
 * @author cby
 * @version 2019-07-25
 */
@Service
@Transactional(readOnly = true)
public class CcmReportConfigureService extends CrudService<CcmReportConfigureDao, CcmReportConfigure> {

	public CcmReportConfigure get(String id) {
		return super.get(id);
	}
	
	public List<CcmReportConfigure> findList(CcmReportConfigure ccmReportConfigure) {
		return super.findList(ccmReportConfigure);
	}
	
	public Page<CcmReportConfigure> findPage(Page<CcmReportConfigure> page, CcmReportConfigure ccmReportConfigure) {
		return super.findPage(page, ccmReportConfigure);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmReportConfigure ccmReportConfigure) {
		super.save(ccmReportConfigure);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmReportConfigure ccmReportConfigure) {
		super.delete(ccmReportConfigure);
	}
	
}