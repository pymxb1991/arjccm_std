/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventAmbi;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventCasedeal;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventRequest;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventRequestdeal;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventRequestService;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.tool.CommUtil;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 请求登记Controller
 * 
 * @author arj
 * @version 2018-01-18
 */
@Controller
@RequestMapping(value = "${adminPath}/event/ccmEventRequest")
public class CcmEventRequestController extends BaseController {

	@Autowired
	private CcmEventRequestService ccmEventRequestService;

	@ModelAttribute
	public CcmEventRequest get(@RequestParam(required = false) String id) {
		CcmEventRequest entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmEventRequestService.get(id);
		}
		if (entity == null) {
			entity = new CcmEventRequest();
		}
		return entity;
	}

	@RequiresPermissions("event:ccmEventRequest:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmEventRequest ccmEventRequest, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CcmEventRequest> page = ccmEventRequestService.findPage(new Page<CcmEventRequest>(request, response),
				ccmEventRequest);
		model.addAttribute("page", page);
		return "ccm/event/ccmEventRequestList";
	}
	/**
	 * 请求详情，用于弹出层dialog的显示，方法内容和form里面的一样  
	 * @param ccmEventAmbi
	 * @param model
	 * @return
	 */
	@RequiresPermissions("event:ccmEventRequest:view")
	@RequestMapping(value = "detail")
	public String detail(CcmEventRequest ccmEventRequest, Model model) {
		model.addAttribute("ccmEventRequest", ccmEventRequest);
		return "ccm/event/ccmEventRequestDetail";
	}
	@RequiresPermissions("event:ccmEventRequest:view")
	@RequestMapping(value = "form")
	public String form(CcmEventRequest ccmEventRequest, Model model) {
//		List<CcmEventRequestdeal> list = ccmEventRequestService.findList(ccmEventRequest.getId());
		List<CcmEventCasedeal> CcmEventCasedealList = ccmEventRequestService.findCasedealList(ccmEventRequest.getId());
		for (CcmEventCasedeal ccmEventCasedeal : CcmEventCasedealList) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(ccmEventCasedeal.getCreateDate());
//			ccmEventCasedeal.setDealDate(date);todo
		}
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"createBy","updateBy","currentUser","dbName","global","page","createDate","updateDate","sqlMap"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String jsonDocumentList = JSONArray.fromObject(CcmEventCasedealList,config).toString(); 
		model.addAttribute("CcmEventCasedealList", jsonDocumentList);
		model.addAttribute("CasedealListNumber", CcmEventCasedealList.size());
		model.addAttribute("ccmEventRequest", ccmEventRequest);
		
		return "ccm/event/ccmEventRequestForm";
	}

	@RequiresPermissions("event:ccmEventRequest:edit")
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request,
			HttpServletResponse response,CcmEventRequest ccmEventRequest, Model model, RedirectAttributes redirectAttributes) throws IOException {
		if("".equals(ccmEventRequest.getId())||ccmEventRequest.getId()==null){
			ccmEventRequest.setType("01");
		}
		ccmEventRequestService.save(ccmEventRequest);
		PrintWriter out = response.getWriter();
		CommUtil.openWinExpDiv(out, "保存成功");
	}

	@RequiresPermissions("event:ccmEventRequest:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmEventRequest ccmEventRequest, RedirectAttributes redirectAttributes) {
		ccmEventRequestService.delete(ccmEventRequest);
		addMessage(redirectAttributes, "删除请求登记成功");
		return "redirect:" + Global.getAdminPath() + "/event/ccmEventRequest/?repage";
	}

}