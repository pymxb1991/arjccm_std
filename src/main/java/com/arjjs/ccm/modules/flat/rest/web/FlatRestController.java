package com.arjjs.ccm.modules.flat.rest.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjjs.ccm.modules.flat.alarm.service.BphAlarmInfoService;
import com.arjjs.ccm.modules.flat.rest.service.FlatRestService;

@RestController
@RequestMapping(value = "${appPath}/rest")
public class FlatRestController {
	
	@Autowired
	FlatRestService flatRestService;
	
	@Autowired
	private BphAlarmInfoService bphAlarmInfoService;
	
	/**
	 * @throws IOException 
	 * @方法描述：接收数据
	 */
	@RequestMapping(value = "receiveData")
	public void receiveData(HttpServletRequest req, HttpServletResponse resp,String param) throws IOException {
		flatRestService.receiveData(param);
	}
	
	/**
	 * @description 请求手机APP视频回调接口
	 * @author wangyikai
	 * @param paramIn
	 */
	@RequestMapping(value = "requestAppVideoCallback")
	public void requestAppVideoCallback(String paramIn) {
		bphAlarmInfoService.requestAppVideoCallback(paramIn);
	}
	
	/**
	 * @description 接收警情对接数据
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "receiveDockingData")
	public String receiveDockingData(String param) {
		return bphAlarmInfoService.receiveDockingData(param);
	}
}
