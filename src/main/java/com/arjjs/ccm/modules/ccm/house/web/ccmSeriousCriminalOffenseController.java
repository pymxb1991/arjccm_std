/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.web;

import com.arjjs.ccm.common.beanvalidator.BeanValidators;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.utils.excel.ImportExcel;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventCasedeal;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventIncident;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventCasedealService;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventIncidentService;
import com.arjjs.ccm.modules.ccm.house.entity.CcmSeriousCriminalOffense;
import com.arjjs.ccm.modules.ccm.house.service.CcmSeriousCriminalOffenseService;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.modules.ccm.log.service.CcmLogTailService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPeopleService;
import com.arjjs.ccm.modules.ccm.sys.entity.SysConfig;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.CommUtil;
import com.arjjs.ccm.tool.EntityTools;
import com.arjjs.ccm.tool.ExportExcel;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 严重刑事犯罪嫌疑人员Controller
 * 
 * @author liuxue
 * @version 2018-09-26
 */
@Controller
@RequestMapping(value = "${adminPath}/house/ccmSeriousCriminalOffense")
public class ccmSeriousCriminalOffenseController extends BaseController {

	@Autowired
	private CcmSeriousCriminalOffenseService ccmSeriousCriminalOffenseService;
	@Autowired
	private CcmPeopleService ccmPeopleService;
	@Autowired
	private CcmLogTailService ccmLogTailService;
	@Autowired
	private CcmEventIncidentService ccmEventIncidentService;
	@Autowired
	private CcmEventCasedealService ccmEventCasedealService;

