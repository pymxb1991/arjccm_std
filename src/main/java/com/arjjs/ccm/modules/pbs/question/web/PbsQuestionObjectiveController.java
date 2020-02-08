/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.web;

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
import com.arjjs.ccm.modules.pbs.question.entity.PbsChoiceItem;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionObjective;
import com.arjjs.ccm.modules.pbs.question.service.PbsChoiceItemService;
import com.arjjs.ccm.modules.pbs.question.service.PbsQuestionObjectiveService;

/**
 * 客观题信息Controller
 * 
 * @author lc
 * @version 2018-06-09
 */
@Controller
@RequestMapping(value = "${adminPath}/question/pbsQuestionObjective")
public class PbsQuestionObjectiveController extends BaseController {

	@Autowired
	private PbsQuestionObjectiveService pbsQuestionObjectiveService;
	@Autowired
	private PbsChoiceItemService pbsChoiceItemService;

	@ModelAttribute
	public PbsQuestionObjective get(@RequestParam(required = false) String id) {
		PbsQuestionObjective entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsQuestionObjectiveService.get(id);
		}
		if (entity == null) {
			entity = new PbsQuestionObjective();
		}
		return entity;
	}

	@RequiresPermissions("question:pbsQuestionObjective:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsQuestionObjective pbsQuestionObjective, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<PbsQuestionObjective> page = pbsQuestionObjectiveService
				.findPage(new Page<PbsQuestionObjective>(request, response), pbsQuestionObjective);
		model.addAttribute("page", page);
		return "pbs/question/bank/pbsQuestionObjectiveList";
	}

	@RequiresPermissions("question:pbsQuestionObjective:view")
	@RequestMapping(value = "addform")
	public String form(PbsQuestionObjective pbsQuestionObjective, Model model) {
		model.addAttribute("pbsQuestionObjective", pbsQuestionObjective);
		return "pbs/question/bank/pbsQuestionObjectiveForm";
	}

	@RequiresPermissions("question:pbsQuestionObjective:view")
	@RequestMapping(value = "checkform")
	public String checkform(PbsQuestionObjective pbsQuestionObjective, Model model) {
		model.addAttribute("pbsQuestionObjective", pbsQuestionObjective);
		// 更新了当前的选项信息
		PbsChoiceItem pbsChoiceItemDto = new PbsChoiceItem();
		pbsChoiceItemDto.setsParentid(pbsQuestionObjective);
		// 显示已经添加了 选项信息
		List<PbsChoiceItem> pbsChoiceItemlist = pbsChoiceItemService.findList(pbsChoiceItemDto);
		model.addAttribute("ChoiceItemlist", pbsChoiceItemlist);
		return "pbs/question/bank/pbsQuestionObjectiveFormCheck";
	}

	@RequiresPermissions("question:pbsQuestionObjective:edit")
	@RequestMapping(value = "save")
	public String save(PbsQuestionObjective pbsQuestionObjective, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsQuestionObjective)) {
			return form(pbsQuestionObjective, model);
		}
		pbsQuestionObjectiveService.save(pbsQuestionObjective);
		addMessage(redirectAttributes, "保存客观题信息成功");
		return "redirect:" + Global.getAdminPath() + "/question/pbsQuestionObjective/?repage";
	}

	@RequiresPermissions("question:pbsQuestionObjective:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsQuestionObjective pbsQuestionObjective, RedirectAttributes redirectAttributes) {
		pbsQuestionObjectiveService.delete(pbsQuestionObjective);
		addMessage(redirectAttributes, "删除客观题信息成功");
		return "redirect:" + Global.getAdminPath() + "/question/pbsQuestionObjective/?repage";
	}

	// 跳转相关的已经添加过的客观题添加选项的内容的页面
	@RequiresPermissions("question:pbsQuestionObjective:view")
	@RequestMapping(value = "additemform")
	public String additemform(PbsQuestionObjective pbsQuestionObjective, Model model) {
		PbsChoiceItem item = new PbsChoiceItem();
		//  赋值类型
		item.setSType(pbsQuestionObjective.getSType());
		// 赋值所属题目
		item.setsParentid(pbsQuestionObjective);
		model.addAttribute("pbsChoiceItem", item);
		return "/pbs/question/bank/pbsChoiceItemFormCheck";
	}

	// 获取所有的
	@RequiresPermissions("question:pbsQuestionObjective:view")
	@RequestMapping(value = "namelist")
	public String namelist(PbsQuestionObjective pbsQuestionObjective, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<PbsQuestionObjective> list = pbsQuestionObjectiveService.findList(pbsQuestionObjective);
		model.addAttribute("list", list);
		return "mapping/PbsQuestionObjectiveList";
	}

}