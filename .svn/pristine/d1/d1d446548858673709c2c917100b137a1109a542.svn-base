/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.web;

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
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowPunish;
import com.arjjs.ccm.modules.ccm.know.service.CcmKnowPunishService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgCommonality;
import com.arjjs.ccm.tool.EchartType;

/**
 * 城管行政处罚Controller
 * @author liang
 * @version 2018-06-02
 */
@Controller
@RequestMapping(value = "${adminPath}/know/ccmKnowPunish")
public class CcmKnowPunishController extends BaseController {

	@Autowired
	private CcmKnowPunishService ccmKnowPunishService;
	
	@ModelAttribute
	public CcmKnowPunish get(@RequestParam(required=false) String id) {
		CcmKnowPunish entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmKnowPunishService.get(id);
		}
		if (entity == null){
			entity = new CcmKnowPunish();
		}
		return entity;
	}
	/**
	 * 行政处罚-首页城市管理
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getType")
	public List<List<Integer>> getType(CcmKnowPunish ccmKnowPunish, Model model) {
		// 返回对象结果
		List<List<Integer>> list = new ArrayList<>();
		String[] ty = {"01","02","03","04","05","06","07"};//行政处罚://警告//罚款//没收//行政拘留//吊销许可证//责令停产停业//其它
		for(int n=0;n<ty.length;n++){
			CcmKnowPunish ccmKnowPunish2 = new CcmKnowPunish();
			ccmKnowPunish2.setType(ty[n]);
			List<Integer> listInt = ccmKnowPunishService.getType(ccmKnowPunish2);
			list.add(listInt);
		}
		return list;
	}
	
	
	
	
	@RequiresPermissions("know:ccmKnowPunish:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmKnowPunish ccmKnowPunish, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmKnowPunish> page = ccmKnowPunishService.findPage(new Page<CcmKnowPunish>(request, response), ccmKnowPunish); 
		model.addAttribute("page", page);
		return "ccm/know/ccmKnowPunishList";
	}

	@RequiresPermissions("know:ccmKnowPunish:view")
	@RequestMapping(value = "form")
	public String form(CcmKnowPunish ccmKnowPunish, Model model) {
		model.addAttribute("ccmKnowPunish", ccmKnowPunish);
		return "ccm/know/ccmKnowPunishForm";
	}

	@RequiresPermissions("know:ccmKnowPunish:edit")
	@RequestMapping(value = "save")
	public String save(CcmKnowPunish ccmKnowPunish, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmKnowPunish)){
			return form(ccmKnowPunish, model);
		}
		ccmKnowPunishService.save(ccmKnowPunish);
		addMessage(redirectAttributes, "保存城管行政处罚成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowPunish/?repage";
	}
	
	@RequiresPermissions("know:ccmKnowPunish:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmKnowPunish ccmKnowPunish, RedirectAttributes redirectAttributes) {
		ccmKnowPunishService.delete(ccmKnowPunish);
		addMessage(redirectAttributes, "删除城管行政处罚成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowPunish/?repage";
	}

}