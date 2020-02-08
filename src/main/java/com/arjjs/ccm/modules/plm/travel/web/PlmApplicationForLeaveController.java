/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.travel.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.act.entity.Act;
import com.arjjs.ccm.modules.act.service.ActTaskService;
import com.arjjs.ccm.modules.act.service.ActUtConfigService;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.plm.act.service.PlmActService;
import com.arjjs.ccm.modules.plm.travel.entity.PlmApplicationForLeave;
import com.arjjs.ccm.modules.plm.travel.service.PlmApplicationForLeaveService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.CommUtil;
import com.arjjs.ccm.tool.PlmTypes;
import com.arjjs.ccm.tool.pdf.PdfDocumentGenerator;
import com.arjjs.ccm.tool.pdf.ResourceUitle;
import com.arjjs.ccm.tool.pdf.exception.DocumentGeneratingException;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 请假申请Controller
 * @author dongqikai
 * @version 2018-07-16
 */
@Controller
@RequestMapping(value = "${adminPath}/travel/plmApplicationForLeave")
public class PlmApplicationForLeaveController extends BaseController {

	@Autowired
	private PlmApplicationForLeaveService plmApplicationForLeaveService;
	
	@Autowired
	private ActTaskService actTaskService;
	@Autowired	
	private PlmActService plmActService;
	
	
	@Autowired
	private ActUtConfigService<PlmApplicationForLeave> actUtConfigService;
	
