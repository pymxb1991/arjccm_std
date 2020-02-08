/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.email.web;

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
import com.arjjs.ccm.modules.plm.email.entity.PlmWorkEmailReply;
import com.arjjs.ccm.modules.plm.email.service.PlmWorkEmailReplyService;

/**
 * 内部邮件回复Controller
 * @author fu
 * @version 2018-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/email/plmWorkEmailReply")
public class PlmWorkEmailReplyController extends BaseController {

	@Autowired
	private PlmWorkEmailReplyService plmWorkEmailReplyService;
	
	@ModelAttribute
	public PlmWorkEmailReply get(@RequestParam(required=false) String id) {
		PlmWorkEmailReply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmWorkEmailReplyService.get(id);
		}
		if (entity == null){
			entity = new PlmWorkEmailReply();
		}
		return entity;
	}
	
//	@RequiresPermissions("email:plmWorkEmailReply:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmWorkEmailReply plmWorkEmailReply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmWorkEmailReply> page = plmWorkEmailReplyService.findPage(new Page<PlmWorkEmailReply>(request, response), plmWorkEmailReply); 
		model.addAttribute("page", page);
		return "plm/email/plmWorkEmailReplyList";
	}

//	@RequiresPermissions("email:plmWorkEmailReply:view")
	@RequestMapping(value = "form")
	public String form(PlmWorkEmailReply plmWorkEmailReply, Model model) {
		model.addAttribute("plmWorkEmailReply", plmWorkEmailReply);
		if(StringUtils.isBlank(plmWorkEmailReply.getId())){
			return "plm/email/plmWorkEmailReplyForm";
		}else{
			return "plm/email/plmWorkEmailReplyView";
		}
		
	}

//	@RequiresPermissions("email:plmWorkEmailReply:edit")
	@RequestMapping(value = "save")
	public String save(PlmWorkEmailReply plmWorkEmailReply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmWorkEmailReply)){
			return form(plmWorkEmailReply, model);
		}
		plmWorkEmailReplyService.save(plmWorkEmailReply);
		addMessage(redirectAttributes, "保存回复成功");
		return "redirect:"+Global.getAdminPath()+"/email/plmWorkEmail/form?id="+plmWorkEmailReply.getWorkEmailId();
	}
	
	@RequiresPermissions("email:plmWorkEmailReply:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmWorkEmailReply plmWorkEmailReply, RedirectAttributes redirectAttributes) {
		plmWorkEmailReplyService.delete(plmWorkEmailReply);
		addMessage(redirectAttributes, "删除内部邮件回复成功");
		return "redirect:"+Global.getAdminPath()+"/email/plmWorkEmailReply/?repage";
	}

}