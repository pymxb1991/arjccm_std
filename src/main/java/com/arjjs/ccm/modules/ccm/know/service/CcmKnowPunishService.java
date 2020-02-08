/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowPunish;
import com.arjjs.ccm.modules.sys.dao.UserDao;
import com.arjjs.ccm.modules.ccm.know.dao.CcmKnowPunishDao;

/**
 * 城管行政处罚Service
 * @author liang
 * @version 2018-06-02
 */
@Service
@Transactional(readOnly = true)
public class CcmKnowPunishService extends CrudService<CcmKnowPunishDao, CcmKnowPunish> {

	@Autowired
	private CcmKnowPunishDao ccmKnowPunishDao;
	
	
	public CcmKnowPunish get(String id) {
		return super.get(id);
	}
	
	public List<CcmKnowPunish> findList(CcmKnowPunish ccmKnowPunish) {
		return super.findList(ccmKnowPunish);
	}
	
	public Page<CcmKnowPunish> findPage(Page<CcmKnowPunish> page, CcmKnowPunish ccmKnowPunish) {
		return super.findPage(page, ccmKnowPunish);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmKnowPunish ccmKnowPunish) {
		super.save(ccmKnowPunish);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmKnowPunish ccmKnowPunish) {
		super.delete(ccmKnowPunish);
	}

	//行政处罚
	public List<Integer> getType(CcmKnowPunish ccmKnowPunish2) {
		return ccmKnowPunishDao.getType(ccmKnowPunish2);
	}
	
}