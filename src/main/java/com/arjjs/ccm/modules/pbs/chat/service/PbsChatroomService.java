/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.chat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.chat.entity.PbsChatroom;
import com.arjjs.ccm.modules.pbs.chat.dao.PbsChatroomDao;

/**
 * 聊天室Service
 * @author lc
 * @version 2018-06-22
 */
@Service
@Transactional(readOnly = true)
public class PbsChatroomService extends CrudService<PbsChatroomDao, PbsChatroom> {

	public PbsChatroom get(String id) {
		return super.get(id);
	}
	
	public List<PbsChatroom> findList(PbsChatroom pbsChatroom) {
		return super.findList(pbsChatroom);
	}
	
	public Page<PbsChatroom> findPage(Page<PbsChatroom> page, PbsChatroom pbsChatroom) {
		return super.findPage(page, pbsChatroom);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsChatroom pbsChatroom) {
		super.save(pbsChatroom);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsChatroom pbsChatroom) {
		super.delete(pbsChatroom);
	}
	
}