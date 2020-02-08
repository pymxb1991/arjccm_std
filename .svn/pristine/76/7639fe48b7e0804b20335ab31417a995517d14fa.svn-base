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
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseDeliberatelyIllegal;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseDeliberatelyIllegalService;
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
 *  故意犯法Controller
 * @author liuxue
 * @version 2018-09-27
 */
@Controller
@RequestMapping(value = "${adminPath}/house/ccmHouseDeliberatelyIllegal")
public class CcmHouseDeliberatelyIllegalController extends BaseController {

	@Autowired
	private CcmHouseDeliberatelyIllegalService ccmHouseDeliberatelyIllegalService;
	
	@Autowired
	private CcmPeopleService ccmPeopleService;
	@Autowired
	private CcmLogTailService ccmLogTailService;
	@Autowired
	private CcmEventIncidentService ccmEventIncidentService;
	@Autowired
	private CcmEventCasedealService ccmEventCasedealService;
	
	@ModelAttribute
	public CcmHouseDeliberatelyIllegal get(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "peopleId", required = false) String peopleId) {
		CcmHouseDeliberatelyIllegal entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHouseDeliberatelyIllegalService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmHouseDeliberatelyIllegalService.getPeopleALL(peopleId);
		}
		if (entity == null) {
			entity = new CcmHouseDeliberatelyIllegal();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}

		return entity;
	}

	@RequiresPermissions("house:ccmHouseDeliberatelyIllegal:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmHouseDeliberatelyIllegal ccmHouseDeliberatelyIllegal, HttpServletRequest request, HttpServletResponse response,
			Model model,@Param("tableType")String tableType) {
		Page<CcmHouseDeliberatelyIllegal> page = new Page();
		String permissionKey = request.getParameter("permissionKey");
		User user = UserUtils.getUser();
		if (user != null && "1".equals(user.getHasPermission())) {//有涉密权限
			page = ccmHouseDeliberatelyIllegalService.findPage(new Page<CcmHouseDeliberatelyIllegal>(request, response), ccmHouseDeliberatelyIllegal);
		} else if (user != null && "0".equals(user.getHasPermission())) {//无涉密权限
			if (user.getPermissionKey() != null && user.getPermissionKey().equals(permissionKey)) {
				page = ccmHouseDeliberatelyIllegalService.findPage(new Page<CcmHouseDeliberatelyIllegal>(request, response), ccmHouseDeliberatelyIllegal);
			} else {
				model.addAttribute("message", "涉密权限不正确！");
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("permissionKey", permissionKey);
		if(StringUtils.isBlank(tableType)){
			return "ccm/house/ccmHouseDeliberatelyIllegalList";
		}else {
			return "ccm/house/emphasis/ccmHouseDeliberatelyIllegalList";
		}
	}

	@RequiresPermissions("house:ccmHouseDeliberatelyIllegal:view")
	@RequestMapping(value = "form")
	public String form(CcmHouseDeliberatelyIllegal ccmHouseDeliberatelyIllegal, Model model) {
		// 创建 查询对象 建立查询条件
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(ccmHouseDeliberatelyIllegal.getId());
		ccmLogTailDto.setRelevanceTable("ccm_house_DeliberatelyIllegal");
		List<CcmLogTail > ccmLogTailList = ccmLogTailService.findListByObject(ccmLogTailDto);
		// 返回查询结果
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"createBy","updateBy","currentUser","dbName","global","page","createDate","updateDate","sqlMap"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String jsonDocumentList = JSONArray.fromObject(ccmLogTailList,config).toString(); 
		model.addAttribute("documentList", jsonDocumentList);
		model.addAttribute("documentNumber", ccmLogTailList.size());
		
		model.addAttribute("ccmLogTailList", ccmLogTailList);
		model.addAttribute("ccmHouseDeliberatelyIllegal", ccmHouseDeliberatelyIllegal);
		return "ccm/house/ccmHouseDeliberatelyIllegalForm";
	}

	@RequiresPermissions("house:ccmHouseDeliberatelyIllegal:edit")
	@RequestMapping(value = "savePop")
	public String savePop(CcmHouseDeliberatelyIllegal ccmHouseDeliberatelyIllegal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmHouseDeliberatelyIllegal)) {
			return form(ccmHouseDeliberatelyIllegal, model);
		}
		ccmHouseDeliberatelyIllegalService.save(ccmHouseDeliberatelyIllegal);
		// 更新 当前人 是 故意犯法释放人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseDeliberatelyIllegal.getPeopleId());
		if(ccmPop!=null){
			ccmPop.setIsDeliberatelyIllegal(1);
			ccmPeopleService.save(ccmPop);
		}
		
		addMessage(redirectAttributes, "保存故意犯法释放人口成功");

		return "redirect:" + Global.getAdminPath() + "/pop/ccmPeople/?repage";
	}

	@RequiresPermissions("house:ccmHouseDeliberatelyIllegal:edit")
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request, HttpServletResponse response, 
			CcmHouseDeliberatelyIllegal ccmHouseDeliberatelyIllegal, Model model, RedirectAttributes redirectAttributes) throws IOException {
		if (!beanValidator(model, ccmHouseDeliberatelyIllegal)) {
//			return form(ccmHouseDeliberatelyIllegal, model);
		}
		// 更新 当前人 是 故意犯法释放人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseDeliberatelyIllegal.getPeopleId());
		ccmHouseDeliberatelyIllegalService.save(ccmHouseDeliberatelyIllegal,ccmPop);
		if(ccmPop!=null){
			ccmPop.setIsDeliberatelyIllegal(1);
			ccmPeopleService.save(ccmPop);
		}
		
		addMessage(redirectAttributes, "保存故意犯法释放人员成功");

//		return "redirect:" + Global.getAdminPath() + "/house/ccmHouseDeliberatelyIllegal/?repage";

		PrintWriter out = response.getWriter();
		CommUtil.openWinExpDiv(out, "保存故意犯法释放人员成功");
		ccmPop.setMqTitle("新增重点人员(故意犯法释放人员)");
	}

	@RequiresPermissions("house:ccmHouseDeliberatelyIllegal:edit")
	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest request, CcmHouseDeliberatelyIllegal ccmHouseDeliberatelyIllegal, RedirectAttributes redirectAttributes) {
		ccmHouseDeliberatelyIllegalService.delete(ccmHouseDeliberatelyIllegal);
		addMessage(redirectAttributes, "删除故意犯法释放人员成功");
		String permissionKey = request.getParameter("permissionKey");
		// 更新 当前人 不再是 故意犯法释放人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseDeliberatelyIllegal.getPeopleId());
		if(ccmPop!=null){
			ccmPop.setIsDeliberatelyIllegal(0);
			ccmPeopleService.save(ccmPop);
		}
		
		return "redirect:" + Global.getAdminPath() + "/house/ccmHouseDeliberatelyIllegal/?repage&permissionKey=" + permissionKey;
	}
    
	
	
