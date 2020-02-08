package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.ext.awt.image.renderable.OffsetRable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildmanage;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseAreainforService;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseBuildmanageService;
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowPolicy;
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowSpace;
import com.arjjs.ccm.modules.ccm.know.service.CcmKnowPolicyService;
import com.arjjs.ccm.modules.ccm.know.service.CcmKnowSpaceService;
import com.arjjs.ccm.modules.ccm.log.service.CcmLogTailService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgArea;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgAreaService;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPopTenantService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestAreaInfor;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.ccm.rest.service.CcmRestOfficeService;
import com.arjjs.ccm.modules.ccm.view.entity.VCcmTeam;
import com.arjjs.ccm.modules.ccm.view.service.VCcmTeamService;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.Tree;
import com.fay.tree.service.IFayTreeNode;
import com.fay.tree.util.FayTreeUtil;

/**
 * 楼栋接口类
 * 
 * @author fuxinshuang
 * @version 2018-03-10
 */
@Controller
@RequestMapping(value = "${appPath}/rest/government")
public class CcmRestGovernment extends BaseController {


	@Autowired
	private CcmOrgAreaService ccmOrgAreaService;
	@Autowired
	private VCcmTeamService vCcmTeamService;
	@Autowired
	private CcmKnowPolicyService ccmKnowPolicyService;
	@Autowired
	private CcmKnowSpaceService ccmKnowSpaceService;
	
	/**
	 * @see 查询辖区信息
	 * @param pageNo   页码
	 * @param pageSize	  分页大小
	 * @return
	 * @author fuxinshuang
	 * @version 2018-03-10
	 */
	@ResponseBody
	@RequestMapping(value = "/queryAreainfor", method = RequestMethod.GET)
	public CcmRestResult queryAreainfor(String userId, HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
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
		CcmRestAreaInfor ccmRestAreaInfor = new CcmRestAreaInfor();
		// 返回结果
		CcmOrgArea ccmOrgArea = ccmOrgAreaService.GetItByUserId(userId);

		ccmRestAreaInfor.setCcmOrgArea(ccmOrgArea);
		if (null != ccmOrgArea) {
			// 返回具体内容
			List<VCcmTeam> ListVccTeam = vCcmTeamService.GetUserByArea(ccmOrgArea.getArea().getId());
			ccmRestAreaInfor.setListVccTeam(ListVccTeam);
			String path = Global.getConfig("FILE_UPLOAD_URL");
			for (VCcmTeam vCcmTeam : ListVccTeam) {
				String photo = vCcmTeam.getPhoto();
				vCcmTeam.setPhoto(path + photo);
			}
		}
		// 返回具体内容
		result.setCode(CcmRestType.OK);
		result.setResult(ccmRestAreaInfor);

		return result;
	}
	
	/**
	 * @see 查询政策法规信息
	 * @param pageNo   页码
	 * @param pageSize	  分页大小
	 * @return
	 * @author fuxinshuang
	 * @version 2018-03-10
	 */
	@ResponseBody
	@RequestMapping(value = "/queryKnowPolicy", method = RequestMethod.GET)
	public CcmRestResult queryKnowPolicy(String userId,CcmKnowPolicy ccmKnowPolicy,HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
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
		Page<CcmKnowPolicy> page = ccmKnowPolicyService.findPage(new Page<CcmKnowPolicy>(req, resp), ccmKnowPolicy);
		
		
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());

		return result;
	}

	/**
	 * @see 获取单个政策法规信息
	 * @param id  ID
	 * @return
	 * @author fuxinshuang
	 * @version 2018-03-10
	 */
	@ResponseBody
	@RequestMapping(value = "/getKnowPolicy", method = RequestMethod.GET)
	public CcmRestResult get(String userId, HttpServletRequest req, HttpServletResponse resp, String id)
			throws IOException {
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
		if (id == null || "".equals(id)) {// 参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}

		CcmKnowPolicy ccmKnowPolicy = ccmKnowPolicyService.get(id);
		logger.info(""+ccmKnowPolicy);
		String file1 = URLDecoder.decode(ccmKnowPolicy.getFile1().replace("|", ""),"utf-8");//ccmKnowPolicy.getFile1();
		String file2 = URLDecoder.decode(ccmKnowPolicy.getFile2().replace("|", ""),"utf-8");//ccmKnowPolicy.getFile2();
		String file3 = URLDecoder.decode(ccmKnowPolicy.getFile3().replace("|", ""),"utf-8");//ccmKnowPolicy.getFile3();
		String path = Global.getConfig("FILE_UPLOAD_URL");//文件上传存储路径
		ccmKnowPolicy.setFile1(file1 !="" ? path+file1 : "");
		ccmKnowPolicy.setFile2(file2 !="" ? path+file2 : "");
		ccmKnowPolicy.setFile3(file3 !="" ? path+file3 : "");
		result.setCode(CcmRestType.OK);
		result.setResult(ccmKnowPolicy);

		return result;
	}
	/**
	 * @see 查询政策法规信息
	 * @param pageNo   页码
	 * @param pageSize	  分页大小
	 * @return
	 * @author fuxinshuang
	 * @version 2018-03-10
	 */
	@ResponseBody
	@RequestMapping(value = "/queryKnowSpace", method = RequestMethod.GET)
	public CcmRestResult queryKnowSpace(String userId,CcmKnowSpace ccmKnowSpace,HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
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
		Page<CcmKnowSpace> page = ccmKnowSpaceService.findPage(new Page<CcmKnowSpace>(req, resp), ccmKnowSpace);
		
		
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());

		return result;
	}

	/**
	 * @see 获取单个政策法规信息
	 * @param id  ID
	 * @return
	 * @author fuxinshuang
	 * @version 2018-03-10
	 */
	@ResponseBody
	@RequestMapping(value = "/getKnowSpace", method = RequestMethod.GET)
	public CcmRestResult getKnowSpace(String userId, HttpServletRequest req, HttpServletResponse resp, String id)
			throws IOException {
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
		if (id == null || "".equals(id)) {// 参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}

		CcmKnowSpace ccmKnowSpace = ccmKnowSpaceService.get(id);

		result.setCode(CcmRestType.OK);
		result.setResult(ccmKnowSpace);

		return result;
	}


}