/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.addressbook.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.addressbook.dao.PlmEmployeeDao;
import com.arjjs.ccm.modules.plm.addressbook.entity.PlmEmployee;

/**
 * 通讯录Service
 * @author liucong
 * @version 2018-07-14
 */
@Service
@Transactional(readOnly = true)
public class PlmEmployeeService extends CrudService<PlmEmployeeDao, PlmEmployee> {

	public PlmEmployee get(String id) {
		return super.get(id);
	}
	
	public List<PlmEmployee> findList(PlmEmployee plmEmployee) {
		return super.findList(plmEmployee);
	}
	
	public Page<PlmEmployee> findPage(Page<PlmEmployee> page, PlmEmployee plmEmployee) {
		return super.findPage(page, plmEmployee);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmEmployee plmEmployee) {
		super.save(plmEmployee);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmEmployee plmEmployee) {
		super.delete(plmEmployee);
	}
	
	@Transactional(readOnly = false)
	public List<PlmEmployee> findView(PlmEmployee plmEmployee){
		return dao.findView(plmEmployee);
	}
}