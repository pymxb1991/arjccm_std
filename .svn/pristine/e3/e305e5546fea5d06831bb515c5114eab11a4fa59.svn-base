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
import com.arjjs.ccm.modules.plm.travel.dao.PlmApplicationForLeaveDao;
import com.arjjs.ccm.modules.plm.travel.dao.PlmWorkOvertimeApplyDao;
import com.arjjs.ccm.modules.plm.travel.entity.PlmApplicationForLeave;
import com.arjjs.ccm.modules.plm.travel.entity.PlmWorkOvertimeApply;
import com.arjjs.ccm.modules.sys.service.SysCodesService;
import com.google.common.collect.Maps;

/**
 * 加班申请Service
 * @author dongqikai
 * @version 2018-07-16
 */
@Service
@Transactional(readOnly = true)
public class PlmWorkOvertimeApplyService extends CrudService<PlmWorkOvertimeApplyDao, PlmWorkOvertimeApply> {

	@Autowired
	private ActTaskService actTaskService;
	
	@Autowired
	private SysCodesService sysCodesService;
	
	public PlmWorkOvertimeApply get(String id) {
		return super.get(id);
	}
	
	public List<PlmWorkOvertimeApply> findList(PlmWorkOvertimeApply plmWorkOvertimeApply) {
		return super.findList(plmWorkOvertimeApply);
	}
	
	public Page<PlmWorkOvertimeApply> findPage(Page<PlmWorkOvertimeApply> page, PlmWorkOvertimeApply plmWorkOvertimeApply) {
		return super.findPage(page, plmWorkOvertimeApply);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmWorkOvertimeApply plmWorkOvertimeApply) {
		if (StringUtils.isBlank(plmWorkOvertimeApply.getId())) {
			plmWorkOvertimeApply.setCode(sysCodesService.getCode(PlmWorkOvertimeApply.class.getName(), 1).get(0));
		}
		super.save(plmWorkOvertimeApply);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmWorkOvertimeApply plmWorkOvertimeApply) {
		super.delete(plmWorkOvertimeApply);
	}
	
	@Transactional(readOnly = false)
	public void auditSave(PlmWorkOvertimeApply plmWorkOvertimeApply) {
		// 设置意见
		plmWorkOvertimeApply.getAct().setComment(("yes".equals(plmWorkOvertimeApply.getAct().getFlag())?"[同意] ":"[驳回] ") + plmWorkOvertimeApply.getAct().getComment());
		plmWorkOvertimeApply.preUpdate();
		
		
		
		
		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(plmWorkOvertimeApply.getAct().getFlag())? "1" : "0");
		actTaskService.complete(plmWorkOvertimeApply.getAct().getTaskId(), plmWorkOvertimeApply.getAct().getProcInsId(), plmWorkOvertimeApply.getAct().getComment(), vars);
	}
	
}