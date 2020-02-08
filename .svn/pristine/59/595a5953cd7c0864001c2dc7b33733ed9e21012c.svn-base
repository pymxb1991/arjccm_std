/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlownode;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowsetstat;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowwork;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowworknode;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.arjjs.ccm.modules.pbs.flow.dao.PbsFlownodeDao;
import com.arjjs.ccm.modules.pbs.flow.dao.PbsFlowsetstatDao;
import com.arjjs.ccm.modules.pbs.flow.dao.PbsFlowworkDao;
import com.arjjs.ccm.modules.pbs.flow.dao.PbsFlowworknodeDao;

/**
 * 工作节点记录Service
 * 
 * @author lc
 * @version 2018-04-23
 */
@Service
@Transactional(readOnly = true)
public class PbsFlowworknodeService extends CrudService<PbsFlowworknodeDao, PbsFlowworknode> {

	@Autowired
	private PbsFlowworknodeDao pbsFlowworknodeDao;
	@Autowired
	private PbsFlownodeDao pbsFlownodeDao;
	@Autowired
	private PbsFlowworkDao pbsFlowworkDao;
	@Autowired
	private PbsFlowsetstatDao pbsFlowsetstatDao;
	// 正在运行
	//private static final String flownodeTypeHandle = "0";
	// 完成申请
	private static final String flowTypePASS = "100";
	// 驳回申请
	private static final String flowTypeREFUSE = "-99";
	
	public PbsFlowworknode get(String id) {
		return super.get(id);
	}

	public List<PbsFlowworknode> findList(PbsFlowworknode pbsFlowworknode) {
		return super.findList(pbsFlowworknode);
	}

	public Page<PbsFlowworknode> findPage(Page<PbsFlowworknode> page, PbsFlowworknode pbsFlowworknode) {
		return super.findPage(page, pbsFlowworknode);
	}

	@Transactional(readOnly = false)
	public void save(PbsFlowworknode pbsFlowworknode) {
		super.save(pbsFlowworknode);
	}

	@Transactional(readOnly = false)
	public void delete(PbsFlowworknode pbsFlowworknode) {
		super.delete(pbsFlowworknode);
	}

	// 通过申请 上一个工作流节点记录
	@Transactional(readOnly = false)
	public void PassHandle(PbsFlowworknode pbsFlowworknode) {
		// 更新 当前的
		pbsFlowworknodeDao.updateStatval(pbsFlowworknode);
		// 获取当前 的节点信息
		PbsFlownode pbsFlownodeCurrent = pbsFlownodeDao.get(pbsFlowworknode.getsNodeid());
		// 获取 下一个节点
		PbsFlownode pbsFlownodeNext = pbsFlownodeDao.get(pbsFlownodeCurrent.getsNextnodeid());
		// 创建 下一个节点
		PbsFlowworknode PbsFlowworknodeNext = new PbsFlowworknode();
		PbsFlowworknodeNext.setSName(pbsFlowworknode.getsFlowworkid().getSName() + ":" + pbsFlownodeNext.getSName());
		PbsFlowworknodeNext.setsFlowid(pbsFlowworknode.getsFlowid());
		PbsFlowworknodeNext.setsFlowworkid(pbsFlowworknode.getsFlowworkid());
		PbsFlowworknodeNext.setsNodeid(pbsFlownodeCurrent.getsNextnodeid());
		PbsFlowworknodeNext.setSBinddata(pbsFlowworknode.getSBinddata());
		// 下一个节点顺位
		PbsFlowworknodeNext.setsSort(pbsFlownodeNext.getsSort());
		PbsFlowworknodeNext.setSBindkey(pbsFlowworknode.getSBindkey());
		PbsFlowworknodeNext.setsBindmember(UserUtils.getPartymem());
		PbsFlowworknodeNext.setsOperator(UserUtils.getUser());
		// 如果为 99 则是 关闭节点
		if (!("99").equals(pbsFlownodeNext.getsNodetype())) {
			PbsFlowworknodeNext.setSSetstatval("0");
			save(PbsFlowworknodeNext);
		} else { // 创建结束节点 默认已经完成
			PbsFlowworknodeNext.setSSetstatval("1");
			save(PbsFlowworknodeNext);
			// 变更 工作任务为已经完成
			PbsFlowwork pbsFlowworkFinish = pbsFlowworknode.getsFlowworkid();
			pbsFlowworkFinish.setsBindstat(flowTypePASS);
			pbsFlowworkFinish.setDtEnddate(new Date());
			pbsFlowworkDao.updateSbindstat(pbsFlowworknode.getsFlowworkid());
		}
		PbsFlowsetstat pbsFlowsetstat = new PbsFlowsetstat();
		pbsFlowsetstat.setsFlownodeid(pbsFlownodeCurrent);
		// 流程操作之后的行为
		List<PbsFlowsetstat> flowsetstats = pbsFlowsetstatDao.findList(pbsFlowsetstat);
		// 数据操作
		if (StringUtils.isNotBlank(pbsFlowworknode.getsFlowworkid().getSBindkey())) {
			for (PbsFlowsetstat flowsetstat : flowsetstats) {
				flowsetstat.setKey(pbsFlowworknode.getsFlowworkid().getSBindkey());
				pbsFlowsetstatDao.updateOperation(flowsetstat);
			}
		}
	}

