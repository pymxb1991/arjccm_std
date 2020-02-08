/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.home.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.arjjs.ccm.modules.plm.home.entity.PlmHome;
import com.arjjs.ccm.modules.plm.home.entity.PlmPortalDetail;
import com.arjjs.ccm.modules.plm.home.entity.PlmPortalDict;
import com.arjjs.ccm.modules.plm.home.entity.PlmPortalPlan;
import com.arjjs.ccm.modules.plm.home.service.PlmHomeService;
import com.arjjs.ccm.modules.plm.home.service.PlmPortalDetailService;
import com.arjjs.ccm.modules.plm.home.service.PlmPortalDictService;
import com.arjjs.ccm.modules.plm.home.service.PlmPortalPlanService;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

/**
 * 个人门户Controller
 * 
 * @author liuxue
 * @version 2018-06-29
 */
@Controller
@RequestMapping(value = "${adminPath}/home/plmHome")
public class PlmHomeController extends BaseController {

	@Autowired
	private PlmHomeService plmHomeService;
	@Autowired
	private PlmPortalDictService plmPortalDictService;

	@Autowired
	private PlmPortalPlanService plmPortalPlanService;

	@Autowired
	private PlmPortalDetailService plmPortalDetailService;

	@ModelAttribute
	public PlmHome get(@RequestParam(required = false) String id) {
		PlmHome entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = plmHomeService.get(id);
		}
		if (entity == null) {
			entity = new PlmHome();
		}
		return entity;
	}

	/**
	 * 个人门户展示页
	 * 
	 * @param plmHome
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list" })
	public String list(PlmHome plmHome, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		plmHome.setUser(user);

		List<PlmHome> portletlist = plmHomeService.findList(plmHome);
		/**
		 * 当该用户的个人门户信息表中没有信息时 将自动导入默认方案
		 */
		if (portletlist == null || portletlist.size() < 1) {
			PlmPortalDetail plmPortalDetail = new PlmPortalDetail();
			PlmPortalPlan plmPortalPlan = new PlmPortalPlan();
			// 设定默认的查询条件
			plmPortalPlan.setExtend1(plmHome.getExtend1());
			plmPortalPlan.setName("默认");
			List<PlmPortalDetail> portletDetaillist = null;
			plmPortalDetail.setParent(plmPortalPlanService.findList(plmPortalPlan).get(0));
			plmPortalDetail.setExtend1(plmHome.getExtend1());
			portletDetaillist = plmPortalDetailService.findList(plmPortalDetail);
			// 导入方案： 将默认方案的明细信息 转入个人门户信息表中
			for (PlmPortalDetail plmPortalDetail2 : portletDetaillist) {
				plmHome.setTitle(plmPortalDetail2.getTitle());
				plmHome.setConnect(plmPortalDetail2.getConnect());
				plmHome.setContent(plmPortalDetail2.getContent());
				plmHome.setType(plmPortalDetail2.getType());
				plmHome.setHight(plmPortalDetail2.getHight());
				plmHome.setLongItude(plmPortalDetail2.getLongItude());
				plmHome.setLatItude(plmPortalDetail2.getLatItude());
				plmHome.setSort(plmPortalDetail2.getSort());
				plmHome.setRemarks(plmPortalDetail2.getRemarks());
				plmHome.setId("");
				plmHome.setPortalDictId(plmPortalDetail2.getPortalDictId());
				plmHome.setExtend1(plmPortalDetail2.getExtend1());
				plmHomeService.save(plmHome);

			}
			plmHome.setTitle("");
			portletlist = plmHomeService.findList(plmHome);
		}
		model.addAttribute("portletlist", portletlist);
		return "plm/home/plmHomeShow";
	}

	/**
	 * 个人门户编辑页
	 * 
	 * @param plmHome
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "edit", "" })
	public String edit(HttpServletRequest request, HttpServletResponse response, Model model,
			PlmPortalPlan plmPortalPlan) {

		model.addAttribute("PlmPortalPlan", plmPortalPlan);

		PlmHome plmHome = new PlmHome();
		User user = UserUtils.getUser();
		plmHome.setUser(user);
		// 方案 下拉列表
		List<PlmPortalPlan> planList = plmPortalPlanService.findList(new PlmPortalPlan());
		model.addAttribute("planList", planList);
		// 窗口展示数据
		List<PlmHome> portletlist = plmHomeService.findList(plmHome);
		// 判断是否预览方案 根据方案 下拉列表 匹配相应方案
		List<PlmPortalDetail> portletDetaillist = null;
		PlmPortalDetail plmPortalDetail = new PlmPortalDetail();
		if (plmPortalPlan == null || StringUtils.isBlank(plmPortalPlan.getId())) {
			model.addAttribute("portletlist", portletlist);
			// 跟剧是否预览方案 判断显示隐藏按钮
			model.addAttribute("isfangan", 0);
		} else {
			plmPortalDetail.setParent(plmPortalPlan);
			portletDetaillist = plmPortalDetailService.findList(plmPortalDetail);
			// 把portletDetaillist 存入Session域中 一遍导入方案时操作
			request.getSession().setAttribute("portletDetaillist", portletDetaillist);
			model.addAttribute("portletlist", portletDetaillist);
			model.addAttribute("isfangan", 1);
		}
		return "plm/home/plmHomeEdit";
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
	public String importPlan(HttpServletRequest request, String pid) {
		PlmHome plmHome = new PlmHome();
		// 把portletDetaillist 从Session域中取出
		List<PlmPortalDetail> portletDetaillist = (List<PlmPortalDetail>) request.getSession()
				.getAttribute("portletDetaillist");
		// 删除该用户的个人门户信息
		User user = UserUtils.getUser();
		plmHome.setUser(user);
		plmHomeService.deleteData(plmHome);
		// 设定平台类型值
		PlmPortalPlan plmPortalPlan = plmPortalPlanService.get(pid);

		// 导入方案： 该方案的明细信息 转入个人门户信息表中
		for (PlmPortalDetail plmPortalDetail2 : portletDetaillist) {
			plmHome.setTitle(plmPortalDetail2.getTitle());
			plmHome.setConnect(plmPortalDetail2.getConnect());
			plmHome.setContent(plmPortalDetail2.getContent());
			plmHome.setType(plmPortalDetail2.getType());
			plmHome.setHight(plmPortalDetail2.getHight());
			plmHome.setLongItude(plmPortalDetail2.getLongItude());
			plmHome.setLatItude(plmPortalDetail2.getLatItude());
			plmHome.setSort(plmPortalDetail2.getSort());
			plmHome.setRemarks(plmPortalDetail2.getRemarks());
			plmHome.setExtend1(plmPortalPlan.getExtend1());
			plmHome.setId("");
			plmHome.setPortalDictId(plmPortalDetail2.getPortalDictId());
			plmHomeService.save(plmHome);
		}
		return "200";
	}

	/**
	 * 门户内容编辑回显
	 * 
	 * @param plmHome
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(PlmHome plmHome, Model model) {
		List<PlmPortalDict> plmPortalDictList = plmPortalDictService.findList(new PlmPortalDict());
		model.addAttribute("plmPortalDictList", plmPortalDictList);
		return "plm/home/plmHomeForm";
	}

	/**
	 * 根据Content 下拉列表 匹配相应更多链接 和标题
	 * 
	 * @param portalDictId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "selectContent")
	public Map<String, Object> selectContent(String portalDictId) {
		PlmPortalDict plmPortalDict = plmPortalDictService.get(portalDictId);
		String connect = plmPortalDict.getConnect();
		String title = plmPortalDict.getTitle();
		Map<String, Object> map = new HashMap<>();
		map.put("connect", connect);
		map.put("title", title);
		return map;
	}

	/**
	 * 编辑个人门户信息
	 * 
	 * @param plmHome
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(PlmHome plmHome, Model model, RedirectAttributes redirectAttributes) {
		plmHomeService.save(plmHome);
		addMessage(redirectAttributes, "保存个人门户成功");
		return "redirect:" + Global.getAdminPath() + "/home/plmHome/?repage";
	}

	/**
	 * 预修改使用方案内容（不改数据库）
	 * 
	 * @param plmHome
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "saveFan")
	public String saveFan(PlmPortalDetail plmPortalDetail, Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		PlmPortalPlan plmPortalPlan = new PlmPortalPlan();
		model.addAttribute("plmPortalPlan", plmPortalPlan);
		List<PlmPortalPlan> planList = plmPortalPlanService.findList(plmPortalPlan);
		model.addAttribute("planList", planList);// 方案 下拉列表
		List<PlmPortalDetail> portletDetaillist = (List<PlmPortalDetail>) request.getSession()
				.getAttribute("portletDetaillist");
		int i = 0;
		for (PlmPortalDetail plmPortalDetail2 : portletDetaillist) {
			if (plmPortalDetail.getId().equals(plmPortalDetail2.getId())) {
				plmPortalDetail2.setTitle(plmPortalDetail.getTitle());
				plmPortalDetail2.setConnect(plmPortalDetail.getConnect());
				plmPortalDetail2.setContent(plmPortalDetail.getContent());
				portletDetaillist.set(i, plmPortalDetail2);
			}
			i++;
		}
		model.addAttribute("portletlist", portletDetaillist);
		model.addAttribute("isfangan", 1);// 跟剧是否预览方案 判断显示隐藏按钮
		addMessage(redirectAttributes, "保存个人门户成功");
		return "plm/home/plmHomeEdit";
	}

	@RequestMapping(value = "delete")
	public String delete(PlmHome plmHome, RedirectAttributes redirectAttributes) {
		plmHomeService.delete(plmHome);
		addMessage(redirectAttributes, "删除个人门户成功");
		return "redirect:" + Global.getAdminPath() + "/home/plmHome/?repage";
	}

	/**
	 * 恢复删除
	 * 
	 * @param plmHome
	 * @param redirectAttributes
	 * @return
	 */

	@RequestMapping(value = "undelete")
	public String undelete(PlmHome plmHome, RedirectAttributes redirectAttributes) {
		plmHomeService.undelete(plmHome);
		addMessage(redirectAttributes, "恢复个人门户成功");
		return "redirect:" + Global.getAdminPath() + "/home/plmHome/?repage";
	}

}