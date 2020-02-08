/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.travel.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.act.service.ActTaskService;
import com.arjjs.ccm.modules.plm.travel.dao.PlmApplyForReimbursementDao;
import com.arjjs.ccm.modules.plm.travel.entity.PlmApplyForReimbursement;
import com.arjjs.ccm.modules.sys.service.SysCodesService;
import com.google.common.collect.Maps;

/**
 * 报销申请Service
 * @author dongqikai
 * @version 2018-07-16
 */
@Service
@Transactional(readOnly = true)
public class PlmApplyForReimbursementService extends CrudService<PlmApplyForReimbursementDao, PlmApplyForReimbursement> {

	@Autowired
	private ActTaskService actTaskService;
	
	@Autowired
	private SysCodesService sysCodesService;
	
	public PlmApplyForReimbursement get(String id) {
		return super.get(id);
	}
	
	public List<PlmApplyForReimbursement> findList(PlmApplyForReimbursement plmApplyForReimbursement) {
		return super.findList(plmApplyForReimbursement);
	}
	
	public Page<PlmApplyForReimbursement> findPage(Page<PlmApplyForReimbursement> page, PlmApplyForReimbursement plmApplyForReimbursement) {
		return super.findPage(page, plmApplyForReimbursement);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmApplyForReimbursement plmApplyForReimbursement) {
		if (StringUtils.isBlank(plmApplyForReimbursement.getId())) {
			plmApplyForReimbursement.setCode(sysCodesService.getCode(PlmApplyForReimbursement.class.getName(), 1).get(0));
		}
		
		super.save(plmApplyForReimbursement);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmApplyForReimbursement plmApplyForReimbursement) {
		super.delete(plmApplyForReimbursement);
	}
	
	@Transactional(readOnly = false)
	public void auditSave(PlmApplyForReimbursement plmApplyForReimbursement) {
		// 设置意见
		plmApplyForReimbursement.getAct().setComment(("yes".equals(plmApplyForReimbursement.getAct().getFlag())?"[同意] ":"[驳回] ") + plmApplyForReimbursement.getAct().getComment());
		plmApplyForReimbursement.preUpdate();
		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(plmApplyForReimbursement.getAct().getFlag())? "1" : "0");
		actTaskService.complete(plmApplyForReimbursement.getAct().getTaskId(), plmApplyForReimbursement.getAct().getProcInsId(), plmApplyForReimbursement.getAct().getComment(), vars);
	}
	
}