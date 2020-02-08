/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.vote.web;

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
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteItem;
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteSubject;
import com.arjjs.ccm.modules.pbs.vote.service.PbsVoteItemService;

/**
 * 投票题目选项信息Controller
 * 
 * @author lc
 * @version 2018-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/vote/pbsVoteItem")
public class PbsVoteItemController extends BaseController {

	@Autowired
	private PbsVoteItemService pbsVoteItemService;

	@ModelAttribute
	public PbsVoteItem get(@RequestParam(required = false) String id,
			@RequestParam(required = false) String sParentid) {
		PbsVoteItem entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsVoteItemService.get(id);
		}
		if (entity == null) {
			entity = new PbsVoteItem();
		}
		if (StringUtils.isNotBlank(sParentid)) {
			entity.setsParentid(new PbsVoteSubject(sParentid));
		}
		return entity;
	}

	@RequiresPermissions("vote:pbsVoteItem:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsVoteItem pbsVoteItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsVoteItem> page = pbsVoteItemService.findPage(new Page<PbsVoteItem>(request, response), pbsVoteItem);
		model.addAttribute("page", page);
		return "pbs/vote/pbsVoteItemList";
	}

	@RequiresPermissions("vote:pbsVoteItem:view")
	@RequestMapping(value = "form")
	public String form(PbsVoteItem pbsVoteItem, Model model) {
		model.addAttribute("pbsVoteItem", pbsVoteItem);
		return "pbs/vote/pbsVoteItemForm";
	}

	// 通过主题进行添加
	@RequiresPermissions("vote:pbsVoteTopic:view")
	@RequestMapping(value = "addformtopic")
	public String addformtopic(PbsVoteItem pbsVoteItem, Model model) {
		model.addAttribute("pbsVoteItem", pbsVoteItem);
		return "pbs/vote/topic/pbsVoteItemFormCheckTopic";
	}

	// 通过题目进行添加
	@RequiresPermissions("vote:pbsVoteTopic:view")
	@RequestMapping(value = "addformsubject")
	public String addformsubject(PbsVoteItem pbsVoteItem, Model model) {
		model.addAttribute("pbsVoteItem", pbsVoteItem);
		return "pbs/vote/subject/pbsVoteItemFormCheckSubject";
	}

	@RequiresPermissions("vote:pbsVoteItem:edit")
	@RequestMapping(value = "save")
	public String save(PbsVoteItem pbsVoteItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsVoteItem)) {
			return form(pbsVoteItem, model);
		}
		pbsVoteItemService.save(pbsVoteItem);
		addMessage(redirectAttributes, "保存投票题目选项信息成功");
		return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteItem/?repage";
	}

	// 通过主题进行添加
	@RequiresPermissions("vote:pbsVoteItem:edit")
	@RequestMapping(value = "addsavetopic")
	public String addsavetopic(PbsVoteItem pbsVoteItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsVoteItem)) {
			return form(pbsVoteItem, model);
		}
		pbsVoteItemService.save(pbsVoteItem);
		addMessage(redirectAttributes, "保存投票题目选项信息成功");
		return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteSubject/addsubjectform?id="
				+ pbsVoteItem.getsParentid().getId();
	}

	// 通过题目进行添加
	@RequiresPermissions("vote:pbsVoteItem:edit")
	@RequestMapping(value = "addsavesubject")
	public String addsavesubject(PbsVoteItem pbsVoteItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsVoteItem)) {
			return form(pbsVoteItem, model);
		}
		pbsVoteItemService.save(pbsVoteItem);
		addMessage(redirectAttributes, "保存投票题目选项信息成功");
		return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteSubject/form?id="
				+ pbsVoteItem.getsParentid().getId();
	}

	@RequiresPermissions("vote:pbsVoteItem:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsVoteItem pbsVoteItem, RedirectAttributes redirectAttributes) {
		pbsVoteItemService.delete(pbsVoteItem);
		addMessage(redirectAttributes, "删除投票题目选项信息成功");
		return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteItem/?repage";
	}

}