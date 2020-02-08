/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.stepaction.web;

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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.flat.stepaction.entity.BphStepAction;
import com.arjjs.ccm.modules.flat.stepaction.service.BphStepActionService;

/**
 * 过程动作关联表Controller
 * @author liu
 * @version 2018-11-17
 */
@Controller
@RequestMapping(value = "${adminPath}/stepaction/bphStepAction")
public class BphStepActionController extends BaseController {

	@Autowired
	private BphStepActionService bphStepActionService;
	
	@ModelAttribute
	public BphStepAction get(@RequestParam(required=false) String id) {
		BphStepAction entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bphStepActionService.get(id);
		}
		if (entity == null){
			entity = new BphStepAction();
		}
		return entity;
	}
	
	@RequiresPermissions("stepaction:bphStepAction:view")
	@RequestMapping(value = {"list", ""})
	public String list(BphStepAction bphStepAction, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BphStepAction> page = bphStepActionService.findPage(new Page<BphStepAction>(request, response), bphStepAction); 
		model.addAttribute("page", page);
		return "flat/stepaction/bphStepActionList";
	}

	@RequiresPermissions("stepaction:bphStepAction:view")
	@RequestMapping(value = "form")
	public String form(BphStepAction bphStepAction, Model model) {
		model.addAttribute("bphStepAction", bphStepAction);
		return "flat/stepaction/bphStepActionForm";
	}

	@RequiresPermissions("stepaction:bphStepAction:edit")
	@RequestMapping(value = "save")
	public String save(BphStepAction bphStepAction, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bphStepAction)){
			return form(bphStepAction, model);
		}
		bphStepActionService.save(bphStepAction);
		addMessage(redirectAttributes, "保存过程动作关联表成功");
		return "redirect:"+Global.getAdminPath()+"/stepaction/bphStepAction/?repage";
	}
	
	@RequiresPermissions("stepaction:bphStepAction:edit")
	@RequestMapping(value = "delete")
	public String delete(BphStepAction bphStepAction, RedirectAttributes redirectAttributes) {
		bphStepActionService.delete(bphStepAction);
		addMessage(redirectAttributes, "删除过程动作关联表成功");
		return "redirect:"+Global.getAdminPath()+"/stepaction/bphStepAction/?repage";
	}

}