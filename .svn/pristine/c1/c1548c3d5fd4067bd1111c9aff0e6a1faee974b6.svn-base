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
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkAdvise;
import com.arjjs.ccm.modules.ccm.work.service.CcmWorkAdviseService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

/**
 * 意见建议Controller
 * @author liang
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/work/ccmWorkAdvise")
public class CcmWorkAdviseController extends BaseController {

	@Autowired
	private CcmWorkAdviseService ccmWorkAdviseService;
	
	@ModelAttribute
	public CcmWorkAdvise get(@RequestParam(required=false) String id) {
		CcmWorkAdvise entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmWorkAdviseService.get(id);
		}
		if (entity == null){
			entity = new CcmWorkAdvise();
		}
		return entity;
	}
	
	@RequiresPermissions("work:ccmWorkAdvise:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmWorkAdvise ccmWorkAdvise, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmWorkAdvise> page = ccmWorkAdviseService.findPage(new Page<CcmWorkAdvise>(request, response), ccmWorkAdvise); 
		model.addAttribute("page", page);
		return "ccm/work/ccmWorkAdviseList";
	}

	@RequiresPermissions("work:ccmWorkAdvise:view")
	@RequestMapping(value = "form")
	public String form(CcmWorkAdvise ccmWorkAdvise, Model model) {
		model.addAttribute("ccmWorkAdvise", ccmWorkAdvise);
		model.addAttribute("userSelfId", UserUtils.getUser());
		return "ccm/work/ccmWorkAdviseForm";
	}

	@RequiresPermissions("work:ccmWorkAdvise:edit")
	@RequestMapping(value = "save")
	public String save(CcmWorkAdvise ccmWorkAdvise, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmWorkAdvise)){
			return form(ccmWorkAdvise, model);
		}
		ccmWorkAdviseService.save(ccmWorkAdvise);
		addMessage(redirectAttributes, "保存意见建议成功");
		return "redirect:"+Global.getAdminPath()+"/work/ccmWorkAdvise/?repage";
	}
	
	@RequiresPermissions("work:ccmWorkAdvise:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmWorkAdvise ccmWorkAdvise, RedirectAttributes redirectAttributes) {
		ccmWorkAdviseService.delete(ccmWorkAdvise);
		addMessage(redirectAttributes, "删除意见建议成功");
		return "redirect:"+Global.getAdminPath()+"/work/ccmWorkAdvise/?repage";
	}

}