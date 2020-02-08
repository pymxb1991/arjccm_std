/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.storage.dao.PlmProvideInfoDao;
import com.arjjs.ccm.modules.plm.storage.entity.PlmProvideInfo;

/**
 * 供应商Service
 * @author dongqikai
 * @version 2018-06-28
 */
@Service
@Transactional(readOnly = true)
public class PlmProvideInfoService extends CrudService<PlmProvideInfoDao, PlmProvideInfo> {

	public PlmProvideInfo get(String id) {
		return super.get(id);
	}
	
	public List<PlmProvideInfo> findList(PlmProvideInfo plmProvideInfo) {
		return super.findList(plmProvideInfo);
	}
	
	public Page<PlmProvideInfo> findPage(Page<PlmProvideInfo> page, PlmProvideInfo plmProvideInfo) {
		return super.findPage(page, plmProvideInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmProvideInfo plmProvideInfo) {
		super.save(plmProvideInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmProvideInfo plmProvideInfo) {
		super.delete(plmProvideInfo);
	}
	
}