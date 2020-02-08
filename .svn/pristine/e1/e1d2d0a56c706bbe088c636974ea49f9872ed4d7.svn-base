/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.web;

import java.io.UnsupportedEncodingException;

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

import com.alibaba.fastjson.JSONObject;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgArea;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgAreaService;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.service.AreaService;
import com.arjjs.ccm.modules.sys.service.SystemService;

/**
 * 区域扩展表（社区、网格）Controller
 * 
 * @author pengjianqiang
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/org/ccmOrgArea")
public class CcmOrgAreaController extends BaseController {

	@Autowired
	private CcmOrgAreaService ccmOrgAreaService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private AreaService areaService;
	
	@ModelAttribute
	public CcmOrgArea get(@RequestParam(required = false) String id) {
		CcmOrgArea entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmOrgAreaService.get(id);
		}
		if (entity == null) {
			entity = new CcmOrgArea();
		}
		return entity;
	}

	@RequiresPermissions("org:ccmOrgArea:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmOrgArea ccmOrgArea, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmOrgArea> page = ccmOrgAreaService.findPage(new Page<CcmOrgArea>(request, response), ccmOrgArea);
		model.addAttribute("page", page);
		return "ccm/org/ccmOrgAreaList";
	}

	@RequiresPermissions("org:ccmOrgArea:view")
	@RequestMapping(value = "form")
	public String form(CcmOrgArea ccmOrgArea, Model model) {
		CcmOrgArea ccmOrgArea2 = ccmOrgAreaService.get(ccmOrgArea);
		model.addAttribute("ccmOrgArea", ccmOrgArea2);
		model.addAttribute("type", ccmOrgArea.getType());
		return "ccm/org/ccmOrgAreaForm";
	}

	@RequiresPermissions("org:ccmOrgArea:edit")
	@RequestMapping(value = "save")
	public String save(CcmOrgArea ccmOrgArea, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmOrgArea)) {
			return form(ccmOrgArea, model);
		}
		ccmOrgAreaService.save(ccmOrgArea);
		addMessage(redirectAttributes, "保存区域成功");
		// return "redirect:"+Global.getAdminPath()+"/org/ccmOrgArea/?repage";
		return "redirect:" + Global.getAdminPath() + "/org/sysArea?type=" + ccmOrgArea.getType() + "";
	}

	@RequiresPermissions("org:ccmOrgArea:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmOrgArea ccmOrgArea, RedirectAttributes redirectAttributes) {
		ccmOrgAreaService.delete(ccmOrgArea);
		addMessage(redirectAttributes, "删除区域成功");
		return "redirect:" + Global.getAdminPath() + "/org/ccmOrgArea/?repage";
	}

	@RequiresPermissions("org:ccmOrgArea:view")
	@RequestMapping(value = "location")
	@ResponseBody
	public JSONObject  location(String userId, RedirectAttributes redirectAttributes) {
		// 查询用户office
		User user = systemService.getUser(userId);
		Office office = user.getOffice();
		Area area = office.getArea();
		Area area2 = areaService.get(area.getId());
		CcmOrgArea ccmOrgArea = new CcmOrgArea();
		ccmOrgArea.setArea(area);
		CcmOrgArea ccmOrgArea2 = ccmOrgAreaService.findCcmOrgArea(ccmOrgArea);
		
		JSONObject  jsonObject  = new JSONObject();
		jsonObject.put("areaPoint", ccmOrgArea2.getAreaPoint());
		jsonObject.put("type", area2.getType());
		
		return jsonObject;
	}
}