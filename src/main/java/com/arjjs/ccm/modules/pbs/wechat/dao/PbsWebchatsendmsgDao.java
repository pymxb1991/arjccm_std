/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.wechat.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.wechat.entity.PbsWebchatsendmsg;

/**
 * 微信消息DAO接口
 * 
 * @author lc
 * @version 2018-06-29
 */
@MyBatisDao
public interface PbsWebchatsendmsgDao extends CrudDao<PbsWebchatsendmsg> {
	// 返回 一个月发送的次数进行 限制
	public int findSum();
}