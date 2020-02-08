/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.publicity.web;

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
import com.arjjs.ccm.modules.ccm.publicity.entity.CcmLogPublicity;
import com.arjjs.ccm.modules.ccm.publicity.service.CcmLogPublicityService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

/**
 * 宣传信息实体类Controller
 * @author 刘永建
 * @version 2019-06-18
 */
@Controller
@RequestMapping(value = "${adminPath}/publicity/ccmLogPublicity")
public class CcmLogPublicityController extends BaseController {

	@Autowired
	private CcmLogPublicityService ccmLogPublicityService;
	
	@ModelAttribute
	public CcmLogPublicity get(@RequestParam(required=false) String id) {
		CcmLogPublicity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmLogPublicityService.get(id);
		}
		if (entity == null){
			entity = new CcmLogPublicity();
		}
		return entity;
	}
	
	@RequiresPermissions("publicity:ccmLogPublicity:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmLogPublicity ccmLogPublicity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmLogPublicity> page = ccmLogPublicityService.findPage(new Page<CcmLogPublicity>(request, response), ccmLogPublicity); 
		model.addAttribute("user", UserUtils.getUser());
		model.addAttribute("page", page);
		return "ccm/publicity/ccmLogPublicityList";
	}

	@RequiresPermissions("publicity:ccmLogPublicity:view")
	@RequestMapping(value = "form")
	public String form(CcmLogPublicity ccmLogPublicity, Model model) {
		model.addAttribute("user", UserUtils.getUser());
		model.addAttribute("ccmLogPublicity", ccmLogPublicity);
		return "ccm/publicity/ccmLogPublicityForm";
	}

	@RequiresPermissions("publicity:ccmLogPublicity:edit")
	@RequestMapping(value = "save")
	public String save(CcmLogPublicity ccmLogPublicity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmLogPublicity)){
			return form(ccmLogPublicity, model);
		}
		ccmLogPublicityService.save(ccmLogPublicity);
		addMessage(redirectAttributes, "保存宣传信息成功");
		return "redirect:"+Global.getAdminPath()+"/publicity/ccmLogPublicity/?repage";
	}
	
	@RequiresPermissions("publicity:ccmLogPublicity:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmLogPublicity ccmLogPublicity, RedirectAttributes redirectAttributes) {
		ccmLogPublicityService.delete(ccmLogPublicity);
		addMessage(redirectAttributes, "删除宣传信息成功");
		return "redirect:"+Global.getAdminPath()+"/publicity/ccmLogPublicity/?repage";
	}

}