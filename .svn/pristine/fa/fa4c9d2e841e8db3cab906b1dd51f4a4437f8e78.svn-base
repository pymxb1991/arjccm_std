/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.web;

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
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolPointsort;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolPointsortService;

/**
 * 巡逻点位顺序Controller
 * @author arj
 * @version 2018-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/patrol/ccmPatrolPointsort")
public class CcmPatrolPointsortController extends BaseController {

	@Autowired
	private CcmPatrolPointsortService ccmPatrolPointsortService;
	
	@ModelAttribute
	public CcmPatrolPointsort get(@RequestParam(required=false) String id) {
		CcmPatrolPointsort entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmPatrolPointsortService.get(id);
		}
		if (entity == null){
			entity = new CcmPatrolPointsort();
		}
		return entity;
	}
	
	@RequiresPermissions("patrol:ccmPatrolPointsort:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmPatrolPointsort ccmPatrolPointsort, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmPatrolPointsort> page = ccmPatrolPointsortService.findPage(new Page<CcmPatrolPointsort>(request, response), ccmPatrolPointsort); 
		model.addAttribute("page", page);
		return "ccm/patrol/ccmPatrolPointsortList";
	}

	@RequiresPermissions("patrol:ccmPatrolPointsort:view")
	@RequestMapping(value = "form")
	public String form(CcmPatrolPointsort ccmPatrolPointsort, Model model) {
		model.addAttribute("ccmPatrolPointsort", ccmPatrolPointsort);
		return "ccm/patrol/ccmPatrolPointsortForm";
	}

	@RequiresPermissions("patrol:ccmPatrolPointsort:edit")
	@RequestMapping(value = "save")
	public String save(CcmPatrolPointsort ccmPatrolPointsort, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmPatrolPointsort)){
			return form(ccmPatrolPointsort, model);
		}
		ccmPatrolPointsortService.save(ccmPatrolPointsort);
		addMessage(redirectAttributes, "保存巡逻点位顺序成功");
		return "redirect:"+Global.getAdminPath()+"/patrol/ccmPatrolPointsort/?repage";
	}
	
	@RequiresPermissions("patrol:ccmPatrolPointsort:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPatrolPointsort ccmPatrolPointsort, RedirectAttributes redirectAttributes) {
		ccmPatrolPointsortService.delete(ccmPatrolPointsort);
		addMessage(redirectAttributes, "删除巡逻点位顺序成功");
		return "redirect:"+Global.getAdminPath()+"/patrol/ccmPatrolPointsort/?repage";
	}

}