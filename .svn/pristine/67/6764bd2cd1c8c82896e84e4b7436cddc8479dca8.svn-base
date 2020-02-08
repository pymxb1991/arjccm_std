/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkNotice;
import com.arjjs.ccm.modules.ccm.work.service.CcmWorkNoticeService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.RabbitMQTools;
import com.google.common.collect.Maps;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 公告Controller
 * @author liang
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/work/ccmWorkNotice")
public class CcmWorkNoticeController extends BaseController {

	@Autowired
	private CcmWorkNoticeService ccmWorkNoticeService;
	
	@ModelAttribute
	public CcmWorkNotice get(@RequestParam(required=false) String id) {
		CcmWorkNotice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmWorkNoticeService.get(id);
		}
		if (entity == null){
			entity = new CcmWorkNotice();
		}
		return entity;
	}
	
	@RequiresPermissions("work:ccmWorkNotice:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmWorkNotice ccmWorkNotice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmWorkNotice> page = ccmWorkNoticeService.findPage(new Page<CcmWorkNotice>(request, response), ccmWorkNotice);
		model.addAttribute("user", UserUtils.getUser());
		model.addAttribute("page", page);
		return "ccm/work/ccmWorkNoticeList";
	}

	@RequiresPermissions("work:ccmWorkNotice:view")
	@RequestMapping(value = "form")
	public String form(CcmWorkNotice ccmWorkNotice, Model model) {
		model.addAttribute("ccmWorkNotice", ccmWorkNotice);
		model.addAttribute("userSelfId", UserUtils.getUser());
		return "ccm/work/ccmWorkNoticeForm";
	}

	@RequiresPermissions("work:ccmWorkNotice:edit")
	@RequestMapping(value = "save")
	public String save(CcmWorkNotice ccmWorkNotice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmWorkNotice)){
			return form(ccmWorkNotice, model);
		}
		ccmWorkNoticeService.save(ccmWorkNotice);
		Map resmap = Maps.newHashMap();
		resmap.put("type","Notice");
		resmap.put("name",ccmWorkNotice.getTitle());
		resmap.put("id",ccmWorkNotice.getId());
		RabbitMQTools.sendMessageToAll(JSONObject.fromObject(resmap).toString());
		addMessage(redirectAttributes, "保存公告成功");
		return "redirect:"+Global.getAdminPath()+"/work/ccmWorkNotice/?repage";
	}
	
	@RequiresPermissions("work:ccmWorkNotice:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmWorkNotice ccmWorkNotice, RedirectAttributes redirectAttributes) {
		ccmWorkNoticeService.delete(ccmWorkNotice);
		addMessage(redirectAttributes, "删除公告成功");
		return "redirect:"+Global.getAdminPath()+"/work/ccmWorkNotice/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "messageList")
	public Page<CcmWorkNotice> messageList(CcmWorkNotice ccmWorkNotice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmWorkNotice> page = ccmWorkNoticeService.findPage(new Page<CcmWorkNotice>(request, response), ccmWorkNotice);
		return page;
	}

}