/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowdefinition;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlownode;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlownodeService;

/**
 * 流程节点Controller
 * 
 * @author lc
 * @version 2018-04-20
 */
@Controller
@RequestMapping(value = "${adminPath}/flow/pbsFlownode")
public class PbsFlownodeController extends BaseController {

	@Autowired
	private PbsFlownodeService pbsFlownodeService;

	@ModelAttribute
	public PbsFlownode get(@RequestParam(required = false) String id, @RequestParam(required = false) String flowid) {
		PbsFlownode entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsFlownodeService.get(id);
		}
		if (entity == null) {
			entity = new PbsFlownode();
		}
		if (StringUtils.isNotBlank(flowid)) {
			// 设置默认
			PbsFlowdefinition pbsFlowdefinition = new PbsFlowdefinition();
			pbsFlowdefinition.setId(flowid);
			entity.setsFlowid(pbsFlowdefinition);
		}
		return entity;
	}

	@RequiresPermissions("flow:pbsFlownode:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsFlownode pbsFlownode, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsFlownode> page = pbsFlownodeService.findPage(new Page<PbsFlownode>(request, response), pbsFlownode);
		model.addAttribute("page", page);
		return "pbs/flow/pbsFlownodeList";
	}

	@RequiresPermissions("flow:pbsFlownode:view")
	@RequestMapping(value = "form")
	public String form(PbsFlownode pbsFlownode, Model model) {
		model.addAttribute("pbsFlownode", pbsFlownode);
		return "pbs/flow/pbsFlownodeForm";
	}

	@RequiresPermissions("flow:pbsFlownode:edit")
	@RequestMapping(value = "save")
	public String save(PbsFlownode pbsFlownode, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsFlownode)) {
			return form(pbsFlownode, model);
		}
		pbsFlownodeService.save(pbsFlownode);
		addMessage(redirectAttributes, "保存流程节点成功");
		return "redirect:" + Global.getAdminPath() + "/flow/pbsFlownode/?repage";
	}

	@RequiresPermissions("flow:pbsFlownode:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsFlownode pbsFlownode, RedirectAttributes redirectAttributes) {
		pbsFlownodeService.delete(pbsFlownode);
		addMessage(redirectAttributes, "删除流程节点成功");
		return "redirect:" + Global.getAdminPath() + "/flow/pbsFlownode/?repage";
	}

	// 获取 所有的 类型
	@RequiresPermissions("flow:pbsFlownode:view")
	@RequestMapping(value = "namelist")
	public String namelist(String sFlowid  , HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 如果 当前id 不为空
		if (StringUtils.isNotBlank(sFlowid)) {
			PbsFlownode pbsFlownodeDto  = new PbsFlownode();
			PbsFlowdefinition flowdefinitionDto = new PbsFlowdefinition();
			flowdefinitionDto.setId(sFlowid);
			pbsFlownodeDto.setsFlowid(flowdefinitionDto);
			List<PbsFlownode> list = pbsFlownodeService.findList(pbsFlownodeDto);
			model.addAttribute("list", list);
		}
		return "/mapping/FlowNodeList";
	}

	@RequiresPermissions("flow:pbsFlownode:view")
	@RequestMapping(value = "addform")
	public String addform(PbsFlownode pbsFlownode, Model model) {
		model.addAttribute("pbsFlownode", pbsFlownode);
		return "/pbs/flow/flowWork/pbsFlownodeForm";
	}

}