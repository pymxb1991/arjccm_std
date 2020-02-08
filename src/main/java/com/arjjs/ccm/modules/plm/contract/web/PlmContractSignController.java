/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.contract.web;

import java.io.IOException;
import java.util.ArrayList;
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
import com.arjjs.ccm.modules.act.service.ActTaskService;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.plm.act.service.PlmActService;
import com.arjjs.ccm.modules.plm.contract.entity.PlmContractSign;
import com.arjjs.ccm.modules.plm.contract.service.PlmContractSignService;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsDict;
import com.arjjs.ccm.modules.plm.statistics.service.PlmStatisticsDictService;
import com.arjjs.ccm.modules.plm.storage.service.PlmEquipmentService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.PlmTypes;
import com.arjjs.ccm.tool.pdf.PdfDocumentGenerator;
import com.arjjs.ccm.tool.pdf.ResourceUitle;
import com.arjjs.ccm.tool.pdf.exception.DocumentGeneratingException;

import net.sf.json.JSONArray;

/**
 * 采购合同会签Controller
 * 
 * @author liuxue
 * @version 2018-07-07
 */
@Controller
@RequestMapping(value = "${adminPath}/contract/plmContractSign")
public class PlmContractSignController extends BaseController {

	@Autowired
	private PlmContractSignService plmContractSignService;
	@Autowired
	private PlmEquipmentService plmEquipmentService;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private PlmActService plmActService;
	@Autowired
	private PlmStatisticsDictService plmStatisticsDictService;

