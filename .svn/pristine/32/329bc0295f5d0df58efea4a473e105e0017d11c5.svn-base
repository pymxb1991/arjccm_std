/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.web;

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
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventCasedeal;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventCasedealService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 事件处理考核Controller
 * 
 * @author pengjianqiang
 * @version 2018-03-26
 */
@Controller
@RequestMapping(value = "${adminPath}/event/ccmEventCasedealCheck")
public class CcmEventCasedealCheckController extends BaseController {

	@Autowired
	private CcmEventCasedealService ccmEventCasedealService;

	// ccmEventCasedeal
	@ModelAttribute
	public CcmEventCasedeal get(@RequestParam(required = false) String id,
			@RequestParam(required = false) String eventIncidentId) {
		CcmEventCasedeal entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmEventCasedealService.get(id);
		}
		if (entity == null) {
			entity = new CcmEventCasedeal();
		}
		return entity;
	}

	/**
	 * @see 返回视图列表页面
	 * @param ccmEventCasedeal 事件处理原型
	 * @param request  
	 * @param response  
	 * @param model 
	 * @return
	 */
	@RequiresPermissions("event:ccmEventCasedealCheck:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmEventCasedeal ccmEventCasedeal, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		ccmEventCasedeal.setIsCheck("check");//和事件处理区别开，考评不需要人员权限的数据限制
		Page<CcmEventCasedeal> page = ccmEventCasedealService.findPage(new Page<CcmEventCasedeal>(request, response),
				ccmEventCasedeal);
		model.addAttribute("page", page);
		return "ccm/event/eventCasedealCheck/ccmEventCasedealCheckList";
	}

	/**
	 * @see  添加或修改表单页面
	 * @param ccmEventCasedeal 事件处理原型
	 * @param model
	 * @return
	 */
	@RequiresPermissions("event:ccmEventCasedealCheck:view")
	@RequestMapping(value = "form")
	public String form(CcmEventCasedeal ccmEventCasedeal, Model model) {
		model.addAttribute("ccmEventCasedeal", ccmEventCasedeal);
		return "ccm/event/eventCasedealCheck/ccmEventCasedealCheckForm";
	}


	/**
	 * @see 用于编辑事件处理页面
	 * @param ccmEventCasedeal 事件处理原型
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws IOException 
	 */
	@RequiresPermissions("event:ccmEventCasedealCheck:edit")
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request,HttpServletResponse response,CcmEventCasedeal ccmEventCasedeal, Model model, RedirectAttributes redirectAttributes) throws IOException {
		ccmEventCasedealService.save(ccmEventCasedeal);
		PrintWriter out = response.getWriter();
		CommUtil.openWinExpDiv(out, "保存成功");
	}

	
	
	/**
	 * @see  删除事件处理信息
	 * @param ccmEventCasedeal 事件处理原型
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("event:ccmEventCasedeal:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmEventCasedeal ccmEventCasedeal, RedirectAttributes redirectAttributes) {
		ccmEventCasedealService.delete(ccmEventCasedeal);
		addMessage(redirectAttributes, "删除事件处理成功");
		return "redirect:" + Global.getAdminPath() + "/event/ccmEventCasedeal/?repage";
	}

}