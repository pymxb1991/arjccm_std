/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyserverproject.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.partyprojectpost.service.CcmPartyProjectPostService;
import com.arjjs.ccm.modules.ccm.partyserverproject.dao.CcmPartyServerProjectDao;
import com.arjjs.ccm.modules.ccm.partyserverproject.entity.CcmPartyServerProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务项目管理Service
 * @author cby
 * @version 2019-08-15
 */
@Service
@Transactional(readOnly = true)
public class CcmPartyServerProjectService extends CrudService<CcmPartyServerProjectDao, CcmPartyServerProject> {

	@Autowired
	private CcmPartyProjectPostService ccmPartyProjectPostService;

	public CcmPartyServerProject get(String id) {
		return super.get(id);
	}
	
	public List<CcmPartyServerProject> findList(CcmPartyServerProject ccmPartyServerProject) {
		return super.findList(ccmPartyServerProject);
	}
	
	public Page<CcmPartyServerProject> findPage(Page<CcmPartyServerProject> page, CcmPartyServerProject ccmPartyServerProject) {
		page = super.findPage(page, ccmPartyServerProject);
		List<CcmPartyServerProject> list = new ArrayList<>();
		for (CcmPartyServerProject serverProject: page.getList()) {
			int count = ccmPartyProjectPostService.countProPostNum(serverProject.getId());
			serverProject.setRelclailNum( count +"");
			list.add(serverProject);
		}
		ccmPartyServerProject.setPage(page);
		page.setList(list);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPartyServerProject ccmPartyServerProject) {
		super.save(ccmPartyServerProject);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPartyServerProject ccmPartyServerProject) {
		super.delete(ccmPartyServerProject);
	}
	
}