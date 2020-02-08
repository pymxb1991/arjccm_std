/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.face.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.iot.face.entity.CcmFaceControl;
import com.arjjs.ccm.modules.iot.face.dao.CcmFaceControlDao;

/**
 * 人脸布控实体类Service
 * @author lgh
 * @version 2019-06-05
 */
@Service
@Transactional(readOnly = true)
public class CcmFaceControlService extends CrudService<CcmFaceControlDao, CcmFaceControl> {

	@Autowired
	private CcmFaceControlDao ccmFaceControlDao;

	public CcmFaceControl get(String id) {
		return super.get(id);
	}
	
	public List<CcmFaceControl> findList(CcmFaceControl ccmFaceControl) {
		return super.findList(ccmFaceControl);
	}
	
	public Page<CcmFaceControl> findPage(Page<CcmFaceControl> page, CcmFaceControl ccmFaceControl) {
		return super.findPage(page, ccmFaceControl);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmFaceControl ccmFaceControl) {
		super.save(ccmFaceControl);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmFaceControl ccmFaceControl) {
		super.delete(ccmFaceControl);
	}

	@Transactional(readOnly = false)
	public void deleteByIdent(CcmFaceControl ccmFaceControl) {
		this.ccmFaceControlDao.deleteByIdent(ccmFaceControl);
	}
	
}