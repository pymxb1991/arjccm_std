/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.home.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.act.entity.Act;
import com.arjjs.ccm.modules.act.service.ActTaskService;
import com.arjjs.ccm.modules.ccm.news.entity.Message;
import com.arjjs.ccm.modules.ccm.news.service.MessageService;
import com.arjjs.ccm.modules.ccm.report.entity.CcmPeopleAmount;
import com.arjjs.ccm.modules.ccm.service.entity.CcmServiceOnline;
import com.arjjs.ccm.modules.ccm.service.service.CcmServiceOnlineService;
import com.arjjs.ccm.modules.cms.entity.Article;
import com.arjjs.ccm.modules.oa.entity.OaNotify;
import com.arjjs.ccm.modules.oa.service.OaNotifyService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.arjjs.ccm.modules.plm.home.service.PlmPortalDictService;

/**
 * 门户内容Controller
 * @author liuxue
 * @version 2018-10-12
 */
@Controller
@RequestMapping(value = "${adminPath}/home/content")
public class HomeContentController extends BaseController {
	@Autowired
	private PlmPortalDictService plmPortalDictService;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private OaNotifyService oaNotifyService;
	@Autowired
	private CcmServiceOnlineService ccmServiceOnlineService;
	@Autowired
	private MessageService messageService;
	//个人门户
		@SuppressWarnings("static-access")
		@RequestMapping(value = { "taskList" })
		public String taskList( HttpServletRequest request, HttpServletResponse response,
				String height, String width, String content,String divId) {
			int line = plmPortalDictService.line(content);
			
			request.setAttribute("porheigh", height);
			request.setAttribute("line", line);
			request.setAttribute("porwidth", width);
			request.setAttribute("porcontent", content);
			request.setAttribute("divId", divId);
			
			return "plm/home/taskList/homeTaskList";
		}
		
		// 任务清单
		@SuppressWarnings({ "static-access" })		
		@RequestMapping(value = { "taskHome" })
		public String taskHome( HttpServletRequest request, HttpServletResponse response, Model model,
				String height, String width, String content,String divId) {
			int line = plmPortalDictService.line(content);
			OaNotify oaNotify=new OaNotify();
			oaNotify.setSelf(true);
			List<OaNotify> alist=oaNotifyService.findList(oaNotify);
			
			CcmServiceOnline ccmServiceOnline =new CcmServiceOnline();
			List<CcmServiceOnline> hlist=ccmServiceOnlineService.findList(ccmServiceOnline);
			request.setAttribute("annunciateList", alist);
			request.setAttribute("handleCheckList", hlist);
			request.setAttribute("annunciateCount", alist.size());
			request.setAttribute("handleCheckCount", hlist.size());
			request.setAttribute("porheigh", height);
			request.setAttribute("line", line);
			request.setAttribute("porwidth", width);
			request.setAttribute("porcontent", content);
			request.setAttribute("divId", divId);
			return "plm/home/taskList/taskHomeList";
		}
		
		// 我的消息
		@SuppressWarnings({ "static-access" })		
		@RequestMapping(value = { "mynews" })
		public String handleCheck(Article article, HttpServletRequest request, HttpServletResponse response, Model model,
				String height, String width, String content,String divId) {
			int line = plmPortalDictService.line(content);
			Message message = new Message();
			message.setUser(UserUtils.getUser());
			List<Message> list = messageService.findList(message); 
			for(int i = 0; i<list.size() ; i++) {
				if("04".equals(list.get(i).getType())) {
					list.get(i).setType("02");
				}
				if("13".equals(list.get(i).getType())) {
					list.get(i).setType("03");
				}
				if("23".equals(list.get(i).getType())) {
					list.get(i).setType("03");
				}
				if("33".equals(list.get(i).getType())) {
					list.get(i).setType("03");
				}
			}
			request.setAttribute("list", list);
			request.setAttribute("handleCheckCount", list.size());
			request.setAttribute("porheigh", height);
			request.setAttribute("line", line);
			request.setAttribute("porwidth", width);
			request.setAttribute("porcontent", content);
			request.setAttribute("divId", divId);
			return "plm/home/taskList/taskNewsList";
		}
			
	
}