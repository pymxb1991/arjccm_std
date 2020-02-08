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
import com.arjjs.ccm.modules.ccm.event.service.CcmEventCasedealService;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventIncidentService;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseDispute;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseDisputeService;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.modules.ccm.log.service.CcmLogTailService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPeopleService;
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
 * 矛盾纠纷Controller
 * 
 * @author liu
 * @version 2018-09-27
 */
@Controller
@RequestMapping(value = "${adminPath}/house/ccmHouseDispute")
public class CcmHouseDisputeController extends BaseController {

	@Autowired
	private CcmHouseDisputeService ccmHouseDisputeService;
	@Autowired
	private CcmPeopleService ccmPeopleService;
	@Autowired
	private CcmLogTailService ccmLogTailService;
	@Autowired
	private CcmEventIncidentService ccmEventIncidentService;
	@Autowired
	private CcmEventCasedealService ccmEventCasedealService;

	@ModelAttribute
	public CcmHouseDispute get(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "peopleId", required = false) String peopleId) {
		CcmHouseDispute entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHouseDisputeService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmHouseDisputeService.getPeopleALL(peopleId);
		}
		if (entity == null) {
			entity = new CcmHouseDispute();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}

		return entity;
	}

	// 人员标记处
	@RequiresPermissions("house:ccmHouseDispute:view")
	@RequestMapping(value = "specialform")
	public String specialform(CcmPeople ccmPeople, Model model) {
		model.addAttribute("ccmPeople", ccmPeople);
		CcmHouseDispute ccmHouseDispute = ccmHouseDisputeService.getPeopleALL(ccmPeople.getId());
		if (ccmHouseDispute == null) {
			ccmHouseDispute = new CcmHouseDispute();
		}
		model.addAttribute("ccmHouseDangerous", ccmHouseDispute);
		return "/ccm/house/pop/ccmHouseDisputeForm";
	}

	@RequiresPermissions("house:ccmHouseDispute:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmHouseDispute ccmHouseDispute, HttpServletRequest request, HttpServletResponse response,
			Model model, @Param("tableType") String tableType) {
		Page<CcmHouseDispute> page = new Page();
		String permissionKey = request.getParameter("permissionKey");
		User user = UserUtils.getUser();
		if (user != null && "1".equals(user.getHasPermission())) {// 有涉密权限
			page = ccmHouseDisputeService.findPage(new Page<CcmHouseDispute>(request, response), ccmHouseDispute);
		} else if (user != null && "0".equals(user.getHasPermission())) {// 无涉密权限
			if (user.getPermissionKey() != null && user.getPermissionKey().equals(permissionKey)) {
				page = ccmHouseDisputeService.findPage(new Page<CcmHouseDispute>(request, response), ccmHouseDispute);
			} else {
				model.addAttribute("message", "涉密权限不正确！");
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("permissionKey", permissionKey);
		if (StringUtils.isBlank(tableType)) {
			return "ccm/house/ccmHouseDisputeList";
		} else {
			return "ccm/house/emphasis/ccmHouseDisputeList";
		}
	}

	@RequiresPermissions("house:ccmHouseDispute:view")
	@RequestMapping(value = "form")
	public String form(CcmHouseDispute ccmHouseDispute, Model model) {
		// 创建 查询对象 建立查询条件
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(ccmHouseDispute.getId());
		ccmLogTailDto.setRelevanceTable("ccm_house_dispute");
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
		model.addAttribute("ccmHouseDispute", ccmHouseDispute);
		return "ccm/house/ccmHouseDisputeForm";
	}

	@RequiresPermissions("house:ccmHouseDispute:edit")
	@RequestMapping(value = "savePop")
	public String savePop(CcmHouseDispute ccmHouseDispute, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmHouseDispute)) {
			return form(ccmHouseDispute, model);
		}
		ccmHouseDisputeService.save(ccmHouseDispute);
		// 更新 当前人 是矛盾纠纷人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseDispute.getPeopleId());
		if (ccmPop != null) {
			ccmPop.setIsDispute(1);
			ccmPeopleService.save(ccmPop);
		}

		addMessage(redirectAttributes, "保存矛盾纠纷人口成功");

		return "redirect:" + Global.getAdminPath() + "/pop/ccmPeople/?repage";
	}

	@RequiresPermissions("house:ccmHouseDispute:edit")
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request, HttpServletResponse response, CcmHouseDispute ccmHouseDispute,
			Model model, RedirectAttributes redirectAttributes) throws IOException {
		if (!beanValidator(model, ccmHouseDispute)) {
//			return form(ccmHouseDispute, model);
		}
		// 更新 当前人 是 矛盾纠纷人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseDispute.getPeopleId());
		ccmHouseDisputeService.save(ccmHouseDispute,ccmPop);
		if (ccmPop != null) {
			ccmPop.setIsDispute(1);
			ccmPeopleService.save(ccmPop);
		}

		addMessage(redirectAttributes, "保存矛盾纠纷人员成功");

//		return "redirect:" + Global.getAdminPath() + "/house/ccmHouseDispute/?repage";

		PrintWriter out = response.getWriter();
		CommUtil.openWinExpDiv(out, "保存矛盾纠纷人员成功");
		ccmPop.setMqTitle("新增重点人员(矛盾纠纷人员)");
	}

	@RequiresPermissions("house:ccmHouseDispute:edit")
	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest request, CcmHouseDispute ccmHouseDispute,
			RedirectAttributes redirectAttributes) {
		ccmHouseDisputeService.delete(ccmHouseDispute);
		addMessage(redirectAttributes, "删除矛盾纠纷人员成功");
		String permissionKey = request.getParameter("permissionKey");
		// 更新 当前人 不再是 矛盾纠纷人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseDispute.getPeopleId());
		if (ccmPop != null) {
			ccmPop.setIsDispute(0);
			ccmPeopleService.save(ccmPop);
		}

		return "redirect:" + Global.getAdminPath() + "/house/ccmHouseDispute/?repage&permissionKey=" + permissionKey;
	}

	/**
	 * 导出矛盾纠纷人员数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("house:ccmHouseDispute:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(CcmHouseDispute ccmHouseDispute, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		String [] strArr={"姓名","联系方式","人口类型","现住门（楼）详址","公民身份号码","发现途径","关注程度"};
		try {
			String fileName = "DisputePeople" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";

			List<CcmHouseDispute> list = new ArrayList<CcmHouseDispute>();
			String permissionKey = request.getParameter("permissionKey");
			User user = UserUtils.getUser();
			if (user != null && "1".equals(user.getHasPermission())) {// 有涉密权限
				list = ccmHouseDisputeService.findList(ccmHouseDispute);
			} else if (user != null && "0".equals(user.getHasPermission())) {// 无涉密权限
				if (user.getPermissionKey() != null && user.getPermissionKey().equals(permissionKey)) {
					list = ccmHouseDisputeService.findList(ccmHouseDispute);
				}
			}
//			List<ccmHouseDispute> list = ccmHouseDisputeService.findList(ccmHouseDispute);
			new ExportExcel("闹事行凶报复嫌疑", CcmHouseDispute.class,strArr).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "闹事行凶报复嫌疑人员失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/house/ccmHouseDispute/?repage";
	}

	/**
	 * 导入吸毒人员数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("house:ccmHouseDispute:view")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/house/ccmHouseDispute/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<CcmHouseDispute> list = ei.getDataList(CcmHouseDispute.class);
			for (CcmHouseDispute HouseDispute : list) {
				try {

					if(EntityTools.isEmpty(HouseDispute)){
						continue;
					}

					if(StringUtils.isBlank(HouseDispute.getDiscoveryWay()) || StringUtils.isBlank(HouseDispute.getDangerLevel()) ){
						StringBuilder str = new StringBuilder();
						str.append("(");
						if(StringUtils.isBlank(HouseDispute.getDiscoveryWay())) {
							str.append("发现途径信息错误;");
						}
						if(StringUtils.isBlank(HouseDispute.getDangerLevel())) {
							str.append("关注程度信息错误;");
						}
						str.append(")");
						failureMsg.append("<br/>闹事行凶报复嫌疑人员名 " + HouseDispute.getName() + " 导入失败：必填项为空。"+str.toString());
						continue;
					}

					// 如果当前用户的身份未填写或者为空或者身份证号码位数不够18位则应该进行 剔除
					if (StringUtils.isBlank(HouseDispute.getIdent()) || HouseDispute.getIdent().length() != 18) {
						failureMsg.append("<br/>实有人口名" + HouseDispute.getName() + " 导入失败：" + "身份证信息错误。");
						HouseDispute.setName(HouseDispute.getName() + "，失败原因：身份证信息错误");
						failureNum++;
						continue;
					}
					
					CcmPeople ccmPeople = new CcmPeople();
					ccmPeople.setIdent(HouseDispute.getIdent());
					List<CcmPeople> list1 = ccmPeopleService.findList(ccmPeople);
					CcmHouseDispute HouseDrugsFind;

					if (list1.isEmpty()) {
						failureMsg.append("<br/>闹事行凶报复嫌疑人员名 " + HouseDispute.getName() + " 导入失败：实有人口表中无此人");
						continue;
					} else {
						ccmPeople.setId(list1.get(0).getId());
						ccmPeople.setUpdateBy(UserUtils.getUser());
						ccmPeople.setUpdateDate(new Date());
						ccmPeople.setIsDispute(1);
						ccmPeopleService.updatePeople(ccmPeople);
						HouseDrugsFind = ccmHouseDisputeService.getPeopleALL(list1.get(0).getId());
						BeanValidators.validateWithException(validator, HouseDispute);
						if (HouseDrugsFind == null) {
							HouseDispute.setPeopleId(list1.get(0).getId());
							ccmHouseDisputeService.save(HouseDispute);
							successNum++;
						} else {
							failureMsg.append("<br/>闹事行凶报复嫌疑人员名 " + HouseDispute.getName() + " 导入失败：记录已存在");
						}
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>闹事行凶报复嫌疑员名 " + HouseDispute.getName() + " 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>登录名 " + HouseDispute.getName() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条闹事行凶报复嫌疑人员，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条闹事行凶报复嫌疑人员" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入闹事行凶报复嫌疑人员失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/house/ccmHouseDispute/?repage";
	}

}