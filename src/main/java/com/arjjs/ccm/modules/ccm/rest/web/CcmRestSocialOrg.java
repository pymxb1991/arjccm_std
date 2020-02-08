package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.common.config.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgSocialorg;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgSocialorgService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 社会组织接口类
 * 
 * @author pengjianqiang
 * @version 2018-02-23
 */
@Controller
@RequestMapping(value = "${appPath}/rest/socialOrg")
public class CcmRestSocialOrg extends BaseController {

	@Autowired
	private CcmOrgSocialorgService ccmOrgSocialorgService;

	/**
	 * @see  获取单个社会组织信息
	 * @param id  ID
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-02-23
	 */
	@ResponseBody
	@RequestMapping(value="/get", method = RequestMethod.GET)
	public CcmRestResult get(String userId,HttpServletRequest req, HttpServletResponse resp, String id) throws IOException {
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
		
		CcmOrgSocialorg ccmOrgSocialorg = ccmOrgSocialorgService.get(id);
		String images = ccmOrgSocialorg.getImages();
		if (StringUtils.isNotEmpty(images)) {
			String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
			ccmOrgSocialorg.setImages(fileUrl + images);
		}
		result.setCode(CcmRestType.OK);
		result.setResult(ccmOrgSocialorg);
		
		return result;
	}
	
	/**
	 * @see  查询社会组织信息
	 * @param CcmOrgSocialorg
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-02-23
	 */
	@ResponseBody
	@RequestMapping(value="/query", method = RequestMethod.GET)
	public CcmRestResult query(String userId,CcmOrgSocialorg ccmOrgSocialorg, HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
		Page<CcmOrgSocialorg> page = ccmOrgSocialorgService.findPage(new Page<CcmOrgSocialorg>(req, resp), ccmOrgSocialorg);
		String file = Global.getConfig("FILE_UPLOAD_URL");
		if(page.getList().size() > 0){
			for(CcmOrgSocialorg res : page.getList()){
				if(StringUtils.isNotEmpty(res.getImages())) {
					res.setImages(file + res.getImages());
				}
			}
		}
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	}
	
	/**
	 * @see  保存社会组织信息
	 * @param 
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-02-03
	 */
	@ResponseBody
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public CcmRestResult modify(String userId,CcmOrgSocialorg ccmOrgSocialorg, HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
		if (ccmOrgSocialorg.getId()!= null && !"".equals(ccmOrgSocialorg.getId())) {
			CcmOrgSocialorg ccmOrgSocialorgDB = ccmOrgSocialorgService.get(ccmOrgSocialorg.getId());
			if (ccmOrgSocialorgDB == null ) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			}
			ccmOrgSocialorg.setAreaPoint(ccmOrgSocialorgDB.getAreaPoint());
			ccmOrgSocialorg.setAreaMap(ccmOrgSocialorgDB.getAreaMap());
			ccmOrgSocialorg.setIcon(ccmOrgSocialorgDB.getIcon());
		}else{
			ccmOrgSocialorg.setAreaPoint("");
			ccmOrgSocialorg.setAreaMap("");
			ccmOrgSocialorg.setIcon("");
		}
		if (ccmOrgSocialorg.getCreateBy()== null) {
			ccmOrgSocialorg.setCreateBy(new User(userId));
		}
		ccmOrgSocialorg.setUpdateBy(new User(userId));
		String file = ccmOrgSocialorg.getImages();
		if(StringUtils.isNotEmpty(file)) {
			if(file.contains(Global.getConfig("FILE_UPLOAD_URL"))) {
				ccmOrgSocialorg.setImages(file.split(Global.getConfig("FILE_UPLOAD_URL"))[1]);
			}else {
				ccmOrgSocialorg.setImages(file);
			}
		}
		ccmOrgSocialorgService.save(ccmOrgSocialorg);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

	
	
	/**
	 * @see  保存社会组织信息（支持新增和编辑,数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-29
	 */
	@ResponseBody
	@RequestMapping(value="/saveSocialorgSyn", method = RequestMethod.POST)
	public CcmRestResult saveSocialorgSyn(String userId,CcmOrgSocialorg ccmOrgSocialorg,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		User user = new User(userId);
		ccmOrgSocialorg.setCreateBy(user);
		ccmOrgSocialorg.setUpdateBy(user);
		
		ccmOrgSocialorgService.save(ccmOrgSocialorg);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	/**
	 * @see  删除社会组织信息（数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-29
	 */
	@ResponseBody
	@RequestMapping(value="/deleteSocialorgSyn", method = RequestMethod.POST)
	public CcmRestResult deleteSocialorgSyn(String userId,CcmOrgSocialorg ccmOrgSocialorg,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		ccmOrgSocialorgService.delete(ccmOrgSocialorg);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

}