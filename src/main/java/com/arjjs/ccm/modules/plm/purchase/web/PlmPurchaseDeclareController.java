/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.purchase.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;
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
import com.arjjs.ccm.modules.plm.purchase.entity.PlmPurchaseDeclare;
import com.arjjs.ccm.modules.plm.purchase.entity.PlmPurchaseDeclareDetail;
import com.arjjs.ccm.modules.plm.purchase.service.PlmPurchaseDeclareService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.PlmTypes;
import com.arjjs.ccm.tool.pdf.PdfDocumentGenerator;
import com.arjjs.ccm.tool.pdf.ResourceUitle;
import com.arjjs.ccm.tool.pdf.exception.DocumentGeneratingException;
import com.google.common.collect.Maps;

/**
 * 采购申报Controller
 * @author liuxue
 * @version 2018-08-25
 */
@Controller
@RequestMapping(value = "${adminPath}/purchase/plmPurchaseDeclare")
public class PlmPurchaseDeclareController extends BaseController {

	@Autowired
	private PlmPurchaseDeclareService plmPurchaseDeclareService;
	
	@Autowired
	private ActTaskService actTaskService;
	@Autowired	
	private PlmActService plmActService;
	
	@Autowired
	private ActUtConfigService<PlmPurchaseDeclare> actUtConfigService;
	
	@ModelAttribute
	public PlmPurchaseDeclare get(@RequestParam(required=false) String id) {
		PlmPurchaseDeclare entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmPurchaseDeclareService.get(id);
			//添加业务流程主表信息
			if(entity!=null) {
				PlmAct plmAct = plmActService.getByTable(entity.getId(), PlmTypes.PURCHASE_DECLARE_ID);
				if(plmAct!=null) {
					entity.setPlmAct(plmAct);
				}
			}
		}
		if (entity == null){
			entity = new PlmPurchaseDeclare();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(PlmPurchaseDeclare plmPurchaseDeclare, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmPurchaseDeclare> page = plmPurchaseDeclareService.findPage(new Page<PlmPurchaseDeclare>(request, response), plmPurchaseDeclare); 
		model.addAttribute("page", page);
		return "plm/purchase/plmPurchaseDeclareList";
	}

	
	@RequestMapping(value = "form")
	public String form(PlmPurchaseDeclare plmPurchaseDeclare, Model model) {
		String viewPage = "plmPurchaseDeclareForm";
		if (StringUtils.isBlank(plmPurchaseDeclare.getId())) {
			plmPurchaseDeclare.setCreateBy(UserUtils.getUser());
			plmPurchaseDeclare.setDepart(UserUtils.getUser().getOffice());
			plmPurchaseDeclare.setApplyDate(new Date());
		}
		
		
		if (StringUtils.isNotBlank(plmPurchaseDeclare.getProcInsId())) {
			Act act = plmPurchaseDeclare.getAct();
			String taskDefKey = act.getTaskDefKey();
			if (act.isFinishTask()) {
				viewPage = "plmPurchaseDeclareView";
			} else if ("modify".equals(taskDefKey)) {
				viewPage = "plmPurchaseDeclareForm";
			} else if ("processEnd".equals(taskDefKey)) {
				viewPage = "plmPurchaseDeclareAudit";
				//终点无审核 去掉驳回按钮
				model.addAttribute("rejectedBtn", false);
			}else {
				viewPage = "plmPurchaseDeclareAudit";
				//终点审核 加驳回按钮
				model.addAttribute("rejectedBtn", true);
			} 
		}
		
		String cancelFlag = "0";
		if (StringUtils.isNotBlank(plmPurchaseDeclare.getCancelFlag()) && "02".equals(plmPurchaseDeclare.getCancelFlag())) {
			cancelFlag = "1";
		}
		model.addAttribute("cancelFlag", cancelFlag);
		model.addAttribute("plmPurchaseDeclare", plmPurchaseDeclare);
		return "plm/purchase/" + viewPage;
	}


	@RequestMapping(value = "save")
	public String save(PlmPurchaseDeclare plmPurchaseDeclare, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmPurchaseDeclare)){
			return form(plmPurchaseDeclare, model);
		}
		
