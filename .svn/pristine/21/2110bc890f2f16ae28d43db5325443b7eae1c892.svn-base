/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.action.web;

import java.io.IOException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.flat.action.entity.BphActionInfo;
import com.arjjs.ccm.modules.flat.action.service.BphActionInfoService;
import com.arjjs.ccm.modules.flat.actionuser.entity.BphActionUser;
import com.arjjs.ccm.modules.flat.actionuser.service.BphActionUserService;
import com.arjjs.ccm.modules.flat.stepinfo.entity.BphStepInfo;
import com.arjjs.ccm.modules.flat.stepinfo.service.BphStepInfoService;

/**
 * 执行动作配置Controller
 * @author liu
 * @version 2018-11-14
 */
@Controller
@RequestMapping(value = "${adminPath}/action/bphActionInfo")
public class BphActionInfoController extends BaseController {

	@Autowired
	private BphActionInfoService bphActionInfoService;
	@Autowired
	private BphActionUserService bphActionUserService;
	@Autowired
	private BphStepInfoService bphStepInfoService;
	
	@ModelAttribute
	public BphActionInfo get(@RequestParam(required=false) String id) {
		BphActionInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bphActionInfoService.get(id);
		}
		if (entity == null){
			entity = new BphActionInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("action:bphActionInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BphActionInfo bphActionInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BphStepInfo> stepList=new ArrayList<BphStepInfo>();
		BphStepInfo bphStepInfo=new BphStepInfo();
		stepList=bphStepInfoService.findList(bphStepInfo);
		model.addAttribute("stepList", stepList);
		Page<BphActionInfo> page = bphActionInfoService.findPage(new Page<BphActionInfo>(request, response), bphActionInfo); 
		model.addAttribute("page", page);
		return "flat/action/bphActionInfoList";
	}

	@RequiresPermissions("action:bphActionInfo:view")
	@RequestMapping(value = "form")
	public String form(BphActionInfo bphActionInfo, Model model ) {
		model.addAttribute("bphActionInfo", bphActionInfo);
		BphActionUser bphActionUser =new BphActionUser();
		bphActionUser.setActionId(bphActionInfo.getId());
		List<BphActionUser> bphActionUserList = bphActionUserService.getIdList(bphActionUser);
		model.addAttribute("bphActionUserList",bphActionUserList);
		return "flat/action/bphActionInfoForm";
	}

	@RequiresPermissions("action:bphActionInfo:edit")
	@RequestMapping(value = "save")
	public void save(String param,HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) throws IOException {
		bphActionInfoService.saveData(param);
	}
	
	@RequiresPermissions("action:bphActionInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BphActionInfo bphActionInfo, RedirectAttributes redirectAttributes) {
		bphActionInfoService.delete(bphActionInfo);
		addMessage(redirectAttributes, "删除执行动作配置成功");
		return "redirect:"+Global.getAdminPath()+"/action/bphActionInfo/?repage";
	}

}