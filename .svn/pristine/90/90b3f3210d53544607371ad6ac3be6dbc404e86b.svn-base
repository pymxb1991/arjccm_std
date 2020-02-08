/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.service.apply;

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
import com.arjjs.ccm.modules.plm.car.dao.apply.PlmCarApplyBuyDao;
import com.arjjs.ccm.modules.plm.car.entity.apply.PlmCarApplyBuy;
import com.arjjs.ccm.modules.sys.service.SysCodesService;
import com.arjjs.ccm.tool.PlmTypes;
import com.google.common.collect.Maps;

/**
 * 购车申请Service
 * @author fu
 * @version 2018-07-07
 */
@Service
@Transactional(readOnly = true)
public class PlmCarApplyBuyService extends CrudService<PlmCarApplyBuyDao, PlmCarApplyBuy> {

	@Autowired
	private ActTaskService actTaskService;	
	@Autowired	
	private PlmActService plmActService;
	@Autowired
	private ActUtConfigService<PlmCarApplyBuy> actUtConfigService;
	@Autowired
	private SysCodesService sysCodesService;
	
	public PlmCarApplyBuy get(String id) {
		return super.get(id);
	}
	
	public List<PlmCarApplyBuy> findList(PlmCarApplyBuy plmCarApplyBuy) {
		return super.findList(plmCarApplyBuy);
	}
	
	public Page<PlmCarApplyBuy> findPage(Page<PlmCarApplyBuy> page, PlmCarApplyBuy plmCarApplyBuy) {
		return super.findPage(page, plmCarApplyBuy);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmCarApplyBuy plmCarApplyBuy) {
		if (StringUtils.isBlank(plmCarApplyBuy.getTitle())) {
			plmCarApplyBuy.setTitle(sysCodesService.getCode(PlmCarApplyBuy.class.getName(), 1).get(0));
		}
		super.save(plmCarApplyBuy);
		//parameter ： 1、业务流程主表  2、流程配置id 3、业务表
		plmActService.save(plmCarApplyBuy.getPlmAct(),PlmTypes.CAR_APPLY_BUY_ID,plmCarApplyBuy.getId());
	}
	@Transactional(readOnly = false)
	public void apply(PlmCarApplyBuy plmCarApplyBuy) {
		//1、保存或修改业务表
		if (StringUtils.isBlank(plmCarApplyBuy.getTitle())) {
			plmCarApplyBuy.setTitle(sysCodesService.getCode(PlmCarApplyBuy.class.getName(), 1).get(0));
		}
		super.save(plmCarApplyBuy);
		if (StringUtils.isBlank(plmCarApplyBuy.getProcInsId())) {
			//2、开启流程，获取流程实例ID和title
			Map<String, String> returnMap = actUtConfigService.getProcInsId(PlmTypes.CAR_APPLY_BUY_ID, plmCarApplyBuy, plmCarApplyBuy.getId());
			//3、更新业务表流程实例ID
			plmCarApplyBuy.setProcInsId(returnMap.get("procInsId"));
			dao.updateProcInsId(plmCarApplyBuy);
			//4、保存业务流程主表
			plmCarApplyBuy.getPlmAct().setTitle(returnMap.get("title"));
			plmActService.save(plmCarApplyBuy.getPlmAct(),PlmTypes.CAR_APPLY_BUY_ID,plmCarApplyBuy.getId(),plmCarApplyBuy.getProcInsId());
		} else {
			plmCarApplyBuy.getAct().setComment(("yes".equals(plmCarApplyBuy.getAct().getFlag())?"[重申] ":"[撤销] ")
					+ plmCarApplyBuy.getAct().getComment());
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("pass", "yes".equals(plmCarApplyBuy.getAct().getFlag())? "1" : "0");
			actTaskService.complete(plmCarApplyBuy.getAct().getTaskId(), plmCarApplyBuy.getAct().getProcInsId(),
					plmCarApplyBuy.getAct().getComment(), plmCarApplyBuy.getPlmAct().getTitle(), vars);
			//如果销毁，将业务流程主表状态置位“已销毁”
			if(!"yes".equals(plmCarApplyBuy.getAct().getFlag())){
				plmActService.updateStatusToDestory(plmCarApplyBuy.getPlmAct());
			}
		}
	}	

	@Transactional(readOnly = false)
	public void auditSave(PlmCarApplyBuy plmCarApplyBuy) {
		
		// 设置意见
		plmCarApplyBuy.getAct().setComment(("yes".equals(plmCarApplyBuy.getAct().getFlag())?"[同意] ":"[驳回] ") + plmCarApplyBuy.getAct().getComment());
		
		plmCarApplyBuy.preUpdate();
		// 对不同环节的业务逻辑进行操作
				String taskDefKey = plmCarApplyBuy.getAct().getTaskDefKey();
				
				//针对所有具有添加督办的环节，若为isSup字段不为空，添加督办信息
				if(StringUtils.isNotBlank(plmCarApplyBuy.getPlmAct().getIsSup())){
					plmActService.updateSup(plmCarApplyBuy.getPlmAct());
				}
				// 审核环节
				if("auditEnd".equals(taskDefKey)){
					//若为最后一步审核，通过，将业务流程主表状态置位“已结束”
					if("yes".equals(plmCarApplyBuy.getAct().getFlag())){
						plmActService.updateStatusToEnd(plmCarApplyBuy.getPlmAct());
					}
				}
		
		
		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(plmCarApplyBuy.getAct().getFlag())? "1" : "0");
		actTaskService.complete(plmCarApplyBuy.getAct().getTaskId(), plmCarApplyBuy.getAct().getProcInsId(), plmCarApplyBuy.getAct().getComment(),plmCarApplyBuy.getPlmAct().getTitle(), vars);

	}
	
	@Transactional(readOnly = false)
	public void delete(PlmCarApplyBuy plmCarApplyBuy) {
		super.delete(plmCarApplyBuy);
	}
	
}