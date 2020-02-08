/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.planinfo.web;

import java.io.IOException;
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
import com.arjjs.ccm.modules.flat.planinfo.entity.BphPlanInfo;
import com.arjjs.ccm.modules.flat.planinfo.service.BphPlanInfoService;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;


/**
 * 数字化预案Controller
 * @author zhanghao
 * @version 2018-11-14
 */
@Controller
@RequestMapping(value = "${adminPath}/planinfo/bphPlanInfo")
public class BphPlanInfoController extends BaseController {

	@Autowired
	private BphPlanInfoService bphPlanInfoService;
	
	@ModelAttribute
	public BphPlanInfo get(@RequestParam(required=false) String id) {
		BphPlanInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bphPlanInfoService.get(id);
		}
		if (entity == null){
			entity = new BphPlanInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("planinfo:bphPlanInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BphPlanInfo bphPlanInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BphPlanInfo> page = bphPlanInfoService.findPage(new Page<BphPlanInfo>(request, response), bphPlanInfo); 
		model.addAttribute("page", page);
		return "flat/planinfo/bphPlanInfoList";
	}

	@RequiresPermissions("planinfo:bphPlanInfo:view")
	@RequestMapping(value = "form")
	public String form(BphPlanInfo bphPlanInfo, Model model) {
		model.addAttribute("bphPlanInfo", bphPlanInfo);
		return "flat/planinfo/bphPlanInfoForm";
	}

	@RequiresPermissions("planinfo:bphPlanInfo:edit")
	@RequestMapping(value = "save")
	public String save(BphPlanInfo bphPlanInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bphPlanInfo)){
			return form(bphPlanInfo, model);
		}
		bphPlanInfoService.save(bphPlanInfo);
		addMessage(redirectAttributes, "保存数字化预案成功");
		return "redirect:"+Global.getAdminPath()+"/planinfo/bphPlanInfo/?repage";
	}
	
	@RequiresPermissions("planinfo:bphPlanInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BphPlanInfo bphPlanInfo, RedirectAttributes redirectAttributes) {
		bphPlanInfoService.delete(bphPlanInfo);
		addMessage(redirectAttributes, "删除数字化预案成功");
		return "redirect:"+Global.getAdminPath()+"/planinfo/bphPlanInfo/?repage";
	}

	@ResponseBody
	@RequiresPermissions("planinfo:bphPlanInfo:edit")
	@RequestMapping(value = "checkPlanName")
	public String checkPlanName(String planName) {
		BphPlanInfo bphPlanInfo = new BphPlanInfo();
		bphPlanInfo.setName(planName);
		bphPlanInfo = bphPlanInfoService.checkPlanName(bphPlanInfo);
		if(bphPlanInfo == null){
			return "true";
		}
		return "false";
	}
	
	@ResponseBody
	@RequestMapping(value = "planAssociated")
	public void planAssociated(HttpServletRequest request, HttpServletResponse response,String isImportant,String typeCode) throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("area") || name.equals("createBy") || name.equals("updateBy") || name.equals("currentUser") || name.equals("page") || name.equals("sqlMap") || name.equals("global") || name.equals("updateDate") || name.equals("createDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		response.getWriter().print(JSONArray.fromObject(bphPlanInfoService.findByIsImportantAndTypeCode(isImportant,typeCode),jsonConfig));;
	}
	
	//门户预案调用的次数统计
	@RequestMapping(value = "findPlanInvokeCount")
	public String findPlanInvokeCount(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BphPlanInfo> planList =  bphPlanInfoService.findPlanInvokeCount();
		model.addAttribute("planList", planList);
		return "flat/home/bphPlanInvokeList";
	}
}