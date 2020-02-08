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
import com.arjjs.ccm.modules.plm.travel.dao.PlmTravelManageDao;
import com.arjjs.ccm.modules.plm.travel.entity.PlmTravelManage;
import com.arjjs.ccm.modules.sys.service.SysCodesService;
import com.google.common.collect.Maps;

/**
 * 出差管理Service
 * @author dongqikai
 * @version 2018-07-13
 */
@Service
@Transactional(readOnly = true)
public class PlmTravelManageService extends CrudService<PlmTravelManageDao, PlmTravelManage> {
	
	@Autowired
	private ActTaskService actTaskService;
	
	@Autowired
	private SysCodesService sysCodesService;

	public PlmTravelManage get(String id) {
		return super.get(id);
	}
	
	public List<PlmTravelManage> findList(PlmTravelManage plmTravelManage) {
		return super.findList(plmTravelManage);
	}
	
	public Page<PlmTravelManage> findPage(Page<PlmTravelManage> page, PlmTravelManage plmTravelManage) {
		return super.findPage(page, plmTravelManage);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmTravelManage plmTravelManage) {
		if (StringUtils.isBlank(plmTravelManage.getCode())) {
			plmTravelManage.setCode(sysCodesService.getCode(PlmTravelManage.class.getName(), 1).get(0));
		}
		super.save(plmTravelManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmTravelManage plmTravelManage) {
		super.delete(plmTravelManage);
	}
	
	@Transactional(readOnly = false)
	public void auditSave(PlmTravelManage plmTravelManage) {
		// 设置意见
		plmTravelManage.getAct().setComment(("yes".equals(plmTravelManage.getAct().getFlag())?"[同意] ":"[驳回] ") + plmTravelManage.getAct().getComment());
		plmTravelManage.preUpdate();
		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(plmTravelManage.getAct().getFlag())? "1" : "0");
		actTaskService.complete(plmTravelManage.getAct().getTaskId(), plmTravelManage.getAct().getProcInsId(), plmTravelManage.getAct().getComment(), vars);

	}
	
}