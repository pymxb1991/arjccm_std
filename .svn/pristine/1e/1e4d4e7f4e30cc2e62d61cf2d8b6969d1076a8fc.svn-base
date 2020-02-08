/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.wechat.web;

import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.modules.pbs.common.config.Global;
import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.wechat.entity.PbsWebchatsendmsg;
import com.arjjs.ccm.modules.pbs.wechat.entity.WeChatMessage;
import com.arjjs.ccm.modules.pbs.wechat.entity.WechatReturnMsg;
import com.arjjs.ccm.modules.pbs.wechat.service.PbsWebchatsendmsgService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.google.common.collect.Maps;

/**
 * 微信端Controller
 * 
 * @author lc
 * @version 2018-05-28
 */
@Controller
@RequestMapping(value = "${adminPath}/wechat")
public class MobileWechatController extends BaseController {

	@Autowired
	private PbsWebchatsendmsgService pbsWebchatsendmsgService;

	@RequiresPermissions("wechat:pbsWebchatsendmsg:view")
	@RequestMapping(value = "getToken")
	@ResponseBody
	public String getToken() {
		// 远程
		RestTemplate restTemplate = new RestTemplate();
		WechatReturnMsg msg = restTemplate
				.getForObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
						+ Global.getAppidwx() + "&secret=" + Global.getSecretwx(), WechatReturnMsg.class);
		// 返回token值
		return msg.getAccess_token();
	}

	// 发送信息
	@RequiresPermissions("wechat:pbsWebchatsendmsg:view")
	@RequestMapping(value = "sendmessage")
	public String SendMessage(String id, RedirectAttributes redirectAttributes) {
		// 当前已经发送的信息
		int count = pbsWebchatsendmsgService.findSum();
		if (count >= 4) {
			addMessage(redirectAttributes, "发送失败：本月消息已经发送了4条");
			return "redirect:" + Global.getAdminPath() + "/wechat/pbsWebchatsendmsg/?repage";
		}

		// 获取 信息结果
		PbsWebchatsendmsg pbsWebchatsendmsg = pbsWebchatsendmsgService.get(id);
		// 发送成功了 就返回
		if (pbsWebchatsendmsg.getsStat().equals("2")) {
			addMessage(redirectAttributes, "发送失败：本消息已经发送");
			return "redirect:" + Global.getAdminPath() + "/wechat/pbsWebchatsendmsg/?repage";
		}
		// 远程获取token
		RestTemplate restTemplate = new RestTemplate();
		WechatReturnMsg msg = restTemplate
				.getForObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
						+ Global.getAppidwx() + "&secret=" + Global.getSecretwx(), WechatReturnMsg.class);
		// 发送的message
		WeChatMessage weChatMessage = new WeChatMessage();
		// 返回的map
		Map<String, Object> filter = Maps.newHashMap();
		Map<String, Object> toAll = Maps.newHashMap();
		filter.put("is_to_all", true);
		toAll.put("content", pbsWebchatsendmsg.getSSenddata());
		weChatMessage.setFilter(filter);
		weChatMessage.setText(toAll);
		weChatMessage.setMsgtype("text");
		// 发送post请求
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<WeChatMessage> entity = new HttpEntity<WeChatMessage>(weChatMessage, headers);
		WechatReturnMsg returnMsg = restTemplate.postForObject(
				"https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + msg.getAccess_token(), entity,
				WechatReturnMsg.class);

		// 更新状态
		pbsWebchatsendmsg.setsStat(returnMsg.getErrcode().equals("0") ? "2" : "3");
		pbsWebchatsendmsg.setDtSendtime(new Date());
		pbsWebchatsendmsg.setSResultcontent(returnMsg.getErrmsg());
		pbsWebchatsendmsg.setSRetcode(returnMsg.getErrcode());
		pbsWebchatsendmsg.setSRetmsg(returnMsg.getMsg_id());

		System.out.println("|||" + returnMsg.getErrmsg());
		// 更新发送的状态
		pbsWebchatsendmsgService.save(pbsWebchatsendmsg);
		if (!returnMsg.getErrcode().equals("0")) {
			addMessage(redirectAttributes, "发送失败：" + DictUtils.getDictValue(returnMsg.getErrcode(), "wxerrorcode", "")
					+ "，错误提示：" + returnMsg.getErrmsg());
		} else {
			addMessage(redirectAttributes, "发送成功。");
		}
		// 返回token值
		return "redirect:" + Global.getAdminPath() + "/wechat/pbsWebchatsendmsg/?repage";
	}

}