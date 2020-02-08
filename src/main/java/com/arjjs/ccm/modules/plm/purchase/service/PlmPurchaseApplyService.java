/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.purchase.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.act.service.ActTaskService;
import com.arjjs.ccm.modules.act.service.ActUtConfigService;
import com.arjjs.ccm.modules.plm.act.service.PlmActService;
import com.arjjs.ccm.modules.plm.purchase.dao.PlmPurchaseApplyDao;
import com.arjjs.ccm.modules.plm.purchase.entity.PlmPurchaseApply;
import com.arjjs.ccm.modules.sys.service.SysCodesService;
import com.arjjs.ccm.tool.PlmTypes;
import com.google.common.collect.Maps;

/**
 * 采购计划申请Service
 * @author liuxue
 * @version 2018-07-07
 */
@Service
@Transactional(readOnly = true)
public class PlmPurchaseApplyService extends CrudService<PlmPurchaseApplyDao, PlmPurchaseApply> {
	@Autowired
	private ActTaskService actTaskService;
	
	@Autowired	
	private PlmActService plmActService;
	
	@Autowired
	private SysCodesService sysCodesService;
	@Autowired
	private ActUtConfigService<PlmPurchaseApply> actUtConfigService;	
	
	public PlmPurchaseApply getByProcInsId(String procInsId) {
		return dao.getByProcInsId(procInsId);
	}

	public PlmPurchaseApply get(String id) {
		return super.get(id);
	}
	
	public List<PlmPurchaseApply> findList(PlmPurchaseApply plmPurchaseApply) {
		return super.findList(plmPurchaseApply);
	}
	
	public Page<PlmPurchaseApply> findPage(Page<PlmPurchaseApply> page, PlmPurchaseApply plmPurchaseApply) {
		return super.findPage(page, plmPurchaseApply);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmPurchaseApply plmPurchaseApply) {
		if (StringUtils.isBlank(plmPurchaseApply.getApplyId())) {
			plmPurchaseApply.setApplyId(sysCodesService.getCode(PlmPurchaseApply.class.getName(), 1).get(0));
		}
		
		super.save(plmPurchaseApply);						
		if (StringUtils.isBlank(plmPurchaseApply.getProcInsId())){
			
			// 启动流程 参数：1、流程表示 2、表名称 3、表中id 4、我的任务中首列显示内容
			Map<String, String> returnMap = actUtConfigService.getProcInsId(PlmTypes.PURCHASE_APPLY_ID, plmPurchaseApply,
					plmPurchaseApply.getId());
			plmPurchaseApply.setProcInsId(returnMap.get("procInsId"));
			dao.updateProcInsId(plmPurchaseApply);
			
			// 保存业务流程主表
			plmPurchaseApply.getPlmAct().setTitle(returnMap.get("title"));
			plmActService.save(plmPurchaseApply.getPlmAct(), PlmTypes.PURCHASE_APPLY_ID, plmPurchaseApply.getId(),
					plmPurchaseApply.getProcInsId());
		}
		
		// 重新编辑申请		
		else{
			
			plmPurchaseApply.getAct().setComment(("yes".equals(plmPurchaseApply.getAct().getFlag())?"[重申] ":"[撤销] ")
					+ plmPurchaseApply.getAct().getComment());
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("pass", "yes".equals(plmPurchaseApply.getAct().getFlag())? "1" : "0");
			actTaskService.complete(plmPurchaseApply.getAct().getTaskId(), plmPurchaseApply.getAct().getProcInsId(),
					plmPurchaseApply.getAct().getComment(), plmPurchaseApply.getPlmAct().getTitle(), vars);
			//如果销毁，将业务流程主表状态置位“已销毁”
			if(!"yes".equals(plmPurchaseApply.getAct().getFlag())){
				plmActService.updateStatusToDestory(plmPurchaseApply.getPlmAct());
			}
		}
	}
	/**
	 * 保存 不提交（不进流程）
	 * @param plmPurchaseApply
	 */
	@Transactional(readOnly = false)
	public void saveUnSubmit(PlmPurchaseApply plmPurchaseApply) {
		if (StringUtils.isBlank(plmPurchaseApply.getApplyId())) {
			plmPurchaseApply.setApplyId(sysCodesService.getCode(PlmPurchaseApply.class.getName(), 1).get(0));
		}
		super.save(plmPurchaseApply);
		//parameter ： 1、业务流程主表  2、流程配置id 3、业务表
		plmActService.save(plmPurchaseApply.getPlmAct(),PlmTypes.PURCHASE_APPLY_ID,plmPurchaseApply.getId());
		
	}
	
	@Transactional(readOnly = false)
	public void auditSave(PlmPurchaseApply plmPurchaseApply) {
		
		// 设置意见
					plmPurchaseApply.getAct().setComment(("yes".equals(plmPurchaseApply.getAct().getFlag())?"[同意] ":"[驳回] ") + plmPurchaseApply.getAct().getComment());
					
					plmPurchaseApply.preUpdate();
					
					// 对不同环节的业务逻辑进行操作
					String taskDefKey = plmPurchaseApply.getAct().getTaskDefKey();

					// 最后一步流程且   需要审核
					if ("auditEnd".equals(taskDefKey)) {							
						// 若为最后一步审核，通过，将业务流程主表状态置位“已结束”
						if ("yes".equals(plmPurchaseApply.getAct().getFlag())) {
							plmActService.updateStatusToEnd(plmPurchaseApply.getPlmAct());      
						}
					}
					// 最后一步流程  不需要审核
					else if ("processEnd".equals(taskDefKey)) {				
						// 将业务流程主表状态置位“已结束”			
							plmActService.updateStatusToEnd(plmPurchaseApply.getPlmAct());      
						
					}
					// 未知环节，直接返回
					else if (StringUtils.isBlank(taskDefKey)) {
						return;
					}
					// 针对所有具有添加督办的环节，若为isSup字段不为空，添加督办信息
					if (StringUtils.isNotBlank(plmPurchaseApply.getPlmAct().getIsSup())) {
						plmActService.updateSup(plmPurchaseApply.getPlmAct());
					 }			
					
					// 提交流程任务
					Map<String, Object> vars = Maps.newHashMap();
					vars.put("pass", "yes".equals(plmPurchaseApply.getAct().getFlag())? "1" : "0");
					actTaskService.complete(plmPurchaseApply.getAct().getTaskId(), plmPurchaseApply.getAct().getProcInsId(), plmPurchaseApply.getAct().getComment(), vars);

				}
	
	
	@Transactional(readOnly = false)
	public void delete(PlmPurchaseApply plmPurchaseApply) {
		super.delete(plmPurchaseApply);
	}
	
	public	List<Map<String, Object>> fundingTypeStatistic(PlmPurchaseApply PlmPurchaseApply) {
		return dao.fundingTypeStatistic(PlmPurchaseApply);
	};
	
}