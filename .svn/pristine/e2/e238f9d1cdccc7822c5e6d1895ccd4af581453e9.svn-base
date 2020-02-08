/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.resource.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.plm.resource.dao.PlmResourceUserDao;
import com.arjjs.ccm.modules.plm.resource.entity.PlmResourceUser;

/**
 * 资源权限Service
 * @author liucong
 * @version 2018-07-20
 */
@Service
@Transactional(readOnly = true)
public class PlmResourceUserService extends CrudService<PlmResourceUserDao, PlmResourceUser> {

	public PlmResourceUser get(String id) {
		return super.get(id);
	}
	
	public List<PlmResourceUser> findList(PlmResourceUser plmResourceUser) {
		if (StringUtils.isNotBlank(plmResourceUser.getParentIds())){
			plmResourceUser.setParentIds(","+plmResourceUser.getParentIds()+",");
		}
		return super.findList(plmResourceUser);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmResourceUser plmResourceUser) {
		super.save(plmResourceUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmResourceUser plmResourceUser) {
		super.delete(plmResourceUser);
	}
	@Transactional(readOnly = false)
	public int findCount(String uId,String rId) {
		return dao.findCount(uId, rId);
	}
	@Transactional(readOnly = false)
	public List<PlmResourceUser> findAllIds(){
		return dao.findAllIds();
	}
	
	@Transactional(readOnly = false)
	public int deleteRId(String rId) {
		return	dao.deleteRId(rId);
	}
}