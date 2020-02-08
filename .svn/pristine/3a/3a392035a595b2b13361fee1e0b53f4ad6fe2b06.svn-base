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
import com.arjjs.ccm.modules.plm.car.dao.apply.PlmCarApplyRepairDao;
import com.arjjs.ccm.modules.plm.car.entity.apply.PlmCarApplyRepair;
import com.arjjs.ccm.modules.sys.service.SysCodesService;
import com.arjjs.ccm.tool.PlmTypes;
import com.arjjs.ccm.tool.Select2Type;
import com.google.common.collect.Maps;

/**
 * 维修申请Service
 * @author fu
 * @version 2018-07-07
 */
@Service
@Transactional(readOnly = true)
public class PlmCarApplyRepairService extends CrudService<PlmCarApplyRepairDao, PlmCarApplyRepair> {

	@Autowired
	private ActTaskService actTaskService;
	@Autowired	
	private PlmActService plmActService;
	@Autowired
	private ActUtConfigService<PlmCarApplyRepair> actUtConfigService;
	@Autowired
	private SysCodesService sysCodesService;
	
	public PlmCarApplyRepair get(String id) {
		return super.get(id);
	}
	
	public List<PlmCarApplyRepair> findList(PlmCarApplyRepair plmCarApplyRepair) {
		return super.findList(plmCarApplyRepair);
	}
	
	public Page<PlmCarApplyRepair> findPage(Page<PlmCarApplyRepair> page, PlmCarApplyRepair plmCarApplyRepair) {
		return super.findPage(page, plmCarApplyRepair);
	}
	@Transactional(readOnly = false)
	public void save(PlmCarApplyRepair plmCarApplyRepair) {
		if (StringUtils.isBlank(plmCarApplyRepair.getTitle())) {
			plmCarApplyRepair.setTitle(sysCodesService.getCode(PlmCarApplyRepair.class.getName(), 1).get(0));
		}
		super.save(plmCarApplyRepair);
		//parameter ： 1、业务流程主表  2、流程配置id 3、业务表
		plmActService.save(plmCarApplyRepair.getPlmAct(),PlmTypes.CAR_APPLY_REPAIR_ID,plmCarApplyRepair.getId());
	}
	@Transactional(readOnly = false)
	public void apply(PlmCarApplyRepair plmCarApplyRepair) {
		//1、保存或修改业务表
		if (StringUtils.isBlank(plmCarApplyRepair.getTitle())) {
			plmCarApplyRepair.setTitle(sysCodesService.getCode(PlmCarApplyRepair.class.getName(), 1).get(0));
		}
		super.save(plmCarApplyRepair);
		if (StringUtils.isBlank(plmCarApplyRepair.getProcInsId())) {
			//2、开启流程，获取流程实例ID和title
			Map<String, String> returnMap = actUtConfigService.getProcInsId(PlmTypes.CAR_APPLY_REPAIR_ID, plmCarApplyRepair, plmCarApplyRepair.getId());
			//3、更新业务表流程实例ID
			plmCarApplyRepair.setProcInsId(returnMap.get("procInsId"));
			dao.updateProcInsId(plmCarApplyRepair);
			//4、保存业务流程主表
			plmCarApplyRepair.getPlmAct().setTitle(returnMap.get("title"));
			plmActService.save(plmCarApplyRepair.getPlmAct(),PlmTypes.CAR_APPLY_REPAIR_ID,plmCarApplyRepair.getId(),plmCarApplyRepair.getProcInsId());
		} else {
			plmCarApplyRepair.getAct().setComment(("yes".equals(plmCarApplyRepair.getAct().getFlag())?"[重申] ":"[撤销] ")
					+ plmCarApplyRepair.getAct().getComment());
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("pass", "yes".equals(plmCarApplyRepair.getAct().getFlag())? "1" : "0");
			actTaskService.complete(plmCarApplyRepair.getAct().getTaskId(), plmCarApplyRepair.getAct().getProcInsId(),
					plmCarApplyRepair.getAct().getComment(), plmCarApplyRepair.getPlmAct().getTitle(), vars);
			//如果销毁，将业务流程主表状态置位“已销毁”
			if(!"yes".equals(plmCarApplyRepair.getAct().getFlag())){
				plmActService.updateStatusToDestory(plmCarApplyRepair.getPlmAct());
			}
		}
	}	

