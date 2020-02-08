/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.task.web;

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
import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.task.entity.PbsTaskrec;
import com.arjjs.ccm.modules.pbs.task.service.PbsTaskrecService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

/**
 * 工作安排记录Controller
 * 
 * @author lc
 * @version 2018-05-03
 */
@Controller
@RequestMapping(value = "${adminPath}/task/pbsTaskrec")
public class PbsTaskrecController extends BaseController {

	@Autowired
	private PbsTaskrecService pbsTaskrecService;

	@ModelAttribute
	public PbsTaskrec get(@RequestParam(required = false) String id) {
		PbsTaskrec entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsTaskrecService.get(id);
		}
		if (entity == null) {
			entity = new PbsTaskrec();
		}
		return entity;
	}

	@RequiresPermissions("task:pbsTaskrec:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsTaskrec pbsTaskrec, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsTaskrec> page = pbsTaskrecService.findPage(new Page<PbsTaskrec>(request, response), pbsTaskrec);
		model.addAttribute("page", page);
		return "pbs/task/pbsTaskrecList";
	}

	@RequiresPermissions("task:pbsTaskrec:view")
	@RequestMapping(value = "form")
	public String form(PbsTaskrec pbsTaskrec, Model model) {
		model.addAttribute("pbsTaskrec", pbsTaskrec);
		return "pbs/task/pbsTaskrecForm";
	}

	@RequiresPermissions("task:pbsTaskrec:edit")
	@RequestMapping(value = "save")
	public String save(PbsTaskrec pbsTaskrec, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsTaskrec)) {
			return form(pbsTaskrec, model);
		}
		pbsTaskrecService.save(pbsTaskrec);
		addMessage(redirectAttributes, "保存工作安排记录成功");
		return "redirect:" + Global.getAdminPath() + "/task/pbsTaskrec/?repage";
	}

	@RequiresPermissions("task:pbsTaskrec:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsTaskrec pbsTaskrec, RedirectAttributes redirectAttributes) {
		pbsTaskrecService.delete(pbsTaskrec);
		addMessage(redirectAttributes, "删除工作安排记录成功");
		return "redirect:" + Global.getAdminPath() + "/task/pbsTaskrec/?repage";
	}

	// 获取 所有的 类型
	@RequestMapping(value = "namelist")
	public String namelist(PbsTaskrec pbsTaskrec, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 当前的党员
		 PbsPartymem curPartymem =  UserUtils.getPartymem();
		// 查询所有
		// pbsTaskrec.setSStatus("0");
		// 当前的接收人和发布人
		if (("0").equals(pbsTaskrec.getGetType())) {
			pbsTaskrec.setIsrelevance(true);
			pbsTaskrec.setsBindmember(curPartymem);
			pbsTaskrec.setsExecutor(curPartymem);
		}
		// 当前的接收人
		if (("1").equals(pbsTaskrec.getGetType())) {
			pbsTaskrec.setsBindmember(curPartymem);
		}
		// 当前的发布人
		if (("2").equals(pbsTaskrec.getGetType())) {
			pbsTaskrec.setsBindmember(curPartymem);
		}
		List<PbsTaskrec> list = pbsTaskrecService.findList(pbsTaskrec);
		model.addAttribute("list", list);
		return "mapping/TaskrecList";
	}
	// 获取 所有的 类型
	@RequestMapping(value = "namelists")
	public String namelists(PbsTaskrec pbsTaskrec, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 当前的党员
		 PbsPartymem curPartymem =  UserUtils.getPartymem();
		// 查询所有
		// pbsTaskrec.setSStatus("0");
		// 当前的接收人和发布人
		if (("0").equals(pbsTaskrec.getGetType())) {
			pbsTaskrec.setIsrelevance(true);
			pbsTaskrec.setsBindmember(curPartymem);
			pbsTaskrec.setsExecutor(curPartymem);
		}
		// 当前的接收人
		if (("1").equals(pbsTaskrec.getGetType())) {
			pbsTaskrec.setsBindmember(curPartymem);
		}
		// 当前的发布人
		if (("2").equals(pbsTaskrec.getGetType())) {
			pbsTaskrec.setsBindmember(curPartymem);
		}
		List<PbsTaskrec> list = pbsTaskrecService.findList(pbsTaskrec);
		model.addAttribute("list", list);
		return "mapping/TaskrecListForGeography";
	}
}