/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.sys.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsRemindMsg;

/**
 * 消息提醒信息DAO接口
 * @author lc
 * @version 2018-08-02
 */
@MyBatisDao
public interface PbsRemindMsgDao extends CrudDao<PbsRemindMsg> {
	
	// 获取当前的通知数
	public int getCountNotice(PbsRemindMsg pbsRemindMsg);
	
	// 更新当前的消息
	public void updateStat(PbsRemindMsg pbsRemindMsg);
}