/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.apply.entity.PbsApplyrec;
import com.arjjs.ccm.modules.pbs.apply.service.PbsApplyrecService;
import com.arjjs.ccm.modules.pbs.common.FlowServiceUtil;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlownode;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowwork;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowworknode;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlownodeService;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlowworkService;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlowworknodeService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

/**
 * 微信端审核流程Controller
 * 
 * @author lc
 * @version 2018-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/flow/deal")
public class MobileFlowController extends BaseController {
	@Autowired
	private PbsApplyrecService pbsApplyrecService;
	@Autowired
	private PbsFlownodeService pbsFlownodeService;
	@Autowired
	private PbsFlowworknodeService pbsFlowworknodeService;
	@Autowired
	private PbsFlowworkService pbsFlowworkService;

	// 关系转移申请
	private static final String TABLEAPPLY = "0";
	// // 加入组织
	// private static final String PbsApplyTypeN1 = "加入组织";
	// // 脱离组织
	// private static final String PbsApplyTypeN2 = "脱离组织";
	// // 工作安排
	// private static final String PbsApplyType2 = "工作安排";
	// 审核通过
	private static final String APPHANDLETYPEYES = "100";
	// 审核驳回
	private static final String APPHANDLETYPENO = "-99";

	// 获取 我的申请列表
	@RequestMapping(value = "dealtMyList")
	public String dealtMyList(Model model) {
		// 当前如果没有党员关系
		PbsFlowwork pbsFlowworkDto = new PbsFlowwork();
		pbsFlowworkDto.setsBindmember(UserUtils.getPartymem());
		List<PbsFlowwork> PbsFlowworkList = pbsFlowworkService.findList(pbsFlowworkDto);
		model.addAttribute("list", PbsFlowworkList);
		return "/Nav-personal/feedback/feedbackList";
	}

	// 申请详细信息
	@RequestMapping(value = "dealtMyInfo")
	public String dealtMyInfo(String id, Model model, RedirectAttributes redirectAttributes) {
		PbsApplyrec pbsApplyrec = pbsApplyrecService.get(id);
		model.addAttribute("pbsApplyrec", pbsApplyrec);
		return "/Nav-personal/feedback/feedbackInfo";
	}

	// 获取 待处理列表
	@RequestMapping(value = "dealtList")
	public String dealtList(Model model) {

		PbsFlowwork pbsFlowworkDto = new PbsFlowwork();
		pbsFlowworkDto.setsBindstat(APPHANDLETYPENO);
		//
		String roleList = UserUtils.getUser().getRoleNames();
		// 当前如果没有党员关系
		if (StringUtils.isNotBlank(roleList) && roleList.contains("系统管理员")) {
			List<PbsFlowwork> PbsFlowworkList = pbsFlowworkService.findListByApprover(pbsFlowworkDto);
			model.addAttribute("list", PbsFlowworkList);
		} else if (StringUtils.isNotBlank(UserUtils.getPartymem().getId())) {
			pbsFlowworkDto.setsBindmember(UserUtils.getPartymem());
			List<PbsFlowwork> PbsFlowworkList = pbsFlowworkService.findListByApprover(pbsFlowworkDto);
			model.addAttribute("list", PbsFlowworkList);
			// 当前的 人员 为系统管理员
		}

		return "/Nav-personal/feedback/dealt/dealtList";
	}

	// 申请审核页面
	@RequestMapping(value = "applyHandle")
	public String applyHandle(String id, Model model, RedirectAttributes redirectAttributes) {
		// 工作流节点
		PbsFlowworknode pbsFlowworknodeDto = new PbsFlowworknode();
		pbsFlowworknodeDto.setSBindkey(id);
		pbsFlowworknodeDto.setSSetstatval("0");
		List<PbsFlowworknode> pbsFlowworknodes = pbsFlowworknodeService.findList(pbsFlowworknodeDto);
		if (pbsFlowworknodes.size() > 0) {
			model.addAttribute("pbsFlowworknode", pbsFlowworknodes.get(0));
		}
		PbsApplyrec pbsApplyrec = pbsApplyrecService.get(id);
		model.addAttribute("pbsApplyrec", pbsApplyrec);
		return "/Nav-personal/feedback/dealt/dealtHandle";
	}

	// 申请详细信息
	@RequestMapping(value = "dealtInfo")
	public String dealtInfo(String id, Model model, RedirectAttributes redirectAttributes) {
		PbsApplyrec pbsApplyrec = pbsApplyrecService.get(id);
		model.addAttribute("pbsApplyrec", pbsApplyrec);
		return "/Nav-personal/feedback/dealt/dealtInfo";
	}

	// 申请反馈信息
	@RequestMapping(value = "dealtFeedback")
	public String dealtFeedback(String id, Model model, RedirectAttributes redirectAttributes) {

		PbsApplyrec pbsApplyrec = pbsApplyrecService.get(id);
		model.addAttribute("pbsApplyrec", pbsApplyrec);
		// 获取申请
		PbsFlowworknode pbsFlowworknodeDto = new PbsFlowworknode();
		pbsFlowworknodeDto.setSSetstatval("1");
		pbsFlowworknodeDto.setSBindkey(id);
		List<PbsFlowworknode> list = pbsFlowworknodeService.findList(pbsFlowworknodeDto);
		model.addAttribute("list", list);
		return "/Nav-personal/relationship/relationshipFeedback";
	}

	// 申请审核
	@ResponseBody
	@RequestMapping(value = "applyAction")
	public String applyAction(String id, String handletype, String sactionremark, Model model) {
		// 当前审核节点
		PbsFlowworknode pbsFlowworknode = pbsFlowworknodeService.get(id);
		// 查看当前节点是否已经添加过
		if (pbsFlowworknode.getSSetstatval().equals("1")) {
			return "fail";
		}
		pbsFlowworknode.setSActionremark(sactionremark);
		pbsFlowworknode.setsOperator(UserUtils.getUser());
		pbsFlowworknode.setsBindmember(UserUtils.getPartymem());
		// 审核通过
		if (APPHANDLETYPEYES.equals(handletype)) {
			// 创建下一个节点 并 完成当前的节点
			pbsFlowworknodeService.PassHandle(pbsFlowworknode);
			PbsFlownode pbsflownode = pbsFlownodeService.get(pbsFlowworknode.getsNodeid().getId());
			// 如果是当前的 id是最大id 则进行
			String maxid = pbsFlownodeService.findnodeMaxSort(pbsflownode.getsFlowid().getId());
			if (maxid.equals(pbsflownode.getsSort())) {
				FlowServiceUtil.rountPass(pbsFlowworknode.getsFlowid().getsTypecode(), pbsFlowworknode.getSBindkey());
				// 获取申请
				PbsApplyrec apply = pbsApplyrecService.get(pbsFlowworknode.getSBindkey());
				// 添加信息读取
				FlowServiceUtil.Notice(TABLEAPPLY, apply.getId(), apply.getsBindmember().getId());
			}
		}
		// 审核驳回
		if (APPHANDLETYPENO.equals(handletype)) {
			pbsFlowworknodeService.RefuseHandle(pbsFlowworknode);
			// 执行相关的方法
			FlowServiceUtil.rountRefuse(pbsFlowworknode.getsFlowid().getsTypecode(), pbsFlowworknode.getSBindkey());
			// 获取申请
			PbsApplyrec apply = pbsApplyrecService.get(pbsFlowworknode.getSBindkey());
			// 添加信息读取
			FlowServiceUtil.Notice(TABLEAPPLY, apply.getId(), apply.getsBindmember().getId());
		}
		return "success";
	}

	// 已经完成的申请列表
	@RequestMapping(value = "dealtFinishList")
	public String dealtFinishList(String id, Model model, RedirectAttributes redirectAttributes) {
		// 当前的党员用户
		PbsPartymem curMem = UserUtils.getPartymem();
		if (StringUtils.isNotBlank(curMem.getId())) {
			PbsFlowwork pbsFlowworkDto = new PbsFlowwork();
			pbsFlowworkDto.setsBindstat(APPHANDLETYPEYES);
			pbsFlowworkDto.setsBindmember(curMem);
			List<PbsFlowwork> PbsFlowworkList = pbsFlowworkService.findListFinish(pbsFlowworkDto);
			model.addAttribute("list", PbsFlowworkList);
		}
		return "Nav-personal/feedback/processed/processedList";
	}

}