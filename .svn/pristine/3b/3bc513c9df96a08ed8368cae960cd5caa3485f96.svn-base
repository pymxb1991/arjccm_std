package com.arjjs.ccm.tool;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.sys.entity.SysConfig;
import com.arjjs.ccm.modules.ccm.sys.service.SysConfigService;

import net.sf.json.JSONObject;

/**
 * 系统初始化设置类，存放系统级常量
 * 
 * @author pengjianqiang
 * @version 2018-05-14
 */
@Controller
@RequestMapping(value = "${appPath}/rest/event")
public class SysConfigInit extends BaseController {

	@Autowired
	private SysConfigService sysConfigService;

	public static String UPPER_URL = "";// 上级平台地址
	public static String UPPER_USERNAME = "";// 上级平台访问用户名
	public static String UPPER_PASSWORD = "";// 上级平台访问密码
	public static SysConfig SYS_LEVEL_CONFIG = new SysConfig(); // 系统类型配置信息
	public static SysConfig ALARM_CHECK_CONFIG = new SysConfig(); // 超时警情最大时间配置

	/**
	 * 方法说明：从数据库中获取初始化常量数据，放到常量中去
	 * 
	 * @return
	 * @author pengjianqiang
	 * @version 2018-05-14
	 */
	public void sysInit() throws Exception {
		List<SysConfig> findList = sysConfigService.findList(new SysConfig());
		findList.forEach(sysConfig -> {
			if(StringUtils.equals("upper_system_config", sysConfig.getId())) {
				JSONObject jsonObject = JSONObject.fromObject(sysConfig.getParamStr());
				String url = jsonObject.getString("url");
				String username = jsonObject.getString("username");
				String password = jsonObject.getString("password");
				if (url != null) {
					UPPER_URL = url;
				}
				if (username != null) {
					UPPER_USERNAME = username;
				}
				if (password != null) {
					UPPER_PASSWORD = password;
				}
			} else if (StringUtils.equals("system_level", sysConfig.getId())) {
				/** 从sysconfig中获取上级平台接口信息 **/
				SYS_LEVEL_CONFIG = sysConfig;
			} else if (StringUtils.equals("alarm_check_config", sysConfig.getId())) {
				//超时警情最大时间配置
				JSONObject jsonObject = JSONObject.fromObject(sysConfig.getParamStr());
				String maxAcceptTime = jsonObject.getString("maxAcceptTime");
				String maxDispatchTime = jsonObject.getString("maxDispatchTime");
				String maxArriveTime = jsonObject.getString("maxArriveTime");
				if (StringUtils.isNotBlank(maxAcceptTime)) {
					ALARM_CHECK_CONFIG.setMaxAcceptTime(maxAcceptTime);
				}
				if (StringUtils.isNotBlank(maxDispatchTime)) {
					ALARM_CHECK_CONFIG.setMaxDispatchTime(maxDispatchTime);
				}
				if (StringUtils.isNotBlank(maxArriveTime)) {
					ALARM_CHECK_CONFIG.setMaxArriveTime(maxArriveTime);
				}
			}
		});
	}
}