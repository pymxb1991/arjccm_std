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
import com.arjjs.ccm.modules.pbs.person.entity.PbsLabelinfo;
import com.arjjs.ccm.modules.pbs.person.entity.PbsMemlabel;
import com.arjjs.ccm.modules.pbs.person.service.PbsLabelinfoService;
import com.arjjs.ccm.modules.pbs.person.service.PbsMemlabelService;

/**
 * 标签信息表Controller
 * 
 * @author lc
 * @version 2018-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/person/pbsLabelinfo")
public class PbsLabelinfoController extends BaseController {

	@Autowired
	private PbsLabelinfoService pbsLabelinfoService;
	@Autowired
	private PbsMemlabelService pbsMemlabelService;

	@ModelAttribute
	public PbsLabelinfo get(@RequestParam(required = false) String id) {
		PbsLabelinfo entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsLabelinfoService.get(id);
		}
		if (entity == null) {
			entity = new PbsLabelinfo();
		}
		return entity;
	}

	@RequiresPermissions("person:pbsLabelinfo:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsLabelinfo pbsLabelinfo, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsLabelinfo> page = pbsLabelinfoService.findPage(new Page<PbsLabelinfo>(request, response), pbsLabelinfo);
		model.addAttribute("page", page);
		return "pbs/person/label/pbsLabelinfoList";
	}

	@RequiresPermissions("person:pbsLabelinfo:view")
	@RequestMapping(value = "form")
	public String form(PbsLabelinfo pbsLabelinfo, Model model) {
		model.addAttribute("pbsLabelinfo", pbsLabelinfo);
		return "pbs/person/label/pbsLabelinfoForm";
	}

	@RequiresPermissions("person:pbsLabelinfo:edit")
	@RequestMapping(value = "save")
	public String save(PbsLabelinfo pbsLabelinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsLabelinfo)) {
			return form(pbsLabelinfo, model);
		}
		// 先是校验 数据
		PbsMemlabel memlabelDto = new PbsMemlabel();
		memlabelDto.setSLabelids(pbsLabelinfo.getSType());
		List<PbsMemlabel> MemlabelList = pbsMemlabelService.findList(memlabelDto);
		//
		if (MemlabelList.size() > 0) {
			addMessage(model, "数据验证失败： 该数据已经使用");
			return form(pbsLabelinfo, model);
		}

		pbsLabelinfoService.save(pbsLabelinfo);
		addMessage(redirectAttributes, "保存标签信息表成功");
		return "redirect:" + Global.getAdminPath() + "/person/pbsLabelinfo/?repage";
	}

	@RequiresPermissions("person:pbsLabelinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsLabelinfo pbsLabelinfo, RedirectAttributes redirectAttributes) {
		// 先是校验 数据
		PbsMemlabel memlabelDto = new PbsMemlabel();
		memlabelDto.setSLabelids(pbsLabelinfo.getSType());
		List<PbsMemlabel> MemlabelList = pbsMemlabelService.findList(memlabelDto);
		//
		if (MemlabelList.size() > 0) {
			addMessage(redirectAttributes, "数据验证失败： 该数据已经使用");
			return "redirect:" + Global.getAdminPath() + "/person/pbsLabelinfo/?repage";
		}
		// 删除 标签页信息
		pbsLabelinfoService.delete(pbsLabelinfo);
		addMessage(redirectAttributes, "删除标签信息表成功");
		return "redirect:" + Global.getAdminPath() + "/person/pbsLabelinfo/?repage";
	}

}