/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.oa.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.oa.entity.OaMessage;

/**
 * 通知通告DAO接口
 * @author admin001
 * @version 2014-05-16
 */
@MyBatisDao
public interface OaMessageDao extends CrudDao<OaMessage> {
	
	/**
	 * 获取通知数目
	 * @param oaMessage
	 * @return
	 */
	public Long findCount(OaMessage oaMessage);
	/**
	 * 我的通告查询appList
	 * @param oaMessage
	 * @return
	 */
	public List<OaMessage> findListApp(OaMessage oaMessage);
	
	//门户公告通知统计
	public List<OaMessage> findNotice(OaMessage oaMessage);
	public List<OaMessage> count(OaMessage oaMessage);
	
}