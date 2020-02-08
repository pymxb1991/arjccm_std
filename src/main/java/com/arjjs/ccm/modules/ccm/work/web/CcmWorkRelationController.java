/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.web;

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
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkRelation;
import com.arjjs.ccm.modules.ccm.work.service.CcmWorkRelationService;

/**
 * 联系人Controller
 * @author liang
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/work/ccmWorkRelation")
public class CcmWorkRelationController extends BaseController {

	@Autowired
	private CcmWorkRelationService ccmWorkRelationService;
	
	@ModelAttribute
	public CcmWorkRelation get(@RequestParam(required=false) String id) {
		CcmWorkRelation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmWorkRelationService.get(id);
		}
		if (entity == null){
			entity = new CcmWorkRelation();
		}
		return entity;
	}
	
	@RequiresPermissions("work:ccmWorkRelation:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmWorkRelation ccmWorkRelation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmWorkRelation> page = ccmWorkRelationService.findPage(new Page<CcmWorkRelation>(request, response), ccmWorkRelation); 
		model.addAttribute("page", page);
		return "ccm/work/ccmWorkRelationList";
	}

	@RequiresPermissions("work:ccmWorkRelation:view")
	@RequestMapping(value = "form")
	public String form(CcmWorkRelation ccmWorkRelation, Model model) {
		model.addAttribute("ccmWorkRelation", ccmWorkRelation);
		return "ccm/work/ccmWorkRelationForm";
	}

	@RequiresPermissions("work:ccmWorkRelation:edit")
	@RequestMapping(value = "save")
	public String save(CcmWorkRelation ccmWorkRelation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmWorkRelation)){
			return form(ccmWorkRelation, model);
		}
		ccmWorkRelationService.save(ccmWorkRelation);
		addMessage(redirectAttributes, "保存联系人成功");
		return "redirect:"+Global.getAdminPath()+"/work/ccmWorkRelation/?repage";
	}
	
	@RequiresPermissions("work:ccmWorkRelation:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmWorkRelation ccmWorkRelation, RedirectAttributes redirectAttributes) {
		ccmWorkRelationService.delete(ccmWorkRelation);
		addMessage(redirectAttributes, "删除联系人成功");
		return "redirect:"+Global.getAdminPath()+"/work/ccmWorkRelation/?repage";
	}

}