/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlownode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.arjjs.ccm.modules.pbs.flow.dao.PbsFlownodeDao;

/**
 * 流程节点Service
 * 
 * @author lc
 * @version 2018-04-20
 */
@Service
@Transactional(readOnly = true)
public class PbsFlownodeService extends CrudService<PbsFlownodeDao, PbsFlownode> {

	// 起始节点
	private static final String NODEBEGIN = "0";
	// 最终节点
	private static final String NODEEND = "99";

	public PbsFlownode get(String id) {
		return super.get(id);
	}

	public List<PbsFlownode> findList(PbsFlownode pbsFlownode) {
		return super.findList(pbsFlownode);
	}

	public Page<PbsFlownode> findPage(Page<PbsFlownode> page, PbsFlownode pbsFlownode) {
		return super.findPage(page, pbsFlownode);
	}

	@Transactional(readOnly = false)
	public void save(PbsFlownode pbsFlownodeCurrent) {
		boolean Insertflag = StringUtils.isEmpty(pbsFlownodeCurrent.getId());
		// 判断是否为 起始点或为终止点
		boolean Skipflag = !((NODEEND).equals(pbsFlownodeCurrent.getsNodetype()))
				|| ((NODEBEGIN).equals(pbsFlownodeCurrent.getsNodetype()));
		// 新增 且不为起始点 或终止点
		if (Insertflag && Skipflag) {
			int maxInt = Integer.parseInt(dao.findMaxSort(pbsFlownodeCurrent.getsFlowid().getId()));
			pbsFlownodeCurrent.setsSort((++maxInt) + "");
			// 获取最后的节点
			PbsFlownode pbsFlownodeLastDto = new PbsFlownode();
			pbsFlownodeLastDto.setsNodetype(NODEEND); // 获取终止点
			pbsFlownodeLastDto.setsFlowid(pbsFlownodeCurrent.getsFlowid());
			List<PbsFlownode> pbsFlownodes = dao.findList(pbsFlownodeLastDto);
			// 最后一个节点
			PbsFlownode pbsFlownodeLast = pbsFlownodes.get(0);
			// 倒数第二个节点
			PbsFlownode pbsFlownodePenult = dao.get(pbsFlownodeLast.getsPrevnodeid());
			// 当前节点 链接
			pbsFlownodeCurrent.setsPrevnodeid(pbsFlownodePenult);
			pbsFlownodeCurrent.setsNextnodeid(pbsFlownodeLast);
			// 上一个节点的链接
			pbsFlownodePenult.setsNextnodeid(pbsFlownodeCurrent);
			// 终点个节点的链接
			pbsFlownodeLast.setsPrevnodeid(pbsFlownodeCurrent);
			// 更新 最终节点 与 倒数第二节点
			super.save(pbsFlownodeCurrent);
			super.save(pbsFlownodePenult);
			super.save(pbsFlownodeLast);
		}
		// 更新
		else {
			// 获取 当前节点原 内容
			PbsFlownode pbsFlownodeCurrentOLd = get(pbsFlownodeCurrent.getId());
			if (pbsFlownodeCurrentOLd.getsPrevnodeid() != null && !pbsFlownodeCurrentOLd.getsPrevnodeid().getId().equals(pbsFlownodeCurrent.getsPrevnodeid().getId())) {
				// 原因： 上一节点 发生了变化 1 删除当前节点 链接 2在新的连接上 创造链接
				/**
				 * 1.开始缝合缺失的节点
				 */
				PbsFlownode pbsFlownodepre = dao.get(pbsFlownodeCurrentOLd.getsPrevnodeid());
				PbsFlownode pbsFlownodenext = dao.get(pbsFlownodeCurrentOLd.getsNextnodeid());
				pbsFlownodepre.setsNextnodeid(pbsFlownodenext);
				pbsFlownodenext.setsPrevnodeid(pbsFlownodepre);
				dao.update(pbsFlownodepre);
				dao.update(pbsFlownodenext);
				/**
				 * 2.新增节点的位置
				 */
				PbsFlownode	pbsFlownodeCurrentPre = dao.get(pbsFlownodeCurrent.getsPrevnodeid().getId());
				// 上一个节点 的下一节点
				PbsFlownode	pbsFlownodeCurrentNext = dao.get(pbsFlownodeCurrentPre.getsNextnodeid().getId());
				pbsFlownodeCurrentPre.setsNextnodeid(pbsFlownodeCurrent);
				pbsFlownodeCurrentNext.setsPrevnodeid(pbsFlownodeCurrent);
				dao.update(pbsFlownodeCurrentPre);
				dao.update(pbsFlownodeCurrentNext);
				// 当前点继承 新的上一点 的下一个节点
				pbsFlownodeCurrent.setsNextnodeid(pbsFlownodeCurrentNext);
				super.save(pbsFlownodeCurrent);
				updateNodeSort(pbsFlownodeCurrent);
			}else{
				// 仅仅更新当前的节点
				super.save(pbsFlownodeCurrent);
			}
		}
	}

