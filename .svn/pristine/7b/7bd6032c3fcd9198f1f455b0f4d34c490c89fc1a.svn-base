/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgGropprevent;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgOrgprevent;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgGroppreventService;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgOrgpreventService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 群防群治队伍Controller
 * @author liang
 * @version 2018-01-12
 */
@Controller
@RequestMapping(value = "${adminPath}/org/ccmOrgGropprevent")
public class CcmOrgGroppreventController extends BaseController {

	@Autowired
	private CcmOrgGroppreventService ccmOrgGroppreventService;
	@Autowired
	private CcmOrgOrgpreventService ccmOrgOrgpreventService;
	
	@ModelAttribute
	public CcmOrgGropprevent get(@RequestParam(required=false) String id) {
		CcmOrgGropprevent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmOrgGroppreventService.get(id);
		}
		if (entity == null){
			entity = new CcmOrgGropprevent();
		}
		return entity;
	}
	/**
	 * 群防群治队伍构成-性别-大屏-滨海新区社会网格化管理信息平台
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findGroppreventSexType")
	public List<String> findGroppreventSexType(Model model) {
		// 返回对象结果
		List<String> list = new ArrayList<>();
		CcmOrgGropprevent ccmOrgGropprevent = new CcmOrgGropprevent();
		ccmOrgGropprevent.setSex("1");//女
		List<CcmOrgGropprevent> list1 = ccmOrgGroppreventService.findList(ccmOrgGropprevent);
		if(list1.size()>0){
			list.add(list1.size()+"");
		}else{
			list.add("0");
		}
		ccmOrgGropprevent.setSex("0");//男
		List<CcmOrgGropprevent> list2 = ccmOrgGroppreventService.findList(ccmOrgGropprevent);
		if(list2.size()>0){
			list.add(list2.size()+"");
		}else{
			list.add("0");
		}
		
		return list;
	}
	
	
	
	
	
	
	@RequiresPermissions("org:ccmOrgGropprevent:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmOrgGropprevent ccmOrgGropprevent, HttpServletRequest request, HttpServletResponse response, Model model, CcmOrgOrgprevent ccmOrgOrgprevent) {
		Page<CcmOrgGropprevent> page = ccmOrgGroppreventService.findPage(new Page<CcmOrgGropprevent>(request, response), ccmOrgGropprevent); 
		model.addAttribute("page", page);
		///查ccmOrgOrgpreventList并返回值
		List<CcmOrgOrgprevent> ccmOrgOrgpreventList = ccmOrgOrgpreventService.findList(ccmOrgOrgprevent);
		model.addAttribute("ccmOrgOrgpreventList", ccmOrgOrgpreventList);
		return "ccm/org/ccmOrgGroppreventList";
	}

	@RequiresPermissions("org:ccmOrgGropprevent:view")
	@RequestMapping(value = "form")
	public String form(CcmOrgGropprevent ccmOrgGropprevent, Model model, CcmOrgOrgprevent ccmOrgOrgprevent) {
		model.addAttribute("ccmOrgGropprevent", ccmOrgGropprevent);
		List<CcmOrgOrgprevent> ccmOrgOrgpreventList = ccmOrgOrgpreventService.findList(ccmOrgOrgprevent);
		model.addAttribute("ccmOrgOrgpreventList", ccmOrgOrgpreventList);
		return "ccm/org/ccmOrgGroppreventForm";
	}

	@RequiresPermissions("org:ccmOrgGropprevent:edit")
	@RequestMapping(value = "save")
	public void save(CcmOrgGropprevent ccmOrgGropprevent, Model model, RedirectAttributes redirectAttributes,
			HttpServletResponse response) {
		if (!beanValidator(model, ccmOrgGropprevent)){
			//return form(ccmOrgGropprevent, model, null);
		}
		ccmOrgGroppreventService.save(ccmOrgGropprevent);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommUtil.openWinExpDiv(out, "保存群防群治队伍成功");
		/*addMessage(redirectAttributes, "保存群防群治队伍成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgGropprevent/?repage";*/
	}
	
	@RequiresPermissions("org:ccmOrgGropprevent:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmOrgGropprevent ccmOrgGropprevent, RedirectAttributes redirectAttributes) {
		ccmOrgGroppreventService.delete(ccmOrgGropprevent);
		addMessage(redirectAttributes, "删除群防群治队伍成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgGropprevent/?repage";
	}

}