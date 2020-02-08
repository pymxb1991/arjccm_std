/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.web;

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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymembind;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemService;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymembindService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

/**
 * 党员表关系Controller
 * 
 * @author lc
 * @version 2018-03-24
 */
@Controller
@RequestMapping(value = "${adminPath}/person/pbsPartymembind")
public class PbsPartymembindController extends BaseController {

	// 党员-用户关系services
	@Autowired
	private PbsPartymembindService pbsPartymembindService;
	// 党员services
	@Autowired
	private PbsPartymemService pbsPartymemService;
	

	@ModelAttribute
	public PbsPartymembind get(@RequestParam(required = false) String id,
			@RequestParam(required = false) String sourceTab, @RequestParam(required = false) String partymemid) {
		PbsPartymembind entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsPartymembindService.get(id);
		}
		if (entity == null) {
			entity = new PbsPartymembind();
		}
		// 当前的 党员id 不为空 则进行查询并存入到数据中
		if (StringUtils.isNotBlank(partymemid)) {
			PbsPartymem partymem = pbsPartymemService.get(partymemid);
			entity.setPartymem(partymem);
		}
		// 如果当前用户已经拥有 党员id
		if (StringUtils.isNotBlank(entity.getSPartymemid())) {
			PbsPartymem partymem = pbsPartymemService.get(entity.getSPartymemid());
			entity.setPartymem(partymem);
		}
		return entity;
	}

	// 显示list结果
	@RequiresPermissions("person:pbsPartymembind:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsPartymembind pbsPartymembind, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsPartymembind> page = pbsPartymembindService.findPage(new Page<PbsPartymembind>(request, response),
				pbsPartymembind);
		model.addAttribute("page", page);
		return "pbs/person/pbsPartymembindList";
	}

	// 显示详细页面
	@RequiresPermissions("person:pbsPartymembind:view")
	@RequestMapping(value = "form")
	public String form(PbsPartymembind pbsPartymembind, Model model) {
		model.addAttribute("pbsPartymembind", pbsPartymembind);

		return "/pbs/person/partymembind/checkBindFormForUser";
	}

	// 保存相关页面
	@RequiresPermissions("person:pbsPartymembind:edit")
	@RequestMapping(value = "save")
	public String save(PbsPartymembind pbsPartymembind, Model model, RedirectAttributes redirectAttributes) {
		// 验证对象信息
		if (!beanValidator(model, pbsPartymembind)) {
			return form(pbsPartymembind, model);
		}
		// 验证数据是否重复
		if (pbsPartymembindService.checkExist(pbsPartymembind)) {
			addMessage(model, "数据验证失败：该用户已经拥有该党员信息");
			// 判断是否 为添加页面内容。
			if (("add").equals(pbsPartymembind.getPageTurn())) {
				return adduser(pbsPartymembind, model);
			} else {
				return form(pbsPartymembind, model);
			}
		}
		// 对于 党员 用户关系 表的 内容 进行添加。
		pbsPartymembindService.save(pbsPartymembind);
		addMessage(redirectAttributes, "保存党员用户关系成功");
		// 判断页面的 返回内容 与返回路径
		if (("add").equals(pbsPartymembind.getPageTurn())) {
			// 如果是添加页面则进行添跳转到 党员页面
			return "redirect:" + Global.getAdminPath() + "/person/pbsPartymem/?repage";
		}
		return "redirect:" + Global.getAdminPath() + "/person/pbsPartymembind/?repage";
	}

	// 删除
	@RequiresPermissions("person:pbsPartymembind:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsPartymembind pbsPartymembind, RedirectAttributes redirectAttributes) {
		pbsPartymembindService.delete(pbsPartymembind);
		addMessage(redirectAttributes, "删除党员用户关系成功");
		return "redirect:" + Global.getAdminPath() + "/person/pbsPartymembind/?repage";
	}

	// adduser - 为现有的党员信息添加 用户归属
	@RequiresPermissions("person:pbsPartymembind:view")
	@RequestMapping(value = "adduser")
	public String adduser(PbsPartymembind pbsPartymembind, Model model) {
		// 如果当前用户已经拥有 党员id
		if (StringUtils.isNotBlank(pbsPartymembind.getSPartymemid())) {
			PbsPartymem partymem = pbsPartymemService.get(pbsPartymembind.getSPartymemid());
			pbsPartymembind.setPartymem(partymem);
		}
		model.addAttribute("pbsPartymembind", pbsPartymembind);
		return "pbs/person/partymembind/addBindFormForUser";
	}


	// 删除
	@RequestMapping(value = "deleteByUser")
	public String deleteByUser( RedirectAttributes redirectAttributes) {
		//  创建党员关系 
		PbsPartymembind pbsPartymembindDto = new PbsPartymembind();
		pbsPartymembindDto.setSPrimarykey01(UserUtils.getUser().getId());
		// 删除党员表 
		pbsPartymembindService.deleteByUser(pbsPartymembindDto);
		addMessage(redirectAttributes, "删除党员用户关系成功");
		return "redirect:" + Global.getAdminPath() + " ";
	}
}