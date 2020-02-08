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
import com.arjjs.ccm.modules.plm.travel.entity.PlmApplicationForLeave;
import com.arjjs.ccm.modules.sys.service.SysCodesService;
import com.google.common.collect.Maps;

/**
 * 请假申请Service
 * @author dongqikai
 * @version 2018-07-16
 */
@Service
@Transactional(readOnly = true)
public class PlmApplicationForLeaveService extends CrudService<PlmApplicationForLeaveDao, PlmApplicationForLeave> {

	@Autowired
	private ActTaskService actTaskService;
	
	@Autowired
	private SysCodesService sysCodesService;
	
	public PlmApplicationForLeave get(String id) {
		return super.get(id);
	}
	
	public List<PlmApplicationForLeave> findList(PlmApplicationForLeave plmApplicationForLeave) {
		return super.findList(plmApplicationForLeave);
	}
	
	public Page<PlmApplicationForLeave> findPage(Page<PlmApplicationForLeave> page, PlmApplicationForLeave plmApplicationForLeave) {
		return super.findPage(page, plmApplicationForLeave);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmApplicationForLeave plmApplicationForLeave) {
		if (StringUtils.isBlank(plmApplicationForLeave.getId())) {
			plmApplicationForLeave.setCode(sysCodesService.getCode(PlmApplicationForLeave.class.getName(), 1).get(0));
		}
		super.save(plmApplicationForLeave);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmApplicationForLeave plmApplicationForLeave) {
		super.delete(plmApplicationForLeave);
	}
	
	@Transactional(readOnly = false)
	public void auditSave(PlmApplicationForLeave plmApplicationForLeave) {
		// 设置意见
		plmApplicationForLeave.getAct().setComment(("yes".equals(plmApplicationForLeave.getAct().getFlag())?"[同意] ":"[驳回] ") + plmApplicationForLeave.getAct().getComment());
		plmApplicationForLeave.preUpdate();
		
		
		
		
		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(plmApplicationForLeave.getAct().getFlag())? "1" : "0");
		actTaskService.complete(plmApplicationForLeave.getAct().getTaskId(), plmApplicationForLeave.getAct().getProcInsId(), plmApplicationForLeave.getAct().getComment(), vars);
	}
	
}