	@ModelAttribute
	public CcmSeriousCriminalOffense get(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "peopleId", required = false) String peopleId) {
		CcmSeriousCriminalOffense entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmSeriousCriminalOffenseService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmSeriousCriminalOffenseService.getPeopleALL(peopleId);
		}
		if (entity == null) {
			entity = new CcmSeriousCriminalOffense();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}

		return entity;
	}

	@RequiresPermissions("house:ccmSeriousCriminalOffense:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmSeriousCriminalOffense ccmSeriousCriminalOffense, HttpServletRequest request,
			HttpServletResponse response, Model model, @Param("tableType") String tableType) {
		Page<CcmSeriousCriminalOffense> page = new Page<CcmSeriousCriminalOffense>();
		String permissionKey = request.getParameter("permissionKey");
		User user = UserUtils.getUser();
		if (user != null && "1".equals(user.getHasPermission())) {// 有涉密权限
			page = ccmSeriousCriminalOffenseService.findPage(new Page<CcmSeriousCriminalOffense>(request, response),
					ccmSeriousCriminalOffense);
		} else if (user != null && "0".equals(user.getHasPermission())) {// 无涉密权限
			if (user.getPermissionKey() != null && user.getPermissionKey().equals(permissionKey)) {
				page = ccmSeriousCriminalOffenseService.findPage(new Page<CcmSeriousCriminalOffense>(request, response),
						ccmSeriousCriminalOffense);
			} else {
				model.addAttribute("message", "涉密权限不正确！");
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("permissionKey", permissionKey);
		if (StringUtils.isBlank(tableType)) {
			return "ccm/house/ccmSeriousCriminalOffenseList";
		} else {
			return "ccm/house/emphasis/ccmSeriousCriminalOffenseList";
		}
	}

	@RequiresPermissions("house:ccmSeriousCriminalOffense:view")
	@RequestMapping(value = "form")
	public String form(CcmSeriousCriminalOffense ccmSeriousCriminalOffense, Model model) {
		// 创建 查询对象 建立查询条件
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(ccmSeriousCriminalOffense.getId());
		ccmLogTailDto.setRelevanceTable("ccm_serious_criminal_offense");
		List<CcmLogTail> ccmLogTailList = ccmLogTailService.findListByObject(ccmLogTailDto);
		// 返回查询结果
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "createBy", "updateBy", "currentUser", "dbName", "global", "page",
				"createDate", "updateDate", "sqlMap" });
		config.setIgnoreDefaultExcludes(false); // 设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String jsonDocumentList = JSONArray.fromObject(ccmLogTailList, config).toString();
		model.addAttribute("documentList", jsonDocumentList);
		model.addAttribute("documentNumber", ccmLogTailList.size());

		model.addAttribute("ccmLogTailList", ccmLogTailList);
		model.addAttribute("ccmSeriousCriminalOffense", ccmSeriousCriminalOffense);
		return "ccm/house/ccmSeriousCriminalOffenseForm";
	}

	@RequiresPermissions("house:ccmSeriousCriminalOffense:edit")
	@RequestMapping(value = "savePop")
	public String savePop(CcmSeriousCriminalOffense ccmSeriousCriminalOffense, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmSeriousCriminalOffense)) {
			return form(ccmSeriousCriminalOffense, model);
		}
		ccmSeriousCriminalOffenseService.save(ccmSeriousCriminalOffense);
		// 更新 当前人 是 严重刑事犯罪嫌疑人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmSeriousCriminalOffense.getPeopleId());
		if (ccmPop != null) {
			ccmPop.setIsCriminalOffense(1);
			ccmPeopleService.save(ccmPop);
		}

		addMessage(redirectAttributes, "保存吸毒人口成功");

		return "redirect:" + Global.getAdminPath() + "/pop/ccmPeople/?repage";
	}

	@RequiresPermissions("house:ccmSeriousCriminalOffense:edit")
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request, HttpServletResponse response,
			CcmSeriousCriminalOffense ccmSeriousCriminalOffense, Model model, RedirectAttributes redirectAttributes)
			throws IOException {
		if (!beanValidator(model, ccmSeriousCriminalOffense)) {
//			return form(ccmSeriousCriminalOffense, model);
		}
		// 更新 当前人 是 严重刑事犯罪嫌疑人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmSeriousCriminalOffense.getPeopleId());
		ccmSeriousCriminalOffenseService.save(ccmSeriousCriminalOffense,ccmPop);
		if (ccmPop != null) {
			ccmPop.setIsCriminalOffense(1);
			ccmPeopleService.save(ccmPop);
		}

		addMessage(redirectAttributes, "保存严重刑事犯罪嫌疑人员成功");

//		return "redirect:" + Global.getAdminPath() + "/house/ccmSeriousCriminalOffense/?repage";

		PrintWriter out = response.getWriter();
		CommUtil.openWinExpDiv(out, "保存严重刑事犯罪嫌疑人员成功");
		ccmPop.setMqTitle("新增重点人员(严重刑事犯罪嫌疑人员)");
	}

	@RequiresPermissions("house:ccmSeriousCriminalOffense:edit")
	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest request, CcmSeriousCriminalOffense ccmSeriousCriminalOffense,
			RedirectAttributes redirectAttributes) {
		ccmSeriousCriminalOffenseService.delete(ccmSeriousCriminalOffense);
		addMessage(redirectAttributes, "删除严重刑事犯罪嫌疑人员成功");
		String permissionKey = request.getParameter("permissionKey");
		// 更新 当前人 不再是 严重刑事犯罪嫌疑人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmSeriousCriminalOffense.getPeopleId());
		if (ccmPop != null) {
			ccmPop.setIsCriminalOffense(0);
			ccmPeopleService.save(ccmPop);
		}

		return "redirect:" + Global.getAdminPath() + "/house/ccmSeriousCriminalOffense/?repage&permissionKey="
				+ permissionKey;
	}

	/* @RequiresPermissions("house:ccmSeriousCriminalOffense:view") */
	@RequestMapping(value = "specialform")
	public String specialform(CcmPeople ccmPeople, Model model) {
		model.addAttribute("ccmPeople", ccmPeople);
		CcmSeriousCriminalOffense DeliberatelyIllegal = ccmSeriousCriminalOffenseService
				.getPeopleALL(ccmPeople.getId());
		if (DeliberatelyIllegal == null) {
			DeliberatelyIllegal = new CcmSeriousCriminalOffense();
		}
		model.addAttribute("ccmSeriousCriminalOffense", DeliberatelyIllegal);
		return "/ccm/house/pop/ccmHousePoPSeriousCriminalOffenseForm";
	}

	/**
	 * 导出严重刑事犯罪嫌疑人员数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("house:ccmSeriousCriminalOffense:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(CcmSeriousCriminalOffense ccmSeriousCriminalOffense, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String [] strArr={"姓名","联系方式","人口类型","现住门（楼）详址","公民身份号码","刑事类型","监管状态","危险程度","参与嫌疑活动描述","发现途径","有无犯罪史"};
		try {
			String fileName = "SerioussCriminalPeople" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";

			List<CcmSeriousCriminalOffense> list = new ArrayList<CcmSeriousCriminalOffense>();
			String permissionKey = request.getParameter("permissionKey");
			User user = UserUtils.getUser();
			if (user != null && "1".equals(user.getHasPermission())) {// 有涉密权限
				list = ccmSeriousCriminalOffenseService.findList(ccmSeriousCriminalOffense);
			} else if (user != null && "0".equals(user.getHasPermission())) {// 无涉密权限
				if (user.getPermissionKey() != null && user.getPermissionKey().equals(permissionKey)) {
					list = ccmSeriousCriminalOffenseService.findList(ccmSeriousCriminalOffense);
				}
			}

//			List<ccmSeriousCriminalOffense> list = ccmSeriousCriminalOffenseService.findList(ccmSeriousCriminalOffense);
			new ExportExcel("严重刑事犯罪嫌疑人员数据", CcmSeriousCriminalOffense.class,strArr).setDataList(list).write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出严重刑事犯罪嫌疑人员失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/house/ccmSeriousCriminalOffense/?repage";
	}

	/**
	 * 导入严重刑事犯罪嫌疑人员数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("house:ccmSeriousCriminalOffense:view")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/house/ccmSeriousCriminalOffense/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<CcmSeriousCriminalOffense> list = ei.getDataList(CcmSeriousCriminalOffense.class);
			for (CcmSeriousCriminalOffense SeriousCriminalOffense : list) {
				try {

					if(EntityTools.isEmpty(SeriousCriminalOffense)){
						continue;
					}

					if(StringUtils.isBlank(SeriousCriminalOffense.getSuspicionType()) ||
							StringUtils.isBlank(SeriousCriminalOffense.getSuperviseStatus()) ||
							StringUtils.isBlank(SeriousCriminalOffense.getDangerLevel()) ||
							StringUtils.isBlank(SeriousCriminalOffense.getActivityDescription()) ||
							StringUtils.isBlank(SeriousCriminalOffense.getDiscoveryWay()) ||
							StringUtils.isBlank(SeriousCriminalOffense.getIsCrimeHistory())
					){
						StringBuilder str = new StringBuilder();
						str.append("(");
						if(StringUtils.isBlank(SeriousCriminalOffense.getSuspicionType())) {
							str.append("刑事类型信息错误;");
						}
						if(StringUtils.isBlank(SeriousCriminalOffense.getSuperviseStatus())) {
							str.append("监管状态信息错误;");
						}
						if(StringUtils.isBlank(SeriousCriminalOffense.getDangerLevel())) {
							str.append("危险程度信息错误;");
						}
						if(StringUtils.isBlank(SeriousCriminalOffense.getActivityDescription())) {
							str.append("参与嫌疑活动描述信息错误;");
						}
						if(StringUtils.isBlank(SeriousCriminalOffense.getDiscoveryWay())) {
							str.append("发现途径信息错误;");
						}
						if(StringUtils.isBlank(SeriousCriminalOffense.getIsCrimeHistory())) {
							str.append("有无犯罪史信息错误;");
						}
						str.append(")");
						failureMsg.append("<br/>严重刑事犯罪嫌疑人员名 " + SeriousCriminalOffense.getName() + " 导入失败：必填项为空。"+str.toString());
						continue;
					}

					// 如果当前用户的身份未填写或者为空或者身份证号码位数不够18位则应该进行 剔除
					if (StringUtils.isBlank(SeriousCriminalOffense.getIdent()) || SeriousCriminalOffense.getIdent().length() != 18) {
						failureMsg.append("<br/>实有人口名" + SeriousCriminalOffense.getName() + " 导入失败：" + "身份证信息错误。");
						SeriousCriminalOffense.setName(SeriousCriminalOffense.getName() + "，失败原因：身份证信息错误");
						failureNum++;
						continue;
					}
					
					CcmPeople ccmPeople = new CcmPeople();
					ccmPeople.setIdent(SeriousCriminalOffense.getIdent());
					List<CcmPeople> list1 = ccmPeopleService.findList(ccmPeople);
					CcmSeriousCriminalOffense SeriousCriminalOffenseFind;

					//判断CcmPeople是否为空 空则加载错误 否 查询是否存在当前表数据
					if (list1.isEmpty()) {
						failureMsg.append("<br/>严重刑事犯罪嫌疑人员名 " + SeriousCriminalOffense.getName() + " 导入失败：实有人口表中无此人");
						continue;
					} else {
						ccmPeople.setId(list1.get(0).getId());
						ccmPeople.setUpdateBy(UserUtils.getUser());
						ccmPeople.setUpdateDate(new Date());
						ccmPeople.setIsCriminalOffense(1);
						ccmPeopleService.updatePeople(ccmPeople);
						SeriousCriminalOffenseFind = ccmSeriousCriminalOffenseService
								.getPeopleALL(list1.get(0).getId());
						BeanValidators.validateWithException(validator, SeriousCriminalOffense);
						if (SeriousCriminalOffenseFind == null) {
							SeriousCriminalOffense.setPeopleId(list1.get(0).getId());
							ccmSeriousCriminalOffenseService.save(SeriousCriminalOffense);
							successNum++;
						} else {
							failureMsg.append("<br/>严重刑事犯罪嫌疑人员名 " + SeriousCriminalOffense.getName() + " 导入失败：记录已存在");
						}

					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>严重刑事犯罪嫌疑人员名 " + SeriousCriminalOffense.getName() + " 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>登录名 " + SeriousCriminalOffense.getName() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条严重刑事犯罪嫌疑人员，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条严重刑事犯罪嫌疑人员" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入严重刑事犯罪嫌疑人员失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/house/ccmSeriousCriminalOffense/?repage";
	}
}