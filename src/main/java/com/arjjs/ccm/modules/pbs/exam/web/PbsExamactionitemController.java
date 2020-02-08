/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.exam.web;

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
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExamactionitem;
import com.arjjs.ccm.modules.pbs.exam.service.PbsExamactionitemService;

/**
 * 考试题目信息Controller
 * @author lc
 * @version 2018-06-11
 */
@Controller
@RequestMapping(value = "${adminPath}/exam/pbsExamactionitem")
public class PbsExamactionitemController extends BaseController {

	@Autowired
	private PbsExamactionitemService pbsExamactionitemService;
	
	@ModelAttribute
	public PbsExamactionitem get(@RequestParam(required=false) String id) {
		PbsExamactionitem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsExamactionitemService.get(id);
		}
		if (entity == null){
			entity = new PbsExamactionitem();
		}
		return entity;
	}
	
	@RequiresPermissions("exam:pbsExamactionitem:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsExamactionitem pbsExamactionitem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsExamactionitem> page = pbsExamactionitemService.findPage(new Page<PbsExamactionitem>(request, response), pbsExamactionitem); 
		model.addAttribute("page", page);
		return "pbs/exam/action/pbsExamactionitemList";
	}

	@RequiresPermissions("exam:pbsExamactionitem:view")
	@RequestMapping(value = "form")
	public String form(PbsExamactionitem pbsExamactionitem, Model model) {
		model.addAttribute("pbsExamactionitem", pbsExamactionitem);
		return "pbs/exam/action/pbsExamactionitemForm";
	}

	@RequiresPermissions("exam:pbsExamactionitem:edit")
	@RequestMapping(value = "save")
	public String save(PbsExamactionitem pbsExamactionitem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsExamactionitem)){
			return form(pbsExamactionitem, model);
		}
		pbsExamactionitemService.save(pbsExamactionitem);
		addMessage(redirectAttributes, "保存考试题目信息成功");
		return "redirect:"+Global.getAdminPath()+"/exam/pbsExamactionitem/?repage";
	}
	
	@RequiresPermissions("exam:pbsExamactionitem:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsExamactionitem pbsExamactionitem, RedirectAttributes redirectAttributes) {
		pbsExamactionitemService.delete(pbsExamactionitem);
		addMessage(redirectAttributes, "删除考试题目信息成功");
		return "redirect:"+Global.getAdminPath()+"/exam/pbsExamactionitem/?repage";
	}

}