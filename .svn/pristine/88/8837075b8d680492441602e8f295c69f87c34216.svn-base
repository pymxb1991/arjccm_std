/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseEscape;
import com.arjjs.ccm.modules.ccm.house.dao.CcmHouseEscapeDao;

/**
 * 在逃人员Service
 * 
 * @author 李彩云
 * @version 2018-09-19
 */
@Service
@Transactional(readOnly = true)
public class CcmHouseEscapeService extends CrudService<CcmHouseEscapeDao, CcmHouseEscape> {
	@Autowired
	private CcmHouseEscapeDao ccmHouseEscapeDao;

	public CcmHouseEscape get(String id) {
		return super.get(id);
	}

	// 获取 所有信息根据 id
	public CcmHouseEscape getPeopleALL(String id) {
		return ccmHouseEscapeDao.getItemByPeopleId(id);
	}

	public List<CcmHouseEscape> findList(CcmHouseEscape ccmHouseEscape) {
		return super.findList(ccmHouseEscape);
	}

	public Page<CcmHouseEscape> findPage(Page<CcmHouseEscape> page, CcmHouseEscape ccmHouseEscape) {
		return super.findPage(page, ccmHouseEscape);
	}

	@Transactional(readOnly = false)
	public void save(CcmHouseEscape ccmHouseEscape) {
		super.save(ccmHouseEscape);
	}

	@Transactional(readOnly = false)
	public void delete(CcmHouseEscape ccmHouseEscape) {
		super.delete(ccmHouseEscape);
	}

}