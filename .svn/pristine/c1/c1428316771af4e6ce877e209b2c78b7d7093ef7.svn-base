/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.rest.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.appversion.entity.AppVersion;
import com.arjjs.ccm.modules.ccm.appversion.service.AppVersionService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * app版本Controller
 * @author lijiupeng
 * @version 2019-08-13
 */
@RestController
@RequestMapping(value = "${appPath}/rest/appVersion")
public class CcmRestAppVersion extends BaseController {

	@Autowired
	private AppVersionService appVersionService;


	@RequestMapping(value = "list")
	public CcmRestResult list(String userId,Integer pageNo,AppVersion appVersion, HttpServletRequest request, HttpServletResponse response, Model model) {
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId == null || "".equals(userId) || !userId.equals(sessionUserId) || pageNo==null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}

		Page<AppVersion> page = appVersionService.findPage(new Page<>(pageNo, 30), appVersion);
		page.getList().forEach(item->{
			item.setDownload(item.getDownload().split("\\|")[1]);
		});


		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());

		return result;
	}

	@RequestMapping(value = "form")
	public CcmRestResult form(String userId,AppVersion appVersion,  HttpServletRequest request, HttpServletResponse response,Model model) {
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId == null || "".equals(userId) || !userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;

		}

		AppVersion appVersion1 = appVersionService.get(appVersion.getId());
			appVersion1.setDownload(appVersion1.getDownload().split("\\|")[1]);
		result.setCode(CcmRestType.OK);
		result.setResult(appVersion1);

		return result;


	}


/*	@RequestMapping(value = "upToDate")
	public CcmRestResult upToDate(Integer VersionCode, HttpServletRequest request, HttpServletResponse response,Model model) {
		CcmRestResult result = new CcmRestResult();

		if(VersionCode==null){
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}

		List<AppVersion> list = appVersionService.findList(new AppVersion());
		if(!list.isEmpty()){
			AppVersion appVersion = list.get(0);
			int i = Integer.parseInt(appVersion.getVersionCode());
			if(StringUtils.isNotBlank(appVersion.getDownload())) {	
				String file = appVersion.getDownload();
				if(file.split("\\|").length > 0) {					
					appVersion.setDownload(Global.getConfig("FILE_UPLOAD_URL")+appVersion.getDownload().split("\\|")[1]);
				}else {
					appVersion.setDownload(Global.getConfig("FILE_UPLOAD_URL")+appVersion.getDownload());
				}
			}
			if(VersionCode<i){
				result.setResult(appVersion);
				result.setCode(CcmRestType.OK);
			}else{
				result.setResult(appVersion);
				result.setCode(1);
			}
		}
		return result;


	}*/


	@RequestMapping(value = "save")
	public CcmRestResult save(String userId, AppVersion appVersion,  HttpServletRequest request, HttpServletResponse response, Model model) {
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId == null || "".equals(userId) || !userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;

		}
		appVersion.setCreateBy(sessionUser);
		appVersion.setUpdateBy(sessionUser);
		appVersionService.save(appVersion);

		result.setCode(CcmRestType.OK);
		result.setResult(appVersion);

		return result;
	}
	
//	@RequestMapping(value = "delete")
//	public String delete(AppVersion appVersion, RedirectAttributes redirectAttributes) {
//		appVersionService.delete(appVersion);
//		addMessage(redirectAttributes, "删除app版本成功");
//		return "redirect:"+Global.getAdminPath()+"/appversion/appVersion/?repage";
//	}

}