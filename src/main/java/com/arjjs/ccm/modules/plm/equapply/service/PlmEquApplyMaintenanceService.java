/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.equapply.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.act.service.ActTaskService;
import com.arjjs.ccm.modules.act.service.ActUtConfigService;
import com.arjjs.ccm.modules.plm.act.service.PlmActService;
import com.arjjs.ccm.modules.plm.equapply.dao.PlmEquApplyDao;
import com.arjjs.ccm.modules.plm.equapply.entity.PlmEquApply;
import com.arjjs.ccm.tool.PlmTypes;
import com.google.common.collect.Maps;

/**
 * 物资申请Service
 * 
 * @author liucong
 * @version 2018-06-30
 */
@Service
@Transactional(readOnly = true)
public class PlmEquApplyMaintenanceService extends CrudService<PlmEquApplyDao, PlmEquApply> {

	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private PlmActService plmActService;
	@Autowired
	private ActUtConfigService<PlmEquApply> actUtConfigService;

	public PlmEquApply getByProcInsId(String procInsId) {
		return dao.getByProcInsId(procInsId);
	}

	public PlmEquApply get(String id) {
		return super.get(id);
	}

	public List<PlmEquApply> findList(PlmEquApply plmEquApply) {
		return super.findList(plmEquApply);
	}

	public Page<PlmEquApply> findPage(Page<PlmEquApply> page, PlmEquApply plmEquApply) {
		return super.findPage(page, plmEquApply);
	}

	/**
	 * 保存不提交申請
	 * 
	 * @param plmEquApply
	 */
	@Transactional(readOnly = false)
	public void notCommit(PlmEquApply plmEquApply) {
		super.save(plmEquApply);
		// parameter ： 1、业务流程主表 2、流程配置id 3、业务表
		plmActService.save(plmEquApply.getPlmAct(), PlmTypes.EQU_APPLY_MAINTENANCE, plmEquApply.getId());
	}

	@Transactional(readOnly = false)
	public void save(PlmEquApply plmEquApply) {
		super.save(plmEquApply);
		if (StringUtils.isBlank(plmEquApply.getProcInsId())) {
			// 启动流程 参数：1、流程表示 2、表名称 3、表中id 4、我的任务中首列显示内容
			Map<String, String> returnMap = actUtConfigService.getProcInsId(PlmTypes.EQU_APPLY_MAINTENANCE, plmEquApply,
					plmEquApply.getId());
			plmEquApply.setProcInsId(returnMap.get("procInsId"));
			dao.updateProcInsId(plmEquApply);
			// 保存业务流程主表
			plmEquApply.getPlmAct().setTitle(returnMap.get("title"));
			plmActService.save(plmEquApply.getPlmAct(), PlmTypes.EQU_APPLY_MAINTENANCE, plmEquApply.getId(),
					plmEquApply.getProcInsId());
		} else {
			plmEquApply.getAct().setComment(("yes".equals(plmEquApply.getAct().getFlag()) ? "[重申] " : "[撤销] ")
					+ plmEquApply.getAct().getComment());
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("pass", "yes".equals(plmEquApply.getAct().getFlag()) ? "1" : "0");
			actTaskService.complete(plmEquApply.getAct().getTaskId(), plmEquApply.getAct().getProcInsId(),
					plmEquApply.getAct().getComment(), plmEquApply.getPlmAct().getTitle(), vars);
			// 如果销毁，将业务流程主表状态置位“已销毁”
			if (!"yes".equals(plmEquApply.getAct().getFlag())) {
				plmActService.updateStatusToDestory(plmEquApply.getPlmAct());
			}
		}
		super.save(plmEquApply);
	}

	@Transactional(readOnly = false)
	public void auditSave(PlmEquApply plmEquApply) {
		// 设置意见
		plmEquApply.getAct().setComment(
				("yes".equals(plmEquApply.getAct().getFlag()) ? "[同意] " : "[驳回] ") + plmEquApply.getAct().getComment());
		plmEquApply.preUpdate();
		// 对不同环节的业务逻辑进行操作
		String taskDefKey = plmEquApply.getAct().getTaskDefKey();
		// 审核环节
		
			// 针对所有具有添加督办的环节，若为isSup字段不为空，添加督办信息
			if (StringUtils.isNotBlank(plmEquApply.getPlmAct().getIsSup())) {
				plmActService.updateSup(plmEquApply.getPlmAct());
			}
		if ("auditEnd".equals(taskDefKey)) {
			
			// 若为最后一步审核，通过，将业务流程主表状态置位“已结束”
			if ("yes".equals(plmEquApply.getAct().getFlag())) {
				plmActService.updateStatusToEnd(plmEquApply.getPlmAct());
			}
		} // 未知环节，直接返回
		 // 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(plmEquApply.getAct().getFlag()) ? "1" : "0");
		actTaskService.complete(plmEquApply.getAct().getTaskId(), plmEquApply.getAct().getProcInsId(),
				plmEquApply.getAct().getComment(), vars);

	}

	@Transactional(readOnly = false)
	public void delete(PlmEquApply plmEquApply) {
		super.delete(plmEquApply);
	}

}