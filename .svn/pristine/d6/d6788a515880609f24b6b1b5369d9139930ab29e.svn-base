/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.logistics.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.logistics.dao.PlmRoomAttendeeDao;
import com.arjjs.ccm.modules.plm.logistics.entity.PlmRoomAttendee;

/**
 * 参会人员Service
 * @author fu
 * @version 2018-06-27
 */
@Service
@Transactional(readOnly = true)
public class PlmRoomAttendeeService extends CrudService<PlmRoomAttendeeDao, PlmRoomAttendee> {

	public PlmRoomAttendee get(String id) {
		return super.get(id);
	}
	
	public List<PlmRoomAttendee> findList(PlmRoomAttendee plmRoomAttendee) {
		return super.findList(plmRoomAttendee);
	}
	
	public Page<PlmRoomAttendee> findPage(Page<PlmRoomAttendee> page, PlmRoomAttendee plmRoomAttendee) {
		return super.findPage(page, plmRoomAttendee);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmRoomAttendee plmRoomAttendee) {
		super.save(plmRoomAttendee);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmRoomAttendee plmRoomAttendee) {
		super.delete(plmRoomAttendee);
	}
	
}