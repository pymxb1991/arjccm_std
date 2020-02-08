/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.web;

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
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivitytype;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivitytypeService;
import com.arjjs.ccm.modules.pbs.sys.entity.GeneralMethod;
import com.arjjs.ccm.modules.pbs.sys.service.PbsGeneralService;

/**
 * 活动定义Controller
 * 
 * @author lc
 * @version 2018-05-14
 */
@Controller
@RequestMapping(value = "${adminPath}/activity/pbsActivitytype")
public class PbsActivitytypeController extends BaseController {

	@Autowired
	private PbsActivitytypeService pbsActivitytypeService;
	@Autowired
	private PbsGeneralService pbsGeneralService;
	
	private static final String TABLEKEY ="s_name";
	private static final String PBSACTIVITYTYPE ="pbs_activitytype";
	 

	@ModelAttribute
	public PbsActivitytype get(@RequestParam(required = false) String id) {
		PbsActivitytype entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsActivitytypeService.get(id);
		}
		if (entity == null) {
			entity = new PbsActivitytype();
		}
		return entity;
	}

	@RequiresPermissions("activity:pbsActivitytype:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsActivitytype pbsActivitytype, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsActivitytype> page = pbsActivitytypeService.findPage(new Page<PbsActivitytype>(request, response),
				pbsActivitytype);
		model.addAttribute("page", page);
		return "pbs/activity/pbsActivitytypeList";
	}

	@RequiresPermissions("activity:pbsActivitytype:view")
	@RequestMapping(value = "form")
	public String form(PbsActivitytype pbsActivitytype, Model model) {
		model.addAttribute("pbsActivitytype", pbsActivitytype);
		return "pbs/activity/pbsActivitytypeForm";
	}

	@RequiresPermissions("activity:pbsActivitytype:edit")
	@RequestMapping(value = "save")
	public String save(PbsActivitytype pbsActivitytype, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsActivitytype)) {
			return form(pbsActivitytype, model);
		}
		// 判断当前的内容是否存在
		GeneralMethod generalMethod = new GeneralMethod();
		generalMethod.setTabletype(PBSACTIVITYTYPE);
		generalMethod.setId(pbsActivitytype.getId());
		generalMethod.setColumntype(TABLEKEY);
		generalMethod.setKey(pbsActivitytype.getSName());
		// 验证数据是否重复
		if (pbsGeneralService.checkExist(generalMethod)) {
			addMessage(model, "数据验证失败：该信息已经存在");
			return form(pbsActivitytype, model);
		}
		pbsActivitytypeService.save(pbsActivitytype);
		addMessage(redirectAttributes, "保存活动定义成功");
		return "redirect:" + Global.getAdminPath() + "/activity/pbsActivitytype/?repage";
	}

	@RequiresPermissions("activity:pbsActivitytype:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsActivitytype pbsActivitytype, RedirectAttributes redirectAttributes) {
		pbsActivitytypeService.delete(pbsActivitytype);
		addMessage(redirectAttributes, "删除活动定义成功");
		return "redirect:" + Global.getAdminPath() + "/activity/pbsActivitytype/?repage";
	}

	@RequestMapping(value = "namelist")
	public String namelist(PbsActivitytype pbsActivitytype, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsActivitytype> list = pbsActivitytypeService.findList(pbsActivitytype);
		model.addAttribute("list", list);
		return "mapping/ActivitytypeList";
	}
}