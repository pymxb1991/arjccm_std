/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.travel.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.act.entity.Act;
import com.arjjs.ccm.modules.act.service.ActTaskService;
import com.arjjs.ccm.modules.act.service.ActUtConfigService;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.plm.act.service.PlmActService;
import com.arjjs.ccm.modules.plm.travel.entity.PlmOfficialDocument;
import com.arjjs.ccm.modules.plm.travel.service.PlmOfficialDocumentService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.tool.PlmTypes;
import com.arjjs.ccm.tool.pdf.PdfDocumentGenerator;
import com.arjjs.ccm.tool.pdf.ResourceUitle;
import com.arjjs.ccm.tool.pdf.exception.DocumentGeneratingException;
import com.google.common.collect.Maps;

/**
 * 公文管理Controller
 * @author dongqikai
 * @version 2018-07-17
 */
@Controller
@RequestMapping(value = "${adminPath}/travel/plmOfficialDocument")
public class PlmOfficialDocumentController extends BaseController {

	@Autowired
	private PlmOfficialDocumentService plmOfficialDocumentService;
	
	@Autowired
	private ActTaskService actTaskService;
	@Autowired	
	private PlmActService plmActService;
	@Autowired
	private ActUtConfigService<PlmOfficialDocument> actUtConfigService;
	
