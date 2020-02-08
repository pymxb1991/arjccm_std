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
import com.arjjs.ccm.modules.ccm.know.service.CcmEconomicsYearService;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 经济运行数据-年Controller
 * @author liang
 * @version 2018-06-03
 */
@Controller
@RequestMapping(value = "${adminPath}/know/ccmEconomicsYear")
public class CcmEconomicsYearController extends BaseController {

	@Autowired
	private CcmEconomicsYearService ccmEconomicsYearService;
	
	@ModelAttribute
	public CcmEconomicsYear get(@RequestParam(required=false) String id) {
		CcmEconomicsYear entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmEconomicsYearService.get(id);
		}
		if (entity == null){
			entity = new CcmEconomicsYear();
		}
		return entity;
	}
	/**
	 * 经济运行数据-大屏经济建设
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getData")
	public String getData(Model model) {
		// 返回对象结果
		List<CcmEconomicsYear> listYears = ccmEconomicsYearService.getData();
		JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{"createBy","updateBy","currentUser","createDate","updateDate","remarks",
				"beginYears","endYears","id",
				"dbName","delFlag","global","isNewRecord","page","sqlMap"});//排除
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String listlistYearsAll = JSONArray.fromObject(listYears,config).toString(); //Json
		return listlistYearsAll;
	}
	@RequiresPermissions("know:ccmEconomicsYear:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmEconomicsYear ccmEconomicsYear, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmEconomicsYear> page = ccmEconomicsYearService.findPage(new Page<CcmEconomicsYear>(request, response), ccmEconomicsYear); 
		model.addAttribute("page", page);
		return "ccm/know/ccmEconomicsYearList";
	}

	@RequiresPermissions("know:ccmEconomicsYear:view")
	@RequestMapping(value = "form")
	public String form(CcmEconomicsYear ccmEconomicsYear, Model model) {
		model.addAttribute("ccmEconomicsYear", ccmEconomicsYear);
		return "ccm/know/ccmEconomicsYearForm";
	}

	@RequiresPermissions("know:ccmEconomicsYear:edit")
	@RequestMapping(value = "save")
	public String save(CcmEconomicsYear ccmEconomicsYear, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmEconomicsYear)){
			return form(ccmEconomicsYear, model);
		}
		ccmEconomicsYearService.save(ccmEconomicsYear);
		addMessage(redirectAttributes, "保存经济运行数据-年成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmEconomicsYear/?repage";
	}
	
	@RequiresPermissions("know:ccmEconomicsYear:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmEconomicsYear ccmEconomicsYear, RedirectAttributes redirectAttributes) {
		ccmEconomicsYearService.delete(ccmEconomicsYear);
		addMessage(redirectAttributes, "删除经济运行数据-年成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmEconomicsYear/?repage";
	}

}