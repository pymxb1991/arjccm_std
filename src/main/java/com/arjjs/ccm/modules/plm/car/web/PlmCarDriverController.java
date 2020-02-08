/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.utils.excel.ExportExcel;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.car.entity.PlmCarDriver;
import com.arjjs.ccm.modules.plm.car.service.PlmCarDriverService;
import com.arjjs.ccm.tool.Select2Type;

/**
 * 驾驶员Controller
 * @author fu
 * @version 2018-06-30
 */
@Controller
@RequestMapping(value = "${adminPath}/car/plmCarDriver")
public class PlmCarDriverController extends BaseController {

	@Autowired
	private PlmCarDriverService plmCarDriverService;
	
	@ModelAttribute
	public PlmCarDriver get(@RequestParam(required=false) String id) {
		PlmCarDriver entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmCarDriverService.get(id);
		}
		if (entity == null){
			entity = new PlmCarDriver();
			entity.setSex("0");
		}
		return entity;
	}
	
	@RequiresPermissions("car:plmCarDriver:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmCarDriver plmCarDriver, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmCarDriver> page = plmCarDriverService.findPage(new Page<PlmCarDriver>(request, response), plmCarDriver); 
		model.addAttribute("page", page);
		return "plm/car/plmCarDriverList";
	}

	@RequiresPermissions("car:plmCarDriver:view")
	@RequestMapping(value = "form")
	public String form(PlmCarDriver plmCarDriver, Model model) {
		model.addAttribute("plmCarDriver", plmCarDriver);
		return "plm/car/plmCarDriverForm";
	}

	@RequiresPermissions("car:plmCarDriver:edit")
	@RequestMapping(value = "save")
	public String save(PlmCarDriver plmCarDriver, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmCarDriver)){
			return form(plmCarDriver, model);
		}
		plmCarDriverService.save(plmCarDriver);
		addMessage(redirectAttributes, "保存驾驶员成功");
		return "redirect:"+Global.getAdminPath()+"/car/plmCarDriver/?repage";
	}
	
	@RequiresPermissions("car:plmCarDriver:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmCarDriver plmCarDriver, RedirectAttributes redirectAttributes) {
		plmCarDriverService.delete(plmCarDriver);
		addMessage(redirectAttributes, "删除驾驶员成功");
		return "redirect:"+Global.getAdminPath()+"/car/plmCarDriver/?repage";
	}
	@ResponseBody
	@RequestMapping(value = {"selectList"})
	public List<Select2Type> selectList(Model model,PlmCarDriver plmCarDriver) {
		List<Select2Type> list = plmCarDriverService.findSelect2Type(plmCarDriver); 
		return list;
	}
	/**
	 * 导出司机数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("car:plmCarDriver:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PlmCarDriver plmCarDriver, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "司机数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<PlmCarDriver> list = plmCarDriverService.findList(plmCarDriver);
    		new ExportExcel("用户数据", PlmCarDriver.class).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出司机失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/plm/car/plmCarDriverList?repage";
    }

/*	/**
	 * 导入司机数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 *//*
	@RequiresPermissions("car:plmCarDriver:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/plm/car/plmCarDriverList?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PlmCarDriver> list = ei.getDataList(PlmCarDriver.class);
			for (PlmCarDriver driver : list){
				try{
					plmCarDriverService.findEntityByDidNum
					if ("true".equals(checkLoginName("", user.getLoginName()))){
						user.setPassword(SystemService.entryptPassword("123456"));
						BeanValidators.validateWithException(validator, user);
						systemService.saveUser(user);
						successNum++;
					}else{
						failureMsg.append("<br/>登录名 "+user.getLoginName()+" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>登录名 "+user.getLoginName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>登录名 "+user.getLoginName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/plm/car/plmCarDriverList/list?repage";
    }
	
	*//**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 *//*
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户数据导入模板"+"templet.xlsx";
    		List<User> list = Lists.newArrayList(); list.add(UserUtils.getUser());
    		new ExportExcel("用户数据", User.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }*/
}