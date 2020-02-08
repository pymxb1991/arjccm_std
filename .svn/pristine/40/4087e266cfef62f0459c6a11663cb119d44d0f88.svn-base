package com.arjjs.ccm.modules.ccm.sys.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.service.SystemService;

/**
 * 用户Controller
 * 
 * @author zjb
 * @version 2018-9-11
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/userAuthstr")
public class UserAuthstrController {

	@Autowired
	private SystemService systemService;

	/**
	 * 用户未审核
	 * 
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value ="list")
	public String list(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
		model.addAttribute("page", page);
		return "modules/sys/authstrList";
	}
}
