/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.web;

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
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityleave;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivityleaveService;

/**
 * 活动请假Controller
 * @author lc
 * @version 2018-05-15
 */
@Controller
@RequestMapping(value = "${adminPath}/activity/pbsActivityleave")
public class PbsActivityleaveController extends BaseController {

	@Autowired
	private PbsActivityleaveService pbsActivityleaveService;
	
	@ModelAttribute
	public PbsActivityleave get(@RequestParam(required=false) String id) {
		PbsActivityleave entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsActivityleaveService.get(id);
		}
		if (entity == null){
			entity = new PbsActivityleave();
		}
		return entity;
	}
	
	@RequiresPermissions("activity:pbsActivityleave:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsActivityleave pbsActivityleave, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsActivityleave> page = pbsActivityleaveService.findPage(new Page<PbsActivityleave>(request, response), pbsActivityleave); 
		model.addAttribute("page", page);
		return "pbs/activity/pbsActivityleaveList";
	}

	@RequiresPermissions("activity:pbsActivityleave:view")
	@RequestMapping(value = "form")
	public String form(PbsActivityleave pbsActivityleave, Model model) {
		model.addAttribute("pbsActivityleave", pbsActivityleave);
		return "pbs/activity/pbsActivityleaveForm";
	}

	@RequiresPermissions("activity:pbsActivityleave:edit")
	@RequestMapping(value = "save")
	public String save(PbsActivityleave pbsActivityleave, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsActivityleave)){
			return form(pbsActivityleave, model);
		}
		pbsActivityleaveService.save(pbsActivityleave);
		addMessage(redirectAttributes, "保存活动请假成功");
		return "redirect:"+Global.getAdminPath()+"/activity/pbsActivityleave/?repage";
	}
	
	@RequiresPermissions("activity:pbsActivityleave:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsActivityleave pbsActivityleave, RedirectAttributes redirectAttributes) {
		pbsActivityleaveService.delete(pbsActivityleave);
		addMessage(redirectAttributes, "删除活动请假成功");
		return "redirect:"+Global.getAdminPath()+"/activity/pbsActivityleave/?repage";
	}

}