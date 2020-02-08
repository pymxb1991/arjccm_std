/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.search.web;

import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.carpass.entity.CcmCarPass;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import com.arjjs.ccm.modules.ccm.carpass.entity.CcmCarPass;
import com.arjjs.ccm.modules.ccm.carpass.service.CcmCarPassService;*/

/**
 * 过车查询Controller
 * @author liuyongjian
 * @version 2019-07-24
 */
@Controller
@RequestMapping(value = "${adminPath}/searchsnap/iotSearchSnap")
public class IotsearchsnapController extends BaseController {	
	@RequiresPermissions("searchsnap:iotSearchSnap:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmCarPass ccmCarPass, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("ccmCarPass", ccmCarPass);
		return "iot/search/iotSearchSnap";
	}

}