/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmTracingpoint;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmTracingpointService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.ccm.service.entity.CcmCommunityWork;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.CommUtil;
import com.arjjs.ccm.tool.CommUtilRest;

/**
 * 轨迹点位信息 Controller
 * 
 * @author arj
 * @version 2018-03-17
 */
@Controller
@RequestMapping(value = "${appPath}/rest/patrol/ccmRestTracingpoint")
public class CcmRestTracingpointController extends BaseController {

	@Autowired
	private CcmTracingpointService ccmTracingpointService;

	/**
	 * 
	 * @param id 轨迹点位Id
	 * @return
	 * @author arj
	 * @version 2018-03-17
	 */
	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public CcmRestResult get(String userId, HttpServletRequest req, HttpServletResponse resp, String id)
			throws IOException {
		// 获取results
		CcmRestResult result = CommUtilRest.getResult(userId, req, resp, id);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		// 当前 是否为空
		CcmTracingpoint ccmTracingpoint = ccmTracingpointService.get(id);
		result.setCode(CcmRestType.OK);
		result.setResult(ccmTracingpoint);
		return result;
	}

	/**
	 * @see 查询我的所有点位轨迹信息
	 * @param pageNo
	 * @param pageSiz
	 * @return
	 * @author arj
	 * @version 2018-03-12
	 */
	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public CcmRestResult query(String userId, HttpServletRequest req, HttpServletResponse resp,
			CcmTracingpoint ccmTracingpoint) throws IOException {
		// 获取结果
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		ccmTracingpoint.setUser(new User(userId));
		List<CcmTracingpoint> tlist = ccmTracingpointService.findList((null == ccmTracingpoint) ? new CcmTracingpoint() : ccmTracingpoint);
		result.setCode(CcmRestType.OK);
		result.setResult(tlist);
		// 输出结果
		return result;
	}

	/**
	 * 
	 * @param userId
	 * @param ccmTracingpoint
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public CcmRestResult modify(String userId, CcmTracingpoint ccmTracingpoint, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		// 获取结果
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		// 如果创建者为空
		if (ccmTracingpoint.getIsNewRecord()) {
			User userDto = new User(userId);
			ccmTracingpoint.setCreateBy(userDto);
			ccmTracingpoint.setUpdateBy(userDto);
		}
		ccmTracingpoint.setCurDate(new Date());
		ccmTracingpoint.setUser(new User(userId));
		ccmTracingpointService.save(ccmTracingpoint);
		// 返回
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
	}

	/**
	 * @see  实时轨迹点（支持新增和编辑,数据同步用）
	 * @param 
	 * @return 
	 * @author liangwanmin
	 * @version 2018-05-21
	 */
	@ResponseBody
	@RequestMapping(value="/saveSyn", method = RequestMethod.POST)
	public CcmRestResult saveSyn(String userId,CcmTracingpoint ccmTracingpoint,String JsonString,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		if (JsonString==null || "".equals(JsonString)) {
			return result;
		}else {
			JsonString = java.net.URLDecoder.decode(JsonString, "UTF-8");
			JsonString = JsonString.replace("&quot;","\"");
		}
		List<CcmTracingpoint> list = new ArrayList<>();
		list = CommUtil.getListByArray(CcmTracingpoint.class, JsonString);
		for(CcmTracingpoint c:list){
			CcmTracingpoint ccmTracingpoint1 = ccmTracingpointService.get(c.getId());
			if(ccmTracingpoint1!=null){
				c.setIsNewRecord(false);
				ccmTracingpointService.save(c);
			}else{
				c.setIsNewRecord(true);
				ccmTracingpointService.save(c);
			}
			
		}
		

		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
}