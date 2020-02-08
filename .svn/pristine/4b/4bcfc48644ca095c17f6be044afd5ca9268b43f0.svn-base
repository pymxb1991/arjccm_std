package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.ccm.service.entity.CcmCommunityWork;
import com.arjjs.ccm.modules.ccm.service.entity.CcmServiceGuidance;
import com.arjjs.ccm.modules.ccm.service.entity.CcmServiceOnline;
import com.arjjs.ccm.modules.ccm.service.entity.CcmServiceWechat;
import com.arjjs.ccm.modules.ccm.service.service.CcmCommunityWorkService;
import com.arjjs.ccm.modules.ccm.service.service.CcmServiceGuidanceService;
import com.arjjs.ccm.modules.ccm.service.service.CcmServiceOnlineService;
import com.arjjs.ccm.modules.ccm.service.service.CcmServiceWechatService;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmWorkReport;
import com.arjjs.ccm.modules.cms.entity.Guestbook;
import com.arjjs.ccm.modules.sys.entity.Dict;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.tool.CommUtilRest;

/**
 * 社区服务
 * 
 * @author arj
 * @version 2018-03-30
 */
@Controller
@RequestMapping(value = "${appPath}/rest/service/wechat")
public class CcmRestServiceWechat extends BaseController {

	@Autowired
	private CcmServiceWechatService ccmServiceWechatService;

	/**
	 * @see 获取单个在线办事
	 * @param id
	 *           在线办事ID
	 * @return
	 * @author fuxinshuang
	 * @version 2018-03-30
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
		CcmServiceWechat ccmServiceWechat = ccmServiceWechatService.get(id);
		result.setCode(CcmRestType.OK);
		result.setResult(ccmServiceWechat);

		return result;
	}

	/**
	 * @see 查询我的在线办事信息
	 * @param pageNo
	 *            页码
	 * @param pageSiz
	 *            分页大小
	 * @return
	 * @author fuxinshuang
	 * @version 2018-03-30
	 */
	@ResponseBody
	@RequestMapping(value = "/querySelf", method = RequestMethod.GET)
	public CcmRestResult querySelf(String userId, HttpServletRequest req, HttpServletResponse resp,
			CcmServiceWechat ccmServiceWechat) throws IOException {
		// 获取结果
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		// 设置当前的 在线办事不为空
		ccmServiceWechat = (null == ccmServiceWechat) ? new CcmServiceWechat() : ccmServiceWechat;
		// 获取userId
		User userEntity = new User();
		userEntity.setId(userId);
		ccmServiceWechat.setCreateBy(userEntity);
		// 设置当前用户 为自身
		ccmServiceWechat.setSelf(true);
		//设置当前的用户   
		ccmServiceWechat.setCurrentUser(userEntity);
		Page<CcmServiceWechat> page = ccmServiceWechatService.findPage(new Page<CcmServiceWechat>(req, resp),
				(null == ccmServiceWechat) ? new CcmServiceWechat() : ccmServiceWechat);
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		// 输出结果
		return result;
	}

	/**
	 * @see 查询所有线办事信息
	 * @param pageNo
	 *            页码
	 * @param pageSiz
	 *            分页大小
	 * @return
	 * @author fuxinshuang
	 * @version 2018-03-15
	 */
	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public CcmRestResult query(String userId, HttpServletRequest req, HttpServletResponse resp,
			CcmServiceWechat ccmServiceWechat) throws IOException {
		// 获取结果
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		// 设置当前的 在线办事不为空
		ccmServiceWechat = (null == ccmServiceWechat) ? new CcmServiceWechat() : ccmServiceWechat;
		// 获取userId
		// 获取userId
		User userEntity = new User();
		userEntity.setId(userId);
		ccmServiceWechat.setCreateBy(userEntity);
		Page<CcmServiceWechat> page = ccmServiceWechatService.findPage(new Page<CcmServiceWechat>(req, resp),
				(null == ccmServiceWechat) ? new CcmServiceWechat() : ccmServiceWechat);
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		// 输出结果
		return result;
	}

	/**
	 * @see 填加在线补充
	 * @param ccmServiceWechat
	 *            公众信息上报对象
	 * @author fuxinshuang
	 * @version 2018-03-17
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public CcmRestResult save(String userId, CcmServiceWechat ccmServiceWechat, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		// 获取结果
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		// 不允许修改已发送在线办事
		if (ccmServiceWechat.getId()!=null && !"".equals(ccmServiceWechat.getId())) {
			result.setCode(CcmRestType.ERROR_NO_MODIFY_PERMISSIONS);
			return result;
		}
		// 如果创建者为空
		if (null == ccmServiceWechat.getCreateBy()) {
			ccmServiceWechat.setCreateBy(new User(userId));
		}
		ccmServiceWechat.setUpdateBy(new User(userId));
		ccmServiceWechat.setIp(req.getRemoteAddr());
		ccmServiceWechat.setCreateDate(new Date());
		ccmServiceWechat.setDelFlag(Guestbook.DEL_FLAG_AUDIT);
		ccmServiceWechatService.save(ccmServiceWechat);
		// 返回
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
	}


}