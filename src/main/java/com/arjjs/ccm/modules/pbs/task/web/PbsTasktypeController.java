/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.task.web;

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
import com.arjjs.ccm.modules.pbs.task.entity.PbsTasktype;
import com.arjjs.ccm.modules.pbs.task.service.PbsTasktypeService;

/**
 * 工作安排类型定义Controller
 * 
 * @author lc
 * @version 2018-05-03
 */
@Controller
@RequestMapping(value = "${adminPath}/task/pbsTasktype")
public class PbsTasktypeController extends BaseController {

	@Autowired
	private PbsTasktypeService pbsTasktypeService;

	@ModelAttribute
	public PbsTasktype get(@RequestParam(required = false) String id) {
		PbsTasktype entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsTasktypeService.get(id);
		}
		if (entity == null) {
			entity = new PbsTasktype();
		}
		return entity;
	}

	@RequiresPermissions("task:pbsTasktype:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsTasktype pbsTasktype, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsTasktype> page = pbsTasktypeService.findPage(new Page<PbsTasktype>(request, response), pbsTasktype);
		model.addAttribute("page", page);
		return "pbs/task/pbsTasktypeList";
	}

	@RequiresPermissions("task:pbsTasktype:view")
	@RequestMapping(value = "form")
	public String form(PbsTasktype pbsTasktype, Model model) {
		model.addAttribute("pbsTasktype", pbsTasktype);
		return "pbs/task/pbsTasktypeForm";
	}

	@RequiresPermissions("task:pbsTasktype:edit")
	@RequestMapping(value = "save")
	public String save(PbsTasktype pbsTasktype, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsTasktype)) {
			return form(pbsTasktype, model);
		}
		pbsTasktypeService.save(pbsTasktype);
		addMessage(redirectAttributes, "保存工作安排类型定义成功");
		return "redirect:" + Global.getAdminPath() + "/task/pbsTasktype/?repage";
	}

	@RequiresPermissions("task:pbsTasktype:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsTasktype pbsTasktype, RedirectAttributes redirectAttributes) {
		pbsTasktypeService.delete(pbsTasktype);
		addMessage(redirectAttributes, "删除工作安排类型定义成功");
		return "redirect:" + Global.getAdminPath() + "/task/pbsTasktype/?repage";
	}

	// 获取 所有的 类型
	@RequestMapping(value = "namelist")
	public String namelist(PbsTasktype pbsTasktype, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsTasktype> list = pbsTasktypeService.findList(pbsTasktype);
		model.addAttribute("list", list);
		return "mapping/TasktypeList";
	}

}