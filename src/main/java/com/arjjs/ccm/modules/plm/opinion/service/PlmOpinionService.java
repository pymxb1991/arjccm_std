/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.opinion.service;

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
import com.arjjs.ccm.modules.plm.equapply.entity.PlmEquApply;
import com.arjjs.ccm.modules.plm.opinion.dao.PlmOpinionDao;
import com.arjjs.ccm.modules.plm.opinion.entity.PlmOpinion;
import com.arjjs.ccm.tool.PlmTypes;
import com.google.common.collect.Maps;

/**
 * 建议意见箱Service
 * @author liucong
 * @version 2018-07-30
 */
@Service
@Transactional(readOnly = true)
public class PlmOpinionService extends CrudService<PlmOpinionDao, PlmOpinion> {

	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private PlmActService plmActService;
	@Autowired
	private ActUtConfigService<PlmOpinion> actUtConfigService;
	
	public PlmEquApply getByProcInsId(String procInsId) {
		return dao.getByProcInsId(procInsId);
	}
	
	public PlmOpinion get(String id) {
		return super.get(id);
	}
	
	public List<PlmOpinion> findList(PlmOpinion plmOpinion) {
		return super.findList(plmOpinion);
	}
	
	public Page<PlmOpinion> findPage(Page<PlmOpinion> page, PlmOpinion plmOpinion) {
		return super.findPage(page, plmOpinion);
	}
	
	/**
	 * 保存不提交申請
	 * 
	 * @param plmOpinion
	 */
	@Transactional(readOnly = false)
	public void notCommit(PlmOpinion plmOpinion) {
		super.save(plmOpinion);
		// parameter ： 1、业务流程主表 2、流程配置id 3、业务表
		plmActService.save(plmOpinion.getPlmAct(), PlmTypes.PLM_OPINION, plmOpinion.getId());
	}
	/**
	 * 流程提交
	 */
	@Transactional(readOnly = false)
	public void save(PlmOpinion plmOpinion) {
		super.save(plmOpinion);
		if (StringUtils.isBlank(plmOpinion.getProcInsId())) {
			// 启动流程 参数：1、流程表示 2、表名称 3、表中id 4、我的任务中首列显示内容
			Map<String, String> returnMap = actUtConfigService.getProcInsId(PlmTypes.PLM_OPINION, plmOpinion,
					plmOpinion.getId());
			plmOpinion.setProcInsId(returnMap.get("procInsId"));
			dao.updateProcInsId(plmOpinion);
			// 保存业务流程主表
			plmOpinion.getPlmAct().setTitle(returnMap.get("title"));
			plmActService.save(plmOpinion.getPlmAct(), PlmTypes.PLM_OPINION, plmOpinion.getId(),
					plmOpinion.getProcInsId());
		} else {
			plmOpinion.getAct().setComment(("yes".equals(plmOpinion.getAct().getFlag()) ? "[重申] " : "[撤销] ")
					+ plmOpinion.getAct().getComment());
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("pass", "yes".equals(plmOpinion.getAct().getFlag()) ? "1" : "0");
			actTaskService.complete(plmOpinion.getAct().getTaskId(), plmOpinion.getAct().getProcInsId(),
					plmOpinion.getAct().getComment(), plmOpinion.getPlmAct().getTitle(), vars);
			// 如果销毁，将业务流程主表状态置位“已销毁”
			if (!"yes".equals(plmOpinion.getAct().getFlag())) {
				plmActService.updateStatusToDestory(plmOpinion.getPlmAct());
			}
		}
		super.save(plmOpinion);
	}
	
	@Transactional(readOnly = false)
	public void auditSave(PlmOpinion plmOpinion) {
		// 设置意见
		plmOpinion.getAct().setComment(
				("yes".equals(plmOpinion.getAct().getFlag()) ? "[同意] " : "[驳回] ") + plmOpinion.getAct().getComment());
		plmOpinion.preUpdate();
		// 对不同环节的业务逻辑进行操作
		String taskDefKey = plmOpinion.getAct().getTaskDefKey();
		// 审核环节
				// 最后一步流程且   需要审核
				if ("auditEnd".equals(taskDefKey)) {							
					// 若为最后一步审核，通过，将业务流程主表状态置位“已结束”
					if ("yes".equals(plmOpinion.getAct().getFlag())) {
						plmActService.updateStatusToEnd(plmOpinion.getPlmAct());      
					}
				}
				// 最后一步流程  不需要审核
				else if ("processEnd".equals(taskDefKey)) {				
					// 将业务流程主表状态置位“已结束”			
						plmActService.updateStatusToEnd(plmOpinion.getPlmAct());      
					
				}
				// 未知环节，直接返回
				else if (StringUtils.isBlank(taskDefKey)) {
					return;
				}
				// 针对所有具有添加督办的环节，若为isSup字段不为空，添加督办信息
				if (StringUtils.isNotBlank(plmOpinion.getPlmAct().getIsSup())) {
					plmActService.updateSup(plmOpinion.getPlmAct());
				 }
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(plmOpinion.getAct().getFlag()) ? "1" : "0");
		actTaskService.complete(plmOpinion.getAct().getTaskId(), plmOpinion.getAct().getProcInsId(),
				plmOpinion.getAct().getComment(), vars);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmOpinion plmOpinion) {
		super.delete(plmOpinion);
	}
	
}