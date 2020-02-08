/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.task.web;

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
import com.arjjs.ccm.modules.pbs.task.entity.PbsTaskoprec;
import com.arjjs.ccm.modules.pbs.task.service.PbsTaskoprecService;

/**
 * 工作安排操作记录Controller
 * @author lc
 * @version 2018-05-03
 */
@Controller
@RequestMapping(value = "${adminPath}/task/pbsTaskoprec")
public class PbsTaskoprecController extends BaseController {

	@Autowired
	private PbsTaskoprecService pbsTaskoprecService;
	
	@ModelAttribute
	public PbsTaskoprec get(@RequestParam(required=false) String id) {
		PbsTaskoprec entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsTaskoprecService.get(id);
		}
		if (entity == null){
			entity = new PbsTaskoprec();
		}
		return entity;
	}
	
	@RequiresPermissions("task:pbsTaskoprec:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsTaskoprec pbsTaskoprec, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsTaskoprec> page = pbsTaskoprecService.findPage(new Page<PbsTaskoprec>(request, response), pbsTaskoprec); 
		model.addAttribute("page", page);
		return "pbs/task/pbsTaskoprecList";
	}

	@RequiresPermissions("task:pbsTaskoprec:view")
	@RequestMapping(value = "form")
	public String form(PbsTaskoprec pbsTaskoprec, Model model) {
		model.addAttribute("pbsTaskoprec", pbsTaskoprec);
		return "pbs/task/pbsTaskoprecForm";
	}

	@RequiresPermissions("task:pbsTaskoprec:edit")
	@RequestMapping(value = "save")
	public String save(PbsTaskoprec pbsTaskoprec, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsTaskoprec)){
			return form(pbsTaskoprec, model);
		}
		if (pbsTaskoprecService.checkExist(pbsTaskoprec)) {
			addMessage(model, "数据验证失败：该用户已经处理过该信息");
			return form(pbsTaskoprec, model);
		}
		pbsTaskoprecService.save(pbsTaskoprec);
		addMessage(redirectAttributes, "保存工作安排操作记录成功");
		return "redirect:"+Global.getAdminPath()+"/task/pbsTaskoprec/?repage";
	}
	
	@RequiresPermissions("task:pbsTaskoprec:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsTaskoprec pbsTaskoprec, RedirectAttributes redirectAttributes) {
		pbsTaskoprecService.delete(pbsTaskoprec);
		addMessage(redirectAttributes, "删除工作安排操作记录成功");
		return "redirect:"+Global.getAdminPath()+"/task/pbsTaskoprec/?repage";
	}

}