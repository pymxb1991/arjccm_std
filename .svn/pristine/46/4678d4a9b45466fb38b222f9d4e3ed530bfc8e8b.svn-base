/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.arjjs.ccm.tool.EntityTools;
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

import com.arjjs.ccm.common.beanvalidator.BeanValidators;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.utils.excel.ExportExcel;
import com.arjjs.ccm.common.utils.excel.ImportExcel;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseEscape;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseEscapeService;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.modules.ccm.log.service.CcmLogTailService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPeopleService;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.CommUtil;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 在逃人员Controller
 * @author 李彩云
 * @version 2018-09-19
 */
@Controller
@RequestMapping(value = "${adminPath}/house/ccmHouseEscape")
public class CcmHouseEscapeController extends BaseController {

	@Autowired
	private CcmHouseEscapeService ccmHouseEscapeService;
	@Autowired
	private CcmPeopleService ccmPeopleService;
	@Autowired
	private CcmLogTailService ccmLogTailService;

	@ModelAttribute
	public CcmHouseEscape get(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "peopleId", required = false) String peopleId) {
		CcmHouseEscape entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmHouseEscapeService.get(id);
		} else if (StringUtils.isNotBlank(peopleId)) {
			entity = ccmHouseEscapeService.getPeopleALL(peopleId);
		}
		if (entity == null) {
			entity = new CcmHouseEscape();
			// 如果 peopleId 不为空 则 添加该ID
			if (StringUtils.isNotBlank(peopleId)) {
				entity.setPeopleId(peopleId);
			}
		}

		return entity;
	}

	@RequiresPermissions("house:ccmHouseEscape:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmHouseEscape ccmHouseEscape, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CcmHouseEscape> page = new Page();
		String permissionKey = request.getParameter("permissionKey");
		User user = UserUtils.getUser();
		if (user != null && "1".equals(user.getHasPermission())) {//有涉密权限
			page = ccmHouseEscapeService.findPage(new Page<CcmHouseEscape>(request, response), ccmHouseEscape);
		} else if (user != null && "0".equals(user.getHasPermission())) {//无涉密权限
			if (user.getPermissionKey() != null && user.getPermissionKey().equals(permissionKey)) {
				page = ccmHouseEscapeService.findPage(new Page<CcmHouseEscape>(request, response), ccmHouseEscape);
			} else {
				model.addAttribute("message", "涉密权限不正确！");
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("permissionKey", permissionKey);
		return "ccm/house/ccmHouseEscapeList";
	}

	@RequiresPermissions("house:ccmHouseEscape:view")
	@RequestMapping(value = "form")
	public String form(CcmHouseEscape ccmHouseEscape, Model model) {
		// 创建 查询对象 建立查询条件
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(ccmHouseEscape.getId());
		ccmLogTailDto.setRelevanceTable("ccm_house_Escape");
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
		model.addAttribute("ccmHouseEscape", ccmHouseEscape);
		return "ccm/house/ccmHouseEscapeForm";
	}

	@RequiresPermissions("house:ccmHouseEscape:edit")
	@RequestMapping(value = "savePop")
	public String savePop(CcmHouseEscape ccmHouseEscape, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmHouseEscape)) {
			return form(ccmHouseEscape, model);
		}
		
		ccmHouseEscapeService.save(ccmHouseEscape);
		// 更新 当前人 是 在逃人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseEscape.getPeopleId());
		if(ccmPop!=null){
			ccmPop.setIsEscape(1);
			ccmPeopleService.save(ccmPop);
		}
		
		addMessage(redirectAttributes, "保存在逃人员成功");

		return "redirect:" + Global.getAdminPath() + "/pop/ccmPeople/?repage";
	}

	@RequiresPermissions("house:ccmHouseEscape:edit")
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request, HttpServletResponse response, 
			CcmHouseEscape ccmHouseEscape, Model model, RedirectAttributes redirectAttributes) throws IOException {
		if (!beanValidator(model, ccmHouseEscape)) {
//			return form(ccmHouseEscape, model);
		}
		ccmHouseEscapeService.save(ccmHouseEscape);
		// 更新 当前人 是 在逃人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseEscape.getPeopleId());
		if(ccmPop!=null){
			ccmPop.setIsEscape(1);
			ccmPeopleService.save(ccmPop);
		}
		
		addMessage(redirectAttributes, "保存在逃人员成功");

//		return "redirect:" + Global.getAdminPath() + "/house/ccmHouseEscape/?repage";

		PrintWriter out = response.getWriter();
		CommUtil.openWinExpDiv(out, "保存在逃人员成功");
		
	}

	@RequiresPermissions("house:ccmHouseEscape:edit")
	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest request, CcmHouseEscape ccmHouseEscape, RedirectAttributes redirectAttributes) {
		ccmHouseEscapeService.delete(ccmHouseEscape);
		addMessage(redirectAttributes, "删除在逃人员成功");
		String permissionKey = request.getParameter("permissionKey");
		// 更新 当前人 不再是在逃人员
		CcmPeople ccmPop = ccmPeopleService.get(ccmHouseEscape.getPeopleId());
		if(ccmPop!=null){
			ccmPop.setIsEscape(0);
			ccmPeopleService.save(ccmPop);
		}
		
		return "redirect:" + Global.getAdminPath() + "/house/ccmHouseEscape/?repage&permissionKey=" + permissionKey;
	}


	/**
	 * 导出在逃人员数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("house:ccmHouseEscape:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(CcmHouseEscape ccmHouseEscape, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "在逃人员数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			
			List<CcmHouseEscape> list = new ArrayList<CcmHouseEscape>();
			String permissionKey = request.getParameter("permissionKey");
			User user = UserUtils.getUser();
			if (user != null && "1".equals(user.getHasPermission())) {//有涉密权限
				list = ccmHouseEscapeService.findList(ccmHouseEscape);
			} else if (user != null && "0".equals(user.getHasPermission())) {//无涉密权限
				if (user.getPermissionKey() != null && user.getPermissionKey().equals(permissionKey)) {
					list = ccmHouseEscapeService.findList(ccmHouseEscape);
				}
			}
			
//			List<CcmHouseEscape> list = ccmHouseEscapeService.findList(ccmHouseEscape);
			new ExportExcel("在逃人员数据", CcmHouseEscape.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出在逃人员失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/house/ccmHouseEscape/?repage";
	}

	/**
	 * 导入在逃人员数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("house:ccmHouseEscape:view")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/house/ccmHouseEscape/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<CcmHouseEscape> list = ei.getDataList(CcmHouseEscape.class);
			for (CcmHouseEscape HouseEscape : list) {
				try {

					if(EntityTools.isEmpty(HouseEscape)){
						continue;
					}
					CcmPeople ccmPeople=new CcmPeople();
					ccmPeople.setIdent(HouseEscape.getIdent());
					List<CcmPeople> list1 = ccmPeopleService.findList(ccmPeople);
					CcmHouseEscape HouseEscapeFind;

					if (list1.isEmpty()){
						failureMsg.append("<br/>在逃人员名 " + HouseEscape.getName() + " 导入失败：实有人口表中无此人");
						continue;
					}else{
						HouseEscapeFind=ccmHouseEscapeService.getPeopleALL(list1.get(0).getId());
					}
					BeanValidators.validateWithException(validator, HouseEscape);
					if(HouseEscapeFind == null){

						ccmHouseEscapeService.save(HouseEscape);
						successNum++;
					}else{
						failureMsg.append("<br/>在逃人员名 " + HouseEscape.getName() + " 导入失败：记录已存在");
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>在逃人员名 " + HouseEscape.getName() + " 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>登录名 " + HouseEscape.getName() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条在逃人员，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条在逃人员" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入在逃人员失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/house/ccmHouseEscape/?repage";
	}
	 
	
}