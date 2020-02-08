package com.arjjs.ccm.modules.im.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmUserGroup;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmUserRelationship;
import com.arjjs.ccm.modules.ccm.rest.service.CcmUserGroupService;
import com.arjjs.ccm.modules.ccm.rest.service.CcmUserRelationshipService;
import com.arjjs.ccm.modules.im.entity.ImParamData;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.Pager;
import com.arjjs.ccm.tool.Tool;
import com.google.common.collect.Lists;

import net.sf.json.JSONObject;

@Service
@Transactional
public class BphImService {
	
	@Autowired
	private CcmUserGroupService ccmUserGroupService;
	@Autowired
	private CcmUserRelationshipService ccmUserRelationshipService;
	
	public Pager historymessage(ImParamData paramData) {
		String url = Global.getConfig("arjimRest") + "historymessage";
		String param = "?userId="+paramData.getUserId()+"&id="+paramData.getId()+"&type="+paramData.getType()+"&skipToPage="+paramData.getSkipToPage();
		String resultStr = Tool.getRestReturn(url+param);
		resultStr = resultStr.substring(1, resultStr.length() - 1);
		resultStr = resultStr.replace("\\", "");
		JSONObject jsonObject = JSONObject.fromObject(resultStr);
		Pager pager = new Pager(Integer.valueOf((String) jsonObject.getString("skipToPage")), Integer.valueOf((String) jsonObject.getString("pageSize")), Integer.valueOf((String) jsonObject.getString("totalsize")));
		return pager;
	}
	
	/**
	 * 方法说明：创建群组
	 * @param ccmUserGroup
	 */
	public void userGroup(CcmUserGroup ccmUserGroup) {
		ccmUserGroup.setGroupOwnerId(UserUtils.getUser().getId());
		ccmUserGroupService.save(ccmUserGroup);
		String userStr = ccmUserGroup.getUserList()+UserUtils.getUser().getId();
		if(StringUtils.isNotBlank(userStr)) {
			addUser(ccmUserGroup.getId(),userStr);
		}
	}
	
	/**
	 * 方法说明：群组添加人员
	 * @param userStr
	 */
	public void addUser(String groupId,String userStr) {
		List<String> userList = Arrays.asList(userStr.split(","));
		List<String> unique = userList.stream().distinct().collect(Collectors.toList());
		List<CcmUserRelationship> ccmUserRelationshipList = Lists.newArrayList();
		for(int i = 0;i < unique.size();i++) {
			CcmUserRelationship ccmUserRelationship = new CcmUserRelationship();
			User user = new User();
			user.setId(unique.get(i));
			ccmUserRelationship.setId(UUID.randomUUID().toString());
			ccmUserRelationship.setCreateBy(UserUtils.getUser());
			ccmUserRelationship.setCreateDate(new Date());
			ccmUserRelationship.setUpdateBy(UserUtils.getUser());
			ccmUserRelationship.setUpdateDate(new Date());
			ccmUserRelationship.setGroupId(groupId);
			ccmUserRelationship.setUser(user);
			ccmUserRelationshipList.add(ccmUserRelationship);
		}
		ccmUserRelationshipService.insertUserRelationship(ccmUserRelationshipList);
	}
}
