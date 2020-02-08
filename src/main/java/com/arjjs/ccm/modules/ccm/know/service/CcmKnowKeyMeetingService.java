/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowKeyMeeting;
import com.arjjs.ccm.modules.ccm.know.dao.CcmKnowKeyMeetingDao;

/**
 * 重要会议Service
 * @author liang
 * @version 2018-03-23
 */
@Service
@Transactional(readOnly = true)
public class CcmKnowKeyMeetingService extends CrudService<CcmKnowKeyMeetingDao, CcmKnowKeyMeeting> {

	public CcmKnowKeyMeeting get(String id) {
		return super.get(id);
	}
	
	public List<CcmKnowKeyMeeting> findList(CcmKnowKeyMeeting ccmKnowKeyMeeting) {
		return super.findList(ccmKnowKeyMeeting);
	}
	
	public Page<CcmKnowKeyMeeting> findPage(Page<CcmKnowKeyMeeting> page, CcmKnowKeyMeeting ccmKnowKeyMeeting) {
		return super.findPage(page, ccmKnowKeyMeeting);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmKnowKeyMeeting ccmKnowKeyMeeting) {
		super.save(ccmKnowKeyMeeting);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmKnowKeyMeeting ccmKnowKeyMeeting) {
		super.delete(ccmKnowKeyMeeting);
	}
	
}