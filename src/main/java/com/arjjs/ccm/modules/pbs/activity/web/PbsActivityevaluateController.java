/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.web;

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
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityevaluate;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivityevaluateService;

/**
 * 活动评分Controller
 * @author lc
 * @version 2018-05-15
 */
@Controller
@RequestMapping(value = "${adminPath}/activity/pbsActivityevaluate")
public class PbsActivityevaluateController extends BaseController {

	@Autowired
	private PbsActivityevaluateService pbsActivityevaluateService;
	
	@ModelAttribute
	public PbsActivityevaluate get(@RequestParam(required=false) String id) {
		PbsActivityevaluate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsActivityevaluateService.get(id);
		}
		if (entity == null){
			entity = new PbsActivityevaluate();
		}
		return entity;
	}
	
	@RequiresPermissions("activity:pbsActivityevaluate:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsActivityevaluate pbsActivityevaluate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsActivityevaluate> page = pbsActivityevaluateService.findPage(new Page<PbsActivityevaluate>(request, response), pbsActivityevaluate); 
		model.addAttribute("page", page);
		return "pbs/activity/pbsActivityevaluateList";
	}

	@RequiresPermissions("activity:pbsActivityevaluate:view")
	@RequestMapping(value = "form")
	public String form(PbsActivityevaluate pbsActivityevaluate, Model model) {
		model.addAttribute("pbsActivityevaluate", pbsActivityevaluate);
		return "pbs/activity/pbsActivityevaluateForm";
	}

	@RequiresPermissions("activity:pbsActivityevaluate:edit")
	@RequestMapping(value = "save")
	public String save(PbsActivityevaluate pbsActivityevaluate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsActivityevaluate)){
			return form(pbsActivityevaluate, model);
		}
		pbsActivityevaluateService.save(pbsActivityevaluate);
		addMessage(redirectAttributes, "保存活动评分成功");
		return "redirect:"+Global.getAdminPath()+"/activity/pbsActivityevaluate/?repage";
	}
	
	@RequiresPermissions("activity:pbsActivityevaluate:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsActivityevaluate pbsActivityevaluate, RedirectAttributes redirectAttributes) {
		pbsActivityevaluateService.delete(pbsActivityevaluate);
		addMessage(redirectAttributes, "删除活动评分成功");
		return "redirect:"+Global.getAdminPath()+"/activity/pbsActivityevaluate/?repage";
	}

}