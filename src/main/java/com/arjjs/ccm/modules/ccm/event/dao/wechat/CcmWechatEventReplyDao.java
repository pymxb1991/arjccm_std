/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.dao.wechat;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.event.entity.wechat.CcmWechatEventReply;

/**
 * 微信信息回复DAO接口
 * @author fu
 * @version 2018-04-14
 */
@MyBatisDao
public interface CcmWechatEventReplyDao extends CrudDao<CcmWechatEventReply> {
	
}