/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.web;

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
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseAreainfor;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseAreainforService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgArea;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgAreaService;
import com.arjjs.ccm.modules.ccm.view.entity.VCcmTeam;
import com.arjjs.ccm.modules.ccm.view.service.VCcmTeamService;

/**
 * 辖区信息Controller
 * 
 * @author Arj
 * @version 2018-01-23
 */
@Controller
@RequestMapping(value = "${adminPath}/house/ccmHouseAreainfor")
public class CcmHouseAreainforController extends BaseController {

	@Autowired
	private CcmHouseAreainforService ccmHouseAreainforService;

	@Autowired
	private CcmOrgAreaService ccmOrgAreaService;

	@Autowired
	private VCcmTeamService vCcmTeamService;

	@ModelAttribute
	public CcmHouseAreainfor get(@RequestParam(required = false) String id) {
		CcmHouseAreainfor entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHouseAreainforService.get(id);
		}
		if (entity == null) {
			entity = new CcmHouseAreainfor();
		}
		return entity;
	}

	@RequiresPermissions("house:ccmHouseAreainfor:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmHouseAreainfor ccmHouseAreainfor, HttpServletRequest request, HttpServletResponse response,
			Model model) throws InterruptedException {
		// 返回结果
		CcmOrgArea ccmOrgArea = ccmOrgAreaService.GetItByUserId();
		if (null != ccmOrgArea) {
			// 返回具体内容
			List<VCcmTeam> ListVccTeam = vCcmTeamService.GetUserByArea(ccmOrgArea.getArea().getId());
			model.addAttribute("ListVccTeam", ListVccTeam);
		}
		// 返回具体内容 
		model.addAttribute("ccmOrgArea", ccmOrgArea);
		Thread.sleep(500);
		return "ccm/house/ccmHouseAreainforList";
	}

	@RequiresPermissions("house:ccmHouseAreainfor:view")
	@RequestMapping(value = "form")
	public String form(CcmHouseAreainfor ccmHouseAreainfor, Model model) {
		model.addAttribute("ccmHouseAreainfor", ccmHouseAreainfor);
		return "ccm/house/ccmHouseAreainforForm";
	}

	@RequiresPermissions("house:ccmHouseAreainfor:edit")
	@RequestMapping(value = "save")
	public String save(CcmHouseAreainfor ccmHouseAreainfor, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmHouseAreainfor)) {
			return form(ccmHouseAreainfor, model);
		}
		ccmHouseAreainforService.save(ccmHouseAreainfor);
		addMessage(redirectAttributes, "保存辖区信息成功");
		return "redirect:" + Global.getAdminPath() + "/house/ccmHouseAreainfor/?repage";
	}

	@RequiresPermissions("house:ccmHouseAreainfor:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmHouseAreainfor ccmHouseAreainfor, RedirectAttributes redirectAttributes) {
		ccmHouseAreainforService.delete(ccmHouseAreainfor);
		addMessage(redirectAttributes, "删除辖区信息成功");
		return "redirect:" + Global.getAdminPath() + "/house/ccmHouseAreainfor/?repage";
	}

}