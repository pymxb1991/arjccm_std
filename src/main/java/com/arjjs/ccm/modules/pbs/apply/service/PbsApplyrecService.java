/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.apply.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.apply.entity.PbsApplyrec;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowdefinition;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowwork;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlowworkService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.arjjs.ccm.modules.pbs.apply.dao.PbsApplyrecDao;

/**
 * 申请记录Service
 * 
 * @author lc
 * @version 2018-04-25
 */
@Service
@Transactional(readOnly = true)
public class PbsApplyrecService extends CrudService<PbsApplyrecDao, PbsApplyrec> {

	// 创建 工作流
	@Autowired
	private PbsFlowworkService pbsFlowworkService;

	private static final String flowType = "pbs_applyrec";
	// 正在执行
	private static final String flowStatusType1 = "0";

	public PbsApplyrec get(String id) {
		return super.get(id);
	}

	public List<PbsApplyrec> findList(PbsApplyrec pbsApplyrec) {
		return super.findList(pbsApplyrec);
	}

	public Page<PbsApplyrec> findPage(Page<PbsApplyrec> page, PbsApplyrec pbsApplyrec) {
		return super.findPage(page, pbsApplyrec);
	}

	@Transactional(readOnly = false)
	public void save(PbsApplyrec pbsApplyrec) {
		super.save(pbsApplyrec);
	}

	@Transactional(readOnly = false)
	public void delete(PbsApplyrec pbsApplyrec) {
		super.delete(pbsApplyrec);
		// 获取当前 的 工作流 根据 任务的审核内容
		PbsFlowwork pbsFlowworkDto = new PbsFlowwork();
		pbsFlowworkDto.setSBindkey(pbsApplyrec.getId());
		List<PbsFlowwork> pbsFlowworks = pbsFlowworkService.findList(pbsFlowworkDto);
		// 一般就只有一个
		if(pbsFlowworks.size()>0){
			pbsFlowworkService.delete(pbsFlowworks.get(0));
		}
	}

	@Transactional(readOnly = false)
	public void apply(PbsApplyrec pbsApplyrec) {
		// 申请记录 的添加
		pbsApplyrec.setsOperator(UserUtils.getUser());
		pbsApplyrec.setsBindmember(UserUtils.getPartymem());
		pbsApplyrec.setSStatus(flowStatusType1);
		super.save(pbsApplyrec);
		// 开始 工作流 的创建
		PbsFlowwork pbsFlowwork = new PbsFlowwork();
		PbsFlowdefinition pbsFlowdefinition = pbsApplyrec.getsType(); // 流程定义模板
		pbsFlowwork.setsFlowid(pbsFlowdefinition);
		pbsFlowwork.setSName(pbsApplyrec.getSResume());
		pbsFlowwork.setSBinddata(flowType);
		pbsFlowwork.setSBindkey(pbsApplyrec.getId());
		pbsFlowwork.setsBindstat(flowStatusType1);
		pbsFlowwork.setDtStartdate(new Date());
		pbsFlowwork.setsOperator(UserUtils.getUser());
		pbsFlowwork.setsBindmember(UserUtils.getPartymem());
		pbsFlowwork.setSDescription(pbsApplyrec.getSContent());
		pbsFlowworkService.save(pbsFlowwork);

	}
	
	@Transactional(readOnly = false)
	public void updateTypeById(String applyId, String typeId) {
		dao.updateTypeById(applyId,typeId);
	}

}