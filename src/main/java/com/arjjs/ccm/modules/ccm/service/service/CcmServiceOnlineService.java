/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.service.entity.CcmServiceOnline;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.modules.ccm.event.dao.CcmEventKaccDao;
import com.arjjs.ccm.modules.ccm.service.dao.CcmServiceOnlineDao;

/**
 * 在线办事Service
 * @author fuxinshuang
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly = true)
public class CcmServiceOnlineService extends CrudService<CcmServiceOnlineDao, CcmServiceOnline> {

	@Autowired
	private CcmServiceOnlineDao ccmServiceOnlineDao;
	
	
	public CcmServiceOnline get(String id) {
		return super.get(id);
	}
	
	public List<CcmServiceOnline> findList(CcmServiceOnline ccmServiceOnline) {
		return super.findList(ccmServiceOnline);
	}
	
	public Page<CcmServiceOnline> findPage(Page<CcmServiceOnline> page, CcmServiceOnline ccmServiceOnline) {
		return super.findPage(page, ccmServiceOnline);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmServiceOnline ccmServiceOnline) {
		super.save(ccmServiceOnline);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmServiceOnline ccmServiceOnline) {
		super.delete(ccmServiceOnline);
	}

	//在线办事事项分类分析
	public List<EchartType> getServiceType() {
		return ccmServiceOnlineDao.getServiceType();
	}

	//在线办事-处理进度
	public List<EchartType> getServiceStatus() {
		return ccmServiceOnlineDao.getServiceStatus();
	}
	
}