	@Transactional(readOnly = false)
	public void auditSave(PlmCarApplyRepair plmCarApplyRepair) {
		
		// 设置意见
		plmCarApplyRepair.getAct().setComment(("yes".equals(plmCarApplyRepair.getAct().getFlag())?"[同意] ":"[驳回] ") + plmCarApplyRepair.getAct().getComment());
		
		plmCarApplyRepair.preUpdate();
		
		// 对不同环节的业务逻辑进行操作
		String taskDefKey = plmCarApplyRepair.getAct().getTaskDefKey();
		
		//针对所有具有添加督办的环节，若为isSup字段不为空，添加督办信息
		if(StringUtils.isNotBlank(plmCarApplyRepair.getPlmAct().getIsSup())){
			plmActService.updateSup(plmCarApplyRepair.getPlmAct());
		}
		// 审核环节
		if ("auditEnd".equals(taskDefKey)){
			
			//若为最后一步审核，通过，将业务流程主表状态置位“已结束”
			if("yes".equals(plmCarApplyRepair.getAct().getFlag())){
				plmActService.updateStatusToEnd(plmCarApplyRepair.getPlmAct());
			}			
		}
		// 未知环节，直接返回
//		else{
//			return;
//		}
		
		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(plmCarApplyRepair.getAct().getFlag())? "1" : "0");
		actTaskService.complete(plmCarApplyRepair.getAct().getTaskId(), plmCarApplyRepair.getAct().getProcInsId(), plmCarApplyRepair.getAct().getComment(),plmCarApplyRepair.getPlmAct().getTitle(), vars);

	}
	
	@Transactional(readOnly = false)
	public void delete(PlmCarApplyRepair plmCarApplyRepair) {
		super.delete(plmCarApplyRepair);
	}

	public List<Select2Type> findSelect2Type() {
		return dao.findSelect2Type();
	}
	/*@Transactional(readOnly = false)
	public void save(PlmCarApplyRepair plmCarApplyRepair) {
		String title = "[车辆维修(保养)申请]-"+plmCarApplyRepair.getUser().getName()+"于"+DateUtils.getDate("yyyy-MM-dd HH:mm")+"发起";
		plmCarApplyRepair.setTitle(title);
		if (StringUtils.isBlank(plmCarApplyRepair.getId())){
			plmCarApplyRepair.preInsert();
			dao.insert(plmCarApplyRepair);
			
			// 启动流程
			String procInsId = actTaskService.startProcess(ActUtils.PD_CAR_APPLY_REPAIR[0], ActUtils.PD_CAR_APPLY_REPAIR[1], plmCarApplyRepair.getId(), title, plmCarApplyRepair.getCurrentUser().getLoginName());
			plmCarApplyRepair.setProcInsId(procInsId);
			dao.updateProcInsId(plmCarApplyRepair);
		}
		
		// 重新编辑申请		
		else{
			if(plmCarApplyRepair.getCurrentUser() == null){
				plmCarApplyRepair.preUpdate();
			}
			plmCarApplyRepair.setProcInsId(plmCarApplyRepair.getAct().getProcInsId());
			dao.update(plmCarApplyRepair);

			plmCarApplyRepair.getAct().setComment(("yes".equals(plmCarApplyRepair.getAct().getFlag())?"[重申] ":"[销毁] ")+plmCarApplyRepair.getAct().getComment());
			
			// 完成流程任务
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("pass", "yes".equals(plmCarApplyRepair.getAct().getFlag())? "1" : "0");
			actTaskService.complete(plmCarApplyRepair.getAct().getTaskId(), plmCarApplyRepair.getAct().getProcInsId(), plmCarApplyRepair.getAct().getComment(), title, vars);
		}
	}*/	
}