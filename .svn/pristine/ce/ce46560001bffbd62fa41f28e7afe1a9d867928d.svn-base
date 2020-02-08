/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.service.entity.CcmCommunityWork;
import com.arjjs.ccm.modules.ccm.service.dao.CcmCommunityWorkDao;

/**
 * 社区服务Service
 * @author arj
 * @version 2018-03-09
 */
@Service
@Transactional(readOnly = true)
public class CcmCommunityWorkService extends CrudService<CcmCommunityWorkDao, CcmCommunityWork> {

	public CcmCommunityWork get(String id) {
		return super.get(id);
	}
	
	public List<CcmCommunityWork> findList(CcmCommunityWork ccmCommunityWork) {
		return super.findList(ccmCommunityWork);
	}
	
	public Page<CcmCommunityWork> findPage(Page<CcmCommunityWork> page, CcmCommunityWork ccmCommunityWork) {
		return super.findPage(page, ccmCommunityWork);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmCommunityWork ccmCommunityWork) {
		super.save(ccmCommunityWork);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmCommunityWork ccmCommunityWork) {
		super.delete(ccmCommunityWork);
	}
	
}