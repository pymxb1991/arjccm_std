/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.web.wechat;

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
import com.arjjs.ccm.modules.ccm.event.entity.wechat.CcmWechatEventAttachment;
import com.arjjs.ccm.modules.ccm.event.service.wechat.CcmWechatEventAttachmentService;

/**
 * 微信信息上报附件Controller
 * @author fu
 * @version 2018-04-14
 */
@Controller
@RequestMapping(value = "${adminPath}/event/wechat/ccmWechatEventAttachment")
public class CcmWechatEventAttachmentController extends BaseController {

	@Autowired
	private CcmWechatEventAttachmentService ccmWechatEventAttachmentService;
	
	@ModelAttribute
	public CcmWechatEventAttachment get(@RequestParam(required=false) String id) {
		CcmWechatEventAttachment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmWechatEventAttachmentService.get(id);
		}
		if (entity == null){
			entity = new CcmWechatEventAttachment();
		}
		return entity;
	}
	
	@RequiresPermissions("event:wechat:ccmWechatEventAttachment:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmWechatEventAttachment ccmWechatEventAttachment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmWechatEventAttachment> page = ccmWechatEventAttachmentService.findPage(new Page<CcmWechatEventAttachment>(request, response), ccmWechatEventAttachment); 
		model.addAttribute("page", page);
		return "ccm/event/wechat/ccmWechatEventAttachmentList";
	}

	@RequiresPermissions("event:wechat:ccmWechatEventAttachment:view")
	@RequestMapping(value = "form")
	public String form(CcmWechatEventAttachment ccmWechatEventAttachment, Model model) {
		model.addAttribute("ccmWechatEventAttachment", ccmWechatEventAttachment);
		return "ccm/event/wechat/ccmWechatEventAttachmentForm";
	}

	@RequiresPermissions("event:wechat:ccmWechatEventAttachment:edit")
	@RequestMapping(value = "save")
	public String save(CcmWechatEventAttachment ccmWechatEventAttachment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmWechatEventAttachment)){
			return form(ccmWechatEventAttachment, model);
		}
		ccmWechatEventAttachmentService.save(ccmWechatEventAttachment);
		addMessage(redirectAttributes, "保存微信信息上报附件成功");
		return "redirect:"+Global.getAdminPath()+"/event/wechat/ccmWechatEventAttachment/?repage";
	}
	
	@RequiresPermissions("event:wechat:ccmWechatEventAttachment:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmWechatEventAttachment ccmWechatEventAttachment, RedirectAttributes redirectAttributes) {
		ccmWechatEventAttachmentService.delete(ccmWechatEventAttachment);
		addMessage(redirectAttributes, "删除微信信息上报附件成功");
		return "redirect:"+Global.getAdminPath()+"/event/wechat/ccmWechatEventAttachment/?repage";
	}

}