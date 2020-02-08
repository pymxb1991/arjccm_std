/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.service.entity.CcmServiceWechat;
import com.arjjs.ccm.modules.ccm.service.dao.CcmServiceWechatDao;

/**
 * 公众信息上报Service
 * @author fuxinshuang
 * @version 2018-03-17
 */
@Service
@Transactional(readOnly = true)
public class CcmServiceWechatService extends CrudService<CcmServiceWechatDao, CcmServiceWechat> {

	public CcmServiceWechat get(String id) {
		return super.get(id);
	}
	
	public List<CcmServiceWechat> findList(CcmServiceWechat ccmServiceWechat) {
		return super.findList(ccmServiceWechat);
	}
	
	public Page<CcmServiceWechat> findPage(Page<CcmServiceWechat> page, CcmServiceWechat ccmServiceWechat) {
		return super.findPage(page, ccmServiceWechat);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmServiceWechat ccmServiceWechat) {
		super.save(ccmServiceWechat);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmServiceWechat ccmServiceWechat) {
		super.delete(ccmServiceWechat);
	}
	
}