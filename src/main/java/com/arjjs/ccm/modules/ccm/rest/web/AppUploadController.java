/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.rest.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.flat.appupload.entity.SysAppUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.flat.appupload.service.SysAppUploadService;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录Controller
 * @author admin001
 * @version 2013-5-31
 */
@RestController
@RequestMapping(value = "${appPath}/rest/appVersion")
public class AppUploadController extends BaseController{

	@Autowired
	private SysAppUploadService sysAppUploadService;
	
//	@ResponseBody
//	@RequestMapping(value = "${appPath}/getAppInfo", method = RequestMethod.GET)
//	public String getAppInfo(){
//		return sysAppUploadService.getAppInfo();
//	}



	@RequestMapping(value = "upToDate")
	public CcmRestResult upToDate(Integer VersionCode, HttpServletRequest request, HttpServletResponse response, Model model) {
		CcmRestResult result = new CcmRestResult();

		if(VersionCode==null){
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}

		SysAppUpload sysAppUpload = sysAppUploadService.getAppInfo();
		if(sysAppUpload != null){
			int i = Integer.parseInt(null!=sysAppUpload.getVersion()? sysAppUpload.getVersion() : "0");
			if(StringUtils.isNotBlank(sysAppUpload.getFiles())) {
				String file = sysAppUpload.getFiles();
				if(file.contains("|")){
					sysAppUpload.setDownload(sysAppUpload.getFiles().replace("|",Global.getConfig("FILE_UPLOAD_URL")));
				} else {
					sysAppUpload.setDownload(Global.getConfig("FILE_UPLOAD_URL") + file);
				}
			}
			if(VersionCode<i){
				result.setResult(sysAppUpload);
				result.setCode(CcmRestType.OK);
			}else{
				result.setResult(sysAppUpload);
				result.setCode(1);
			}
		}
		return result;


	}


}
