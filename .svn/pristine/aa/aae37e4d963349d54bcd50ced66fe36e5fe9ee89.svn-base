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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.course.entity.PbsCourseoperate;
import com.arjjs.ccm.modules.pbs.course.service.PbsCourseoperateService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

/**
 * 课程操作信息Controller
 * 
 * @author lc
 * @version 2018-04-14
 */
@Controller
@RequestMapping(value = "${adminPath}/course/pbsCourseoperate")
public class PbsCourseoperateController extends BaseController {

	@Autowired
	private PbsCourseoperateService pbsCourseoperateService;

	@ModelAttribute
	public PbsCourseoperate get(@RequestParam(required = false) String id) {
		PbsCourseoperate entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsCourseoperateService.get(id);
		}
		if (entity == null) {
			entity = new PbsCourseoperate();
		}
		return entity;
	}

	@RequiresPermissions("course:pbsCourseoperate:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsCourseoperate pbsCourseoperate, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsCourseoperate> page = pbsCourseoperateService.findPage(new Page<PbsCourseoperate>(request, response),
				pbsCourseoperate);
		model.addAttribute("page", page);
		return "pbs/course/pbsCourseoperateList";
	}

	@RequiresPermissions("course:pbsCourseoperate:view")
	@RequestMapping(value = "form")
	public String form(PbsCourseoperate pbsCourseoperate, Model model) {
		model.addAttribute("pbsCourseoperate", pbsCourseoperate);
		return "pbs/course/pbsCourseoperateForm";
	}

	@RequiresPermissions("course:pbsCourseoperate:edit")
	@RequestMapping(value = "save")
	public String save(PbsCourseoperate pbsCourseoperate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsCourseoperate)) {
			return form(pbsCourseoperate, model);
		}
		pbsCourseoperateService.save(pbsCourseoperate);
		addMessage(redirectAttributes, "保存课程操作信息成功");
		return "redirect:" + Global.getAdminPath() + "/course/pbsCourseoperate/?repage";
	}

	@RequiresPermissions("course:pbsCourseoperate:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsCourseoperate pbsCourseoperate, RedirectAttributes redirectAttributes) {
		pbsCourseoperateService.delete(pbsCourseoperate);
		addMessage(redirectAttributes, "删除课程操作信息成功");
		return "redirect:" + Global.getAdminPath() + "/course/pbsCourseoperate/?repage";
	}

	// 提交评论
	@ResponseBody
	@RequestMapping(value = "commentSave")
	public String commentSave(PbsCourseoperate pbsCourseoperate, Model model) {
		// 验证
		if (!beanValidator(model, pbsCourseoperate)) {
			return "false";
		}
		if (StringUtils.isBlank(pbsCourseoperate.getSContent())) {
			return "false";
		}
		// 添加 用户 id
		pbsCourseoperate.setSUserid(UserUtils.getUser());
		pbsCourseoperateService.save(pbsCourseoperate);
		return "sucess";
	}

	// 跳转 评论
	@RequestMapping(value = "courseComment")
	public String courseInfo(String id, Model model) {
		// 查询全部评论
		PbsCourseoperate pbsCourseoperateDto = new PbsCourseoperate();
		pbsCourseoperateDto.setSParentid(id);
		pbsCourseoperateDto.setSOptype("3");
		List<PbsCourseoperate> list = pbsCourseoperateService.findList(pbsCourseoperateDto);
		// 查看 全部课程评论
		model.addAttribute("commentList", list);
		// 查询用户的评论
		pbsCourseoperateDto.setSUserid(UserUtils.getUser());
		List<PbsCourseoperate> Userlist = pbsCourseoperateService.findList(pbsCourseoperateDto);
		// 查看 全部课程评论
		model.addAttribute("userComList", Userlist);
		return "/Nav-Study/Study/StudySPComment";
	}

	// 创建当前用户的观看时长
	@ResponseBody
	@RequestMapping(value = "updateOperate")
	public String updateOperate(PbsCourseoperate pbsCourseoperate, Model model) {

		// 更新操作者信息
		if (StringUtils.isNoneBlank(UserUtils.getPartymem().getId())) {
			pbsCourseoperate.setsBindmember(UserUtils.getPartymem());
		}
		pbsCourseoperate.setSUserid(UserUtils.getUser());
		// 更新时长
		if (StringUtils.isNoneBlank(pbsCourseoperate.getITime()) && (!pbsCourseoperate.getITime().equals("0"))) {
			pbsCourseoperateService.save(pbsCourseoperate);
		}
		// System.out.println(pbsCourseoperate.getITime());
		return SUCCESS;
	}

}