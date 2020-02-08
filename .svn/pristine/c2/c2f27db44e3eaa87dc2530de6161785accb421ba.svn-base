/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.message.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.iot.message.entity.CcmMessageManage;
import com.arjjs.ccm.modules.iot.message.dao.CcmMessageManageDao;

/**
 * 消息管理Service
 * @author cby
 * @version 2019-07-24
 */
@Service
@Transactional(readOnly = true)
public class CcmMessageManageService extends CrudService<CcmMessageManageDao, CcmMessageManage> {
	
	@Autowired
	private CcmMessageManageDao ccmMessageManageDao;
	
	public CcmMessageManage get(String id) {
		return super.get(id);
	}
	
	public List<CcmMessageManage> findList(CcmMessageManage ccmMessageManage) {
		return super.findList(ccmMessageManage);
	}
	
	public Page<CcmMessageManage> findPage(Page<CcmMessageManage> page, CcmMessageManage ccmMessageManage) {
		return super.findPage(page, ccmMessageManage);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmMessageManage ccmMessageManage) {
		super.save(ccmMessageManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmMessageManage ccmMessageManage) {
		super.delete(ccmMessageManage);
	}
	
	@Transactional(readOnly = false)
	public void send(CcmMessageManage ccmMessageManage) {
		ccmMessageManageDao.send(ccmMessageManage);
	}
}