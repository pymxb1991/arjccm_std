/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.thinkingreport.web;

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
import com.arjjs.ccm.modules.pbs.thinkingreport.entity.PbsThinkingreport;
import com.arjjs.ccm.modules.pbs.thinkingreport.service.PbsThinkingreportService;

/**
 * 思想汇报信息Controller
 * 
 * @author lc
 * @version 2018-05-28
 */
@Controller
@RequestMapping(value = "${adminPath}/thinkingreport/pbsThinkingreport")
public class PbsThinkingreportController extends BaseController {

	@Autowired
	private PbsThinkingreportService pbsThinkingreportService;

	@ModelAttribute
	public PbsThinkingreport get(@RequestParam(required = false) String id) {
		PbsThinkingreport entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsThinkingreportService.get(id);
		}
		if (entity == null) {
			entity = new PbsThinkingreport();
		}
		return entity;
	}

	@RequiresPermissions("thinkingreport:pbsThinkingreport:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsThinkingreport pbsThinkingreport, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsThinkingreport> page = pbsThinkingreportService.findPage(new Page<PbsThinkingreport>(request, response),
				pbsThinkingreport);
		model.addAttribute("page", page);
		return "pbs/thinkingreport/pbsThinkingreportList";
	}

	@RequiresPermissions("thinkingreport:pbsThinkingreport:view")
	@RequestMapping(value = "form")
	public String form(PbsThinkingreport pbsThinkingreport, Model model) {
		model.addAttribute("pbsThinkingreport", pbsThinkingreport);
		return "pbs/thinkingreport/pbsThinkingreportForm";
	}

	@RequiresPermissions("thinkingreport:pbsThinkingreport:edit")
	@RequestMapping(value = "save")
	public String save(PbsThinkingreport pbsThinkingreport, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsThinkingreport)) {
			return form(pbsThinkingreport, model);
		}
		pbsThinkingreportService.save(pbsThinkingreport);
		addMessage(redirectAttributes, "保存思想汇报信息成功");
		return "redirect:" + Global.getAdminPath() + "/thinkingreport/pbsThinkingreport/?repage";
	}

	@RequiresPermissions("thinkingreport:pbsThinkingreport:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsThinkingreport pbsThinkingreport, RedirectAttributes redirectAttributes) {
		pbsThinkingreportService.delete(pbsThinkingreport);
		addMessage(redirectAttributes, "删除思想汇报信息成功");
		return "redirect:" + Global.getAdminPath() + "/thinkingreport/pbsThinkingreport/?repage";
	}

	// 获取 所有的 类型
	@RequestMapping(value = "namelist")
	public String namelist(PbsThinkingreport pbsThinkingreport, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsThinkingreport> list = pbsThinkingreportService.findList(pbsThinkingreport);
		model.addAttribute("list", list);
		return "mapping/PbsThinkingreportList";
	}
}