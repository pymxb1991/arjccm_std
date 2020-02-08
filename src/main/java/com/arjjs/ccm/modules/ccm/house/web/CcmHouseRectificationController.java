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
import com.arjjs.ccm.modules.ccm.house.entity.CcmHousePsychogeny;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseRectification;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseRectificationService;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.modules.ccm.log.service.CcmLogTailService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPeopleService;
import com.arjjs.ccm.modules.sys.entity.Dict;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.service.DictService;
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
 * 社区矫正人员Controller
 * @author arj
 * @version 2018-01-04
 */
@Controller
@RequestMapping(value = "${adminPath}/house/ccmHouseRectification")
public class CcmHouseRectificationController extends BaseController {

	@Autowired
	private CcmHouseRectificationService ccmHouseRectificationService;
	@Autowired
	private CcmPeopleService ccmPeopleService;
	@Autowired
	private CcmLogTailService ccmLogTailService;
	@Autowired
	private DictService dictService;

	@ModelAttribute
	public CcmHouseRectification get(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "peopleId", required = false) String peopleId) {
		CcmHouseRectification entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHouseRectificationService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmHouseRectificationService.getPeopleALL(peopleId);
		}
		if (entity == null) {
			entity = new CcmHouseRectification();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(@Param("tableType") String tableType, CcmHouseRectification ccmHouseRectification,
			HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<CcmHouseRectification> page = ccmHouseRectificationService.findPage(new Page<CcmHouseRectification>(request, response), ccmHouseRectification); 
//		model.addAttribute("page", page);

		Page<CcmHouseRectification> page = new Page();
		String permissionKey = request.getParameter("permissionKey");
		User user = UserUtils.getUser();
		if (user != null && "1".equals(user.getHasPermission())) {// 有涉密权限
			page = ccmHouseRectificationService.findPage(new Page<CcmHouseRectification>(request, response),
					ccmHouseRectification);
		} else if (user != null && "0".equals(user.getHasPermission())) {// 无涉密权限
			if (user.getPermissionKey() != null && user.getPermissionKey().equals(permissionKey)) {
				page = ccmHouseRectificationService.findPage(new Page<CcmHouseRectification>(request, response),
						ccmHouseRectification);
			} else {
				model.addAttribute("message", "涉密权限不正确！");
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("permissionKey", permissionKey);
		if (StringUtils.isBlank(tableType)) {
			return "ccm/house/ccmHouseRectificationList";
		} else {
			return "ccm/house/emphasis/ccmHouseRectificationList";
		}
	}

	@RequiresPermissions("house:ccmHouseRectification:view")
	@RequestMapping(value = "form")
	public String form(CcmHouseRectification ccmHouseRectification, Model model) {
		// 创建 查询对象 建立查询条件
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(ccmHouseRectification.getId());
		ccmLogTailDto.setRelevanceTable("ccm_House_Rectification");
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
		model.addAttribute("ccmHouseRectification", ccmHouseRectification);
		return "ccm/house/ccmHouseRectificationForm";
	}

	@RequiresPermissions("house:ccmHouseRectification:edit")
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request, HttpServletResponse response,
			CcmHouseRectification ccmHouseRectification, Model model, RedirectAttributes redirectAttributes)
			throws IOException {
		if (!beanValidator(model, ccmHouseRectification)) {
//			return form(ccmHouseRectification, model);
		}
		// 更新 当前人 是 社区矫正人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseRectification.getPeopleId());
		ccmHouseRectificationService.save(ccmHouseRectification,ccmPop);
		if (ccmPop != null) {
			ccmPop.setIsRectification(1);
			ccmPeopleService.save(ccmPop);
		}
		addMessage(redirectAttributes, "保存社区矫正人员成功");
//		return "redirect:"+Global.getAdminPath()+"/house/ccmHouseRectification/?repage";

		PrintWriter out = response.getWriter();
		CommUtil.openWinExpDiv(out, "保存社区矫正人员成功");
		ccmPop.setMqTitle("新增重点人员(社区矫正人员)");
	}

	@RequiresPermissions("house:ccmHouseRectification:edit")
	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest request, CcmHouseRectification ccmHouseRectification,
			RedirectAttributes redirectAttributes) {
		ccmHouseRectificationService.delete(ccmHouseRectification);
		addMessage(redirectAttributes, "删除社区矫正人员成功");
		// 更新 当前人不再 是 社区矫正人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseRectification.getPeopleId());
		String permissionKey = request.getParameter("permissionKey");
		if (ccmPop != null) {
			ccmPop.setIsRectification(0);
			ccmPeopleService.save(ccmPop);
		}

		addMessage(redirectAttributes, "保存社区矫正人员成功");
		return "redirect:" + Global.getAdminPath() + "/house/ccmHouseRectification/?repage&permissionKey="
				+ permissionKey;
	}

	@RequiresPermissions("house:ccmHouseRectification:edit")
	@RequestMapping(value = "savePop")
	public String savePop(CcmHouseRectification ccmHouseRectification, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmHouseRectification)) {
			return form(ccmHouseRectification, model);
		}
		ccmHouseRectificationService.save(ccmHouseRectification);
		// 更新 当前人 是 社区矫正人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseRectification.getPeopleId());
		if (ccmPop != null) {
			ccmPop.setIsRectification(1);
			ccmPeopleService.save(ccmPop);
		}

		addMessage(redirectAttributes, "保存社区矫正人员成功");

		return "redirect:" + Global.getAdminPath() + "/pop/ccmPeople/?repage";
	}

	@RequiresPermissions("house:ccmHouseRectification:view")
	@RequestMapping(value = "specialform")
	public String specialform(CcmPeople ccmPeople, Model model) {
		model.addAttribute("ccmPeople", ccmPeople);
		CcmHouseRectification rectification = ccmHouseRectificationService.getPeopleALL(ccmPeople.getId());
		if (rectification == null) {
			rectification = new CcmHouseRectification();
		}
		model.addAttribute("ccmHouseRectification", rectification);
		return "/ccm/house/pop/ccmHousePoPRectificationsForm";
	}

	/**
	 * 导出社区矫正人员数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("house:ccmHouseRectification:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(CcmHouseRectification ccmHouseRectification, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String [] strArr={"姓名","联系方式","人口类型","现住门（楼）详址","公民身份号码","社区矫正人员编号","矫正类別","矫正开始日期","接收方式","是否建立矫正小组","关注程度","案事件类別","矫正结束日期","矫正小组人员组成情况","是否有脱管","是否有漏管","是否重新犯罪"};
		try {
			String fileName = "RectificationPeople" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";

			List<CcmHouseRectification> list = new ArrayList<CcmHouseRectification>();
			String permissionKey = request.getParameter("permissionKey");
			User user = UserUtils.getUser();
			if (user != null && "1".equals(user.getHasPermission())) {// 有涉密权限
				list = ccmHouseRectificationService.findList(ccmHouseRectification);
			} else if (user != null && "0".equals(user.getHasPermission())) {// 无涉密权限
				if (user.getPermissionKey() != null && user.getPermissionKey().equals(permissionKey)) {
					list = ccmHouseRectificationService.findList(ccmHouseRectification);
				}
			}
			//将多选字段转为字典了label
			Dict dict = new Dict();
			dict.setType("ccm_four_case");
			List<Dict> four = dictService.findList(dict);
			dict.setType("ccm_three_case");
			List<Dict> three = dictService.findList(dict);
			dict.setType("ccm_jzxz_ryzc");
			List<Dict> jzxz = dictService.findList(dict);
			for (CcmHouseRectification rectification : list) {
				String[] fourarr = rectification.getFourHis().split(",");
				StringBuilder str1 = new StringBuilder();
				for(int i=0;i<four.size();i++) {
					for(int j=0;j<fourarr.length;j++) {
						if(four.get(i).getValue().equals(fourarr[j])) {
							str1.append(","+four.get(i).getLabel());
						}
					}
				}
				str1.append(",");
				rectification.setFourHis(str1.toString());
				
				String[] thrarr = rectification.getThrHold().split(",");
				StringBuilder str2 = new StringBuilder();
				for(int i=0;i<three.size();i++) {
					for(int j=0;j<thrarr.length;j++) {
						if(three.get(i).getValue().equals(thrarr[j])) {
							str2.append(","+three.get(i).getLabel());
						}
					}
				}
				str2.append(",");
				rectification.setThrHold(str2.toString());
				
				String[] jzxzarr = rectification.getCorrected().split(",");
				StringBuilder str3 = new StringBuilder();
				for(int i=0;i<jzxz.size();i++) {
					for(int j=0;j<jzxzarr.length;j++) {
						if(jzxz.get(i).getValue().equals(jzxzarr[j])) {
							str3.append(","+jzxz.get(i).getLabel());
						}
					}
				}
				str3.append(",");
				rectification.setCorrected(str3.toString());
 			}
//			List<CcmHouseRectification> list = ccmHouseRectificationService.findList(ccmHouseRectification);
			new ExportExcel("社区矫正人员数据", CcmHouseRectification.class,strArr).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出社区矫正人员失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/house/ccmHouseRectification/?repage";
	}

	/**
	 * 导入社区矫正人员数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("house:ccmHouseRectification:view")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/house/ccmHouseRectification/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<CcmHouseRectification> list = ei.getDataList(CcmHouseRectification.class);
			//将多选字段转为字典了label
			Dict dict = new Dict();
			dict.setType("ccm_four_case");
			List<Dict> four = dictService.findList(dict);
			dict.setType("ccm_three_case");
			List<Dict> three = dictService.findList(dict);
			dict.setType("ccm_jzxz_ryzc");
			List<Dict> jzxz = dictService.findList(dict);
			for (CcmHouseRectification HouseRectification : list) {
				try {


					if(EntityTools.isEmpty(HouseRectification)){
						continue;
					}
					if(StringUtils.isBlank(HouseRectification.getRectNum()) ||
							StringUtils.isBlank(HouseRectification.getRectType()) ||
							HouseRectification.getRectBegin()==null ||
							StringUtils.isBlank(HouseRectification.getReceiveMode()) ||
							HouseRectification.getCorrecthas()==null ||
							StringUtils.isBlank(HouseRectification.getAtteType()) ||
							StringUtils.isBlank(HouseRectification.getCaseType()) ||
							HouseRectification.getRectEnd()==null ||
							StringUtils.isBlank(HouseRectification.getCorrected()) ||
							HouseRectification.getDetached()==null ||
							HouseRectification.getLackContr()==null ||
							HouseRectification.getReoffend()==null
					){
						StringBuilder str = new StringBuilder();
						str.append("(");
						if(StringUtils.isBlank(HouseRectification.getRectNum())) {
							str.append("社区矫正人员编号信息错误;");
						}
						if(StringUtils.isBlank(HouseRectification.getRectType())) {
							str.append("矫正类別信息错误;");
						}
						if(HouseRectification.getRectBegin()==null) {
							str.append("矫正开始日期信息错误;");
						}
						if(StringUtils.isBlank(HouseRectification.getReceiveMode())) {
							str.append("接收方式信息错误;");
						}
						if(HouseRectification.getCorrecthas()==null) {
							str.append("是否建立矫正小组信息错误;");
						}
						if(StringUtils.isBlank(HouseRectification.getAtteType())) {
							str.append("关注程度信息错误;");
						}
						if(StringUtils.isBlank(HouseRectification.getCaseType())) {
							str.append("案事件类別信息错误;");
						}
						if(HouseRectification.getRectEnd()==null) {
							str.append("矫正结束日期信息错误;");
						}
						if(StringUtils.isBlank(HouseRectification.getCorrected())) {
							str.append("矫正小组人员组成情况信息错误;");
						}
						if(HouseRectification.getDetached()==null) {
							str.append("是否有脱管信息错误;");
						}
						if(HouseRectification.getLackContr()==null) {
							str.append("是否有漏管信息错误;");
						}
						if(HouseRectification.getReoffend()==null) {
							str.append("是否重新犯罪信息错误;");
						}
						str.append(")");
						failureMsg.append("<br/>社区矫正人员名 " + HouseRectification.getName() + " 导入失败：必填项为空。"+str.toString());
						continue;
					}
					
					if(StringUtils.isNotEmpty(HouseRectification.getFourHis())) {
						String[] fourarr = HouseRectification.getFourHis().split(",");
						StringBuilder str1 = new StringBuilder();
						for(int i=0;i<four.size();i++) {
							for(int j=0;j<fourarr.length;j++) {
								if(four.get(i).getLabel().equals(fourarr[j])) {
									str1.append(","+four.get(i).getValue());
								}
							}
						}
						str1.append(",");
						HouseRectification.setFourHis(str1.toString());
					}
					if(StringUtils.isNotEmpty(HouseRectification.getThrHold())) {
						String[] thrarr = HouseRectification.getThrHold().split(",");
						StringBuilder str2 = new StringBuilder();
						for(int i=0;i<three.size();i++) {
							for(int j=0;j<thrarr.length;j++) {
								if(three.get(i).getLabel().equals(thrarr[j])) {
									str2.append(","+three.get(i).getValue());
								}
							}
						}
						str2.append(",");
						HouseRectification.setThrHold(str2.toString());
					}
					if(StringUtils.isNotEmpty(HouseRectification.getCorrected())) {
						String[] jzxzarr = HouseRectification.getCorrected().split(",");
						StringBuilder str3 = new StringBuilder();
						for(int i=0;i<jzxz.size();i++) {
							for(int j=0;j<jzxzarr.length;j++) {
								if(jzxz.get(i).getLabel().equals(jzxzarr[j])) {
									str3.append(","+jzxz.get(i).getValue());
								}
							}
						}
						str3.append(",");
						HouseRectification.setCorrected(str3.toString());
					}

					// 如果当前用户的身份未填写或者为空或者身份证号码位数不够18位则应该进行 剔除
					if (StringUtils.isBlank(HouseRectification.getIdent()) || HouseRectification.getIdent().length() != 18) {
						failureMsg.append("<br/>实有人口名" + HouseRectification.getName() + " 导入失败：" + "身份证信息错误。");
						HouseRectification.setName(HouseRectification.getName() + "，失败原因：身份证信息错误");
						failureNum++;
						continue;
					}
					
					CcmPeople ccmPeople = new CcmPeople();
					ccmPeople.setIdent(HouseRectification.getIdent());
					List<CcmPeople> list1 = ccmPeopleService.findList(ccmPeople);
					CcmHouseRectification HouseRectificationFind;

					if (list1.isEmpty()) {
						failureMsg.append("<br/>社区矫正人员名 " + HouseRectification.getName() + " 导入失败：实有人口表中无此人");
						continue;
					} else {
						ccmPeople.setId(list1.get(0).getId());
						ccmPeople.setUpdateBy(UserUtils.getUser());
						ccmPeople.setUpdateDate(new Date());
						ccmPeople.setIsRectification(1);
						ccmPeopleService.updatePeople(ccmPeople);
						HouseRectificationFind = ccmHouseRectificationService.getPeopleALL(list1.get(0).getId());
						if (HouseRectificationFind == null) {
							HouseRectification.setPeopleId(list1.get(0).getId());
							ccmHouseRectificationService.save(HouseRectification);
							successNum++;
						} else {
							failureMsg.append("<br/>社区矫正人员名 " + HouseRectification.getName() + " 导入失败：记录已存在");
						}
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>社区矫正人员名 " + HouseRectification.getName() + " 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>登录名 " + HouseRectification.getName() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条社区矫正人员，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条社区矫正人员" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入社区矫正人员失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/house/ccmHouseRectification/?repage";
	}

}