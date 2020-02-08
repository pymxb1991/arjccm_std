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
import com.arjjs.ccm.modules.plm.car.dao.apply.PlmCarApplyScrapDao;
import com.arjjs.ccm.modules.plm.car.entity.apply.PlmCarApplyScrap;
import com.arjjs.ccm.modules.sys.service.SysCodesService;
import com.arjjs.ccm.tool.PlmTypes;
import com.arjjs.ccm.tool.Select2Type;
import com.google.common.collect.Maps;

/**
 * 报废申请Service
 * @author fu
 * @version 2018-07-07
 */
@Service
@Transactional(readOnly = true)
public class PlmCarApplyScrapService extends CrudService<PlmCarApplyScrapDao, PlmCarApplyScrap> {

	@Autowired
	private ActTaskService actTaskService;
	@Autowired	
	private PlmActService plmActService;
	@Autowired
	private ActUtConfigService<PlmCarApplyScrap> actUtConfigService;	
	@Autowired
	private SysCodesService sysCodesService;
	
	public PlmCarApplyScrap get(String id) {
		return super.get(id);
	}
	
	public List<PlmCarApplyScrap> findList(PlmCarApplyScrap plmCarApplyScrap) {
		return super.findList(plmCarApplyScrap);
	}
	
	public Page<PlmCarApplyScrap> findPage(Page<PlmCarApplyScrap> page, PlmCarApplyScrap plmCarApplyScrap) {
		return super.findPage(page, plmCarApplyScrap);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmCarApplyScrap plmCarApplyScrap) {
		if (StringUtils.isBlank(plmCarApplyScrap.getTitle())) {
			plmCarApplyScrap.setTitle(sysCodesService.getCode(PlmCarApplyScrap.class.getName(), 1).get(0));
		}
		super.save(plmCarApplyScrap);
		//parameter ： 1、业务流程主表  2、流程配置id 3、业务表
		plmActService.save(plmCarApplyScrap.getPlmAct(),PlmTypes.CAR_APPLY_SCRAP_ID,plmCarApplyScrap.getId());
	}
	@Transactional(readOnly = false)
	public void apply(PlmCarApplyScrap plmCarApplyScrap) {
		//1、保存或修改业务表
		if (StringUtils.isBlank(plmCarApplyScrap.getTitle())) {
			plmCarApplyScrap.setTitle(sysCodesService.getCode(PlmCarApplyScrap.class.getName(), 1).get(0));
		}
		super.save(plmCarApplyScrap);
		if (StringUtils.isBlank(plmCarApplyScrap.getProcInsId())) {
			//2、开启流程，获取流程实例ID和title
			Map<String, String> returnMap = actUtConfigService.getProcInsId(PlmTypes.CAR_APPLY_SCRAP_ID, plmCarApplyScrap, plmCarApplyScrap.getId());
			//3、更新业务表流程实例ID
			plmCarApplyScrap.setProcInsId(returnMap.get("procInsId"));
			dao.updateProcInsId(plmCarApplyScrap);
			//4、保存业务流程主表
			plmCarApplyScrap.getPlmAct().setTitle(returnMap.get("title"));
			plmActService.save(plmCarApplyScrap.getPlmAct(),PlmTypes.CAR_APPLY_SCRAP_ID,plmCarApplyScrap.getId(),plmCarApplyScrap.getProcInsId());
		} else {
			plmCarApplyScrap.getAct().setComment(("yes".equals(plmCarApplyScrap.getAct().getFlag())?"[重申] ":"[撤销] ")
					+ plmCarApplyScrap.getAct().getComment());
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("pass", "yes".equals(plmCarApplyScrap.getAct().getFlag())? "1" : "0");
			actTaskService.complete(plmCarApplyScrap.getAct().getTaskId(), plmCarApplyScrap.getAct().getProcInsId(),
					plmCarApplyScrap.getAct().getComment(), plmCarApplyScrap.getPlmAct().getTitle(), vars);
			//如果销毁，将业务流程主表状态置位“已销毁”
			if(!"yes".equals(plmCarApplyScrap.getAct().getFlag())){
				plmActService.updateStatusToDestory(plmCarApplyScrap.getPlmAct());
			}
		}
	}	

	@Transactional(readOnly = false)
	public void auditSave(PlmCarApplyScrap plmCarApplyScrap) {
		
		// 设置意见
		plmCarApplyScrap.getAct().setComment(("yes".equals(plmCarApplyScrap.getAct().getFlag())?"[同意] ":"[驳回] ") + plmCarApplyScrap.getAct().getComment());
		
		plmCarApplyScrap.preUpdate();
		
		// 对不同环节的业务逻辑进行操作
		String taskDefKey = plmCarApplyScrap.getAct().getTaskDefKey();
		
		//针对所有具有添加督办的环节，若为isSup字段不为空，添加督办信息
		if(StringUtils.isNotBlank(plmCarApplyScrap.getPlmAct().getIsSup())){
			plmActService.updateSup(plmCarApplyScrap.getPlmAct());
		}
		// 审核环节
		if("auditEnd".equals(taskDefKey)){
			//若为最后一步审核，通过，将业务流程主表状态置位“已结束”
			if("yes".equals(plmCarApplyScrap.getAct().getFlag())){
				plmActService.updateStatusToEnd(plmCarApplyScrap.getPlmAct());
			}
		}

		
		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(plmCarApplyScrap.getAct().getFlag())? "1" : "0");
		actTaskService.complete(plmCarApplyScrap.getAct().getTaskId(), plmCarApplyScrap.getAct().getProcInsId(), plmCarApplyScrap.getAct().getComment(),plmCarApplyScrap.getPlmAct().getTitle(), vars);

	}
	@Transactional(readOnly = false)
	public void delete(PlmCarApplyScrap plmCarApplyScrap) {
		super.delete(plmCarApplyScrap);
	}

	public List<Select2Type> findSelect2Type() {
		return dao.findSelect2Type();
	}
	
}