	@ModelAttribute
	public PlmContractSign get(@RequestParam(required = false) String id) {
		PlmContractSign entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = plmContractSignService.get(id);
			// 添加业务流程主表信息
			if(entity!=null) {
				PlmAct plmAct = plmActService.getByTable(entity.getId(), PlmTypes.CONTRACT_SIGN_ID);
				if(plmAct!=null) {
					entity.setPlmAct(plmAct);
				}
			}
		}
		if (entity == null) {
			entity = new PlmContractSign();
			entity.setApplyDate(new Date());
			// 因为entity.setDepart(UserUtils.getUser().getOffice());这样写会出现 部门查询时
			// 会把UserUtils.getUser().getOffice()中 的部门更改的bug(暂无法找到原因 )
			Office office = new Office();
			office.setId(UserUtils.getUser().getOffice().getId());
			office.setName(UserUtils.getUser().getOffice().getName());
			entity.setDepart(office);
		}
		return entity;
	}

	/**
	 * 列表页面
	 * 
	 * @param plmContractSign
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */

	@RequestMapping(value = { "list", "" })
	public String list(PlmContractSign plmContractSign, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmContractSign> page = plmContractSignService.findPage(new Page<PlmContractSign>(request, response), plmContractSign);
		model.addAttribute("page", page);
		return "plm/contract/plmContractSignList";
	}

	/**
	 * 根据不同的审批进度 挑转不同的页面
	 * 
	 * @param plmContractSign
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "form")
	public String form(PlmContractSign plmContractSign, Model model) {
		// 供应商下拉列表
		model.addAttribute("provideList", plmEquipmentService.getProvidList());
		plmContractSign.getAct().setProcInsId(plmContractSign.getProcInsId());
		if (StringUtils.isBlank(plmContractSign.getId())) {
			plmContractSign.setCreateBy(UserUtils.getUser());
		}
		String view = "plmContractSignForm";
		// 查看审批申请单
		if (StringUtils.isNotBlank(plmContractSign.getProcInsId())) {
			// 环节编号
			String taskDefKey = plmContractSign.getAct().getTaskDefKey();
			// 查看工单
			if (plmContractSign.getAct().isFinishTask()) {
				view = "plmContractSignView";
			}
			// 修改环节
			else if ("modify".equals(taskDefKey)) {
				view = "plmContractSignForm";
			}
			// 审核环节
			else if ("processEnd".equals(taskDefKey)) {
				view = "plmContractSignAudit";
				// 终点无审核 去掉驳回按钮
				model.addAttribute("rejectedBtn", false);
			} else {
				view = "plmContractSignAudit";
				// 终点审核 加驳回按钮
				model.addAttribute("rejectedBtn", true);
			}
		}
		String cancelFlag = "0";
		if (StringUtils.isNotBlank(plmContractSign.getCancelFlag()) && "02".equals(plmContractSign.getCancelFlag())) {
			cancelFlag = "1";
		}
		model.addAttribute("cancelFlag", cancelFlag);
		model.addAttribute("plmContractSign", plmContractSign);
		return "plm/contract/" + view;
	}

	/**
	 * 生成pdf (返回流)
	 * 
	 * @param plmContractSign
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws DocumentGeneratingException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "printPdfIo")
	public void printPdfIo(PlmContractSign plmContractSign, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws DocumentGeneratingException {
		// 将对象转成map 并将时间类型格式化"yyyy-MM-dd"
		Map<String, Object> purmap = ResourceUitle.transBean2Map(plmContractSign);
		// 有数据字典的 要把值换成名称 最后一位参数是默认值
		purmap.put("contractType", DictUtils.getDictLabel(plmContractSign.getContractType(), "contract_contract_type", "其他"));
		purmap.put("isStandard", DictUtils.getDictLabel(plmContractSign.getContractType(), "contract_is_standard", "否"));
		purmap.put("provider", DictUtils.getDictLabel(plmContractSign.getProvider(), "contract_provider", ""));
		purmap.put("isBudget", DictUtils.getDictLabel(plmContractSign.getIsBudget(), "contract_isBudget", ""));
		if (StringUtils.isNotBlank(plmContractSign.getPlmAct().getIsSup())) {
			purmap.put("isSup", DictUtils.getDictLabel(plmContractSign.getPlmAct().getIsSup(), "yes_no", ""));
		}
		// 流转信息 actProcIns
		plmContractSign.getAct().setProcInsId(plmContractSign.getProcInsId());
		// 1: ProcInsId 2审批内容跨列数Colspan 3审批标题跨列数titleColspan
		purmap.put("actProcIns", actTaskService.histoicTable(plmContractSign.getProcInsId(), "5", "1"));
		// 获取项目根路径
		String path = request.getSession().getServletContext().getRealPath("/");
		// classpath 中模板路径
		String template = "WEB-INF/views/plm/contract/plmContractSignViewTemplate.html";
		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf 返回流
		pdfGenerator.generate(template, purmap, path, response);
	}

	/**
	 * 保存 不提交
	 * 
	 * @param plmContractSign
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */

	@RequestMapping(value = "saveUnSubmit")
	public String saveUnSubmit(PlmContractSign plmContractSign, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmContractSign)) {
			return form(plmContractSign, model);
		}
		plmContractSignService.saveUnSubmit(plmContractSign);
		addMessage(redirectAttributes, "保存采购合同会成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}

	@RequestMapping(value = "save")
	public String save(PlmContractSign plmContractSign, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmContractSign)) {
			return form(plmContractSign, model);
		}
		plmContractSignService.save(plmContractSign);
		addMessage(redirectAttributes, "提交采购合同会签成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}

	/**
	 * 工单执行（完成任务）
	 * 
	 * @param plmContractSign
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "saveAudit")
	public String saveAudit(PlmContractSign plmContractSign, Model model) {
		if (StringUtils.isBlank(plmContractSign.getAct().getFlag()) || StringUtils.isBlank(plmContractSign.getAct().getComment())) {
			addMessage(model, "请填写审核意见。");
			return form(plmContractSign, model);
		}
		plmContractSignService.auditSave(plmContractSign);
		return "redirect:" + adminPath + "/act/task/todo/";
	}

	@RequestMapping(value = "delete")
	public String delete(PlmContractSign plmContractSign, RedirectAttributes redirectAttributes) {
		plmContractSignService.delete(plmContractSign);
		addMessage(redirectAttributes, "删除采购合同会签成功");
		return "redirect:" + Global.getAdminPath() + "/contract/plmContractSign/?repage";
	}

	/**
	 * 统计图
	 */
	@RequestMapping(value = { "contractStatistics" })
	public String contractStatistics(HttpServletRequest request, HttpServletResponse response, String height, String width, String content, String sid) {
		PlmStatisticsDict PlmStatisticsDict = plmStatisticsDictService.typeAndLine(content);
		request.setAttribute("porline", PlmStatisticsDict.getLine());
		request.setAttribute("portype", PlmStatisticsDict.getType());
		request.setAttribute("porheigh", height);
		request.setAttribute("porwidth", width);
		request.setAttribute("porid", sid);
		PlmContractSign plmContractSign = new PlmContractSign();
		// 统计各个经费类型的数量 以EchartType类返回
		List<Map<String, Object>> echartTypeList = plmContractSignService.contractTypeStatistic(plmContractSign);
		List<Object> listValue = new ArrayList<>();
		for (Map<String, Object> map : echartTypeList) {
			map.put("type", DictUtils.getDictLabel((String) map.get("type"), "contract_contract_type", "其他"));
			listValue.add(map);
		}
		// 将list 转化为json格式
		JSONArray jsondata = JSONArray.fromObject(listValue);
		request.setAttribute("jsondata", jsondata);
		return "plm/statistics/contract/contractStatistics";
	}
}