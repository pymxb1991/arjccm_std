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
import com.arjjs.ccm.modules.plm.travel.entity.PlmTravelManage;
import com.arjjs.ccm.modules.plm.travel.service.PlmTravelManageService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.PlmTypes;
import com.arjjs.ccm.tool.pdf.PdfDocumentGenerator;
import com.arjjs.ccm.tool.pdf.ResourceUitle;
import com.arjjs.ccm.tool.pdf.exception.DocumentGeneratingException;
import com.google.common.collect.Maps;

/**
 * 出差管理Controller
 * @author dongqikai
 * @version 2018-07-13
 */
@Controller
@RequestMapping(value = "${adminPath}/travel/plmTravelManage")
public class PlmTravelManageController extends BaseController {

	@Autowired
	private PlmTravelManageService plmTravelManageService;
	
	@Autowired
	private ActTaskService actTaskService;
	@Autowired	
	private PlmActService plmActService;
	@Autowired
	private ActUtConfigService<PlmTravelManage> actUtConfigService;
	
	@ModelAttribute
	public PlmTravelManage get(@RequestParam(required=false) String id) {
		PlmTravelManage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmTravelManageService.get(id);
			//添加业务流程主表信息
			if(entity!=null) {
				PlmAct plmAct = plmActService.getByTable(entity.getId(), PlmTypes.TRAVEL_APPLY_ID);
				if(plmAct!=null) {
					entity.setPlmAct(plmAct);
				}
			}
		}
		if (entity == null){
			entity = new PlmTravelManage();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(PlmTravelManage plmTravelManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmTravelManage> page = plmTravelManageService.findPage(new Page<PlmTravelManage>(request, response), plmTravelManage); 
		model.addAttribute("page", page);
		return "plm/travel/plmTravelManageList";
	}


	@RequestMapping(value = "form")
	public String form(PlmTravelManage plmTravelManage, Model model) {
		String viewPage = "plmTravelManageForm";
		if (StringUtils.isBlank(plmTravelManage.getId())) {
			plmTravelManage.setFldApplicant(UserUtils.getUser());
			plmTravelManage.setFldDept(UserUtils.getUser().getOffice());
			
			
			plmTravelManage.setFldDt(new Date());
		}
		if (StringUtils.isNotBlank(plmTravelManage.getProcInsId())) {
			Act act = plmTravelManage.getAct();
			String taskDefKey = act.getTaskDefKey();
			if (act.isFinishTask()) {
				viewPage = "plmTravelManageView";
			} else if ("modify".equals(taskDefKey)) {
				viewPage = "plmTravelManageForm";
			}else if ("processEnd".equals(taskDefKey)) {
				viewPage = "plmTravelManageAudit";
				//终点无审核 去掉驳回按钮
				model.addAttribute("rejectedBtn", false);
			}else {
				viewPage = "plmTravelManageAudit";
				//终点审核 加驳回按钮
				model.addAttribute("rejectedBtn", true);
			}
		}
		String cancelFlag = "0";
		if (StringUtils.isNotBlank(plmTravelManage.getCancelFlag()) && "02".equals(plmTravelManage.getCancelFlag())) {
			cancelFlag = "1";
		}
		model.addAttribute("cancelFlag", cancelFlag);
		model.addAttribute("plmTravelManage", plmTravelManage);
		return "plm/travel/" + viewPage;
	}

	
	@RequestMapping(value = "save")
	public String save(PlmTravelManage plmTravelManage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmTravelManage)){
			return form(plmTravelManage, model);
		}
		plmTravelManageService.save(plmTravelManage);
		//parameter ： 1、业务流程主表  2、流程配置id 3、业务表*
				plmActService.save(plmTravelManage.getPlmAct(),PlmTypes.TRAVEL_APPLY_ID,plmTravelManage.getId());
		addMessage(redirectAttributes, "保存出差申请表单成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}
	
	
	@RequestMapping(value = "apply")
	public String apply(PlmTravelManage plmTravelManage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmTravelManage)){
			return form(plmTravelManage, model);
		}
		plmTravelManageService.save(plmTravelManage);
		if (StringUtils.isBlank(plmTravelManage.getProcInsId())) {
			
			Map<String, String> returnMap = actUtConfigService.getProcInsId(PlmTypes.TRAVEL_APPLY_ID, plmTravelManage, plmTravelManage.getId());
			plmTravelManage.setProcInsId(returnMap.get("procInsId"));
			plmTravelManageService.save(plmTravelManage);
			//4、保存业务流程主表*
			plmTravelManage.getPlmAct().setTitle(returnMap.get("title"));
			plmActService.save(plmTravelManage.getPlmAct(),PlmTypes.TRAVEL_APPLY_ID,plmTravelManage.getId(),plmTravelManage.getProcInsId());
		} else {
			plmTravelManage.getAct().setComment(("yes".equals(plmTravelManage.getAct().getFlag())?"[重申] ":"[撤销] ")
					+ plmTravelManage.getAct().getComment());
			Map<String, Object> vars = Maps.newHashMap();
			
			vars.put("pass", "yes".equals(plmTravelManage.getAct().getFlag())? "1" : "0");
			actTaskService.complete(plmTravelManage.getAct().getTaskId(), plmTravelManage.getAct().getProcInsId(),
					plmTravelManage.getAct().getComment(), "", vars);
			//如果销毁，将业务流程主表状态置位“已销毁”*
			if(!"yes".equals(plmTravelManage.getAct().getFlag())){				
				plmActService.updateStatusToDestory(plmTravelManage.getPlmAct());
			}
		}
		addMessage(redirectAttributes, "提交出差申请成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}
	
	
	@RequestMapping(value = "saveAudit")
	public String saveAudit(PlmTravelManage plmTravelManage, Model model) {
		if (StringUtils.isBlank(plmTravelManage.getAct().getFlag())
				|| StringUtils.isBlank(plmTravelManage.getAct().getComment())){
			addMessage(model, "请填写审核意见。");
			return form(plmTravelManage, model);
		}
					// 对不同环节的业务逻辑进行操作*
					String taskDefKey = plmTravelManage.getAct().getTaskDefKey();
										
					// 最后一步流程且   需要审核
					if ("auditEnd".equals(taskDefKey)) {							
						// 若为最后一步审核，通过，将业务流程主表状态置位“已结束”
						if ("yes".equals(plmTravelManage.getAct().getFlag())) {
							plmActService.updateStatusToEnd(plmTravelManage.getPlmAct());      
						}
					}
					// 最后一步流程  不需要审核
					else if ("processEnd".equals(taskDefKey)) {				
						// 将业务流程主表状态置位“已结束”			
							plmActService.updateStatusToEnd(plmTravelManage.getPlmAct());      
						
					}
					// 未知环节，直接返回
					else if (StringUtils.isBlank(taskDefKey)) {
						return "redirect:" + adminPath + "/act/task/todo/";
					}
					 // 针对所有具有添加督办的环节，若为isSup字段不为空，添加督办信息
					if (StringUtils.isNotBlank(plmTravelManage.getPlmAct().getIsSup())) {
						plmActService.updateSup(plmTravelManage.getPlmAct());
					}
		
		plmTravelManageService.auditSave(plmTravelManage);
		return "redirect:" + adminPath + "/act/task/todo/";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(PlmTravelManage plmTravelManage, RedirectAttributes redirectAttributes) {
		plmTravelManageService.delete(plmTravelManage);
		addMessage(redirectAttributes, "删除出差申请表单成功");
		return "redirect:"+Global.getAdminPath()+"/travel/plmTravelManage/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "printPdfIo")
	public void printPdfIo(PlmTravelManage plmTravelManage, Model model, RedirectAttributes redirectAttributes ,HttpServletRequest request ,HttpServletResponse response ) throws DocumentGeneratingException{
		
		
		      //将对象转成map 并将时间类型格式化"yyyy-MM-dd"
		      Map<String, Object> purmap=ResourceUitle.transBean2Map(plmTravelManage);	
		      
		      //有数据字典的  要换成名称
		     
		      if(StringUtils.isNotBlank(plmTravelManage.getPlmAct().getIsSup())){
		    	  purmap.put("isSup",DictUtils.getDictLabel(plmTravelManage.getPlmAct().getIsSup(),"yes_no",""));
		      }
		      //流转信息  actProcIns
		      plmTravelManage.getAct().setProcInsId(plmTravelManage.getProcInsId());
		      
			//1: ProcInsId   2审批内容跨列数Colspan      3审批标题跨列数titleColspan
		      purmap.put("actProcIns",actTaskService.histoicTable(plmTravelManage.getProcInsId(), "6" ,"2"));
		      
		      
		     //获取项目根路径
		     String path=request.getSession().getServletContext().getRealPath("/");
		       // classpath 中模板路径
				String template = "WEB-INF/views/plm/travel/plmTravelManageViewTemplate.html";
				PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
				// 生成pdf 返回流			
				pdfGenerator.generate(template, purmap, path,response);			          		            
		              
		            		
	}
}