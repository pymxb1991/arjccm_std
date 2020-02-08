/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.notebook.web;

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
import com.arjjs.ccm.modules.ccm.notebook.entity.CcmNotebook;
import com.arjjs.ccm.modules.ccm.notebook.service.CcmNotebookService;

/**
 * 记事本Controller
 * @author liuyongjian
 * @version 2019-06-18
 */
@Controller
@RequestMapping(value = "${adminPath}/notebook/ccmNotebook")
public class CcmNotebookController extends BaseController {

	@Autowired
	private CcmNotebookService ccmNotebookService;
	
	@ModelAttribute
	public CcmNotebook get(@RequestParam(required=false) String id) {
		CcmNotebook entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmNotebookService.get(id);
		}
		if (entity == null){
			entity = new CcmNotebook();
		}
		return entity;
	}
	
	@RequiresPermissions("notebook:ccmNotebook:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmNotebook ccmNotebook, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmNotebook> page = ccmNotebookService.findPage(new Page<CcmNotebook>(request, response), ccmNotebook); 
		model.addAttribute("page", page);
		return "ccm/notebook/ccmNotebookList";
	}

	@RequiresPermissions("notebook:ccmNotebook:view")
	@RequestMapping(value = "form")
	public String form(CcmNotebook ccmNotebook, Model model) {
		model.addAttribute("ccmNotebook", ccmNotebook);
		return "ccm/notebook/ccmNotebookForm";
	}

	@RequiresPermissions("notebook:ccmNotebook:edit")
	@RequestMapping(value = "save")
	public String save(CcmNotebook ccmNotebook, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmNotebook)){
			return form(ccmNotebook, model);
		}
		ccmNotebookService.save(ccmNotebook);
		addMessage(redirectAttributes, "保存记事本成功");
		return "redirect:"+Global.getAdminPath()+"/notebook/ccmNotebook/?repage";
	}
	
	@RequiresPermissions("notebook:ccmNotebook:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmNotebook ccmNotebook, RedirectAttributes redirectAttributes) {
		ccmNotebookService.delete(ccmNotebook);
		addMessage(redirectAttributes, "删除记事本成功");
		return "redirect:"+Global.getAdminPath()+"/notebook/ccmNotebook/?repage";
	}

}