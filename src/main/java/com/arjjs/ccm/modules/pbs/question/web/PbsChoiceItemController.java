/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.web;

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
 * 选择题选项Controller
 * 
 * @author lc
 * @version 2018-06-09
 */
@Controller
@RequestMapping(value = "${adminPath}/question/pbsChoiceItem")
public class PbsChoiceItemController extends BaseController {

	@Autowired
	private PbsChoiceItemService pbsChoiceItemService;
	@Autowired
	private PbsQuestionObjectiveService pbsQuestionObjectiveService;

	@ModelAttribute
	public PbsChoiceItem get(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "sParentid", required = false) String sParentid) {
		PbsChoiceItem entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsChoiceItemService.get(id);
		}
		if (entity == null) {
			entity = new PbsChoiceItem();
			// 填充基本的类型与父id
			if (StringUtils.isNotBlank(sParentid)) {
				PbsQuestionObjective pbsQuestionObjective = pbsQuestionObjectiveService.get(sParentid);
				if (null != pbsQuestionObjective) {
					// 赋值相关的类型 与 父关系
					entity.setSType(pbsQuestionObjective.getSType());
					entity.setsParentid(pbsQuestionObjective);
				}
			}
		}
		return entity;
	}

	@RequiresPermissions("question:pbsChoiceItem:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsChoiceItem pbsChoiceItem, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsChoiceItem> page = pbsChoiceItemService.findPage(new Page<PbsChoiceItem>(request, response),
				pbsChoiceItem);
		model.addAttribute("page", page);
		return "pbs/question/bank/pbsChoiceItemList";
	}

	@RequiresPermissions("question:pbsChoiceItem:view")
	@RequestMapping(value = "form")
	public String form(PbsChoiceItem pbsChoiceItem, Model model) {
		model.addAttribute("pbsChoiceItem", pbsChoiceItem);
		return "pbs/question/bank/pbsChoiceItemForm";
	}

	@RequiresPermissions("question:pbsChoiceItem:edit")
	@RequestMapping(value = "save")
	public String save(PbsChoiceItem pbsChoiceItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsChoiceItem)) {
			return form(pbsChoiceItem, model);
		}
		pbsChoiceItemService.save(pbsChoiceItem);
		addMessage(redirectAttributes, "保存选择题选项成功");
		return "redirect:" + Global.getAdminPath() + "/question/pbsChoiceItem/?repage";
	}

	// 用于添加相关的试题的选项内容的方法
	@RequiresPermissions("question:pbsChoiceItem:edit")
	@RequestMapping(value = "Checksave")
	public String Checksave(PbsChoiceItem pbsChoiceItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsChoiceItem)) {
			return form(pbsChoiceItem, model);
		}
		pbsChoiceItemService.save(pbsChoiceItem);
		addMessage(redirectAttributes, "保存选择题选项成功");
		//返回题目页
		PbsQuestionObjective objective =  pbsQuestionObjectiveService.get(pbsChoiceItem.getsParentid());
		model.addAttribute("pbsQuestionObjective", objective);
		return "redirect:" + Global.getAdminPath() + "/question/pbsQuestionObjective/checkform/?id="+objective.getId();
	}

	@RequiresPermissions("question:pbsChoiceItem:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsChoiceItem pbsChoiceItem, RedirectAttributes redirectAttributes) {
		pbsChoiceItemService.delete(pbsChoiceItem);
		addMessage(redirectAttributes, "删除选择题选项成功");
		return "redirect:" + Global.getAdminPath() + "/question/pbsChoiceItem/?repage";
	}
	
	@RequestMapping(value = "additemform")
	public String additemform(PbsChoiceItem pbsChoiceItem, Model model) {
		model.addAttribute("pbsChoiceItem", pbsChoiceItem);
		return "/pbs/question/bank/pbsChoiceItemFormCheck";
	}


}