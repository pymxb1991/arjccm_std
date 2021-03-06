/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.oa.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.oa.dao.OaNotifyDao;
import com.arjjs.ccm.modules.oa.dao.OaNotifyRecordDao;
import com.arjjs.ccm.modules.oa.entity.OaNotify;
import com.arjjs.ccm.modules.oa.entity.OaNotifyRecord;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 通知通告Service
 * @author admin001
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OaNotifyService extends CrudService<OaNotifyDao, OaNotify> {

	@Autowired
	private OaNotifyRecordDao oaNotifyRecordDao;

	public OaNotify get(String id) {
		OaNotify entity = dao.get(id);
		return entity;
	}
	
	/**
	 * 获取通知发送记录
	 * @param oaNotify
	 * @return
	 */
	public OaNotify getRecordList(OaNotify oaNotify) {
		oaNotify.setOaNotifyRecordList(oaNotifyRecordDao.findList(new OaNotifyRecord(oaNotify)));
		return oaNotify;
	}
	
	public Page<OaNotify> find(Page<OaNotify> page, OaNotify oaNotify) {
		oaNotify.setPage(page);
		page.setList(dao.findList(oaNotify));
		return page;
	}
	
	/**
	 * 获取通知数目
	 * @param oaNotify
	 * @return
	 */
	public Long findCount(OaNotify oaNotify) {
		return dao.findCount(oaNotify);
	}
	
	@Transactional(readOnly = false)
	public void save(OaNotify oaNotify) {
		super.save(oaNotify);
		
		// 更新发送接受人记录
		oaNotifyRecordDao.deleteByOaNotifyId(oaNotify.getId());
		if (oaNotify.getOaNotifyRecordList().size() > 0){
			oaNotifyRecordDao.insertAll(oaNotify.getOaNotifyRecordList());
		}
	}


	@Transactional(readOnly = false)
	public void save1(OaNotify oaNotify) {
		super.save(oaNotify);

		oaNotify.getOaNotifyRecordList().forEach(item->{
			item.setOaNotify(oaNotify);
		});
		// 更新发送接受人记录
		oaNotifyRecordDao.deleteByOaNotifyId(oaNotify.getId());
		if (oaNotify.getOaNotifyRecordList().size() > 0){
			oaNotify.getOaNotifyRecordList().forEach(item->{
				item.preInsert();
				oaNotifyRecordDao.insert(item);
			});
//			oaNotifyRecordDao.insertAll(oaNotify.getOaNotifyRecordList());
		}
	}
	
	/**
	 * 更新阅读状态
	 */
	@Transactional(readOnly = false)
	public void updateReadFlag(OaNotify oaNotify) {
		OaNotifyRecord oaNotifyRecord = new OaNotifyRecord();
		oaNotifyRecord.setOaNotify(oaNotify);
		oaNotifyRecord.setUser(StringUtils.isNotEmpty(oaNotify.getUserId()) ? new User(oaNotify.getUserId()) : UserUtils.getUser());
//		if (null != oaNotifyRecord.getCurrentUser()) {
//			oaNotifyRecord.setUser(oaNotifyRecord.getCurrentUser());
//		} else {
//            oaNotifyRecord.setUser(oaNotify.getCreateBy());
//		}
		oaNotifyRecord.setReadDate(new Date());
		oaNotifyRecord.setReadFlag("1");
		oaNotifyRecordDao.update(oaNotifyRecord);
	}

	//我的通告未读数量
	public int getNumOaNotifyRecord(OaNotifyRecord oaNotifyRecord) {
		return oaNotifyRecordDao.getNumOaNotifyRecord(oaNotifyRecord);
	}


	/**
	 * 我的通告查询appList
	 * @param oaNotify
	 * @return
	 */
	public Page<OaNotify> findApp(Page<OaNotify> page, OaNotify oaNotify) {
		oaNotify.setPage(page);
		page.setList(dao.findListApp(oaNotify));
		return page;
	}


	
	/**
	 * app跟新我的通告状态
	 */
	@Transactional(readOnly = false)
	public void updateReadFlag(OaNotify oaNotify, User user) {
		OaNotifyRecord oaNotifyRecord = new OaNotifyRecord(oaNotify);
		oaNotifyRecord.setUser(user);
		oaNotifyRecord.setReadDate(new Date());
		oaNotifyRecord.setReadFlag("1");
		oaNotifyRecordDao.update(oaNotifyRecord);		
	}
	
	//门户公告通知统计
	public List<OaNotify> findNotice(OaNotify oaNotify){
		return dao.findNotice(oaNotify);
	}
	public List<OaNotify> count(){
		OaNotify oaNotify = new OaNotify();
		oaNotify.setUserId(UserUtils.getUser().getId());
		return dao.count(oaNotify);
	}

	public List<OaNotify> queryNotifyList(String id,String userId) {
		return dao.queryNotifyList(id,userId);
	}

	public int selectNotifyTotal(String userId) {
		return dao.selectNotifyTotal(userId);
	}

	public int selectNotifyTodoCount(String userId) {
		return dao.selectNotifyTodoCount(userId);
	}

	@Transactional(readOnly = false)
	public int updateSatausByIdAndUserId(String id,String userId) {
		return dao.updateSatausByIdAndUserId(id,userId);
	}
}