/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.group.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.group.entity.CcmGroupMember;
import com.arjjs.ccm.modules.ccm.group.dao.CcmGroupMemberDao;

/**
 * 群成员管理Service
 * @author liuyongjian
 * @version 2019-08-07
 */
@Service
@Transactional(readOnly = true)
public class CcmGroupMemberService extends CrudService<CcmGroupMemberDao, CcmGroupMember> {

	public CcmGroupMember get(String id) {
		return super.get(id);
	}
	
	public List<CcmGroupMember> findList(CcmGroupMember ccmGroupMember) {
		return super.findList(ccmGroupMember);
	}
	
	public Page<CcmGroupMember> findPage(Page<CcmGroupMember> page, CcmGroupMember ccmGroupMember) {
		return super.findPage(page, ccmGroupMember);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmGroupMember ccmGroupMember) {
		super.save(ccmGroupMember);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmGroupMember ccmGroupMember) {
		super.delete(ccmGroupMember);
	}
	
}