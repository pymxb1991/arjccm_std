package com.arjjs.ccm.modules.ccm.pop.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPeopleService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.CommUtil;
import com.arjjs.ccm.tool.EchartType;
import com.google.common.collect.Maps;

/**
 * 实有人口Controller 统计查询
 * 
 * @author arj
 * @version 2018-01-15
 */
@Controller
@RequestMapping(value = "${adminPath}/pop/ccmPepInfo")
public class CcmPeopleInfoController extends BaseController {

	@Autowired
	private CcmPeopleService ccmPeopleService;

	@ModelAttribute
	public CcmPeople get(@RequestParam(required = false) String type) {
		CcmPeople entity = null;
		entity = new CcmPeople();

		return entity;
	}

	/**
	 * @see  用于显示人口统计页面
	 * @param ccmPeople
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pop:ccmPepInfo:view")
	@RequestMapping(value = { "form", "" })
	public String form(CcmPeople ccmPeople, Model model) {
		model.addAttribute("ccmPeople", ccmPeople);
		return "ccm/pop/ccmPepInfo";
	}

	/**
	 * @see  获取Json 数据
	 * @param type 当前用户类别
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pop:ccmPepInfo:view")
	@ResponseBody
	@RequestMapping(value = "popEchA")
	public Map<String, Object> popEchA(@RequestParam(value = "type", required = false) String type, Model model) {
		// 当前条件是否存在
		CcmPeople ccmPeople = new CcmPeople();
		if (!StringUtils.isBlank(type)) {
			ccmPeople.setType(type);
		}
		/**
		 * 展示 单列数据传送
		 */
		List<EchartType> eListS1 = ccmPeopleService.getListByMon(UserUtils.getUser(), ccmPeople);
		List<EchartType> eListS2 = ccmPeopleService.getListByDay(UserUtils.getUser(), ccmPeople);
		// 排序
		Collections.sort(eListS1, new Comparator<EchartType>() {
			@Override
			public int compare(EchartType o1, EchartType o2) {
				return CommUtil.compareE(o1, o2);
			}
		});
		// 排序
		Collections.sort(eListS2, new Comparator<EchartType>() {
			@Override
			public int compare(EchartType o1, EchartType o2) {
				return CommUtil.compareE(o1, o2);
			}
		});
		// 解析数据
		Map<String, Object> map = Maps.newHashMap();
		map.put("按月", eListS1);
		map.put("按日", eListS2);
		/**
		 * 展示 多组数据传送
		 */
		ccmPeople.setType("10");
		List<EchartType> eList1 = ccmPeopleService.getListByMon(UserUtils.getUser(), ccmPeople);
		ccmPeople.setType("20");
		List<EchartType> eList2 = ccmPeopleService.getListByMon(UserUtils.getUser(), ccmPeople);
		ccmPeople.setType("30");
		List<EchartType> eList3 = ccmPeopleService.getListByMon(UserUtils.getUser(), ccmPeople);
		// mapList.add(map);
		List<String> M = new ArrayList<>();
		// 添加 共同 List
		M = CommUtil.getTitleList(M, eList3);
		M = CommUtil.getTitleList(M, eList2);
		M = CommUtil.getTitleList(M, eList1);
		// 自排序 日期方法
		Collections.sort(M, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return CommUtil.compareE(o1, o2);
			}
		});
		// 获取标准的List
		List<EchartType> ListReslt1 = CommUtil.getSimple(M, eList1);
		List<EchartType> ListReslt2 = CommUtil.getSimple(M, eList2);
		List<EchartType> ListReslt3 = CommUtil.getSimple(M, eList3);
		// 生成结果
		map.put("户籍", ListReslt1);
		map.put("流动", ListReslt2);
		map.put("境外", ListReslt3);
		return map;
	}

}
