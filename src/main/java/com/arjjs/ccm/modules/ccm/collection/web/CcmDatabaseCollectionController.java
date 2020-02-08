/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.collection.web;

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
import com.arjjs.ccm.modules.ccm.collection.entity.CcmDatabaseCollection;
import com.arjjs.ccm.modules.ccm.collection.service.CcmDatabaseCollectionService;
import com.arjjs.ccm.modules.ccm.book.entity.dataCollectionEntity;

import java.util.List;

/**
 * 收藏夹管理Controller
 * @author cdz
 * @version 2019-09-16
 */
@Controller
@RequestMapping(value = "${adminPath}/collection/ccmDatabaseCollection")
public class CcmDatabaseCollectionController extends BaseController {

	@Autowired
	private CcmDatabaseCollectionService ccmDatabaseCollectionService;
	
	@ModelAttribute
	public CcmDatabaseCollection get(@RequestParam(required=false) String id) {
		CcmDatabaseCollection entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmDatabaseCollectionService.get(id);
		}
		if (entity == null){
			entity = new CcmDatabaseCollection();
		}
		return entity;
	}
	
	@RequiresPermissions("collection:ccmDatabaseCollection:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmDatabaseCollection ccmDatabaseCollection, HttpServletRequest request, HttpServletResponse response, Model model) {
		String id = null;
		Page<CcmDatabaseCollection> page = ccmDatabaseCollectionService.findPage(new Page<CcmDatabaseCollection>(request, response), ccmDatabaseCollection);
		List<dataCollectionEntity> lists =  ccmDatabaseCollectionService.findListCollection(id);
		model.addAttribute("page", page);
		model.addAttribute("lists", lists);
		return "ccm/collection/ccmDatabaseCollectionList";
	}

	@RequiresPermissions("collection:ccmDatabaseCollection:view")
	@RequestMapping(value = "form")
	public String form(CcmDatabaseCollection ccmDatabaseCollection, Model model) {
		model.addAttribute("ccmDatabaseCollection", ccmDatabaseCollection);
		return "ccm/collection/ccmDatabaseCollectionForm";
	}

	@RequiresPermissions("collection:ccmDatabaseCollection:edit")
	@RequestMapping(value = "save")
	public String save(CcmDatabaseCollection ccmDatabaseCollection, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmDatabaseCollection)){
			return form(ccmDatabaseCollection, model);
		}
		ccmDatabaseCollectionService.save(ccmDatabaseCollection);
		addMessage(redirectAttributes, "保存收藏夹管理成功");
		return "redirect:"+Global.getAdminPath()+"/collection/ccmDatabaseCollection/?repage";
	}
	
	@RequiresPermissions("collection:ccmDatabaseCollection:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmDatabaseCollection ccmDatabaseCollection, RedirectAttributes redirectAttributes) {
		ccmDatabaseCollectionService.delete(ccmDatabaseCollection);
		addMessage(redirectAttributes, "删除收藏夹管理成功");
		return "redirect:"+Global.getAdminPath()+"/collection/ccmDatabaseCollection/?repage";
	}

}