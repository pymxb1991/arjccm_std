package com.arjjs.ccm.modules.im.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmUserGroup;
import com.arjjs.ccm.modules.im.entity.ImParamData;
import com.arjjs.ccm.modules.im.service.BphImService;
import com.arjjs.ccm.tool.Pager;

@Controller
@RequestMapping(value = "${adminPath}/im")
public class BphImController extends BaseController {
	
	@Autowired
	private BphImService bphImService;
	
	@RequestMapping(value = "historymessage")
	public String historymessage(Model model, HttpServletRequest request,HttpServletResponse response,ImParamData paramData) {
		Pager pager = bphImService.historymessage(paramData);
		model.addAttribute("pager", pager);
		return "layim/page/historymessage";
	}
	
	/**
	 * 方法说明：创建分组
	 * @param request
	 * @param response
	 * @param ccmUserGroup
	 * @throws IOException
	 */
	@RequestMapping(value = "userGroup")
	public void userGroup(HttpServletRequest request,HttpServletResponse response,CcmUserGroup ccmUserGroup) {
		 bphImService.userGroup(ccmUserGroup);                                                        
	}
	
	
	/**
	 * 方法说明：删除分组
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	public int deleteUserGroup(HttpServletRequest request,HttpServletResponse response,String id) {
		//TODO 暂时未作，后期补充
		return 0;
	}
	
	/**
	 * 方法说明：删除群组下得部分人员
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 */
	@RequestMapping("deleteUser")
	public int deleteUser(HttpServletRequest request,HttpServletResponse response,String userId) {
		//TODO 暂时未作，后期补充
		return 0;
	}
}
