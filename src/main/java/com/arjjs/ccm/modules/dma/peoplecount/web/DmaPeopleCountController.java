package com.arjjs.ccm.modules.dma.peoplecount.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.tool.EchartType;
import net.sf.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPeopleService;
import com.arjjs.ccm.modules.ccm.sys.entity.SysDicts;
import com.arjjs.ccm.modules.ccm.sys.service.SysDictsService;

/**
 * 
 * 实有人口统计Controller
 * 
 * @author cby
 * @version 2019-09-04
 */
@Controller
@RequestMapping(value = "${adminPath}/comprehensiveTopic/damPeopleCount")
public class DmaPeopleCountController extends BaseController {
	@Autowired
	private CcmPeopleService ccmPeopleService;
	//数据字典
	@Autowired
	private SysDictsService sysDictsService;
	
	@RequiresPermissions("comprehensiveTopic:dmaSocialSecurity:view")
	@RequestMapping(value = { "Index", "" })
	public String Index(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "dma/peoplecount/peopleIndex";
	}
	

	
	//人口性别分析统计
	@ResponseBody
	@RequestMapping(value = "peopleSexCount")
	public Map<String, Object> peopleSexCount(CcmPeople ccmPeople, Model model,RedirectAttributes redirectAttributes) {
		List<SysDicts> list = sysDictsService.findAllListByType("sex");
		String[] dict = new String[list.size()];
		List<Map<String, Object>> listData = new ArrayList<Map<String,Object>>();
		int i = 0;
		CcmPeople people = new CcmPeople();
		for (SysDicts dicts : list) {
			dict[i] = dicts.getLabel();
			people.setSex(dicts.getValue());
			int num = ccmPeopleService.peopleSexCount(people).getResultNum();
			Map<String, Object> temp = new HashMap<>();
			temp.put("name", dicts.getLabel());
			temp.put("value", num);
			listData.add(temp);
			i++;
		}
		Map<String, Object> data = new HashMap<>();
		data.put("name", dict);
		data.put("value", listData);
		return data;
	}
	
	
	//人员出生时间统计
	@ResponseBody
	@RequestMapping(value = "peopleBirthdayCount")
	public Map<String, Object> peopleBirthdayCount(CcmPeople ccmPeople, Model model,RedirectAttributes redirectAttributes) {
		List<SysDicts> list = sysDictsService.findAllListByType("sex");
		Map<String, Object> result = ccmPeopleService.peopleBirthdayCount(list);
		return result;
	}
	
	//各民族人员统计
	@ResponseBody
	@RequestMapping(value = "peopleNationCount")
	public Map<String, Object> peopleNationCount(CcmPeople ccmPeople, Model model,RedirectAttributes redirectAttributes) {
		List<SysDicts> list = sysDictsService.findAllListByType("sys_volk");
		return ccmPeopleService.peopleNationCount(list);
	}
	
	//人员年龄段统计
	@ResponseBody
	@RequestMapping(value = "peopleAgeCount")
	public Map<String, Object> peopleAgeCount(CcmPeople ccmPeople, Model model,RedirectAttributes redirectAttributes) {
		return ccmPeopleService.peopleAgeCount();
	}
	
	//人员区域社区(街道)分布分析统计
	@ResponseBody
	@RequestMapping(value = "peopleRegionCount")
	public List<EchartType> peopleRegionCount(Model model, HttpServletResponse response) {
		List<EchartType> list = ccmPeopleService.peopleRegionCount();
		JSONArray jsondata = JSONArray.fromObject(list);

		return jsondata;
	}
	
}
