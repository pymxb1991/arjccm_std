/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.dma.socialsecurity.web;

import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.bayonet.entity.CcmCarBayonet;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 两新组织Controller
 * 
 * @author lhf
 * @version 2019-09-3
 */
@Controller
@RequestMapping(value = "${adminPath}/comprehensiveTopic/dmaSocialSecurity")
public class DmaSocialSecurityController extends BaseController {

	@RequiresPermissions("comprehensiveTopic:dmaSocialSecurity:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmCarBayonet ccmCarBayonet, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		return "dma/socialsecurity/socialSecurityList";
	}

}