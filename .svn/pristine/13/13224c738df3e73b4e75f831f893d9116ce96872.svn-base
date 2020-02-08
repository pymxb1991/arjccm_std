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
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityecomment;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivityecommentService;

/**
 * 活动评论信息Controller
 * @author lc
 * @version 2018-07-10
 */
@Controller
@RequestMapping(value = "${adminPath}/activity/pbsActivityecomment")
public class PbsActivityecommentController extends BaseController {

	@Autowired
	private PbsActivityecommentService pbsActivityecommentService;
	
	@ModelAttribute
	public PbsActivityecomment get(@RequestParam(required=false) String id) {
		PbsActivityecomment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsActivityecommentService.get(id);
		}
		if (entity == null){
			entity = new PbsActivityecomment();
		}
		return entity;
	}
	
	@RequiresPermissions("activity:pbsActivityecomment:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsActivityecomment pbsActivityecomment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsActivityecomment> page = pbsActivityecommentService.findPage(new Page<PbsActivityecomment>(request, response), pbsActivityecomment); 
		model.addAttribute("page", page);
		return "pbs/activity/pbsActivityecommentList";
	}

	@RequiresPermissions("activity:pbsActivityecomment:view")
	@RequestMapping(value = "form")
	public String form(PbsActivityecomment pbsActivityecomment, Model model) {
		model.addAttribute("pbsActivityecomment", pbsActivityecomment);
		return "pbs/activity/pbsActivityecommentForm";
	}

	@RequiresPermissions("activity:pbsActivityecomment:edit")
	@RequestMapping(value = "save")
	public String save(PbsActivityecomment pbsActivityecomment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsActivityecomment)){
			return form(pbsActivityecomment, model);
		}
		pbsActivityecommentService.save(pbsActivityecomment);
		addMessage(redirectAttributes, "保存活动评论信息成功");
		return "redirect:"+Global.getAdminPath()+"/activity/pbsActivityecomment/?repage";
	}
	
	@RequiresPermissions("activity:pbsActivityecomment:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsActivityecomment pbsActivityecomment, RedirectAttributes redirectAttributes) {
		pbsActivityecommentService.delete(pbsActivityecomment);
		addMessage(redirectAttributes, "删除活动评论信息成功");
		return "redirect:"+Global.getAdminPath()+"/activity/pbsActivityecomment/?repage";
	}

}