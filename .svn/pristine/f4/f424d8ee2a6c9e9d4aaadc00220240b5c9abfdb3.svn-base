/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlownode;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowwork;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowworknode;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.modules.pbs.flow.dao.PbsFlowworkDao;

/**
 * 运行工作流Service
 * 
 * @author lc
 * @version 2018-04-23
 */
@Service
@Transactional(readOnly = true)
public class PbsFlowworkService extends CrudService<PbsFlowworkDao, PbsFlowwork> {

	@Autowired
	private PbsFlownodeService pbsFlownodeService;
	// 工作节点记录
	@Autowired
	private PbsFlowworknodeService pbsFlowworknodeService;

	public PbsFlowwork get(String id) {
		return super.get(id);
	}

	public List<PbsFlowwork> findList(PbsFlowwork pbsFlowwork) {
		return super.findList(pbsFlowwork);
	}

	public Page<PbsFlowwork> findPage(Page<PbsFlowwork> page, PbsFlowwork pbsFlowwork) {
		return super.findPage(page, pbsFlowwork);
	}

	@Transactional(readOnly = false)
	public void save(PbsFlowwork pbsFlowwork) {
		boolean flagExist = StringUtils.isBlank(pbsFlowwork.getId());
		super.save(pbsFlowwork);
		// 判断 当前 标记为存在
		if (flagExist) {
			// 如果为新增 的工作流任务则新增 已完成 的工作节点
			PbsFlownode pbsFlownodeDto1 = new PbsFlownode();
			pbsFlownodeDto1.setsFlowid(pbsFlowwork.getsFlowid());
			pbsFlownodeDto1.setsNodetype("0");
			List<PbsFlownode> FlownodeList = pbsFlownodeService.findList(pbsFlownodeDto1);
			if (FlownodeList.size() > 0) {
				// 模板节点
				PbsFlownode PbsFlownodeBegin = FlownodeList.get(0);
				// 开始节点(0) 的添加
				PbsFlowworknode PbsFlowworknodeBegin = new PbsFlowworknode();
				PbsFlowworknodeBegin.setSName(pbsFlowwork.getSName() + ":开始节点");
				PbsFlowworknodeBegin.setsFlowid(pbsFlowwork.getsFlowid());
				PbsFlowworknodeBegin.setsFlowworkid(pbsFlowwork);
				PbsFlowworknodeBegin.setsSort("0");
				PbsFlowworknodeBegin.setsNodeid(PbsFlownodeBegin);
				PbsFlowworknodeBegin.setSBinddata(pbsFlowwork.getSBinddata());
				PbsFlowworknodeBegin.setSBindkey(pbsFlowwork.getSBindkey());
				// 默认已经完成
				PbsFlowworknodeBegin.setSSetstatval("1");
				PbsFlowworknodeBegin.setsBindmember(pbsFlowwork.getsBindmember());
				PbsFlowworknodeBegin.setsOperator(UserUtils.getUser());
				pbsFlowworknodeService.save(PbsFlowworknodeBegin);
				// 创建第一个正式的节点(1)
				PbsFlownode PbsFlownodeSecond = pbsFlownodeService.get(PbsFlownodeBegin.getsNextnodeid().getId());
				PbsFlowworknode FlowWorkNodeSecond = new PbsFlowworknode();
				FlowWorkNodeSecond.setSName(pbsFlowwork.getSName() + ":" + PbsFlownodeSecond.getSName());
				FlowWorkNodeSecond.setsFlowid(pbsFlowwork.getsFlowid());
				FlowWorkNodeSecond.setsNodeid(PbsFlownodeSecond);
				FlowWorkNodeSecond.setsSort(PbsFlownodeSecond.getsSort());
				FlowWorkNodeSecond.setsFlowworkid(pbsFlowwork);
				FlowWorkNodeSecond.setSSetstatval("0");
				FlowWorkNodeSecond.setSBinddata(pbsFlowwork.getSBinddata());
				FlowWorkNodeSecond.setSBindkey(pbsFlowwork.getSBindkey());
				// 为了保证 逻辑统一 第一个正式节点的 操作人 应为 
				// FlowWorkNodeSecond.setsBindmember(pbsFlowwork.getsBindmember());
				// FlowWorkNodeSecond.setsOperator(UserUtils.getUser());
				pbsFlowworknodeService.save(FlowWorkNodeSecond);
			}
		}
	}

	@Transactional(readOnly = false)
	public void delete(PbsFlowwork pbsFlowwork) {
		super.delete(pbsFlowwork);
		// 删除同一个 工作流的 所有节点
		PbsFlowworknode pbsFlowworknode = new PbsFlowworknode();
		pbsFlowworknode.setsFlowworkid(pbsFlowwork);
		pbsFlowworknodeService.deleteByflowworkid(pbsFlowworknode);
	}

	// 查询当前的 需要被审核 的 工作流
	public List<PbsFlowwork> findListByApprover(PbsFlowwork PbsFlowwork) {
		return dao.findListByApprover(PbsFlowwork);
	}

	// 查询当前的 需要被审核 的 工作流
	public Page<PbsFlowwork> findPageByApprover(Page<PbsFlowwork> page, PbsFlowwork pbsFlowwork) {
		pbsFlowwork.setPage(page);
		return page.setList(dao.findListByApprover(pbsFlowwork));
	}

	// 查看已经完成的审核工作流
	public List<PbsFlowwork> findListFinish(PbsFlowwork PbsFlowwork) {
		return dao.findListFinish(PbsFlowwork);
	}

	// 查看已经完成的审核工作流
	public Page<PbsFlowwork> findPageFinish(Page<PbsFlowwork> page, PbsFlowwork pbsFlowwork) {
		pbsFlowwork.setPage(page);
		return page.setList(dao.findListFinish(pbsFlowwork));
	}
}