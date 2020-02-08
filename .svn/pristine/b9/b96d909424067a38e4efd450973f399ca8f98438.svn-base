/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.log.web;

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
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogImpPopSign;
import com.arjjs.ccm.modules.ccm.log.service.CcmLogImpPopSignService;

/**
 * 重点人员签到记录Controller
 * @author pengjianqiang
 * @version 2019-03-05
 */
@Controller
@RequestMapping(value = "${adminPath}/log/ccmLogImpPopSign")
public class CcmLogImpPopSignController extends BaseController {

	@Autowired
	private CcmLogImpPopSignService ccmLogImpPopSignService;
	
	@ModelAttribute
	public CcmLogImpPopSign get(@RequestParam(required=false) String id) {
		CcmLogImpPopSign entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmLogImpPopSignService.get(id);
		}
		if (entity == null){
			entity = new CcmLogImpPopSign();
		}
		return entity;
	}
	
	@RequiresPermissions("log:ccmLogImpPopSign:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmLogImpPopSign ccmLogImpPopSign, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmLogImpPopSign> page = ccmLogImpPopSignService.findPage(new Page<CcmLogImpPopSign>(request, response), ccmLogImpPopSign); 
		model.addAttribute("page", page);
		return "ccm/log/ccmLogImpPopSignList";
	}

	@RequiresPermissions("log:ccmLogImpPopSign:view")
	@RequestMapping(value = "form")
	public String form(CcmLogImpPopSign ccmLogImpPopSign, Model model) {
		model.addAttribute("ccmLogImpPopSign", ccmLogImpPopSign);
		return "ccm/log/ccmLogImpPopSignForm";
	}

	@RequiresPermissions("log:ccmLogImpPopSign:edit")
	@RequestMapping(value = "save")
	public String save(CcmLogImpPopSign ccmLogImpPopSign, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmLogImpPopSign)){
			return form(ccmLogImpPopSign, model);
		}
		ccmLogImpPopSignService.save(ccmLogImpPopSign);
		addMessage(redirectAttributes, "保存重点人员签到记录成功");
		return "redirect:"+Global.getAdminPath()+"/log/ccmLogImpPopSign/?repage";
	}
	
	@RequiresPermissions("log:ccmLogImpPopSign:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmLogImpPopSign ccmLogImpPopSign, RedirectAttributes redirectAttributes) {
		ccmLogImpPopSignService.delete(ccmLogImpPopSign);
		addMessage(redirectAttributes, "删除重点人员签到记录成功");
		return "redirect:"+Global.getAdminPath()+"/log/ccmLogImpPopSign/?repage";
	}

}