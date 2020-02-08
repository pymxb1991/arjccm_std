/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventCasedeal;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventRequest;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventRequestdeal;
import com.arjjs.ccm.modules.ccm.event.dao.CcmEventCasedealDao;
import com.arjjs.ccm.modules.ccm.event.dao.CcmEventRequestDao;
import com.arjjs.ccm.modules.ccm.event.dao.CcmEventRequestdealDao;

/**
 * 请求登记Service
 * @author arj
 * @version 2018-01-18
 */
@Service
@Transactional(readOnly = true)
public class CcmEventRequestService extends CrudService<CcmEventRequestDao, CcmEventRequest> {
	@Autowired
	private CcmEventCasedealDao casedealDao;
	
	public CcmEventRequest get(String id) {
		return super.get(id);
	}
	
	public List<CcmEventRequest> findList(CcmEventRequest ccmEventRequest) {
		return super.findList(ccmEventRequest);
	}
	
	public Page<CcmEventRequest> findPage(Page<CcmEventRequest> page, CcmEventRequest ccmEventRequest) {
		return super.findPage(page, ccmEventRequest);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmEventRequest ccmEventRequest) {
		super.save(ccmEventRequest);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmEventRequest ccmEventRequest) {
		super.delete(ccmEventRequest);
	}
	
	/*public List<CcmEventRequestdeal> findList(String id){
		return ccmeventrequestdealdao.listWithPID(id);
	}*/
	//查询事件处理
	public List<CcmEventCasedeal> findCasedealList(String id) {
		return casedealDao.listWithPID(id);
	}
	
}