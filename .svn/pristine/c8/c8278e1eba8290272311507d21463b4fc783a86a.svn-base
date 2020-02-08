/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.statistics.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatistics;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsDetail;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsDict;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsPlan;
import com.arjjs.ccm.modules.plm.statistics.service.PlmStatisticsDetailService;
import com.arjjs.ccm.modules.plm.statistics.service.PlmStatisticsDictService;
import com.arjjs.ccm.modules.plm.statistics.service.PlmStatisticsPlanService;
import com.arjjs.ccm.modules.plm.statistics.service.PlmStatisticsService;
import com.arjjs.ccm.modules.sys.entity.Dict;
import com.arjjs.ccm.modules.sys.service.DictService;
import com.google.common.collect.Lists;

/**
 * 统计首页Controller
 * 
 * @author liuxue
 * @version 2018-07-24
 */
@Controller
@RequestMapping(value = "${adminPath}/statistics/plmStatistics")
public class PlmStatisticsController extends BaseController {

	@Autowired
	private PlmStatisticsService plmStatisticsService;
	@Autowired
	private PlmStatisticsDictService plmStatisticsDictService;
	@Autowired
	private PlmStatisticsPlanService plmStatisticsPlanService;
	@Autowired
	private PlmStatisticsDetailService plmStatisticsDetailService;
	@Autowired
	private DictService dictService;