	@ModelAttribute
	public PlmOfficialDocument get(@RequestParam(required=false) String id) {
		PlmOfficialDocument entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmOfficialDocumentService.get(id);
			//添加业务流程主表信息
			if(entity!=null) {
				String plmTypesId="";
				if (PlmOfficialDocument.TYPE_RECEIVE.equals(entity.getType())) {			
					plmTypesId=PlmTypes.OFFICIAL_DECUMENT_RECEIVE;
				} else if (PlmOfficialDocument.TYPE_SEND.equals(entity.getType())) {		 
					plmTypesId=PlmTypes.OFFICIAL_DECUMENT_SEND;
				}
				PlmAct plmAct = plmActService.getByTable(entity.getId(),plmTypesId);
				if(plmAct!=null) {
					entity.setPlmAct(plmAct);
				}
			}
		}
		if (entity == null){
			entity = new PlmOfficialDocument();
		}
		return entity;
	}
	

	@RequestMapping(value = {"list", ""})
	public String list(PlmOfficialDocument plmOfficialDocument, HttpServletRequest request, HttpServletResponse response, Model model,String type) {
    if(PlmOfficialDocument.TYPE_RECEIVE.equals(type)) {
    	plmOfficialDocument.setType(PlmOfficialDocument.TYPE_RECEIVE);
	}else if(PlmOfficialDocument.TYPE_SEND.equals(type)) {
    	plmOfficialDocument.setType(PlmOfficialDocument.TYPE_SEND);
	}
		Page<PlmOfficialDocument> page = plmOfficialDocumentService.findPage(new Page<PlmOfficialDocument>(request, response), plmOfficialDocument); 
		
		
		model.addAttribute("page", page);
		return "plm/travel/plmOfficialDocumentList";
	}

	
	@RequestMapping(value = "form")
	public String form(PlmOfficialDocument plmOfficialDocument, Model model) {
		String viewPage = "plmOfficialDocumentForm";
		if (StringUtils.isBlank(plmOfficialDocument.getId())) {
			String ProcDefId=plmOfficialDocument.getAct().getProcDefId();
			ProcDefId=ProcDefId.substring(0, ProcDefId.lastIndexOf(":")-2);
			if(ProcDefId.equals("official_document_receive")) {
				plmOfficialDocument.setType(PlmOfficialDocument.TYPE_RECEIVE);
			}else {
				plmOfficialDocument.setType(PlmOfficialDocument.TYPE_SEND);
			}
			plmOfficialDocument.setDate(new Date());
		}
		if (StringUtils.isNotBlank(plmOfficialDocument.getProcInsId())) {
			Act act = plmOfficialDocument.getAct();
			String taskDefKey = act.getTaskDefKey();
			if (act.isFinishTask()) {
				viewPage = "plmOfficialDocumentView";
			} else if ("modify".equals(taskDefKey)) {
				viewPage = "plmOfficialDocumentForm";
			}else if ("processEnd".equals(taskDefKey)) {
				viewPage = "plmOfficialDocumentAudit";
				//终点无审核 去掉驳回按钮
				model.addAttribute("rejectedBtn", false);
			}else {
				viewPage = "plmOfficialDocumentAudit";
				//终点审核 加驳回按钮
				model.addAttribute("rejectedBtn", true);
			} 
		}
		String cancelFlag = "0";
		if (StringUtils.isNotBlank(plmOfficialDocument.getCancelFlag()) && "02".equals(plmOfficialDocument.getCancelFlag())) {
			cancelFlag = "1";
		}
		model.addAttribute("cancelFlag", cancelFlag);
		model.addAttribute("plmOfficialDocument", plmOfficialDocument);
		return "plm/travel/" + viewPage;
	}


	@RequestMapping(value = "save")
	public String save(PlmOfficialDocument plmOfficialDocument, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmOfficialDocument)){
			return form(plmOfficialDocument, model);
		}
		plmOfficialDocumentService.save(plmOfficialDocument);
		
		String plmTypesId="";
		if (PlmOfficialDocument.TYPE_RECEIVE.equals(plmOfficialDocument.getType())) {			
			plmTypesId=PlmTypes.OFFICIAL_DECUMENT_RECEIVE;
		} else if (PlmOfficialDocument.TYPE_SEND.equals(plmOfficialDocument.getType())) {		 
			plmTypesId=PlmTypes.OFFICIAL_DECUMENT_SEND;
		}		
		//parameter ： 1、业务流程主表  2、流程配置id 3、业务表*
			plmActService.save(plmOfficialDocument.getPlmAct(),plmTypesId,plmOfficialDocument.getId());
		addMessage(redirectAttributes, "保存公文成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}
	
	
	@RequestMapping(value = "apply")
	public String apply(PlmOfficialDocument plmOfficialDocument, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmOfficialDocument)){
			return form(plmOfficialDocument, model);
		}
		plmOfficialDocumentService.save(plmOfficialDocument);
		if (StringUtils.isBlank(plmOfficialDocument.getProcInsId())) {
			Map<String, String> returnMap =null;
			String plmTypesId="";
			if (PlmOfficialDocument.TYPE_RECEIVE.equals(plmOfficialDocument.getType())) {
				 returnMap = actUtConfigService.getProcInsId(PlmTypes.OFFICIAL_DECUMENT_RECEIVE, plmOfficialDocument, plmOfficialDocument.getId());
				plmOfficialDocument.setProcInsId(returnMap.get("procInsId"));
				plmTypesId=PlmTypes.OFFICIAL_DECUMENT_RECEIVE;
			} else if (PlmOfficialDocument.TYPE_SEND.equals(plmOfficialDocument.getType())) {
				 returnMap = actUtConfigService.getProcInsId(PlmTypes.OFFICIAL_DECUMENT_SEND, plmOfficialDocument, plmOfficialDocument.getId());
				plmOfficialDocument.setProcInsId(returnMap.get("procInsId"));
				plmTypesId=PlmTypes.OFFICIAL_DECUMENT_SEND;
			}
			plmOfficialDocumentService.save(plmOfficialDocument);
			//4、保存业务流程主表*
			
			plmOfficialDocument.getPlmAct().setTitle(returnMap.get("title"));
			plmActService.save(plmOfficialDocument.getPlmAct(),plmTypesId,plmOfficialDocument.getId(),plmOfficialDocument.getProcInsId());
			
		} else {
			plmOfficialDocument.getAct().setComment(("yes".equals(plmOfficialDocument.getAct().getFlag())?"[重申] ":"[撤销] ")
					+ plmOfficialDocument.getAct().getComment());
			Map<String, Object> vars = Maps.newHashMap();
			
			vars.put("pass", "yes".equals(plmOfficialDocument.getAct().getFlag())? "1" : "0");
			actTaskService.complete(plmOfficialDocument.getAct().getTaskId(), plmOfficialDocument.getAct().getProcInsId(),
					plmOfficialDocument.getAct().getComment(), "", vars);
			//如果销毁，将业务流程主表状态置位“已销毁”*
			if(!"yes".equals(plmOfficialDocument.getAct().getFlag())){				
				plmActService.updateStatusToDestory(plmOfficialDocument.getPlmAct());
			}
		}
		addMessage(redirectAttributes, "提交申请成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}
	

	@RequestMapping(value = "saveAudit")
	public String saveAudit(PlmOfficialDocument plmOfficialDocument, Model model) {
		if (StringUtils.isBlank(plmOfficialDocument.getAct().getFlag())
				|| StringUtils.isBlank(plmOfficialDocument.getAct().getComment())){
			addMessage(model, "请填写审核意见。");
			return form(plmOfficialDocument, model);
		}
		// 对不同环节的业务逻辑进行操作*
		String taskDefKey = plmOfficialDocument.getAct().getTaskDefKey();
		// 最后一步流程且   需要审核
		if ("auditEnd".equals(taskDefKey)) {							
			// 若为最后一步审核，通过，将业务流程主表状态置位“已结束”
			if ("yes".equals(plmOfficialDocument.getAct().getFlag())) {
				plmActService.updateStatusToEnd(plmOfficialDocument.getPlmAct());      
			}
		}
		// 最后一步流程  不需要审核
		else if ("processEnd".equals(taskDefKey)) {				
			// 将业务流程主表状态置位“已结束”			
				plmActService.updateStatusToEnd(plmOfficialDocument.getPlmAct());      
			
		}
		// 未知环节，直接返回
		else if (StringUtils.isBlank(taskDefKey)) {
			return "redirect:" + adminPath + "/act/task/todo/";
		}
		 // 针对所有具有添加督办的环节，若为isSup字段不为空，添加督办信息
		if (StringUtils.isNotBlank(plmOfficialDocument.getPlmAct().getIsSup())) {
			plmActService.updateSup(plmOfficialDocument.getPlmAct());
		}
		plmOfficialDocumentService.auditSave(plmOfficialDocument);
		return "redirect:" + adminPath + "/act/task/todo/";
	}
	

	@RequestMapping(value = "delete")
	public String delete(PlmOfficialDocument plmOfficialDocument, RedirectAttributes redirectAttributes) {
		plmOfficialDocumentService.delete(plmOfficialDocument);
		addMessage(redirectAttributes, "删除公文成功");
		return "redirect:"+Global.getAdminPath()+"/travel/plmOfficialDocument/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "printPdfIo")
	public void printPdfIo(PlmOfficialDocument plmOfficialDocument, Model model, RedirectAttributes redirectAttributes ,HttpServletRequest request ,HttpServletResponse response ) throws DocumentGeneratingException{
		
		
		      //将对象转成map 并将时间类型格式化"yyyy-MM-dd"
		      Map<String, Object> purmap=ResourceUitle.transBean2Map(plmOfficialDocument);	
		      
		      //有数据字典的  要换成名称plmOfficialDocument.confidentiality, 'confident_type', '未选择'
		      //plmOfficialDocument.documentType, 'official_doc_type', '未选择'
		      //plmOfficialDocument.deadline, 'deadline_type', '未选择'
		      //plmOfficialDocument.urgent, 'urgent_type', '未选择'
		      purmap.put("confidentiality",DictUtils.getDictLabel(plmOfficialDocument.getConfidentiality(),"confident_type",""));	
		      purmap.put("documentType",DictUtils.getDictLabel(plmOfficialDocument.getDocumentType(),"official_doc_type",""));	
		      purmap.put("deadline",DictUtils.getDictLabel(plmOfficialDocument.getDeadline(),"deadline_type",""));	
		      purmap.put("urgent",DictUtils.getDictLabel(plmOfficialDocument.getUrgent(),"urgent_type",""));	
		      
		      if(StringUtils.isNotBlank(plmOfficialDocument.getPlmAct().getIsSup())){
		    	  purmap.put("isSup",DictUtils.getDictLabel(plmOfficialDocument.getPlmAct().getIsSup(),"yes_no",""));
		      }
		      //流转信息  actProcIns
		      plmOfficialDocument.getAct().setProcInsId(plmOfficialDocument.getProcInsId());
		      
			//1: ProcInsId   2审批内容跨列数Colspan      3审批标题跨列数titleColspan
		      purmap.put("actProcIns",actTaskService.histoicTable(plmOfficialDocument.getProcInsId(), "5" ,"1"));
		      
		      
		     //获取项目根路径
		     String path=request.getSession().getServletContext().getRealPath("/");
		       // classpath 中模板路径
				String template = "WEB-INF/views/plm/travel/plmOfficialDocumentViewTemplate.html";
				PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
				// 生成pdf 返回流			
				pdfGenerator.generate(template, purmap, path,response);			          		            
		              
		            		
	}
}