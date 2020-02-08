/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.logistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.logistics.dao.PlmRoomDao;
import com.arjjs.ccm.modules.plm.logistics.entity.PlmRoom;
import com.arjjs.ccm.tool.Select2Type;

/**
 * 会议室管理Service
 * @author fu
 * @version 2018-06-26
 */
@Service
@Transactional(readOnly = true)
public class PlmRoomService extends CrudService<PlmRoomDao, PlmRoom> {
	
	@Autowired
	private PlmRoomDao plmRoomDao;

	public PlmRoom get(String id) {
		return super.get(id);
	}
	
	public List<PlmRoom> findList(PlmRoom plmRoom) {
		return super.findList(plmRoom);
	}
	
	public Page<PlmRoom> findPage(Page<PlmRoom> page, PlmRoom plmRoom) {
		return super.findPage(page, plmRoom);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmRoom plmRoom) {
		super.save(plmRoom);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmRoom plmRoom) {
		super.delete(plmRoom);
	}

	public List<Select2Type> findSelect2Type(PlmRoom plmRoom) {
		return plmRoomDao.findSelect2Type(plmRoom);
	}
	
}