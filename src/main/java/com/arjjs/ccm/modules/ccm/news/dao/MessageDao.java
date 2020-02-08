/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.news.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.news.entity.Message;

/**
 * 消息管理DAO接口
 * @author cby
 * @version 2019-11-28
 */
@MyBatisDao
public interface MessageDao extends CrudDao<Message> {
	
}