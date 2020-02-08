/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.email.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.email.dao.PlmEmailServerDao;
import com.arjjs.ccm.modules.plm.email.entity.PlmEmailServer;

/**
 * 邮箱配置Service
 * @author liucong
 * @version 2018-07-24
 */
@Service
@Transactional(readOnly = true)
public class PlmEmailServerService extends CrudService<PlmEmailServerDao, PlmEmailServer> {

	public PlmEmailServer get(String id) {
		return super.get(id);
	}
	
	public List<PlmEmailServer> findList(PlmEmailServer plmEmailServer) {
		return super.findList(plmEmailServer);
	}
	
	public Page<PlmEmailServer> findPage(Page<PlmEmailServer> page, PlmEmailServer plmEmailServer) {
		return super.findPage(page, plmEmailServer);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmEmailServer plmEmailServer) {
		super.save(plmEmailServer);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmEmailServer plmEmailServer) {
		super.delete(plmEmailServer);
	}
	
}