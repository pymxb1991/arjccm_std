/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.task.web;

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
import com.arjjs.ccm.modules.pbs.task.entity.PbsTaskevaluate;
import com.arjjs.ccm.modules.pbs.task.service.PbsTaskevaluateService;

/**
 * 工作安排反馈信息Controller
 * @author lc
 * @version 2018-05-03
 */
@Controller
@RequestMapping(value = "${adminPath}/task/pbsTaskevaluate")
public class PbsTaskevaluateController extends BaseController {

	@Autowired
	private PbsTaskevaluateService pbsTaskevaluateService;
	
	@ModelAttribute
	public PbsTaskevaluate get(@RequestParam(required=false) String id) {
		PbsTaskevaluate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsTaskevaluateService.get(id);
		}
		if (entity == null){
			entity = new PbsTaskevaluate();
		}
		return entity;
	}
	
	@RequiresPermissions("task:pbsTaskevaluate:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsTaskevaluate pbsTaskevaluate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsTaskevaluate> page = pbsTaskevaluateService.findPage(new Page<PbsTaskevaluate>(request, response), pbsTaskevaluate); 
		model.addAttribute("page", page);
		return "pbs/task/pbsTaskevaluateList";
	}

	@RequiresPermissions("task:pbsTaskevaluate:view")
	@RequestMapping(value = "form")
	public String form(PbsTaskevaluate pbsTaskevaluate, Model model) {
		model.addAttribute("pbsTaskevaluate", pbsTaskevaluate);
		return "pbs/task/pbsTaskevaluateForm";
	}

	@RequiresPermissions("task:pbsTaskevaluate:edit")
	@RequestMapping(value = "save")
	public String save(PbsTaskevaluate pbsTaskevaluate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsTaskevaluate)){
			return form(pbsTaskevaluate, model);
		}
		pbsTaskevaluateService.save(pbsTaskevaluate);
		addMessage(redirectAttributes, "保存工作安排反馈信息成功");
		return "redirect:"+Global.getAdminPath()+"/task/pbsTaskevaluate/?repage";
	}
	
	@RequiresPermissions("task:pbsTaskevaluate:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsTaskevaluate pbsTaskevaluate, RedirectAttributes redirectAttributes) {
		pbsTaskevaluateService.delete(pbsTaskevaluate);
		addMessage(redirectAttributes, "删除工作安排反馈信息成功");
		return "redirect:"+Global.getAdminPath()+"/task/pbsTaskevaluate/?repage";
	}

}