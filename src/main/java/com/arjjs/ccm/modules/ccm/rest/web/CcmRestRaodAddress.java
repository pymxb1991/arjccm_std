package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmRoadAddress;
import com.arjjs.ccm.modules.ccm.pop.service.CcmRoadAddressService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 街路巷接口
 * @author pengjianqiang
 * @version 2018-02-02
 */
@Controller
@RequestMapping(value = "${appPath}/rest/ccmRoadAddress")
public class CcmRestRaodAddress extends BaseController {

	@Autowired
	private CcmRoadAddressService ccmRoadAddressService;
	
	/**
	 * @see  获取单个街路巷信息
	 * @param id  街路巷ID
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-02-02
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
		
		CcmRoadAddress ccmRoadAddress = ccmRoadAddressService.get(id);
		String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
		if(StringUtils.isNotEmpty(ccmRoadAddress.getImage())){
			ccmRoadAddress.setImage(fileUrl + ccmRoadAddress.getImage());
		}
		result.setCode(CcmRestType.OK);
		result.setResult(ccmRoadAddress);
		
		return result;
	}
	
	/**
	 * @see  查询街路巷信息所有
	 * @param name  街路巷名称
	 * @parama ddress  地址
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-02-03
	 */
	@ResponseBody
	@RequestMapping(value="/query", method = RequestMethod.GET)
	public CcmRestResult query(String userId,CcmRoadAddress ccmRoadAddress,HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
		ccmRoadAddress.setCheckUser(sessionUser);
		Page<CcmRoadAddress> page = ccmRoadAddressService
				.findPage(new Page<CcmRoadAddress>(req, resp), ccmRoadAddress);
		String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
		if(page.getList().size()>0){
			for (int i = 0; i < page.getList().size(); i++) {
				if(StringUtils.isNotEmpty(page.getList().get(i).getImage())){
					page.getList().get(i).setImage(fileUrl + page.getList().get(i).getImage());
				}
			}
		}
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	}
	
	
	/**
	 * @see  保存街路巷信息
	 * @param 
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-02-03
	 */
	@ResponseBody
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public CcmRestResult modify(String userId,CcmRoadAddress ccmRoadAddress,HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
		if (ccmRoadAddress.getId()!= null && !"".equals(ccmRoadAddress.getId())) {
			CcmRoadAddress ccmRoadAddressDB = ccmRoadAddressService.get(ccmRoadAddress.getId());
			if (ccmRoadAddressDB == null ) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			}
			String file = ccmRoadAddress.getImage();
			if(StringUtils.isNotEmpty(file)) {
				if(file.contains(Global.getConfig("FILE_UPLOAD_URL"))) {
					ccmRoadAddress.setImage(file.split(Global.getConfig("FILE_UPLOAD_URL"))[1]);
				}else {
					ccmRoadAddress.setImage(file);
				}
			}
			/*ccmRoadAddress.setAreaPoint(ccmRoadAddressDB.getAreaPoint());
			ccmRoadAddress.setAreaMap(ccmRoadAddressDB.getAreaMap());
			ccmRoadAddress.setImage(ccmRoadAddressDB.getImage());*/
		}else{
			/*ccmRoadAddress.setAreaPoint("");
			ccmRoadAddress.setAreaMap("");*/
			ccmRoadAddress.setImage(ccmRoadAddress.getImage());
		}
		if (ccmRoadAddress.getCreateBy()== null) {
			ccmRoadAddress.setCreateBy(new User(userId));
		}
		ccmRoadAddress.setUpdateBy(new User(userId));
		ccmRoadAddressService.save(ccmRoadAddress);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
	}
	/**
	 * @see  保存街路巷信息（支持新增和编辑,数据同步用）
	 * @param 
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-05-12
	 */
	@ResponseBody
	@RequestMapping(value="/saveSyn", method = RequestMethod.POST)
	public CcmRestResult saveSyn(String userId,CcmRoadAddress ccmRoadAddress,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		User user = new User(userId);
		ccmRoadAddress.setCreateBy(user);
		ccmRoadAddress.setUpdateBy(user);
		
		ccmRoadAddressService.save(ccmRoadAddress);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

	/**
	 * @see  删除街路巷信息（数据同步用）
	 * @param 
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-05-17
	 */
	@ResponseBody
	@RequestMapping(value="/deleteSyn", method = RequestMethod.POST)
	public CcmRestResult deleteSyn(String userId,CcmRoadAddress ccmRoadAddress,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		ccmRoadAddressService.delete(ccmRoadAddress);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	
	/**
	 * @see  删除街路巷信息
	 * @param 
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-05-12
	 */
	@ResponseBody
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public CcmRestResult delete(String userId,CcmRoadAddress ccmRoadAddress,HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
		if (ccmRoadAddress.getId()!= null && !"".equals(ccmRoadAddress.getId())) {
			CcmRoadAddress ccmRoadAddressDB = ccmRoadAddressService.get(ccmRoadAddress.getId());
			if (ccmRoadAddressDB == null ) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			}
		}
		ccmRoadAddressService.delete(ccmRoadAddress);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

}