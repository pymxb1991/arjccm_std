/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.course.web;

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
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.course.entity.PbsCourseinfoEx;
import com.arjjs.ccm.modules.pbs.course.service.PbsCourseinfoServiceEx;
import com.arjjs.ccm.modules.pbs.course.service.PbsCourseoperateService;

/**
 * 课程信息Controller
 * 
 * @author lc
 * @version 2018-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/course/pbsCourseinfo")
public class PbsCourseinfoExController extends BaseController {

	@Autowired
	private PbsCourseinfoServiceEx pbsCourseinfoService;
	@Autowired
	private PbsCourseoperateService pbsCourseoperateService;
	
	
	
	@ModelAttribute
	public PbsCourseinfoEx get(@RequestParam(required = false) String id) {
		PbsCourseinfoEx entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsCourseinfoService.get(id);
		}
		if (entity == null) {
			entity = new PbsCourseinfoEx();
		}
		return entity;
	}

	@RequiresPermissions("course:pbsCourseinfo:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsCourseinfoEx pbsCourseinfo, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsCourseinfoEx> page = pbsCourseinfoService.findPage(new Page<PbsCourseinfoEx>(request, response),
				pbsCourseinfo);
		model.addAttribute("page", page);
		return "pbs/course/pbsCourseinfoList";
	}

	@RequiresPermissions("course:pbsCourseinfo:view")
	@RequestMapping(value = "form")
	public String form(PbsCourseinfoEx pbsCourseinfo, Model model) {
		model.addAttribute("pbsCourseinfo", pbsCourseinfo);
		return "pbs/course/pbsCourseinfoForm";
	}

	@RequiresPermissions("course:pbsCourseinfo:edit")
	@RequestMapping(value = "save")
	public String save(PbsCourseinfoEx pbsCourseinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsCourseinfo)) {
			return form(pbsCourseinfo, model);
		}
		if (!StringUtils.isBlank(pbsCourseinfo.getSFileurl())) {
			String url = pbsCourseinfo.getSFileurl();
			int one = url.lastIndexOf(".");
			// 赋予文件类型
			pbsCourseinfo.setSFiletype(url.substring((one + 1), url.length()));
		}
		if (StringUtils.isBlank(pbsCourseinfo.getISort())) {
			pbsCourseinfo.setISort("1");
		}
		pbsCourseinfoService.save(pbsCourseinfo);
		addMessage(redirectAttributes, "保存课程信息成功");
		return "redirect:" + Global.getAdminPath() + "/course/pbsCourseinfo/?repage";
	}

	@RequiresPermissions("course:pbsCourseinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsCourseinfoEx pbsCourseinfo, RedirectAttributes redirectAttributes) {
		pbsCourseinfoService.delete(pbsCourseinfo);
		addMessage(redirectAttributes, "删除课程信息成功");
		return "redirect:" + Global.getAdminPath() + "/course/pbsCourseinfo/?repage";
	}

	@RequestMapping(value = "coursePage")
	public String pageTo(PbsCourseinfoEx pbsCourseinfo, Model model) {
		// 跳转 课程列表页
		List<PbsCourseinfoEx> List = pbsCourseinfoService.findList(pbsCourseinfo);
		// 课程列表
		model.addAttribute("list", List);
		return "/Nav-Study/Study/StudySP";
	}

	@RequestMapping(value = "courseInfo")
	public String courseInfo(PbsCourseinfoEx pbsCourseinfo, Model model) {
		// 获取该课程的评论数
		int sum=pbsCourseoperateService.getCounts(pbsCourseinfo.getId());
		// 当前评论数
		model.addAttribute("commentSum", sum);
		//   查看 课程页
		model.addAttribute("pbsCourseinfo", pbsCourseinfo);
		return "/Nav-Study/Study/StudySPInfo";
	}

 	
}