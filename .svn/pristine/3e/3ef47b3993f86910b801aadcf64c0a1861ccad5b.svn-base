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
import com.arjjs.ccm.modules.plm.travel.dao.PlmBorrowMoneyDao;
import com.arjjs.ccm.modules.plm.travel.entity.PlmBorrowMoney;
import com.arjjs.ccm.modules.sys.service.SysCodesService;
import com.google.common.collect.Maps;

/**
 * 借款申请Service
 * @author dongqikai
 * @version 2018-07-16
 */
@Service
@Transactional(readOnly = true)
public class PlmBorrowMoneyService extends CrudService<PlmBorrowMoneyDao, PlmBorrowMoney> {

	@Autowired
	private ActTaskService actTaskService;

	@Autowired
	private SysCodesService sysCodesService;
	
	public PlmBorrowMoney get(String id) {
		return super.get(id);
	}
	
	public List<PlmBorrowMoney> findList(PlmBorrowMoney plmBorrowMoney) {
		return super.findList(plmBorrowMoney);
	}
	
	public Page<PlmBorrowMoney> findPage(Page<PlmBorrowMoney> page, PlmBorrowMoney plmBorrowMoney) {
		return super.findPage(page, plmBorrowMoney);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmBorrowMoney plmBorrowMoney) {
		if (StringUtils.isBlank(plmBorrowMoney.getId())) {
			plmBorrowMoney.setCode(sysCodesService.getCode(PlmBorrowMoney.class.getName(), 1).get(0));
		}
		super.save(plmBorrowMoney);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmBorrowMoney plmBorrowMoney) {
		super.delete(plmBorrowMoney);
	}
	
	@Transactional(readOnly = false)
	public void auditSave(PlmBorrowMoney plmBorrowMoney) {
		// 设置意见
		plmBorrowMoney.getAct().setComment(("yes".equals(plmBorrowMoney.getAct().getFlag())?"[同意] ":"[驳回] ") + plmBorrowMoney.getAct().getComment());
		plmBorrowMoney.preUpdate();
		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(plmBorrowMoney.getAct().getFlag())? "1" : "0");
		actTaskService.complete(plmBorrowMoney.getAct().getTaskId(), plmBorrowMoney.getAct().getProcInsId(), plmBorrowMoney.getAct().getComment(), vars);
	}
	
}