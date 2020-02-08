/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.oa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.oa.dao.OaMessageDao;
import com.arjjs.ccm.modules.oa.dao.OaMessageRecordDao;
import com.arjjs.ccm.modules.oa.entity.OaMessage;
import com.arjjs.ccm.modules.oa.entity.OaMessageRecord;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

/**
 * 我的消息Service
 * @author admin001
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OaMessageService extends CrudService<OaMessageDao, OaMessage> {

	@Autowired
	private OaMessageRecordDao oaMessageRecordDao;

	public OaMessage get(String id) {
		OaMessage entity = dao.get(id);
		return entity;
	}
	
	/**
	 * 获取通知发送记录
	 * @param oaMessage
	 * @return
	 */
	public OaMessage getRecordList(OaMessage oaMessage) {
		oaMessage.setOaMessageRecordList(oaMessageRecordDao.findList(new OaMessageRecord(oaMessage)));
		return oaMessage;
	}
	
	public Page<OaMessage> find(Page<OaMessage> page, OaMessage oaMessage) {
		oaMessage.setPage(page);
		page.setList(dao.findList(oaMessage));
		return page;
	}
	
	/**
	 * 获取通知数目
	 * @param oaMessage
	 * @return
	 */
	public Long findCount(OaMessage oaMessage) {
		return dao.findCount(oaMessage);
	}
	
	@Transactional(readOnly = false)
	public void save(OaMessage oaMessage) {
		super.save(oaMessage);
		
		// 更新发送接受人记录
		oaMessageRecordDao.deleteByOaMessageId(oaMessage.getId());
		if (oaMessage.getOaMessageRecordList().size() > 0){
			oaMessageRecordDao.insertAll(oaMessage.getOaMessageRecordList());
		}
	}
	
	/**
	 * 更新阅读状态
	 */
	@Transactional(readOnly = false)
	public void updateReadFlag(OaMessage oaMessage) {
		OaMessageRecord oaMessageRecord = new OaMessageRecord(oaMessage);
		oaMessageRecord.setUser(oaMessageRecord.getCurrentUser());
		oaMessageRecord.setReadDate(new Date());
		oaMessageRecord.setReadFlag("1");
		oaMessageRecordDao.update(oaMessageRecord);
	}

	//我的通告未读数量
	public int getNumOaMessageRecord(OaMessageRecord oaMessageRecord) {
		return oaMessageRecordDao.getNumOaMessageRecord(oaMessageRecord);
	}


	/**
	 * 我的通告查询appList
	 * @param oaMessage
	 * @return
	 */
	public Page<OaMessage> findApp(Page<OaMessage> page, OaMessage oaMessage) {
		oaMessage.setPage(page);
		page.setList(dao.findListApp(oaMessage));
		return page;
	}


	
	/**
	 * app跟新我的通告状态
	 */
	@Transactional(readOnly = false)
	public void updateReadFlag(OaMessage oaMessage, User user) {
		OaMessageRecord oaMessageRecord = new OaMessageRecord(oaMessage);
		oaMessageRecord.setUser(user);
		oaMessageRecord.setReadDate(new Date());
		oaMessageRecord.setReadFlag("1");
		oaMessageRecordDao.update(oaMessageRecord);		
	}
	
	//门户公告通知统计
	public List<OaMessage> findNotice(OaMessage oaMessage){
		return dao.findNotice(oaMessage);
	}
	public List<OaMessage> count(){
		OaMessage oaMessage = new OaMessage();
		oaMessage.setUserId(UserUtils.getUser().getId());
		return dao.count(oaMessage);
	}
}