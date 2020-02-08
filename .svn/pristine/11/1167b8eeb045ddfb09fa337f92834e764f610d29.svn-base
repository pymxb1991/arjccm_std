/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.broadcast.web;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Encoder;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.broadcast.entity.CcmDeviceBroadcast;
import com.arjjs.ccm.modules.ccm.broadcast.service.CcmDeviceBroadcastService;
import com.arjjs.ccm.tool.PasswordUtils;
import com.arjjs.ccm.tool.Tool;


/**
 * 广播站Controller
 * @author maoxb
 * @version 2019-08-28
 */
@Controller
@RequestMapping(value = "${adminPath}/broadcast/ccmDeviceBroadcast")
public class CcmDeviceBroadcastController extends BaseController {

	@Autowired
	private CcmDeviceBroadcastService ccmDeviceBroadcastService;
	
	@ModelAttribute
	public CcmDeviceBroadcast get(@RequestParam(required=false) String id) {
		CcmDeviceBroadcast entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmDeviceBroadcastService.get(id);
		}
		if (entity == null){
			entity = new CcmDeviceBroadcast();
		}
		return entity;
	}
	
	@RequiresPermissions("broadcast:ccmDeviceBroadcast:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmDeviceBroadcast ccmDeviceBroadcast, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmDeviceBroadcast> page = ccmDeviceBroadcastService.findPage(new Page<CcmDeviceBroadcast>(request, response), ccmDeviceBroadcast); 
		model.addAttribute("page", page);
		return "ccm/broadcast/ccmDeviceBroadcastList";
	}

	@RequiresPermissions("broadcast:ccmDeviceBroadcast:view")
	@RequestMapping(value = "form")
	public String form(CcmDeviceBroadcast ccmDeviceBroadcast, Model model) {
		model.addAttribute("ccmDeviceBroadcast", ccmDeviceBroadcast);
		return "ccm/broadcast/ccmDeviceBroadcastForm";
	}

	@RequiresPermissions("broadcast:ccmDeviceBroadcast:edit")
	@RequestMapping(value = "save")
	public String save(CcmDeviceBroadcast ccmDeviceBroadcast, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmDeviceBroadcast)){
			return form(ccmDeviceBroadcast, model);
		}
		ccmDeviceBroadcastService.save(ccmDeviceBroadcast);
		addMessage(redirectAttributes, "保存广播站成功");
		return "redirect:"+Global.getAdminPath()+"/broadcast/ccmDeviceBroadcast/?repage";
	}
	
	@RequiresPermissions("broadcast:ccmDeviceBroadcast:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmDeviceBroadcast ccmDeviceBroadcast, RedirectAttributes redirectAttributes) {
		ccmDeviceBroadcastService.delete(ccmDeviceBroadcast);
		addMessage(redirectAttributes, "删除广播站成功");
		return "redirect:"+Global.getAdminPath()+"/broadcast/ccmDeviceBroadcast/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = "upload")
	public String upload(CcmDeviceBroadcast ccmDeviceBroadcast, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (StringUtils.isNotEmpty(ccmDeviceBroadcast.getFileUrl()) && StringUtils.isNotEmpty(ccmDeviceBroadcast.getCode())){
			String URL = Global.getConfig("FILE_UPLOAD_TO_BROAD");
			String file = Global.getConfig("FILE_UPLOAD_URL") + URLDecoder.decode(ccmDeviceBroadcast.getFileUrl().replace("|", ""),"utf-8");
			String fileType = file.substring(file.lastIndexOf(".")+1);
			int docType;
			if("mp3".equals(fileType)) {
				docType = 1; 
			}else if("txt".equals(fileType)) { 
				docType = 0;  
			}else {
				return "3";
			}
			long currentTime=new Date().getTime(); 
			String clientId = "11";
			String signature = "timestamp="+currentTime+"&status=30&docUrl="+file+"&docType="+docType+"&areaId="+ccmDeviceBroadcast.getCode();
			StringBuffer param = new StringBuffer();
			param.append("clientId="+clientId);
			param.append("&signature="+PasswordUtils.createPassword(signature).toUpperCase());
			param.append("&timestamp="+currentTime);
			param.append("&status=30");
			param.append("&docUrl="+file);
			param.append("&docType="+docType);
			param.append("&areaId="+ccmDeviceBroadcast.getCode());
			logger.debug("下发广播参数日志打印----------》》》》》》》》》"+param);
			String result = Tool.sendPost(URL, param.toString());
			logger.debug("下发广播返回结果信息日志打印----------》》》》》》》》》"+result);
			HashMap map = JSON.parseObject(result, HashMap.class);
			String str = map.get("status").toString();
			logger.debug("下发广播返回结果信息日志打印----------》》》》》》》》》"+map.get("message").toString());
			String message = Tool.toUTF8(map.get("message").toString());
			logger.debug("下发广播返回结果信息日志打印----------》》》》》》》》》"+message);
			logger.debug("下发广播返回页面结果日志打印----------》》》》》》》》》"+str);
			if("1".equals(str)) {
				return str;
			}else {
				return "0";
			}
		}else {
			return "4";
		}
	}

}