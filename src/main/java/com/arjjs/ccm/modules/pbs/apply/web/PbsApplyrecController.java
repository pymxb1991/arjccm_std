/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.apply.web;

import java.util.Arrays;
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
import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.apply.entity.PbsApplyrec;
import com.arjjs.ccm.modules.pbs.apply.service.PbsApplyrecService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemService;

/**
 * 申请记录Controller
 * 
 * @author lc
 * @version 2018-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/apply/pbsApplyrec")
public class PbsApplyrecController extends BaseController {
	@Autowired
	private PbsApplyrecService pbsApplyrecService;
	@Autowired
	private PbsPartymemService pbsPartymemService;

	@ModelAttribute
	public PbsApplyrec get(@RequestParam(required = false) String id) {
		PbsApplyrec entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsApplyrecService.get(id);
		}
		if (entity == null) {
			entity = new PbsApplyrec();
		}
		return entity;
	}

	@RequiresPermissions("apply:pbsApplyrec:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsApplyrec pbsApplyrec, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsApplyrec> page = pbsApplyrecService.findPage(new Page<PbsApplyrec>(request, response), pbsApplyrec);
		model.addAttribute("page", page);
		return "pbs/apply/pbsApplyrecList";
	}

	@RequiresPermissions("apply:pbsApplyrec:view")
	@RequestMapping(value = "form")
	public String form(PbsApplyrec pbsApplyrec, Model model) {
		model.addAttribute("pbsApplyrec", pbsApplyrec);
		PbsPartymem partymemDto = new PbsPartymem();
		// 当前 审核人 不为空
		if (StringUtils.isNotEmpty(pbsApplyrec.getsApprover())) {
			List<String> ids = Arrays.asList(pbsApplyrec.getsApprover().split(","));
			partymemDto.setIds(ids);
			List<PbsPartymem> pbspartymems = pbsPartymemService.findList(partymemDto);
			String memNames = Collections3.extractToString(pbspartymems, "SName", ",");
			pbsApplyrec.setsApproverName(memNames);
		}
		return "pbs/apply/pbsApplyrecForm";
	}

	@RequiresPermissions("apply:pbsApplyrec:edit")
	@RequestMapping(value = "save")
	public String save(PbsApplyrec pbsApplyrec, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsApplyrec)) {
			return form(pbsApplyrec, model);
		}
		pbsApplyrecService.save(pbsApplyrec);
		addMessage(redirectAttributes, "保存申请记录成功");
		return "redirect:" + Global.getAdminPath() + "/apply/pbsApplyrec/?repage";
	}

	@RequiresPermissions("apply:pbsApplyrec:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsApplyrec pbsApplyrec, RedirectAttributes redirectAttributes) {
		pbsApplyrecService.delete(pbsApplyrec);
		addMessage(redirectAttributes, "删除申请记录成功");
		return "redirect:" + Global.getAdminPath() + "/apply/pbsApplyrec/?repage";
	}

	// 获取 所有的 类型
	@RequiresPermissions("apply:pbsApplyrec:view")
	@RequestMapping(value = "namelist")
	public String namelist(PbsApplyrec pbsApplyrec, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsApplyrec> list = pbsApplyrecService.findList(pbsApplyrec);
		model.addAttribute("list", list);
		return "/mapping/PbsApplyrecList";
	}

}