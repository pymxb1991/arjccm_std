/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.oa.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.oa.entity.OaMessageRecord;

/**
 * 通知通告记录DAO接口
 * @author admin001
 * @version 2014-05-16
 */
@MyBatisDao
public interface OaMessageRecordDao extends CrudDao<OaMessageRecord> {

	/**
	 * 插入通知记录
	 * @param oaMessageRecordList
	 * @return
	 */
	public int insertAll(List<OaMessageRecord> oaMessageRecordList);
	
	/**
	 * 根据通知ID删除通知记录
	 * @param oaMessageId 通知ID
	 * @return
	 */
	public int deleteByOaMessageId(String oaMessageId);

	//我的通告未读数量
	public int getNumOaMessageRecord(OaMessageRecord oaMessageRecord);
	
}