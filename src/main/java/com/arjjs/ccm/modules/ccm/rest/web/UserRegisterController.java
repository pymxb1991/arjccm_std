package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.IdGen;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.rest.service.UserRegisterService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.service.SystemService;

/**
 * 用户注册Controller
 * 
 * @author zjb
 * @version 2018-9-11
 */
@Controller
@RequestMapping(value = "${appPath}/sys/userRegister")
public class UserRegisterController {

	@Autowired
	private SystemService systemService;

	@Autowired
	private UserRegisterService userRegisterService;

	/**
	 * 验证登录名是否有效
	 * 
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkLoginName", method = RequestMethod.POST)
	public String queryDevice(String loginName, HttpServletRequest req, HttpServletResponse resp, String userId)
			throws IOException {
		if (loginName != null && systemService.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}

	@ResponseBody
	@RequestMapping(value = "/RegisterUser", method = RequestMethod.POST)
	public String RegisterUser(User user) {
		Office o = new Office();
		o.setId("1");
		o.setName("新用户");
		User user1 = new User();
		user1.setId("1");

		user.setId(IdGen.uuid());
		user.setOffice(o);
		user.setCompany(o);
		user.setCreateBy(user1);
		user.setCreateDate(new Date());
		user.setUpdateBy(user1);
		user.setUpdateDate(new Date());
		user.setDelFlag("0");
		user.setIsNewRecord(true);
		userRegisterService.insertUser(user);
		return "true";
	}

}
