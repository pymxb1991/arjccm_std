/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

/**
 * 党员部门关系Controller
 * 
 * @author lc
 * @version 2018-04-03
 */
@Controller
@RequestMapping(value = "${adminPath}/pbsDepartmentbind/Pc")
public class DepartmentbindPcController extends BaseController {

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

	@RequestMapping(value = "index")
	public String index(PbsDepartmentbind pbsDepartmentbind, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		return "pbs/person/pc/pbsDepartmentbindIndex";
	}

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
		page.setCount(pbsDepartmentbindList.size());
		page.setList(pbsDepartmentbindList);
		model.addAttribute("page", page);
		return "pbs/person/pc/pbsDepartmentbindList";
	}

}