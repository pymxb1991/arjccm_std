/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.sys.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.utils.excel.ExportExcel;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.modules.ccm.log.service.CcmLogTailService;
import com.arjjs.ccm.modules.ccm.message.entity.CcmMessage;
import com.arjjs.ccm.modules.ccm.message.service.CcmMessageService;
import com.arjjs.ccm.modules.ccm.rest.web.CcmRestEvent;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmEventExport;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmSelfExport;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmWorkReport;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmWorkReportExport;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmWorkReportRead;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmWorkReportTO;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmWorkReportVO;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmWorkReportYO;
import com.arjjs.ccm.modules.ccm.sys.service.CcmWorkReportService;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.CommUtil;
import com.arjjs.ccm.tool.RabbitMQTools;
import com.google.common.collect.Maps;

import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作日志Controller
 * 
 * @author arj
 * @version 2018-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/ccmWorkReport")
public class CcmWorkReportController extends BaseController {

	@Autowired
	private CcmWorkReportService ccmWorkReportService;
	@Autowired
	private CcmLogTailService ccmLogTailService;
	@Autowired
	private CcmMessageService ccmMessageService;

	@ModelAttribute
	public CcmWorkReport get(@RequestParam(required = false) String id) {
		CcmWorkReport entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmWorkReportService.get(id);
		}
		if (entity == null) {
			entity = new CcmWorkReport();
		}
		return entity;
	}

	@RequiresPermissions("sys:ccmWorkReport:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmWorkReport ccmWorkReport, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		ccmWorkReport.setCreateBy(UserUtils.getUser());
		Page<CcmWorkReport> page = ccmWorkReportService.findPage(new Page<CcmWorkReport>(request, response),
				ccmWorkReport);
		model.addAttribute("page", page);
		return "ccm/sys/ccmWorkReportList";
	}

	@RequiresPermissions("sys:ccmWorkReport:view")
	@RequestMapping(value = "form")
	public String form(CcmWorkReport ccmWorkReport, Model model) {
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(ccmWorkReport.getId());
		ccmLogTailDto.setRelevanceTable("ccm_sys_workreport");
		List<CcmLogTail> ccmLogTailList = ccmLogTailService.findListByObject(ccmLogTailDto);
		// 返回查询结果
		model.addAttribute("ccmLogTailList", ccmLogTailList);
		if (StringUtils.isNotEmpty(ccmWorkReport.getId())) {
			ccmWorkReport = ccmWorkReportService.getRecordList(ccmWorkReport);
		}
		model.addAttribute("ccmWorkReport", ccmWorkReport);
		model.addAttribute("viewRep", "01");
		return "ccm/sys/ccmWorkReportForm";
	}

	@RequiresPermissions("sys:ccmWorkReport:edit")
	@RequestMapping(value = "save")
	public String save(CcmWorkReport ccmWorkReport, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmWorkReport)) {
			return form(ccmWorkReport, model);
		}
		// 如果是修改，则状态为已发布，则不能再进行操作
		if (StringUtils.isNotBlank(ccmWorkReport.getId())) {
			CcmWorkReport e = ccmWorkReportService.get(ccmWorkReport.getId());
			if ("1".equals(e.getStatus())) {
				addMessage(redirectAttributes, "已发布，不能操作！");
				return "redirect:" + adminPath + "/sys/ccmWorkReport/form?id=" + ccmWorkReport.getId();
			}
		}
		// 判断是否过期 dt1.getTime() > dt2.getTime()
		Date dt1 = new Date();
		Date dt2 = ccmWorkReport.getEndDate();
		if (dt1.getTime() > dt2.getTime()) {
			ccmWorkReport.setStatus("1");
		} else {
			ccmWorkReport.setStatus("0");
		}
		ccmWorkReportService.save(ccmWorkReport);
		User user = UserUtils.getUser();
		List<CcmWorkReportRead> list = ccmWorkReport.getCcmWorkReportReadList();
		List<CcmMessage> messagelist = new ArrayList<CcmMessage>();
		Date createDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
		for (CcmWorkReportRead ccmWorkReportRead : list) {
			Map<String, Object> resmap = new HashMap<String, Object>();
			resmap.put("type","03");
			resmap.put("name","您有一条日志消息，请注意查收！");
			resmap.put("id",ccmWorkReportRead.getUser().getId());
			RabbitMQTools.sendMessage(ccmWorkReportRead.getUser().getId(), JSONObject.fromObject(resmap).toString());
			
			CcmMessage ccmMessage = new CcmMessage();
			ccmMessage.preInsert();
			ccmMessage.setType("33");//通知

			ccmMessage.setContent(sdf.format(createDate)+ " 通知：工作日志!");
			ccmMessage.setReadFlag("0");//未读
			ccmMessage.setObjId(ccmWorkReport.getId());
			ccmMessage.setUserId(ccmWorkReportRead.getUser().getId());
			ccmMessage.setCreateBy(user);
			ccmMessage.setUpdateBy(user);
			messagelist.add(ccmMessage);
		}
		if(messagelist.size()!=0) {
			ccmMessageService.insertEventAll(messagelist);
			CcmRestEvent.sendMessageToMq(messagelist);
		}
		addMessage(redirectAttributes, "保存工作日志成功");
		return "redirect:" + Global.getAdminPath() + "/sys/ccmWorkReport/?repage";
	}

	@RequiresPermissions("sys:ccmWorkReport:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmWorkReport ccmWorkReport, RedirectAttributes redirectAttributes) {
		ccmWorkReportService.delete(ccmWorkReport);
		addMessage(redirectAttributes, "删除工作日志成功");
		return "redirect:" + Global.getAdminPath() + "/sys/ccmWorkReport/?repage";
	}

	/**
	 * 我的工作日志记录
	 */
	@RequestMapping(value = "self")
	public String selfList(CcmWorkReport ccmWorkReport, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		ccmWorkReport.setSelf(true);
		Page<CcmWorkReport> page = ccmWorkReportService.findPage(new Page<CcmWorkReport>(request, response),
				ccmWorkReport);
		model.addAttribute("page", page);
		return "ccm/sys/ccmWorkReportReadList";
	}

	/**
	 * 我的通知列表-数据
	 */
	@RequiresPermissions("oa:ccmWorkReport:view")
	@RequestMapping(value = "selfData")
	@ResponseBody
	public Page<CcmWorkReport> listData(CcmWorkReport ccmWorkReport, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ccmWorkReport.setSelf(true);
		Page<CcmWorkReport> page = ccmWorkReportService.findPage(new Page<CcmWorkReport>(request, response),
				ccmWorkReport);
		return page;
	}

	/**
	 * 查看我的通知
	 */
	@RequestMapping(value = "view")
	public String view(CcmWorkReport ccmWorkReport, Model model) {
		model.addAttribute("viewRep", "02");
		if (StringUtils.isNotBlank(ccmWorkReport.getId())) {
			CcmLogTail ccmLogTailDto = new CcmLogTail();
			ccmLogTailDto.setRelevanceId(ccmWorkReport.getId());
			ccmLogTailDto.setRelevanceTable("ccm_sys_workreport");
			List<CcmLogTail> ccmLogTailList = ccmLogTailService.findListByObject(ccmLogTailDto);
			// 返回查询结果
			model.addAttribute("ccmLogTailList", ccmLogTailList);
			ccmWorkReportService.updateReadFlag(ccmWorkReport);
			ccmWorkReport = ccmWorkReportService.getRecordList(ccmWorkReport);
			model.addAttribute("ccmWorkReport", ccmWorkReport);

			return "/ccm/sys/ccmWorkReportForm";
		}
		return "redirect:" + adminPath + "/sys/ccmWorkReport/self?repage";
	}

	/**
	 * 查看我的通知-数据
	 */
	@RequestMapping(value = "viewData")
	@ResponseBody
	public CcmWorkReport viewData(CcmWorkReport ccmWorkReport, Model model) {
		if (StringUtils.isNotBlank(ccmWorkReport.getId())) {
			ccmWorkReportService.updateReadFlag(ccmWorkReport);
			return ccmWorkReport;
		}
		return null;
	}

	/**
	 * 查看我的通知-发送记录
	 */
	@RequestMapping(value = "viewRecordData")
	@ResponseBody
	public CcmWorkReport viewRecordData(CcmWorkReport ccmWorkReport, Model model) {
		if (StringUtils.isNotBlank(ccmWorkReport.getId())) {
			ccmWorkReport = ccmWorkReportService.getRecordList(ccmWorkReport);
			return ccmWorkReport;
		}
		return null;
	}

	/**
	 * 获取我的通知数目
	 */
	@RequestMapping(value = "self/count")
	@ResponseBody
	public String selfCount(CcmWorkReport ccmWorkReport, Model model) {
		ccmWorkReport.setSelf(true);
		ccmWorkReport.setReadFlag("0");
		return String.valueOf(ccmWorkReportService.findCount(ccmWorkReport));
	}

	/**
	 * 
	 * 获取日常工作列表
	 */
	@RequiresPermissions("sys:ccmWorkReport:view")
	@RequestMapping(value = { "Job" })
	public String Joblist(CcmWorkReport ccmWorkReport, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		ccmWorkReport.setCreateBy(UserUtils.getUser());
		Page<CcmWorkReport> page = ccmWorkReportService.findJobPage(new Page<CcmWorkReport>(request, response),
				ccmWorkReport);
		model.addAttribute("page", page);
		return "ccm/sys/ccmWorkReportJobList";
	}
	
	/**
	 * 
	 * 获取日常走访列表
	 */
	@RequiresPermissions("sys:ccmWorkReport:view")
	@RequestMapping(value = { "Visit" })
	public String Visitlist(CcmWorkReport ccmWorkReport, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		ccmWorkReport.setCreateBy(UserUtils.getUser());
		Page<CcmWorkReport> page = ccmWorkReportService.findVisitPage(new Page<CcmWorkReport>(request, response),
				ccmWorkReport);
		model.addAttribute("page", page);
		return "ccm/sys/ccmWorkReportVisitList";
	}

	/**
	 * 
	 * 获取事件处理列表
	 */
	@RequiresPermissions("sys:ccmWorkReport:view")
	@RequestMapping(value = { "EventProcess" })
	public String Eventlist(CcmWorkReport ccmWorkReport, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		ccmWorkReport.setCreateBy(UserUtils.getUser());
		Page<CcmWorkReport> page = ccmWorkReportService.findEventProcessPage(new Page<CcmWorkReport>(request, response),
				ccmWorkReport);
		model.addAttribute("page", page);
		return "ccm/sys/ccmWorkReportEventProcessList";
	}
	
	/**
	 * 查看详情
	 */
	@RequiresPermissions("sys:ccmWorkReport:view")
	@RequestMapping(value = "Jobform")
	public String Jobform(CcmWorkReport ccmWorkReport, Model model) {
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(ccmWorkReport.getId());
		ccmLogTailDto.setRelevanceTable("ccm_sys_workreport");
		List<CcmLogTail> ccmLogTailList = ccmLogTailService.findListByObject(ccmLogTailDto);
		// 返回查询结果
		model.addAttribute("ccmLogTailList", ccmLogTailList);
		if (StringUtils.isNotEmpty(ccmWorkReport.getId())) {
			ccmWorkReport = ccmWorkReportService.findByID(ccmWorkReport);
		}
		model.addAttribute("ccmWorkReport", ccmWorkReport);
		model.addAttribute("viewRep", "01");
		return "ccm/sys/ccmWorkReportJobForm";
	}
	
	/**
	 * 修改前查看详情
	 */
	@RequiresPermissions("sys:ccmWorkReport:view")
	@RequestMapping(value = "SaveJobform")
	public String SaveJobform(CcmWorkReport ccmWorkReport, Model model) {
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(ccmWorkReport.getId());
		ccmLogTailDto.setRelevanceTable("ccm_sys_workreport");
		List<CcmLogTail> ccmLogTailList = ccmLogTailService.findListByObject(ccmLogTailDto);
		// 返回查询结果
		model.addAttribute("ccmLogTailList", ccmLogTailList);
		if (StringUtils.isNotEmpty(ccmWorkReport.getId())) {
			ccmWorkReport = ccmWorkReportService.findByID(ccmWorkReport);
		}
		model.addAttribute("ccmWorkReport", ccmWorkReport);
		model.addAttribute("viewRep", "02");
		return "ccm/sys/ccmWorkReportJobForm";
	}

	/**
	 * 修改日志信息
	 * @param ccmWorkReport
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws IOException 
	 */
	@RequiresPermissions("sys:ccmWorkReport:edit")
	@RequestMapping(value = "saveJob")
	public void saveJob(HttpServletRequest request, HttpServletResponse response, CcmWorkReport ccmWorkReport, Model model, RedirectAttributes redirectAttributes) throws IOException {
		if (!beanValidator(model, ccmWorkReport)) {
//			return form(ccmWorkReport, model);
		}
		
		ccmWorkReportService.updateJob(ccmWorkReport);
		addMessage(redirectAttributes, "保存工作日志成功");
		
		PrintWriter out = response.getWriter();
		CommUtil.openWinExpDiv(out, "保存工作日志成功");
		
//		if(StringUtils.isBlank(ccmWorkReport.getType()) || "01".equals(ccmWorkReport.getType())){
//			return "redirect:" + Global.getAdminPath() + "/sys/ccmWorkReport/Job";
//		}
//		if(StringUtils.isBlank(ccmWorkReport.getType()) || "02".equals(ccmWorkReport.getType())){
//			return "redirect:" + Global.getAdminPath() + "/sys/ccmWorkReport/Visit";
//		}
//		else{
//			return "redirect:" + Global.getAdminPath() + "/sys/ccmWorkReport/EventProcess";
//		}
	}
	
	/**
	 * 
	 * 获取日志统计列表
	 */
	@RequiresPermissions("sys:ccmWorkReport:view")
	@RequestMapping(value = { "Count" })
	public String Countlist(CcmWorkReport ccmWorkReport, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		ccmWorkReport.setCreateBy(UserUtils.getUser());
		Page<CcmWorkReport> page = ccmWorkReportService.findCountPage(new Page<CcmWorkReport>(request, response),ccmWorkReport);
		model.addAttribute("page", page);
		return "ccm/sys/ccmWorkReportCountList";
	}
	
	
	/**
	 * 
	 * 导出日志统计列表
	 */
	@RequiresPermissions("sys:ccmWorkReport:view")
	@RequestMapping(value = { "exportCount" })
	public void exportCount(CcmWorkReport ccmWorkReport, HttpServletRequest request, HttpServletResponse response,
			Model model) {		
		try {
			   String fileName = "日志统计"  + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			   List<CcmWorkReportYO> list = ccmWorkReportService.exportCount(ccmWorkReport);
			   new ExportExcel("日志统计", CcmWorkReportYO.class).setDataList(list).write(response, fileName).dispose();
		} catch (Exception e) {
			   System.out.println("导出日志统计数据失败！失败信息：" + e.getMessage());
		}
	}
	
	/**
	 * 
	 * 导出日常工作列表
	 */
	@RequiresPermissions("sys:ccmWorkReport:view")
	@RequestMapping(value = { "exportJob" })
	public void exportJob(CcmWorkReport ccmWorkReport, HttpServletRequest request, HttpServletResponse response,
			Model model) {		
		try {
			   ccmWorkReport.setType("01");
			   String fileName = "日常工作"  + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			   List<CcmWorkReportTO> list = ccmWorkReportService.exportJob(ccmWorkReport);
			   new ExportExcel("日常工作", CcmWorkReportTO.class).setDataList(list).write(response, fileName).dispose();
		} catch (Exception e) {
			   System.out.println("导出日常工作数据失败！失败信息：" + e.getMessage());
		}
	}
	
	/**
	 * 
	 * 导出日常走访列表
	 */
	@RequiresPermissions("sys:ccmWorkReport:view")
	@RequestMapping(value = { "exportVisit" })
	public void exportVisit(CcmWorkReport ccmWorkReport, HttpServletRequest request, HttpServletResponse response,
			Model model) {		
		try {
			   ccmWorkReport.setType("02");
			   String fileName = "日常走访"  + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			   List<CcmWorkReportVO> list = ccmWorkReportService.exportVisit(ccmWorkReport);
			   new ExportExcel("日常走访", CcmWorkReportVO.class).setDataList(list).write(response, fileName).dispose();
		} catch (Exception e) {
			   System.out.println("导出日常走访数据失败！失败信息：" + e.getMessage());
		}
	}
	
	/**
	 * 
	 * 导出事件处理列表
	 */
	@RequiresPermissions("sys:ccmWorkReport:view")
	@RequestMapping(value = { "exportEvent" })
	public void exportEvent(CcmWorkReport ccmWorkReport, HttpServletRequest request, HttpServletResponse response,
			Model model) {		
		try {
			   ccmWorkReport.setType("03");
			   String fileName = "事件处理"  + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			   List<CcmWorkReportVO> list = ccmWorkReportService.exportEvent(ccmWorkReport);
			   new ExportExcel("事件处理", CcmWorkReportVO.class).setDataList(list).write(response, fileName).dispose();
		} catch (Exception e) {
			   System.out.println("导出事件处理数据失败！失败信息：" + e.getMessage());
		}
	}

	/**
	 *
	 * 导出我的工作日志
	 */
	@RequiresPermissions("sys:ccmWorkReport:view")
	@RequestMapping(value = { "exportWorkReport" })
	public void exportWorkReport(CcmWorkReport ccmWorkReport, HttpServletRequest request, HttpServletResponse response,
							Model model) {
		try {
			ccmWorkReport.setCreateBy(UserUtils.getUser());
			String fileName = "我的工作日志"  + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<CcmWorkReportExport> list = ccmWorkReportService.exportWorkReport(ccmWorkReport);
//			List<CcmWorkReport> list = ccmWorkReportService.exportEvent(ccmWorkReport);
			new ExportExcel("我的工作日志", CcmWorkReportExport.class).setDataList(list).write(response, fileName).dispose();
		} catch (Exception e) {
			System.out.println("导出事件处理数据失败！失败信息：" + e.getMessage());
		}
	}

	/**
	 *
	 * 导出日志收件箱
	 */
	@RequiresPermissions("sys:ccmWorkReport:view")
	@RequestMapping(value = { "exportSelf" })
	public void exportSelf(CcmWorkReport ccmWorkReport, HttpServletRequest request, HttpServletResponse response,
								 Model model) {
		try {
			ccmWorkReport.setSelf(true);
			String fileName = "我的工作日志"  + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<CcmSelfExport> list = ccmWorkReportService.exportSelf(ccmWorkReport);
			new ExportExcel("我的工作日志", CcmSelfExport.class).setDataList(list).write(response, fileName).dispose();
		} catch (Exception e) {
			System.out.println("导出事件处理数据失败！失败信息：" + e.getMessage());
		}
	}
}