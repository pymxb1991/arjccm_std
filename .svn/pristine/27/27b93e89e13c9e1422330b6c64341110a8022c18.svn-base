/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.purchase.web;

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
import com.arjjs.ccm.modules.plm.purchase.entity.PlmPurchaseApply;
import com.arjjs.ccm.modules.plm.purchase.service.PlmPurchaseApplyService;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsDict;
import com.arjjs.ccm.modules.plm.statistics.service.PlmStatisticsDictService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.PlmTypes;
import com.arjjs.ccm.tool.pdf.PdfDocumentGenerator;
import com.arjjs.ccm.tool.pdf.ResourceUitle;
import com.arjjs.ccm.tool.pdf.exception.DocumentGeneratingException;

import net.sf.json.JSONArray;

/**
 * 采购计划申请Controller
 * 
 * @author liuxue
 * @version 2018-07-07
 */
@Controller
@RequestMapping(value = "${adminPath}/purchase/plmPurchaseApply")
public class PlmPurchaseApplyController extends BaseController {

	@Autowired
	private PlmPurchaseApplyService plmPurchaseApplyService;

	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private PlmActService plmActService;

	@Autowired
	private PlmStatisticsDictService plmStatisticsDictService;

	@ModelAttribute
	public PlmPurchaseApply get(@RequestParam(required = false) String id) {
		PlmPurchaseApply entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = plmPurchaseApplyService.get(id);
			// 添加业务流程主表信息
			if (entity != null) {
				PlmAct plmAct = plmActService.getByTable(entity.getId(), PlmTypes.PURCHASE_APPLY_ID);
				if(plmAct!=null) {
					entity.setPlmAct(plmAct);
				}
			}
		}
		if (entity == null) {
			entity = new PlmPurchaseApply();
			entity.setOffice(UserUtils.getUser().getOffice());
			entity.setApplyDate(new Date());
		}
		return entity;
	}

	/**
	 * 列表
	 * 
	 * @param plmPurchaseApply
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */

	@RequestMapping(value = { "list", "" })
	public String list(PlmPurchaseApply plmPurchaseApply, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PlmPurchaseApply> page = plmPurchaseApplyService.findPage(new Page<PlmPurchaseApply>(request, response),
				plmPurchaseApply);
		model.addAttribute("page", page);

		return "plm/purchase/plmPurchaseApplyList";
	}

	/**
	 * 根据不同的审批进度 挑转不同的页面
	 * 
	 * @param plmPurchaseApply
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "form")
	public String form(PlmPurchaseApply plmPurchaseApply, Model model) {

		plmPurchaseApply.getAct().setProcInsId(plmPurchaseApply.getProcInsId());
		String view = "plmPurchaseApplyForm";

		if (StringUtils.isBlank(plmPurchaseApply.getId())) {
			plmPurchaseApply.setCreateBy(UserUtils.getUser());
		}

		// 查看审批申请单*
		if (StringUtils.isNotBlank(plmPurchaseApply.getProcInsId())) {

			// 环节编号
			String taskDefKey = plmPurchaseApply.getAct().getTaskDefKey();

			// 查看工单
			if (plmPurchaseApply.getAct().isFinishTask()) {
				view = "plmPurchaseApplyView";
			}
			// 重申修改环节
			else if ("modify".equals(taskDefKey)) {
				view = "plmPurchaseApplyForm";
			}
			// 审核环节
			else if ("processEnd".equals(taskDefKey)) {
				view = "plmPurchaseApplyAudit";
				// 终点无审核 去掉驳回按钮
				model.addAttribute("rejectedBtn", false);
			} else {
				view = "plmPurchaseApplyAudit";
				// 终点审核 加驳回按钮
				model.addAttribute("rejectedBtn", true);
			}

		}
		String cancelFlag = "0";
		if (StringUtils.isNotBlank(plmPurchaseApply.getCancelFlag()) && "02".equals(plmPurchaseApply.getCancelFlag())) {
			cancelFlag = "1";
		}
		model.addAttribute("cancelFlag", cancelFlag);
		model.addAttribute("plmPurchaseApply", plmPurchaseApply);

		return "plm/purchase/" + view;
	}

	/**
	 * 生成pdf (返回流)
	 * 
	 * @param plmPurchaseApply
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws DocumentGeneratingException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "printPdfIo")
	public void printPdfIo(PlmPurchaseApply plmPurchaseApply, Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response) throws DocumentGeneratingException {

		// 将对象转成map 并将时间类型格式化"yyyy-MM-dd"
		Map<String, Object> purmap = ResourceUitle.transBean2Map(plmPurchaseApply);

		// 有数据字典的 要换成名称
		purmap.put("fundingType",
				DictUtils.getDictLabel(plmPurchaseApply.getFundingType(), "purchase_funding_type", "其他"));
		if (StringUtils.isNotBlank(plmPurchaseApply.getPlmAct().getIsSup())) {
			purmap.put("isSup", DictUtils.getDictLabel(plmPurchaseApply.getPlmAct().getIsSup(), "yes_no", ""));
		}

		// 联系电话/手机
		String userPurphone = plmPurchaseApply.getUserPur().getPhone();
		if (userPurphone.isEmpty()) {
			userPurphone = plmPurchaseApply.getUserPur().getMobile();
		}
		purmap.put("userPurphone", userPurphone);

		// 联系电话/手机/邮箱
		String userTechphone = plmPurchaseApply.getUserTech().getPhone();
		if (userTechphone.isEmpty()) {
			userTechphone = plmPurchaseApply.getUserTech().getMobile();
			if (userTechphone.isEmpty()) {
				userTechphone = plmPurchaseApply.getUserTech().getEmail();
			}
		}
		purmap.put("userTechphone", userTechphone);

		// 流转信息 actProcIns
		plmPurchaseApply.getAct().setProcInsId(plmPurchaseApply.getProcInsId());
		// 1: ProcInsId 2审批内容跨列数Colspan 3审批标题跨列数titleColspan
		purmap.put("actProcIns", actTaskService.histoicTable(plmPurchaseApply.getProcInsId(), "5", "1"));

		// 获取项目根路径
		String path = request.getSession().getServletContext().getRealPath("/");
		// classpath 中模板路径
		String template = "WEB-INF/views/plm/purchase/plmPurchaseApplyViewTemplate.html";
		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf 返回流
		pdfGenerator.generate(template, purmap, path, response);
	}

	/**
	 * 保存 不提交
	 * 
	 * @param plmPurchaseApply
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */

	@RequestMapping(value = "saveUnSubmit")
	public String saveUnSubmit(PlmPurchaseApply plmPurchaseApply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmPurchaseApply)) {
			return form(plmPurchaseApply, model);
		}
		plmPurchaseApplyService.saveUnSubmit(plmPurchaseApply);
		addMessage(redirectAttributes, "保存采购计划申请成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}

	/**
	 * 提交
	 * 
	 * @param plmPurchaseApply
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */

	@RequestMapping(value = "save")
	public String save(PlmPurchaseApply plmPurchaseApply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmPurchaseApply)) {
			return form(plmPurchaseApply, model);
		}

		plmPurchaseApplyService.save(plmPurchaseApply);
		addMessage(redirectAttributes, "提交采购计划申请成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}

	/**
	 * 工单执行（完成任务）
	 * 
	 * @param plmPurchaseApply
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "saveAudit")
	public String saveAudit(PlmPurchaseApply plmPurchaseApply, Model model) {
		if (StringUtils.isBlank(plmPurchaseApply.getAct().getFlag())
				|| StringUtils.isBlank(plmPurchaseApply.getAct().getComment())) {
			addMessage(model, "请填写审核意见。");
			return form(plmPurchaseApply, model);
		}
		plmPurchaseApplyService.auditSave(plmPurchaseApply);
		return "redirect:" + adminPath + "/act/task/todo/";
	}

	@RequestMapping(value = "delete")
	public String delete(PlmPurchaseApply plmPurchaseApply, RedirectAttributes redirectAttributes) {
		plmPurchaseApplyService.delete(plmPurchaseApply);
		addMessage(redirectAttributes, "删除采购计划申请成功");
		return "redirect:" + Global.getAdminPath() + "/purchase/plmPurchaseApply/?repage";
	}

	/**
	 * 统计图
	 */
	@RequestMapping(value = "purchaseStatistics")
	public String purchaseStatistics(HttpServletRequest request, HttpServletResponse response, String height, String width, String content, String sid) {
		PlmStatisticsDict PlmStatisticsDict = plmStatisticsDictService.typeAndLine(content);
		request.setAttribute("porline", PlmStatisticsDict.getLine());
		request.setAttribute("portype", PlmStatisticsDict.getType());
		request.setAttribute("porheigh", height);
		request.setAttribute("porwidth", width);
		request.setAttribute("porid", sid);
		PlmPurchaseApply plmPurchaseApply = new PlmPurchaseApply();
		// 统计各个经费类型的数量 以EchartType类返回
		List<Map<String, Object>> echartTypeList = plmPurchaseApplyService.fundingTypeStatistic(plmPurchaseApply);
		List<Object> listValue = new ArrayList<>();
		for (Map<String, Object> map : echartTypeList) {
			map.put("type", DictUtils.getDictLabel((String) map.get("type"), "purchase_funding_type", "其他"));
			listValue.add(map);
		}
		// 将list 转化为json格式
		JSONArray jsondata = JSONArray.fromObject(listValue);
		request.setAttribute("jsondata", jsondata);
		return "plm/statistics/purchase/purchaseStatistics";
	}
}