package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventCasedeal;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventCasedealService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.oa.entity.OaNotify;
import com.arjjs.ccm.modules.oa.entity.OaNotifyRecord;
import com.arjjs.ccm.modules.oa.service.OaNotifyService;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.EchartType;

/**
 * 通用接口类
 * 
 * @author liangwanmin
 * @version 2018-08-16
 */
@Controller
@RequestMapping(value = "${appPath}/rest/echartType")
public class CcmRestEchartType extends BaseController {

	@Autowired
	private CcmEventCasedealService ccmEventCasedealService;
	@Autowired
	private OaNotifyService oaNotifyService;
	
	/**
	 * @see  查询数量信息-app轮询查询事件处理未处理数量、我的通告未读数量
	 * @param EchartType
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 
	 * @author liangwanmin
	 * @version 2018-08-16
	 */
	@ResponseBody
	@RequestMapping(value="/query", method = RequestMethod.GET)
	public CcmRestResult query(String userId,OaNotify oaNotify, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		
		List<EchartType> returnNum = new ArrayList<>();
		User user = new User();
		user.setId(userId);
		//事件处理未处理数量
		CcmEventCasedeal ccmEventCasedeal = new CcmEventCasedeal();
		ccmEventCasedeal.setHandleUser(user);
		int numEventCasedeal = ccmEventCasedealService.getNumEventCasedeal(ccmEventCasedeal);
		//我的通告未读数量
		OaNotifyRecord oaNotifyRecord = new OaNotifyRecord();
		oaNotifyRecord.setUser(user);
		oaNotifyRecord.setReadFlag("0");
		int numOaNotifyRecord = oaNotifyService.getNumOaNotifyRecord(oaNotifyRecord);
		
		EchartType echartType1 = new EchartType();
		echartType1.setType("eventCasedeal");
		echartType1.setValue(numEventCasedeal+"");
		returnNum.add(echartType1);
		EchartType echartType2 = new EchartType();
		echartType2.setType("oaNotifyRecord");
		echartType2.setValue(numOaNotifyRecord+"");
		returnNum.add(echartType2);
		
		
		result.setCode(CcmRestType.OK);
		result.setResult(returnNum);
		return result;
	}
	
	
}