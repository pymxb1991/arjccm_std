/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.web;

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
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymembind;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymemreg;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemService;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymembindService;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemregService;
import com.arjjs.ccm.modules.pbs.sys.dao.PbsRoleDao;
import com.arjjs.ccm.modules.sys.dao.UserDao;
import com.arjjs.ccm.modules.sys.entity.Role;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.google.common.collect.Lists;

/**
 * 党员登记信息Controller
 * 
 * @author lc
 * @version 2018-04-08
 */
@Controller
@RequestMapping(value = "${adminPath}/person/pbsPartymemreg")
public class PbsPartymemregController extends BaseController {

	// 党员登记信息
	@Autowired
	private PbsPartymemregService pbsPartymemregService;
	// 党员信息
	@Autowired
	private PbsPartymemService pbsPartymemService;
	// 党员关系信息
	@Autowired
	private PbsPartymembindService pbsPartymembindService;
	@Autowired
	private UserDao userDao;
	 @Autowired
	private PbsRoleDao roleDao;

	// 党员角色
	private static final String ROLEPARTYMEM = "5";

	@ModelAttribute
	public PbsPartymemreg get(@RequestParam(required = false) String id) {
		PbsPartymemreg entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsPartymemregService.get(id);
		}
		if (entity == null) {
			entity = new PbsPartymemreg();
		}
		return entity;
	}

	@RequiresPermissions("person:pbsPartymemreg:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsPartymemreg pbsPartymemreg, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsPartymemreg> page = pbsPartymemregService.findPage(new Page<PbsPartymemreg>(request, response),
				pbsPartymemreg);
		model.addAttribute("page", page);
		return "pbs/person/pbsPartymemregList";
	}

	@RequiresPermissions("person:pbsPartymemreg:view")
	@RequestMapping(value = "form")
	public String form(PbsPartymemreg pbsPartymemreg, Model model) {
		// 党员信息
		PbsPartymem pbsPartymemDto = new PbsPartymem();
		// 联查 党员信息
		pbsPartymemDto.setSIdtype(pbsPartymemreg.getSIdtype());
		pbsPartymemDto.setSIdnum(pbsPartymemreg.getSIdnum());
		List<PbsPartymem> PbsPartymemList = pbsPartymemService.findList(pbsPartymemDto);
		// 当前的 登记 有对应的内容
		if (PbsPartymemList.size() > 0) {
			// 向页面传递 党员内容
			model.addAttribute("pbsPartymem", PbsPartymemList.get(0));
		}
		model.addAttribute("pbsPartymemreg", pbsPartymemreg);
		return "pbs/person/pbsPartymemregForm";
	}

	@RequiresPermissions("person:pbsPartymemreg:edit")
	@RequestMapping(value = "save")
	public String save(PbsPartymemreg pbsPartymemreg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsPartymemreg)) {
			return form(pbsPartymemreg, model);
		}
		// 保存党员
		pbsPartymemregService.save(pbsPartymemreg);
		addMessage(redirectAttributes, "保存党员登记信息成功");
		return "redirect:" + Global.getAdminPath() + "/person/pbsPartymemreg/?repage";
	}

	@RequiresPermissions("person:pbsPartymemreg:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsPartymemreg pbsPartymemreg, RedirectAttributes redirectAttributes) {
		pbsPartymemregService.delete(pbsPartymemreg);
		addMessage(redirectAttributes, "删除党员登记信息成功");
		return "redirect:" + Global.getAdminPath() + "/person/pbsPartymemreg/?repage";
	}

	// 审核 登记状态
	@RequiresPermissions("person:pbsPartymemreg:edit")
	@RequestMapping(value = "updatstat")
	public String updatstat(PbsPartymemreg pbsPartymemreg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsPartymemreg)) {
			return form(pbsPartymemreg, model);
		}
		// 如果当前的内容
		if (("1").equals(pbsPartymemreg.getSRegstat())) {
			// 如果 登记对应党员id 不存在
			if (StringUtils.isBlank(pbsPartymemreg.getPbspartymemid())) {
				addMessage(model, "数据验证失败： 审核无法通过，因为没有对应的党员信息。");
				return form(pbsPartymemreg, model);
			}

			//
			PbsPartymembind pbsPartymembind = new PbsPartymembind();
			// 党员id
			pbsPartymembind.setSType("User");
			pbsPartymembind.setSSource("sys_user");
			pbsPartymembind.setSPrimarykey01(pbsPartymemreg.getUserid());
			// 党员-用户关系数
			List<PbsPartymembind> binds = pbsPartymembindService.findList(pbsPartymembind);
			if (binds.size() > 0) {
				addMessage(model, "数据验证失败： 已经存在该用户所匹配党员信息。");
				return form(pbsPartymemreg, model);
			}
			// 保存党员-用户信息
			pbsPartymembind.setSPartymemid(pbsPartymemreg.getPbspartymemid());
			pbsPartymembindService.save(pbsPartymembind);
			// 先获取当前的角色列表
			// User user = userDao.get(pbsPartymemreg.getUserid());
			// Role role = new Role();
			// role.getSqlMap().put("dsf", BaseService.dataScopeFilter(user,
			// "o", "u"));
			// List<Role> roleList = roleDao.findList
			
			List<String> roleIds = roleDao.queryRole(pbsPartymemreg.getUserid());
			
			boolean flag = true;
			/*for (Role curRole : UserUtils.getRoleList()) {
				if (curRole.getId().equals(ROLEPARTYMEM)) {
					flag = false;
				}
			}*/
			for(String roleId : roleIds) {
				if (roleId.equals(ROLEPARTYMEM)) {
					flag = false;
				}
			}
			// 如果没有党员id 则进行添加单管
			if (flag) {
				List<Role> InsertroleList = Lists.newArrayList();
				InsertroleList.add(new Role(ROLEPARTYMEM));
				User userRole = new User(pbsPartymemreg.getUserid());
				userRole.setRoleList(InsertroleList);
				userDao.insertUserRole(userRole);
			}
		}
		// 保存党员
		pbsPartymemregService.updatepartymemstat(pbsPartymemreg);
		addMessage(redirectAttributes, "保存党员登记信息成功");
		return "redirect:" + Global.getAdminPath() + "/person/pbsPartymemreg/?repage";
	}

	// 取消登记申请
	@RequestMapping(value = "regcancel")
	public String regcancel(PbsPartymemreg pbsPartymemreg, RedirectAttributes redirectAttributes) {
		pbsPartymemregService.delete(pbsPartymemreg);
		addMessage(redirectAttributes, "删除党员登记信息成功");
		return "redirect:" + Global.getAdminPath() + "/sys/Mobile/personInfo";
	}

	// 解除登记绑定
	@RequestMapping(value = "regDelete")
	public String regDelete(PbsPartymemreg pbsPartymemreg, RedirectAttributes redirectAttributes) {
		// 删除党员登记 申请
		pbsPartymemregService.delete(pbsPartymemreg);
		// 删除党员用户 关系
		PbsPartymembind partymembindDto = new PbsPartymembind();
		partymembindDto.setSPrimarykey01(UserUtils.getUser().getId());
		pbsPartymembindService.deleteByUser(partymembindDto);
		addMessage(redirectAttributes, "删除党员登记信息成功");
		return "redirect:" + Global.getAdminPath() + "/sys/Mobile/personInfo";
	}
}