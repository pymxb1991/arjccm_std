/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.org.dao.CcmOrgTeamDao;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgTeam;
import com.arjjs.ccm.modules.ccm.org.entity.CcmUserDevicePo;
import com.arjjs.ccm.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 综治队伍Service
 * @author liang
 * @version 2018-01-13
 */
@Service
@Transactional(readOnly = true)
public class CcmOrgTeamService extends CrudService<CcmOrgTeamDao, CcmOrgTeam> {

	//新填userId查询
	@Autowired
	private CcmOrgTeamDao ccmOrgTeamDao;
		
	public CcmOrgTeam get(String id) {
		return super.get(id);
	}
	
	//新填userId查询
	public CcmOrgTeam findUserId(String userId) {
		return ccmOrgTeamDao.findUserId(userId);
	}
	
	public List<CcmOrgTeam> findList(CcmOrgTeam ccmOrgTeam) {
		return super.findList(ccmOrgTeam);
	}
	
	public Page<CcmOrgTeam> findPage(Page<CcmOrgTeam> page, CcmOrgTeam ccmOrgTeam) {
		return super.findPage(page, ccmOrgTeam);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmOrgTeam ccmOrgTeam) {
		super.save(ccmOrgTeam);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmOrgTeam ccmOrgTeam) {
		super.delete(ccmOrgTeam);
	}
	
	/**
	 * 通过部门ID获取用户列表，仅返回用户id和name（树查询用户时用）
	 * 
	 * @param user
	 * @return
	 */
	public List<User> findUserByOfficeId(CcmOrgTeam ccmOrgTeam) {
		List<User> list =new ArrayList<>();
		list = ccmOrgTeamDao.findUserByOfficeId(ccmOrgTeam);
		return list;
	}
	
	public List<String> findOnlineUserId() {
		return ccmOrgTeamDao.findOnlineUserId();
	}
	public List<CcmUserDevicePo> findOnlineUserInfo() {
		return ccmOrgTeamDao.findOnlineUserInfo();
	}
}