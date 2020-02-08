package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgArea;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgAreaService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.sys.entity.Area;

/**
 * 区域接口类-上下级同步用
 * 
 * @author pengjianqiang
 * @version 2018-05-10
 */
@Controller
@RequestMapping(value = "${appPath}/rest/orgArea")
public class CcmRestOrgArea extends BaseController {


	@Autowired
	private CcmOrgAreaService ccmOrgAreaService;

	
	/**
	 * @see 查询辖区信息（获取区域下所有子区域的辖区信息,包括自己）
	 * @param areaId	区域id
	 * @return
	 * @author pengjianqiang
	 * @version 2018-05-10
	 */
	@ResponseBody
	@RequestMapping(value = "/getOrgAreaByParentID", method = RequestMethod.GET)
	public CcmRestResult getOrgAreaByParentID(String areaId, HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		CcmRestResult result = new CcmRestResult();
		CcmOrgArea voCcmOrgArea = new CcmOrgArea();
		Area area = new Area();
		area.setParentIds(areaId);
		voCcmOrgArea.setArea(area);
		// 返回结果
		List<CcmOrgArea> ccmOrgArea = ccmOrgAreaService.findList(voCcmOrgArea);

		result.setCode(CcmRestType.OK);
		result.setResult(ccmOrgArea);

		return result;
	}


}