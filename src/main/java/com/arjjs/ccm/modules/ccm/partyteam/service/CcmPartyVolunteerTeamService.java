/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyteam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.partyteam.entity.CcmPartyVolunteerTeam;
import com.arjjs.ccm.modules.ccm.partyteam.dao.CcmPartyVolunteerTeamDao;

/**
 * 队伍管理Service
 * @author maoxb
 * @version 2019-08-13
 */
@Service
@Transactional(readOnly = true)
public class CcmPartyVolunteerTeamService extends CrudService<CcmPartyVolunteerTeamDao, CcmPartyVolunteerTeam> {

	public CcmPartyVolunteerTeam get(String id) {
		return super.get(id);
	}
	
	public List<CcmPartyVolunteerTeam> findList(CcmPartyVolunteerTeam ccmPartyVolunteerTeam) {
		return super.findList(ccmPartyVolunteerTeam);
	}
	
	public Page<CcmPartyVolunteerTeam> findPage(Page<CcmPartyVolunteerTeam> page, CcmPartyVolunteerTeam ccmPartyVolunteerTeam) {
		return super.findPage(page, ccmPartyVolunteerTeam);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPartyVolunteerTeam ccmPartyVolunteerTeam) {
		super.save(ccmPartyVolunteerTeam);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPartyVolunteerTeam ccmPartyVolunteerTeam) {
		super.delete(ccmPartyVolunteerTeam);
	}
	
}