/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.equapply.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.equapply.dao.PlmEquApplyDetailDao;
import com.arjjs.ccm.modules.plm.equapply.entity.PlmEquApplyDetail;

/**
 * 申请明细Service
 * @author liu
 * @version 2018-07-12
 */
@Service
@Transactional(readOnly = true)
public class PlmEquApplyDetailService extends CrudService<PlmEquApplyDetailDao, PlmEquApplyDetail> {

	public PlmEquApplyDetail get(String id) {
		return super.get(id);
	}
	
	public List<PlmEquApplyDetail> findList(PlmEquApplyDetail plmEquApplyDetail) {
		return super.findList(plmEquApplyDetail);
	}
	
	public Page<PlmEquApplyDetail> findPage(Page<PlmEquApplyDetail> page, PlmEquApplyDetail plmEquApplyDetail) {
		return super.findPage(page, plmEquApplyDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmEquApplyDetail plmEquApplyDetail) {
		super.save(plmEquApplyDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmEquApplyDetail plmEquApplyDetail) {
		super.delete(plmEquApplyDetail);
	}
	
}