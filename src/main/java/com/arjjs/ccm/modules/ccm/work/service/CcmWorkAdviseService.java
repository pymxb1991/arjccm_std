/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkAdvise;
import com.arjjs.ccm.modules.ccm.work.dao.CcmWorkAdviseDao;

/**
 * 意见建议Service
 * @author liang
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class CcmWorkAdviseService extends CrudService<CcmWorkAdviseDao, CcmWorkAdvise> {

	public CcmWorkAdvise get(String id) {
		return super.get(id);
	}
	
	public List<CcmWorkAdvise> findList(CcmWorkAdvise ccmWorkAdvise) {
		return super.findList(ccmWorkAdvise);
	}
	
	public Page<CcmWorkAdvise> findPage(Page<CcmWorkAdvise> page, CcmWorkAdvise ccmWorkAdvise) {
		return super.findPage(page, ccmWorkAdvise);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmWorkAdvise ccmWorkAdvise) {
		super.save(ccmWorkAdvise);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmWorkAdvise ccmWorkAdvise) {
		super.delete(ccmWorkAdvise);
	}
	
}