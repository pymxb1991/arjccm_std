/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.web;

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
import com.arjjs.ccm.modules.pbs.person.entity.PbsPositionlevel;
import com.arjjs.ccm.modules.pbs.person.service.PbsPositionlevelService;

/**
 * 职位信息Controller
 * 
 * @author lc
 * @version 2018-06-15
 */
@Controller
@RequestMapping(value = "${adminPath}/person/pbsPositionlevel")
public class PbsPositionlevelController extends BaseController {

	@Autowired
	private PbsPositionlevelService pbsPositionlevelService;

	@ModelAttribute
	public PbsPositionlevel get(@RequestParam(required = false) String id) {
		PbsPositionlevel entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsPositionlevelService.get(id);
		}
		if (entity == null) {
			entity = new PbsPositionlevel();
		}
		return entity;
	}

	@RequiresPermissions("person:pbsPositionlevel:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsPositionlevel pbsPositionlevel, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsPositionlevel> page = pbsPositionlevelService.findPage(new Page<PbsPositionlevel>(request, response),
				pbsPositionlevel);
		model.addAttribute("page", page);
		return "pbs/person/pbsPositionlevelList";
	}

	@RequiresPermissions("person:pbsPositionlevel:view")
	@RequestMapping(value = "form")
	public String form(PbsPositionlevel pbsPositionlevel, Model model) {
		model.addAttribute("pbsPositionlevel", pbsPositionlevel);
		return "pbs/person/pbsPositionlevelForm";
	}

	@RequiresPermissions("person:pbsPositionlevel:edit")
	@RequestMapping(value = "save")
	public String save(PbsPositionlevel pbsPositionlevel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsPositionlevel)) {
			return form(pbsPositionlevel, model);
		}
		pbsPositionlevelService.save(pbsPositionlevel);
		addMessage(redirectAttributes, "保存职位信息成功");
		return "redirect:" + Global.getAdminPath() + "/person/pbsPositionlevel/?repage";
	}

	@RequiresPermissions("person:pbsPositionlevel:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsPositionlevel pbsPositionlevel, RedirectAttributes redirectAttributes) {
		pbsPositionlevelService.delete(pbsPositionlevel);
		addMessage(redirectAttributes, "删除职位信息成功");
		return "redirect:" + Global.getAdminPath() + "/person/pbsPositionlevel/?repage";
	}

	// 获取所有的
	@RequiresPermissions("person:pbsPositionlevel:edit")
	@RequestMapping(value = "namelist")
	public String namelist(PbsPositionlevel pbsPositionlevel, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsPositionlevel> list = pbsPositionlevelService.findList(pbsPositionlevel);
		model.addAttribute("list", list);
		return "mapping/PositionlevelList";
	}
}