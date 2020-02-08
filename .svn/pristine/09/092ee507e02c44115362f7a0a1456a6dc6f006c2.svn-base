/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.message.web;

import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.message.entity.CcmMessage;
import com.arjjs.ccm.modules.ccm.message.service.CcmMessageService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息Controller
 *
 * @author lhf
 * @version 2019-10-24
 */
@Controller
@RequestMapping(value = "${adminPath}/message/ccmMessage")
public class CcmMessageController extends BaseController {

	@Autowired
	private CcmMessageService ccmMessageService;

	@ModelAttribute
	public CcmMessage get(@RequestParam(value = "id", required = false) String id) {
		CcmMessage entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmMessageService.get(id);
		}
		if (entity == null) {
			entity = new CcmMessage();
		}
		return entity;
	}

	@RequestMapping("read")
	public void read(CcmMessage ccmMessage, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotBlank(ccmMessage.getId())){
			ccmMessage.setReadFlag("1");
			ccmMessageService.save(ccmMessage);
		}
	}

	/**
	 * 今日消息和登录用户未读消息
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getListTodayAndUnread")
	public List<CcmMessage> getListTodayAndUnread() {
		String userId = UserUtils.getUser().getId();
		List<CcmMessage> listTodayAndUnread = ccmMessageService.getListTodayAndUnread(userId);
		List<CcmMessage> newList = new ArrayList<>();
		for (CcmMessage ccmMessage : listTodayAndUnread) {
			if(ccmMessage.getReadFlag().equals("0")){
				ccmMessage.setReadFlag("1");
				ccmMessageService.updateRead(ccmMessage);
				ccmMessage.setReadFlag("0");
			}
		}
		return listTodayAndUnread;
	}

}