		plmPurchaseDeclareService.save(plmPurchaseDeclare);
		//parameter ： 1、业务流程主表  2、流程配置id 3、业务表*
		plmActService.save(plmPurchaseDeclare.getPlmAct(),PlmTypes.PURCHASE_DECLARE_ID,plmPurchaseDeclare.getId());
		
		addMessage(redirectAttributes, "保存采购申报成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}
	
	@RequestMapping(value = "apply")
	public String apply(PlmPurchaseDeclare plmPurchaseDeclare, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmPurchaseDeclare)){
			return form(plmPurchaseDeclare, model);
		}
		
			plmPurchaseDeclareService.save(plmPurchaseDeclare);
		if (StringUtils.isBlank(plmPurchaseDeclare.getProcInsId())) {
			Map<String, String> returnMap = actUtConfigService.getProcInsId(PlmTypes.PURCHASE_DECLARE_ID, plmPurchaseDeclare, plmPurchaseDeclare.getId());
			plmPurchaseDeclare.setProcInsId(returnMap.get("procInsId"));
			
			plmPurchaseDeclareService.save(plmPurchaseDeclare);
			
			
			//4、保存业务流程主表*
			plmPurchaseDeclare.getPlmAct().setTitle(returnMap.get("title"));
			plmActService.save(plmPurchaseDeclare.getPlmAct(),PlmTypes.PURCHASE_DECLARE_ID,plmPurchaseDeclare.getId(),plmPurchaseDeclare.getProcInsId());
			
		} else {
			plmPurchaseDeclare.getAct().setComment(("yes".equals(plmPurchaseDeclare.getAct().getFlag())?"[重申] ":"[撤销] ")
					+ plmPurchaseDeclare.getAct().getComment());
			Map<String, Object> vars = Maps.newHashMap();
			
			vars.put("pass", "yes".equals(plmPurchaseDeclare.getAct().getFlag())? "1" : "0");
			actTaskService.complete(plmPurchaseDeclare.getAct().getTaskId(), plmPurchaseDeclare.getAct().getProcInsId(),
					plmPurchaseDeclare.getAct().getComment(), "", vars);
			//如果销毁，将业务流程主表状态置位“已销毁”*
			if(!"yes".equals(plmPurchaseDeclare.getAct().getFlag())){				
				plmActService.updateStatusToDestory(plmPurchaseDeclare.getPlmAct());
			}
			
		}
		addMessage(redirectAttributes, "提交采购申报成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}
	

	@RequestMapping(value = "saveAudit")
	public String saveAudit(PlmPurchaseDeclare plmPurchaseDeclare, Model model) {
		if (StringUtils.isBlank(plmPurchaseDeclare.getAct().getFlag())
				|| StringUtils.isBlank(plmPurchaseDeclare.getAct().getComment())){
			addMessage(model, "请填写审核意见。");
			return form(plmPurchaseDeclare, model);
		}
		// 对不同环节的业务逻辑进行操作*
		String taskDefKey = plmPurchaseDeclare.getAct().getTaskDefKey();
		// 最后一步流程且   需要审核
				if ("auditEnd".equals(taskDefKey)) {							
					// 若为最后一步审核，通过，将业务流程主表状态置位“已结束”
					if ("yes".equals(plmPurchaseDeclare.getAct().getFlag())) {
						plmActService.updateStatusToEnd(plmPurchaseDeclare.getPlmAct());      
					}
				}
				// 最后一步流程  不需要审核
				else if ("processEnd".equals(taskDefKey)) {				
					// 将业务流程主表状态置位“已结束”			
						plmActService.updateStatusToEnd(plmPurchaseDeclare.getPlmAct());      
					
				}
				// 未知环节，直接返回
				else if (StringUtils.isBlank(taskDefKey)) {
					return "redirect:" + adminPath + "/act/task/todo/";
				}
				 // 针对所有具有添加督办的环节，若为isSup字段不为空，添加督办信息
				if (StringUtils.isNotBlank(plmPurchaseDeclare.getPlmAct().getIsSup())) {
					plmActService.updateSup(plmPurchaseDeclare.getPlmAct());
				}
		
		plmPurchaseDeclareService.auditSave(plmPurchaseDeclare);
		return "redirect:" + adminPath + "/act/task/todo/";
	}
	
	
	
	

	@RequestMapping(value = "delete")
	public String delete(PlmPurchaseDeclare plmPurchaseDeclare, RedirectAttributes redirectAttributes) {
		plmPurchaseDeclareService.delete(plmPurchaseDeclare);
		addMessage(redirectAttributes, "删除采购申报成功");
		return "redirect:"+Global.getAdminPath()+"/purchase/plmPurchaseDeclare/?repage";
	}
	/**
     * 生成pdf  (返回流)
     * @param plmPurchaseDeclare
     * @param model
     * @param redirectAttributes
     * @return
	 * @throws DocumentGeneratingException 
	 * @throws IOException 
     */
	@ResponseBody
	@RequestMapping(value = "printPdfIo")
	public void printPdfIo(PlmPurchaseDeclare plmPurchaseDeclare, Model model, RedirectAttributes redirectAttributes ,HttpServletRequest request ,HttpServletResponse response ) throws DocumentGeneratingException {
		
		
		      //将对象转成map 并将时间类型格式化"yyyy-MM-dd"
		      Map<String, Object> purmap=ResourceUitle.transBean2Map(plmPurchaseDeclare);	
		      
		      if(StringUtils.isNotBlank(plmPurchaseDeclare.getPlmAct().getIsSup())){
		    	  purmap.put("isSup",DictUtils.getDictLabel(plmPurchaseDeclare.getPlmAct().getIsSup(),"yes_no",""));
		      }	
	          
		      
		      
		      List<PlmPurchaseDeclareDetail> detlist=plmPurchaseDeclare.getPlmPurchaseDeclareDetailList();
		      String detail="";
		      
		      for (PlmPurchaseDeclareDetail plmPurchaseDeclareDetail : detlist) {
		    	 
		    	  
				detail+="<tr >						\n" + 
						"							<td style='border-left: 0px; '>\n" + 
						                           plmPurchaseDeclareDetail.getName()+
						"							</td>\n" + 
						"							<td>\n" + 
						                              plmPurchaseDeclareDetail.getSpec()  + 
						"							</td>\n" + 
						"							<td>\n" + 
													 plmPurchaseDeclareDetail.getNumber()  + 
						"							</td>\n" + 
						"							<td>\n" + 
														 plmPurchaseDeclareDetail.getDeclareMoney()  + 
						"							</td>\n" + 
						"							<td>\n" + 
														 plmPurchaseDeclareDetail.getVerifyMoney() + 
						"							</td>\n" + 
						"							<td>\n" + 
														 plmPurchaseDeclareDetail.getPlace() + 
						"							</td>\n" + 
						"							<td>\n" + 
														 plmPurchaseDeclareDetail.getUser().getName()  + 
						"							</td>													\n" + 
						"							\n" + 
						"						</tr>";
			}
		      
		         
		      purmap.put("detail", detail.replaceAll("null", ""));
		      
		      
		      //流转信息  actProcIns
		      plmPurchaseDeclare.getAct().setProcInsId(plmPurchaseDeclare.getProcInsId());		      
		      //1: ProcInsId   2审批内容跨列数Colspan      3审批标题跨列数titleColspan
		      purmap.put("actProcIns",actTaskService.histoicTable(plmPurchaseDeclare.getProcInsId(), "6" ,"2"));
		      
		      
		     //获取项目根路径
		     String path=request.getSession().getServletContext().getRealPath("/");
		       // classpath 中模板路径
				String template = "WEB-INF/views/plm/purchase/plmPurchaseDeclareViewTemplate.html";
				PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
				// 生成pdf 返回流			
			    pdfGenerator.generate(template, purmap, path,response);		            		
	}
}