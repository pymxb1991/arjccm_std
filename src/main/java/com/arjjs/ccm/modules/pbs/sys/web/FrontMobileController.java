/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.Role;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.pbs.sys.service.SystemServiceEx;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;

/**
 * 登录Controller
 * 
 * @author lc
 * @version 2018-03-27
 */
@Controller
@RequestMapping(value = "${frontPath}/sys/Mobile")
public class FrontMobileController extends BaseController {

	@Autowired
	private SystemServiceEx systemService;

	// 显示 主页内容
	@RequestMapping(value = { "index", "" })
	public String pbsvotelist(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 进入网址首页
		return "/index";
	}

	// 跳转网页 -无数据加载
	@RequestMapping(value = "pageTurn")
	public String pageTurn(@RequestParam(required = false) String pageTo, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 进入跳转网页
		return pageTo;
	}

	// 进行注册页面
	@RequestMapping(value = "register")
	public String register(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 进入跳转网页
		return "/modules/sys/register";
	}

	@ResponseBody
	@RequestMapping(value = "save")
	public String save(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {

		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		user.setCompany(new Office("wx001"));
		user.setOffice(new Office("wx001"));
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(SystemServiceEx.entryptPassword(user.getNewPassword()));
		}

		// 验证对象 是否 合法
		if (!beanValidator(model, user)) {
			Map<String, Object> validatorMap = model.asMap();
			return validatorMap.get("message").toString();
		}

		// 验证对象 是否登录
		if (systemService.getUserByLoginName(user.getLoginName()) != null) {
			return "注册失败，登录名已存在";
		}

		// 验证对象 的 手机号 与 email
		if (systemService.getUserByLoginName(user.getLoginName()) != null) {
			return "注册失败，手机号 或 email 已存在";
		}

		// 角色数据有效性验证，过滤不在授权内的角色
		// 新增对象
		List<Role> roleNList = Lists.newArrayList();
		// 默认获取全部的 用户信息
		Role roleDto = new Role();
		List<Role> roleList = systemService.findRole(roleDto);
		// 传入的对象 角色
		List<String> roleIdList = user.getRoleIdList();
		// 变更当前的用户角色
		for (Role r : roleList) {
			if (roleIdList.contains(r.getId())) {
				roleNList.add(r);
			}
		}
		// 设置 用户 列表
		user.setRoleList(roleNList);
		// 保存用户信息
		systemService.registerUser(user);
		// 清除当前用户缓存
		if (user.getLoginName().equals(UserUtils.getUser().getLoginName())) {
			UserUtils.clearCache();
			// UserUtils.getCacheMap().clear();
		}
		addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
		return "sucess";
	}
	
	 

}