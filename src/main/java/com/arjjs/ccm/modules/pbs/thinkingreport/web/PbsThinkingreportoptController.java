/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.thinkingreport.web;

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
import com.arjjs.ccm.modules.pbs.thinkingreport.entity.PbsThinkingreportopt;
import com.arjjs.ccm.modules.pbs.thinkingreport.service.PbsThinkingreportoptService;

/**
 * 思想汇报操作Controller
 * @author lc
 * @version 2018-05-28
 */
@Controller
@RequestMapping(value = "${adminPath}/thinkingreport/pbsThinkingreportopt")
public class PbsThinkingreportoptController extends BaseController {

	@Autowired
	private PbsThinkingreportoptService pbsThinkingreportoptService;
	
	@ModelAttribute
	public PbsThinkingreportopt get(@RequestParam(required=false) String id) {
		PbsThinkingreportopt entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsThinkingreportoptService.get(id);
		}
		if (entity == null){
			entity = new PbsThinkingreportopt();
		}
		return entity;
	}
	
	@RequiresPermissions("thinkingreport:pbsThinkingreportopt:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsThinkingreportopt pbsThinkingreportopt, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsThinkingreportopt> page = pbsThinkingreportoptService.findPage(new Page<PbsThinkingreportopt>(request, response), pbsThinkingreportopt); 
		model.addAttribute("page", page);
		return "pbs/thinkingreport/pbsThinkingreportoptList";
	}

	@RequiresPermissions("thinkingreport:pbsThinkingreportopt:view")
	@RequestMapping(value = "form")
	public String form(PbsThinkingreportopt pbsThinkingreportopt, Model model) {
		model.addAttribute("pbsThinkingreportopt", pbsThinkingreportopt);
		return "pbs/thinkingreport/pbsThinkingreportoptForm";
	}

	@RequiresPermissions("thinkingreport:pbsThinkingreportopt:edit")
	@RequestMapping(value = "save")
	public String save(PbsThinkingreportopt pbsThinkingreportopt, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsThinkingreportopt)){
			return form(pbsThinkingreportopt, model);
		}
		pbsThinkingreportoptService.save(pbsThinkingreportopt);
		addMessage(redirectAttributes, "保存思想汇报操作成功");
		return "redirect:"+Global.getAdminPath()+"/thinkingreport/pbsThinkingreportopt/?repage";
	}
	
	@RequiresPermissions("thinkingreport:pbsThinkingreportopt:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsThinkingreportopt pbsThinkingreportopt, RedirectAttributes redirectAttributes) {
		pbsThinkingreportoptService.delete(pbsThinkingreportopt);
		addMessage(redirectAttributes, "删除思想汇报操作成功");
		return "redirect:"+Global.getAdminPath()+"/thinkingreport/pbsThinkingreportopt/?repage";
	}

}