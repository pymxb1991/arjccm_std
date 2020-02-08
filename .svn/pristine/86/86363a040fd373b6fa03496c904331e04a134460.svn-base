/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.citycomponents.web;

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
import com.arjjs.ccm.modules.ccm.citycomponents.entity.CcmLand;
import com.arjjs.ccm.modules.ccm.citycomponents.service.CcmLandService;

/**
 * 土地管理Controller
 * @author pengjianqiang
 * @version 2018-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/citycomponents/ccmLand")
public class CcmLandController extends BaseController {

	@Autowired
	private CcmLandService ccmLandService;
	
	@ModelAttribute
	public CcmLand get(@RequestParam(required=false) String id) {
		CcmLand entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmLandService.get(id);
		}
		if (entity == null){
			entity = new CcmLand();
		}
		return entity;
	}
	
	@RequiresPermissions("citycomponents:ccmLand:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmLand ccmLand, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmLand> page = ccmLandService.findPage(new Page<CcmLand>(request, response), ccmLand); 
		model.addAttribute("page", page);
		return "ccm/citycomponents/ccmLandList";
	}

	@RequiresPermissions("citycomponents:ccmLand:view")
	@RequestMapping(value = "form")
	public String form(CcmLand ccmLand, Model model) {
		model.addAttribute("ccmLand", ccmLand);
		return "ccm/citycomponents/ccmLandForm";
	}

	@RequiresPermissions("citycomponents:ccmLand:edit")
	@RequestMapping(value = "save")
	public String save(CcmLand ccmLand, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmLand)){
			return form(ccmLand, model);
		}
		ccmLandService.save(ccmLand);
		addMessage(redirectAttributes, "保存土地成功");
		return "redirect:"+Global.getAdminPath()+"/citycomponents/ccmLand/list?repage";
	}
	
	@RequiresPermissions("citycomponents:ccmLand:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmLand ccmLand, RedirectAttributes redirectAttributes) {
		ccmLandService.delete(ccmLand);
		addMessage(redirectAttributes, "删除土地成功");
		return "redirect:"+Global.getAdminPath()+"/citycomponents/ccmLand/list?repage";
	}

}