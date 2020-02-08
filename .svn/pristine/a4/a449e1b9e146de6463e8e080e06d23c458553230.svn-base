/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.oa.web;

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
import com.arjjs.ccm.modules.ccm.oa.entity.CcmOfficialDocument;
import com.arjjs.ccm.modules.ccm.oa.service.CcmOfficialDocumentService;
import com.arjjs.ccm.modules.oa.entity.TestAudit;

/**
 * 公文Controller
 * @author pengjianqiang
 * @version 2018-03-19
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/ccmOfficialDocument")
public class CcmOfficialDocumentController extends BaseController {

	@Autowired
	private CcmOfficialDocumentService ccmOfficialDocumentService;
	
	@ModelAttribute
	public CcmOfficialDocument get(@RequestParam(required=false) String id) {
		CcmOfficialDocument entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmOfficialDocumentService.get(id);
		}
		if (entity == null){
			entity = new CcmOfficialDocument();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:ccmOfficialDocument:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmOfficialDocument ccmOfficialDocument, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmOfficialDocument> page = ccmOfficialDocumentService.findPage(new Page<CcmOfficialDocument>(request, response), ccmOfficialDocument); 
		model.addAttribute("page", page);
		return "ccm/oa/ccmOfficialDocumentList";
	}

	@RequiresPermissions("oa:ccmOfficialDocument:view")
	@RequestMapping(value = "form")
	public String form(CcmOfficialDocument ccmOfficialDocument, Model model) {
		ccmOfficialDocument.getAct().setProcInsId(ccmOfficialDocument.getProcInsId());
		
		/*model.addAttribute("ccmOfficialDocument", ccmOfficialDocument);
		return "ccm/oa/ccmOfficialDocumentForm";
		*/

		String view = "ccmOfficialDocumentForm";
		
		// 查看审批申请单
		if (StringUtils.isNotBlank(ccmOfficialDocument.getId())){

			// 环节编号
			String taskDefKey = ccmOfficialDocument.getAct().getTaskDefKey();
			
			// 查看工单
			if(ccmOfficialDocument.getAct().isFinishTask()){
				view = "ccmOfficialDocumentView";
			}
			// 修改环节
			else if ("modify1".equals(taskDefKey)){
				view = "ccmOfficialDocumentForm";
			}
			// 审核环节
			else if ("app1".equals(taskDefKey)){
				view = "ccmOfficialDocumentAudit";
			}
			// 兑现环节
			else if ("app2".equals(taskDefKey)){
				view = "ccmOfficialDocumentAudit";
			}
		}

		model.addAttribute("ccmOfficialDocument", ccmOfficialDocument);
		return "ccm/oa/" + view;
		
	}

	@RequiresPermissions("oa:ccmOfficialDocument:edit")
	@RequestMapping(value = "save")
	public String save(CcmOfficialDocument ccmOfficialDocument, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmOfficialDocument)){
			return form(ccmOfficialDocument, model);
		}
		System.out.println(ccmOfficialDocument.getProcInsId()+"------------------");
		ccmOfficialDocumentService.save(ccmOfficialDocument);
		addMessage(redirectAttributes, "提交审批'" + ccmOfficialDocument.getSubject() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/oa/ccmOfficialDocument/?repage";
	}

	/**
	 * 工单执行（完成任务）
	 * @param testAudit
	 * @param model
	 * @return
	 */
	@RequiresPermissions("oa:ccmOfficialDocument:edit")
	@RequestMapping(value = "saveAudit")
	public String saveAudit(CcmOfficialDocument ccmOfficialDocument, Model model) {
		if (StringUtils.isBlank(ccmOfficialDocument.getAct().getFlag())
				|| StringUtils.isBlank(ccmOfficialDocument.getAct().getComment())){
			addMessage(model, "请填写审核意见。");
			return form(ccmOfficialDocument, model);
		}
		ccmOfficialDocumentService.auditSave(ccmOfficialDocument);
		return "redirect:" + adminPath + "/act/task/todo/";
	}
	
	@RequiresPermissions("oa:ccmOfficialDocument:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmOfficialDocument ccmOfficialDocument, RedirectAttributes redirectAttributes) {
		ccmOfficialDocumentService.delete(ccmOfficialDocument);
		addMessage(redirectAttributes, "删除公文成功");
		return "redirect:"+Global.getAdminPath()+"/oa/ccmOfficialDocument/?repage";
	}

}