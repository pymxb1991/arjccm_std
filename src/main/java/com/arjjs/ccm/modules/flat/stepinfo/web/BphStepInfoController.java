/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.stepinfo.web;

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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.flat.planinfo.entity.BphPlanInfo;
import com.arjjs.ccm.modules.flat.planinfo.service.BphPlanInfoService;
import com.arjjs.ccm.modules.flat.stepinfo.entity.BphStepInfo;
import com.arjjs.ccm.modules.flat.stepinfo.service.BphStepInfoService;

/**
 * 预案过程Controller
 * @author zhanghao
 * @version 2018-11-14
 */
@Controller
@RequestMapping(value = "${adminPath}/stepinfo/bphStepInfo")
public class BphStepInfoController extends BaseController {

	@Autowired
	private BphStepInfoService bphStepInfoService;
	@Autowired
	private BphPlanInfoService bphPlanInfoService;
	@ModelAttribute
	public BphStepInfo get(@RequestParam(required=false) String id) {
		BphStepInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bphStepInfoService.get(id);
		}
		if (entity == null){
			entity = new BphStepInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("stepinfo:bphStepInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BphStepInfo bphStepInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BphPlanInfo> planList=new ArrayList<BphPlanInfo>();
		BphPlanInfo bphPlanInfo=new BphPlanInfo();
		planList=bphPlanInfoService.findList(bphPlanInfo);
		model.addAttribute("planList", planList);
		Page<BphStepInfo> page = bphStepInfoService.findPage(new Page<BphStepInfo>(request, response), bphStepInfo); 
		model.addAttribute("page", page);
		return "flat/stepinfo/bphStepInfoList";
	}

	@RequiresPermissions("stepinfo:bphStepInfo:view")
	@RequestMapping(value = "form")
	public String form(BphStepInfo bphStepInfo, Model model) {
		model.addAttribute("bphStepInfo", bphStepInfo);
		return "flat/stepinfo/bphStepInfoForm";
	}

	@RequiresPermissions("stepinfo:bphStepInfo:edit")
	@RequestMapping(value = "save")
	public String save(BphStepInfo bphStepInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bphStepInfo)){
			return form(bphStepInfo, model);
		}
		bphStepInfoService.save(bphStepInfo);
		addMessage(redirectAttributes, "保存预案过程成功");
		return "redirect:"+Global.getAdminPath()+"/stepinfo/bphStepInfo/?repage";
	}
	
	@RequiresPermissions("stepinfo:bphStepInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BphStepInfo bphStepInfo, RedirectAttributes redirectAttributes) {
		bphStepInfoService.delete(bphStepInfo);
		addMessage(redirectAttributes, "删除预案过程成功");
		return "redirect:"+Global.getAdminPath()+"/stepinfo/bphStepInfo/?repage";
	}
	
	@ResponseBody
	@RequiresPermissions("stepinfo:bphStepInfo:edit")
	@RequestMapping(value = "checkStepName")
	public String checkStepName(String stepName) {
		BphStepInfo bphStepInfo = new BphStepInfo();
		bphStepInfo.setName(stepName);
		bphStepInfo = bphStepInfoService.checkStepName(bphStepInfo);
		if(bphStepInfo == null){
			return "true";
		}
		return "false";
	}
}