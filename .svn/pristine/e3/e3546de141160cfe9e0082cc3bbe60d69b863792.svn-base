/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.web;

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
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowKeyJob;
import com.arjjs.ccm.modules.ccm.know.service.CcmKnowKeyJobService;

/**
 * 专项工作Controller
 * @author liang
 * @version 2018-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/know/ccmKnowKeyJob")
public class CcmKnowKeyJobController extends BaseController {

	@Autowired
	private CcmKnowKeyJobService ccmKnowKeyJobService;
	
	@ModelAttribute
	public CcmKnowKeyJob get(@RequestParam(required=false) String id) {
		CcmKnowKeyJob entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmKnowKeyJobService.get(id);
		}
		if (entity == null){
			entity = new CcmKnowKeyJob();
		}
		return entity;
	}
	
	@RequiresPermissions("know:ccmKnowKeyJob:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmKnowKeyJob ccmKnowKeyJob, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmKnowKeyJob> page = ccmKnowKeyJobService.findPage(new Page<CcmKnowKeyJob>(request, response), ccmKnowKeyJob); 
		model.addAttribute("page", page);
		return "ccm/know/ccmKnowKeyJobList";
	}

	@RequiresPermissions("know:ccmKnowKeyJob:view")
	@RequestMapping(value = "form")
	public String form(CcmKnowKeyJob ccmKnowKeyJob, Model model) {
		model.addAttribute("ccmKnowKeyJob", ccmKnowKeyJob);
		return "ccm/know/ccmKnowKeyJobForm";
	}

	@RequiresPermissions("know:ccmKnowKeyJob:edit")
	@RequestMapping(value = "save")
	public String save(CcmKnowKeyJob ccmKnowKeyJob, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmKnowKeyJob)){
			return form(ccmKnowKeyJob, model);
		}
		ccmKnowKeyJobService.save(ccmKnowKeyJob);
		addMessage(redirectAttributes, "保存专项工作成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowKeyJob/?repage";
	}
	
	@RequiresPermissions("know:ccmKnowKeyJob:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmKnowKeyJob ccmKnowKeyJob, RedirectAttributes redirectAttributes) {
		ccmKnowKeyJobService.delete(ccmKnowKeyJob);
		addMessage(redirectAttributes, "删除专项工作成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowKeyJob/?repage";
	}

}