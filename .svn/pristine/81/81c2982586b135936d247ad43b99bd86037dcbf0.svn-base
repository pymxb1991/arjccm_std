/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.web;

import java.util.Arrays;
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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivitysigninService;
import com.arjjs.ccm.modules.pbs.course.entity.PbsCourseoperate;
import com.arjjs.ccm.modules.pbs.course.service.PbsCourseoperateService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsDepartmentbind;
import com.arjjs.ccm.modules.pbs.person.entity.PbsLabelinfo;
import com.arjjs.ccm.modules.pbs.person.entity.PbsMemlabel;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.service.PbsDepartmentbindService;
import com.arjjs.ccm.modules.pbs.person.service.PbsLabelinfoService;
import com.arjjs.ccm.modules.pbs.person.service.PbsMemlabelService;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemService;

/**
 * 党员信息Controller
 * 
 * @author lc
 * @version 2018-03-24
 */
@Controller
@RequestMapping(value = "${adminPath}/person/pbsPartymem")
public class PbsPartymemController extends BaseController {

	// 党员
	@Autowired
	private PbsPartymemService pbsPartymemService;
	// 党员-部门
	@Autowired
	private PbsDepartmentbindService pbsDepartmentbindService;
	// 课程操作
	@Autowired
	private PbsCourseoperateService pbsCourseoperateService;
	// 活动签到
	@Autowired
	private PbsActivitysigninService pbsActivitysigninService;
	// 党员标签
	@Autowired
	private PbsMemlabelService pbsMemlabelService;
	// 标签列表
	@Autowired
	private PbsLabelinfoService pbsLabelinfoService;

	@ModelAttribute
	public PbsPartymem get(@RequestParam(required = false) String id) {
		PbsPartymem entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsPartymemService.get(id);
		}
		if (entity == null) {
			entity = new PbsPartymem();
		}
		return entity;
	}

	@RequiresPermissions("person:pbsPartymem:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsPartymem pbsPartymem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsPartymem> page = pbsPartymemService.findPage(new Page<PbsPartymem>(request, response), pbsPartymem);
		model.addAttribute("page", page);
		// 获取的标签信息
		List<PbsLabelinfo> pbsLabelinfos = pbsLabelinfoService.findList(new PbsLabelinfo());
		model.addAttribute("pbsLabelinfos", pbsLabelinfos);
		return "pbs/person/pbsPartymemList";
	}

	@RequestMapping("image")
	public String image(PbsPartymem pbsPartymem, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		Page<PbsPartymem> page = pbsPartymemService.findPage(new Page<PbsPartymem>(request, response), pbsPartymem);
		model.addAttribute("page", page);
		return "pbs/person/pbsPartymemImage";
	}

	@RequiresPermissions("person:pbsPartymem:view")
	@RequestMapping(value = "form")
	public String form(PbsPartymem pbsPartymem, Model model) {
		if(StringUtils.isNotBlank(pbsPartymem.getId())) {
			// 党员标签
			PbsMemlabel pbsMemlabelDto = new PbsMemlabel();
			pbsMemlabelDto.setsMemberid(pbsPartymem);
			List<PbsMemlabel> pbsMemlabels = pbsMemlabelService.findList(pbsMemlabelDto);
			// 党员标签
			if (pbsMemlabels.size() > 0) {
				pbsPartymem.setPbsMemlabel(pbsMemlabels.get(0));
			}
		}
		model.addAttribute("pbsPartymem", pbsPartymem);
		// 获取的标签信息
		List<PbsLabelinfo> pbsLabelinfos = pbsLabelinfoService.findList(new PbsLabelinfo());
		model.addAttribute("pbsLabelinfos", pbsLabelinfos);
		return "pbs/person/pbsPartymemForm";
	}

	@RequiresPermissions("person:pbsPartymem:edit")
	@RequestMapping(value = "save")
	public String save(PbsPartymem pbsPartymem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsPartymem)) {
			return form(pbsPartymem, model);
		}
		// 验证数据是否重复
		if (pbsPartymemService.checkExist(pbsPartymem)) {
			addMessage(model, "数据验证失败： 身份证信息数据重复");
			return form(pbsPartymem, model);
		}
		pbsPartymemService.save(pbsPartymem);
		addMessage(redirectAttributes, "保存党员信息成功");
		return "redirect:" + Global.getAdminPath() + "/person/pbsPartymem/?repage";
	}

	@RequiresPermissions("person:pbsPartymem:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsPartymem pbsPartymem, RedirectAttributes redirectAttributes) {
		pbsPartymemService.delete(pbsPartymem);
		pbsMemlabelService.deleteBymemId(pbsPartymem.getId());
		addMessage(redirectAttributes, "删除党员信息成功");
		return "redirect:" + Global.getAdminPath() + "/person/pbsPartymem/?repage";
	}

	// 查询用
	@RequestMapping(value = "findPbsListByOffice")
	public String findPbsListByOffice(String officeid, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		PbsPartymem pbsPartymemDto = new PbsPartymem();
		pbsPartymemDto.setOfficeid(officeid);
		List<PbsPartymem> list = pbsPartymemService.findListByOffice(pbsPartymemDto);
		model.addAttribute("list", list);
		return "mapping/MemberList";
	}

	// 查看任务信息图页面
	@RequiresPermissions("person:pbsPartymem:view")
	@RequestMapping("checkDetail")
	public String checkDetail(PbsPartymem pbsPartymem, String[] obj, Model model) {
		List<String> idList = Arrays.asList(obj);
		model.addAttribute("idList", idList);
		model.addAttribute("pbsPartymem", pbsPartymem);
		// 查询关联部门信息
		List<PbsDepartmentbind> PbsDepartmentbindList = pbsDepartmentbindService.findRecordBypbsPartymemId(pbsPartymem);
		model.addAttribute("PbsDepartmentbindList", PbsDepartmentbindList);
		// 根据党员id查询参加课程总数
		List<PbsCourseoperate> pbsCourseoperate = pbsCourseoperateService.getSumByMemberId(pbsPartymem.getId());
		model.addAttribute("pbsCourseoperate", pbsCourseoperate);
		// 根据党员id查询参加活动的总数
		int activitySum = pbsActivitysigninService.getActivitySumByMemberId(pbsPartymem.getId());
		model.addAttribute("activitySum", activitySum);
		List<String> departName = pbsPartymemService.findDepartName(pbsPartymem.getId());
		if(departName != null && departName.size() > 0){
		    model.addAttribute("departName",departName.get(0));
		}
		return "pbs/person/pbsPartymemInfo";
	}
}