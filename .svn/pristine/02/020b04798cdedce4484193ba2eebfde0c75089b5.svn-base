/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.lane.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.lane.entity.CcmLane;
import com.arjjs.ccm.modules.ccm.lane.dao.CcmLaneDao;

/**
 * 车道表实体类Service
 * @author lgh
 * @version 2019-06-03
 */
@Service
@Transactional(readOnly = true)
public class CcmLaneService extends CrudService<CcmLaneDao, CcmLane> {

	public CcmLane get(String id) {
		return super.get(id);
	}
	
	public List<CcmLane> findList(CcmLane ccmLane) {
		return super.findList(ccmLane);
	}
	
	public Page<CcmLane> findPage(Page<CcmLane> page, CcmLane ccmLane) {
		return super.findPage(page, ccmLane);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmLane ccmLane) {
		super.save(ccmLane);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmLane ccmLane) {
		super.delete(ccmLane);
	}
	
}