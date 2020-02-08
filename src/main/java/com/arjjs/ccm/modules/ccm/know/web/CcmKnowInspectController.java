/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowInspect;
import com.arjjs.ccm.modules.ccm.know.service.CcmKnowInspectService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgNpse;
import com.arjjs.ccm.tool.EchartType;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import static com.arjjs.ccm.tool.DateTools.getSixMonth;

/**
 * 检查记录Controller
 * @author liang
 * @version 2018-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/know/ccmKnowInspect")
public class CcmKnowInspectController extends BaseController {

	@Autowired
	private CcmKnowInspectService ccmKnowInspectService;
	
	@ModelAttribute
	public CcmKnowInspect get(@RequestParam(required=false) String id) {
		CcmKnowInspect entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmKnowInspectService.get(id);
		}
		if (entity == null){
			entity = new CcmKnowInspect();
		}
		return entity;
	}
	
	/**
	 * 安全生产防范检查
	 */
	@ResponseBody
	@RequestMapping(value = "getTypeSafeData")
	public String getTypeSafeData(Model model) {
		List<EchartType> listTypeSafe = ccmKnowInspectService.getTypeSafeData();//安全生产防范检查
		EchartType newEchartType = new EchartType();//非空判断
		newEchartType.setType("暂无数据");
		newEchartType.setValue("0");
		if(listTypeSafe.size()==0){
			listTypeSafe.add(newEchartType);
		}
		JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{""});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String listTypeSafeString = JSONArray.fromObject(listTypeSafe,config).toString(); //Json
		return listTypeSafeString;
	}



	/**
	 * 安全生产防范检查echarts
	 */
	@ResponseBody
	@RequestMapping(value = "getTypeSafeDataeCharts")
	public Map<String, Object> getTypeSafeDataeCharts(Model model) {
		List<EchartType> listTypeSafe = ccmKnowInspectService.getTypeSafeDataecharts();//安全生产防范检查
		Map safemap = listTypeSafe.stream().collect(Collectors.toMap(EchartType::getType, EchartType::getValue));


		Map map = Maps.newHashMap();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		List<String> listdata = getSixMonth(df.format(new Date()));
		List<String> listvalue = Lists.newArrayList();
		for (int i = 0; i < listdata.size(); i++) {
			if(safemap.get(listdata.get(i))!=null){
				listvalue.add(safemap.get(listdata.get(i)).toString());
			} else {
				listvalue.add("0");
			}
		}
		map.put("data",listdata);
		map.put("value",listvalue);
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	//
	@RequiresPermissions("know:ccmKnowInspect:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmKnowInspect ccmKnowInspect, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmKnowInspect> page = ccmKnowInspectService.findPage(new Page<CcmKnowInspect>(request, response), ccmKnowInspect); 
		model.addAttribute("page", page);
		return "ccm/know/ccmKnowInspectList";
	}

	@RequiresPermissions("know:ccmKnowInspect:view")
	@RequestMapping(value = "form")
	public String form(CcmKnowInspect ccmKnowInspect, Model model) {
		model.addAttribute("ccmKnowInspect", ccmKnowInspect);
		return "ccm/know/ccmKnowInspectForm";
	}

	@RequiresPermissions("know:ccmKnowInspect:edit")
	@RequestMapping(value = "save")
	public String save(CcmKnowInspect ccmKnowInspect, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmKnowInspect)){
			return form(ccmKnowInspect, model);
		}
		ccmKnowInspectService.save(ccmKnowInspect);
		addMessage(redirectAttributes, "保存检查记录成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowInspect/?repage";
	}
	
	@RequiresPermissions("know:ccmKnowInspect:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmKnowInspect ccmKnowInspect, RedirectAttributes redirectAttributes) {
		ccmKnowInspectService.delete(ccmKnowInspect);
		addMessage(redirectAttributes, "删除检查记录成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowInspect/?repage";
	}

}