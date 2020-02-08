/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.web.apply;

import java.io.IOException;
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
import com.arjjs.ccm.modules.act.service.ActTaskService;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.plm.act.service.PlmActService;
import com.arjjs.ccm.modules.plm.car.entity.apply.PlmCarApplyRepair;
import com.arjjs.ccm.modules.plm.car.service.apply.PlmCarApplyRepairService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.PlmTypes;
import com.arjjs.ccm.tool.pdf.PdfDocumentGenerator;
import com.arjjs.ccm.tool.pdf.ResourceUitle;
import com.arjjs.ccm.tool.pdf.exception.DocumentGeneratingException;

/**
 * 维修申请Controller
 * @author fu
 * @version 2018-07-07
 */
@Controller
@RequestMapping(value = "${adminPath}/car/apply/plmCarApplyRepair")
public class PlmCarApplyRepairController extends BaseController {

	@Autowired
	private PlmCarApplyRepairService plmCarApplyRepairService;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private PlmActService plmActService;
	
	@ModelAttribute
	public PlmCarApplyRepair get(@RequestParam(required=false) String id) {
		PlmCarApplyRepair entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmCarApplyRepairService.get(id);
			//添加业务流程主表信息
			if(entity!=null) {
				PlmAct plmAct = plmActService.getByTable(entity.getId(), PlmTypes.CAR_APPLY_REPAIR_ID);
				if(plmAct!=null) {
					entity.setPlmAct(plmAct);
				}
			}
		}
		if (entity == null){
			entity = new PlmCarApplyRepair();
			entity.setUser(UserUtils.getUser());
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(PlmCarApplyRepair plmCarApplyRepair, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmCarApplyRepair> page = plmCarApplyRepairService.findPage(new Page<PlmCarApplyRepair>(request, response), plmCarApplyRepair); 
		model.addAttribute("page", page);
		return "plm/car/apply/plmCarApplyRepairList";
	}

	@RequestMapping(value = "form")
	public String form(PlmCarApplyRepair plmCarApplyRepair, Model model) {

		plmCarApplyRepair.getAct().setProcInsId(plmCarApplyRepair.getProcInsId());

		String view = "plmCarApplyRepairForm";
		
		// 查看审批申请单
		if (StringUtils.isNotBlank(plmCarApplyRepair.getProcInsId())){

			// 环节编号
			String taskDefKey = plmCarApplyRepair.getAct().getTaskDefKey();
			
			// 查看工单
			if(plmCarApplyRepair.getAct().isFinishTask()){
				view = "plmCarApplyRepairView";
			}
			// 修改环节
			else if ("modify".equals(taskDefKey)){
				view = "plmCarApplyRepairForm";
			}
			// 审核环节
			else {
				view = "plmCarApplyRepairAudit";
			}
		}
		String cancelFlag = "0";
		if (StringUtils.isNotBlank(plmCarApplyRepair.getCancelFlag()) && "02".equals(plmCarApplyRepair.getCancelFlag())) {
			cancelFlag = "1";
		}
		model.addAttribute("cancelFlag", cancelFlag);
		model.addAttribute("plmCarApplyRepair", plmCarApplyRepair);
		return "plm/car/apply/" + view;
	}		
	@RequestMapping(value = "save")
	public String save(PlmCarApplyRepair plmCarApplyRepair, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmCarApplyRepair)){
			return form(plmCarApplyRepair, model);
		}
		plmCarApplyRepairService.save(plmCarApplyRepair);
		
		addMessage(redirectAttributes, "保存维修(保养)申请成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}
	
	@RequestMapping(value = "apply")
	public String apply(PlmCarApplyRepair plmCarApplyRepair, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmCarApplyRepair)){
			return form(plmCarApplyRepair, model);
		}
		plmCarApplyRepairService.apply(plmCarApplyRepair);

		addMessage(redirectAttributes, "提交维修(保养)申请成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}
	
	/**
	 * 工单执行（完成任务）
	 * @param testAudit
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveAudit")
	public String saveAudit(PlmCarApplyRepair plmCarApplyRepair, Model model) {
		if (StringUtils.isBlank(plmCarApplyRepair.getAct().getFlag())
				|| StringUtils.isBlank(plmCarApplyRepair.getAct().getComment())){
			addMessage(model, "请填写审核意见。");
			return form(plmCarApplyRepair, model);
		}
		plmCarApplyRepairService.auditSave(plmCarApplyRepair);
		return "redirect:" + adminPath + "/act/taskSelf/todo/";
	}
	
	@RequestMapping(value = "delete")
	public String delete(PlmCarApplyRepair plmCarApplyRepair, RedirectAttributes redirectAttributes) {
		plmCarApplyRepairService.delete(plmCarApplyRepair);
		addMessage(redirectAttributes, "删除维修(保养)申请成功");
		return "redirect:"+Global.getAdminPath()+"/car/apply/plmCarApplyRepair/?repage";
	}
	/**
     * 生成pdf  (返回流)
     * @param plmPurchaseApply
     * @param model
     * @param redirectAttributes
     * @return
	 * @throws DocumentGeneratingException 
	 * @throws IOException 
     */
	@ResponseBody
	@RequestMapping(value = "printPdfIo")
	public void printPdfIo(PlmCarApplyRepair plmCarApplyRepair, Model model, RedirectAttributes redirectAttributes ,HttpServletRequest request ,HttpServletResponse response ) throws DocumentGeneratingException{
		
		
		      //将对象转成map 并将时间类型格式化"yyyy-MM-dd"
		      Map<String, Object> purmap=ResourceUitle.transBean2Map(plmCarApplyRepair);	
		      
		      //有数据字典的  要换成名称
		      if(StringUtils.isNotBlank(plmCarApplyRepair.getPlmAct().getIsSup())){
		    	  purmap.put("isSup",DictUtils.getDictLabel(plmCarApplyRepair.getPlmAct().getIsSup(),"yes_no",""));
		      }		      
		      
		      //流转信息  actProcIns
		      plmCarApplyRepair.getAct().setProcInsId(plmCarApplyRepair.getProcInsId());
		      
		      //1: ProcInsId   2审批内容跨列数Colspan      3审批标题跨列数titleColspan
		      purmap.put("actProcIns",actTaskService.histoicTable(plmCarApplyRepair.getProcInsId(), "6" ,"2"));
		      
		      
		     //获取项目根路径
		     String path=request.getSession().getServletContext().getRealPath("/");
		       // classpath 中模板路径
				String template = "WEB-INF/views/plm/car/apply/plmCarApplyRepairViewTemplate.html";
				PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
				// 生成pdf 返回流			
				pdfGenerator.generate(template, purmap, path,response);			          		            
		              
		            		
	}
	
}