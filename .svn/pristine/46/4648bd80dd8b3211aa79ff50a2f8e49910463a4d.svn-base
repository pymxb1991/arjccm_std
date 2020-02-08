/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.apply.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.apply.entity.PbsApplyrec;
import com.arjjs.ccm.modules.pbs.apply.service.PbsApplyrecService;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowdefinition;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowworknode;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlowdefinitionService;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlowworknodeService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsDepartmentbind;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.service.PbsDepartmentbindService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

/**
 * 申请记录Controller
 * 
 * @author lc
 * @version 2018-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/apply/ApplyPc")
public class PbsApplyPcController extends BaseController {
	@Autowired
	private PbsApplyrecService pbsApplyrecService;
	@Autowired
	private PbsFlowdefinitionService pbsFlowdefinitionService;
	@Autowired
	private PbsFlowworknodeService pbsFlowworknodeService;
	@Autowired
	private PbsDepartmentbindService pbsDepartmentbindService;

	// 获取党员申请
	@RequestMapping(value = { "list", "" })
	public String list(PbsApplyrec pbsApplyrec, HttpServletRequest request, HttpServletResponse response, Model model) {
		PbsPartymem curpartymem = UserUtils.getPartymem();
		// 如果不为空 则进行
		if (StringUtils.isNotBlank(curpartymem.getId())) {
			pbsApplyrec.setsBindmember(curpartymem);
			Page<PbsApplyrec> page = pbsApplyrecService.findPage(new Page<PbsApplyrec>(request, response), pbsApplyrec);
			model.addAttribute("page", page);
		}
		return "pbs/apply/pcRelation/pbsApplyrecList";
	}

	// 申请详细信息
	@RequestMapping(value = "applyInfo")
	public String applyInfo(String id, Model model, RedirectAttributes redirectAttributes) {

		PbsApplyrec pbsApplyrec = pbsApplyrecService.get(id);
		model.addAttribute("pbsApplyrec", pbsApplyrec);
		// 获取申请
		PbsFlowworknode pbsFlowworknodeDto = new PbsFlowworknode();
		pbsFlowworknodeDto.setSSetstatval("1");
		pbsFlowworknodeDto.setSBindkey(id);
		// 忽略当前的页面
		pbsFlowworknodeDto.setIgnoreNode(true);
		List<PbsFlowworknode> list = pbsFlowworknodeService.findList(pbsFlowworknodeDto);
		model.addAttribute("list", list);
		return "pbs/apply/pcRelation/pbsApplyrecForm";
	}

	// 申请页面
	@RequestMapping(value = "applyNew")
	public String applyNew(PbsApplyrec pbsApplyrec, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("pbsApplyrec", pbsApplyrec);
		return "pbs/apply/pcRelation/pbsApplyrecNewForm";
	}

	// 申请反馈信息
	// @RequestMapping(value = "applyFeedback")
	// public String applyFeedback(String id, Model model, RedirectAttributes
	// redirectAttributes) {
	// // 获取相关的内容
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

	// 提交申请
	@RequestMapping(value = "apply")
	public String apply(PbsApplyrec pbsApplyrec, Model model, RedirectAttributes redirectAttributes) {
		// 
		if (!beanValidator(model, pbsApplyrec)) {
			return applyNew(pbsApplyrec, model, redirectAttributes);
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

		return "redirect:" + Global.getAdminPath() + "/apply/ApplyPc/?repage";
	}

}