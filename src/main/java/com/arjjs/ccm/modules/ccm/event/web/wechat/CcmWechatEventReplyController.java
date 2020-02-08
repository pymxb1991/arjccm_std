/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.web.wechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.event.entity.wechat.CcmWechatEvent;
import com.arjjs.ccm.modules.ccm.event.entity.wechat.CcmWechatEventReply;
import com.arjjs.ccm.modules.ccm.event.service.wechat.CcmWechatEventReplyService;
import com.arjjs.ccm.modules.ccm.event.service.wechat.CcmWechatEventService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmWechatEventReplyRest;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

/**
 * 微信信息回复Controller
 * @author fu
 * @version 2018-04-14
 */
@Controller
@RequestMapping(value = "${adminPath}/event/wechat/ccmWechatEventReply")
public class CcmWechatEventReplyController extends BaseController {

	@Autowired
	private CcmWechatEventReplyService ccmWechatEventReplyService;
	@Autowired
	private CcmWechatEventService ccmWechatEventService;
	
	@ModelAttribute
	public CcmWechatEventReply get(@RequestParam(required=false) String id) {
		CcmWechatEventReply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmWechatEventReplyService.get(id);
		}
		if (entity == null){
			entity = new CcmWechatEventReply();
		}
		return entity;
	}
	
	@RequiresPermissions("event:wechat:ccmWechatEventReply:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmWechatEventReply ccmWechatEventReply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmWechatEventReply> page = ccmWechatEventReplyService.findPage(new Page<CcmWechatEventReply>(request, response), ccmWechatEventReply); 
		model.addAttribute("page", page);
		
		if("chatEvent".equals(ccmWechatEventReply.getType())){
			model.addAttribute("ccmWechatEventReply", ccmWechatEventReply);
			return "ccm/event/wechat/ccmWechatEventReplyListEvent";
		}
		return "ccm/event/wechat/ccmWechatEventReplyList";
	}

	@RequiresPermissions("event:wechat:ccmWechatEventReply:view")
	@RequestMapping(value = "form")
	public String form(CcmWechatEventReply ccmWechatEventReply, Model model) {
		model.addAttribute("ccmWechatEventReply", ccmWechatEventReply);
		if("chatEvent".equals(ccmWechatEventReply.getType())){
			return "ccm/event/wechat/ccmWechatEventReplyFormEvent";
		}
		return "ccm/event/wechat/ccmWechatEventReplyForm";
	}

	@RequiresPermissions("event:wechat:ccmWechatEventReply:edit")
	@RequestMapping(value = "save")
	public String save(CcmWechatEventReply ccmWechatEventReply, Model model, RedirectAttributes redirectAttributes) throws Exception {
		if (!beanValidator(model, ccmWechatEventReply)){
			return form(ccmWechatEventReply, model);
		}
		String reply_time = DateUtils.formatDateTime(ccmWechatEventReply.getReplyTime());
		String param = "event_id="+ccmWechatEventReply.getEvent().getId()+"&reply_user_id="+UserUtils.getUser().getId()+"&reply_info="+ccmWechatEventReply.getReplyInfo()+"&reply_time="+reply_time;
		String result = sendPost("http://www.zswjw.net/wx/reply",param);
		if(result == null){
			ccmWechatEventReply.setStatus("02");
		}else{
			ccmWechatEventReply.setStatus("01");
		}
		ccmWechatEventReplyService.save(ccmWechatEventReply);
		CcmWechatEvent ccmWechatEvent = ccmWechatEventService.get(ccmWechatEventReply.getEvent().getId());
		if("02".equals(ccmWechatEvent.getState())){
			ccmWechatEvent.setState("03");
			ccmWechatEventService.save(ccmWechatEvent);
		}
		if(result == null){
			addMessage(redirectAttributes, "微信回复发送失败，请重新保存");
		}else{
			addMessage(redirectAttributes, "微信回复发送成功");
		}
		
		
		if("chatEvent".equals(ccmWechatEventReply.getType())){
			return "redirect:"+Global.getAdminPath()+"/event/wechat/ccmWechatEventReply/?event.id="+ccmWechatEventReply.getEvent().getId()+"&type=chatEvent";
		}
		return "redirect:"+Global.getAdminPath()+"/event/wechat/ccmWechatEventReply/?repage";
	}
	
     /**
      * 向指定 URL 发送POST方法的请求
      * 
      * @param url
      *            发送请求的 URL
      * @param param
      *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
      * @return 所代表远程资源的响应结果
      */
     public String sendPost(String url, String param) {
         OutputStreamWriter out = null;
         BufferedReader in = null;
         String result = "";
         try {
             URL realUrl = new URL(url);
             HttpURLConnection conn = null;
             conn = (HttpURLConnection) realUrl.openConnection();
             // 打开和URL之间的连接
 
             // 发送POST请求必须设置如下两行
             conn.setDoOutput(true);
             conn.setDoInput(true);
             conn.setRequestMethod("POST");    // POST方法
 
 
             // 设置通用的请求属性
 
             conn.setRequestProperty("accept", "*/*");
             conn.setRequestProperty("connection", "Keep-Alive");
             conn.setRequestProperty("user-agent",
                     "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
             conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
 
             conn.connect();

             // 获取URLConnection对象对应的输出流
             out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
             // 发送请求参数
             out.write(param);
             // flush输出流的缓冲
             out.flush();
             // 定义BufferedReader输入流来读取URL的响应
             if (conn.getResponseCode() == 200) {  
	             in = new BufferedReader(
	                     new InputStreamReader(conn.getInputStream()));
	             String line;
	             while ((line = in.readLine()) != null) {
	                 result += line;
	             }
             }else{
            	 return null;
             }
         } catch (Exception e) {
             System.out.println("发送 POST 请求出现异常！"+e);
             e.printStackTrace();
             return null;
         }
         //使用finally块来关闭输出流、输入流
         finally{
             try{
                 if(out!=null){
                     out.close();
                 }
                 if(in!=null){
                     in.close();
                 }
             }
             catch(IOException ex){
                 ex.printStackTrace();
             }
         }
         return result;
     }

     /**
      * 向指定URL发送GET方法的请求
      * 
      * @param url
      *            发送请求的URL
      * @param param
      *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
      * @return result 所代表远程资源的响应结果
      */
     public String sendGet(String url, String param) {
         String result = "";
         BufferedReader in = null;
         try {
             String urlNameString = url + "?" + param;
             URL realUrl = new URL(urlNameString);
             // 打开和URL之间的连接
             URLConnection connection = realUrl.openConnection();
             // 设置通用的请求属性
             connection.setRequestProperty("accept", "*/*");
             connection.setRequestProperty("connection", "Keep-Alive");
             connection.setRequestProperty("user-agent",
                     "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
             // 建立实际的连接
             connection.connect();
             // 获取所有响应头字段
             Map<String, List<String>> map = connection.getHeaderFields();
             // 遍历所有的响应头字段
             for (String key : map.keySet()) {
                 System.out.println(key + "--->" + map.get(key));
             }
             // 定义 BufferedReader输入流来读取URL的响应
             in = new BufferedReader(new InputStreamReader(
                     connection.getInputStream()));
             String line;
             while ((line = in.readLine()) != null) {
                 result += line;
             }
         } catch (Exception e) {
             System.out.println("发送GET请求出现异常！" + e);
             e.printStackTrace();
         }
         // 使用finally块来关闭输入流
         finally {
             try {
                 if (in != null) {
                     in.close();
                 }
             } catch (Exception e2) {
                 e2.printStackTrace();
             }
         }
         return result;
     }
	
	
	
	@RequiresPermissions("event:wechat:ccmWechatEventReply:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmWechatEventReply ccmWechatEventReply, RedirectAttributes redirectAttributes) {
		ccmWechatEventReplyService.delete(ccmWechatEventReply);
		addMessage(redirectAttributes, "删除微信回复成功");
		return "redirect:"+Global.getAdminPath()+"/event/wechat/ccmWechatEventReply/?repage";
	}

}