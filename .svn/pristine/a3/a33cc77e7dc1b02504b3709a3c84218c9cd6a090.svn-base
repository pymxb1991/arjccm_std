/**
 * Copyright &copy; 2012-2016 <a href="http://www.arjjs.com">arjjs</a> All rights reserved.
 */
package com.arjjs.ccm.modules.plm.act.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.act.entity.Act;
import com.arjjs.ccm.modules.act.service.ActTaskService;
import com.arjjs.ccm.modules.plm.home.service.PlmPortalDictService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

/**
 * 流程个人任务相关Controller
 * @author admin001
 * @version 2013-11-03
 */
@Controller
@RequestMapping(value = "${adminPath}/act/taskSelf")
public class ActTaskSelfController extends BaseController {

	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private PlmPortalDictService plmPortalDictService;
	
	/**
	 * 获取待办列表
	 * @param procDefKey 流程定义标识
	 * @return
	 */
	@RequestMapping(value = {"todo", ""})
	public String todoList(Act act, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String procInsIds = request.getParameter("procInsIds");
		act.setProcDefId(procInsIds);
		List<Act> list = actTaskService.todoList(act,"");
		model.addAttribute("list", list);
		if (UserUtils.getPrincipal().isMobileLogin()){
			return renderString(response, list);
		}
		return "plm/act/actTaskTodoList";
	}
	
	/**
	 * 获取已办任务
	 * @param page
	 * @param procDefKey 流程定义标识
	 * @return
	 */
	@RequestMapping(value = "historic")
	public String historicList(Act act, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String procInsIds = request.getParameter("procInsIds");
		act.setProcDefId(procInsIds);
		Page<Act> page = new Page<Act>(request, response);
		page = actTaskService.historicList(page, act,"");
		model.addAttribute("page", page);
		if (UserUtils.getPrincipal().isMobileLogin()){
			return renderString(response, page);
		}
		return "plm/act/actTaskHistoricList";
	}

	/**
	 * 个人门户定制首页-我的待办
	 * @param request
	 * @param response
	 * @param height
	 * @param width
	 * @param content
	 * @return
	 */
	@RequestMapping(value = {"actforHomeTodo"})
	public String actforHomeTodo(Act act, HttpServletRequest request, HttpServletResponse response ,String height,String width , String content,String divId) {
	    
	    // 最大显示行数
		  int line=plmPortalDictService.line(content);

		List<Act> list = actTaskService.todoList(act,"");
		request.setAttribute("list", list);
		request.setAttribute("porheigh", height);
		request.setAttribute("line", line);
		request.setAttribute("porwidth", width);
		request.setAttribute("porcontent", content);
		request.setAttribute("divId", divId);
		return "plm/home/act/plmActTodoList";
	}
	//获取我的待办数量
	public int actforHomeTodoNum(Act act) {
		List<Act> list = actTaskService.todoList(act,"");
		return list.size();
	}
	
	
	/**
	 * 个人门户定制首页-我的已办
	 * @param request
	 * @param response
	 * @param height
	 * @param width
	 * @param content
	 * @return
	 */
	@RequestMapping(value = {"actforHomeFinish"})
	public String actforHomeFinish(Act act, HttpServletRequest request, HttpServletResponse response ,String height,String width , String content,String divId) {
	    
	    // 最大显示行数
		  int line=plmPortalDictService.line(content);

		Page<Act> page = new Page<Act>(request, response);
		page = actTaskService.historicList(page, act,"");
		request.setAttribute("page", page);
		request.setAttribute("porheigh", height);
		request.setAttribute("line", line);
		request.setAttribute("porwidth", width);
		request.setAttribute("porcontent", content);
		request.setAttribute("divId", divId);
		return "plm/home/act/plmActFinishList";
	}	

	//获取我的已办数量
	public long actforHomeFinishNum(Act act,HttpServletRequest request, HttpServletResponse response) {
		Page<Act> page = new Page<Act>(request, response);
		page = actTaskService.historicList(page, act,"");
		return page.getCount();
	}	

}
