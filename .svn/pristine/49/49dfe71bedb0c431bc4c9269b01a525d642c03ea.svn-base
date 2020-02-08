package com.arjjs.ccm.modules.ccm.rest.web;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.modules.ccm.publicity.entity.CcmLogPublicity;
import com.arjjs.ccm.modules.ccm.publicity.service.CcmLogPublicityService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 宣传信息app接口
 * 
 * @author UserLi
 *
 */
@Controller
@RequestMapping(value = "${appPath}/rest/publish")
public class CcmRestPublish {

	@Autowired
	private CcmLogPublicityService ccmLogPublicityService;

	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public CcmRestResult query(Integer pageNo, String userId, Integer pageSize, CcmLogPublicity ccmLogPublicity, HttpServletRequest req,
                               HttpServletResponse response) {
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser == null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId == null || "".equals(userId) || !userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		if (pageSize == null) {
			pageSize=30;
		}

		if (pageNo == null) {
			pageNo=1;
		}

		ccmLogPublicity.setStatus("01");

		Page<CcmLogPublicity> page = ccmLogPublicityService.findPage(new Page<>(pageNo, pageSize), ccmLogPublicity);
		result.setCode(CcmRestType.OK);
		result.setReturnFlag(true);
		result.setResult(page.getList());

		return result;

	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public CcmRestResult query(String id, String userId, HttpServletRequest req,
                               HttpServletResponse response) {
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser == null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (Tool.isExistBlank(userId,id) || !userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}


		CcmLogPublicity ccmLogPublicity = ccmLogPublicityService.get(id);
		result.setCode(CcmRestType.OK);
		result.setReturnFlag(true);
		result.setResult(ccmLogPublicity);
		return result;

	}

}
