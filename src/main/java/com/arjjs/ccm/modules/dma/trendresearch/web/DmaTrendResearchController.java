/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.dma.trendresearch.web;

import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventIncidentService;
import com.arjjs.ccm.modules.ccm.report.service.CcmPeopleStatService;
import com.arjjs.ccm.tool.EchartType;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 趋势分析Controller
 * 
 * @author lhf
 * @version 2019-09-6
 */
@Controller
@RequestMapping(value = "${adminPath}/trendResearch/dmaTrendResearch")
public class DmaTrendResearchController extends BaseController {
	@Autowired
	CcmPeopleStatService ccmPeopleStatService;

 	@Autowired
	CcmEventIncidentService ccmEventIncidentService;

	@RequiresPermissions("trendResearch:dmaTrendResearch:view")
	@RequestMapping(value = { "list", "" })
	public String list(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		return "dma/trendresearch/trendResearchList";
	}




	@ResponseBody
	@RequestMapping(value = "getCountpersonAndEvent")
	public Map<String, Object> getCountpersonAndEvent(){
		List<EchartType> reslist =  ccmPeopleStatService.getcountperson(12);
		List<EchartType> reseventlist = ccmEventIncidentService.getcountevent("12");
		Map<String,String> resmap = Maps.newHashMap();
		for(int i = 0;i<reseventlist.size();i++){
			resmap.put(reseventlist.get(i).getType(),reseventlist.get(i).getValue());
		}

		String[] type = new String[reslist.size()];
		String[] value = new String[reslist.size()];
		String[] value1 = new String[reslist.size()];
		String[] value2 = new String[reslist.size()];
		for (int i = 0; i < reslist.size(); i++) {
			type[i] = reslist.get(i).getType();
			value[i] = reslist.get(i).getValue();
			value1[i] = reslist.get(i).getValue1();
			if(resmap.containsKey(reslist.get(i).getType())){
				value2[i] = resmap.get(reslist.get(i).getType());
			} else {
				value2[i] = "0";
			}

		}

		Map<String, Object> data = Maps.newHashMap();
		data.put("name", type);
		data.put("value", value);
		data.put("value1", value1);
		data.put("value2", value2);
		return data;
	}

}