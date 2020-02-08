/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.web;

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
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionRuleitem;
import com.arjjs.ccm.modules.pbs.question.service.PbsQuestionRuleitemService;

/**
 * 评分规则定义Controller
 * @author lc
 * @version 2018-06-06
 */
@Controller
@RequestMapping(value = "${adminPath}/question/pbsQuestionRuleitem")
public class PbsQuestionRuleitemController extends BaseController {

	@Autowired
	private PbsQuestionRuleitemService pbsQuestionRuleitemService;
	
	@ModelAttribute
	public PbsQuestionRuleitem get(@RequestParam(required=false) String id) {
		PbsQuestionRuleitem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsQuestionRuleitemService.get(id);
		}
		if (entity == null){
			entity = new PbsQuestionRuleitem();
		}
		return entity;
	}
	
	@RequiresPermissions("question:pbsQuestionRuleitem:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsQuestionRuleitem pbsQuestionRuleitem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsQuestionRuleitem> page = pbsQuestionRuleitemService.findPage(new Page<PbsQuestionRuleitem>(request, response), pbsQuestionRuleitem); 
		model.addAttribute("page", page);
		return "pbs/question/rule/pbsQuestionRuleitemList";
	}

	@RequiresPermissions("question:pbsQuestionRuleitem:view")
	@RequestMapping(value = "form")
	public String form(PbsQuestionRuleitem pbsQuestionRuleitem, Model model) {
		model.addAttribute("pbsQuestionRuleitem", pbsQuestionRuleitem);
		return "pbs/question/rule/pbsQuestionRuleitemForm";
	}

	@RequiresPermissions("question:pbsQuestionRuleitem:edit")
	@RequestMapping(value = "save")
	public String save(PbsQuestionRuleitem pbsQuestionRuleitem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsQuestionRuleitem)){
			return form(pbsQuestionRuleitem, model);
		}
		pbsQuestionRuleitemService.save(pbsQuestionRuleitem);
		addMessage(redirectAttributes, "保存评分规则定义成功");
		return "redirect:"+Global.getAdminPath()+"/question/pbsQuestionRuleitem/?repage";
	}
	
	@RequiresPermissions("question:pbsQuestionRuleitem:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsQuestionRuleitem pbsQuestionRuleitem, RedirectAttributes redirectAttributes) {
		pbsQuestionRuleitemService.delete(pbsQuestionRuleitem);
		addMessage(redirectAttributes, "删除评分规则定义成功");
		return "redirect:"+Global.getAdminPath()+"/question/pbsQuestionRuleitem/?repage";
	}

}