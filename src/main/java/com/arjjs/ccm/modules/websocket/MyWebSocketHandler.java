package com.arjjs.ccm.modules.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.arjjs.ccm.modules.pbs.chat.service.PbsChatroomService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.arjjs.ccm.modules.websocket.bean.Message;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Socket处理器
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {
	// 用于保存HttpSession与WebSocketSession的映射关系
	public static final Map<String, WebSocketSession> userSocketSessionMap;
	// 用于保存uid与房间id的关系
	public static final Map<String, String> roomSocketSessionMap;
	
	// 聊天室信息
	@Autowired
	private PbsChatroomService pbsChatroomService;
	// 当前的 党员信息
	@Autowired
	private PbsPartymemService pbsPartymemService;
	
	static {
		userSocketSessionMap = new ConcurrentHashMap<String, WebSocketSession>();
		roomSocketSessionMap = new ConcurrentHashMap<String, String>();
	}

	/**
	 * 建立连接后,把登录用户的id写入WebSocketSession
	 */
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("进入后开始添加Map!");
		// 这个uid 是党员id
		String uid = (String) session.getAttributes().get("uid");
		String roomid = (String) session.getAttributes().get("roomid");
		// 获取当前的房间名字
		String roomName = pbsChatroomService.get(roomid).getSTitle();
		PbsPartymem partymem = pbsPartymemService.get(uid);
		String username = partymem.getSName();
		// 当前的党员添加id
		if (roomSocketSessionMap.get(uid) == null ) {
			roomSocketSessionMap.put(uid , roomid);
		}
		// 当前的党员添加seesionMap
		if (userSocketSessionMap.get(uid) == null) {
			userSocketSessionMap.put(uid, session);
			Message msg = new Message();
			msg.setFrom("0L");// 0表示上线消息
			msg.setFromid(uid);
			msg.setRoomid(roomid);
			msg.setFromName(username+"("+roomName+")");
			this.broadcast(roomid,
					new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
		}
	}

	/**
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 */
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		if (message.getPayloadLength() == 0)
			return;
		Message msg = new Gson().fromJson(message.getPayload().toString(), Message.class);
		msg.setDate(new Date());
		sendMessageToUser(msg.getTo(),
				new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
	}

	/**
	 * 消息传输错误处理
	 */
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// 移除当前抛出异常用户的Socket会话
		while (it.hasNext()) {
			Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				String roomid = (String) session.getAttributes().get("roomid");
				// 获取当前的房间名字
				String roomName = pbsChatroomService.get(roomid).getSTitle();
				System.out.println("房间id:"+roomid);
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
				PbsPartymem partymem = pbsPartymemService.get(entry.getKey());
				String username = partymem.getSName();
				Message msg = new Message();
				msg.setFrom("-2L");
				msg.setFromid(partymem.getId());
				msg.setFromName(username+"("+roomName+")");
				this.broadcast( roomid,
						new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
				break;
			}
		}
	}

	/**
	 * 关闭连接后
	 */
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.out.println("Websocket:" + session.getId() + "已经关闭");
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		// 移除当前用户的Socket会话
		while (it.hasNext()) {
			Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				roomSocketSessionMap.remove(entry.getKey());
				userSocketSessionMap.remove(entry.getKey());
				String roomid = (String) session.getAttributes().get("roomid");
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
				PbsPartymem partymem = pbsPartymemService.get(entry.getKey());
				String username = partymem.getSName();
				Message msg = new Message();
				msg.setFrom("-2L");// 下线消息，用-2表示
				msg.setFromid(partymem.getId());
				msg.setFromName(username);
				this.broadcast(roomid,
						new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
				break;
			}
		}
	}

	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给所有在线用户发送消息
	 * @param message
	 * @throws IOException
	 */
	public void broadcast(String roomid,final TextMessage message) throws IOException {
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
		Iterator<Entry<String, String>> roomit = roomSocketSessionMap.entrySet().iterator();
		// 当前的用户不需要被发送请求
		final String Uid = UserUtils.getPartymem().getId();
		System.out.println("roomid:"+roomid);
		// 房间list
		final List<String>   userList  = Lists.newArrayList();
		while (roomit.hasNext()) {
			final Entry<String, String> entry = roomit.next();
			if(entry.getValue().equals(roomid)) {
				//  用户uidList
				userList.add(entry.getKey());
			}
		}
		// 多线程群发
		while (it.hasNext()) {
			final Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().isOpen()) {
				// entry.getValue().sendMessage(message);
				new Thread(new Runnable() {
					public void run() {
						try {
							// 当前的 用户得是在该room下
							if (entry.getValue().isOpen()&& userList.contains(entry.getKey())&& (!entry.getKey().equals(Uid))) {
								System.out.println(entry.getKey()+"开始发送！！！！！！！");
								entry.getValue().sendMessage(message);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}).start();
			}
		}
	}

	/**
	 * 给某个用户发送消息
	 * @param userName
	 * @param message
	 * @throws IOException
	 */
	public void sendMessageToUser(String uid, TextMessage message) throws IOException {
		System.out.println(userSocketSessionMap.size());
		WebSocketSession session = userSocketSessionMap.get(uid);
		System.out.println("uid:" + uid);
		if (session != null && session.isOpen()) {
			session.sendMessage(message);
		}
	}

}
