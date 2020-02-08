/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.contract.service;

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
import com.arjjs.ccm.modules.plm.contract.dao.PlmContractSignDao;
import com.arjjs.ccm.modules.plm.contract.entity.PlmContractSign;
import com.arjjs.ccm.modules.sys.service.SysCodesService;
import com.arjjs.ccm.tool.PlmTypes;
import com.google.common.collect.Maps;

/**
 * 采购合同会签Service
 * 
 * @author liuxue
 * @version 2018-07-07
 */
@Service
@Transactional(readOnly = true)
public class PlmContractSignService extends CrudService<PlmContractSignDao, PlmContractSign> {

	@Autowired
	private ActTaskService actTaskService;

	@Autowired
	private SysCodesService sysCodesService;

	@Autowired
	private PlmActService plmActService;

	@Autowired
	private ActUtConfigService<PlmContractSign> actUtConfigService;

	public PlmContractSign get(String id) {
		return super.get(id);
	}

	public List<PlmContractSign> findList(PlmContractSign plmContractSign) {
		return super.findList(plmContractSign);
	}

	public Page<PlmContractSign> findPage(Page<PlmContractSign> page, PlmContractSign plmContractSign) {
		return super.findPage(page, plmContractSign);
	}

	@Transactional(readOnly = false)
	public void save(PlmContractSign plmContractSign) {
		if (StringUtils.isBlank(plmContractSign.getApplyId())) {
			plmContractSign.setApplyId(sysCodesService.getCode(PlmContractSign.class.getName(), 1).get(0));
		}
		super.save(plmContractSign);
		if (StringUtils.isBlank(plmContractSign.getProcInsId())) {

			// 启动流程 参数：1、流程表示 2、表名称 3、表中id 4、我的任务中首列显示内容
			Map<String, String> returnMap = actUtConfigService.getProcInsId(PlmTypes.CONTRACT_SIGN_ID, plmContractSign,
					plmContractSign.getId());
			plmContractSign.setProcInsId(returnMap.get("procInsId"));
			dao.updateProcInsId(plmContractSign);

			// 保存业务流程主表
			plmContractSign.getPlmAct().setTitle(returnMap.get("title"));
			plmActService.save(plmContractSign.getPlmAct(), PlmTypes.CONTRACT_SIGN_ID, plmContractSign.getId(),
					plmContractSign.getProcInsId());
		}

		// 重新编辑申请
		else {

			plmContractSign.getAct().setComment(("yes".equals(plmContractSign.getAct().getFlag()) ? "[重申] " : "[撤销] ")
					+ plmContractSign.getAct().getComment());
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("pass", "yes".equals(plmContractSign.getAct().getFlag()) ? "1" : "0");
			actTaskService.complete(plmContractSign.getAct().getTaskId(), plmContractSign.getAct().getProcInsId(),
					plmContractSign.getAct().getComment(), plmContractSign.getPlmAct().getTitle(), vars);
			// 如果销毁，将业务流程主表状态置位“已销毁”
			if (!"yes".equals(plmContractSign.getAct().getFlag())) {
				plmActService.updateStatusToDestory(plmContractSign.getPlmAct());
			}
		}
	}

	/**
	 * 保存 不提交（不进流程）
	 * 
	 * @param plmContractSign
	 */
	@Transactional(readOnly = false)
	public void saveUnSubmit(PlmContractSign plmContractSign) {
		if (StringUtils.isBlank(plmContractSign.getApplyId())) {
			plmContractSign.setApplyId(sysCodesService.getCode(PlmContractSign.class.getName(), 1).get(0));
		}

		super.save(plmContractSign);
		// parameter ： 1、业务流程主表 2、流程配置id 3、业务表
		plmActService.save(plmContractSign.getPlmAct(), PlmTypes.CONTRACT_SIGN_ID, plmContractSign.getId());
	}

	@Transactional(readOnly = false)
	public void auditSave(PlmContractSign plmContractSign) {

		// 设置意见
		plmContractSign.getAct().setComment(("yes".equals(plmContractSign.getAct().getFlag()) ? "[同意] " : "[驳回] ")
				+ plmContractSign.getAct().getComment());

		plmContractSign.preUpdate();

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = plmContractSign.getAct().getTaskDefKey();
		// 最后一步流程且 需要审核
		if ("auditEnd".equals(taskDefKey)) {
			// 若为最后一步审核，通过，将业务流程主表状态置位“已结束”
			if ("yes".equals(plmContractSign.getAct().getFlag())) {
				plmActService.updateStatusToEnd(plmContractSign.getPlmAct());
			}
		}
		// 最后一步流程 不需要审核
		else if ("processEnd".equals(taskDefKey)) {
			// 将业务流程主表状态置位“已结束”
			plmActService.updateStatusToEnd(plmContractSign.getPlmAct());

		}
		// 未知环节，直接返回
		else if (StringUtils.isBlank(taskDefKey)) {
			return;
		}
		// 针对所有具有添加督办的环节，若为isSup字段不为空，添加督办信息
		if (StringUtils.isNotBlank(plmContractSign.getPlmAct().getIsSup())) {
			plmActService.updateSup(plmContractSign.getPlmAct());
		}

		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(plmContractSign.getAct().getFlag()) ? "1" : "0");
		actTaskService.complete(plmContractSign.getAct().getTaskId(), plmContractSign.getAct().getProcInsId(),
				plmContractSign.getAct().getComment(), vars);

	}

	@Transactional(readOnly = false)
	public void delete(PlmContractSign plmContractSign) {
		super.delete(plmContractSign);
	}

	public List<Map<String, Object>> contractTypeStatistic(PlmContractSign plmContractSign) {
		return dao.contractTypeStatistic(plmContractSign);
	};
}