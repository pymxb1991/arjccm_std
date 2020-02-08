/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.statistics.web;

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
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsDict;
import com.arjjs.ccm.modules.plm.statistics.service.PlmStatisticsDictService;

/**
 * 统计首页字典Controller
 * @author liuxue
 * @version 2018-07-24
 */
@Controller
@RequestMapping(value = "${adminPath}/statistics/plmStatisticsDict")
public class PlmStatisticsDictController extends BaseController {

	@Autowired
	private PlmStatisticsDictService plmStatisticsDictService;
	
	@ModelAttribute
	public PlmStatisticsDict get(@RequestParam(required=false) String id) {
		PlmStatisticsDict entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmStatisticsDictService.get(id);
		}
		if (entity == null){
			entity = new PlmStatisticsDict();
		}
		return entity;
	}
	
	@RequiresPermissions("statistics:plmStatisticsDict:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmStatisticsDict plmStatisticsDict, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmStatisticsDict> page = plmStatisticsDictService.findPage(new Page<PlmStatisticsDict>(request, response), plmStatisticsDict); 
		model.addAttribute("page", page);
		return "plm/statistics/plmStatisticsDictList";
	}

	@RequiresPermissions("statistics:plmStatisticsDict:view")
	@RequestMapping(value = "form")
	public String form(PlmStatisticsDict plmStatisticsDict, Model model) {
		model.addAttribute("plmStatisticsDict", plmStatisticsDict);
		return "plm/statistics/plmStatisticsDictForm";
	}

	@RequiresPermissions("statistics:plmStatisticsDict:edit")
	@RequestMapping(value = "save")
	public String save(PlmStatisticsDict plmStatisticsDict, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmStatisticsDict)){
			return form(plmStatisticsDict, model);
		}
		plmStatisticsDictService.save(plmStatisticsDict);
		addMessage(redirectAttributes, "保存统计首页字典成功");
		return "redirect:"+Global.getAdminPath()+"/statistics/plmStatisticsDict/?repage";
	}
	
	@RequiresPermissions("statistics:plmStatisticsDict:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmStatisticsDict plmStatisticsDict, RedirectAttributes redirectAttributes) {
		plmStatisticsDictService.delete(plmStatisticsDict);
		addMessage(redirectAttributes, "删除统计首页字典成功");
		return "redirect:"+Global.getAdminPath()+"/statistics/plmStatisticsDict/?repage";
	}

}