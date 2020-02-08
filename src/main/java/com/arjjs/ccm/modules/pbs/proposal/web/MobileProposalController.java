/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.proposal.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.proposal.entity.PbsProposal;
import com.arjjs.ccm.modules.pbs.proposal.entity.PbsProposalarea;
import com.arjjs.ccm.modules.pbs.proposal.entity.PbsProposalopt;
import com.arjjs.ccm.modules.pbs.proposal.service.PbsProposalService;
import com.arjjs.ccm.modules.pbs.proposal.service.PbsProposalareaService;
import com.arjjs.ccm.modules.pbs.proposal.service.PbsProposaloptService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

/**
 * 建议信息Controller
 * 
 * @author lc
 * @version 2018-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/proposal/pbsProposal")
public class MobileProposalController extends BaseController {

	@Autowired
	private PbsProposalService pbsProposalService;
	@Autowired
	private PbsProposalareaService pbsProposalareaService;
	@Autowired
	private PbsProposaloptService pbsProposaloptService;

	// 派发给我的 任务列表
	@RequestMapping(value = "proposalList")
	public String proposalList(Model model) {
		// 当前如果没有党员关系
		if (StringUtils.isNotBlank(UserUtils.getPartymem().getId())) {
			// 需要被发布的游戏
			PbsProposal pbsProposalDto1 = new PbsProposal();
			pbsProposalDto1.setSPublish("0");
			pbsProposalDto1.setsAcceptermem(UserUtils.getPartymem());
			pbsProposalDto1.setsReportermem(UserUtils.getPartymem());
			List<PbsProposal> list1 = pbsProposalService.findList(pbsProposalDto1);
			// 自己提交的 建议
			PbsProposal pbsProposalDto2 = new PbsProposal();
			pbsProposalDto2.setSPublish("1");
			List<PbsProposal> list2 = pbsProposalService.findList(pbsProposalDto2);
			Set<PbsProposal> ts = new HashSet<PbsProposal>();
			ts.addAll(list1);
			ts.addAll(list2);
			model.addAttribute("list", ts);
			// 获取 当前的 党员信息
			model.addAttribute("partymem", UserUtils.getPartymem());
		}
		return "Nav-activity/proposal/proposal/proposalList";
	}

	// 建议详细信息
	@RequestMapping(value = "proposalInfo")
	public String proposalInfo(String id, Model model) {
		// 获取 当前的 建言献策
		PbsProposal pbsProposal = pbsProposalService.get(id);
		// 获取当前的 建言分区
		PbsProposalarea pbsProposalarea = pbsProposalareaService.get(pbsProposal.getsAreaid().getId());
		// 获取 当前的 建议
		model.addAttribute("pbsProposal", pbsProposal);
		// 获取 当前的 建议分区
		model.addAttribute("pbsProposalarea", pbsProposalarea);
		// 如果当前的审核人  
		if(pbsProposalarea.getsMastermem().getId() .equals(UserUtils.getPartymem().getId())){
			model.addAttribute("handleFlag",true);
		}
		
		return "Nav-activity/proposal/proposal/proposalInfo";
	}

	// 查看 回复的信息
	@RequestMapping(value = "proposalReplyPage")
	public String proposalReplyPage(String id, Model model) {
		// 获取当前的 操作信息 当前的 信息 为
		PbsProposalopt pbsProposaloptDto = new PbsProposalopt();
		pbsProposaloptDto.setSOpttype("0");
		pbsProposaloptDto.setsProposalid(new PbsProposal(id));
		List<PbsProposalopt> pbsProposaloptList = pbsProposaloptService.findList(pbsProposaloptDto);
		model.addAttribute("pbsProposaloptList", pbsProposaloptList);
		// 返回 建议 id
		model.addAttribute("proposalId", id);

		return "Nav-activity/proposal/proposal/proposalReply";
	}

	// 审核当前的 建议
	@RequestMapping(value = "proposalHandlePage")
	public String proposalHandlePage(String id, Model model) {
		// 获取当前的建议
		PbsProposal pbsProposal = pbsProposalService.get(id);
		// 返回的内容
		model.addAttribute("proposal", pbsProposal);
		return "Nav-activity/proposal/proposal/proposalHandle";
	}

	// 查看当前建议审核是否通过 以及反馈信息
	@RequestMapping(value = "proposalHandleInfoPage")
	public String proposalHandleInfoPage(String id, Model model) {
		// 获取当前的 操作信息 当前的 信息 为
		PbsProposalopt pbsProposaloptDto = new PbsProposalopt();
		// 当前为评价
		pbsProposaloptDto.setSOpttype("1");
		List<PbsProposalopt> pbsProposaloptList = pbsProposaloptService.findList(pbsProposaloptDto);
		if(pbsProposaloptList.size()>0){
			// 返回相关 的 审核信息
			model.addAttribute("proposalopt", pbsProposaloptList.get(0));
		}
		return "Nav-activity/proposal/proposal/proposalInfo";
	}

	// 回复当前的信息
	@ResponseBody
	@RequestMapping(value = "proposalReplyAction")
	public String proposalReplyAction(PbsProposalopt pbsProposalopt, Model model) {
		// 如果 当前 党员信息不为空
		if (StringUtils.isNotBlank(UserUtils.getPartymem().getId())) {
			// 配置当前的建议内容
			PbsProposal pbsProposal = pbsProposalService.get(pbsProposalopt.getsProposalid().getId());
			// 建议的分区内容
			pbsProposalopt.setsAreatype(pbsProposal.getsAreaid());
			pbsProposalopt.setsAreavesion(pbsProposal.getSAreaversion());
			// 获取当前的 内容
			pbsProposalopt.setsReportermem(UserUtils.getPartymem());
			pbsProposalopt.setSShowtype(pbsProposal.getSShowtype());
			pbsProposalopt.setSReporteruser(UserUtils.getUser());
			// 保存当前 建议操作
			pbsProposaloptService.save(pbsProposalopt);
			return SUCCESS;
		}
		return FAIL;
	}

	// 审核当前的信息
	@ResponseBody
	@RequestMapping(value = "proposalHandleAction")
	public String proposalValueAction(PbsProposalopt pbsProposalopt, Model model) {
		if (StringUtils.isNotBlank(UserUtils.getPartymem().getId())) {
			// 配置当前的建议内容
			PbsProposal pbsProposal = pbsProposalService.get(pbsProposalopt.getsProposalid().getId());
			// 建议的分区内容
			pbsProposalopt.setsAreatype(pbsProposal.getsAreaid());
			pbsProposalopt.setsAreavesion(pbsProposal.getSAreaversion());
			// 获取当前的 内容
			pbsProposalopt.setsReportermem(UserUtils.getPartymem());
			pbsProposalopt.setSShowtype(pbsProposal.getSShowtype());
			pbsProposalopt.setSReporteruser(UserUtils.getUser());
			// 保存当前 建议操作
			pbsProposaloptService.save(pbsProposalopt);
			// 设置当前 建议是否通过 1： 通过 2：不通过
			String publish =pbsProposalopt.getSOpresult().equals("1")?"1":"0";
			pbsProposal.setSStat(pbsProposalopt.getSOpresult());
			pbsProposal.setSPublish(publish);
			// 更细当前的建议
			pbsProposalService.save(pbsProposal);
			return SUCCESS;
		}
		return FAIL;
		
	}

	// 提交 建言献策
	@ResponseBody
	@RequestMapping(value = "proposalSubmit")
	public String proposalSubmit(PbsProposal pbsProposal, Model model) {
		// 当前如果有党员关系
		if (StringUtils.isNotBlank(UserUtils.getPartymem().getId())) {
			// 获取 当前的 评价分区 的 接收人
			PbsProposalarea pbsProposalarea = pbsProposalareaService.get(pbsProposal.getsAreaid().getId());
			// 当前的 接收的党员
			pbsProposal.setsReportermem(UserUtils.getPartymem());
			pbsProposal.setsReporteruser(UserUtils.getUser());
			// 当前的 建议分区 接收的党员
			pbsProposal.setsAcceptermem(pbsProposalarea.getsMastermem());
			// 已经提交了
			pbsProposal.setSStat("0");
			pbsProposal.setSPublish("0");
			pbsProposalService.save(pbsProposal);
			return SUCCESS;
		}
		return FAIL;
	}

}