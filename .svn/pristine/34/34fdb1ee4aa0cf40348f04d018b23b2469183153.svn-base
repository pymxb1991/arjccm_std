/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.vote.web;

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
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteItem;
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteSubject;
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteTopic;
import com.arjjs.ccm.modules.pbs.vote.service.PbsVoteItemService;
import com.arjjs.ccm.modules.pbs.vote.service.PbsVoteSubjectService;
import com.arjjs.ccm.modules.pbs.vote.service.PbsVoteTopicService;

/**
 * 投票题目Controller
 * 
 * @author lc
 * @version 2018-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/vote/pbsVoteSubject")
public class PbsVoteSubjectController extends BaseController {

	@Autowired
	private PbsVoteTopicService pbsVoteTopicService;
	@Autowired
	private PbsVoteSubjectService pbsVoteSubjectService;
	@Autowired
	private PbsVoteItemService pbsVoteItemService;

	@ModelAttribute
	public PbsVoteSubject get(@RequestParam(required = false) String id,
			@RequestParam(required = false) String sParentid) {
		PbsVoteSubject entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsVoteSubjectService.get(id);
		}
		if (entity == null) {
			entity = new PbsVoteSubject();
		}
		// 若是主题id不为空 则进行赋值
		if (StringUtils.isNotBlank(sParentid)) {
			entity.setsParentid(new PbsVoteTopic(sParentid));
		}
		return entity;
	}

	@RequiresPermissions("vote:pbsVoteSubject:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsVoteSubject pbsVoteSubject, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsVoteSubject> page = pbsVoteSubjectService.findPage(new Page<PbsVoteSubject>(request, response),
				pbsVoteSubject);
		model.addAttribute("page", page);
		return "pbs/vote/subject/pbsVoteSubjectList";
	}

	@RequiresPermissions("vote:pbsVoteSubject:view")
	@RequestMapping(value = "form")
	public String form(PbsVoteSubject pbsVoteSubject, Model model) {
		model.addAttribute("pbsVoteSubject", pbsVoteSubject);
		if(StringUtils.isNoneBlank(pbsVoteSubject.getId())) {
		// 显示所有的 选项列
		PbsVoteItem pbsVoteItemDto = new PbsVoteItem();
		pbsVoteItemDto.setsParentid(pbsVoteSubject);
		List<PbsVoteItem> itemList = pbsVoteItemService.findList(pbsVoteItemDto);
		model.addAttribute("itemList", itemList);
		}
		return "pbs/vote/subject/pbsVoteSubjectForm";
	}

	// 通过主题进行添加
	@RequiresPermissions("vote:pbsVoteTopic:view")
	@RequestMapping(value = "addsubjectform")
	public String addsubjectform(PbsVoteSubject pbsVoteSubject, Model model) {
		model.addAttribute("pbsVoteSubject", pbsVoteSubject);
		if(StringUtils.isNoneBlank(pbsVoteSubject.getId())) {
			// 显示所有的 选项列
			PbsVoteItem pbsVoteItemDto = new PbsVoteItem();
			pbsVoteItemDto.setsParentid(pbsVoteSubject);
			List<PbsVoteItem> itemList = pbsVoteItemService.findList(pbsVoteItemDto);
			model.addAttribute("itemList", itemList);
		}
		return "pbs/vote/topic/pbsVoteSubjectFormCheckTopic";
	}

	@RequiresPermissions("vote:pbsVoteSubject:edit")
	@RequestMapping(value = "save")
	public String save(PbsVoteSubject pbsVoteSubject, Model model, RedirectAttributes redirectAttributes) {
		if(pbsVoteSubject.getsParentid().getId().equals("0"))
		return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteSubject/?repage";
		if (!beanValidator(model, pbsVoteSubject)) {
			return form(pbsVoteSubject, model);
		}
		pbsVoteSubjectService.save(pbsVoteSubject);
		addMessage(redirectAttributes, "保存投票题目成功");
		return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteSubject/?repage";
	}

	// 通过主题页进行添加题目
	@RequiresPermissions("vote:pbsVoteSubject:edit")
	@RequestMapping(value = "addsubjectsave")
	public String addsubjectsave(PbsVoteSubject pbsVoteSubject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsVoteSubject)) {
			return form(pbsVoteSubject, model);
		}
		pbsVoteSubjectService.save(pbsVoteSubject);
		addMessage(redirectAttributes, "保存投票题目成功");
		PbsVoteTopic pbsVoteTopic =  pbsVoteTopicService.get(pbsVoteSubject.getsParentid().getId());
		// 完成操作后 返回不同的主题页面
		if (("0").equals(pbsVoteTopic.getSBelongfunc())) {
			return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteTopic/form?id="+ pbsVoteSubject.getsParentid().getId();
		} else {
			return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteTopic/formsurvey?id="+ pbsVoteSubject.getsParentid().getId();
		}
	}

	@RequiresPermissions("vote:pbsVoteSubject:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsVoteSubject pbsVoteSubject, RedirectAttributes redirectAttributes) {
		pbsVoteSubjectService.delete(pbsVoteSubject);
		addMessage(redirectAttributes, "删除投票题目成功");
		return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteSubject/?repage";
	}

	// 获取所有题目内容
	@RequiresPermissions("vote:pbsVoteSubject:edit")
	@RequestMapping(value = "namelist")
	public String namelist(PbsVoteSubject pbsVoteSubject, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsVoteSubject> list = pbsVoteSubjectService.findList(pbsVoteSubject);
		model.addAttribute("list", list);
		return "mapping/VoteSubject";
	}

}