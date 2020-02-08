package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestChatFriend;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestChatResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestChatUser;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestLayIMData;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestLayIMGroup;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestLayIMResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestLayIMUser;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmUserGroup;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmUserRelationship;
import com.arjjs.ccm.modules.ccm.rest.service.CcmRestOfficeService;
import com.arjjs.ccm.modules.ccm.rest.service.CcmUserGroupService;
import com.arjjs.ccm.modules.ccm.rest.service.CcmUserRelationshipService;
import com.arjjs.ccm.modules.ccm.view.entity.VCcmTeam;
import com.arjjs.ccm.modules.ccm.view.service.VCcmTeamService;
import com.arjjs.ccm.modules.sys.dao.UserDao;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.service.OfficeService;
import com.arjjs.ccm.tool.SearchTab;

/**
 * 楼栋接口类
 * 
 * @author fuxinshuang
 * @version 2018-03-08
 */
@Controller
@RequestMapping(value = "${appPath}/rest/chat")
public class CcmRestChat extends BaseController {

	@Autowired
	private CcmUserGroupService ccmUserGroupService;
	@Autowired
	private CcmUserRelationshipService ccmUserRelationshipService;
	@Autowired
	private VCcmTeamService vCcmTeamService;
	@Autowired
	private CcmRestOfficeService restOfficeService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private UserDao userDao;

	/**
	 * @see  获取单个用户群信息
	 * @param id  楼栋ID
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-08
	 */
	@ResponseBody
	@RequestMapping(value="/getGroup", method = RequestMethod.GET)
	public CcmRestResult getGroup(String userId,HttpServletRequest req, HttpServletResponse resp, String id) throws IOException {
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (id == null || "".equals(id)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		
		CcmUserGroup ccmUserGroup = ccmUserGroupService.get(id);
		String avatar = ccmUserGroup.getAvatar();
		String path = Global.getConfig("FILE_UPLOAD_URL");//文件上传存储路径
		ccmUserGroup.setAvatar(avatar!="" ? path+avatar : "");
		result.setCode(CcmRestType.OK);
		result.setResult(ccmUserGroup);
		
		return result;
	}
	
	/**
	 * @see  查询用户群信息
	 * @param name  群名称
	 * @param user.id  用户 
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-02-03
	 */
/*	@ResponseBody
	@RequestMapping(value="/queryGroup", method = RequestMethod.GET)
	public CcmRestResult queryGroup(CcmUserGroup ccmUserGroup,String userId,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		ccmUserGroup.setUser(sessionUser);
		Page<CcmUserGroup> page = ccmUserGroupService
				.findPage(new Page<CcmUserGroup>(req, resp), ccmUserGroup);
	
		result.setCode(CcmRestType.OK);
		result.setResult(page);
		
		return result;
	}*/
	
	
	/**
	 * @see  创建和修改用户群信息
	 * @param 
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-08
	 */
	@ResponseBody
	@RequestMapping(value="/saveGroup", method = RequestMethod.POST)
	public CcmRestResult saveGroup(String userId,CcmUserGroup ccmUserGroup,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		Boolean flag = true;	//是否新建
		System.out.println(ccmUserGroup.getId()+"--------"+ccmUserGroup.getIsNewRecord());
		if (ccmUserGroup.getId()!= null && !"".equals(ccmUserGroup.getId())) {
			CcmUserGroup ccmUserGroupDB = ccmUserGroupService.get(ccmUserGroup.getId());
			if (ccmUserGroupDB == null ) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			}
			if(!userId.equals(ccmUserGroupDB.getCreateBy().getId())){	//如果操作者不是群创建者，提示无权限
				result.setCode(CcmRestType.ERROR_NO_PERSSION);
				return result;
			}
			flag = false;
		}
		if (flag) {	
			ccmUserGroup.setCreateBy(new User(userId));
		}
		ccmUserGroup.setUpdateBy(new User(userId));
		ccmUserGroupService.save(ccmUserGroup);
		if (flag) {	
			CcmUserRelationship ccmUserRelationship = new CcmUserRelationship();
			ccmUserRelationship.setGroupId(ccmUserGroup.getId());
			ccmUserRelationship.setUser(new User(userId));
			ccmUserRelationship.setCreateBy(new User(userId));
			ccmUserRelationship.setUpdateBy(new User(userId));
			ccmUserRelationshipService.save(ccmUserRelationship);
		}
		result.setCode(CcmRestType.OK);
		ccmUserGroup.setType("group");
		String avatar = ccmUserGroup.getAvatar();
		String path = Global.getConfig("FILE_UPLOAD_URL");//文件上传存储路径
		ccmUserGroup.setAvatar(avatar!="" ? path+avatar : "");
		result.setResult(ccmUserGroup);
		return result;
		
	}
	/**
	 * @see  删除用户群信息
	 * @param 
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-08
	 */
	@ResponseBody
	@RequestMapping(value="/deleteGroup", method = RequestMethod.POST)
	public CcmRestResult deleteGroup(String userId,CcmUserGroup ccmUserGroup,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String creatById = null;
		if (ccmUserGroup.getId()!= null && !"".equals(ccmUserGroup.getId())) {
			CcmUserGroup ccmUserGroupDB = ccmUserGroupService.get(ccmUserGroup.getId());
			if (ccmUserGroupDB == null ) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			}
			creatById = ccmUserGroupDB.getCreateBy().getId();
		}
		
