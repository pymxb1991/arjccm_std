/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.place.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.place.dao.CcmBasePlaceDao;
import com.arjjs.ccm.modules.ccm.place.entity.CcmBasePlace;
import com.arjjs.ccm.tool.EchartType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 场所基本信息表Service
 * @author lgh
 * @version 2019-04-23
 */
@Service
@Transactional(readOnly = true)
public class CcmBasePlaceService extends CrudService<CcmBasePlaceDao, CcmBasePlace> {
	@Autowired
	private CcmBasePlaceDao ccmBasePlaceDao;
	
	public CcmBasePlace get(String id) {
		return super.get(id);
	}
	
	public List<CcmBasePlace> findList(CcmBasePlace ccmBasePlace) {
		return super.findList(ccmBasePlace);
	}
	
	public Page<CcmBasePlace> findPage(Page<CcmBasePlace> page, CcmBasePlace ccmBasePlace) {
		return super.findPage(page, ccmBasePlace);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmBasePlace ccmBasePlace) {
		super.save(ccmBasePlace);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmBasePlace ccmBasePlace) {
		super.delete(ccmBasePlace);
	}

	@Transactional(readOnly = false)
	public boolean updateCoordinates(CcmBasePlace c) {
		return ccmBasePlaceDao.updateCoordinates(c);
	}

    public List<EchartType> getImportByKeyPoint() {
		return ccmBasePlaceDao.getImportByKeyPoint();
    }
	//根据场所类型统计
	public List<EchartType> getCountbyplaceType() {
		return ccmBasePlaceDao.getcountbyplaceType();
	}

}