/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.web;

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
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActinotifications;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityrec;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActinotificationsService;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivityrecService;
import com.arjjs.ccm.modules.pbs.sys.entity.GeneralMethod;
import com.arjjs.ccm.modules.pbs.sys.service.PbsGeneralService;

/**
 * 活动信息Controller
 * 
 * @author lc
 * @version 2018-05-14
 */
@Controller
@RequestMapping(value = "${adminPath}/activity/pbsActivityrec")
public class PbsActivityrecController extends BaseController {

	@Autowired
	private PbsActivityrecService pbsActivityrecService;
	@Autowired
	private PbsActinotificationsService pbsActinotificationsService;
	
	@Autowired
	private PbsGeneralService pbsGeneralService;
	
	private static final String TABLEKEY ="pbs_activityrec";
	private static final String COLNAME ="s_title";
	@ModelAttribute
	public PbsActivityrec get(@RequestParam(required = false) String id) {
		PbsActivityrec entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsActivityrecService.get(id);
		}
		if (entity == null) {
			entity = new PbsActivityrec();
		}
		return entity;
	}

	@RequiresPermissions("activity:pbsActivityrec:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsActivityrec pbsActivityrec, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsActivityrec> page = pbsActivityrecService.findPage(new Page<PbsActivityrec>(request, response),
				pbsActivityrec);
		model.addAttribute("page", page);
		return "pbs/activity/pbsActivityrecList";
	}

	@RequiresPermissions("activity:pbsActivityrec:view")
	@RequestMapping(value = "form")
	public String form(PbsActivityrec pbsActivityrec, Model model) {
		PbsActinotifications pbsActinotificationsDto = new PbsActinotifications();
		pbsActinotificationsDto.setsActivityid(pbsActivityrec);
		// 赋值 人员
		List<PbsActinotifications> lists = pbsActinotificationsService.findList(pbsActinotificationsDto);
		pbsActivityrec.setPbsActinotificationList(lists);
		model.addAttribute("pbsActivityrec", pbsActivityrec);
		return "pbs/activity/pbsActivityrecForm";
	}

	@RequiresPermissions("activity:pbsActivityrec:edit")
	@RequestMapping(value = "save")
	public String save(PbsActivityrec pbsActivityrec, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsActivityrec)) {
			return form(pbsActivityrec, model);
		}
		// 判断当前的内容是否存在
		GeneralMethod generalMethod = new GeneralMethod();
		generalMethod.setTabletype(TABLEKEY);
		generalMethod.setId(pbsActivityrec.getId());
		generalMethod.setColumntype(COLNAME);
		generalMethod.setKey(pbsActivityrec.getSTitle());
		// 验证数据是否重复
		if (pbsGeneralService.checkExist(generalMethod)) {
			addMessage(model, "数据验证失败：该信息已经存在");
			return form(pbsActivityrec, model);
		}
		pbsActivityrecService.save(pbsActivityrec);
		addMessage(redirectAttributes, "保存活动信息成功");
		return "redirect:" + Global.getAdminPath() + "/activity/pbsActivityrec/?repage";
	}

	@RequiresPermissions("activity:pbsActivityrec:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsActivityrec pbsActivityrec, RedirectAttributes redirectAttributes) {
		pbsActivityrecService.delete(pbsActivityrec);
		addMessage(redirectAttributes, "删除活动信息成功");
		return "redirect:" + Global.getAdminPath() + "/activity/pbsActivityrec/?repage";
	}

	@RequestMapping(value = "namelist")
	public String namelist(PbsActivityrec pbsactivityrec, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsActivityrec> list = pbsActivityrecService.findList(pbsactivityrec);
		model.addAttribute("list", list);
		return "mapping/ActivityrecList";
	}
}