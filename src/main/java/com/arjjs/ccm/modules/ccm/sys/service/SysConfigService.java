/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.sys.service;

import com.alibaba.fastjson.JSONObject;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.rest.entity.SysMapConfig;
import com.arjjs.ccm.modules.ccm.sys.dao.SysConfigDao;
import com.arjjs.ccm.modules.ccm.sys.entity.SysConfig;
import com.arjjs.ccm.tool.Constants;
import com.arjjs.ccm.tool.HtmlUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统信息配置Service
 * @author liang
 * @version 2018-05-10
 */
@Service
@Transactional(readOnly = true)
public class SysConfigService extends CrudService<SysConfigDao, SysConfig> {

	public SysConfig get(String id) {
		return super.get(id);
	}
	
	public List<SysConfig> findList(SysConfig sysConfig) {
		return super.findList(sysConfig);
	}
	
	public Page<SysConfig> findPage(Page<SysConfig> page, SysConfig sysConfig) {
		return super.findPage(page, sysConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(SysConfig sysConfig) {
		super.save(sysConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysConfig sysConfig) {
		super.delete(sysConfig);
	}

	public String getImageMapConfig() {
		String imgeUrl = "";
		SysConfig sysConfig = get(Constants.MAP_CONFIG_ID);

		List<SysMapConfig> sysMapConfigs =  new ArrayList<>();
		if(StringUtils.isNotBlank(sysConfig.getParamStr())){
			JSONObject jsonObject = JSONObject.parseObject(sysConfig.getParamStr());
			if (jsonObject != null && !jsonObject.isEmpty() ){
				SysMapConfig sysMapConfig = JSONObject.toJavaObject(jsonObject, SysMapConfig.class);
				imgeUrl = sysMapConfig.getImageMapUrl();
				String urlAndPort2 = HtmlUtil.getStr(sysMapConfig.getImageMapUrl());
				String mapServerUrl =  Global.getConfig("map_server_url") == null ? "" : Global.getConfig("map_server_url").toString();
				System.out.println("mapServerUrl： "+ mapServerUrl);
				if (StringUtils.isNotBlank(mapServerUrl)){
					imgeUrl = mapServerUrl+ urlAndPort2;
					sysMapConfig.setImageMapUrl(imgeUrl);
				}
				sysMapConfigs.add(sysMapConfig);
			}
		}
		return  imgeUrl;
	}
}