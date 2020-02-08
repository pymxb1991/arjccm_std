/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.bayonet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.bayonet.entity.CcmCarBayonet;
import com.arjjs.ccm.modules.ccm.bayonet.dao.CcmCarBayonetDao;

/**
 * 车辆卡口实体类Service
 * @author lgh
 * @version 2019-05-31
 */
@Service
@Transactional(readOnly = true)
public class CcmCarBayonetService extends CrudService<CcmCarBayonetDao, CcmCarBayonet> {

	@Autowired
	private CcmCarBayonetDao ccmCarBayonetDao;

	public CcmCarBayonet get(String id) {
		return super.get(id);
	}
	
	public List<CcmCarBayonet> findList(CcmCarBayonet ccmCarBayonet) {
		return super.findList(ccmCarBayonet);
	}
	
	public Page<CcmCarBayonet> findPage(Page<CcmCarBayonet> page, CcmCarBayonet ccmCarBayonet) {
		return super.findPage(page, ccmCarBayonet);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmCarBayonet ccmCarBayonet) {
		super.save(ccmCarBayonet);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmCarBayonet ccmCarBayonet) {
		super.delete(ccmCarBayonet);
	}

    public int getCount(CcmCarBayonet ccmCarBayonet) {
		return ccmCarBayonetDao.getCount(ccmCarBayonet);
    }
}