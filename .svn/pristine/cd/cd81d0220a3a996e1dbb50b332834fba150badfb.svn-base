package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.line.entity.CcmLineProtect;
import com.arjjs.ccm.modules.ccm.line.service.CcmLineProtectService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 护路护线接口类
 * 
 * @author pengjianqiang
 * @version 2018-02-23
 */
@Controller
@RequestMapping(value = "${appPath}/rest/line")
public class CcmRestLine extends BaseController {

	@Autowired
	private CcmLineProtectService ccmLineProtectService;

	/**
	 * @see  获取单个线路信息
	 * @param id  ID
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-07
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
		
		CcmLineProtect ccmLineProtect = ccmLineProtectService.get(id);
		
		result.setCode(CcmRestType.OK);
		result.setResult(ccmLineProtect);
		
		return result;
	}
	
	/**
	 * @see  查询线路信息
	 * @param CcmHouseSchoolrim
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-07
	 */
	@ResponseBody
	@RequestMapping(value="/query", method = RequestMethod.GET)
	public CcmRestResult query(String userId,CcmLineProtect ccmLineProtect, HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

		Page<CcmLineProtect> page = ccmLineProtectService.findPage(new Page<CcmLineProtect>(req, resp), ccmLineProtect);
	
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	}
	
	/**
	 * @see  修改线路信息
	 * @param 
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-03-07
	 */
	@ResponseBody
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public CcmRestResult modify(String userId,CcmLineProtect ccmLineProtect,HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
		if (ccmLineProtect.getId()!= null && !"".equals(ccmLineProtect.getId())) {
			CcmLineProtect ccmLineProtectDB = ccmLineProtectService.get(ccmLineProtect.getId());
			if (ccmLineProtectDB == null ) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
			}
		}
		if (ccmLineProtect.getCreateBy()== null) {
			ccmLineProtect.setCreateBy(new User(userId));
		}
		ccmLineProtect.setUpdateBy(new User(userId));
		ccmLineProtectService.save(ccmLineProtect);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

	
	
	/**
	 * @see  保存护路护线信息（支持新增和编辑,数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-29
	 */
	@ResponseBody
	@RequestMapping(value="/saveLineProtectSyn", method = RequestMethod.POST)
	public CcmRestResult saveLineProtectSyn(String userId,CcmLineProtect ccmLineProtect,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		User user = new User(userId);
		ccmLineProtect.setCreateBy(user);
		ccmLineProtect.setUpdateBy(user);
		
		//富文本替换&amp;为&
		String dangCase = ccmLineProtect.getDangCase();
		dangCase = dangCase.replace("&amp;", "&");
		ccmLineProtect.setDangCase(dangCase);
		
		ccmLineProtectService.save(ccmLineProtect);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	/**
	 * @see  删除护路护线信息（数据同步用）
	 * @author liangwanmin
	 * @version 2018-05-29
	 */
	@ResponseBody
	@RequestMapping(value="/deleteLineProtectSyn", method = RequestMethod.POST)
	public CcmRestResult deleteLineProtectSyn(String userId,CcmLineProtect ccmLineProtect,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		ccmLineProtectService.delete(ccmLineProtect);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

}