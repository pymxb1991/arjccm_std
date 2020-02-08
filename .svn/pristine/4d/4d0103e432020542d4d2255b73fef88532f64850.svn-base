/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.message.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.message.entity.CcmMessage;

import java.util.List;

/**
 * 消息通知dao
 * @author lhf
 * @version 2019-10-23
 */
@MyBatisDao
public interface CcmMessageDao extends CrudDao<CcmMessage> {

	void insertEventAll(List<CcmMessage> list);

    List<CcmMessage> getListTodayAndUnread(String userId);


    List<CcmMessage> getListTodayAndUnreadBymessage(CcmMessage ccmMessage);

}