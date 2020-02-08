/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.opinion.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.plm.act.service.PlmActService;
import com.arjjs.ccm.modules.plm.opinion.entity.PlmOpinion;
import com.arjjs.ccm.modules.plm.opinion.service.PlmOpinionService;
import com.arjjs.ccm.tool.PlmTypes;

/**
 * 建议意见箱Controller
 * 
 * @author liucong
 * @version 2018-07-30
 */
@Controller
@RequestMapping(value = "${adminPath}/opinion/plmOpinion")
public class PlmOpinionController extends BaseController {

	@Autowired
	private PlmOpinionService plmOpinionService;
	@Autowired
	private PlmActService plmActService;

	@ModelAttribute
	public PlmOpinion get(@RequestParam(required = false) String id) {
		PlmOpinion entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = plmOpinionService.get(id);
			// 添加业务流程主表信息
			if(entity!=null) {
				PlmAct plmAct = plmActService.getByTable(entity.getId(), PlmTypes.PLM_OPINION);
				if(plmAct!=null) {
					entity.setPlmAct(plmAct);
				}
			}
		}
		if (entity == null) {
			entity = new PlmOpinion();
		}
		return entity;
	}

	
	@RequestMapping(value = { "list", "" })
	public String list(PlmOpinion plmOpinion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmOpinion> page = plmOpinionService.findPage(new Page<PlmOpinion>(request, response), plmOpinion);
		model.addAttribute("page", page);
		return "plm/opinion/plmOpinionList";
	}

	@RequestMapping(value = "form")
	public String form(PlmOpinion plmOpinion, Model model) {
		plmOpinion.getAct().setProcInsId(plmOpinion.getProcInsId());
		String view = "plmOpinionForm";
		if (StringUtils.isNotBlank(plmOpinion.getProcInsId())) {
			plmOpinion.getAct().setProcInsId(plmOpinion.getProcInsId());
			// 环节编号
			String taskDefKey = plmOpinion.getAct().getTaskDefKey();
			// 查看工单
			if (plmOpinion.getAct().isFinishTask()) {
				view = "plmOpinionView";
			} else if ("modify".equals(taskDefKey)) {
				// 修改环节
				view = "plmOpinionForm";
			} else if ("processEnd".equals(taskDefKey)) {
				view = "plmOpinionAudit";
				//终点无审核 去掉驳回按钮
				model.addAttribute("rejectedBtn", false);
			}else {
				// 审核环节
				view = "plmOpinionAudit";
				//终点审核 加驳回按钮
				model.addAttribute("rejectedBtn", true);
			}
		}
		String cancelFlag = "0";
		if (StringUtils.isNotBlank(plmOpinion.getCancelFlag()) && "02".equals(plmOpinion.getCancelFlag())) {
			cancelFlag = "1";
		}
		model.addAttribute("cancelFlag", cancelFlag);
		model.addAttribute("plmOpinion", plmOpinion);
		return "plm/opinion/" + view;
	}

	@RequestMapping(value = "save")
	public String save(PlmOpinion plmOpinion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmOpinion)) {
			return form(plmOpinion, model);
		}
		plmOpinionService.save(plmOpinion);
		addMessage(redirectAttributes, "提交建议意见成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}

	/**
	 * 保存不提交
	 * 
	 * @param plmEquApply
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "notCommit")
	public String notCommit(PlmOpinion plmOpinion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmOpinion)) {
			return form(plmOpinion, model);
		}
		plmOpinionService.notCommit(plmOpinion);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}

	/**
	 * 
	 * @param plmOpinion
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveAudit")
	public String saveAudit(PlmOpinion plmOpinion, Model model) {
		if (StringUtils.isBlank(plmOpinion.getAct().getFlag())
				|| StringUtils.isBlank(plmOpinion.getAct().getComment())) {
			addMessage(model, "请填写审核意见。");
			return form(plmOpinion, model);
		}
		plmOpinionService.auditSave(plmOpinion);
		return "redirect:" + adminPath + "/act/task/todo/";
	}

	@RequestMapping(value = "delete")
	public String delete(PlmOpinion plmOpinion, RedirectAttributes redirectAttributes) {
		plmOpinionService.delete(plmOpinion);
		addMessage(redirectAttributes, "删除建议意见成功");
		return "redirect:" + Global.getAdminPath() + "/opinion/plmOpinion/?repage";
	}

}