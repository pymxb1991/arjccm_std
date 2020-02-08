/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.logistics.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
import com.arjjs.ccm.modules.plm.logistics.entity.PlmRoomApply;
import com.arjjs.ccm.modules.plm.logistics.service.PlmRoomMeetingApplyService;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsDict;
import com.arjjs.ccm.modules.plm.statistics.service.PlmStatisticsDictService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.PlmTypes;
import com.arjjs.ccm.tool.pdf.PdfDocumentGenerator;
import com.arjjs.ccm.tool.pdf.ResourceUitle;
import com.arjjs.ccm.tool.pdf.exception.DocumentGeneratingException;

import net.sf.json.JSONArray;

/**
 * 会议申请Controller
 * 
 * @author fu
 * @version 2018-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/logistics/plmRoomMeetingApply")
public class PlmRoomMeetingApplyController extends BaseController {

	@Autowired
	private PlmRoomMeetingApplyService plmRoomApplyService;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private PlmActService plmActService;
	@Autowired
	private PlmStatisticsDictService plmStatisticsDictService;

	@ModelAttribute
	public PlmRoomApply get(@RequestParam(required = false) String id) {
		PlmRoomApply entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = plmRoomApplyService.get(id);
			// 添加业务流程主表信息
			if(entity!=null) {
				PlmAct plmAct = plmActService.getByTable(entity.getId(), PlmTypes.ROOM_APPLY_MEETING_ID);
				if(plmAct!=null) {
					entity.setPlmAct(plmAct);
				}
			}
		}
		if (entity == null) {
			entity = new PlmRoomApply();
			entity.setInitiator(UserUtils.getUser());
			entity.setCategory("01");
		}
		return entity;
	}

	/**
	 * @param plmRoomApply
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String list(PlmRoomApply plmRoomApply, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		plmRoomApply.setCreateBy(UserUtils.getUser());
		Page<PlmRoomApply> page = plmRoomApplyService.findPage(new Page<PlmRoomApply>(request, response), plmRoomApply);
		model.addAttribute("page", page);
		return "plm/logistics/plmRoomMeetingApplyList";
	}

	@RequestMapping(value = "form")
	public String form(PlmRoomApply plmRoomApply, Model model) {
		plmRoomApply.getAct().setProcInsId(plmRoomApply.getProcInsId());
		String view = "plmRoomMeetingApplyForm";
		if (StringUtils.isNotBlank(plmRoomApply.getId())) {
			// 查找参会人员user表
			plmRoomApply = plmRoomApplyService.findAttendeeList(plmRoomApply);
		}
		if (StringUtils.isNotBlank(plmRoomApply.getProcInsId())) {
			// 增加查找参会人员user表
			// 环节编号
			String taskDefKey = plmRoomApply.getAct().getTaskDefKey();

			// 查看工单
			if (plmRoomApply.getAct().isFinishTask()) {
				view = "plmRoomMeetingApplyView";
			}
			// 修改环节
			else if ("modify".equals(taskDefKey)) {
				view = "plmRoomMeetingApplyForm";
			} else if ("processEnd".equals(taskDefKey)) {
				view = "plmRoomMeetingApplyAudit";
				// 终点无审核 去掉驳回按钮
				model.addAttribute("rejectedBtn", false);
			} else {
				// 审核环节
				view = "plmRoomMeetingApplyAudit";
				// 终点审核 加驳回按钮
				model.addAttribute("rejectedBtn", true);
			}

		}
		String cancelFlag = "0";
		if (StringUtils.isNotBlank(plmRoomApply.getCancelFlag()) && "02".equals(plmRoomApply.getCancelFlag())) {
			cancelFlag = "1";
		}
		model.addAttribute("cancelFlag", cancelFlag);
		model.addAttribute("plmRoomApply", plmRoomApply);
		return "plm/logistics/" + view;
	}

	@RequestMapping(value = "save")
	public String save(PlmRoomApply plmRoomApply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmRoomApply)) {
			return form(plmRoomApply, model);
		}
		plmRoomApplyService.save(plmRoomApply);
		addMessage(redirectAttributes, "提交审批'" + plmRoomApply.getSubject() + "'成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}

	@RequestMapping(value = "apply")
	public String apply(PlmRoomApply plmRoomApply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmRoomApply)) {
			return form(plmRoomApply, model);
		}
		plmRoomApplyService.apply(plmRoomApply);
		addMessage(redirectAttributes, "提交审批'" + plmRoomApply.getSubject() + "'成功");
		return "redirect:" + Global.getAdminPath() + "/act/task/apply/";
	}

	/**
	 * 工单执行（完成任务）
	 * 
	 * @param testAudit
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveAudit")
	public String saveAudit(PlmRoomApply plmRoomApply, Model model) {
		if (StringUtils.isBlank(plmRoomApply.getAct().getFlag())
				|| StringUtils.isBlank(plmRoomApply.getAct().getComment())) {
			addMessage(model, "请填写审核意见。");
			return form(plmRoomApply, model);
		}
		plmRoomApplyService.auditSave(plmRoomApply);
		return "redirect:" + adminPath + "/act/task/todo/";
	}

	@RequestMapping(value = "delete")
	public String delete(PlmRoomApply plmRoomApply, RedirectAttributes redirectAttributes) {
		plmRoomApplyService.delete(plmRoomApply);
		addMessage(redirectAttributes, "删除会议申请成功");
		return "redirect:" + Global.getAdminPath() + "/logistics/plmRoomMeetingApply/?repage";
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
	public void printPdfIo(PlmRoomApply plmRoomApply, Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response) throws DocumentGeneratingException {

		// 将对象转成map 并将时间类型格式化"yyyy-MM-dd"
		Map<String, Object> purmap = ResourceUitle.transBean2Map(plmRoomApply);

		// 有数据字典的 要换成名称
		purmap.put("isSup", DictUtils.getDictLabel(plmRoomApply.getPlmAct().getIsSup(), "yes_no", ""));
		purmap.put("type", DictUtils.getDictLabel(plmRoomApply.getType(), "plm_room_apply_type", ""));
		// 流转信息 actProcIns
		plmRoomApply.getAct().setProcInsId(plmRoomApply.getProcInsId());

		// 1: ProcInsId 2审批内容跨列数Colspan 3审批标题跨列数titleColspan
		purmap.put("actProcIns", actTaskService.histoicTable(plmRoomApply.getProcInsId(), "6", "2"));

		// 获取项目根路径
		String path = request.getSession().getServletContext().getRealPath("/");
		// classpath 中模板路径
		String template = "WEB-INF/views/plm/logistics/plmRoomMeetingApplyViewTemplate.html";
		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf 返回流
		pdfGenerator.generate(template, purmap, path, response);

	}

	/**
	 * 统计图 接待 /会议室 使用次数 以月为时间轴
	 */
	@RequestMapping(value = { "roomApplyCount" })
	public String roomApplyCount(HttpServletRequest request, HttpServletResponse response, String height, String width,
			String content, String sid) {
		PlmStatisticsDict PlmStatisticsDict = plmStatisticsDictService.typeAndLine(content);
		request.setAttribute("porline", PlmStatisticsDict.getLine());
		request.setAttribute("portype", PlmStatisticsDict.getType());
		request.setAttribute("porheigh", height);
		request.setAttribute("porwidth", width);
		request.setAttribute("porid", sid);

		int qmonth = 12;// 前几个月
		// map : type(01 接待 02会议) qmonth（前几个月）
		Map<String, Object> mapechart = new HashMap<String, Object>();
		mapechart.put("qmonth", qmonth);

		// 统计各个前5个月申请使用接待室的数量
		mapechart.put("type", 01);
		List<Map<String, Object>> echartTypeList2 = plmRoomApplyService.findApplyCount(mapechart);
		// 统计各个前5个月申请使用会议室的数量
		mapechart.put("type", 02);
		List<Map<String, Object>> echartTypeList3 = plmRoomApplyService.findApplyCount(mapechart);

		List<Object> listmonth = new ArrayList<>();

		List<Object> listValue2 = new ArrayList<>();
		List<Object> listValue3 = new ArrayList<>();
		Calendar cale = Calendar.getInstance();
		// 获取当前月份
		int month = cale.get(Calendar.MONTH) + 1;
		for (int i = 1; i <= qmonth; i++) {
			int monthfor = month - qmonth + i;
			if (monthfor <= 0) {
				monthfor += 12;
			}
			listmonth.add(monthfor + "月");

			// 把结果填入list 集合 如果该月没数据填入0
			for (Map<String, Object> echartTypeMap2 : echartTypeList2) {
				if ((int) echartTypeMap2.get("month") == monthfor) {
					listValue2.add(echartTypeMap2.get("count"));
				} else {
					listValue2.add(0);
				}
			}
			for (Map<String, Object> echartTypeMap3 : echartTypeList3) {
				if ((int) echartTypeMap3.get("month") == monthfor) {
					listValue3.add(echartTypeMap3.get("count"));
				} else {
					listValue3.add(0);
				}
			}

		}

		// 将list 转化为json格式
		JSONArray jsonmonth = JSONArray.fromObject(listmonth);

		JSONArray jsondata2 = JSONArray.fromObject(listValue2);
		JSONArray jsondata3 = JSONArray.fromObject(listValue3);
		request.setAttribute("jsonmonth", jsonmonth);

		request.setAttribute("jsondata2", jsondata2);
		request.setAttribute("jsondata3", jsondata3);

		return "plm/statistics/logistics/roomApplyCountStatistic";
	}

	/**
	 * 统计图 <!--接待 /会议室 使用次数 -->
	 */
	@RequestMapping(value = { "roomUseCount" })
	public String roomUseCount(String isOffice, HttpServletRequest request, HttpServletResponse response, String height, String width, String content, String sid) {
		PlmStatisticsDict PlmStatisticsDict = plmStatisticsDictService.typeAndLine(content);
		request.setAttribute("porline", PlmStatisticsDict.getLine());
		request.setAttribute("portype", PlmStatisticsDict.getType());
		request.setAttribute("porheigh", height);
		request.setAttribute("porwidth", width);
		request.setAttribute("porid", sid);
		// mapechart : type(01 接待 02会议)
		Map<String, Object> mapechart = new HashMap<String, Object>();
		// 接待室
		mapechart.put("type", 02);
		List<EchartType> listValue1 = plmRoomApplyService.roomUseCount(mapechart);
		// 会议室
		mapechart.put("type", 01);
		List<EchartType> listValue2 = plmRoomApplyService.roomUseCount(mapechart);
		// 将list 转化为json格式
		JSONArray jsondata1 = JSONArray.fromObject(listValue1);
		JSONArray jsondata2 = JSONArray.fromObject(listValue2);
		request.setAttribute("jsondata1", jsondata1);
		request.setAttribute("jsondata2", jsondata2);
		return "plm/statistics/logistics/roomUseCount";
	}
}