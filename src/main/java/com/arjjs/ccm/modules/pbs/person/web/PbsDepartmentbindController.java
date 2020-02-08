/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.person.entity.PbsDepartmentbind;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.service.PbsDepartmentbindService;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemService;
import com.arjjs.ccm.modules.pbs.sys.service.PbsDepartmentetcService;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsOffice;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 党员部门关系Controller
 * 
 * @author lc
 * @version 2018-04-03
 */
@Controller
@RequestMapping(value = "${adminPath}/person/pbsDepartmentbind")
public class PbsDepartmentbindController extends BaseController {

	@Autowired
	private PbsDepartmentbindService pbsDepartmentbindService;
	@Autowired
	private PbsPartymemService pbsPartymemService;
	@Autowired
	private PbsDepartmentetcService pbsDepartmentetcService;

	@ModelAttribute
	public PbsDepartmentbind get(@RequestParam(required = false) String id,
			@RequestParam(required = false) String partymemid) {
		PbsDepartmentbind entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsDepartmentbindService.get(id);
		}
		if (entity == null) {
			entity = new PbsDepartmentbind();
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

	@RequiresPermissions("person:pbsDepartmentbind:view")
	@RequestMapping(value = "index")
	public String index(PbsDepartmentbind pbsDepartmentbind, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		return "/pbs/person/pbsDepartmentbindIndex";
	}

	@RequiresPermissions("person:pbsDepartmentbind:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsDepartmentbind pbsDepartmentbind, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		PbsPartymem CurPartymem = UserUtils.getPartymem();
		List<PbsOffice> listOffice = new ArrayList<>();
		if (StringUtils.isNotBlank(CurPartymem.getId())) {
			// 当前的 用户 为党员
			PbsDepartmentbind pbsDepartmentbindDto = new PbsDepartmentbind();
			pbsDepartmentbindDto.setSPartymemid(CurPartymem.getId());
			List<PbsDepartmentbind> pbsDepartmentbinds = pbsDepartmentbindService.findList(pbsDepartmentbindDto);
			//
			for (PbsDepartmentbind bind : pbsDepartmentbinds) {
				PbsOffice officeNew = new PbsOffice();
				officeNew.setType("2");
				officeNew.setId(bind.getSDepartmentid());
				listOffice.add(officeNew);
			}
		}
		List<PbsOffice> list = pbsDepartmentetcService.getAllOfficeWithMem(listOffice);
		// 部门列表信息
		String OfficeString = Collections3.extractToString(list, "id", ",");
		// TODO 其实需要用contains 但是数据是手写 未使用 UUID 所以使用先遍历相等
		// 部门信息
		List<PbsDepartmentbind> pbsDepartmentbinds = pbsDepartmentbindService.findList(pbsDepartmentbind);
		// 部门
		List<PbsDepartmentbind> pbsDepartmentbindList = Lists.newArrayList();
		for (PbsDepartmentbind Departmentbind : pbsDepartmentbinds) {
			// 分割字符串
			String[] compareDep = OfficeString.split(",");
			for (String com : compareDep) {
				if (com.equals(Departmentbind.getSDepartmentid())) {
					pbsDepartmentbindList.add(Departmentbind);
				}
			}
		}

		Page<PbsDepartmentbind> page = new Page<PbsDepartmentbind>(request, response);
		// 部门列表
		page.setList(pbsDepartmentbindList);
		page.setCount(pbsDepartmentbindList.size());
		model.addAttribute("page", page);
		return "pbs/person/pbsDepartmentbindList";
	}

	@RequiresPermissions("person:pbsDepartmentbind:view")
	@RequestMapping(value = "form")
	public String form(PbsDepartmentbind pbsDepartmentbind, Model model) {
		model.addAttribute("pbsDepartmentbind", pbsDepartmentbind);
		return "/pbs/person/partymembind/checkBindFormOffice";
	}

	@RequiresPermissions("person:pbsDepartmentbind:edit")
	@RequestMapping(value = "save")
	public String save(PbsDepartmentbind pbsDepartmentbind, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsDepartmentbind)) {
			return form(pbsDepartmentbind, model);
		}
		// 验证数据是否重复
		if (pbsDepartmentbindService.checkExist(pbsDepartmentbind)) {
			addMessage(model, "数据验证失败：该部门已经拥有该党员信息");
			// 判断返回 添加 或是修改页面
			if (("add").equals(pbsDepartmentbind.getPageTurn())) {
				return addoffice(pbsDepartmentbind, model);
			} else {
				return form(pbsDepartmentbind, model);
			}
		}
		// 添加信息
		pbsDepartmentbindService.save(pbsDepartmentbind);
		addMessage(redirectAttributes, "保存党员部门关系成功");
		// 如果是 添加页面 保存 默认 返回 党员 基础信息页面
		if (("add").equals(pbsDepartmentbind.getPageTurn())) {
			// 如果是添加页面则进行添跳转到 党员页面
			return "redirect:" + Global.getAdminPath() + "/person/pbsPartymem/?repage";
		}
		return "redirect:" + Global.getAdminPath() + "/person/pbsDepartmentbind/?repage";
	}

	@RequiresPermissions("person:pbsDepartmentbind:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsDepartmentbind pbsDepartmentbind, RedirectAttributes redirectAttributes) {
		pbsDepartmentbindService.delete(pbsDepartmentbind);
		addMessage(redirectAttributes, "删除党员部门关系成功");
		return "redirect:" + Global.getAdminPath() + "/person/pbsDepartmentbind/?repage";
	}

	// addoffice - 为现有的党员信息添加 区域信息
	@RequiresPermissions("person:pbsPartymembind:view")
	@RequestMapping(value = "addoffice")
	public String addoffice(PbsDepartmentbind pbsPartymembind, Model model) {
		// 如果验证时 被退回 则进行子对象的添加
		if (StringUtils.isNotBlank(pbsPartymembind.getSPartymemid())) {
			PbsPartymem partymem = pbsPartymemService.get(pbsPartymembind.getSPartymemid());
			pbsPartymembind.setPartymem(partymem);
		}
		model.addAttribute("pbsPartymembind", pbsPartymembind);
		return "/pbs/person/partymembind/addBindFormOffice";
	}

	// 查看当前的所有的 党员信息
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String id,
			@RequestParam(required = false) String officeId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		PbsDepartmentbind pbsDepartmentbindDto = new PbsDepartmentbind();
		// 组织id
		// pbsDepartmentbindDto.setsDepartmentidSelect(id);
		pbsDepartmentbindDto.setsDepartmentidSelect(officeId);
		List<PbsDepartmentbind> list = pbsDepartmentbindService.findList(pbsDepartmentbindDto);
		for (int i = 0; i < list.size(); i++) {
			PbsDepartmentbind e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", "u_" + e.getSPartymemid());
			map.put("pId", officeId);
			map.put("name", StringUtils.replace(e.getPartymemname(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}

	// 获取当前的党员用户的所在的党支部
	@ResponseBody
	@RequestMapping(value = "selfOffice")
	public List<PbsDepartmentbind> selfOffice() {
		String id = UserUtils.getPartymem().getId();
		if (StringUtils.isNoneBlank(id)) {
			// 查询当前的党支部关系信息
			PbsDepartmentbind pbsDepartmentbindDto = new PbsDepartmentbind();
			pbsDepartmentbindDto.setSPartymemid(id);
			List<PbsDepartmentbind> departmentbindList = pbsDepartmentbindService.findList(pbsDepartmentbindDto);
			return departmentbindList;
		}
		return null;
	}

	@RequestMapping(value = "namelist")
	public String namelist(Model model) {
		String id = UserUtils.getPartymem().getId();
		if (StringUtils.isNoneBlank(id)) {
			// 查询当前的党支部关系信息
			PbsDepartmentbind pbsDepartmentbindDto = new PbsDepartmentbind();
			pbsDepartmentbindDto.setSPartymemid(id);
			List<PbsDepartmentbind> departmentbindList = pbsDepartmentbindService.findList(pbsDepartmentbindDto);
			model.addAttribute("list", departmentbindList);
		}
		return "mapping/DepartList";
	}

}