/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildmanageUnit;

import com.arjjs.ccm.modules.ccm.house.dao.CcmHouseBuildmanageUnitDao;

/**
 * 楼栋单元户信息排列Service
 * @author liu
 * @version 2019-04-24
 */
@Service
@Transactional(readOnly = true)
public class CcmHouseBuildmanageUnitService extends CrudService<CcmHouseBuildmanageUnitDao, CcmHouseBuildmanageUnit> {

	public CcmHouseBuildmanageUnit get(String id) {
		return super.get(id);
	}
	
	public List<CcmHouseBuildmanageUnit> findList(CcmHouseBuildmanageUnit ccmHouseBuildmanageUnit) {
		return super.findList(ccmHouseBuildmanageUnit);
	}
	
	public List<CcmHouseBuildmanageUnit> findListByBuildmanageId (String buildmanageId) {
		return dao.findListByBuildmanageId(buildmanageId);
	}
	public Page<CcmHouseBuildmanageUnit> findPage(Page<CcmHouseBuildmanageUnit> page, CcmHouseBuildmanageUnit ccmHouseBuildmanageUnit) {
		return super.findPage(page, ccmHouseBuildmanageUnit);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmHouseBuildmanageUnit ccmHouseBuildmanageUnit) {
		super.save(ccmHouseBuildmanageUnit);
	}
	
	@Transactional(readOnly = false)
	public int saveData(List<CcmHouseBuildmanageUnit> ccmHouseBuildmanageUnit) {
		return dao.saveData(ccmHouseBuildmanageUnit);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmHouseBuildmanageUnit ccmHouseBuildmanageUnit) {
		super.delete(ccmHouseBuildmanageUnit);
	}
	
	@Transactional(readOnly = false)
	public int deleteHouseBuildmanageUnit(List<CcmHouseBuildmanageUnit>  ccmHouseBuildmanageUnitList) {
		String buildmanageId = "";
		buildmanageId = ccmHouseBuildmanageUnitList.get(0).getBuildmanageId();
		return dao.deleteHouseBuildmanageUnit(buildmanageId);
	}
}