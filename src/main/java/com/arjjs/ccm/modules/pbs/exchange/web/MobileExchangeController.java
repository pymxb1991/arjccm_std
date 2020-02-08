/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.exchange.web;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.chat.entity.PbsChatroom;
import com.arjjs.ccm.modules.pbs.chat.service.PbsChatroomService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.arjjs.ccm.modules.websocket.MyWebSocketHandler;
import com.arjjs.ccm.modules.websocket.bean.Message;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.GsonBuilder;

/**
 * 微信端工作安排记录Controller
 * 
 * @author lc
 * @version 2018-05-03
 */
@Controller
@RequestMapping(value = "${adminPath}/exchange/deemo")
public class MobileExchangeController extends BaseController {

	// 党员信息
	@Autowired
	private PbsPartymemService PbsPartymemService;
	// 聊天室信息
	@Autowired
	private PbsChatroomService pbsChatroomService;
	// socket 接口
	@Autowired
	private MyWebSocketHandler handler;
	
	private final static String ROOMDEFAULT = "99";
	
	@RequestMapping(value = "pageList")
	public String dealtMyList(HttpSession httpSession, HttpServletRequest request, Model model,
			@RequestParam("roomid") String roomid) {
		model.addAttribute("bathpath", request.getServerName() + ":" + request.getServerPort());
		if (StringUtils.isNoneBlank(UserUtils.getPartymem().getSName())) {
			model.addAttribute("parmember", UserUtils.getPartymem());
			// 尝试获取当前的党员信息
			PbsPartymem mem = UserUtils.getPartymem();
			System.out.println("当前的党员信息:" + mem.getId());
			if (StringUtils.isNoneBlank(mem.getId())) {
				httpSession.setAttribute("username", mem.getSName());
				httpSession.setAttribute("uid", mem.getId());
				httpSession.setAttribute("roomid", roomid);
			}
			return "/pbs/exchange/chatroom";
		} else {
			return "/";
		}
	}

	// 微信端 页面
	@RequestMapping(value = "chat")
	public String chat(HttpSession httpSession, HttpServletRequest request, Model model,String roomid) {
		// 返回 websocket 接口信息
		model.addAttribute("bathpath", request.getServerName() + ":" + request.getServerPort());
		// 必须是党员才能进入
		if (StringUtils.isNoneBlank(UserUtils.getPartymem().getSName())) {
			model.addAttribute("parmember", UserUtils.getPartymem());
			// 尝试获取当前的党员信息
			PbsPartymem mem = UserUtils.getPartymem();
			System.out.println("当前的党员信息:" + mem.getId());
			if (StringUtils.isNoneBlank(mem.getId())) {
				httpSession.setAttribute("username", mem.getSName());
				httpSession.setAttribute("uid", mem.getId());
				// 默认room 为 大厅
				httpSession.setAttribute("roomid", StringUtils.isEmpty(roomid)?ROOMDEFAULT:roomid);
			}
			return "/chat/chat";
		} else {
			return "/";
		}
	}
	
	
	
	// 微信端查询 当前房屋的用户
	@ResponseBody
	@RequestMapping(value = "roomusers")
	public String roomusers(HttpSession httpSession, HttpServletRequest request, Model model,
			@RequestParam("roomid") String roomid) { 
		
		
		
		return SUCCESS;
	}
	 

	@RequestMapping("/onlineusers")
	@ResponseBody
	public List<Message> onlineusers(HttpSession session) {
		// seesionMap
		Map<String, WebSocketSession> map = MyWebSocketHandler.userSocketSessionMap;
		Map<String,String> roomMap = MyWebSocketHandler.roomSocketSessionMap;
		// 查询所有的房间信息
		List<PbsChatroom> chatrooms = pbsChatroomService.findList(new PbsChatroom());
		// 循环的maps信息
		Map<String,PbsChatroom> roomMaps= getChatRoom(chatrooms);
		String roomid = (String) session.getAttribute("roomid");
		// 实际用户
		Set<String> userset = map.keySet();
		Iterator<String> userit = userset.iterator();
		// 	返回的Message list
		List<Message> msgset =Lists.newArrayList();
		// 循环房屋信息添加
		for(PbsChatroom chatroom :chatrooms) {
			Message msg = new Message();
			msg.setFromid(chatroom.getId());
			// 是否已经进入
			if(roomid.equals(chatroom.getId())) {
				msg.setFromName(chatroom.getSTitle()+"(已进入)");
				msg.setGoflag(true);
			}else {
				msg.setFromName(chatroom.getSTitle());
				msg.setGoflag(false);
			}
			msg.setFrom("RL");
			msg.setRoomid(chatroom.getId());
			msgset.add(msg);
		}
		// 党员用户的添加
		String Curuser = UserUtils.getPartymem().getSName();
		// 遍历 当前的用户
		while (userit.hasNext()) {
			String memid = userit.next();
			PbsPartymem partymem = PbsPartymemService.get(memid);
			String name = partymem.getSName();
			// 当前用户的房间id
			String roomeachid = roomMap.get(partymem.getId());
			System.out.println("当前的房间号:"+roomMap.get(partymem.getId()).equals(roomid) );
			// 非当前党员用户&& 当前的党员用户不为空
			if (!Curuser.equals(name) && (StringUtils.isNoneBlank(name))&&roomeachid.equals(roomid)) {
				Message msg = new Message();
				msg.setFromid(partymem.getId());
				msg.setFromName(partymem.getSName()+("("+roomMaps.get(roomeachid).getSTitle()+")"));
				msg.setRoomid(roomeachid);
				msg.setFrom("0L");
				msgset.add(msg);
			}
		}
		return msgset;
	}

	// 发布系统广播（群发）
	@ResponseBody
	@RequestMapping(value = "broadcast", method = RequestMethod.POST)
	public String broadcast(@RequestParam("fromName") String fromName, @RequestParam("roomid") String roomid, @RequestParam("text") String text)
			throws IOException {
		Message msg = new Message();
		msg.setDate(new Date());
		msg.setFrom("-1L");// -1表示系统广播
		msg.setFromName(fromName);
		msg.setFromid(roomid);
		msg.setTo("0L");
		msg.setText(text);
		handler.broadcast(roomid,
				new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
		return SUCCESS;
	}

	
	 // 返回map对象信息
	public static Map<String, PbsChatroom> getChatRoom(List<PbsChatroom> chatrooms){
		Map<String,PbsChatroom> maps = Maps.newConcurrentMap();
		for(PbsChatroom entry:chatrooms ) {
			maps.put(entry.getId(),entry);
		}
		return maps;
	}
	
}