/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.equapply.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
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
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.act.service.ActTaskService;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.plm.act.service.PlmActService;
import com.arjjs.ccm.modules.plm.equapply.entity.PlmEquApply;
import com.arjjs.ccm.modules.plm.equapply.entity.PlmEquEquipment;
import com.arjjs.ccm.modules.plm.equapply.service.PlmEquApplyMaintenanceService;
import com.arjjs.ccm.modules.plm.equapply.service.PlmEquEquipmentService;
import com.arjjs.ccm.modules.plm.storage.entity.AjaxResultEntity;
import com.arjjs.ccm.modules.plm.storage.entity.PlmEquipment;
import com.arjjs.ccm.modules.plm.storage.service.PlmEquipmentService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.tool.CommUtil;
import com.arjjs.ccm.tool.PlmTypes;
import com.arjjs.ccm.tool.pdf.PdfDocumentGenerator;
import com.arjjs.ccm.tool.pdf.ResourceUitle;
import com.arjjs.ccm.tool.pdf.exception.DocumentGeneratingException;

import net.sf.json.JSONObject;

/**
 * 物资申请Controller
 * 
 * @author liucong
 * @version 2018-06-30
 */
@Controller
@RequestMapping(value = "${adminPath}/equapply/plmEquApplyMaintenance")
public class PlmEquApplyMaintenanceController extends BaseController {

	@Autowired
	private PlmEquApplyMaintenanceService plmEquApplyMaintenanceService;
	@Autowired
	private PlmEquipmentService plmEquipmentService;
	@Autowired
	private PlmActService plmActService;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private PlmEquEquipmentService plmEquEquipmentService;
	PlmTypes plmTypes;

