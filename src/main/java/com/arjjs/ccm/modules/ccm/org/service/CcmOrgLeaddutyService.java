/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.service;

import java.util.List;

import com.arjjs.ccm.tool.EchartType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgLeadduty;
import com.arjjs.ccm.modules.ccm.org.dao.CcmOrgLeaddutyDao;

/**
 * 综治领导责任制Service
 * @author wwh
 * @version 2018-01-23
 */
@Service
@Transactional(readOnly = true)
public class CcmOrgLeaddutyService extends CrudService<CcmOrgLeaddutyDao, CcmOrgLeadduty> {

	public CcmOrgLeadduty get(String id) {
		return super.get(id);
	}
	
	public List<CcmOrgLeadduty> findList(CcmOrgLeadduty ccmOrgLeadduty) {
		return super.findList(ccmOrgLeadduty);
	}
	
	public Page<CcmOrgLeadduty> findPage(Page<CcmOrgLeadduty> page, CcmOrgLeadduty ccmOrgLeadduty) {
		return super.findPage(page, ccmOrgLeadduty);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmOrgLeadduty ccmOrgLeadduty) {
		super.save(ccmOrgLeadduty);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmOrgLeadduty ccmOrgLeadduty) {
		super.delete(ccmOrgLeadduty);
	}

	/**
	 * @see 根据综治领导责任制统计情况。
	 * @return
	 */
	public List<EchartType> getByPolicyType() {
		return dao.getByPolicyType();
	}
}