/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.plm.storage.dao.PlmCheckDao;
import com.arjjs.ccm.modules.plm.storage.entity.PlmCheck;
import com.arjjs.ccm.modules.sys.service.SysCodesService;

/**
 * 物资盘点Service
 * @author dongqikai
 * @version 2018-07-10
 */
@Service
@Transactional(readOnly = true)
public class PlmCheckService extends CrudService<PlmCheckDao, PlmCheck> {

	@Autowired
	private SysCodesService sysCodesService;
	
	public PlmCheck get(String id) {
		return super.get(id);
	}
	
	public List<PlmCheck> findList(PlmCheck plmCheck) {
		return super.findList(plmCheck);
	}
	
	public Page<PlmCheck> findPage(Page<PlmCheck> page, PlmCheck plmCheck) {
		return super.findPage(page, plmCheck);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmCheck plmCheck) {
		if (StringUtils.isBlank(plmCheck.getCode())) {
			plmCheck.setCode(sysCodesService.getCode(PlmCheck.class.getName(), 1).get(0));
		}
		super.save(plmCheck);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmCheck plmCheck) {
		super.delete(plmCheck);
	}
	
}