/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.cms.entity.CcmFontUser;
import com.arjjs.ccm.modules.cms.dao.CcmFontUserDao;

/**
 * 居民用户管理Service
 * @author liuxue
 * @version 2018-10-15
 */
@Service
@Transactional(readOnly = true)
public class CcmFontUserService extends CrudService<CcmFontUserDao, CcmFontUser> {

	public CcmFontUser get(String id) {
		return super.get(id);
	}
	
	public List<CcmFontUser> findList(CcmFontUser ccmFontUser) {
		return super.findList(ccmFontUser);
	}
	
	public CcmFontUser getByLoginName(CcmFontUser ccmFontUser){
		return dao.getByLoginName(ccmFontUser);
	};
	
	public Page<CcmFontUser> findPage(Page<CcmFontUser> page, CcmFontUser ccmFontUser) {
		return super.findPage(page, ccmFontUser);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmFontUser ccmFontUser) {
		super.save(ccmFontUser);
	}
	
	@Transactional(readOnly = false)
	public int  insert(CcmFontUser ccmFontUser) {
		return dao.insert(ccmFontUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmFontUser ccmFontUser) {
		super.delete(ccmFontUser);
	}
	
}