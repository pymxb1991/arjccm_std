/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.bayonet.web;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.bayonet.entity.CcmCarBayonet;
import com.arjjs.ccm.modules.ccm.bayonet.service.CcmCarBayonetService;
import com.arjjs.ccm.tool.CommUtil;
import com.google.common.collect.Maps;
import com.sun.javafx.collections.MappingChange.Map;

/**
 * 车辆卡口实体类Controller
 * 
 * @author lgh
 * @version 2019-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/bayonet/ccmCarBayonet")
public class CcmCarBayonetController extends BaseController {

	@Autowired
	private CcmCarBayonetService ccmCarBayonetService;

	@ModelAttribute
	public CcmCarBayonet get(@RequestParam(required = false) String id) {
		CcmCarBayonet entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmCarBayonetService.get(id);
		}
		if (entity == null) {
			entity = new CcmCarBayonet();
		}
		return entity;
	}

	@RequiresPermissions("bayonet:ccmCarBayonet:view")
	@RequestMapping(value = "getList")
	@ResponseBody
	public Object getList() {

		List<CcmCarBayonet> list = ccmCarBayonetService.findList(new CcmCarBayonet());

		return list;
	}

	@RequiresPermissions("bayonet:ccmCarBayonet:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmCarBayonet ccmCarBayonet, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CcmCarBayonet> page = ccmCarBayonetService.findPage(new Page<CcmCarBayonet>(request, response),
				ccmCarBayonet);
		model.addAttribute("page", page);
		return "ccm/bayonet/ccmCarBayonetList";
	}

	@RequiresPermissions("bayonet:ccmCarBayonet:view")
	@RequestMapping(value = "form")
	public String form(CcmCarBayonet ccmCarBayonet, Model model) {
		model.addAttribute("ccmCarBayonet", ccmCarBayonet);
		return "ccm/bayonet/ccmCarBayonetForm";
	}

	@RequiresPermissions("bayonet:ccmCarBayonet:edit")
	@RequestMapping(value = "save")
	public void save(CcmCarBayonet ccmCarBayonet, Model model, RedirectAttributes redirectAttributes,
			HttpServletResponse response) {
		/*
		 * if (!beanValidator(model, ccmCarBayonet)){ return form(ccmCarBayonet, model);
		 * }
		 */
		ccmCarBayonetService.save(ccmCarBayonet);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommUtil.openWinExpDiv(out, "保存车辆卡口成功");

	}

	@RequiresPermissions("bayonet:ccmCarBayonet:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmCarBayonet ccmCarBayonet, RedirectAttributes redirectAttributes) {
		ccmCarBayonetService.delete(ccmCarBayonet);
		addMessage(redirectAttributes, "删除车辆卡口成功");
		return "redirect:" + Global.getAdminPath() + "/bayonet/ccmCarBayonet/?repage";
	}

	//得到卡口数量
	@ResponseBody
	@RequestMapping(value = "getCount")
	public String getCount(CcmCarBayonet ccmCarBayonet) {
		int count = ccmCarBayonetService.getCount(ccmCarBayonet);
		return String.valueOf(count);
	}
}