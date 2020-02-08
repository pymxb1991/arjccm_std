/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.pop.web;

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
import com.arjjs.ccm.modules.ccm.pop.entity.CcmRoadAddress;
import com.arjjs.ccm.modules.ccm.pop.service.CcmRoadAddressService;

/**
 * 街路巷Controller
 * @author liujindan
 * @version 2019-02-25
 */
@Controller
@RequestMapping(value = "${adminPath}/pop/ccmRoadAddress")
public class CcmRoadAddressController extends BaseController {

	@Autowired
	private CcmRoadAddressService ccmRoadAddressService;
	
	@ModelAttribute
	public CcmRoadAddress get(@RequestParam(required=false) String id) {
		CcmRoadAddress entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmRoadAddressService.get(id);
		}
		if (entity == null){
			entity = new CcmRoadAddress();
		}
		return entity;
	}
	
	@RequiresPermissions("pop:ccmRoadAddress:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmRoadAddress ccmRoadAddress, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmRoadAddress> page = ccmRoadAddressService.findPage(new Page<CcmRoadAddress>(request, response), ccmRoadAddress); 
		model.addAttribute("page", page);
		return "ccm/pop/ccmRoadAddressList";
	}

	@RequiresPermissions("pop:ccmRoadAddress:view")
	@RequestMapping(value = "form")
	public String form(CcmRoadAddress ccmRoadAddress, Model model) {
		model.addAttribute("ccmRoadAddress", ccmRoadAddress);
		return "ccm/pop/ccmRoadAddressForm";
	}

	@RequiresPermissions("pop:ccmRoadAddress:edit")
	@RequestMapping(value = "save")
	public String save(CcmRoadAddress ccmRoadAddress, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmRoadAddress)){
			return form(ccmRoadAddress, model);
		}
		ccmRoadAddressService.save(ccmRoadAddress);
		addMessage(redirectAttributes, "保存街路巷成功");
		return "redirect:"+Global.getAdminPath()+"/pop/ccmRoadAddress/?repage";
	}
	
	@RequiresPermissions("pop:ccmRoadAddress:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmRoadAddress ccmRoadAddress, RedirectAttributes redirectAttributes) {
		ccmRoadAddressService.delete(ccmRoadAddress);
		addMessage(redirectAttributes, "删除街路巷成功");
		return "redirect:"+Global.getAdminPath()+"/pop/ccmRoadAddress/?repage";
	}

}