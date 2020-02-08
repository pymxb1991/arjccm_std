/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.TreeDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmDevice;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmLiveVideo;

/**
 * 视频监控DAO接口
 * @author pengjianqiang
 * @version 2018-01-22
 */
@MyBatisDao
public interface CcmLiveVideoDao extends TreeDao<CcmLiveVideo> {
	public List<CcmDevice> findAreaDeviceList(CcmDevice ccmDevice);
}