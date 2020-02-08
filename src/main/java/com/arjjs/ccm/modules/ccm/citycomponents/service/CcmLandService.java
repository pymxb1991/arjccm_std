/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.citycomponents.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.citycomponents.entity.CcmCityComponents;
import com.arjjs.ccm.modules.ccm.citycomponents.entity.CcmLand;
import com.arjjs.ccm.modules.ccm.citycomponents.dao.CcmLandDao;

/**
 * 土地管理Service
 * 
 * @author pengjianqiang
 * @version 2018-03-06
 */
@Service
@Transactional(readOnly = true)
public class CcmLandService extends CrudService<CcmLandDao, CcmLand> {

	@Autowired
	private CcmLandDao ccmLandDao;

	public CcmLand get(String id) {
		return super.get(id);
	}

	public List<CcmLand> findList(CcmLand ccmLand) {
		return super.findList(ccmLand);
	}

	public Page<CcmLand> findPage(Page<CcmLand> page, CcmLand ccmLand) {
		return super.findPage(page, ccmLand);
	}

	@Transactional(readOnly = false)
	public void save(CcmLand ccmLand) {
		super.save(ccmLand);
	}

	@Transactional(readOnly = false)
	public void delete(CcmLand ccmLand) {
		super.delete(ccmLand);
	}

	// 更新点位坐标信息
	@Transactional(readOnly = false)
	public boolean updateCoordinates(CcmLand CcmLand) {
		return ccmLandDao.updateCoordinates(CcmLand) > 0;
	}

}