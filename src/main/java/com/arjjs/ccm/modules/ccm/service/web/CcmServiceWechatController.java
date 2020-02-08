/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.web;

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
import com.arjjs.ccm.modules.ccm.service.entity.CcmServiceWechat;
import com.arjjs.ccm.modules.ccm.service.service.CcmServiceWechatService;

/**
 * 公众信息上报Controller
 * @author fuxinshuang
 * @version 2018-03-17
 */
@Controller
@RequestMapping(value = "${adminPath}/service/ccmServiceWechat")
public class CcmServiceWechatController extends BaseController {

	@Autowired
	private CcmServiceWechatService ccmServiceWechatService;
	
	@ModelAttribute
	public CcmServiceWechat get(@RequestParam(required=false) String id) {
		CcmServiceWechat entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmServiceWechatService.get(id);
		}
		if (entity == null){
			entity = new CcmServiceWechat();
		}
		return entity;
	}
	
	@RequiresPermissions("service:ccmServiceWechat:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmServiceWechat ccmServiceWechat, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmServiceWechat> page = ccmServiceWechatService.findPage(new Page<CcmServiceWechat>(request, response), ccmServiceWechat); 
		model.addAttribute("page", page);
		return "ccm/service/ccmServiceWechatList";
	}

	@RequiresPermissions("service:ccmServiceWechat:view")
	@RequestMapping(value = "form")
	public String form(CcmServiceWechat ccmServiceWechat, Model model) {
		model.addAttribute("ccmServiceWechat", ccmServiceWechat);
		return "ccm/service/ccmServiceWechatForm";
	}

	@RequiresPermissions("service:ccmServiceWechat:edit")
	@RequestMapping(value = "save")
	public String save(CcmServiceWechat ccmServiceWechat, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmServiceWechat)){
			return form(ccmServiceWechat, model);
		}
		ccmServiceWechatService.save(ccmServiceWechat);
		addMessage(redirectAttributes, "保存公众信息上报成功");
		return "redirect:"+Global.getAdminPath()+"/service/ccmServiceWechat/?repage";
	}
	
	@RequiresPermissions("service:ccmServiceWechat:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmServiceWechat ccmServiceWechat, RedirectAttributes redirectAttributes) {
		ccmServiceWechatService.delete(ccmServiceWechat);
		addMessage(redirectAttributes, "删除公众信息上报成功");
		return "redirect:"+Global.getAdminPath()+"/service/ccmServiceWechat/?repage";
	}

}