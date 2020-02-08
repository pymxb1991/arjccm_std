/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.policy.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.policy.dao.PlmKnowPolicyDao;
import com.arjjs.ccm.modules.plm.policy.entity.PlmKnowPolicy;

/**
 * 政策法规Service
 * @author liu
 * @version 2018-06-20
 */
@Service
@Transactional(readOnly = true)
public class PlmKnowPolicyService extends CrudService<PlmKnowPolicyDao, PlmKnowPolicy> {

	public PlmKnowPolicy get(String id) {
		return super.get(id);
	}
	
	public List<PlmKnowPolicy> findList(PlmKnowPolicy plmKnowPolicy) {
		return super.findList(plmKnowPolicy);
	}
	
	public Page<PlmKnowPolicy> findPage(Page<PlmKnowPolicy> page, PlmKnowPolicy plmKnowPolicy) {
		return super.findPage(page, plmKnowPolicy);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmKnowPolicy plmKnowPolicy) {
		super.save(plmKnowPolicy);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmKnowPolicy plmKnowPolicy) {
		super.delete(plmKnowPolicy);
	}
	
	@Transactional(readOnly = false)
	public List<PlmKnowPolicy> findView(PlmKnowPolicy plmKnowPolicy){
		return dao.findView(plmKnowPolicy);
	}
}