/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.email.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.email.dao.PlmEmailBoxDao;
import com.arjjs.ccm.modules.plm.email.entity.PlmEmailBox;

/**
 * 邮箱表Service
 * @author liucong
 * @version 2018-07-24
 */
@Service
@Transactional(readOnly = true)
public class PlmEmailBoxService extends CrudService<PlmEmailBoxDao, PlmEmailBox> {

	public PlmEmailBox get(String id) {
		return super.get(id);
	}
	
	public List<PlmEmailBox> findList(PlmEmailBox plmEmailBox) {
		return super.findList(plmEmailBox);
	}
	
	public Page<PlmEmailBox> findPage(Page<PlmEmailBox> page, PlmEmailBox plmEmailBox) {
		return super.findPage(page, plmEmailBox);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmEmailBox plmEmailBox) {
		super.save(plmEmailBox);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmEmailBox plmEmailBox) {
		super.delete(plmEmailBox);
	}
	
}