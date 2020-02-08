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
import com.arjjs.ccm.modules.cms.entity.CmsBbsComment;
import com.arjjs.ccm.modules.cms.service.CmsBbsCommentService;

/**
 * 网上论坛一级评论Controller
 * @author maoxb
 * @version 2019-08-01
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/cmsBbsComment")
public class CmsBbsCommentController extends BaseController {

	@Autowired
	private CmsBbsCommentService cmsBbsCommentService;
	
	@ModelAttribute
	public CmsBbsComment get(@RequestParam(required=false) String id) {
		CmsBbsComment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsBbsCommentService.get(id);
		}
		if (entity == null){
			entity = new CmsBbsComment();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsBbsComment:view")
	@RequestMapping(value = {"list", ""})
	public String list(CmsBbsComment cmsBbsComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsBbsComment> page = cmsBbsCommentService.findPage(new Page<CmsBbsComment>(request, response), cmsBbsComment); 
		model.addAttribute("page", page);
		return "modules/cms/cmsBbsCommentList";
	}

	@RequiresPermissions("cms:cmsBbsComment:view")
	@RequestMapping(value = "form")
	public String form(CmsBbsComment cmsBbsComment, Model model) {
		model.addAttribute("cmsBbsComment", cmsBbsComment);
		return "modules/cms/cmsBbsCommentForm";
	}

	@RequiresPermissions("cms:cmsBbsComment:edit")
	@RequestMapping(value = "save")
	public String save(CmsBbsComment cmsBbsComment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsBbsComment)){
			return form(cmsBbsComment, model);
		}
		cmsBbsCommentService.save(cmsBbsComment);
		addMessage(redirectAttributes, "保存网上论坛一级评论成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsBbsComment/?repage";
	}
	
	@RequiresPermissions("cms:cmsBbsComment:edit")
	@RequestMapping(value = "delete")
	public String delete(CmsBbsComment cmsBbsComment, RedirectAttributes redirectAttributes) {
		cmsBbsCommentService.delete(cmsBbsComment);
		addMessage(redirectAttributes, "删除网上论坛一级评论成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsBbsComment/?repage";
	}

}