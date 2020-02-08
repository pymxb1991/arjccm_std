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
import com.arjjs.ccm.modules.plm.car.entity.apply.PlmCarApplyScrap;
import com.arjjs.ccm.modules.plm.car.service.apply.PlmCarApplyScrapService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.PlmTypes;
import com.arjjs.ccm.tool.pdf.PdfDocumentGenerator;
import com.arjjs.ccm.tool.pdf.ResourceUitle;
import com.arjjs.ccm.tool.pdf.exception.DocumentGeneratingException;

/**
 * 报废申请Controller
 * @author fu
 * @version 2018-07-07
 */
@Controller
@RequestMapping(value = "${adminPath}/car/apply/plmCarApplyScrap")
public class PlmCarApplyScrapController extends BaseController {

	@Autowired
	private PlmCarApplyScrapService plmCarApplyScrapService;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private PlmActService plmActService;
	
	@ModelAttribute
	public PlmCarApplyScrap get(@RequestParam(required=false) String id) {
		PlmCarApplyScrap entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmCarApplyScrapService.get(id);
			//添加业务流程主表信息
			if(entity!=null) {
				PlmAct plmAct = plmActService.getByTable(entity.getId(), PlmTypes.CAR_APPLY_SCRAP_ID);
				if(plmAct!=null) {
					entity.setPlmAct(plmAct);
				}
			}
		}
		if (entity == null){
			entity = new PlmCarApplyScrap();
			entity.setUser(UserUtils.getUser());
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(PlmCarApplyScrap plmCarApplyScrap, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmCarApplyScrap> page = plmCarApplyScrapService.findPage(new Page<PlmCarApplyScrap>(request, response), plmCarApplyScrap); 
		model.addAttribute("page", page);
		return "plm/car/apply/plmCarApplyScrapList";
	}

	@RequestMapping(value = "form")
	public String form(PlmCarApplyScrap plmCarApplyScrap, Model model) {
		plmCarApplyScrap.getAct().setProcInsId(plmCarApplyScrap.getProcInsId());
		

		String view = "plmCarApplyScrapForm";
		
		// 查看审批申请单
		if (StringUtils.isNotBlank(plmCarApplyScrap.getProcInsId())){

			// 环节编号
			String taskDefKey = plmCarApplyScrap.getAct().getTaskDefKey();
			
			// 查看工单
			if(plmCarApplyScrap.getAct().isFinishTask()){
				view = "plmCarApplyScrapView";
			}
			// 修改环节
			else if ("modify".equals(taskDefKey)){
				view = "plmCarApplyScrapForm";
			}
			// 审核环节1
			else {
				view = "plmCarApplyScrapAudit";
			}
		}
		String cancelFlag = "0";
		if (StringUtils.isNotBlank(plmCarApplyScrap.getCancelFlag()) && "02".equals(plmCarApplyScrap.getCancelFlag())) {
			cancelFlag = "1";
		}
		model.addAttribute("cancelFlag", cancelFlag);
		model.addAttribute("plmCarApplyScrap", plmCarApplyScrap);
		return "plm/car/apply/" + view;
	}

	/**
	 * 工单执行（完成任务）
	 * @param testAudit
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveAudit")
	public String saveAudit(PlmCarApplyScrap plmCarApplyScrap, Model model) {
		if (StringUtils.isBlank(plmCarApplyScrap.getAct().getFlag())
				|| StringUtils.isBlank(plmCarApplyScrap.getAct().getComment())){
			addMessage(model, "请填写审核意见。");
			return form(plmCarApplyScrap, model);
		}
		plmCarApplyScrapService.auditSave(plmCarApplyScrap);
		return "redirect:" + adminPath + "/act/task/todo/";
	}
	
	@RequestMapping(value = "save")
	public String save(PlmCarApplyScrap plmCarApplyScrap, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmCarApplyScrap)){
			return form(plmCarApplyScrap, model);
		}
		plmCarApplyScrapService.save(plmCarApplyScrap);
		addMessage(redirectAttributes, "保存报废申请成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}
	@RequestMapping(value = "apply")
	public String apply(PlmCarApplyScrap plmCarApplyScrap, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmCarApplyScrap)){
			return form(plmCarApplyScrap, model);
		}
		plmCarApplyScrapService.apply(plmCarApplyScrap);

		addMessage(redirectAttributes, "提交报废申请成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}	
	@RequestMapping(value = "delete")
	public String delete(PlmCarApplyScrap plmCarApplyScrap, RedirectAttributes redirectAttributes) {
		plmCarApplyScrapService.delete(plmCarApplyScrap);
		addMessage(redirectAttributes, "删除报废申请成功");
		return "redirect:"+Global.getAdminPath()+"/car/apply/plmCarApplyScrap/?repage";
	}
	@ResponseBody
	@RequestMapping(value = "printPdfIo")
	public void printPdfIo(PlmCarApplyScrap plmCarApplyScrap, Model model, RedirectAttributes redirectAttributes ,HttpServletRequest request ,HttpServletResponse response ) throws DocumentGeneratingException{
		
		
		      //将对象转成map 并将时间类型格式化"yyyy-MM-dd"
		      Map<String, Object> purmap=ResourceUitle.transBean2Map(plmCarApplyScrap);	
		      
		      //有数据字典的  要换成名称
		      purmap.put("isSup",DictUtils.getDictLabel(plmCarApplyScrap.getPlmAct().getIsSup(),"yes_no",""));
		      purmap.put("type",DictUtils.getDictLabel(plmCarApplyScrap.getType(),"plm_car_apply_scrap_type",""));
		      //流转信息  actProcIns
		      plmCarApplyScrap.getAct().setProcInsId(plmCarApplyScrap.getProcInsId());
		      
		      //1: ProcInsId   2审批内容跨列数Colspan      3审批标题跨列数titleColspan
		      purmap.put("actProcIns",actTaskService.histoicTable(plmCarApplyScrap.getProcInsId(), "6" ,"2"));
		      
		      
		     //获取项目根路径
		     String path=request.getSession().getServletContext().getRealPath("/");
		       // classpath 中模板路径
				String template = "WEB-INF/views/plm/car/apply/plmCarApplyScrapViewTemplate.html";
				PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
				// 生成pdf 返回流			
				pdfGenerator.generate(template, purmap, path,response);			          		            
		              
		            		
	}
}