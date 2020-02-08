/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

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
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgNpse;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgNpseEconomic;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgNpseEconomicService;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgNpseService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPopBehind;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 企业经济运行数据Controller
 * @author liang
 * @version 2018-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/org/ccmOrgNpseEconomic")
public class CcmOrgNpseEconomicController extends BaseController {

	@Autowired
	private CcmOrgNpseEconomicService ccmOrgNpseEconomicService;
	@Autowired
	private CcmOrgNpseService ccmOrgNpseService;
	
	
	
	@ModelAttribute
	public CcmOrgNpseEconomic get(@RequestParam(required=false) String id) {
		CcmOrgNpseEconomic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmOrgNpseEconomicService.get(id);
		}
		if (entity == null){
			entity = new CcmOrgNpseEconomic();
		}
		return entity;
	}
	
	//关联企业
	@RequiresPermissions("org:ccmOrgNpseEconomic:view")
	@RequestMapping(value = "listEconomic")
	public String listEconomic(CcmOrgNpseEconomic ccmOrgNpseEconomic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmOrgNpseEconomic> page = ccmOrgNpseEconomicService.findPage(new Page<CcmOrgNpseEconomic>(request, response), ccmOrgNpseEconomic); 
		CcmOrgNpse ccmOrgNpse = new CcmOrgNpse();
		ccmOrgNpse = ccmOrgNpseService.get(ccmOrgNpseEconomic.getNaspId());
		model.addAttribute("page", page);
		model.addAttribute("ccmOrgNpseEconomic", ccmOrgNpseEconomic);
		model.addAttribute("ccmOrgNpse", ccmOrgNpse);
		return "ccm/org/ccmOrgNpseEconomicListOne";
	}
	//关联企业
	@RequiresPermissions("org:ccmOrgNpseEconomic:view")
	@RequestMapping(value = "formEconomic")
	public String formEconomic(CcmOrgNpseEconomic ccmOrgNpseEconomic, Model model) {
		if(ccmOrgNpseEconomic.getId()!=null && !"".equals(ccmOrgNpseEconomic.getId())){
			ccmOrgNpseEconomic = ccmOrgNpseEconomicService.get(ccmOrgNpseEconomic.getId());
		}
		model.addAttribute("ccmOrgNpseEconomic", ccmOrgNpseEconomic);
		return "ccm/org/ccmOrgNpseEconomicFormOne";
	}
	//关联企业
	@RequiresPermissions("org:ccmOrgNpseEconomic:edit")
	@RequestMapping(value = "saveEconomic")
	public String saveEconomic(CcmOrgNpseEconomic ccmOrgNpseEconomic, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmOrgNpseEconomic)){
			return formEconomic(ccmOrgNpseEconomic, model);
		}
		ccmOrgNpseEconomicService.save(ccmOrgNpseEconomic);
		addMessage(redirectAttributes, "保存企业经济运行数据成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgNpseEconomic/listEconomic?repage&naspId="+ccmOrgNpseEconomic.getNaspId();
	}
	//关联企业
	@RequiresPermissions("org:ccmOrgNpseEconomic:edit")
	@RequestMapping(value = "deleteEconomic")
	public String deleteEconomic(CcmOrgNpseEconomic ccmOrgNpseEconomic, RedirectAttributes redirectAttributes) {
		ccmOrgNpseEconomicService.delete(ccmOrgNpseEconomic);
		addMessage(redirectAttributes, "删除企业经济运行数据成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgNpseEconomic/listEconomic?repage&naspId="+ccmOrgNpseEconomic.getNaspId();
	}
	
	
	
	
	
	
	//
	@RequiresPermissions("org:ccmOrgNpseEconomic:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmOrgNpseEconomic ccmOrgNpseEconomic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmOrgNpseEconomic> page = ccmOrgNpseEconomicService.findPage(new Page<CcmOrgNpseEconomic>(request, response), ccmOrgNpseEconomic); 
		model.addAttribute("page", page);
		return "ccm/org/ccmOrgNpseEconomicList";
	}

	@RequiresPermissions("org:ccmOrgNpseEconomic:view")
	@RequestMapping(value = "form")
	public String form(CcmOrgNpseEconomic ccmOrgNpseEconomic, Model model, String flag) {
		model.addAttribute("ccmOrgNpseEconomic", ccmOrgNpseEconomic);
		model.addAttribute("flag", flag);
		return "ccm/org/ccmOrgNpseEconomicForm";
	}

	@RequiresPermissions("org:ccmOrgNpseEconomic:edit")
	@RequestMapping(value = "save")
	public void save(CcmOrgNpseEconomic ccmOrgNpseEconomic, Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		if (!beanValidator(model, ccmOrgNpseEconomic)){
//			return form(ccmOrgNpseEconomic, model, "");
		}
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(ccmOrgNpseEconomic.getId()==null || "".equals(ccmOrgNpseEconomic.getId())){
			if(ccmOrgNpseEconomic.getCompId()!=null && !"".equals(ccmOrgNpseEconomic.getCompId())){
				CcmOrgNpse ccmOrgNpse = new CcmOrgNpse();
				ccmOrgNpse.setCompId(ccmOrgNpseEconomic.getCompId());
				List<CcmOrgNpse> list = ccmOrgNpseService.findList(ccmOrgNpse);
				if(list.size()==1){
					ccmOrgNpseEconomic.setNaspId(list.get(0).getId());//注入企业Id
				}else{
//					return form(ccmOrgNpseEconomic, model, "工商执照注册号存在问题，请核查！");
					CommUtil.openWinExpDiv(out, "工商执照注册号存在问题，请核查！");
					return;
				}
			}else{
//				return form(ccmOrgNpseEconomic, model, "请输入工商执照注册号！");
				CommUtil.openWinExpDiv(out, "请输入工商执照注册号！");
				return;
			}
			
		}
		ccmOrgNpseEconomicService.save(ccmOrgNpseEconomic);
		CommUtil.openWinExpDiv(out, "保存企业经济运行数据成功");
//		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgNpseEconomic/?repage";
	}
	
	@RequiresPermissions("org:ccmOrgNpseEconomic:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmOrgNpseEconomic ccmOrgNpseEconomic, RedirectAttributes redirectAttributes) {
		ccmOrgNpseEconomicService.delete(ccmOrgNpseEconomic);
		addMessage(redirectAttributes, "删除企业经济运行数据成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgNpseEconomic/?repage";
	}
	/**
	 * 导出经济运行数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("org:ccmOrgNpseEconomic:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(CcmOrgNpseEconomic ccmOrgNpseEconomic, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "经济运行数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<CcmOrgNpseEconomic> list = ccmOrgNpseEconomicService.findList(ccmOrgNpseEconomic);
			new ExportExcel("经济运行数据", CcmOrgNpseEconomic.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出经济运行数据失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/org/ccmOrgNpseEconomic/?repage";
	}

	/**
	 * 导入经济运行数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("org:ccmOrgNpseEconomic:view")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/org/ccmOrgNpseEconomic/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<CcmOrgNpseEconomic> list = ei.getDataList(CcmOrgNpseEconomic.class);
			for (CcmOrgNpseEconomic ccmOrgNpseEconomic : list) {
				try {

					BeanValidators.validateWithException(validator, ccmOrgNpseEconomic);
					CcmOrgNpse ccmOrgNpse = new CcmOrgNpse();
					ccmOrgNpse.setCompId(ccmOrgNpseEconomic.getCompId());
					List<CcmOrgNpse> listCcmOrgNpse = ccmOrgNpseService.findList(ccmOrgNpse);//企业
					
					if(listCcmOrgNpse.size()==1){
						ccmOrgNpseEconomic.setNaspId(listCcmOrgNpse.get(0).getId());//注入企业Id
						CcmOrgNpseEconomic ccmEconomic = new CcmOrgNpseEconomic();//同年同企业
						ccmEconomic.setNaspId(listCcmOrgNpse.get(0).getId());
						//ccmEconomic.setCompId(ccmOrgNpseEconomic.getCompId());
						Date yearsFlag = new Date();
						yearsFlag.setTime(0);
						yearsFlag.setYear(ccmOrgNpseEconomic.getYears().getYear());
						yearsFlag.setMonth(0);
						yearsFlag.setDate(1);
						yearsFlag.setHours(0);
						yearsFlag.setMinutes(0);
						yearsFlag.setSeconds(0);
						ccmEconomic.setBeginYears(yearsFlag);
						ccmEconomic.setEndYears(yearsFlag);
						List<CcmOrgNpseEconomic> listCcmOrgNpseEconomic = ccmOrgNpseEconomicService.findList(ccmEconomic);
						if(listCcmOrgNpseEconomic.size()==0){
							ccmOrgNpseEconomic.setYears(yearsFlag);
							ccmOrgNpseEconomicService.save(ccmOrgNpseEconomic);
							successNum++;
						}else{
							failureMsg.append("<br/>经济运行数据导入失败： 工商执照注册号" + ccmOrgNpseEconomic.getCompId() +"和年份"+ccmOrgNpseEconomic.getYears() +" 已存在！");
						}
					}else{
						failureMsg.append("<br/>工商执照注册号 " + ccmOrgNpseEconomic.getCompId() + " 导入失败：工商执照注册号存在问题，请核查！");
					}
					
					
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>工商执照注册号 " + ccmOrgNpseEconomic.getCompId() + " 导入失败：工商执照注册号存在问题，请核查！");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>工商执照注册号 " + ccmOrgNpseEconomic.getCompId() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条经济运行数据，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条经济运行数据" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入经济运行数据失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/org/ccmOrgNpseEconomic/?repage";
	}
	 
}