	@ModelAttribute
	public PlmApplicationForLeave get(@RequestParam(required=false) String id) {
		PlmApplicationForLeave entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmApplicationForLeaveService.get(id);
			//添加业务流程主表信息
			if(entity!=null) {
				PlmAct plmAct = plmActService.getByTable(entity.getId(), PlmTypes.LEAVE_APPLY);
				if(plmAct!=null) {
					entity.setPlmAct(plmAct);
				}
			}
		}
		if (entity == null){
			entity = new PlmApplicationForLeave();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(PlmApplicationForLeave plmApplicationForLeave, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmApplicationForLeave> page = plmApplicationForLeaveService.findPage(new Page<PlmApplicationForLeave>(request, response), plmApplicationForLeave); 
		model.addAttribute("page", page);
		return "plm/travel/plmApplicationForLeaveList";
	}

	
	@RequestMapping(value = "form")
	public String form(PlmApplicationForLeave plmApplicationForLeave, Model model) {
		String viewPage = "plmApplicationForLeaveForm";
		if (StringUtils.isBlank(plmApplicationForLeave.getId())) {
			plmApplicationForLeave.setApplyer(UserUtils.getUser());
			plmApplicationForLeave.setDepartment(UserUtils.getUser().getOffice());
		}
		
		if (StringUtils.isNotBlank(plmApplicationForLeave.getProcInsId())) {
			Act act = plmApplicationForLeave.getAct();
			String taskDefKey = act.getTaskDefKey();
			if (act.isFinishTask()) {
				viewPage = "plmApplicationForLeaveView";
			} else if ("modify".equals(taskDefKey)) {
				viewPage = "plmApplicationForLeaveForm";
			} else if ("processEnd".equals(taskDefKey)) {
				viewPage = "plmApplicationForLeaveAudit";
				//终点无审核 去掉驳回按钮
				model.addAttribute("rejectedBtn", false);
			}else {
				viewPage = "plmApplicationForLeaveAudit";
				//终点审核 加驳回按钮
				model.addAttribute("rejectedBtn", true);
			} 
		}
		String cancelFlag = "0";
		if (StringUtils.isNotBlank(plmApplicationForLeave.getCancelFlag()) && "02".equals(plmApplicationForLeave.getCancelFlag())) {
			cancelFlag = "1";
		}
		model.addAttribute("cancelFlag", cancelFlag);
		model.addAttribute("plmApplicationForLeave", plmApplicationForLeave);
		return "plm/travel/" + viewPage;
	}

	
	@RequestMapping(value = "save")
	public void save(PlmApplicationForLeave plmApplicationForLeave,HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) throws IOException {
		if (!beanValidator(model, plmApplicationForLeave)){
//			return form(plmApplicationForLeave, model);
		}
		plmApplicationForLeaveService.save(plmApplicationForLeave);
		
		//parameter ： 1、业务流程主表  2、流程配置id 3、业务表*
		plmActService.save(plmApplicationForLeave.getPlmAct(),PlmTypes.LEAVE_APPLY,plmApplicationForLeave.getId());
		
		addMessage(redirectAttributes, "保存请假申请成功");
//		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
		PrintWriter out = response.getWriter();
		CommUtil.openWinExpDiv(out, "提交出请假请成功");
	}
	

	@RequestMapping(value = "apply")
	public void apply(PlmApplicationForLeave plmApplicationForLeave,HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) throws IOException {
		if (!beanValidator(model, plmApplicationForLeave)){
//			return form(plmApplicationForLeave, model);
		}
		
			plmApplicationForLeaveService.save(plmApplicationForLeave);
		if (StringUtils.isBlank(plmApplicationForLeave.getProcInsId())) {
			Map<String, String> returnMap = actUtConfigService.getProcInsId(PlmTypes.LEAVE_APPLY, plmApplicationForLeave, plmApplicationForLeave.getId());
			plmApplicationForLeave.setProcInsId(returnMap.get("procInsId"));
			
			plmApplicationForLeaveService.save(plmApplicationForLeave);
			
			
			//4、保存业务流程主表*
			plmApplicationForLeave.getPlmAct().setTitle(returnMap.get("title"));
			plmActService.save(plmApplicationForLeave.getPlmAct(),PlmTypes.LEAVE_APPLY,plmApplicationForLeave.getId(),plmApplicationForLeave.getProcInsId());
			
		} else {
			plmApplicationForLeave.getAct().setComment(("yes".equals(plmApplicationForLeave.getAct().getFlag())?"[重申] ":"[撤销] ")
					+ plmApplicationForLeave.getAct().getComment());
			Map<String, Object> vars = Maps.newHashMap();
			
			vars.put("pass", "yes".equals(plmApplicationForLeave.getAct().getFlag())? "1" : "0");
			actTaskService.complete(plmApplicationForLeave.getAct().getTaskId(), plmApplicationForLeave.getAct().getProcInsId(),
					plmApplicationForLeave.getAct().getComment(), "", vars);
			//如果销毁，将业务流程主表状态置位“已销毁”*
			if(!"yes".equals(plmApplicationForLeave.getAct().getFlag())){				
				plmActService.updateStatusToDestory(plmApplicationForLeave.getPlmAct());
			}
			
		}
		addMessage(redirectAttributes, "提交出请假请成功");
//		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
		PrintWriter out = response.getWriter();
		CommUtil.openWinExpDiv(out, "提交出请假请成功");
	}
	

	@RequestMapping(value = "saveAudit")
	public String saveAudit(PlmApplicationForLeave plmApplicationForLeave, Model model) {
		if (StringUtils.isBlank(plmApplicationForLeave.getAct().getFlag())
				|| StringUtils.isBlank(plmApplicationForLeave.getAct().getComment())){
			addMessage(model, "请填写审核意见。");
			return form(plmApplicationForLeave, model);
		}
		// 对不同环节的业务逻辑进行操作*
		String taskDefKey = plmApplicationForLeave.getAct().getTaskDefKey();
		// 最后一步流程且   需要审核
				if ("auditEnd".equals(taskDefKey)) {							
					// 若为最后一步审核，通过，将业务流程主表状态置位“已结束”
					if ("yes".equals(plmApplicationForLeave.getAct().getFlag())) {
						plmActService.updateStatusToEnd(plmApplicationForLeave.getPlmAct());      
					}
				}
				// 最后一步流程  不需要审核
				else if ("processEnd".equals(taskDefKey)) {				
					// 将业务流程主表状态置位“已结束”			
						plmActService.updateStatusToEnd(plmApplicationForLeave.getPlmAct());      
					
				}
				// 未知环节，直接返回
				else if (StringUtils.isBlank(taskDefKey)) {
					return "redirect:" + adminPath + "/act/task/todo/";
				}
				 // 针对所有具有添加督办的环节，若为isSup字段不为空，添加督办信息
				if (StringUtils.isNotBlank(plmApplicationForLeave.getPlmAct().getIsSup())) {
					plmActService.updateSup(plmApplicationForLeave.getPlmAct());
				}
		
		plmApplicationForLeaveService.auditSave(plmApplicationForLeave);
		return "redirect:" + adminPath + "/act/task/todo/";
	}
	

	@RequestMapping(value = "delete")
	public String delete(PlmApplicationForLeave plmApplicationForLeave, RedirectAttributes redirectAttributes) {
		plmApplicationForLeaveService.delete(plmApplicationForLeave);
		addMessage(redirectAttributes, "删除请假申请成功");
		return "redirect:"+Global.getAdminPath()+"/travel/plmApplicationForLeave/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "printPdfIo")
	public void printPdfIo(PlmApplicationForLeave plmApplicationForLeave, Model model, RedirectAttributes redirectAttributes ,HttpServletRequest request ,HttpServletResponse response ) throws DocumentGeneratingException{
		
		
		      //将对象转成map 并将时间类型格式化"yyyy-MM-dd"
		      Map<String, Object> purmap=ResourceUitle.transBean2Map(plmApplicationForLeave);	
		      
		      //有数据字典的  要换成名称plmApplicationForLeave.applyType, 'leave_type', ''
		      purmap.put("applyType",DictUtils.getDictLabel(plmApplicationForLeave.getApplyType(),"leave_type",""));	
		      
		      if(StringUtils.isNotBlank(plmApplicationForLeave.getPlmAct().getIsSup())){
		    	  purmap.put("isSup",DictUtils.getDictLabel(plmApplicationForLeave.getPlmAct().getIsSup(),"yes_no",""));
		      }
		      //流转信息  actProcIns
		      plmApplicationForLeave.getAct().setProcInsId(plmApplicationForLeave.getProcInsId());
		      
			//1: ProcInsId   2审批内容跨列数Colspan      3审批标题跨列数titleColspan
		      purmap.put("actProcIns",actTaskService.histoicTable(plmApplicationForLeave.getProcInsId(), "5" ,"1"));
		      
		      
		     //获取项目根路径
		     String path=request.getSession().getServletContext().getRealPath("/");
		       // classpath 中模板路径
				String template = "WEB-INF/views/plm/travel/plmApplicationForLeaveViewTemplate.html";
				PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
				// 生成pdf 返回流			
				pdfGenerator.generate(template, purmap, path,response);			          		            
		              
		            		
	}
	
}