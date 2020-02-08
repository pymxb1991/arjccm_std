/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.apply.entity.PbsApplyrec;
import com.arjjs.ccm.modules.pbs.apply.service.PbsApplyrecService;
import com.arjjs.ccm.modules.pbs.common.FlowServiceUtil;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowdefinition;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlownode;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowwork;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowworknode;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlowdefinitionService;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlownodeService;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlowworkService;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlowworknodeService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsDepartmentbind;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.service.PbsDepartmentbindService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

/**
 * 微信端审核流程Controller
 * 
 * @author lc
 * @version 2018-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/flow/dealPc")
public class PbsFlowPcApplyController extends BaseController {
	@Autowired
	private PbsApplyrecService pbsApplyrecService;
	@Autowired
	private PbsFlownodeService pbsFlownodeService;
	@Autowired
	private PbsFlowworknodeService pbsFlowworknodeService;
	@Autowired
	private PbsFlowworkService pbsFlowworkService;
	@Autowired
	private PbsFlowdefinitionService pbsFlowdefinitionService;
	@Autowired
	private PbsDepartmentbindService pbsDepartmentbindService;
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
	@RequestMapping(value = { "dealtMyList", "" })
	public String dealtMyList(PbsFlowwork pbsFlowworkDto, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		PbsPartymem curPartymem = UserUtils.getPartymem();
		if (!StringUtils.isEmpty(curPartymem.getId())) {
			// 当前如果没有党员关系
			pbsFlowworkDto.setsBindmember(UserUtils.getPartymem());
			Page<PbsFlowwork> page = pbsFlowworkService.findPage(new Page<PbsFlowwork>(request, response),
					pbsFlowworkDto);
			model.addAttribute("page", page);
		}
		return "pbs/apply/pcHandle/pbsFlowworkMyList";
	}

	// 申请详细信息
	@RequestMapping(value = "dealtMyInfo")
	public String dealtMyInfo(String id, Model model) {
		PbsApplyrec pbsApplyrec = pbsApplyrecService.get(id);
		if (null == pbsApplyrec) {
			pbsApplyrec = new PbsApplyrec();
		}
		model.addAttribute("pbsApplyrec", pbsApplyrec);
		// 获取申请
		PbsFlowworknode pbsFlowworknodeDto = new PbsFlowworknode();
		// 已经通过的
		pbsFlowworknodeDto.setSSetstatval("1");
		pbsFlowworknodeDto.setSBindkey(id);
		// 忽略当前的页面
		pbsFlowworknodeDto.setIgnoreNode(true);
		List<PbsFlowworknode> list = pbsFlowworknodeService.findList(pbsFlowworknodeDto);
		model.addAttribute("list", list);
		return "pbs/apply/pcHandle/pbsFlowworkMyForm";
	}

	// 申请页面
	@RequestMapping(value = "applyNew")
	public String applyNew(PbsApplyrec pbsApplyrec, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("pbsApplyrec", pbsApplyrec);
		return "pbs/apply/pcHandle/pbsApplyrecNewForm";
	}

	// 申请
	@RequestMapping(value = "apply")
	public String apply(PbsApplyrec pbsApplyrec, Model model, RedirectAttributes redirectAttributes) {
		// 校验该数据正常
		if (!beanValidator(model, pbsApplyrec)) {
			return applyNew(pbsApplyrec, model, redirectAttributes);
		}
		// 如果 当前的部门 为空
		if ((null == pbsApplyrec.getsPartment()) || StringUtils.isEmpty(pbsApplyrec.getsPartment().getId())) {
			return dealtMyInfo(pbsApplyrec.getId(), model);
		}
		PbsFlowdefinition pbsFlowdefinitionDto = new PbsFlowdefinition();
		pbsFlowdefinitionDto.setsTypecode(pbsApplyrec.getFlowtype());
		// 获取 当前类型的 工作流
		List<PbsFlowdefinition> pbsFlowdefinitions = pbsFlowdefinitionService.findList(pbsFlowdefinitionDto);
		// 获取当前的 党员管理
		if (pbsFlowdefinitions.size() > 0) {
			pbsApplyrec.setsType(pbsFlowdefinitions.get(0));
		}
		/**
		 * 当前的 党员 是否符合 当前的 转移条件
		 */
		PbsPartymem curPartymem = UserUtils.getPartymem();
		PbsDepartmentbind pbsDepartmentbindDto = new PbsDepartmentbind();
		pbsDepartmentbindDto.setSDepartmentid(pbsApplyrec.getsPartment().getId());
		pbsDepartmentbindDto.setSPartymemid(curPartymem.getId());
		List<PbsDepartmentbind> departmentbinds = pbsDepartmentbindService.findList(pbsDepartmentbindDto);
		// 判断关系转移的逻辑是否成立 关系转入
		if (("JoinOfc").equals(pbsApplyrec.getFlowtype())) {
			if (departmentbinds.size() > 0) {
				addMessage(model, "数据校验失败：您已经加入了该部门!");
				return applyNew(pbsApplyrec, model, redirectAttributes);
			}
		}
		// 关系脱离
		if (("DragOfc").equals(pbsApplyrec.getFlowtype())) {
			if (departmentbinds.size() == 0) {
				addMessage(model, "数据校验失败：您尚未加入了该部门!");
				return applyNew(pbsApplyrec, model, redirectAttributes);
			}
		}
		// 提交组织关系转移
		pbsApplyrecService.apply(pbsApplyrec);
		addMessage(redirectAttributes, "保存申请记录成功");
		return "redirect:" + Global.getAdminPath() + "/flow/dealPc/dealtMyList?repage";
	}

	// 获取 待处理列表
	@RequestMapping(value = "dealtList")
	public String dealtList(PbsFlowwork pbsFlowworkDto, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 当前的党员
		PbsPartymem curpartymem = UserUtils.getPartymem();
		pbsFlowworkDto.setsBindstat(APPHANDLETYPENO);
		//
		String roleList = UserUtils.getUser().getRoleNames();
		// 当前如果没有党员关系
		if (StringUtils.isNotBlank(roleList) && roleList.contains("系统管理员")) {
			Page<PbsFlowwork> PbsFlowworkList = pbsFlowworkService
					.findPageByApprover(new Page<PbsFlowwork>(request, response), pbsFlowworkDto);
			model.addAttribute("page", PbsFlowworkList);
		} else if (StringUtils.isNotBlank(curpartymem.getId())) {
			pbsFlowworkDto.setsBindmember(curpartymem);
			Page<PbsFlowwork> PbsFlowworkList = pbsFlowworkService
					.findPageByApprover(new Page<PbsFlowwork>(request, response), pbsFlowworkDto);
			model.addAttribute("page", PbsFlowworkList);
			// 当前的 人员 为系统管理员
		}
		return "pbs/apply/pcHandle/pbsFlowworkDealList";
	}

	// 申请审核页面
	@RequestMapping(value = "applyHandle")
	public String applyHandle(String id, Model model, RedirectAttributes redirectAttributes) {
		// 工作流节点
		PbsFlowworknode pbsFlowworknodeDto = new PbsFlowworknode();
		pbsFlowworknodeDto.setSBindkey(id);
		// pbsFlowworknodeDto.setSSetstatval("0");
		List<PbsFlowworknode> pbsFlowworknodes = pbsFlowworknodeService.findList(pbsFlowworknodeDto);
		model.addAttribute("list", pbsFlowworknodes);
		// 如果当前正常使用则 会有一个 正在审核的节点
		for (PbsFlowworknode worknode : pbsFlowworknodes) {
			// 正在审核的环节为0
			if (("0").equals(worknode.getSSetstatval())) {
				model.addAttribute("worknodeid", worknode.getId());
			}
		}
		PbsApplyrec pbsApplyrec = pbsApplyrecService.get(id);
		model.addAttribute("pbsApplyrec", pbsApplyrec);
		return "pbs/apply/pcHandle/pbsFlowworkDealForm";
	}

	// // 申请详细信息
	//
	// @RequestMapping(value = "dealtInfo")
	// public String dealtInfo(String id, Model model, RedirectAttributes
	// redirectAttributes) {
	// PbsApplyrec pbsApplyrec = pbsApplyrecService.get(id);
	// model.addAttribute("pbsApplyrec", pbsApplyrec);
	// return "/Nav-personal/feedback/dealt/dealtInfo";
	// }
	//
	// // 申请反馈信息
	// @RequestMapping(value = "dealtFeedback")
	// public String dealtFeedback(String id, Model model, RedirectAttributes
	// redirectAttributes) {
	// PbsApplyrec pbsApplyrec = pbsApplyrecService.get(id);
	// model.addAttribute("pbsApplyrec", pbsApplyrec);
	// // 获取申请
	// PbsFlowworknode pbsFlowworknodeDto = new PbsFlowworknode();
	// pbsFlowworknodeDto.setSSetstatval("1");
	// pbsFlowworknodeDto.setSBindkey(id);
	// List<PbsFlowworknode> list =
	// pbsFlowworknodeService.findList(pbsFlowworknodeDto);
	// model.addAttribute("list", list);
	// return "/Nav-personal/relationship/relationshipFeedback";
	// }

	// 申请
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
			}
		}
		// 审核驳回
		if (APPHANDLETYPENO.equals(handletype)) {
			pbsFlowworknodeService.RefuseHandle(pbsFlowworknode);
			// 执行相关的方法
			FlowServiceUtil.rountRefuse(pbsFlowworknode.getsFlowid().getsTypecode(), pbsFlowworknode.getSBindkey());
		}
		return "success";
	}

	// 已经完成的申请列表
	@RequestMapping(value = "dealtFinishList")
	public String dealtFinishList(PbsFlowwork pbsFlowworkDto, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 当前的党员
		PbsPartymem CurpbsPartymem = UserUtils.getPartymem();
		if (StringUtils.isNotBlank(CurpbsPartymem.getId())) {
			pbsFlowworkDto.setsBindstat(APPHANDLETYPEYES);
			pbsFlowworkDto.setsBindmember(CurpbsPartymem);
			Page<PbsFlowwork> PbsFlowworkList = pbsFlowworkService
					.findPageFinish(new Page<PbsFlowwork>(request, response), pbsFlowworkDto);
			model.addAttribute("page", PbsFlowworkList);
		}
		return "pbs/apply/pcHandle/pbsFlowworkProcessedList";
	}

	// 申请详细信息
	@RequestMapping(value = "dealtFinishInfo")
	public String dealtFinishInfo(String id, Model model) {
		PbsApplyrec pbsApplyrec = pbsApplyrecService.get(id);
		if (null == pbsApplyrec) {
			pbsApplyrec = new PbsApplyrec();
		}
		model.addAttribute("pbsApplyrec", pbsApplyrec);
		// 获取申请
		PbsFlowworknode pbsFlowworknodeDto = new PbsFlowworknode();
		// 已经通过的
		pbsFlowworknodeDto.setSSetstatval("1");
		pbsFlowworknodeDto.setSBindkey(id);
		// 忽略当前的页面
		pbsFlowworknodeDto.setIgnoreNode(true);
		List<PbsFlowworknode> list = pbsFlowworknodeService.findList(pbsFlowworknodeDto);
		model.addAttribute("list", list);
		return "pbs/apply/pcHandle/pbsFlowworkProcessedForm";
	}

	/*
	 * // 申请详细信息
	 * 
	 * @RequestMapping(value = "dealtNew") public String dealtNew( Model model)
	 * { model.addAttribute("pbsApplyrec", pbsApplyrec); return
	 * "/Nav-personal/feedback/dealt/dealtInfo"; }
	 */

}