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
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowtype;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlowtypeService;
import com.arjjs.ccm.modules.pbs.sys.entity.GeneralMethod;
import com.arjjs.ccm.modules.pbs.sys.service.PbsGeneralService;

/**
 * 流程类型Controller
 * 
 * @author lc
 * @version 2018-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/flow/pbsFlowtype")
public class PbsFlowtypeController extends BaseController {

	@Autowired
	private PbsFlowtypeService pbsFlowtypeService;
	@Autowired
	private PbsGeneralService pbsGeneralDao;
	
	private static final String PBSFLOWTYPE= "pbs_flowtype";
	private static final String TABLEKEY= "s_name";
	
	
	@ModelAttribute
	public PbsFlowtype get(@RequestParam(required = false) String id) {
		PbsFlowtype entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsFlowtypeService.get(id);
		}
		if (entity == null) {
			entity = new PbsFlowtype();
		}
		return entity;
	}

	@RequiresPermissions("flow:pbsFlowtype:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsFlowtype pbsFlowtype, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsFlowtype> page = pbsFlowtypeService.findPage(new Page<PbsFlowtype>(request, response), pbsFlowtype);
		model.addAttribute("page", page);
		return "pbs/flow/pbsFlowtypeList";
	}

	@RequiresPermissions("flow:pbsFlowtype:edit")
	@RequestMapping(value = "form")
	public String form(PbsFlowtype pbsFlowtype, Model model) {
		model.addAttribute("pbsFlowtype", pbsFlowtype);
		return "pbs/flow/pbsFlowtypeForm";
	}

	@RequiresPermissions("flow:pbsFlowtype:edit")
	@RequestMapping(value = "save")
	public String save(PbsFlowtype pbsFlowtype, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsFlowtype)) {
			return form(pbsFlowtype, model);
		}
		// 判断当前的内容是否存在
		GeneralMethod generalMethod = new GeneralMethod();
		generalMethod.setTabletype(PBSFLOWTYPE);
		generalMethod.setId(pbsFlowtype.getId());
		generalMethod.setColumntype(TABLEKEY);
		generalMethod.setKey(pbsFlowtype.getSName());
		// 验证数据是否重复
		if (pbsGeneralDao.checkExist(generalMethod)) {
			addMessage(model, "数据验证失败：该信息已经存在");
			return form(pbsFlowtype, model);
		}
		pbsFlowtypeService.save(pbsFlowtype);
		addMessage(redirectAttributes, "保存流程类型成功");
		return "redirect:" + Global.getAdminPath() + "/flow/pbsFlowtype/?repage";
	}

	@RequiresPermissions("flow:pbsFlowtype:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsFlowtype pbsFlowtype, RedirectAttributes redirectAttributes) {
		pbsFlowtypeService.delete(pbsFlowtype);
		addMessage(redirectAttributes, "删除流程类型成功");
		return "redirect:" + Global.getAdminPath() + "/flow/pbsFlowtype/?repage";
	}

	//获取 所有的 类型
	@RequiresPermissions("flow:pbsFlowtype:view")
	@RequestMapping(value = "namelist")
	public String namelist(PbsFlowtype pbsFlowtype, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsFlowtype> list = pbsFlowtypeService.findList(pbsFlowtype);
		model.addAttribute("list", list);
		return "mapping/FlowTypeList";
	}
}