	@ModelAttribute
	public PlmStatistics get(@RequestParam(required = false) String id) {
		PlmStatistics entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = plmStatisticsService.get(id);
		}
		if (entity == null) {
			entity = new PlmStatistics();
		}
		return entity;
	}

	/**
	 * 统计首页展示页
	 * 
	 * @param plmStatistics
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String list(PlmStatistics plmStatistics, HttpServletRequest request, HttpServletResponse response, Model model) {
		int pageMax = plmStatisticsService.pagesMax();
		if (pageMax == 0) {
			/**
			 * 当该用户的统计首页信息表中没有信息时 将自动导入方案名称有“默认”字符的第一个（get(0)）方案
			 */
			PlmStatisticsDetail plmStatisticsDetail = new PlmStatisticsDetail();
			PlmStatisticsPlan plmStatisticsPlan = new PlmStatisticsPlan();
			plmStatisticsPlan.setName("默认");
			List<PlmStatisticsDetail> portletDetaillist = null;
			List<PlmStatisticsPlan> portletPlanlist = plmStatisticsPlanService.findList(plmStatisticsPlan);
			plmStatisticsDetail.setParent(portletPlanlist.get(0));
			portletDetaillist = plmStatisticsDetailService.findList(plmStatisticsDetail);
			// 导入方案： 将默认方案的明细信息 转入统计首页信息表中
			for (PlmStatisticsDetail plmStatisticsDetail2 : portletDetaillist) {
				plmStatistics.setTitle(plmStatisticsDetail2.getTitle());
				plmStatistics.setContent(plmStatisticsDetail2.getContent());
				plmStatistics.setType(portletPlanlist.get(0).getType());
				plmStatistics.setPages("1");
				plmStatistics.setPageTitle(portletPlanlist.get(0).getPageTitle());
				plmStatistics.setLongItude(plmStatisticsDetail2.getLongItude());
				plmStatistics.setLatItude(plmStatisticsDetail2.getLatItude());
				plmStatistics.setSort(plmStatisticsDetail2.getSort());
				plmStatistics.setRemarks(plmStatisticsDetail2.getRemarks());
				plmStatistics.setId("");
				plmStatisticsService.save(plmStatistics);
			}
			pageMax = 1;
		}
		List<Map<String, Object>> listmap = Lists.newArrayList();
		for (int pages = 1; pages <= pageMax; pages++) {
			plmStatistics.setPages(String.valueOf(pages));
			List<PlmStatistics> portletlist = plmStatisticsService.findList(plmStatistics);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("portletlist", portletlist);
			map.put("pageTitle", portletlist.get(0).getPageTitle());
			listmap.add(map);
		}
		model.addAttribute("listmap", listmap);
		return "plm/statistics/plmStatisticsShow";
	}

	/**
	 * 把pages 存入Session 域中
	 * 
	 * @param request
	 * @param response
	 * @param pages
	 * @return
	 */
	@RequestMapping(value = { "jianTou" })
	public String jianTou(HttpServletRequest request, HttpServletResponse response, String pages, String list) {
		request.getSession().setAttribute("pages", pages);
		if (list.equals("0")) {
			return "redirect:" + Global.getAdminPath() + "/statistics/plmStatistics/?repage";
		}
		return "redirect:" + Global.getAdminPath() + "/statistics/plmStatistics/list?repage";
	}

	/**
	 * 统计首页编辑页
	 * 
	 * @param plmStatistics
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "edit", "" })
	public String edit(HttpServletRequest request, HttpServletResponse response, Model model, PlmStatisticsPlan plmStatisticsPlan) {
		model.addAttribute("PlmStatisticsPlan", plmStatisticsPlan);
		PlmStatistics plmStatistics = new PlmStatistics();
		// 方案 下拉列表
		List<PlmStatisticsPlan> planList = plmStatisticsPlanService.findList(plmStatisticsPlan);
		model.addAttribute("planList", planList);
		String pages = (String) request.getSession().getAttribute("pages");
		if (pages == null || pages.equals("")) {
			pages = "1";
			request.getSession().setAttribute("pages", pages);
		}
		plmStatistics.setPages(pages);
		// 窗口展示数据
		List<PlmStatistics> portletlist = plmStatisticsService.findList(plmStatistics);
		// 判断是否预览方案 根据方案 下拉列表 匹配相应方案
		List<PlmStatisticsDetail> portletDetaillist = null;
		PlmStatisticsDetail plmStatisticsDetail = new PlmStatisticsDetail();
		if (plmStatisticsPlan.toString().isEmpty() || plmStatisticsPlan.getId() == null || "".equals(plmStatisticsPlan.getId())) {
			model.addAttribute("portletlist", portletlist);
			if (portletlist != null && portletlist.size() > 0) {
				model.addAttribute("pageTitle", portletlist.get(0).getPageTitle());
			}
			model.addAttribute("pages", pages);
			// 跟剧是否预览方案 判断显示隐藏按钮
			model.addAttribute("isfangan", 0);
		} else {
			plmStatisticsDetail.setParent(plmStatisticsPlan);
			portletDetaillist = plmStatisticsDetailService.findList(plmStatisticsDetail);
			// 把portletDetaillist plmStatisticsPlan存入Session域中 一遍导入方案时操作
			request.getSession().setAttribute("portletDetaillist", portletDetaillist);
			plmStatisticsPlan = plmStatisticsPlanService.get(plmStatisticsPlan);
			request.getSession().setAttribute("plmStatisticsPlan", plmStatisticsPlan);
			model.addAttribute("portletlist", portletDetaillist);
			model.addAttribute("pageTitle", plmStatisticsPlan.getPageTitle());
			model.addAttribute("isfangan", 1);
		}
		return "plm/statistics/plmStatisticsEdit";
	}

	/**
	 * pageTitle修改
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "pageTitle")
	public String pageTitle(HttpServletRequest request, String pageTitle, String pages) {
		PlmStatistics plmStatistics = new PlmStatistics();
		if (pages == null || pages.equals("")) {
			PlmStatisticsPlan plmStatisticsPlan = (PlmStatisticsPlan) request.getSession().getAttribute("plmStatisticsPlan");
			plmStatisticsPlan.setPageTitle(pageTitle);
			request.getSession().setAttribute("plmStatisticsPlan", plmStatisticsPlan);
		} else {
			plmStatistics.setPages(pages);
			plmStatistics.setPageTitle(pageTitle);
			plmStatisticsService.updatePageTitle(plmStatistics);
		}
		return pageTitle;
	}

	/**
	 * 设置背景图片
	 */
	@RequestMapping(value = "backgroundImage")
	public String setBackgroundImage(String backgroundImage, HttpServletRequest request, HttpServletResponse response) {
		Dict dict = new Dict();
		dict.setType("statisticBackgroundImageSava");
		dict.setLabel("存储");
		dict.setValue(backgroundImage);
		// dictService.updateTypeAndLabel(dict);
		return "redirect:" + Global.getAdminPath() + "/statistics/plmStatistics/?repage";
	}

	/**
	 * 获取背景图片名称
	 */
	@ResponseBody
	@RequestMapping(value = "backgroundImageName")
	public String getBackgroundImage(HttpServletRequest request, HttpServletResponse response) {
		Dict dict = new Dict();
		dict.setType("statisticBackgroundImageSava");
		dict.setLabel("存储");
		List<Dict> list = dictService.findList(dict);
		String name = "bg.jpg";
		if (list.size() > 0) {
			name = list.get(0).getValue();
		}
		return name;
	}

	/**
	 * 删除此页
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delPage")
	public String delPage(HttpServletRequest request, String pages) {
		PlmStatistics plmStatistics = new PlmStatistics();
		plmStatistics.setPages(pages);
		plmStatisticsService.delete(plmStatistics);
		plmStatisticsService.updatePages(plmStatistics);
		return "200";
	}

	/**
	 * 导入方案
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "importPlan")
	public String importPlan(HttpServletRequest request) {
		PlmStatistics plmStatistics = new PlmStatistics();
		// //把portletDetaillist plmStatisticsPlan从Session域中取出
		List<PlmStatisticsDetail> portletDetaillist = (List<PlmStatisticsDetail>) request.getSession().getAttribute("portletDetaillist");
		PlmStatisticsPlan plmStatisticsPlan = (PlmStatisticsPlan) request.getSession().getAttribute("plmStatisticsPlan");
		// 删除该用户的统计首页信息
		/*
		 * User user = UserUtils.getUser(); plmStatistics.setUser(user);
		 */
		String pages = (String) request.getSession().getAttribute("pages");
		plmStatistics.setPages(pages);
		plmStatisticsService.delete(plmStatistics);
		// 导入方案： 该方案的明细信息 转入统计首页信息表中
		for (PlmStatisticsDetail plmStatisticsDetail2 : portletDetaillist) {
			plmStatistics.setTitle(plmStatisticsDetail2.getTitle());
			plmStatistics.setContent(plmStatisticsDetail2.getContent());
			plmStatistics.setType(plmStatisticsPlan.getType());
			plmStatistics.setPages(pages);
			plmStatistics.setPageTitle(plmStatisticsPlan.getPageTitle());
			plmStatistics.setLongItude(plmStatisticsDetail2.getLongItude());
			plmStatistics.setLatItude(plmStatisticsDetail2.getLatItude());
			plmStatistics.setSort(plmStatisticsDetail2.getSort());
			plmStatistics.setRemarks(plmStatisticsDetail2.getRemarks());
			plmStatistics.setId("");
			plmStatisticsService.save(plmStatistics);
		}
		return "200";
	}

	/**
	 * 首页内容编辑回显
	 * 
	 * @param plmStatistics
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(PlmStatistics plmStatistics, Model model) {
		model.addAttribute("plmStatistics", plmStatistics);
		// Content 下拉列表
		PlmStatisticsDict plmStatisticsDict = new PlmStatisticsDict();
		List<PlmStatisticsDict> plmStatisticsDictList = plmStatisticsDictService.findList(plmStatisticsDict);
		model.addAttribute("plmStatisticsDictList", plmStatisticsDictList);
		return "plm/statistics/plmStatisticsForm";
	}

	/**
	 * 根据Content 下拉列表 匹配相应更多链接 和标题
	 * 
	 * @param content
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "selectContent")
	public Map<String, Object> selectContent(String content) {
		PlmStatisticsDict plmStatisticsDict = new PlmStatisticsDict();
		plmStatisticsDict.setContent(content);
		List<PlmStatisticsDict> plmStatisticsDictList = plmStatisticsDictService.findList(plmStatisticsDict);
		String title = plmStatisticsDictList.get(0).getTitle();
		Map<String, Object> map = new HashMap<>();
		map.put("title", title);
		return map;
	}

	/**
	 * 编辑统计首页信息
	 * 
	 * @param plmStatistics
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(PlmStatistics plmStatistics, Model model, RedirectAttributes redirectAttributes) {
		plmStatisticsService.save(plmStatistics);
		addMessage(redirectAttributes, "保存统计首页成功");
		return "redirect:" + Global.getAdminPath() + "/statistics/plmStatistics/?repage";
	}

	/**
	 * 预修改使用方案内容（不改数据库）
	 * 
	 * @param plmStatistics
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "saveFan")
	public String saveFan(PlmStatisticsDetail plmStatisticsDetail, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		PlmStatisticsPlan plmStatisticsPlan = new PlmStatisticsPlan();
		model.addAttribute("PlmStatisticsPlan", plmStatisticsPlan);
		// 方案 下拉列表
		List<PlmStatisticsPlan> planList = plmStatisticsPlanService.findList(plmStatisticsPlan);
		model.addAttribute("planList", planList);
		List<PlmStatisticsDetail> portletDetaillist = (List<PlmStatisticsDetail>) request.getSession().getAttribute("portletDetaillist");
		int i = 0;
		for (PlmStatisticsDetail plmStatisticsDetail2 : portletDetaillist) {
			if (plmStatisticsDetail.getId().equals(plmStatisticsDetail2.getId())) {
				plmStatisticsDetail2.setTitle(plmStatisticsDetail.getTitle());
				plmStatisticsDetail2.setContent(plmStatisticsDetail.getContent());
				portletDetaillist.set(i, plmStatisticsDetail2);
			}
			i++;
		}
		model.addAttribute("portletlist", portletDetaillist);
		// 跟剧是否预览方案 判断显示隐藏按钮
		model.addAttribute("isfangan", 1);
		addMessage(redirectAttributes, "保存统计首页成功");
		return "plm/statistics/plmStatisticsEdit";
	}

	@RequiresPermissions("statistics:plmStatistics:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmStatistics plmStatistics, RedirectAttributes redirectAttributes) {
		plmStatisticsService.delete(plmStatistics);
		addMessage(redirectAttributes, "删除统计首页成功");
		return "redirect:" + Global.getAdminPath() + "/statistics/plmStatistics/?repage";
	}
}