/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowSpace;
import com.arjjs.ccm.modules.ccm.know.service.CcmKnowSpaceService;

/**
 * 地方政策Controller
 * @author wwh
 * @version 2018-01-05
 */
@Controller
@RequestMapping(value = "${adminPath}/know/ccmKnowSpace")
public class CcmKnowSpaceController extends BaseController {

	@Autowired
	private CcmKnowSpaceService ccmKnowSpaceService;
	
	@ModelAttribute
	public CcmKnowSpace get(@RequestParam(required=false) String id) {
		CcmKnowSpace entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmKnowSpaceService.get(id);
		}
		if (entity == null){
			entity = new CcmKnowSpace();
		}
		return entity;
	}
	
	@RequiresPermissions("know:ccmKnowSpace:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmKnowSpace ccmKnowSpace, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmKnowSpace> page = ccmKnowSpaceService.findPage(new Page<CcmKnowSpace>(request, response), ccmKnowSpace); 
		model.addAttribute("page", page);
		return "ccm/know/ccmKnowSpaceList";
	}

	@RequiresPermissions("know:ccmKnowSpace:view")
	@RequestMapping(value = "form")
	public String form(CcmKnowSpace ccmKnowSpace, Model model) {
		model.addAttribute("ccmKnowSpace", ccmKnowSpace);
		return "ccm/know/ccmKnowSpaceForm";
	}

	@RequiresPermissions("know:ccmKnowSpace:edit")
	@RequestMapping(value = "save")
	public String save(CcmKnowSpace ccmKnowSpace, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		if (!beanValidator(model, ccmKnowSpace)){
			return form(ccmKnowSpace, model);
		}
		ccmKnowSpace.setFile1(URLDecoder.decode(ccmKnowSpace.getFile1(), "UTF-8"));
		ccmKnowSpace.setFile2(URLDecoder.decode(ccmKnowSpace.getFile2(), "UTF-8"));
		ccmKnowSpace.setFile3(URLDecoder.decode(ccmKnowSpace.getFile3(), "UTF-8"));
		
		ccmKnowSpaceService.save(ccmKnowSpace);
		addMessage(redirectAttributes, "保存地方政策成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowSpace/?repage";
	}
	
	@RequiresPermissions("know:ccmKnowSpace:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmKnowSpace ccmKnowSpace, RedirectAttributes redirectAttributes) {
		ccmKnowSpaceService.delete(ccmKnowSpace);
		addMessage(redirectAttributes, "删除地方政策成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowSpace/?repage";
	}

}