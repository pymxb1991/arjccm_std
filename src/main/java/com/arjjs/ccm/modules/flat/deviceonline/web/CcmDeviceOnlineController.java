/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.deviceonline.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.common.utils.JedisUtils;
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
import com.arjjs.ccm.modules.flat.deviceonline.entity.CcmDeviceOnline;
import com.arjjs.ccm.modules.flat.deviceonline.service.CcmDeviceOnlineService;

/**
 * 设备在线实体类Controller
 * 
 * @author lgh
 * @version 2019-07-13
 */
@Controller
@RequestMapping(value = "${adminPath}/deviceonline/ccmDeviceOnline")
public class CcmDeviceOnlineController extends BaseController {

	@Autowired
	private CcmDeviceOnlineService ccmDeviceOnlineService;

	private final int cacheSeconds=60*60*24;
	@ModelAttribute
	public CcmDeviceOnline get(@RequestParam(required = false) String id) {
		CcmDeviceOnline entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmDeviceOnlineService.get(id);
		}
		if (entity == null) {
			entity = new CcmDeviceOnline();
		}
		return entity;
	}

	@RequiresPermissions("deviceonline:ccmDeviceOnline:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmDeviceOnline ccmDeviceOnline, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		Map<String, Object> ccmDeviceOnline1 = JedisUtils.getObjectMap("ccmDeviceOnline");
		if(ccmDeviceOnline1!=null){
			model.addAttribute("page", ccmDeviceOnline1);
			return "flat/deviceonline/ccmDeviceOnlineList";
		}

		Page<CcmDeviceOnline> page = ccmDeviceOnlineService.findPage(new Page<CcmDeviceOnline>(request, response),
				ccmDeviceOnline);
		model.addAttribute("page", page);

		List<CcmDeviceOnline> list = ccmDeviceOnlineService.findList(new CcmDeviceOnline());
		for (CcmDeviceOnline online : list) {
			// 判断当前时间和最新更新时间的时间差，如果大于3分钟，则判定为离线状态
			long timeDiff = (new Date()).getTime() - online.getUpdateTime().getTime();
			if (timeDiff / (1000 * 60 * 60) > 3) {
				online.setStatus("2");
				ccmDeviceOnlineService.save(online);
			}
		}

		JedisUtils.setObject("ccmDeviceOnline",page,cacheSeconds);
		return "flat/deviceonline/ccmDeviceOnlineList";
	}

	@RequiresPermissions("deviceonline:ccmDeviceOnline:view")
	@RequestMapping(value = "form")
	public String form(CcmDeviceOnline ccmDeviceOnline, Model model) {
		model.addAttribute("ccmDeviceOnline", ccmDeviceOnline);
		return "flat/deviceonline/ccmDeviceOnlineForm";
	}

	@RequiresPermissions("deviceonline:ccmDeviceOnline:edit")
	@RequestMapping(value = "save")
	public String save(CcmDeviceOnline ccmDeviceOnline, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmDeviceOnline)) {
			return form(ccmDeviceOnline, model);
		}
		ccmDeviceOnlineService.save(ccmDeviceOnline);
		addMessage(redirectAttributes, "保存设备在线记录成功");
		return "redirect:" + Global.getAdminPath() + "/deviceonline/ccmDeviceOnline/?repage";
	}

	@RequiresPermissions("deviceonline:ccmDeviceOnline:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmDeviceOnline ccmDeviceOnline, RedirectAttributes redirectAttributes) {
		ccmDeviceOnlineService.delete(ccmDeviceOnline);
		addMessage(redirectAttributes, "删除设备在线记录成功");
		return "redirect:" + Global.getAdminPath() + "/deviceonline/ccmDeviceOnline/?repage";
	}

}