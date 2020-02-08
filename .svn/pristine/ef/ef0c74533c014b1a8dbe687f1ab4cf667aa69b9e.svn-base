/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymembind;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemService;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymembindService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.service.OfficeService;
//import com.arjjs.ccm.modules.sys.service.SystemService;

/**
 * 户籍人口表单Controller
 * 
 * @author arj
 * @version 2017-12-27
 */
@Controller
@RequestMapping(value = "${frontPath}/user")
public class PbsUserTestController extends BaseController {

	// 系统 -使用用户
	/*@Autowired
	private SystemService systemService;*/
	// 党员信息服务
	@Autowired
	private PbsPartymemService pbsPartymemService;
	// 党员信息关系服务
	@Autowired
	private PbsPartymembindService pbsPartymembindService;
	// 用户服务
	// @Autowired
	// private UserService userService;
	// 单位
	@Autowired
	private OfficeService officeService;

	// 党员list 信息
	@RequestMapping(value = { "pbspartymemlist", "" })
	public String pbspartymemlist(Office office, HttpServletRequest request, HttpServletResponse response, Model model) {
		PbsPartymem PbsPartymemDto = new PbsPartymem();
		List<PbsPartymem> list = pbsPartymemService.findList(PbsPartymemDto);
		// 向页面 添加对象信息
		model.addAttribute("usrList", list);
		return "/pbs/archives/archivesUser";
	}

	// 单位list 信息
	@RequestMapping(value = "officelist")
	public String officelist(HttpServletRequest request, HttpServletResponse response, Model model) {
		Office officeDto = new Office();
		officeDto.setParentIds("0");
		List<Office> list = officeService.findList(officeDto);
		model.addAttribute("ofcList", list);// 向页面添加对象信息
		return "/pbs/archives/archivesOffice";
	}

	// 用户list 信息
	@RequestMapping(value = "pbspartymeminfo")
	public String userInfo(@RequestParam(required = false) String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 用户信息
		PbsPartymem pbsPartymemDto = new PbsPartymem();
		pbsPartymemDto.setId(id);
		PbsPartymem pbspartymem = pbsPartymemService.get(id);
		// 向页面 添加对象信息
		PbsPartymembind pbsPartymembindDto = new PbsPartymembind();
		pbsPartymembindDto.setSPrimarykey01(id);
		// 关系
		List<PbsPartymembind> list = pbsPartymembindService.findList(pbsPartymembindDto);
		model.addAttribute("pbspartymem", pbspartymem);
		model.addAttribute("binds", list);

		return "/pbs/archives/archivesInfo";
	}
}