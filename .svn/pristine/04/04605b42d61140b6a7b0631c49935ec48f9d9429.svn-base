/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.addressbook.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.service.TreeService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.plm.addressbook.dao.PlmEmployeeGroupsDao;
import com.arjjs.ccm.modules.plm.addressbook.entity.PlmEmployeeGroups;

/**
 * 个人通讯录分组Service
 * @author liucong
 * @version 2018-07-16
 */
@Service
@Transactional(readOnly = true)
public class PlmEmployeeGroupsService extends TreeService<PlmEmployeeGroupsDao, PlmEmployeeGroups> {

	public PlmEmployeeGroups get(String id) {
		return super.get(id);
	}
	
	public List<PlmEmployeeGroups> findList(PlmEmployeeGroups plmEmployeeGroups) {
		if (StringUtils.isNotBlank(plmEmployeeGroups.getParentIds())){
			plmEmployeeGroups.setParentIds(","+plmEmployeeGroups.getParentIds()+",");
		}
		return super.findList(plmEmployeeGroups);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmEmployeeGroups plmEmployeeGroups) {
		super.save(plmEmployeeGroups);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmEmployeeGroups plmEmployeeGroups) {
		super.delete(plmEmployeeGroups);
	}
	
}