	@Transactional(readOnly = false)
	public void delete(PbsFlownode pbsFlownodeCurrent) {
		PbsFlownode pbsFlownodepre = dao.get(pbsFlownodeCurrent.getsPrevnodeid());
		PbsFlownode pbsFlownodenext = dao.get(pbsFlownodeCurrent.getsNextnodeid());
		pbsFlownodepre.setsNextnodeid(pbsFlownodenext);
		pbsFlownodenext.setsPrevnodeid(pbsFlownodepre);
		dao.update(pbsFlownodenext);
		dao.update(pbsFlownodepre);
		super.delete(pbsFlownodeCurrent);
		// 删除后进行排序
		updateNodeSort(pbsFlownodeCurrent);
	}

	// 获取当前的 最大值
	public String findnodeMaxSort(String id) {
		return dao.findMaxSort(id);
	}

	// 更新 当前节点的所有
	@Transactional(readOnly = false)
	public void updateNodeSort(PbsFlownode pbsFlownodeCurrent) {
		// 获取 当前的
		PbsFlownode pbsFlownodeDto = new PbsFlownode();
		pbsFlownodeDto.setsFlowid(pbsFlownodeCurrent.getsFlowid());
		// 所有节点 的list
		List<PbsFlownode> pbsFlownodeList = dao.findList(pbsFlownodeDto);
		// 所有的 节点 的Map
		Map<String, PbsFlownode> Mapnode = Maps.newHashMap();
		// 选取第一节点
		PbsFlownode pbsFlownodeBegin = new PbsFlownode();
		// 遍历list 生成 Map
		for (PbsFlownode pbsFlownode : pbsFlownodeList) {
			Mapnode.put(pbsFlownode.getId(), pbsFlownode);
			if (NODEBEGIN.equals(pbsFlownode.getsNodetype())) {
				pbsFlownodeBegin = pbsFlownode;
			}
		}
		// 需要更新的节点
		List<PbsFlownode> pbsFlownodeDaoList = Lists.newArrayList();
		// 当前节点 起始为 开始节点
		PbsFlownode nodeCurrentWhile = pbsFlownodeBegin;
		// 当前节点是否为 最终节点
		int sort = 1;
		do {
			// 获取一个节点
			PbsFlownode nodeCurrent = Mapnode.get(nodeCurrentWhile.getsNextnodeid().getId());
			// 赋值 顺位
			nodeCurrent.setsSort(sort + "");
			sort++;
			if (!NODEEND.equalsIgnoreCase(nodeCurrent.getsNodetype())) {
				System.out.println("当前的节点："+nodeCurrent.getsNodetype());
				pbsFlownodeDaoList.add(nodeCurrent);
			}
			// 游标替换
			nodeCurrentWhile = nodeCurrent;
		} while (!NODEEND.equalsIgnoreCase(nodeCurrentWhile.getsNodetype()));
		if(pbsFlownodeDaoList.size() == 0) {
			return;
		}
		dao.updatesSort(pbsFlownodeDaoList);
	}
}