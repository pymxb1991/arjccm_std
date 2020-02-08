package com.arjjs.ccm.modules.ccm.rest.web;

import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmMobileDevice;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmMobileDeviceService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.tool.CommUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * app设备接口类
 * 
 * @author fuxinshuang
 * @version 2018-03-29
 */
@Controller
@RequestMapping(value = "${appPath}/rest/ccmMobileDevice")
@Api(description = "app设备接口类")
public class CcmRestCcmMobileDevice extends BaseController {

	@Autowired
	private CcmMobileDeviceService ccmMobileDeviceService;


	/**
	 * @see app设备（支持新增和编辑,数据同步用）
	 * @param 
	 * @return 
	 * @author liangwanmin
	 * @version 2018-05-21
	 */
	@ResponseBody
	@RequestMapping(value="/saveSyn", method = RequestMethod.POST)
	@ApiOperation(value = "app设备（支持新增和编辑,数据同步用）")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "用户 id", paramType = "form"),
			@ApiImplicitParam(name = "JsonString", value = "json数据", paramType = "query")})
	public CcmRestResult saveSyn(String userId, @RequestBody CcmMobileDevice ccmMobileDevice, String JsonString, HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
		List<CcmMobileDevice> list = new ArrayList<>();
		list = CommUtil.getListByArray(CcmMobileDevice.class, JsonString);
		for(CcmMobileDevice c:list){
			CcmMobileDevice ccmMobileDevice1 = ccmMobileDeviceService.get(c.getId());
			if(ccmMobileDevice1!=null){
				c.setIsNewRecord(false);
				ccmMobileDeviceService.save(c);
			}else{
				c.setIsNewRecord(true);
				ccmMobileDeviceService.save(c);
			}
			
		}
		

		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

	
	
	
	
}