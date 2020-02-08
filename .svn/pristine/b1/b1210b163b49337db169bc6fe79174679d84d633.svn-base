/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.act.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.act.entity.ActUtConfig;
import com.arjjs.ccm.modules.act.service.ActUtConfigService;
import com.arjjs.ccm.modules.gen.entity.GenTable;
import com.arjjs.ccm.modules.gen.service.GenTableService;
import com.arjjs.ccm.tool.Select2Type;

/**
 * 流程配置Controller
 * @author dongqikai
 * @version 2018-07-16
 */
@Controller
@RequestMapping(value = "${adminPath}/act/actUtConfig")
public class ActUtConfigController extends BaseController {

	@Autowired
	private ActUtConfigService<?> actUtConfigService;
	
	@Autowired
	private GenTableService genTableService;
	
	@ModelAttribute
	public ActUtConfig get(@RequestParam(required=false) String id) {
		ActUtConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = actUtConfigService.get(id);
		}
		if (entity == null){
			entity = new ActUtConfig();
		}
		return entity;
	}
	
	@RequiresPermissions("act:actUtConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(ActUtConfig actUtConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ActUtConfig> page = actUtConfigService.findPage(new Page<ActUtConfig>(request, response), actUtConfig); 
		model.addAttribute("page", page);
		return "modules/act/actUtConfigList";
	}

	@RequiresPermissions("act:actUtConfig:view")
	@RequestMapping(value = "form")
	public String form(ActUtConfig actUtConfig, Model model) {
		List<GenTable> tableList = genTableService.findTableListFormDb(new GenTable());//获取数据库表
		model.addAttribute("tableList", tableList);
		model.addAttribute("actUtConfig", actUtConfig);
		return "modules/act/actUtConfigForm";
	}

	@RequiresPermissions("act:actUtConfig:edit")
	@RequestMapping(value = "save")
	public String save(ActUtConfig actUtConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, actUtConfig)){
			return form(actUtConfig, model);
		}
		actUtConfigService.save(actUtConfig);
		addMessage(redirectAttributes, "保存配置成功");
		return "redirect:"+Global.getAdminPath()+"/act/actUtConfig/?repage";
	}
	
	@RequiresPermissions("act:actUtConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(ActUtConfig actUtConfig, RedirectAttributes redirectAttributes) {
		actUtConfigService.delete(actUtConfig);
		addMessage(redirectAttributes, "删除配置成功");
		return "redirect:"+Global.getAdminPath()+"/act/actUtConfig/?repage";
	}
	
	/**
	 * 根据所选表信息id获取对应表的字段（已过滤部分字段）
	 * @param tableId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="getSelectList")
	@ResponseBody
	public List<Select2Type> getSelectList(@RequestParam(value="tableId") String tableId, Model model) {
		return actUtConfigService.getSelectList(tableId);
	}

}