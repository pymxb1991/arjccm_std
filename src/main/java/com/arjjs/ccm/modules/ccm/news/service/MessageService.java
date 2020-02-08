/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.news.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.news.entity.Message;
import com.arjjs.ccm.modules.ccm.news.dao.MessageDao;

/**
 * 消息管理Service
 * @author cby
 * @version 2019-11-28
 */
@Service
@Transactional(readOnly = true)
public class MessageService extends CrudService<MessageDao, Message> {

	public Message get(String id) {
		return super.get(id);
	}
	
	public List<Message> findList(Message message) {
		return super.findList(message);
	}
	
	public Page<Message> findPage(Page<Message> page, Message message) {
		return super.findPage(page, message);
	}
	
	@Transactional(readOnly = false)
	public void save(Message message) {
		super.save(message);
	}
	
	@Transactional(readOnly = false)
	public void delete(Message message) {
		super.delete(message);
	}
	
}