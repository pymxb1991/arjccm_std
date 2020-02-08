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
import com.arjjs.ccm.modules.plm.car.dao.apply.PlmCarApplyUseDao;
import com.arjjs.ccm.modules.plm.car.entity.apply.PlmCarApplyUse;
import com.arjjs.ccm.modules.sys.service.SysCodesService;
import com.arjjs.ccm.tool.PlmTypes;
import com.arjjs.ccm.tool.Select2Type;
import com.google.common.collect.Maps;

/**
 * 用车申请Service
 * @author fu
 * @version 2018-07-06
 */
@Service
@Transactional(readOnly = true)
public class PlmCarApplyUseService extends CrudService<PlmCarApplyUseDao, PlmCarApplyUse> {
	
	@Autowired
	private ActTaskService actTaskService;
	@Autowired	
	private PlmActService plmActService;
	@Autowired
	private ActUtConfigService<PlmCarApplyUse> actUtConfigService;	
	@Autowired
	private SysCodesService sysCodesService;
	
	public PlmCarApplyUse get(String id) {
		return super.get(id);
	}
	
	public List<PlmCarApplyUse> findList(PlmCarApplyUse plmCarApplyUse) {
		return super.findList(plmCarApplyUse);
	}
	
	public Page<PlmCarApplyUse> findPage(Page<PlmCarApplyUse> page, PlmCarApplyUse plmCarApplyUse) {
		return super.findPage(page, plmCarApplyUse);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmCarApplyUse plmCarApplyUse) {
		if (StringUtils.isBlank(plmCarApplyUse.getTitle())) {
			plmCarApplyUse.setTitle(sysCodesService.getCode(PlmCarApplyUse.class.getName(), 1).get(0));
		}
		super.save(plmCarApplyUse);
		//parameter ： 1、业务流程主表  2、流程配置id 3、业务表
		plmActService.save(plmCarApplyUse.getPlmAct(),PlmTypes.CAR_APPLY_USE_ID,plmCarApplyUse.getId());
	}
	@Transactional(readOnly = false)
	public void apply(PlmCarApplyUse plmCarApplyUse) {
		//1、保存或修改业务表
		if (StringUtils.isBlank(plmCarApplyUse.getTitle())) {
			plmCarApplyUse.setTitle(sysCodesService.getCode(PlmCarApplyUse.class.getName(), 1).get(0));
		}
		super.save(plmCarApplyUse);
		if (StringUtils.isBlank(plmCarApplyUse.getProcInsId())) {
			//2、开启流程，获取流程实例ID和title
			Map<String, Object> vars = Maps.newHashMap();
			//vars.put("officeLeader", "zzzz");	//可以通过该方式给流程传递变量
			Map<String, String> returnMap = actUtConfigService.getProcInsId(PlmTypes.CAR_APPLY_USE_ID, plmCarApplyUse, plmCarApplyUse.getId(),vars);
			//3、更新业务表流程实例ID
			plmCarApplyUse.setProcInsId(returnMap.get("procInsId"));
			dao.updateProcInsId(plmCarApplyUse);
			//4、保存业务流程主表
			plmCarApplyUse.getPlmAct().setTitle(returnMap.get("title"));
			plmActService.save(plmCarApplyUse.getPlmAct(),PlmTypes.CAR_APPLY_USE_ID,plmCarApplyUse.getId(),plmCarApplyUse.getProcInsId());
		} else {
			plmCarApplyUse.getAct().setComment(("yes".equals(plmCarApplyUse.getAct().getFlag())?"[重申] ":"[撤销] ")
					+ plmCarApplyUse.getAct().getComment());
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("pass", "yes".equals(plmCarApplyUse.getAct().getFlag())? "1" : "0");
			actTaskService.complete(plmCarApplyUse.getAct().getTaskId(), plmCarApplyUse.getAct().getProcInsId(),
					plmCarApplyUse.getAct().getComment(), plmCarApplyUse.getPlmAct().getTitle(), vars);
			//如果销毁，将业务流程主表状态置位“已销毁”
			if(!"yes".equals(plmCarApplyUse.getAct().getFlag())){
				plmActService.updateStatusToDestory(plmCarApplyUse.getPlmAct());
			}
		}
	}	

	@Transactional(readOnly = false)
	public void auditSave(PlmCarApplyUse plmCarApplyUse) {
		
		// 设置意见
		plmCarApplyUse.getAct().setComment(("yes".equals(plmCarApplyUse.getAct().getFlag())?"[同意] ":"[驳回] ") + plmCarApplyUse.getAct().getComment());
		
		plmCarApplyUse.preUpdate();
		
		// 对不同环节的业务逻辑进行操作
		String taskDefKey = plmCarApplyUse.getAct().getTaskDefKey();
		
		//针对所有具有添加督办的环节，若为isSup字段不为空，添加督办信息
		if(StringUtils.isNotBlank(plmCarApplyUse.getPlmAct().getIsSup())){
			plmActService.updateSup(plmCarApplyUse.getPlmAct());
		}
		// 审核环节
		if("auditEnd".equals(taskDefKey)){
			//若为最后一步审核，通过，将业务流程主表状态置位“已结束”
			if("yes".equals(plmCarApplyUse.getAct().getFlag())){
				plmActService.updateStatusToEnd(plmCarApplyUse.getPlmAct());
			}
		}
		// 未知环节，直接返回
		/*else{
			return;
		}*/
		
		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(plmCarApplyUse.getAct().getFlag())? "1" : "0");
		actTaskService.complete(plmCarApplyUse.getAct().getTaskId(), plmCarApplyUse.getAct().getProcInsId(), plmCarApplyUse.getAct().getComment(),plmCarApplyUse.getPlmAct().getTitle(), vars);

	}
		
	@Transactional(readOnly = false)
	public void delete(PlmCarApplyUse plmCarApplyUse) {
		super.delete(plmCarApplyUse);
	}

	public List<Select2Type> findSelect2Type() {
		return dao.findSelect2Type() ;
	}
	
}