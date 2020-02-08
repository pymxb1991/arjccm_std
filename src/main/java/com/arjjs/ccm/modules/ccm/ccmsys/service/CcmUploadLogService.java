/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmUploadLog;
import com.arjjs.ccm.modules.ccm.ccmsys.dao.CcmUploadLogDao;

/**
 * 待上传上级平台记录管理Service
 * @author pengjianqiang
 * @version 2018-05-12
 */
@Service
@Transactional(readOnly = true)
public class CcmUploadLogService extends CrudService<CcmUploadLogDao, CcmUploadLog> {

	public CcmUploadLog get(String id) {
		return super.get(id);
	}
	
	public List<CcmUploadLog> findList(CcmUploadLog ccmUploadLog) {
		return super.findList(ccmUploadLog);
	}
	
	public Page<CcmUploadLog> findPage(Page<CcmUploadLog> page, CcmUploadLog ccmUploadLog) {
		return super.findPage(page, ccmUploadLog);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmUploadLog ccmUploadLog) {
		super.save(ccmUploadLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmUploadLog ccmUploadLog) {
		super.delete(ccmUploadLog);
	}
	
}