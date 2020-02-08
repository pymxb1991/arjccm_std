/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.service.TreeService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmDevice;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmLiveVideo;
import com.arjjs.ccm.modules.ccm.ccmsys.dao.CcmLiveVideoDao;

/**
 * 视频监控Service
 * @author pengjianqiang
 * @version 2018-01-22
 */
@Service
@Transactional(readOnly = true)
public class CcmLiveVideoService extends TreeService<CcmLiveVideoDao, CcmLiveVideo> {

	//新填树查询
	@Autowired
	private CcmLiveVideoDao ccmLiveVideoDao;
	
	public CcmLiveVideo get(String id) {
		return super.get(id);
	}
	
	public List<CcmDevice> findAreaDeviceList(CcmDevice ccmDevice) {
		if (StringUtils.isNotBlank(ccmDevice.getParentIds())){
			ccmDevice.setParentIds(","+ccmDevice.getParentIds()+",");
		}
		return ccmLiveVideoDao.findAreaDeviceList(ccmDevice);
	}
	
}