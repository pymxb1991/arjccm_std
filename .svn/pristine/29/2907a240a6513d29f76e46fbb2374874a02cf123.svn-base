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
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionRule;
import com.arjjs.ccm.modules.pbs.question.service.PbsQuestionRuleService;

/**
 * 评分规则定义Controller
 * @author lc
 * @version 2018-06-06
 */
@Controller
@RequestMapping(value = "${adminPath}/question/pbsQuestionRule")
public class PbsQuestionRuleController extends BaseController {

	@Autowired
	private PbsQuestionRuleService pbsQuestionRuleService;
	
	@ModelAttribute
	public PbsQuestionRule get(@RequestParam(required=false) String id) {
		PbsQuestionRule entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsQuestionRuleService.get(id);
		}
		if (entity == null){
			entity = new PbsQuestionRule();
		}
		return entity;
	}
	
	@RequiresPermissions("question:pbsQuestionRule:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsQuestionRule pbsQuestionRule, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsQuestionRule> page = pbsQuestionRuleService.findPage(new Page<PbsQuestionRule>(request, response), pbsQuestionRule); 
		model.addAttribute("page", page);
		return "pbs/question/rule/pbsQuestionRuleList";
	}

	@RequiresPermissions("question:pbsQuestionRule:view")
	@RequestMapping(value = "form")
	public String form(PbsQuestionRule pbsQuestionRule, Model model) {
		model.addAttribute("pbsQuestionRule", pbsQuestionRule);
		return "pbs/question/rule/pbsQuestionRuleForm";
	}

	@RequiresPermissions("question:pbsQuestionRule:edit")
	@RequestMapping(value = "save")
	public String save(PbsQuestionRule pbsQuestionRule, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsQuestionRule)){
			return form(pbsQuestionRule, model);
		}
		pbsQuestionRuleService.save(pbsQuestionRule);
		addMessage(redirectAttributes, "保存评分规则定义成功");
		return "redirect:"+Global.getAdminPath()+"/question/pbsQuestionRule/?repage";
	}
	
	@RequiresPermissions("question:pbsQuestionRule:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsQuestionRule pbsQuestionRule, RedirectAttributes redirectAttributes) {
		pbsQuestionRuleService.delete(pbsQuestionRule);
		addMessage(redirectAttributes, "删除评分规则定义成功");
		return "redirect:"+Global.getAdminPath()+"/question/pbsQuestionRule/?repage";
	}

}