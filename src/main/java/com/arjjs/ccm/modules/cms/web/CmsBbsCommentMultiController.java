/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.cms.web;

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
import com.arjjs.ccm.modules.cms.entity.CmsBbsCommentMulti;
import com.arjjs.ccm.modules.cms.service.CmsBbsCommentMultiService;

/**
 * 网上论坛多级评论Controller
 * @author maoxb
 * @version 2019-08-01
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/cmsBbsCommentMulti")
public class CmsBbsCommentMultiController extends BaseController {

	@Autowired
	private CmsBbsCommentMultiService cmsBbsCommentMultiService;
	
	@ModelAttribute
	public CmsBbsCommentMulti get(@RequestParam(required=false) String id) {
		CmsBbsCommentMulti entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsBbsCommentMultiService.get(id);
		}
		if (entity == null){
			entity = new CmsBbsCommentMulti();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsBbsCommentMulti:view")
	@RequestMapping(value = {"list", ""})
	public String list(CmsBbsCommentMulti cmsBbsCommentMulti, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsBbsCommentMulti> page = cmsBbsCommentMultiService.findPage(new Page<CmsBbsCommentMulti>(request, response), cmsBbsCommentMulti); 
		model.addAttribute("page", page);
		return "modules/cms/cmsBbsCommentMultiList";
	}

	@RequiresPermissions("cms:cmsBbsCommentMulti:view")
	@RequestMapping(value = "form")
	public String form(CmsBbsCommentMulti cmsBbsCommentMulti, Model model) {
		model.addAttribute("cmsBbsCommentMulti", cmsBbsCommentMulti);
		return "modules/cms/cmsBbsCommentMultiForm";
	}

	@RequiresPermissions("cms:cmsBbsCommentMulti:edit")
	@RequestMapping(value = "save")
	public String save(CmsBbsCommentMulti cmsBbsCommentMulti, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsBbsCommentMulti)){
			return form(cmsBbsCommentMulti, model);
		}
		cmsBbsCommentMultiService.save(cmsBbsCommentMulti);
		addMessage(redirectAttributes, "保存网上论坛多级评论成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsBbsCommentMulti/?repage";
	}
	
	@RequiresPermissions("cms:cmsBbsCommentMulti:edit")
	@RequestMapping(value = "delete")
	public String delete(CmsBbsCommentMulti cmsBbsCommentMulti, RedirectAttributes redirectAttributes) {
		cmsBbsCommentMultiService.delete(cmsBbsCommentMulti);
		addMessage(redirectAttributes, "删除网上论坛多级评论成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsBbsCommentMulti/?repage";
	}

}