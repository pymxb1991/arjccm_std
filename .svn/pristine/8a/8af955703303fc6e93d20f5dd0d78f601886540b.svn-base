/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyreport.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.partybuild.entity.CcmPartyOrganiz;
import com.arjjs.ccm.modules.ccm.partybuild.service.CcmPartyOrganizService;
import com.arjjs.ccm.modules.ccm.partyperson.entity.CcmPartyPerson;
import com.arjjs.ccm.modules.ccm.partyperson.service.CcmPartyPersonService;
import com.arjjs.ccm.modules.ccm.partyreport.dao.CcmPartyReportDao;
import com.arjjs.ccm.modules.ccm.partyreport.entity.CcmPartyReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 双报道情况管理Service
 * @author maoxb
 * @version 2019-08-14
 */
@Service
@Transactional(readOnly = true)
public class CcmPartyReportService extends CrudService<CcmPartyReportDao, CcmPartyReport> {

	@Autowired
	private CcmPartyOrganizService ccmPartyOrganizService;

	@Autowired
	private CcmPartyPersonService ccmPartyPersonService;

	public CcmPartyReport get(String id) {
		return super.get(id);
	}
	
	public List<CcmPartyReport> findList(CcmPartyReport ccmPartyReport) {
		return super.findList(ccmPartyReport);
	}
	
	public Page<CcmPartyReport> findPage(Page<CcmPartyReport> page, CcmPartyReport ccmPartyReport) {
		page = super.findPage(page, ccmPartyReport);
		String type = ccmPartyReport.getType();
		List<CcmPartyReport> list = new ArrayList<>();
		for (CcmPartyReport partyReport :page.getList()) {
			if ("1".equals(type)){
				CcmPartyOrganiz ccmPartyOrganiz = ccmPartyOrganizService.get(partyReport.getOrgParty());
				partyReport.setOrgPartyEntity(ccmPartyOrganiz);
				list.add(partyReport);
			}else if("2".equals(type)){
				CcmPartyPerson ccmPartyPerson = ccmPartyPersonService.get(partyReport.getOrgParty());
				partyReport.setPerPartyEntity(ccmPartyPerson);
				list.add(partyReport);
			}
		}
		ccmPartyReport.setPage(page);
		page.setList(list);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPartyReport ccmPartyReport) {
		super.save(ccmPartyReport);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPartyReport ccmPartyReport) {
		super.delete(ccmPartyReport);
	}
	
}