/*	@RequiresPermissions("house:ccmHouseDeliberatelyIllegal:view")*/
	@RequestMapping(value = "specialform")
	public String specialform(CcmPeople ccmPeople, Model model) {
		model.addAttribute("ccmPeople", ccmPeople);
		CcmHouseDeliberatelyIllegal DeliberatelyIllegal = ccmHouseDeliberatelyIllegalService.getPeopleALL(ccmPeople.getId());
		if (DeliberatelyIllegal == null) {
			DeliberatelyIllegal = new CcmHouseDeliberatelyIllegal();
		}
		model.addAttribute("ccmHouseDeliberatelyIllegal", DeliberatelyIllegal);
		return "/ccm/house/pop/ccmHousePoPDeliberatelyIllegalForm";
	}

	/**
	 * 导出故意犯法释放人员数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("house:ccmHouseDeliberatelyIllegal:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(CcmHouseDeliberatelyIllegal ccmHouseDeliberatelyIllegal, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String [] strArr={"姓名","联系方式","人口类型","现住门（楼）详址","公民身份号码","所判罪行","监管状态","审判结果","违法时间","关注程度","释放时间"};
		try {
			String fileName = "DeliberatelyPeople" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			
			List<CcmHouseDeliberatelyIllegal> list = new ArrayList<CcmHouseDeliberatelyIllegal>();
			String permissionKey = request.getParameter("permissionKey");
			User user = UserUtils.getUser();
			if (user != null && "1".equals(user.getHasPermission())) {//有涉密权限
				list = ccmHouseDeliberatelyIllegalService.findList(ccmHouseDeliberatelyIllegal);
			} else if (user != null && "0".equals(user.getHasPermission())) {//无涉密权限
				if (user.getPermissionKey() != null && user.getPermissionKey().equals(permissionKey)) {
					list = ccmHouseDeliberatelyIllegalService.findList(ccmHouseDeliberatelyIllegal);
				}
			}
			
//			List<CcmHouseDeliberatelyIllegal> list = ccmHouseDeliberatelyIllegalService.findList(ccmHouseDeliberatelyIllegal);
			new ExportExcel("故意犯法释放人员数据", CcmHouseDeliberatelyIllegal.class,strArr).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出故意犯法释放人员失败！失败信息：" + e.getMessage());
			System.out.println(e.getMessage());
		}
		return "redirect:" + adminPath + "/house/ccmHouseDeliberatelyIllegal/?repage";
	}

	/**
	 * 导入故意犯法释放人员数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("house:ccmHouseDeliberatelyIllegal:view")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/house/ccmHouseDeliberatelyIllegal/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<CcmHouseDeliberatelyIllegal> list = ei.getDataList(CcmHouseDeliberatelyIllegal.class);
			for (CcmHouseDeliberatelyIllegal HouseDeliberatelyIllegal : list) {
				try {

					if(EntityTools.isEmpty(HouseDeliberatelyIllegal)){
						continue;
					}

					if(StringUtils.isBlank(HouseDeliberatelyIllegal.getConvictCrimes()) ||
							StringUtils.isBlank(HouseDeliberatelyIllegal.getSuperviseStatus()) ||
							StringUtils.isBlank(HouseDeliberatelyIllegal.getConvictResult()) ||
							HouseDeliberatelyIllegal.getIllegalDate()==null ||
							StringUtils.isBlank(HouseDeliberatelyIllegal.getAtteType()) ||
							HouseDeliberatelyIllegal.getAcquittalDate()==null
					){
						StringBuilder str = new StringBuilder();
						str.append("(");
						if(StringUtils.isBlank(HouseDeliberatelyIllegal.getConvictCrimes())) {
							str.append("所判罪行信息错误;");
						}
						if(StringUtils.isBlank(HouseDeliberatelyIllegal.getSuperviseStatus())) {
							str.append("监管状态信息错误;");
						}
						if(StringUtils.isBlank(HouseDeliberatelyIllegal.getConvictResult())) {
							str.append("审判结果信息错误;");
						}
						if(StringUtils.isBlank(HouseDeliberatelyIllegal.getAtteType())) {
							str.append("关注程度信息错误;");
						}
						if(HouseDeliberatelyIllegal.getIllegalDate()==null) {
							str.append("违法时间信息错误;");
						}
						if(HouseDeliberatelyIllegal.getAcquittalDate()==null) {
							str.append("释放时间信息错误;");
						}
						str.append(")");
						failureMsg.append("<br/>故意犯法释放人员名 " + HouseDeliberatelyIllegal.getName() + " 导入失败：必填项为空。"+str.toString());
						continue;
					}

					// 如果当前用户的身份未填写或者为空或者身份证号码位数不够18位则应该进行 剔除
					if (StringUtils.isBlank(HouseDeliberatelyIllegal.getIdent()) || HouseDeliberatelyIllegal.getIdent().length() != 18) {
						failureMsg.append("<br/>实有人口名" + HouseDeliberatelyIllegal.getName() + " 导入失败：" + "身份证信息错误。");
						HouseDeliberatelyIllegal.setName(HouseDeliberatelyIllegal.getName() + "，失败原因：身份证信息错误");
						failureNum++;
						continue;
					}
					
					CcmPeople ccmPeople=new CcmPeople();
					ccmPeople.setIdent(HouseDeliberatelyIllegal.getIdent());
					List<CcmPeople> list1 = ccmPeopleService.findList(ccmPeople);
					CcmHouseDeliberatelyIllegal HouseDeliberatelyIllegalFind;

					if (list1.isEmpty()){
						failureMsg.append("<br/>故意犯法释放人员名 " + HouseDeliberatelyIllegal.getName() + " 导入失败：实有人口表中无此人");
						continue;
					}else{
						ccmPeople.setId(list1.get(0).getId());
						ccmPeople.setUpdateBy(UserUtils.getUser());
						ccmPeople.setUpdateDate(new Date());
						ccmPeople.setIsDeliberatelyIllegal(1);
						ccmPeopleService.updatePeople(ccmPeople);
						HouseDeliberatelyIllegalFind=ccmHouseDeliberatelyIllegalService.getPeopleALL(list1.get(0).getId());
						BeanValidators.validateWithException(validator, HouseDeliberatelyIllegal);
						if(HouseDeliberatelyIllegalFind == null){
							HouseDeliberatelyIllegal.setPeopleId(list1.get(0).getId());
							ccmHouseDeliberatelyIllegalService.save(HouseDeliberatelyIllegal);
							successNum++;
						}else{
							failureMsg.append("<br/>故意犯法释放人员名 " + HouseDeliberatelyIllegal.getName() + " 导入失败：记录已存在");
						}
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>故意犯法释放人员名 " + HouseDeliberatelyIllegal.getName() + " 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>登录名 " + HouseDeliberatelyIllegal.getName() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条故意犯法释放人员，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条故意犯法释放人员" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入故意犯法释放人员失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/house/ccmHouseDeliberatelyIllegal/?repage";
	}

}