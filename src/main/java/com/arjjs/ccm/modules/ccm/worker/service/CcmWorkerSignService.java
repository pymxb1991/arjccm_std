/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.worker.service;

import java.util.List;

import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.worker.entity.CcmWorkerSign;
import com.arjjs.ccm.modules.ccm.worker.dao.CcmWorkerSignDao;

/**
 * 社工签到Service
 * @author yiqingxuan
 * @version 2019-06-17
 */
@Service
@Transactional(readOnly = true)
public class CcmWorkerSignService extends CrudService<CcmWorkerSignDao, CcmWorkerSign> {

	public CcmWorkerSign get(String id) {
		return super.get(id);
	}
	
	public List<CcmWorkerSign> findList(CcmWorkerSign ccmWorkerSign) {
		return super.findList(ccmWorkerSign);
	}
	
	public Page<CcmWorkerSign> findPage(Page<CcmWorkerSign> page, CcmWorkerSign ccmWorkerSign) {
		if(UserUtils.getUser().getId()!=null){
			ccmWorkerSign.getSqlMap().put("dsf", dataScopeFilter( UserUtils.getUser(), "o", "u2"));
		} else {
			if(ccmWorkerSign.getUser()!=null){
				ccmWorkerSign.getSqlMap().put("dsf", dataScopeFilter( ccmWorkerSign.getUser(), "o", "u2"));
			}
		}
		return super.findPage(page, ccmWorkerSign);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmWorkerSign ccmWorkerSign) {
		super.save(ccmWorkerSign);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmWorkerSign ccmWorkerSign) {
		super.delete(ccmWorkerSign);
	}
	
}