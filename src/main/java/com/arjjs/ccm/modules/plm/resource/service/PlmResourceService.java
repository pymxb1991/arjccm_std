/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.resource.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.resource.dao.PlmResourceDao;
import com.arjjs.ccm.modules.plm.resource.entity.PlmResource;

/**
 * 资源共享Service
 * @author liucong
 * @version 2018-07-20
 */
@Service
@Transactional(readOnly = true)
public class PlmResourceService extends CrudService<PlmResourceDao, PlmResource> {

	public PlmResource get(String id) {
		return super.get(id);
	}
	
	public List<PlmResource> findList(PlmResource plmResource) {
		return super.findList(plmResource);
	}
	
	public Page<PlmResource> findPage(Page<PlmResource> page, PlmResource plmResource) {
		return super.findPage(page, plmResource);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmResource plmResource) {
		super.save(plmResource);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmResource plmResource) {
		super.delete(plmResource);
	}
	
	@Transactional(readOnly = false)
	public List<PlmResource> findListAllById(String uId){
		return dao.findListAllById(uId);
		
	}
	
	@Transactional(readOnly = false)
	public PlmResource findListById(PlmResource plmResource){
		return dao.findListById(plmResource);
	}
	
	@Transactional(readOnly = false)
	public int  updateType(String rId) {
		return dao.updateType(rId);
		
	}
}