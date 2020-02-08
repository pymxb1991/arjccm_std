/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.logistics.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.logistics.dao.PlmRoomApplyResourceDao;
import com.arjjs.ccm.modules.plm.logistics.entity.PlmRoomApplyResource;
import com.arjjs.ccm.modules.plm.logistics.entity.PlmRoomAttendee;

/**
 * 会议室管理Service
 * @author fu
 * @version 2018-06-26
 */
@Service
@Transactional(readOnly = true)
public class PlmRoomApplyResourceService extends CrudService<PlmRoomApplyResourceDao, PlmRoomApplyResource> {
	
	
	@Transactional(readOnly = false)
	public void save(PlmRoomApplyResource plmRoomApplyResource) {
		super.save(plmRoomApplyResource);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmRoomApplyResource plmRoomApplyResource) {
		super.delete(plmRoomApplyResource);
	}
	
	@Transactional(readOnly = false)
	public List<PlmRoomApplyResource> findList(PlmRoomApplyResource plmRoomApplyResource) {
		return super.findList(plmRoomApplyResource);
	}
	
	public Page<PlmRoomApplyResource> findPage(Page<PlmRoomApplyResource> page, PlmRoomApplyResource plmRoomApplyResource) {
		return super.findPage(page, plmRoomApplyResource);
	}
	
	
}