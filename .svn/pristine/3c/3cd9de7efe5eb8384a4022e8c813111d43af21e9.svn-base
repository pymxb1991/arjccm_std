/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.service.wechat;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.event.entity.wechat.CcmWechatEvent;
import com.arjjs.ccm.modules.ccm.event.dao.wechat.CcmWechatEventDao;

/**
 * 微信信息上报Service
 * @author fu
 * @version 2018-04-14
 */
@Service
@Transactional(readOnly = true)
public class CcmWechatEventService extends CrudService<CcmWechatEventDao, CcmWechatEvent> {

	public CcmWechatEvent get(String id) {
		return super.get(id);
	}
	
	public List<CcmWechatEvent> findList(CcmWechatEvent ccmWechatEvent) {
		return super.findList(ccmWechatEvent);
	}
	
	public Page<CcmWechatEvent> findPage(Page<CcmWechatEvent> page, CcmWechatEvent ccmWechatEvent) {
		return super.findPage(page, ccmWechatEvent);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmWechatEvent ccmWechatEvent) {
		super.save(ccmWechatEvent);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmWechatEvent ccmWechatEvent) {
		super.delete(ccmWechatEvent);
	}
	
}