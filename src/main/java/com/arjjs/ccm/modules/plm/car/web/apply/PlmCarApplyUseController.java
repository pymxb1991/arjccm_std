/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.web.apply;

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
import com.arjjs.ccm.modules.plm.car.entity.apply.PlmCarApplyUse;
import com.arjjs.ccm.modules.plm.car.service.apply.PlmCarApplyUseService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.PlmTypes;
import com.arjjs.ccm.tool.pdf.PdfDocumentGenerator;
import com.arjjs.ccm.tool.pdf.ResourceUitle;
import com.arjjs.ccm.tool.pdf.exception.DocumentGeneratingException;

/**
 * 用车申请Controller
 * @author fu
 * @version 2018-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/car/apply/plmCarApplyUse")
public class PlmCarApplyUseController extends BaseController {

	@Autowired
	private PlmCarApplyUseService plmCarApplyUseService;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private PlmActService plmActService;
	
	@ModelAttribute
	public PlmCarApplyUse get(@RequestParam(required=false) String id) {
		PlmCarApplyUse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmCarApplyUseService.get(id);
			//添加业务流程主表信息
			if(entity!=null) {
				PlmAct plmAct = plmActService.getByTable(entity.getId(), PlmTypes.CAR_APPLY_USE_ID);
				if(plmAct!=null) {
					entity.setPlmAct(plmAct);
				}
			}
		}
		if (entity == null){
			entity = new PlmCarApplyUse();
			entity.setUser(UserUtils.getUser());
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(PlmCarApplyUse plmCarApplyUse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmCarApplyUse> page = plmCarApplyUseService.findPage(new Page<PlmCarApplyUse>(request, response), plmCarApplyUse); 
		model.addAttribute("page", page);
		return "plm/car/apply/plmCarApplyUseList";
	}

	@RequestMapping(value = "form")
	public String form(PlmCarApplyUse plmCarApplyUse, Model model) {
		plmCarApplyUse.getAct().setProcInsId(plmCarApplyUse.getProcInsId());
		
		String view = "plmCarApplyUseForm";
		
		// 查看审批申请单
		if (StringUtils.isNotBlank(plmCarApplyUse.getProcInsId())){

			// 环节编号
			String taskDefKey = plmCarApplyUse.getAct().getTaskDefKey();
			
			// 查看工单
			if(plmCarApplyUse.getAct().isFinishTask()){
				view = "plmCarApplyUseView";
			}
			// 修改环节
			else if ("modify".equals(taskDefKey)){
				view = "plmCarApplyUseForm";
			}
			// 审核环节1
			/*else if ("audit1".equals(taskDefKey)){
				view = "plmCarApplyUseAudit";
			}*/
			// 审核环节2
			else {
				view = "plmCarApplyUseAudit";
			}
		}
		String cancelFlag = "0";
		if (StringUtils.isNotBlank(plmCarApplyUse.getCancelFlag()) && "02".equals(plmCarApplyUse.getCancelFlag())) {
			cancelFlag = "1";
		}
		model.addAttribute("cancelFlag", cancelFlag);
		model.addAttribute("plmCarApplyUse", plmCarApplyUse);
		return "plm/car/apply/" + view;
	}
	/**
	 * 工单执行（完成任务）
	 * @param testAudit
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveAudit")
	public String saveAudit(PlmCarApplyUse plmCarApplyUse, Model model) {
		if (StringUtils.isBlank(plmCarApplyUse.getAct().getFlag())
				|| StringUtils.isBlank(plmCarApplyUse.getAct().getComment())){
			addMessage(model, "请填写审核意见。");
			return form(plmCarApplyUse, model);
		}
		plmCarApplyUseService.auditSave(plmCarApplyUse);
		return "redirect:" + adminPath + "/act/task/todo/";
	}

	@RequestMapping(value = "save")
	public String save(PlmCarApplyUse plmCarApplyUse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmCarApplyUse)){
			return form(plmCarApplyUse, model);
		}
		plmCarApplyUseService.save(plmCarApplyUse);
		addMessage(redirectAttributes, "保存用车申请成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}
	@RequestMapping(value = "apply")
	public String apply(PlmCarApplyUse plmCarApplyUse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmCarApplyUse)){
			return form(plmCarApplyUse, model);
		}
		plmCarApplyUseService.apply(plmCarApplyUse);

		addMessage(redirectAttributes, "提交用车申请成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}	
	@RequestMapping(value = "delete")
	public String delete(PlmCarApplyUse plmCarApplyUse, RedirectAttributes redirectAttributes) {
		plmCarApplyUseService.delete(plmCarApplyUse);
		addMessage(redirectAttributes, "删除用车申请成功");
		return "redirect:"+Global.getAdminPath()+"/car/apply/plmCarApplyUse/?repage";
	}
	@ResponseBody
	@RequestMapping(value = "printPdfIo")
	public void printPdfIo(PlmCarApplyUse plmCarApplyUse, Model model, RedirectAttributes redirectAttributes ,HttpServletRequest request ,HttpServletResponse response ) throws DocumentGeneratingException{
		
		
		      //将对象转成map 并将时间类型格式化"yyyy-MM-dd"
		      Map<String, Object> purmap=ResourceUitle.transBean2Map(plmCarApplyUse);	
		      
		      //有数据字典的  要换成名称
		      if(StringUtils.isNotBlank(plmCarApplyUse.getPlmAct().getIsSup())){
		    	  purmap.put("isSup",DictUtils.getDictLabel(plmCarApplyUse.getPlmAct().getIsSup(),"yes_no",""));
		      }		      
		      
		      //流转信息  actProcIns
		      plmCarApplyUse.getAct().setProcInsId(plmCarApplyUse.getProcInsId());
		      
		      //1: ProcInsId   2审批内容跨列数Colspan      3审批标题跨列数titleColspan
		      purmap.put("actProcIns",actTaskService.histoicTable(plmCarApplyUse.getProcInsId(), "6" ,"2"));
		      
		      
		     //获取项目根路径
		     String path=request.getSession().getServletContext().getRealPath("/");
		       // classpath 中模板路径
				String template = "WEB-INF/views/plm/car/apply/plmCarApplyUseViewTemplate.html";
				PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
				// 生成pdf 返回流			
				pdfGenerator.generate(template, purmap, path,response);			          		            
		              
		            		
	}
}