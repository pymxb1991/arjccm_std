/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.actionuser.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.flat.actionuser.dao.BphActionUserDao;
import com.arjjs.ccm.modules.flat.actionuser.entity.BphActionUser;

/**
 * 动作执行人员关联表Service
 * @author liu
 * @version 2018-11-15
 */
@Service
@Transactional(readOnly = true)
public class BphActionUserService extends CrudService<BphActionUserDao, BphActionUser> {

	public BphActionUser get(String id) {
		return super.get(id);
	}
	
	public List<BphActionUser> getIdList(BphActionUser bphActionUser) {
		return dao.getIdList(bphActionUser);
	}
	
	public List<BphActionUser> findList(BphActionUser bphActionUser) {
		return super.findList(bphActionUser);
	}
	
	public Page<BphActionUser> findPage(Page<BphActionUser> page, BphActionUser bphActionUser) {
		return super.findPage(page, bphActionUser);
	}
	
	@Transactional(readOnly = false)
	public void save(BphActionUser bphActionUser) {
		super.save(bphActionUser);
	}
	
	@Transactional(readOnly = false)
	public void saveBphActionUser(BphActionUser bphActionUser) {
		super.save(bphActionUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(BphActionUser bphActionUser) {
		super.delete(bphActionUser);
	}
	
	@Transactional(readOnly = false)
	public void deleteBphActionUser(BphActionUser bphActionUser) {
		dao.deleteBphActionUser(bphActionUser);
	}
}