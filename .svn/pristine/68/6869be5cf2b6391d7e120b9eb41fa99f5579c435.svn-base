/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.score.web;

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
import com.arjjs.ccm.modules.kpi.score.entity.KpiSchemeJournal;
import com.arjjs.ccm.modules.kpi.score.service.KpiSchemeJournalService;

/**
 * 绩效流水Controller
 * @author liang
 * @version 2018-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/score/kpiSchemeJournal")
public class KpiSchemeJournalController extends BaseController {

	@Autowired
	private KpiSchemeJournalService kpiSchemeJournalService;
	
	@ModelAttribute
	public KpiSchemeJournal get(@RequestParam(required=false) String id) {
		KpiSchemeJournal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = kpiSchemeJournalService.get(id);
		}
		if (entity == null){
			entity = new KpiSchemeJournal();
		}
		return entity;
	}
	
	@RequiresPermissions("score:kpiSchemeJournal:view")
	@RequestMapping(value = {"list", ""})
	public String list(KpiSchemeJournal kpiSchemeJournal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<KpiSchemeJournal> page = kpiSchemeJournalService.findPage(new Page<KpiSchemeJournal>(request, response), kpiSchemeJournal); 
		model.addAttribute("page", page);
		return "kpi/score/kpiSchemeJournalList";
	}

	@RequiresPermissions("score:kpiSchemeJournal:view")
	@RequestMapping(value = "form")
	public String form(KpiSchemeJournal kpiSchemeJournal, Model model) {
		model.addAttribute("kpiSchemeJournal", kpiSchemeJournal);
		return "kpi/score/kpiSchemeJournalForm";
	}

	@RequiresPermissions("score:kpiSchemeJournal:edit")
	@RequestMapping(value = "save")
	public String save(KpiSchemeJournal kpiSchemeJournal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, kpiSchemeJournal)){
			return form(kpiSchemeJournal, model);
		}
		kpiSchemeJournalService.save(kpiSchemeJournal);
		addMessage(redirectAttributes, "保存绩效流水成功");
		return "redirect:"+Global.getAdminPath()+"/score/kpiSchemeJournal/?repage";
	}
	
	@RequiresPermissions("score:kpiSchemeJournal:edit")
	@RequestMapping(value = "delete")
	public String delete(KpiSchemeJournal kpiSchemeJournal, RedirectAttributes redirectAttributes) {
		kpiSchemeJournalService.delete(kpiSchemeJournal);
		addMessage(redirectAttributes, "删除绩效流水成功");
		return "redirect:"+Global.getAdminPath()+"/score/kpiSchemeJournal/?repage";
	}

}