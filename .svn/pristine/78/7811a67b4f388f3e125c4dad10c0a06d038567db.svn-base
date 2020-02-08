/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityrec;

/**
 * 活动信息DAO接口
 * 
 * @author lc
 * @version 2018-05-14
 */
@MyBatisDao
public interface PbsActivityrecDao extends CrudDao<PbsActivityrec> {

	// 更新 签到人数
	public void updateNum(PbsActivityrec pbsActivityrec);

	// 根据收到通知的人 获取 活动信息
	public List<PbsActivityrec> findListBysAcceptorid(PbsActivityrec pbsActivityrec);
	
	
}