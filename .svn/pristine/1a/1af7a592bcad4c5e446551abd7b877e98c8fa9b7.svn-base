/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrollog.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.patrollog.dao.CcmPatrolCheckDao;
import com.arjjs.ccm.modules.ccm.patrollog.entity.CcmPatrolCheck;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 巡检考评Service
 * @author 刘永建
 * @version 2019-07-15
 */
@Service
@Transactional(readOnly = true)
public class CcmPatrolCheckService extends CrudService<CcmPatrolCheckDao, CcmPatrolCheck> {

	public CcmPatrolCheck get(String id) {
		return super.get(id);
	}
	
	public List<CcmPatrolCheck> findList(CcmPatrolCheck ccmPatrolCheck) {
		return super.findList(ccmPatrolCheck);
	}
	
	public Page<CcmPatrolCheck> findPage(Page<CcmPatrolCheck> page, CcmPatrolCheck ccmPatrolCheck) {
		User user = UserUtils.getUser();
		if(user==null){
			return new Page<CcmPatrolCheck>();
		}
		ccmPatrolCheck.getSqlMap().put("dsf", dataScopeFilter(ccmPatrolCheck.getCurrentUser(), "o", "u"));
		return super.findPage(page, ccmPatrolCheck);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPatrolCheck ccmPatrolCheck) {
		super.save(ccmPatrolCheck);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPatrolCheck ccmPatrolCheck) {
		super.delete(ccmPatrolCheck);
	}
	
}