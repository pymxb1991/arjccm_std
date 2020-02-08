/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.news.web;

import java.util.List;

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
import com.arjjs.ccm.modules.ccm.news.entity.Message;
import com.arjjs.ccm.modules.ccm.news.service.MessageService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

/**
 * 消息管理Controller
 * @author cby
 * @version 2019-11-28
 */
@Controller
@RequestMapping(value = "${adminPath}/news/message")
public class MessageController extends BaseController {

	@Autowired
	private MessageService messageService;
	
	@ModelAttribute
	public Message get(@RequestParam(required=false) String id) {
		Message entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = messageService.get(id);
		}
		if (entity == null){
			entity = new Message();
		}
		return entity;
	}
	
	@RequiresPermissions("news:message:view")
	@RequestMapping(value = {"list", ""})
	public String list(Message message, HttpServletRequest request, HttpServletResponse response, Model model) {
		message.setUser(UserUtils.getUser());
		Page<Message> page = messageService.findPage(new Page<Message>(request, response), message); 
		List<Message> list = page.getList();
		for(int i = 0; i<list.size() ; i++) {
			if("04".equals(list.get(i).getType())) {
				list.get(i).setType("02");
			}
			if("13".equals(list.get(i).getType())) {
				list.get(i).setType("03");
			}
			if("23".equals(list.get(i).getType())) {
				list.get(i).setType("03");
			}
			if("33".equals(list.get(i).getType())) {
				list.get(i).setType("03");
			}
		}
		model.addAttribute("page", page);
		return "ccm/news/messageList";
	}

	@RequiresPermissions("news:message:view")
	@RequestMapping(value = "form")
	public String form(Message message, Model model) {
		model.addAttribute("message", message);
		return "ccm/news/messageForm";
	}

	@RequiresPermissions("news:message:edit")
	@RequestMapping(value = "save")
	public String save(Message message, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, message)){
			return form(message, model);
		}
		messageService.save(message);
		addMessage(redirectAttributes, "保存消息管理成功");
		return "redirect:"+Global.getAdminPath()+"/news/message/?repage";
	}
	
	@RequiresPermissions("news:message:edit")
	@RequestMapping(value = "delete")
	public String delete(Message message, RedirectAttributes redirectAttributes) {
		messageService.delete(message);
		addMessage(redirectAttributes, "删除消息管理成功");
		return "redirect:"+Global.getAdminPath()+"/news/message/?repage";
	}

}