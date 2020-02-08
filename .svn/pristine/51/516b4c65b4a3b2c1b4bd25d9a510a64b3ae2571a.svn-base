/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgCommonality;
import com.arjjs.ccm.tool.SearchTabMore;
import com.arjjs.ccm.modules.ccm.org.dao.CcmOrgCommonalityDao;

/**
 * 公共机构管理Service
 * @author liang
 * @version 2018-02-24
 */
@Service
@Transactional(readOnly = true)
public class CcmOrgCommonalityService extends CrudService<CcmOrgCommonalityDao, CcmOrgCommonality> {

	//生成公共机构地图信息-区域图
	@Autowired
	private CcmOrgCommonalityDao ccmOrgCommonalityDao;
	
	public CcmOrgCommonality get(String id) {
		return super.get(id);
	}
	
	public List<CcmOrgCommonality> findList(CcmOrgCommonality ccmOrgCommonality) {
		return super.findList(ccmOrgCommonality);
	}
	
	//生成公共机构地图信息-区域图
	public List<CcmOrgCommonality> findCommonalityList(CcmOrgCommonality ccmOrgCommonality) {
		return ccmOrgCommonalityDao.findCommonalityList(ccmOrgCommonality);
	}
	
	public Page<CcmOrgCommonality> findPage(Page<CcmOrgCommonality> page, CcmOrgCommonality ccmOrgCommonality) {
		return super.findPage(page, ccmOrgCommonality);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmOrgCommonality ccmOrgCommonality) {
		super.save(ccmOrgCommonality);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmOrgCommonality ccmOrgCommonality) {
		super.delete(ccmOrgCommonality);
	}
	
	// 更新点坐标
	@Transactional(readOnly = false)
	public boolean updateCoordinates(CcmOrgCommonality ccmOrgCommonality){
		return ccmOrgCommonalityDao.updateCoordinates(ccmOrgCommonality)>0;
	}

	public SearchTabMore getCountInfo(CcmOrgCommonality ccmOrgCommonality) {
		return ccmOrgCommonalityDao.getCountInfo(ccmOrgCommonality);
	}
}