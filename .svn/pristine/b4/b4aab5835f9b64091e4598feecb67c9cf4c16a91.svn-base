/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.list.web;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.arjjs.ccm.modules.ccm.list.entity.CcmListPeople;
import com.arjjs.ccm.modules.ccm.list.service.CcmListPeopleService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 静态库和黑名单人员实体类Controller
 * @author jpy
 * @version 2019-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/list/ccmListPeople")
public class CcmListPeopleController extends BaseController {

	@Autowired
	private CcmListPeopleService ccmListPeopleService;
	
	@ModelAttribute
	public CcmListPeople get(@RequestParam(required=false) String id) {
		CcmListPeople entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmListPeopleService.get(id);
		}
		if (entity == null){
			entity = new CcmListPeople();
		}
		return entity;
	}
	
	@RequiresPermissions("list:ccmListPeople:view")
	@RequestMapping(value = { "listPeopleList" })
	public String laneList(String type, Model model) {
		if(StringUtils.isBlank(type)) {
			type ="01";
		}
		model.addAttribute("type", type);
		if(type.equals("01")) {
			model.addAttribute("title", "黑名单");
		}else {
			model.addAttribute("title", "静态库");
		}
		return "ccm/list/listPeopleList";
	}
	
	@RequiresPermissions("list:ccmListPeople:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmListPeople ccmListPeople, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotBlank(ccmListPeople.getListId()) && ccmListPeople.getListId().equals("0")) {
			ccmListPeople.setListId(null);
		}
		Page<CcmListPeople> page = ccmListPeopleService.findPage(new Page<CcmListPeople>(request, response), ccmListPeople); 
		model.addAttribute("page", page);
		if(StringUtils.isBlank(ccmListPeople.getType())) {
			ccmListPeople.setType("01");
		}
		model.addAttribute("type", ccmListPeople.getType());
		model.addAttribute("listId", ccmListPeople.getListId());
		if(ccmListPeople.getType().equals("01")) {
			model.addAttribute("title", "黑名单");
		}else {
			model.addAttribute("title", "静态库");
		}
		return "ccm/list/ccmListPeopleList";
	}

	@RequiresPermissions("list:ccmListPeople:view")
	@RequestMapping(value = "form")
	public String form(CcmListPeople ccmListPeople, Model model) {
		model.addAttribute("type", ccmListPeople.getType());
		model.addAttribute("ccmListPeople", ccmListPeople);
		return "ccm/list/ccmListPeopleForm";
	}

	@RequiresPermissions("list:ccmListPeople:edit")
	@RequestMapping(value = "save")
	public void save(CcmListPeople ccmListPeople, Model model, RedirectAttributes redirectAttributes, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!beanValidator(model, ccmListPeople)){
			CommUtil.openWinExpDiv(out, "保存人员失败");
		}else {
			ccmListPeopleService.save(ccmListPeople);
			CommUtil.openWinExpDiv(out, "保存人员成功");
		}
	}
	
	@RequiresPermissions("list:ccmListPeople:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmListPeople ccmListPeople, RedirectAttributes redirectAttributes) {
		ccmListPeopleService.delete(ccmListPeople);
		addMessage(redirectAttributes, "删除人员成功");
		return "redirect:"+Global.getAdminPath()+"/list/ccmListPeople/?type="+ccmListPeople.getType()+"&repage";
	}

}