	// 拒绝申请 上一个工作流节点记录
	@Transactional(readOnly = false)
	public void RefuseHandle(PbsFlowworknode pbsFlowworknode) {
		// 更新
		pbsFlowworknodeDao.updateStatval(pbsFlowworknode);
		// 获取 结束节点 创建结束
		PbsFlownode pbsFlownodeDto = new PbsFlownode();
		pbsFlownodeDto.setsFlowid(pbsFlowworknode.getsFlowid());
		pbsFlownodeDto.setsNodetype("99");
		List<PbsFlownode> pbsFlownodeList = pbsFlownodeDao.findList(pbsFlownodeDto);
		if (pbsFlownodeList.size() > 0) {
			PbsFlownode pbsFlownodeEnd = pbsFlownodeList.get(0);
			PbsFlowworknode PbsFlowworknodeNext = new PbsFlowworknode();
			PbsFlowworknodeNext.setSName(pbsFlowworknode.getsFlowworkid().getSName() + ":结束节点");
			PbsFlowworknodeNext.setsFlowid(pbsFlowworknode.getsFlowid());
			PbsFlowworknodeNext.setsNodeid(pbsFlownodeEnd);
			PbsFlowworknodeNext.setsFlowworkid(pbsFlowworknode.getsFlowworkid());
			// 结束节点
			PbsFlowworknodeNext.setsSort(pbsFlownodeEnd.getsSort());
			PbsFlowworknodeNext.setSBinddata(pbsFlowworknode.getSBinddata());
			PbsFlowworknodeNext.setSBindkey(pbsFlowworknode.getSBindkey());
			// 当前用户 的信息
			PbsFlowworknodeNext.setsBindmember(UserUtils.getPartymem());
			PbsFlowworknodeNext.setsOperator(UserUtils.getUser());
			PbsFlowworknodeNext.setSSetstatval("1");
			save(PbsFlowworknodeNext);
		}
		// 变更 工作任务为已经完成
		PbsFlowwork pbsFlowworkFinish = pbsFlowworknode.getsFlowworkid();
		pbsFlowworkFinish.setsBindstat(flowTypeREFUSE);
		pbsFlowworkFinish.setDtEnddate(new Date());
		pbsFlowworkDao.updateSbindstat(pbsFlowworknode.getsFlowworkid());
	}
	
	@Transactional(readOnly = false)
	public void deleteByflowworkid(PbsFlowworknode pbsFlowworknode)	{
		dao.deleteByflowworkid(pbsFlowworknode);
	}

}