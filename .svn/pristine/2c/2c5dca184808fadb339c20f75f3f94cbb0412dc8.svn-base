/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkRelation;
import com.arjjs.ccm.modules.ccm.work.dao.CcmWorkRelationDao;

/**
 * 联系人Service
 * @author liang
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class CcmWorkRelationService extends CrudService<CcmWorkRelationDao, CcmWorkRelation> {

	public CcmWorkRelation get(String id) {
		return super.get(id);
	}
	
	public List<CcmWorkRelation> findList(CcmWorkRelation ccmWorkRelation) {
		return super.findList(ccmWorkRelation);
	}
	
	public Page<CcmWorkRelation> findPage(Page<CcmWorkRelation> page, CcmWorkRelation ccmWorkRelation) {
		return super.findPage(page, ccmWorkRelation);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmWorkRelation ccmWorkRelation) {
		super.save(ccmWorkRelation);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmWorkRelation ccmWorkRelation) {
		super.delete(ccmWorkRelation);
	}
	
}