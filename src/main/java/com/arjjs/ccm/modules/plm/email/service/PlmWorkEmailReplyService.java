/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.email.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.email.dao.PlmWorkEmailReplyDao;
import com.arjjs.ccm.modules.plm.email.entity.PlmWorkEmailReply;

/**
 * 内部邮件回复Service
 * @author fu
 * @version 2018-08-02
 */
@Service
@Transactional(readOnly = true)
public class PlmWorkEmailReplyService extends CrudService<PlmWorkEmailReplyDao, PlmWorkEmailReply> {

	public PlmWorkEmailReply get(String id) {
		return super.get(id);
	}
	
	public List<PlmWorkEmailReply> findList(PlmWorkEmailReply plmWorkEmailReply) {
		return super.findList(plmWorkEmailReply);
	}
	
	public Page<PlmWorkEmailReply> findPage(Page<PlmWorkEmailReply> page, PlmWorkEmailReply plmWorkEmailReply) {
		return super.findPage(page, plmWorkEmailReply);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmWorkEmailReply plmWorkEmailReply) {
		super.save(plmWorkEmailReply);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmWorkEmailReply plmWorkEmailReply) {
		super.delete(plmWorkEmailReply);
	}
	
}