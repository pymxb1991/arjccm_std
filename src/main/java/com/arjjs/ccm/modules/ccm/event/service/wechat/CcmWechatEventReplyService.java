/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.service.wechat;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.event.entity.wechat.CcmWechatEventReply;
import com.arjjs.ccm.modules.ccm.event.dao.wechat.CcmWechatEventReplyDao;

/**
 * 微信信息回复Service
 * @author fu
 * @version 2018-04-14
 */
@Service
@Transactional(readOnly = true)
public class CcmWechatEventReplyService extends CrudService<CcmWechatEventReplyDao, CcmWechatEventReply> {

	public CcmWechatEventReply get(String id) {
		return super.get(id);
	}
	
	public List<CcmWechatEventReply> findList(CcmWechatEventReply ccmWechatEventReply) {
		return super.findList(ccmWechatEventReply);
	}
	
	public Page<CcmWechatEventReply> findPage(Page<CcmWechatEventReply> page, CcmWechatEventReply ccmWechatEventReply) {
		return super.findPage(page, ccmWechatEventReply);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmWechatEventReply ccmWechatEventReply) {
		super.save(ccmWechatEventReply);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmWechatEventReply ccmWechatEventReply) {
		super.delete(ccmWechatEventReply);
	}
	
}