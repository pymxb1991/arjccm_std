/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.attendanceapply.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.attendance.entity.CcmWorkerAttendance;
import com.arjjs.ccm.modules.ccm.attendance.service.CcmWorkerAttendanceService;
import com.arjjs.ccm.modules.ccm.attendanceapply.entity.CcmWorkerAttendanceApply;
import com.arjjs.ccm.modules.ccm.attendanceapply.service.CcmWorkerAttendanceApplyService;
import com.arjjs.ccm.modules.ccm.rest.service.CcmRestOfficeService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 加班请假申请Controller
 * @author yi
 * @version 2019-11-04
 */
@Controller
@RequestMapping(value = "${adminPath}/attendanceapply/ccmWorkerAttendanceApply")
public class CcmWorkerAttendanceApplyController extends BaseController {

	@Autowired
	private CcmWorkerAttendanceApplyService ccmWorkerAttendanceApplyService;
	@Autowired
	private CcmWorkerAttendanceService ccmWorkerAttendanceService;
	@Autowired
	private CcmRestOfficeService restOfficeService;

	public static final String officetotalNum = "-1";   //总部门
	public static final String officeregionNum = "2";   //区域部门

	@ModelAttribute
	public CcmWorkerAttendanceApply get(@RequestParam(required=false) String id) {
		CcmWorkerAttendanceApply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmWorkerAttendanceApplyService.get(id);
		}
		if (entity == null){
			entity = new CcmWorkerAttendanceApply();
		}
		return entity;
	}

	/**
	 * type  2 请假申请   3 加班申请
	 * @param ccmWorkerAttendanceApply
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("attendanceapply:ccmWorkerAttendanceApply:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmWorkerAttendanceApply ccmWorkerAttendanceApply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmWorkerAttendanceApply> page = ccmWorkerAttendanceApplyService.findPage(new Page<CcmWorkerAttendanceApply>(request, response), ccmWorkerAttendanceApply);
		model.addAttribute("page", page);
		model.addAttribute("type" , ccmWorkerAttendanceApply.getType());
		return "ccm/attendance/ccmWorkerAttendanceApplyList";
	}

	@RequiresPermissions("attendanceapply:ccmWorkerAttendanceApply:view")
	@RequestMapping(value = "form")
	public String form(CcmWorkerAttendanceApply ccmWorkerAttendanceApply, Model model) {
		model.addAttribute("ccmWorkerAttendanceApply", ccmWorkerAttendanceApply);
		model.addAttribute("type" , ccmWorkerAttendanceApply.getType());
		return "ccm/attendance/ccmWorkerAttendanceApplyForm";
	}

	@RequiresPermissions("attendanceapply:ccmWorkerAttendanceApply:edit")
	@RequestMapping(value = "save")
	public String save(CcmWorkerAttendanceApply ccmWorkerAttendanceApply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmWorkerAttendanceApply)){
			return form(ccmWorkerAttendanceApply, model);
		}
 		ccmWorkerAttendanceApplyService.save(ccmWorkerAttendanceApply);
		CcmWorkerAttendance res = ccmWorkerAttendanceService.getByapplyId(ccmWorkerAttendanceApply.getId());
 		if(res==null && ccmWorkerAttendanceApply.getApplyType().equals("2")){
			CcmWorkerAttendance ccmWorkerAttendance = new CcmWorkerAttendance();
			BeanUtils.copyProperties(ccmWorkerAttendanceApply , ccmWorkerAttendance);
			ccmWorkerAttendance.setId("");
			ccmWorkerAttendance.setApplyId(ccmWorkerAttendanceApply.getId());
			ccmWorkerAttendanceService.save(ccmWorkerAttendance);
			addMessage(redirectAttributes, "申请通过");
			if(ccmWorkerAttendanceApply.getType().equals("2")){
				return "redirect:"+Global.getAdminPath()+"/attendance/ccmWorkerAttendance/leavelist?repage";
			} else if(ccmWorkerAttendanceApply.getType().equals("3")){
				return "redirect:"+Global.getAdminPath()+"/attendance/ccmWorkerAttendance/workingtimelist?repage";
			}
		}
		addMessage(redirectAttributes, "保存申请成功");
		return "redirect:"+Global.getAdminPath()+"/attendanceapply/ccmWorkerAttendanceApply/?repage&type="+ ccmWorkerAttendanceApply.getType();
	}
	
	@RequiresPermissions("attendanceapply:ccmWorkerAttendanceApply:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmWorkerAttendanceApply ccmWorkerAttendanceApply, RedirectAttributes redirectAttributes) {
		ccmWorkerAttendanceApplyService.delete(ccmWorkerAttendanceApply);
		addMessage(redirectAttributes, "删除加班请假申请成功");
		return "redirect:"+Global.getAdminPath()+"/attendanceapply/ccmWorkerAttendanceApply/?repage";
	}

}