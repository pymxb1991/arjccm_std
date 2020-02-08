/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.web;

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
import com.arjjs.ccm.modules.ccm.org.entity.SysArea;
import com.arjjs.ccm.modules.ccm.org.service.SysAreaService;

/**
 * 区域扩展表（区域查询）Controller
 * @author pengjianqiang
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/org/sysArea")
public class SysAreaController extends BaseController {

	@Autowired
	private SysAreaService sysAreaService;
	
	@ModelAttribute
	public SysArea get(@RequestParam(required=false) String id) {
		SysArea entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysAreaService.get(id);
		}
		if (entity == null){
			entity = new SysArea();
		}
		return entity;
	}
	
	@RequiresPermissions("org:sysArea:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysArea sysArea, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysArea> page = sysAreaService.findPage(new Page<SysArea>(request, response), sysArea); 
		model.addAttribute("page", page);
		model.addAttribute("type", sysArea.getType());
		return "ccm/org/sysAreaList";
	}

	@RequiresPermissions("org:sysArea:view")
	@RequestMapping(value = "form")
	public String form(SysArea sysArea, Model model) {
		model.addAttribute("sysArea", sysArea);
		return "ccm/org/sysAreaForm";
	}

	@RequiresPermissions("org:sysArea:edit")
	@RequestMapping(value = "save")
	public String save(SysArea sysArea, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysArea)){
			return form(sysArea, model);
		}
		sysAreaService.save(sysArea);
		addMessage(redirectAttributes, "保存区域成功");
		return "redirect:"+Global.getAdminPath()+"/org/sysArea/?repage";
	}
	
	@RequiresPermissions("org:sysArea:edit")
	@RequestMapping(value = "delete")
	public String delete(SysArea sysArea, RedirectAttributes redirectAttributes) {
		sysAreaService.delete(sysArea);
		addMessage(redirectAttributes, "删除区域成功");
		return "redirect:"+Global.getAdminPath()+"/org/sysArea/?repage";
	}

}