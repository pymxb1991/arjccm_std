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
import com.arjjs.ccm.modules.pbs.apply.service.PbsApplyrecService;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowwork;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlowworkService;

/**
 * 运行工作流Controller
 * @author lc
 * @version 2018-04-23
 */
@Controller
@RequestMapping(value = "${adminPath}/flow/pbsFlowwork")
public class PbsFlowworkController extends BaseController {

	@Autowired
	private PbsFlowworkService pbsFlowworkService;
	@Autowired
	private PbsApplyrecService pbsApplyrecService;
	
	@ModelAttribute
	public PbsFlowwork get(@RequestParam(required=false) String id) {
		PbsFlowwork entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsFlowworkService.get(id);
		}
		if (entity == null){
			entity = new PbsFlowwork();
		}
		return entity;
	}
	
	@RequiresPermissions("flow:pbsFlowwork:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsFlowwork pbsFlowwork, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsFlowwork> page = pbsFlowworkService.findPage(new Page<PbsFlowwork>(request, response), pbsFlowwork); 
		model.addAttribute("page", page);
		return "pbs/flow/pbsFlowworkList";
	}

	@RequiresPermissions("flow:pbsFlowwork:view")
	@RequestMapping(value = "form")
	public String form(PbsFlowwork pbsFlowwork, Model model) {
		model.addAttribute("pbsFlowwork", pbsFlowwork);
		return "pbs/flow/pbsFlowworkForm";
	}

	@RequiresPermissions("flow:pbsFlowwork:edit")
	@RequestMapping(value = "save")
	public String save(PbsFlowwork pbsFlowwork, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsFlowwork)){
			return form(pbsFlowwork, model);
		}
		pbsApplyrecService.updateTypeById(pbsFlowwork.getSBindkey(),pbsFlowwork.getsFlowid().getId());
		pbsFlowworkService.save(pbsFlowwork);
		addMessage(redirectAttributes, "保存运行工作流成功");
		return "redirect:"+Global.getAdminPath()+"/flow/pbsFlowwork/?repage";
	}
	
	@RequiresPermissions("flow:pbsFlowwork:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsFlowwork pbsFlowwork, RedirectAttributes redirectAttributes) {
		pbsFlowworkService.delete(pbsFlowwork);
		addMessage(redirectAttributes, "删除运行工作流成功");
		return "redirect:"+Global.getAdminPath()+"/flow/pbsFlowwork/?repage";
	}

	// 获取 所有的 类型
	@RequiresPermissions("flow:pbsFlowwork:view")
	@RequestMapping(value = "namelist")
	public String namelist(PbsFlowwork pbsFlowwork, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<PbsFlowwork> list = pbsFlowworkService.findList(pbsFlowwork);
		model.addAttribute("list", list);
		return "mapping/FlowWorkList";
	}
}