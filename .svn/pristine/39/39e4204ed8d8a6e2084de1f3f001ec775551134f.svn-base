/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.oa.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.oa.entity.OaNotify;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通知通告DAO接口
 * @author admin001
 * @version 2014-05-16
 */
@MyBatisDao
public interface OaNotifyDao extends CrudDao<OaNotify> {
	
	/**
	 * 获取通知数目
	 * @param oaNotify
	 * @return
	 */
	public Long findCount(OaNotify oaNotify);
	/**
	 * 我的通告查询appList
	 * @param oaNotify
	 * @return
	 */
	public List<OaNotify> findListApp(OaNotify oaNotify);
	
	//门户公告通知统计
	public List<OaNotify> findNotice(OaNotify oaNotify);
	public List<OaNotify> count(OaNotify oaNotify);

	public List<OaNotify> queryNotifyList(@Param("id") String id, @Param("userId") String userId);

	int selectNotifyTotal(@Param("userId") String userId);

	int selectNotifyTodoCount(@Param("userId") String userId);

	int updateSatausByIdAndUserId(@Param("id") String id,@Param("userId") String userId);
}