package com.arjjs.ccm.modules.pbs.question.web;


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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.course.entity.PbsCourseinfoEx;
import com.arjjs.ccm.modules.pbs.course.entity.PbsCourseoperate;
import com.arjjs.ccm.modules.pbs.course.service.PbsCourseinfoServiceEx;
import com.arjjs.ccm.modules.pbs.course.service.PbsCourseoperateService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

@Controller
@RequestMapping("${adminPath}/question/pbsTestOnlinePC")
public class PbsTestOnlinePCController extends BaseController {
	@Autowired
	private PbsCourseinfoServiceEx pbsCourseinfoServiceEx;
	@Autowired
	private PbsCourseoperateService pbsCourseoperateService;

	
	@ModelAttribute
	public PbsCourseinfoEx get(@RequestParam(required = false) String id) {
		PbsCourseinfoEx entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsCourseinfoServiceEx.get(id);
		}
		if (entity == null) {
			entity = new PbsCourseinfoEx();
		}
		return entity;
	}

	
	@RequestMapping(value = "coursePage")
	public String pageTo(PbsCourseinfoEx pbsCourseinfoEx, Model model,HttpServletRequest request,HttpServletResponse response) {
		// 跳转 课程列表页
		Page<PbsCourseinfoEx> page = pbsCourseinfoServiceEx.findPage(new Page<PbsCourseinfoEx>(request, response), pbsCourseinfoEx);
		// 课程列表
		model.addAttribute("page", page);
		return "/pbs/question/pbsStudySP";
	}
	
	@RequestMapping(value = "courseInfo")
	public String courseInfo(PbsCourseinfoEx pbsCourseinfoEx, Model model) {
		// 获取该课程的评论数
		int sum=pbsCourseoperateService.getCounts(pbsCourseinfoEx.getId());
		// 当前评论数
		model.addAttribute("commentSum", sum);
		//   查看 课程页
		model.addAttribute("pbsCourseinfoEx", pbsCourseinfoEx);
		return "/pbs/question/pbsStudySPInfo";
	}
	
	// 跳转 评论
	@RequestMapping(value = "courseComment")
	public String courseInfo(String id, Model model) {
		model.addAttribute("studyId", id);
		// 查询全部评论
		PbsCourseoperate pbsCourseoperateDto = new PbsCourseoperate();
		pbsCourseoperateDto.setSParentid(id);
		List<PbsCourseoperate> list = pbsCourseoperateService.findList(pbsCourseoperateDto);
		// 查看 全部课程评论
		model.addAttribute("commentList", list);
		// 查询用户的评论
		pbsCourseoperateDto.setSUserid(UserUtils.getUser());
		List<PbsCourseoperate> Userlist = pbsCourseoperateService.findList(pbsCourseoperateDto);
		// 查看 全部课程评论
		model.addAttribute("userComList", Userlist);
		return "/pbs/question/pbsStudySPComment";
	}
}
