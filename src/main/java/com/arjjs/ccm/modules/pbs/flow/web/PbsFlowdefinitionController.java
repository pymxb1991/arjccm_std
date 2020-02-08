/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.web;

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
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowdefinition;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlowdefinitionService;
import com.arjjs.ccm.modules.pbs.sys.entity.GeneralMethod;
import com.arjjs.ccm.modules.pbs.sys.service.PbsGeneralService;

/**
 * 流程定义信息Controller
 * 
 * @author lc
 * @version 2018-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/flow/pbsFlowdefinition")
public class PbsFlowdefinitionController extends BaseController {

	@Autowired
	private PbsFlowdefinitionService pbsFlowdefinitionService;
	@Autowired
	private PbsGeneralService pbsGeneralDao;
	
	private static final String PBSFLOWDEFINITION= "pbs_flowdefinition";
	private static final String TABLEKEY= "s_name";
	

	@ModelAttribute
	public PbsFlowdefinition get(@RequestParam(required = false) String id) {
		PbsFlowdefinition entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsFlowdefinitionService.get(id);
		}
		if (entity == null) {
			entity = new PbsFlowdefinition();
		}
		return entity;
	}

	@RequiresPermissions("flow:pbsFlowdefinition:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsFlowdefinition pbsFlowdefinition, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsFlowdefinition> page = pbsFlowdefinitionService.findPage(new Page<PbsFlowdefinition>(request, response),
				pbsFlowdefinition);
		model.addAttribute("page", page);
		return "pbs/flow/pbsFlowdefinitionList";
	}

	@RequiresPermissions("flow:pbsFlowdefinition:view")
	@RequestMapping(value = "form")
	public String form(PbsFlowdefinition pbsFlowdefinition, Model model) {
		model.addAttribute("pbsFlowdefinition", pbsFlowdefinition);
		return "pbs/flow/pbsFlowdefinitionForm";
	}

	@RequiresPermissions("flow:pbsFlowdefinition:edit")
	@RequestMapping(value = "save")
	public String save(PbsFlowdefinition pbsFlowdefinition, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsFlowdefinition)) {
			return form(pbsFlowdefinition, model);
		}
		// 判断当前的内容是否存在
		GeneralMethod generalMethod = new GeneralMethod();
		generalMethod.setTabletype(PBSFLOWDEFINITION);
		generalMethod.setId(pbsFlowdefinition.getId());
		generalMethod.setColumntype(TABLEKEY);
		generalMethod.setKey(pbsFlowdefinition.getSName());
		// 验证数据是否重复
		if (pbsGeneralDao.checkExist(generalMethod)) {
			addMessage(model, "数据验证失败：该信息已经存在");
			return form(pbsFlowdefinition, model);
		}
		pbsFlowdefinitionService.save(pbsFlowdefinition);
		addMessage(redirectAttributes, "保存流程定义信息成功");
		return "redirect:" + Global.getAdminPath() + "/flow/pbsFlowdefinition/?repage";
	}

	@RequiresPermissions("flow:pbsFlowdefinition:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsFlowdefinition pbsFlowdefinition, RedirectAttributes redirectAttributes) {
		pbsFlowdefinitionService.delete(pbsFlowdefinition);
		addMessage(redirectAttributes, "删除流程定义信息成功");
		return "redirect:" + Global.getAdminPath() + "/flow/pbsFlowdefinition/?repage";
	}

	// 获取 所有的 类型
	@RequestMapping(value = "namelist")
	public String namelist(PbsFlowdefinition pbsFlowdefinition, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		pbsFlowdefinition.setSStartstat("1");
		List<PbsFlowdefinition> list = pbsFlowdefinitionService.findList(pbsFlowdefinition);
		model.addAttribute("list", list);
		return "mapping/FlowDefinitionList";
	}

	// 修改页面
	@RequiresPermissions("flow:pbsFlowdefinition:view")
	@RequestMapping(value = "checkform")
	public String Checkform(PbsFlowdefinition pbsFlowdefinition, Model model) {
		model.addAttribute("pbsFlowdefinition", pbsFlowdefinition);
		return "pbs/flow/flowWork/pbsFlowdefinitionForm";
	}

}