/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.service.wechat;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.event.entity.wechat.CcmWechatEventAttachment;
import com.arjjs.ccm.modules.ccm.event.dao.wechat.CcmWechatEventAttachmentDao;

/**
 * 微信信息上报附件Service
 * @author fu
 * @version 2018-04-14
 */
@Service
@Transactional(readOnly = true)
public class CcmWechatEventAttachmentService extends CrudService<CcmWechatEventAttachmentDao, CcmWechatEventAttachment> {

	public CcmWechatEventAttachment get(String id) {
		return super.get(id);
	}
	
	public List<CcmWechatEventAttachment> findList(CcmWechatEventAttachment ccmWechatEventAttachment) {
		return super.findList(ccmWechatEventAttachment);
	}
	
	public Page<CcmWechatEventAttachment> findPage(Page<CcmWechatEventAttachment> page, CcmWechatEventAttachment ccmWechatEventAttachment) {
		return super.findPage(page, ccmWechatEventAttachment);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmWechatEventAttachment ccmWechatEventAttachment) {
		super.save(ccmWechatEventAttachment);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmWechatEventAttachment ccmWechatEventAttachment) {
		super.delete(ccmWechatEventAttachment);
	}
	
}