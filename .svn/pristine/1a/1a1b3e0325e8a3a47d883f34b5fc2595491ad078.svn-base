/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.web;

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
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActinotifications;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActinotificationsService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

/**
 * 活动通知Controller
 * @author lc
 * @version 2018-05-14
 */
@Controller
@RequestMapping(value = "${adminPath}/activity/pbsActinotifications")
public class PbsActinotificationsController extends BaseController {

	@Autowired
	private PbsActinotificationsService pbsActinotificationsService;
	
	@ModelAttribute
	public PbsActinotifications get(@RequestParam(required=false) String id) {
		PbsActinotifications entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsActinotificationsService.get(id);
		}
		if (entity == null){
			entity = new PbsActinotifications();
		}
		return entity;
	}
	
	@RequiresPermissions("activity:pbsActinotifications:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsActinotifications pbsActinotifications, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 如果不是 超级用户则需特别查看
		if(!UserUtils.getUser().isAdmin()){
			pbsActinotifications.setsAcceptorid(UserUtils.getPartymem());
		}
		Page<PbsActinotifications> page = pbsActinotificationsService.findPage(new Page<PbsActinotifications>(request, response), pbsActinotifications); 
		model.addAttribute("page", page);
		return "pbs/activity/pbsActinotificationsList";
	}

	@RequiresPermissions("activity:pbsActinotifications:view")
	@RequestMapping(value = "form")
	public String form(PbsActinotifications pbsActinotifications, Model model) {
		// 设置为 以查看
		pbsActinotifications.setSStat("1");
		pbsActinotificationsService.save(pbsActinotifications);
		model.addAttribute("pbsActinotifications", pbsActinotifications);
		return "pbs/activity/pbsActinotificationsForm";
	}

	@RequiresPermissions("activity:pbsActinotifications:edit")
	@RequestMapping(value = "save")
	public String save(PbsActinotifications pbsActinotifications, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsActinotifications)){
			return form(pbsActinotifications, model);
		}
		pbsActinotificationsService.save(pbsActinotifications);
		addMessage(redirectAttributes, "保存活动通知成功");
		return "redirect:"+Global.getAdminPath()+"/activity/pbsActinotifications/?repage";
	}
	
	@RequiresPermissions("activity:pbsActinotifications:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsActinotifications pbsActinotifications, RedirectAttributes redirectAttributes) {
		pbsActinotificationsService.delete(pbsActinotifications);
		addMessage(redirectAttributes, "删除活动通知成功");
		return "redirect:"+Global.getAdminPath()+"/activity/pbsActinotifications/?repage";
	}

}