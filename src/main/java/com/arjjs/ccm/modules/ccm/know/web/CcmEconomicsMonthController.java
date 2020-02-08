/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.know.entity.CcmEconomicsMonth;
import com.arjjs.ccm.modules.ccm.know.entity.CcmEconomicsYear;
import com.arjjs.ccm.modules.ccm.know.service.CcmEconomicsMonthService;
import com.arjjs.ccm.tool.EchartType;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 经济运行数据-月Controller
 * @author liang
 * @version 2018-06-03
 */
@Controller
@RequestMapping(value = "${adminPath}/know/ccmEconomicsMonth")
public class CcmEconomicsMonthController extends BaseController {

	@Autowired
	private CcmEconomicsMonthService ccmEconomicsMonthService;
	
	@ModelAttribute
	public CcmEconomicsMonth get(@RequestParam(required=false) String id) {
		CcmEconomicsMonth entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmEconomicsMonthService.get(id);
		}
		if (entity == null){
			entity = new CcmEconomicsMonth();
		}
		return entity;
	}

	/**
	 * 经济数据-大屏经济建设
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getData")
	public List<String> getData(Model model) {
		// 返回对象结果
		List<String> list = new ArrayList<>();
		List<CcmEconomicsMonth> listMonth = ccmEconomicsMonthService.getData();
		if(listMonth.size()!=0){
			list.add(listMonth.get(0).getGdp()+"");
			list.add(listMonth.get(0).getPersonalIncome()+"");
		}else{
			list.add("0");
			list.add("0");
		}
		return list;
	}
	/**
	 * 税收统计-大屏经济建设
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getShuiShouData")
	public String getShuiShouData(Model model) {
		// 返回对象结果
		List<CcmEconomicsMonth> listMonth = ccmEconomicsMonthService.getShuiShouData();
		JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{"createBy","updateBy","currentUser","createDate","updateDate","remarks",
				"beginMonths","endMonths","id",
				"dbName","delFlag","global","isNewRecord","page","sqlMap"});//排除
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String listMonthAll = JSONArray.fromObject(listMonth,config).toString(); //Json
		return listMonthAll;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequiresPermissions("know:ccmEconomicsMonth:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmEconomicsMonth ccmEconomicsMonth, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmEconomicsMonth> page = ccmEconomicsMonthService.findPage(new Page<CcmEconomicsMonth>(request, response), ccmEconomicsMonth); 
		model.addAttribute("page", page);
		return "ccm/know/ccmEconomicsMonthList";
	}

	@RequiresPermissions("know:ccmEconomicsMonth:view")
	@RequestMapping(value = "form")
	public String form(CcmEconomicsMonth ccmEconomicsMonth, Model model) {
		model.addAttribute("ccmEconomicsMonth", ccmEconomicsMonth);
		return "ccm/know/ccmEconomicsMonthForm";
	}

	@RequiresPermissions("know:ccmEconomicsMonth:edit")
	@RequestMapping(value = "save")
	public String save(CcmEconomicsMonth ccmEconomicsMonth, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmEconomicsMonth)){
			return form(ccmEconomicsMonth, model);
		}
		ccmEconomicsMonthService.save(ccmEconomicsMonth);
		addMessage(redirectAttributes, "保存经济运行数据-月成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmEconomicsMonth/?repage";
	}
	
	@RequiresPermissions("know:ccmEconomicsMonth:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmEconomicsMonth ccmEconomicsMonth, RedirectAttributes redirectAttributes) {
		ccmEconomicsMonthService.delete(ccmEconomicsMonth);
		addMessage(redirectAttributes, "删除经济运行数据-月成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmEconomicsMonth/?repage";
	}

}