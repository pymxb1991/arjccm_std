/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.service.TreeService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.plm.storage.dao.PlmStorageDao;
import com.arjjs.ccm.modules.plm.storage.entity.PlmStorage;
import com.arjjs.ccm.modules.sys.service.SysCodesService;

/**
 * 仓库管理Service
 * @author dongqikai
 * @version 2018-06-27
 */
@Service
@Transactional(readOnly = true)
public class PlmStorageService extends TreeService<PlmStorageDao, PlmStorage> {

	@Autowired
	private SysCodesService sysCodesService;
	
	public PlmStorage get(String id) {
		return super.get(id);
	}
	
	public List<PlmStorage> findList(PlmStorage plmStorage) {
		if (StringUtils.isNotBlank(plmStorage.getParentIds())){
			plmStorage.setParentIds(","+plmStorage.getParentIds()+",");
		}
		return super.findList(plmStorage);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmStorage plmStorage) {
		if (StringUtils.isBlank(plmStorage.getCode())) {
			plmStorage.setCode(sysCodesService.getCode(PlmStorage.class.getName(), 1).get(0));
		}
		super.save(plmStorage);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmStorage plmStorage) {
		super.delete(plmStorage);
	}
	public List<PlmStorage> countStorageList(){
		
		return dao.countStorageList();
	};
}