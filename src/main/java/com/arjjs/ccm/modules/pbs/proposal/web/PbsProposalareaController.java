/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.proposal.web;

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
import com.arjjs.ccm.modules.pbs.proposal.entity.PbsProposalarea;
import com.arjjs.ccm.modules.pbs.proposal.service.PbsProposalareaService;
import com.arjjs.ccm.modules.pbs.sys.entity.GeneralMethod;
import com.arjjs.ccm.modules.pbs.sys.service.PbsGeneralService;

/**
 * 建议分区Controller
 * 
 * @author lc
 * @version 2018-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/proposal/pbsProposalarea")
public class PbsProposalareaController extends BaseController {

	@Autowired
	private PbsProposalareaService pbsProposalareaService;
	@Autowired
	private PbsGeneralService pbsGeneralDao;

	private static final String TABLE = "pbs_proposalarea";
	private static final String TABLEKEY = "s_name";

	@ModelAttribute
	public PbsProposalarea get(@RequestParam(required = false) String id) {
		PbsProposalarea entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsProposalareaService.get(id);
		}
		if (entity == null) {
			entity = new PbsProposalarea();
		}
		return entity;
	}

	@RequiresPermissions("proposal:pbsProposalarea:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsProposalarea pbsProposalarea, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsProposalarea> page = pbsProposalareaService.findPage(new Page<PbsProposalarea>(request, response),
				pbsProposalarea);
		model.addAttribute("page", page);
		return "pbs/proposal/pbsProposalareaList";
	}

	@RequiresPermissions("proposal:pbsProposalarea:view")
	@RequestMapping(value = "form")
	public String form(PbsProposalarea pbsProposalarea, Model model) {
		model.addAttribute("pbsProposalarea", pbsProposalarea);
		return "pbs/proposal/pbsProposalareaForm";
	}

	@RequiresPermissions("proposal:pbsProposalarea:edit")
	@RequestMapping(value = "save")
	public String save(PbsProposalarea pbsProposalarea, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsProposalarea)) {
			return form(pbsProposalarea, model);
		}
		// 判断当前的内容是否存在
		GeneralMethod generalMethod = new GeneralMethod();
		generalMethod.setTabletype(TABLE);
		generalMethod.setId(pbsProposalarea.getId());
		generalMethod.setColumntype(TABLEKEY);
		generalMethod.setKey(pbsProposalarea.getSName());
		// 验证数据是否重复
		if (pbsGeneralDao.checkExist(generalMethod)) {
			addMessage(model, "数据验证失败：该信息已经存在");
			return form(pbsProposalarea, model);
		}
		pbsProposalareaService.save(pbsProposalarea);
		addMessage(redirectAttributes, "保存建议分区成功");
		return "redirect:" + Global.getAdminPath() + "/proposal/pbsProposalarea/?repage";
	}

	@RequiresPermissions("proposal:pbsProposalarea:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsProposalarea pbsProposalarea, RedirectAttributes redirectAttributes) {
		pbsProposalareaService.delete(pbsProposalarea);
		addMessage(redirectAttributes, "删除建议分区成功");
		return "redirect:" + Global.getAdminPath() + "/proposal/pbsProposalarea/?repage";
	}

	// 获取 所有的 类型
	@RequestMapping(value = "namelist")
	public String namelist(PbsProposalarea pbsProposalarea, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsProposalarea> list = pbsProposalareaService.findList(pbsProposalarea);
		model.addAttribute("list", list);
		return "mapping/PbsProposalareaList";
	}	

}