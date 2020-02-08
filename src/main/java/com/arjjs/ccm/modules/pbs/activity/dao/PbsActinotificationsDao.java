/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActinotifications;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityrec;

/**
 * 活动通知DAO接口
 * 
 * @author lc
 * @version 2018-05-14
 */
@MyBatisDao
public interface PbsActinotificationsDao extends CrudDao<PbsActinotifications> {

	public void insertAll(List<PbsActinotifications> list);

	// 删除整个活动的所有通知
	public void DeleteByActivityid(PbsActivityrec sActivityid);

	// 更新查看的状态
	public void updatesStat(PbsActinotifications pbsActinotifications);
}