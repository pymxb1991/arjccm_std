/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.chat.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.chat.entity.PbsChatroom;

/**
 * 聊天室DAO接口
 * @author lc
 * @version 2018-06-22
 */
@MyBatisDao
public interface PbsChatroomDao extends CrudDao<PbsChatroom> {
	
}