		if(!userId.equals(creatById) ){	//如果操作者不是群的创建者不能删除
			
			result.setCode(CcmRestType.ERROR_NO_PERSSION);
			return result;
		}
		
		ccmUserGroupService.delete(ccmUserGroup);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	/**
	 * @see  获取单个群好友信息
	 * @param id  楼栋ID
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-08
	 *//*
	@ResponseBody
	@RequestMapping(value="/getRelationship", method = RequestMethod.GET)
	public CcmRestResult getRelationship(String userId,HttpServletRequest req, HttpServletResponse resp, String id) throws IOException {
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (id == null || "".equals(id)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		
		CcmUserRelationship ccmUserRelationship = ccmUserRelationshipService.get(id);
		
		result.setCode(CcmRestType.OK);
		result.setResult(ccmUserRelationship);
		
		return result;
	}
	
	*//**
	 * @see  查询单个群所有好友信息
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-08
	 */
	@ResponseBody
	@RequestMapping(value="/queryRelationship", method = RequestMethod.GET)
	public CcmRestResult queryRelationship(CcmUserRelationship ccmUserRelationship,String userId,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		CcmUserGroup ccmUserGroup = new CcmUserGroup();
		ccmUserGroup.setId(ccmUserRelationship.getGroupId());
		List<VCcmTeam> vCcmTeamList = ccmUserRelationshipService.findTeamListByGroup(ccmUserGroup);	//查询群组下所有用户
		List<CcmRestChatUser> list = new ArrayList<>();	 //将用户信息格式化
		for (VCcmTeam vCcmTeam2 : vCcmTeamList) {
			String photo = vCcmTeam2.getPhoto();
			String path = Global.getConfig("FILE_UPLOAD_URL");//文件上传存储路径
			String avatar = photo!="" ? path + photo : "";
			CcmRestChatUser friendUser = new CcmRestChatUser(vCcmTeam2.getName(),vCcmTeam2.getId(),vCcmTeam2.getStatus(),vCcmTeam2.getRemarks(),avatar); //user字段名格式化
			list.add(friendUser);
		}
		result.setCode(CcmRestType.OK);
		result.setResult(list);
		
		return result;
	}
	
	
	/**
	 * @see  添加群好友信息
	 * @param 
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-08
	 */
	@ResponseBody
	@RequestMapping(value="/saveRelationship", method = RequestMethod.POST)
	public CcmRestResult saveRelationship(String userId,CcmUserRelationship ccmUserRelationship,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmUserRelationship.getGroupId() != null && !"".equals(ccmUserRelationship.getGroupId())) {
			CcmUserGroup ccmUserGroupDB = ccmUserGroupService.get(ccmUserRelationship.getGroupId());
			if (ccmUserGroupDB == null ) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			}
		}
		for (String userId2 : ccmUserRelationship.getUserIdList()) {
			User userDB = userDao.get(userId2);
			if(userDB == null){
				result.setCode(CcmRestType.ERROR_NO_USER);
				return result;
			}
			CcmUserRelationship ccmUserRelationship2 = new CcmUserRelationship();
			ccmUserRelationship2.setGroupId(ccmUserRelationship.getGroupId());
			ccmUserRelationship2.setUser(new User(userId2));
			ccmUserRelationship2.setCreateBy(new User(userId));
			ccmUserRelationship2.setUpdateBy(new User(userId));
			ccmUserRelationshipService.save(ccmUserRelationship2);
		}
		
		
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	/**
	 * @see  删除群好友信息
	 * @param 
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-08
	 */
	@ResponseBody
	@RequestMapping(value="/deleteRelationship", method = RequestMethod.POST)
	public CcmRestResult deleteRelationship(String userId,CcmUserRelationship ccmUserRelationship,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String creatById = null;
		if (ccmUserRelationship.getGroupId() != null && !"".equals(ccmUserRelationship.getGroupId())) {
			CcmUserGroup ccmUserGroupDB = ccmUserGroupService.get(ccmUserRelationship.getGroupId());
			if (ccmUserGroupDB == null ) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			}
			creatById = ccmUserGroupDB.getCreateBy().getId();	//获取群创建者Id
		}
		for (String userId2 : ccmUserRelationship.getUserIdList()) {
			User userDB = userDao.get(userId2);
			if(userDB == null){
				result.setCode(CcmRestType.ERROR_NO_USER);
				return result;
			}
			if(!userId.equals(userId2) && !userId.equals(creatById) ){	//如果操作者不是群的创建者，只能删除自己，不能删除其他群成员
				
				result.setCode(CcmRestType.ERROR_NO_PERSSION);
				return result;
			}
			CcmUserRelationship ccmUserRelationship2 = new CcmUserRelationship();
			ccmUserRelationship2.setGroupId(ccmUserRelationship.getGroupId());
			ccmUserRelationship2.setUser(new User(userId2));
			ccmUserRelationshipService.deleteByGroupAndUser(ccmUserRelationship2);
		}
		
		
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	/**
	 * @see  查询我的好友信息
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-08
	 */
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value="/queryMyFriend", method = RequestMethod.GET)
	public CcmRestResult query(String userId,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		/*if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}*/
		CcmRestChatResult ccmRestChatResult = new CcmRestChatResult();  //输出要求结果
		VCcmTeam vCcmTeam = vCcmTeamService.get(userId);
		String photo = vCcmTeam.getPhoto();
		String path = Global.getConfig("FILE_UPLOAD_URL");//文件上传存储路径
		String avatar = photo!="" ? path + photo :"";
		CcmRestChatUser ccmRestChatUser = new CcmRestChatUser(vCcmTeam.getName(),vCcmTeam.getId(),vCcmTeam.getStatus(),vCcmTeam.getRemarks(),avatar); //user字段名格式化
		ccmRestChatResult.setMine(ccmRestChatUser);  //我的信息存储入最终结果
		Office office = officeService.get(vCcmTeam.getCompanyId());	//获取用户所在机构
		Office officep = new Office();
		officep.setParent(office);
		List<Office> officelist = restOfficeService.findList(officep);	//查询用户所在机构下一级部门
		List<CcmRestChatFriend> chatFriendList = new ArrayList<>();   //将下一级部门作为好友分组信息并格式化
		for (Office office2 : officelist) {
			CcmRestChatFriend chatFriend = new CcmRestChatFriend(office2.getName(),office2.getId()); //分组信息字段名格式化
			List<VCcmTeam> vCcmTeamList =  vCcmTeamService.findByOfficeParentIdsLike(office2);	//查询当前分组下所有用户信息
			List<CcmRestChatUser> list = new ArrayList<>();	 //将用户信息格式化
			for (VCcmTeam vCcmTeam2 : vCcmTeamList) {
				photo = vCcmTeam2.getPhoto();
				avatar = path + photo;
				CcmRestChatUser friendUser = new CcmRestChatUser(vCcmTeam2.getName(),vCcmTeam2.getId(),vCcmTeam2.getStatus(),vCcmTeam2.getRemarks(),avatar); //user字段名格式化
				list.add(friendUser);
			}
			chatFriend.setList(list);   //向分组存储入最终好友list
			chatFriendList.add(chatFriend);  //将格式化好的分组+好友信息 存入分组list
		}
		ccmRestChatResult.setFriend(chatFriendList);
		
		List<CcmUserGroup> ccmUserGroupList= ccmUserGroupService.findListByUserId(userId);	//查询当前用户所有群组
		for (CcmUserGroup ccmUserGroup : ccmUserGroupList) {
			List<VCcmTeam> vCcmTeamList = ccmUserRelationshipService.findTeamListByGroup(ccmUserGroup);	//查询群组下所有用户
			List<CcmRestChatUser> list = new ArrayList<>();	 //将用户信息格式化
			for (VCcmTeam vCcmTeam2 : vCcmTeamList) {
				photo = vCcmTeam2.getPhoto();
				avatar = path + photo;
				CcmRestChatUser friendUser = new CcmRestChatUser(vCcmTeam2.getName(),vCcmTeam2.getId(),vCcmTeam2.getStatus(),vCcmTeam2.getRemarks(),avatar); //user字段名格式化
				list.add(friendUser);
			}
			ccmUserGroup.setList(list);
			String groupAvatar = ccmUserGroup.getAvatar();
			ccmUserGroup.setAvatar(path + groupAvatar);
		}
		ccmRestChatResult.setGroup(ccmUserGroupList);
		
		
		result.setCode(CcmRestType.OK);
		result.setResult(ccmRestChatResult);
		
		return result;
		
	}
	/**
	 * @see  分页查询我的好友信息
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-08
	 */
	@ResponseBody
	@RequestMapping(value="/queryMyFriendPage", method = RequestMethod.GET)
	public CcmRestResult queryMyFriendPage(String userId,String name,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		VCcmTeam vCcmTeam = vCcmTeamService.get(userId);
		VCcmTeam vCcmTeam2 = new VCcmTeam();
		vCcmTeam2.setCompanyId(vCcmTeam.getCompanyId());
		vCcmTeam2.setName(name);
		Page<VCcmTeam> page = vCcmTeamService.findPage(new Page<VCcmTeam>(req, resp), vCcmTeam2); 
		List<VCcmTeam> vCcmTeamList = page.getList();
		List<VCcmTeam> vCcmTeamListNew = new ArrayList<>();
		for (VCcmTeam vCcmTeam3 : vCcmTeamList) {
			String photo = vCcmTeam3.getPhoto();
			String path = Global.getConfig("FILE_UPLOAD_URL");//文件上传存储路径
			vCcmTeam3.setPhoto(photo!="" ? path+photo : "");
			vCcmTeamListNew.add(vCcmTeam3);
		}
		page.setList(vCcmTeamListNew);
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
		
	}
	/**
	 * @see  分页查询我的好友信息（不包含当前群中已存在的群成员，用户群成员添加）
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-08
	 */
	@ResponseBody
	@RequestMapping(value="/queryMyFriendAvailable", method = RequestMethod.GET)
	public CcmRestResult queryMyFriendAvailable(String groupId,String userId,String name,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		VCcmTeam vCcmTeam = vCcmTeamService.get(userId);
		VCcmTeam vCcmTeam2 = new VCcmTeam();
		vCcmTeam2.setCompanyId(vCcmTeam.getCompanyId());
		vCcmTeam2.setName(name);
		vCcmTeam2.setGroupId(groupId);
		Page<VCcmTeam> page = vCcmTeamService.findMyFriendAvailablePage(new Page<VCcmTeam>(req, resp), vCcmTeam2); 
		List<VCcmTeam> vCcmTeamList = page.getList();
		List<VCcmTeam> vCcmTeamListNew = new ArrayList<>();
		for (VCcmTeam vCcmTeam3 : vCcmTeamList) {
			String photo = vCcmTeam3.getPhoto();
			String path = Global.getConfig("FILE_UPLOAD_URL");//文件上传存储路径
			vCcmTeam3.setPhoto(photo!="" ? path+photo : "");
			vCcmTeamListNew.add(vCcmTeam3);
		}
		page.setList(vCcmTeamListNew);
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
		
	}


	// 新密现场添加 派出所-警员用户  pengjianqiang 20180919
	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public CcmRestLayIMResult getList(String id) {
		if (id== null || "".equals(id)) {
			return null;
		}
		VCcmTeam vCcmTeam = vCcmTeamService.get(id);
		
		CcmRestLayIMResult restLayIMResult = new CcmRestLayIMResult();
		CcmRestLayIMUser mine = new CcmRestLayIMUser(vCcmTeam.getName(),vCcmTeam.getId(),"online",vCcmTeam.getOffice().getName(),vCcmTeam.getPhoto());

		List<CcmRestLayIMGroup> friend = new ArrayList<CcmRestLayIMGroup>();
		
		List<SearchTab> office12 = vCcmTeamService.findByTypes();//所有的type=2的结构（新密：派出所）
		for (SearchTab searchTab : office12) {
			List<CcmRestLayIMUser> listUser = new ArrayList<CcmRestLayIMUser>();
			VCcmTeam vCcmTeam2 = new VCcmTeam();
			vCcmTeam2.setMore1(searchTab.getValue1());//派出所部门Id
			List<VCcmTeam> vCcmTeamList =  vCcmTeamService.findUserByOffice(vCcmTeam2);	//查询当前派出所下所有用户信息，递归至警务站
			for (VCcmTeam vCcmTeam3 : vCcmTeamList) {
				CcmRestLayIMUser u = new CcmRestLayIMUser(vCcmTeam3.getName(), vCcmTeam3.getId(), vCcmTeam3.getStatus(), vCcmTeam3.getMore1(), vCcmTeam3.getPhoto());
				listUser.add(u);
			}
			CcmRestLayIMGroup g = new CcmRestLayIMGroup(searchTab.getValue2(), searchTab.getValue1(), "1", listUser);

			friend.add(g);
			
		}
		CcmRestLayIMData data = new CcmRestLayIMData(mine, friend);
		
		
		restLayIMResult.setCode("0");
		restLayIMResult.setMsg("");
		restLayIMResult.setData(data);
		
		return restLayIMResult;
	}

	// 测试假数据
	@ResponseBody
	@RequestMapping(value = "/getMembers", method = RequestMethod.GET)
	public String getMembers() {
		String ss = "";
		//ss = "{  \\\"code\\\": 0  ,\\\"msg\\\": \\\"\\\"  ,\\\"data\\\": {    \\\"owner\\\": {      \\\"username\\\": \\\"贤心\\\"      ,\\\"id\\\": \\\"100001\\\"      ,\\\"avatar\\\": \\\"http://tp1.sinaimg.cn/1571889140/180/40030060651/1\\\"      ,\\\"sign\\\": \\\"这些都是测试数据，实际使用请严格按照该格式返回\\\"    }    ,\\\"members\\\": 12    ,\\\"list\\\": [{      \\\"username\\\": \\\"贤心\\\"      ,\\\"id\\\": \\\"100001\\\"      ,\\\"avatar\\\": \\\"http://tp1.sinaimg.cn/1571889140/180/40030060651/1\\\"      ,\\\"sign\\\": \\\"这些都是测试数据，实际使用请严格按照该格式返回\\\"    },{      \\\"username\\\": \\\"Z_子晴\\\"      ,\\\"id\\\": \\\"108101\\\"      ,\\\"avatar\\\": \\\"http://tva3.sinaimg.cn/crop.0.0.512.512.180/8693225ajw8f2rt20ptykj20e80e8weu.jpg\\\"      ,\\\"sign\\\": \\\"微电商达人\\\"    },{      \\\"username\\\": \\\"Lemon_CC\\\"      ,\\\"id\\\": \\\"102101\\\"      ,\\\"avatar\\\": \\\"http://tp2.sinaimg.cn/1833062053/180/5643591594/0\\\"      ,\\\"sign\\\": \\\"\\\"    },{      \\\"username\\\": \\\"马小云\\\"      ,\\\"id\\\": \\\"168168\\\"      ,\\\"avatar\\\": \\\"http://tp4.sinaimg.cn/2145291155/180/5601307179/1\\\"      ,\\\"sign\\\": \\\"让天下没有难写的代码\\\"    },{      \\\"username\\\": \\\"徐小峥\\\"      ,\\\"id\\\": \\\"666666\\\"      ,\\\"avatar\\\": \\\"http://tp2.sinaimg.cn/1783286485/180/5677568891/1\\\"      ,\\\"sign\\\": \\\"代码在囧途，也要写到底\\\"    },{      \\\"username\\\": \\\"罗玉凤\\\"      ,\\\"id\\\": \\\"121286\\\"      ,\\\"avatar\\\": \\\"http://tp1.sinaimg.cn/1241679004/180/5743814375/0\\\"      ,\\\"sign\\\": \\\"在自己实力不济的时候，不要去相信什么媒体和记者。他们不是善良的人，有时候候他们的采访对当事人而言就是陷阱\\\"    },{      \\\"username\\\": \\\"长泽梓Azusa\\\"      ,\\\"id\\\": \\\"100001222\\\"      ,\\\"avatar\\\": \\\"http://tva1.sinaimg.cn/crop.0.0.180.180.180/86b15b6cjw1e8qgp5bmzyj2050050aa8.jpg\\\"      ,\\\"sign\\\": \\\"我是日本女艺人长泽あずさ\\\"    },{      \\\"username\\\": \\\"大鱼_MsYuyu\\\"      ,\\\"id\\\": \\\"12123454\\\"      ,\\\"avatar\\\": \\\"http://tp1.sinaimg.cn/5286730964/50/5745125631/0\\\"      ,\\\"sign\\\": \\\"我瘋了！這也太準了吧  超級笑點低\\\"    },{      \\\"username\\\": \\\"谢楠\\\"      ,\\\"id\\\": \\\"10034001\\\"      ,\\\"avatar\\\": \\\"http://tp4.sinaimg.cn/1665074831/180/5617130952/0\\\"      ,\\\"sign\\\": \\\"\\\"    },{      \\\"username\\\": \\\"柏雪近在它香\\\"      ,\\\"id\\\": \\\"3435343\\\"      ,\\\"avatar\\\": \\\"http://tp2.sinaimg.cn/2518326245/180/5636099025/0\\\"      ,\\\"sign\\\": \\\"\\\"    },{      \\\"username\\\": \\\"林心如\\\"      ,\\\"id\\\": \\\"76543\\\"      ,\\\"avatar\\\": \\\"http://tp3.sinaimg.cn/1223762662/180/5741707953/0\\\"      ,\\\"sign\\\": \\\"我爱贤心\\\"    },{      \\\"username\\\": \\\"佟丽娅\\\"      ,\\\"id\\\": \\\"4803920\\\"      ,\\\"avatar\\\": \\\"http://tp4.sinaimg.cn/1345566427/180/5730976522/0\\\"      ,\\\"sign\\\": \\\"我也爱贤心吖吖啊\\\"    }]  }}";
		ss = "{  \"code\": 0  ,\"msg\": \"\"  ,\"data\": {    \"owner\": {      \"username\": \"贤心\"      ,\"id\": \"100001\"      ,\"avatar\": \"http://tp1.sinaimg.cn/1571889140/180/40030060651/1\"      ,\"sign\": \"这些都是测试数据，实际使用请严格按照该格式返回\"    }    ,\"members\": 12    ,\"list\": [{      \"username\": \"贤心\"      ,\"id\": \"100001\"      ,\"avatar\": \"http://tp1.sinaimg.cn/1571889140/180/40030060651/1\"      ,\"sign\": \"这些都是测试数据，实际使用请严格按照该格式返回\"    },{      \"username\": \"Z_子晴\"      ,\"id\": \"108101\"      ,\"avatar\": \"http://tva3.sinaimg.cn/crop.0.0.512.512.180/8693225ajw8f2rt20ptykj20e80e8weu.jpg\"      ,\"sign\": \"微电商达人\"    },{      \"username\": \"Lemon_CC\"      ,\"id\": \"102101\"      ,\"avatar\": \"http://tp2.sinaimg.cn/1833062053/180/5643591594/0\"      ,\"sign\": \"\"    },{      \"username\": \"马小云\"      ,\"id\": \"168168\"      ,\"avatar\": \"http://tp4.sinaimg.cn/2145291155/180/5601307179/1\"      ,\"sign\": \"让天下没有难写的代码\"    },{      \"username\": \"徐小峥\"      ,\"id\": \"666666\"      ,\"avatar\": \"http://tp2.sinaimg.cn/1783286485/180/5677568891/1\"      ,\"sign\": \"代码在囧途，也要写到底\"    },{      \"username\": \"罗玉凤\"      ,\"id\": \"121286\"      ,\"avatar\": \"http://tp1.sinaimg.cn/1241679004/180/5743814375/0\"      ,\"sign\": \"在自己实力不济的时候，不要去相信什么媒体和记者。他们不是善良的人，有时候候他们的采访对当事人而言就是陷阱\"    },{      \"username\": \"长泽梓Azusa\"      ,\"id\": \"100001222\"      ,\"avatar\": \"http://tva1.sinaimg.cn/crop.0.0.180.180.180/86b15b6cjw1e8qgp5bmzyj2050050aa8.jpg\"      ,\"sign\": \"我是日本女艺人长泽あずさ\"    },{      \"username\": \"大鱼_MsYuyu\"      ,\"id\": \"12123454\"      ,\"avatar\": \"http://tp1.sinaimg.cn/5286730964/50/5745125631/0\"      ,\"sign\": \"我瘋了！這也太準了吧  超級笑點低\"    },{      \"username\": \"谢楠\"      ,\"id\": \"10034001\"      ,\"avatar\": \"http://tp4.sinaimg.cn/1665074831/180/5617130952/0\"      ,\"sign\": \"\"    },{      \"username\": \"柏雪近在它香\"      ,\"id\": \"3435343\"      ,\"avatar\": \"http://tp2.sinaimg.cn/2518326245/180/5636099025/0\"      ,\"sign\": \"\"    },{      \"username\": \"林心如\"      ,\"id\": \"76543\"      ,\"avatar\": \"http://tp3.sinaimg.cn/1223762662/180/5741707953/0\"      ,\"sign\": \"我爱贤心\"    },{      \"username\": \"佟丽娅\"      ,\"id\": \"4803920\"      ,\"avatar\": \"http://tp4.sinaimg.cn/1345566427/180/5730976522/0\"      ,\"sign\": \"我也爱贤心吖吖啊\"    }]  }}";
		
		
		return ss;
	}

}