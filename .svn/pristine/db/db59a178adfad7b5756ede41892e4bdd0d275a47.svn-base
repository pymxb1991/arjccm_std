/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.web;

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
import com.arjjs.ccm.modules.pbs.common.FlowServiceUtil;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlownode;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowworknode;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlownodeService;
//import com.arjjs.ccm.modules.pbs.flow.service.PbsFlowworkService;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlowworknodeService;

/**
 * 工作节点记录Controller
 * 
 * @author lc
 * @version 2018-04-23
 */
@Controller
@RequestMapping(value = "${adminPath}/flow/pbsFlowworknode")
public class PbsFlowworknodeController extends BaseController {

	@Autowired
	private PbsFlowworknodeService pbsFlowworknodeService;
	@Autowired
	private PbsFlownodeService pbsFlownodeService;
	/*@Autowired
	private PbsFlowworkService PbsFlowworkService;*/

	@ModelAttribute
	public PbsFlowworknode get(@RequestParam(required = false) String id) {
		PbsFlowworknode entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsFlowworknodeService.get(id);
		}
		if (entity == null) {
			entity = new PbsFlowworknode();
		}
		return entity;
	}

	@RequiresPermissions("flow:pbsFlowworknode:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsFlowworknode pbsFlowworknode, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsFlowworknode> page = pbsFlowworknodeService.findPage(new Page<PbsFlowworknode>(request, response),
				pbsFlowworknode);
		model.addAttribute("page", page);
		return "pbs/flow/pbsFlowworknodeList";
	}

	@RequiresPermissions("flow:pbsFlowworknode:view")
	@RequestMapping(value = "form")
	public String form(PbsFlowworknode pbsFlowworknode, Model model) {
		model.addAttribute("pbsFlowworknode", pbsFlowworknode);
		return "pbs/flow/pbsFlowworknodeForm";
	}

	@RequiresPermissions("flow:pbsFlowworknode:edit")
	@RequestMapping(value = "save")
	public String save(PbsFlowworknode pbsFlowworknode, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsFlowworknode)) {
			return form(pbsFlowworknode, model);
		}
		pbsFlowworknodeService.save(pbsFlowworknode);
		addMessage(redirectAttributes, "保存工作节点记录成功");
		return "redirect:" + Global.getAdminPath() + "/flow/pbsFlowworknode/?repage";
	}

	@RequiresPermissions("flow:pbsFlowworknode:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsFlowworknode pbsFlowworknode, RedirectAttributes redirectAttributes) {
		pbsFlowworknodeService.delete(pbsFlowworknode);
		addMessage(redirectAttributes, "删除工作节点记录成功");
		return "redirect:" + Global.getAdminPath() + "/flow/pbsFlowworknode/?repage";
	}

	@RequestMapping(value = "passhandle")
	public String passhandle(PbsFlowworknode pbsFlowworknode, RedirectAttributes redirectAttributes) {
		// 创建下一个节点 并 完成当前的节点
		pbsFlowworknodeService.PassHandle(pbsFlowworknode);
		PbsFlownode PbsFlownode = pbsFlownodeService.get(pbsFlowworknode.getsNodeid().getId());
		// 如果是当前的 id是最大id 则进行
		String maxid = pbsFlownodeService.findnodeMaxSort(PbsFlownode.getsFlowid().getId());
		if (maxid.equals(PbsFlownode.getsSort())) {
			FlowServiceUtil.rountPass(pbsFlowworknode.getsFlowid().getsTypecode(), pbsFlowworknode.getSBindkey());
		}
		return "redirect:" + Global.getAdminPath() + "/flow/pbsFlowworknode/?repage";
	}

	@RequestMapping(value = "refusehandle")
	public String handle(PbsFlowworknode pbsFlowworknode, RedirectAttributes redirectAttributes) {
		pbsFlowworknodeService.RefuseHandle(pbsFlowworknode);
		// 执行相关的方法
		FlowServiceUtil.rountRefuse(pbsFlowworknode.getsFlowid().getsTypecode(), pbsFlowworknode.getSBindkey());
		return "redirect:" + Global.getAdminPath() + "/flow/pbsFlowworknode/?repage";
	}

}