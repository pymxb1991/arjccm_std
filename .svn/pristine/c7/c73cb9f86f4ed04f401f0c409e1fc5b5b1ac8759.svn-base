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
import com.arjjs.ccm.modules.plm.travel.dao.PlmOfficialDocumentDao;
import com.arjjs.ccm.modules.plm.travel.entity.PlmBorrowMoney;
import com.arjjs.ccm.modules.plm.travel.entity.PlmOfficialDocument;
import com.arjjs.ccm.modules.sys.service.SysCodesService;
import com.google.common.collect.Maps;

/**
 * 公文管理Service
 * @author dongqikai
 * @version 2018-07-17
 */
@Service
@Transactional(readOnly = true)
public class PlmOfficialDocumentService extends CrudService<PlmOfficialDocumentDao, PlmOfficialDocument> {

	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private SysCodesService sysCodesService;
	
	public PlmOfficialDocument get(String id) {
		return super.get(id);
	}
	
	public List<PlmOfficialDocument> findList(PlmOfficialDocument plmOfficialDocument) {
		return super.findList(plmOfficialDocument);
	}
	
	public Page<PlmOfficialDocument> findPage(Page<PlmOfficialDocument> page, PlmOfficialDocument plmOfficialDocument) {
		return super.findPage(page, plmOfficialDocument);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmOfficialDocument plmOfficialDocument) {
		if (StringUtils.isBlank(plmOfficialDocument.getId())) {
			plmOfficialDocument.setCode(sysCodesService.getCode(PlmBorrowMoney.class.getName(), 1).get(0));
		}
		super.save(plmOfficialDocument);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmOfficialDocument plmOfficialDocument) {
		super.delete(plmOfficialDocument);
	}
	
	@Transactional(readOnly = false)
	public void auditSave(PlmOfficialDocument plmOfficialDocument) {
		// 设置意见
		plmOfficialDocument.getAct().setComment(("yes".equals(plmOfficialDocument.getAct().getFlag())?"[同意] ":"[驳回] ") + plmOfficialDocument.getAct().getComment());
		plmOfficialDocument.preUpdate();
		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(plmOfficialDocument.getAct().getFlag())? "1" : "0");
		actTaskService.complete(plmOfficialDocument.getAct().getTaskId(), plmOfficialDocument.getAct().getProcInsId(), plmOfficialDocument.getAct().getComment(), vars);
	}
	
}