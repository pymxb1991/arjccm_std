/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.web;

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
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowKeyFile;
import com.arjjs.ccm.modules.ccm.know.service.CcmKnowKeyFileService;

/**
 * 重要文件Controller
 * @author liang
 * @version 2018-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/know/ccmKnowKeyFile")
public class CcmKnowKeyFileController extends BaseController {

	@Autowired
	private CcmKnowKeyFileService ccmKnowKeyFileService;
	
	@ModelAttribute
	public CcmKnowKeyFile get(@RequestParam(required=false) String id) {
		CcmKnowKeyFile entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmKnowKeyFileService.get(id);
		}
		if (entity == null){
			entity = new CcmKnowKeyFile();
		}
		return entity;
	}
	
	@RequiresPermissions("know:ccmKnowKeyFile:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmKnowKeyFile ccmKnowKeyFile, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmKnowKeyFile> page = ccmKnowKeyFileService.findPage(new Page<CcmKnowKeyFile>(request, response), ccmKnowKeyFile); 
		model.addAttribute("page", page);
		return "ccm/know/ccmKnowKeyFileList";
	}

	@RequiresPermissions("know:ccmKnowKeyFile:view")
	@RequestMapping(value = "form")
	public String form(CcmKnowKeyFile ccmKnowKeyFile, Model model) {
		model.addAttribute("ccmKnowKeyFile", ccmKnowKeyFile);
		return "ccm/know/ccmKnowKeyFileForm";
	}

	@RequiresPermissions("know:ccmKnowKeyFile:edit")
	@RequestMapping(value = "save")
	public String save(CcmKnowKeyFile ccmKnowKeyFile, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmKnowKeyFile)){
			return form(ccmKnowKeyFile, model);
		}
		ccmKnowKeyFileService.save(ccmKnowKeyFile);
		addMessage(redirectAttributes, "保存重要文件成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowKeyFile/?repage";
	}
	
	@RequiresPermissions("know:ccmKnowKeyFile:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmKnowKeyFile ccmKnowKeyFile, RedirectAttributes redirectAttributes) {
		ccmKnowKeyFileService.delete(ccmKnowKeyFile);
		addMessage(redirectAttributes, "删除重要文件成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowKeyFile/?repage";
	}

}