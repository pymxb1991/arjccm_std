/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.count.web;

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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.kpi.count.entity.CcmKpiCount;
import com.arjjs.ccm.modules.kpi.count.service.CcmKpiCountService;

/**
 * 绩效统计实体类Controller
 * 
 * @author lgh
 * @version 2019-07-17
 */
@Controller
@RequestMapping(value = "${adminPath}/count/ccmKpiCount")
public class CcmKpiCountController extends BaseController {

	@Autowired
	private CcmKpiCountService ccmKpiCountService;

	@ModelAttribute
	public CcmKpiCount get(@RequestParam(required = false) String id) {
		CcmKpiCount entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmKpiCountService.get(id);
		}
		if (entity == null) {
			entity = new CcmKpiCount();
		}
		return entity;
	}

	@RequiresPermissions("count:ccmKpiCount:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmKpiCount ccmKpiCount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmKpiCount> page = ccmKpiCountService.findPage(new Page<CcmKpiCount>(request, response), ccmKpiCount);
		model.addAttribute("page", page);
		return "kpi/count/ccmKpiCountList";
	}

	@RequiresPermissions("count:ccmKpiCount:view")
	@RequestMapping(value = "form")
	public String form(CcmKpiCount ccmKpiCount, Model model) {
		model.addAttribute("ccmKpiCount", ccmKpiCount);
		return "kpi/count/ccmKpiCountForm";
	}

	@RequiresPermissions("count:ccmKpiCount:edit")
	@RequestMapping(value = "save")
	public String save(CcmKpiCount ccmKpiCount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmKpiCount)) {
			return form(ccmKpiCount, model);
		}
		ccmKpiCountService.save(ccmKpiCount);
		addMessage(redirectAttributes, "保存绩效统计记录成功");
		return "redirect:" + Global.getAdminPath() + "/count/ccmKpiCount/?repage";
	}

	@RequiresPermissions("count:ccmKpiCount:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmKpiCount ccmKpiCount, RedirectAttributes redirectAttributes) {
		ccmKpiCountService.delete(ccmKpiCount);
		addMessage(redirectAttributes, "删除绩效统计记录成功");
		return "redirect:" + Global.getAdminPath() + "/count/ccmKpiCount/?repage";
	}

}