
/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.equapply.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.equapply.dao.PlmEquEquipmentDao;
import com.arjjs.ccm.modules.plm.equapply.entity.PlmEquEquipment;

/**
 * 维修报废详细表Service
 * @author liu
 * @version 2018-08-31
 */
@Service
@Transactional(readOnly = true)
public class PlmEquEquipmentService extends CrudService<PlmEquEquipmentDao, PlmEquEquipment> {
	public PlmEquEquipment get(String id) {
		return super.get(id);
	}
	
	public List<PlmEquEquipment> findList(PlmEquEquipment plmEquEquipment) {
		return super.findList(plmEquEquipment);
	}
	
	public Page<PlmEquEquipment> findPage(Page<PlmEquEquipment> page, PlmEquEquipment plmEquEquipment) {
		return super.findPage(page, plmEquEquipment);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmEquEquipment plmEquEquipment) {
		super.save(plmEquEquipment);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmEquEquipment plmEquEquipment) {
		super.delete(plmEquEquipment);
	}
	
	public List<PlmEquEquipment> findByApply(String applyId){
		return dao.findByApply(applyId);
	}
}