	@ModelAttribute
	public PlmEquApply get(@RequestParam(required = false) String id, @RequestParam(required = false) String ids) {
		PlmEquApply entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = plmEquApplyMaintenanceService.get(id);
			// 添加业务流程主表信息
			if ((ids == "" || ids == null) && entity != null) {
				PlmAct plmAct = plmActService.getByTable(entity.getId(), PlmTypes.EQU_APPLY_MAINTENANCE);
				if(plmAct!=null) {
					entity.setPlmAct(plmAct);
				}
			}
		}
		if (entity == null) {
			entity = new PlmEquApply();
		}
		return entity;
	}

	/**
	 * 
	 * @param plmEquApply
	 * @param request
	 * @param response
	 * @param model
	 * @return 查询全部返回list页面
	 */
	@RequestMapping(value = { "list", "" })
	public String list(PlmEquApply plmEquApply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmEquApply> page = plmEquApplyMaintenanceService.findPage(new Page<PlmEquApply>(request, response),
				plmEquApply);
		model.addAttribute("page", page);
		return "plm/equapply/plmEquApplyMaintenanceList";
	}

	/**
	 * 
	 * @param plmEquApply
	 * @param model
	 * @return 跳转到添加申请信息form页面
	 */
	@RequestMapping(value = "form")
	public String form(PlmEquApply plmEquApply, Model model) {
		plmEquApply.getAct().setProcInsId(plmEquApply.getProcInsId());
		String view = "plmEquApplyMaintenanceForm";
		if (StringUtils.isNotBlank(plmEquApply.getProcInsId())) {
			plmEquApply.getAct().setProcInsId(plmEquApply.getProcInsId());
			// 环节编号
			String taskDefKey = plmEquApply.getAct().getTaskDefKey();
			// 查看工单
			if (plmEquApply.getAct().isFinishTask()) {
				view = "plmEquApplyMaintenanceView";
			} else if ("modify".equals(taskDefKey)) {
				// 修改环节
				view = "plmEquApplyMaintenanceForm";
			} else if ("processEnd".equals(taskDefKey)) {
				view = "plmEquApplyMaintenanceAudit";
				// 终点无审核 去掉驳回按钮
				model.addAttribute("rejectedBtn", false);
			} else {
				// 审核环节
				view = "plmEquApplyMaintenanceAudit";
				// 终点审核 加驳回按钮
				model.addAttribute("rejectedBtn", true);
			}
		}
		String cancelFlag = "0";
		if (StringUtils.isNotBlank(plmEquApply.getCancelFlag()) && "02".equals(plmEquApply.getCancelFlag())) {
			cancelFlag = "1";
		}
		model.addAttribute("cancelFlag", cancelFlag);
		List<PlmEquEquipment> plmEquEquipmentList = plmEquEquipmentService.findByApply(plmEquApply.getId());
		model.addAttribute("plmEquEquipmentList", plmEquEquipmentList);
		model.addAttribute("plmEquApplyMaintenance", plmEquApply);
		return "plm/equapply/" + view;
	}

	/**
	 * 
	 * @param plmEquApply
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return 实现物品的添加/修改到数据的功能返回list页面
	 * @throws IOException 
	 */
	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value = "save")
	public String save(PlmEquApply plmEquApply, PlmEquipment plmEquipment, Model model,
			RedirectAttributes redirectAttributes, ServletRequest request) {
		if (!beanValidator(model, plmEquApply)) {
			return form(plmEquApply, model);
		}
		plmEquApply.setType(plmTypes.EQU_MAINTENANCE);
		plmEquApplyMaintenanceService.save(plmEquApply);
		String[] equIds = request.getParameterValues("equIds");
		if (equIds != null && equIds.length > 0) {
			for (int i = 0; i < equIds.length; i++) {
				PlmEquEquipment plmEquEquipment = new PlmEquEquipment();
				plmEquipment = plmEquipmentService.findCode(equIds[i]);
				plmEquEquipment.setId("");
				plmEquEquipment.setApplyId(plmEquApply.getId());
				plmEquEquipment.setCode(plmEquipment.getCode());
				plmEquEquipment.setName(plmEquipment.getName());
				plmEquEquipment.setSpec(plmEquipment.getSpec());
				plmEquEquipment.setTypeId(plmEquipment.getTypeId());
				plmEquEquipment.setTypeChild(plmEquipment.getTypeChild());
				plmEquEquipment.setShape(plmEquipment.getShape());
				plmEquEquipment.setProductionDate(plmEquipment.getProductionDate());
				plmEquEquipmentService.save(plmEquEquipment);
			}
		}
		addMessage(redirectAttributes, "提交成功");
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

	@SuppressWarnings({ "unused", "static-access" })
	@RequestMapping(value = "notCommit")
	public String notCommit(PlmEquApply plmEquApply, PlmEquipment plmEquipment, Model model,
			RedirectAttributes redirectAttributes, ServletRequest request) {
		if (!beanValidator(model, plmEquApply)) {
			return form(plmEquApply, model);
		}
		plmEquApply.setType(plmTypes.EQU_MAINTENANCE);
		plmEquApplyMaintenanceService.notCommit(plmEquApply);
		String[] equIds = request.getParameterValues("equIds");
		if (equIds != null && equIds.length > 0) {
			for (int i = 0; i < equIds.length; i++) {
				PlmEquEquipment plmEquEquipment = new PlmEquEquipment();
				plmEquipment = plmEquipmentService.findCode(equIds[i]);
				plmEquEquipment.setId("");
				plmEquEquipment.setApplyId(plmEquApply.getId());
				plmEquEquipment.setCode(plmEquipment.getCode());
				plmEquEquipment.setName(plmEquipment.getName());
				plmEquEquipment.setSpec(plmEquipment.getSpec());
				plmEquEquipment.setTypeId(plmEquipment.getTypeId());
				plmEquEquipment.setTypeChild(plmEquipment.getTypeChild());
				plmEquEquipment.setShape(plmEquipment.getShape());
				plmEquEquipment.setProductionDate(plmEquipment.getProductionDate());
				plmEquEquipmentService.save(plmEquEquipment);
			}
		}
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}

	/**
	 * 
	 * @param plmEquApply
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveAudit")
	public String saveAudit(PlmEquApply plmEquApply, Model model) {
		if (StringUtils.isBlank(plmEquApply.getAct().getFlag())
				|| StringUtils.isBlank(plmEquApply.getAct().getComment())) {
			addMessage(model, "请填写审核意见。");
			return form(plmEquApply, model);
		}
		plmEquApplyMaintenanceService.auditSave(plmEquApply);
		return "redirect:" + adminPath + "/act/task/todo/";
	}

	@RequestMapping(value = "delete")
	public String delete(PlmEquApply plmEquApply, RedirectAttributes redirectAttributes) {
		plmEquApplyMaintenanceService.delete(plmEquApply);
		addMessage(redirectAttributes, "删除物资申请成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}

	@RequestMapping(value = "deleteDetail")
	@ResponseBody
	public void deleteId(@RequestParam(required = false) String id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		AjaxResultEntity resultEntity = new AjaxResultEntity();
		if (StringUtils.isBlank(id)) {
			resultEntity.setRet(AjaxResultEntity.ERROR_PARAM);
			resultEntity.setMessage("id为空！");
			response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
			return;
		}
		PlmEquEquipment plmEquEquipment = new PlmEquEquipment();
		plmEquEquipment.setId(id);
		plmEquEquipmentService.delete(plmEquEquipment);
		resultEntity.setRet(AjaxResultEntity.ERROR_OK);
		response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
	}

	/**
	 * 根据编号获取资产信息
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */

	@RequestMapping(value = "getEquipmentById")
	@ResponseBody
	public void getEquipmentById(@RequestParam(required = true) String code, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		AjaxResultEntity resultEntity = new AjaxResultEntity();
		if (StringUtils.isBlank(code)) {
			resultEntity.setRet(AjaxResultEntity.ERROR_PARAM);
			resultEntity.setMessage("物资编号为空！");
			response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
			return;
		}
		PlmEquipment plmEquipment = new PlmEquipment();
		plmEquipment.setCode(code);
		List<PlmEquipment> equList = plmEquipmentService.findList(plmEquipment);
		/*
		 if (equList == null || !(equList.size() > 0) || equList.get(0) == null) {
		 resultEntity.setRet(AjaxResultEntity.ERROR_PARAM);
		 resultEntity.setMessage("该物资信息不存在！");
		 response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
		 return; }*/
		 
		plmEquipment = equList.get(0);
		StringBuffer buffer = new StringBuffer();
		buffer.append("<tr>");
		buffer.append("<td style=\"display: none;\"><input name=\"equIds\" type=\"text\" value=\""
				+ plmEquipment.getId() + "\"/></td>");
		buffer.append("<td  class=\"trtop codeId\" colspan=\"2\">");
		buffer.append(plmEquipment.getCode());
		buffer.append("</td>");
		buffer.append(getTd(plmEquipment.getName()));
		buffer.append(getTd(plmEquipment.getSpec()));
		buffer.append(getTd(DictUtils.getDictLabel(plmEquipment.getTypeId(), "plm_equipment_type", "未知")));
		buffer.append(getTd(DictUtils.getDictLabel(plmEquipment.getTypeChild(), "plm_equipment_type_child", "未知")));
		buffer.append(getTd(DateUtils.formatDateTime(plmEquipment.getProductionDate())));
		buffer.append(getTd("<a  title='delete'>删除</a>"));
		buffer.append("</tr>");
		resultEntity.setRet(AjaxResultEntity.ERROR_OK);
		resultEntity.setResult(buffer.toString());
		response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
	}

	/**
	 * 拼接td节点
	 * 
	 * @param value
	 * @return
	 */
	private String getTd(String value) {
		return "<td class='trtop'>" + value + "</td>";
	}

	@ResponseBody
	@RequestMapping(value = "printPdfIo")
	public void printPdfIo(PlmEquApply plmEquApply, Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response) throws DocumentGeneratingException {

		// 将对象转成map 并将时间类型格式化"yyyy-MM-dd"
		Map<String, Object> purmap = ResourceUitle.transBean2Map(plmEquApply);

		// 有数据字典的 要换成名称plmEquApply.equipment.typeId, 'plm_equipment_type', ''
		// plmEquApply.equipment.typeChild, 'plm_equipment_type_child', ''

		if (StringUtils.isNotBlank(plmEquApply.getPlmAct().getIsSup())) {
			purmap.put("isSup", DictUtils.getDictLabel(plmEquApply.getPlmAct().getIsSup(), "yes_no", ""));
		}
		
		
		List<PlmEquEquipment> plmEquEquipmentList = plmEquEquipmentService.findByApply(plmEquApply.getId());
      
		String detail="";
      
		for (PlmEquEquipment plmEquApplyDetail2 : plmEquEquipmentList) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			
			
			detail+="<tr>\n" + 
				"           <td class='trtop'>"+plmEquApplyDetail2.getCode()+"</td>\n" +
				"			<td class='trtop'>"+plmEquApplyDetail2.getName()+"</td>\n" + 
				"			<td class='trtop'>"+plmEquApplyDetail2.getSpec()+"</td>\n" + 
				"			<td class='trtop'>"+DictUtils.getDictLabel(plmEquApplyDetail2.getTypeId(), "plm_equipment_type", "")+"</td>\n" + 
				"           <td class='trtop'>"+DictUtils.getDictLabel(plmEquApplyDetail2.getTypeChild(), "plm_equipment_type_child", "")+"</td>\n " +
				"           <td class='trtop'>"+sdf.format(plmEquApplyDetail2.getProductionDate())+"</td>\n" +
				"				</tr>";
		}
      
         
      	purmap.put("detail", detail.replaceAll("null", ""));
		// 流转信息 actProcIns
		plmEquApply.getAct().setProcInsId(plmEquApply.getProcInsId());

		// 1: ProcInsId 2审批内容跨列数Colspan 3审批标题跨列数titleColspan
		purmap.put("actProcIns", actTaskService.histoicTable(plmEquApply.getProcInsId(), "6", "2"));

		// 获取项目根路径
		String path = request.getSession().getServletContext().getRealPath("/");
		// classpath 中模板路径
		String template = "WEB-INF/views/plm/equapply/plmEquApplyMaintenanceViewTemplate.html";
		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf 返回流
		pdfGenerator.generate(template, purmap, path, response);

	}

}
