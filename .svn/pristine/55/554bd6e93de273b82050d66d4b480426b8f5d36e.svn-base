package com.arjjs.ccm.modules.ccm.ccmsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.ccmsys.dao.CcmLogDao;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmLog;


@Service
@Transactional(readOnly = true)
public class CcmLogService extends CrudService<CcmLogDao, CcmLog>{
	
	@Autowired
	private CcmLogDao ccmLogDao;
	
	public CcmLog getById(String id) {
		return ccmLogDao.getById(id);
	}

}
