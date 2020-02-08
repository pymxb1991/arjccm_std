/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.files.web;

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
import com.arjjs.ccm.modules.plm.files.entity.PlmPublicFiles;
import com.arjjs.ccm.modules.plm.files.service.PlmPublicFilesService;

/**
 * 附件Controller
 * @author dongqikai
 * @version 2018-06-22
 */
@Controller
@RequestMapping(value = "${adminPath}/files/plmPublicFiles")
public class PlmPublicFilesController extends BaseController {

	@Autowired
	private PlmPublicFilesService plmPublicFilesService;
	
	@ModelAttribute
	public PlmPublicFiles get(@RequestParam(required=false) String id) {
		PlmPublicFiles entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmPublicFilesService.get(id);
		}
		if (entity == null){
			entity = new PlmPublicFiles();
		}
		return entity;
	}
	
	@RequiresPermissions("files:plmPublicFiles:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmPublicFiles plmPublicFiles, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmPublicFiles> page = plmPublicFilesService.findPage(new Page<PlmPublicFiles>(request, response), plmPublicFiles); 
		model.addAttribute("page", page);
		return "plm/files/plmPublicFilesList";
	}

	@RequiresPermissions("files:plmPublicFiles:view")
	@RequestMapping(value = "form")
	public String form(PlmPublicFiles plmPublicFiles, Model model) {
		model.addAttribute("plmPublicFiles", plmPublicFiles);
		return "plm/files/plmPublicFilesForm";
	}

	@RequiresPermissions("files:plmPublicFiles:edit")
	@RequestMapping(value = "save")
	public String save(PlmPublicFiles plmPublicFiles, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmPublicFiles)){
			return form(plmPublicFiles, model);
		}
		plmPublicFilesService.save(plmPublicFiles);
		addMessage(redirectAttributes, "保存附件成功");
		return "redirect:"+Global.getAdminPath()+"/files/plmPublicFiles/?repage";
	}
	
	@RequiresPermissions("files:plmPublicFiles:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmPublicFiles plmPublicFiles, RedirectAttributes redirectAttributes) {
		plmPublicFilesService.delete(plmPublicFiles);
		addMessage(redirectAttributes, "删除附件成功");
		return "redirect:"+Global.getAdminPath()+"/files/plmPublicFiles/?repage";
	}

}