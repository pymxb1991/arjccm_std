/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.travel.web;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.arjjs.ccm.modules.plm.travel.entity.PlmBorrowMoney;
import com.arjjs.ccm.modules.plm.travel.service.PlmBorrowMoneyService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.CommUtil;
import com.arjjs.ccm.tool.PlmTypes;
import com.arjjs.ccm.tool.pdf.PdfDocumentGenerator;
import com.arjjs.ccm.tool.pdf.ResourceUitle;
import com.arjjs.ccm.tool.pdf.exception.DocumentGeneratingException;
import com.google.common.collect.Maps;

/**
 * 借款申请Controller
 * @author dongqikai
 * @version 2018-07-16
 */
@Controller
@RequestMapping(value = "${adminPath}/travel/plmBorrowMoney")
public class PlmBorrowMoneyController extends BaseController {

	@Autowired
	private PlmBorrowMoneyService plmBorrowMoneyService;
	
	@Autowired
	private ActTaskService actTaskService;
	@Autowired	
	private PlmActService plmActService;
	@Autowired
	private ActUtConfigService<PlmBorrowMoney> actUtConfigService;
	
	@ModelAttribute
	public PlmBorrowMoney get(@RequestParam(required=false) String id) {
		PlmBorrowMoney entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmBorrowMoneyService.get(id);
			//添加业务流程主表信息
			if(entity!=null) {
				PlmAct plmAct = plmActService.getByTable(entity.getId(), PlmTypes.BORROW_MONEY);
				if(plmAct!=null) {
					entity.setPlmAct(plmAct);
				}
			}
		}
		if (entity == null){
			entity = new PlmBorrowMoney();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(PlmBorrowMoney plmBorrowMoney, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmBorrowMoney> page = plmBorrowMoneyService.findPage(new Page<PlmBorrowMoney>(request, response), plmBorrowMoney); 
		model.addAttribute("page", page);
		return "plm/travel/plmBorrowMoneyList";
	}

	
	@RequestMapping(value = "form")
	public String form(PlmBorrowMoney plmBorrowMoney, Model model) {
		String viewPage = "plmBorrowMoneyForm";
		if (StringUtils.isBlank(plmBorrowMoney.getId())) {
			plmBorrowMoney.setApplyer(UserUtils.getUser());
			plmBorrowMoney.setDepartment(UserUtils.getUser().getOffice());
		}
		if (StringUtils.isNotBlank(plmBorrowMoney.getProcInsId())) {
			Act act = plmBorrowMoney.getAct();
			String taskDefKey = act.getTaskDefKey();
			if (act.isFinishTask()) {
				viewPage = "plmBorrowMoneyView";
			} else if ("modify".equals(taskDefKey)) {
				viewPage = "plmBorrowMoneyForm";
			} else if ("processEnd".equals(taskDefKey)) {
				viewPage = "plmBorrowMoneyAudit";
				//终点无审核 去掉驳回按钮
				model.addAttribute("rejectedBtn", false);
			}else {
				viewPage = "plmBorrowMoneyAudit";
				//终点审核 加驳回按钮
				model.addAttribute("rejectedBtn", true);
			} 
		}
		String cancelFlag = "0";
		if (StringUtils.isNotBlank(plmBorrowMoney.getCancelFlag()) && "02".equals(plmBorrowMoney.getCancelFlag())) {
			cancelFlag = "1";
		}
		model.addAttribute("cancelFlag", cancelFlag);
		model.addAttribute("plmBorrowMoney", plmBorrowMoney);
		return "plm/travel/" + viewPage;
	}


	@RequestMapping(value = "save")
	public void save(PlmBorrowMoney plmBorrowMoney, Model model, RedirectAttributes redirectAttributes, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!beanValidator(model, plmBorrowMoney)){
			CommUtil.openWinExpDiv(out, "保存名单库失败");
		}else {			
			plmBorrowMoneyService.save(plmBorrowMoney);
			//parameter ： 1、业务流程主表  2、流程配置id 3、业务表*
			plmActService.save(plmBorrowMoney.getPlmAct(),PlmTypes.BORROW_MONEY,plmBorrowMoney.getId());
			CommUtil.openWinExpDiv(out, "保存借款申请成功");
		}
	}
	
	
	@RequestMapping(value = "apply")
	public String apply(PlmBorrowMoney plmBorrowMoney, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmBorrowMoney)){
			return form(plmBorrowMoney, model);
		}
		plmBorrowMoneyService.save(plmBorrowMoney);
		if (StringUtils.isBlank(plmBorrowMoney.getProcInsId())) {
			Map<String, String> returnMap  = actUtConfigService.getProcInsId(PlmTypes.BORROW_MONEY, plmBorrowMoney, plmBorrowMoney.getId());
			plmBorrowMoney.setProcInsId(returnMap.get("procInsId"));
			plmBorrowMoneyService.save(plmBorrowMoney);
			
			//4、保存业务流程主表*
			plmBorrowMoney.getPlmAct().setTitle(returnMap.get("title"));
			plmActService.save(plmBorrowMoney.getPlmAct(),PlmTypes.BORROW_MONEY,plmBorrowMoney.getId(),plmBorrowMoney.getProcInsId());
		} else {
			plmBorrowMoney.getAct().setComment(("yes".equals(plmBorrowMoney.getAct().getFlag())?"[重申] ":"[撤销] ")
					+ plmBorrowMoney.getAct().getComment());
			Map<String, Object> vars = Maps.newHashMap();
			
			vars.put("pass", "yes".equals(plmBorrowMoney.getAct().getFlag())? "1" : "0");
			actTaskService.complete(plmBorrowMoney.getAct().getTaskId(), plmBorrowMoney.getAct().getProcInsId(),
					plmBorrowMoney.getAct().getComment(), "", vars);
			//如果销毁，将业务流程主表状态置位“已销毁”*
			if(!"yes".equals(plmBorrowMoney.getAct().getFlag())){				
				plmActService.updateStatusToDestory(plmBorrowMoney.getPlmAct());
			}
		}
		addMessage(redirectAttributes, "提交借款申请成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}
	
	
	@RequestMapping(value = "saveAudit")
	public String saveAudit(PlmBorrowMoney plmBorrowMoney, Model model) {
		if (StringUtils.isBlank(plmBorrowMoney.getAct().getFlag())
				|| StringUtils.isBlank(plmBorrowMoney.getAct().getComment())){
			addMessage(model, "请填写审核意见。");
			return form(plmBorrowMoney, model);
		}
		// 对不同环节的业务逻辑进行操作*
				String taskDefKey = plmBorrowMoney.getAct().getTaskDefKey();
				// 最后一步流程且   需要审核
				if ("auditEnd".equals(taskDefKey)) {							
					// 若为最后一步审核，通过，将业务流程主表状态置位“已结束”
					if ("yes".equals(plmBorrowMoney.getAct().getFlag())) {
						plmActService.updateStatusToEnd(plmBorrowMoney.getPlmAct());      
					}
				}
				// 最后一步流程  不需要审核
				else if ("processEnd".equals(taskDefKey)) {				
					// 将业务流程主表状态置位“已结束”			
						plmActService.updateStatusToEnd(plmBorrowMoney.getPlmAct());      
					
				}
				// 未知环节，直接返回
				else if (StringUtils.isBlank(taskDefKey)) {
					return "redirect:" + adminPath + "/act/task/todo/";
				}
				 // 针对所有具有添加督办的环节，若为isSup字段不为空，添加督办信息
				if (StringUtils.isNotBlank(plmBorrowMoney.getPlmAct().getIsSup())) {
					plmActService.updateSup(plmBorrowMoney.getPlmAct());
				}
		plmBorrowMoneyService.auditSave(plmBorrowMoney);
		return "redirect:" + adminPath + "/act/task/todo/";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(PlmBorrowMoney plmBorrowMoney, RedirectAttributes redirectAttributes) {
		plmBorrowMoneyService.delete(plmBorrowMoney);
		addMessage(redirectAttributes, "删除借款申请成功");
		return "redirect:"+Global.getAdminPath()+"/travel/plmBorrowMoney/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "printPdfIo")
	public void printPdfIo(PlmBorrowMoney plmBorrowMoney, Model model, RedirectAttributes redirectAttributes ,HttpServletRequest request ,HttpServletResponse response ) throws DocumentGeneratingException{
		
		
		      //将对象转成map 并将时间类型格式化"yyyy-MM-dd"
		      Map<String, Object> purmap=ResourceUitle.transBean2Map(plmBorrowMoney);	
		      
		      //有数据字典的  要换成名称
		      if(StringUtils.isNotBlank(plmBorrowMoney.getPlmAct().getIsSup())){
		    	  purmap.put("isSup",DictUtils.getDictLabel(plmBorrowMoney.getPlmAct().getIsSup(),"yes_no",""));
		      }
		      //流转信息  actProcIns
		      plmBorrowMoney.getAct().setProcInsId(plmBorrowMoney.getProcInsId());
		      
			//1: ProcInsId   2审批内容跨列数Colspan      3审批标题跨列数titleColspan
		      purmap.put("actProcIns",actTaskService.histoicTable(plmBorrowMoney.getProcInsId(), "5" ,"1"));
		      
		      
		     //获取项目根路径
		     String path=request.getSession().getServletContext().getRealPath("/");
		       // classpath 中模板路径
				String template = "WEB-INF/views/plm/travel/plmBorrowMoneyViewTemplate.html";
				PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
				// 生成pdf 返回流			
				pdfGenerator.generate(template, purmap, path,response);			          		            
		              
		            		
	}
}