package com.arjjs.ccm.modules.flat.tree.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.utils.SpringContextHolder;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgTeam;
import com.arjjs.ccm.modules.flat.alarm.service.BphAlarmInfoService;
import com.arjjs.ccm.modules.flat.handle.entity.BphAlarmHandleUserState;
import com.arjjs.ccm.modules.flat.handle.service.BphAlarmHandleService;
import com.arjjs.ccm.modules.flat.realtimeSituation.entity.PeopleData;
import com.arjjs.ccm.modules.flat.rest.service.FlatRestService;
import com.arjjs.ccm.modules.flat.tree.dao.FlatTreeDao;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.LayUIBean;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
@Transactional
public class FlatTreeService {
	@Autowired
	FlatTreeDao flatTreeDao;
	public LayUIBean officeAndUserTreeData() {
		LayUIBean result = new LayUIBean();
		result.setCode("0");
		result.setMsg("操作成功");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		BphAlarmInfoService bean = SpringContextHolder.getBean("bphAlarmInfoService");
		List<Office> list = bean.findOfficeAllList(null);
		list.forEach(office -> {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", office.getId());
			map.put("title", office.getName());
			List<Map<String, Object>> checkArrList = Lists.newArrayList();
			Map<String, Object> checkArrMap = Maps.newHashMap();
			checkArrMap.put("type", "0");
			checkArrMap.put("isChecked", "0");
			checkArrList.add(checkArrMap);
			map.put("checkArr", checkArrList);
			map.put("parentId", office.getParentId());
			map.put("basicData", "office");
			mapList.add(map);
		});
		List<CcmOrgTeam> userList = flatTreeDao.findAllUser();
		List<User> onlineUsers = UserUtils.getOnlineUsers();
		List<String> userIdList = Lists.newArrayList();
		for (int i = 0; i < onlineUsers.size(); i++) {
			userIdList.add(onlineUsers.get(i).getId());
		}
		for(Map.Entry<String, PeopleData> entry : FlatRestService.peoPleMap.entrySet()) {
			userIdList.add(entry.getValue().getUserId());
		}
		BphAlarmHandleUserState bphAlarmHandleUserState = new BphAlarmHandleUserState();
		String[] userIds = new String[userIdList.size()];
		bphAlarmHandleUserState.setUserIds(userIdList.toArray(userIds));
		BphAlarmHandleService handleBean = SpringContextHolder.getBean("bphAlarmHandleService");
		List<BphAlarmHandleUserState> findUserState = handleBean.findUserState(bphAlarmHandleUserState);
		userList.forEach(user -> {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", user.getId());
			String loginState = "offline";
			String userState = "free";
			for(User u : onlineUsers) {
				if(StringUtils.equals(user.getId(), u.getId())) {
					loginState = "online";
					for(BphAlarmHandleUserState state : findUserState) {
						if (StringUtils.equals(state.getUserId(), user.getId())) {
							userState = state.getUserState();
							break;
						}
					}
					break;
				}
			}
			for(Map.Entry<String, PeopleData> entry : FlatRestService.peoPleMap.entrySet()) {
				if(StringUtils.equals(user.getId(), entry.getValue().getUserId())) {
					loginState = "online";
					for(BphAlarmHandleUserState state : findUserState) {
						if (StringUtils.equals(state.getUserId(), user.getId())) {
							userState = state.getUserState();
							break;
						}
					}
					break;
				}
			}
			if(StringUtils.equals("online", loginState)) {
				if(StringUtils.equals("free", userState)) {
					map.put("title", user.getUser().getName() + " [备勤]");
				} else {
					map.put("title", user.getUser().getName() + " [忙碌]");
				}
			} else {
				map.put("title", user.getUser().getName() + " [离线]");
			}
			List<Map<String, Object>> checkArrList = Lists.newArrayList();
			Map<String, Object> checkArrMap = Maps.newHashMap();
			checkArrMap.put("type", "0");
			checkArrMap.put("isChecked", "0");
			checkArrList.add(checkArrMap);
			map.put("checkArr", checkArrList);
			map.put("parentId", user.getUser().getOffice().getId());
			map.put("basicData", "user");
			mapList.add(map);
		});
		result.setData(mapList);
		return result;
	}
	
	public LayUIBean officeTreeData() {
		LayUIBean result = new LayUIBean();
		result.setCode("0");
		result.setMsg("操作成功");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		BphAlarmInfoService bean = SpringContextHolder.getBean("bphAlarmInfoService");
		List<Office> list = bean.findOfficeAllList(null);
		list.forEach(office -> {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", office.getId());
			map.put("title", office.getName());
			List<Map<String, Object>> checkArrList = Lists.newArrayList();
			Map<String, Object> checkArrMap = Maps.newHashMap();
			checkArrMap.put("type", "0");
			checkArrMap.put("isChecked", "0");
			checkArrList.add(checkArrMap);
			map.put("checkArr", checkArrList);
			map.put("parentId", office.getParentId());
			map.put("basicData", office.getType());
			mapList.add(map);
		});
		result.setData(mapList);
		return result;
	}
	
}
