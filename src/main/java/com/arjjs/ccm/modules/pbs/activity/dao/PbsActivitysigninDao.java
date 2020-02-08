/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivitysignin;

/**
 * 活动签到DAO接口
 * 
 * @author lc
 * @version 2018-05-15
 */
@MyBatisDao
public interface PbsActivitysigninDao extends CrudDao<PbsActivitysignin> {

	// 获取 人员数量
	public int getMemSum(PbsActivitysignin pbsActivitysignin);
	
	// 获取党员参加活动的数量
	public int getActivitySumByMemberId(String sSignbindmember);
	
	//插入所有补录人员的信息
	public int saveRecord(List<PbsActivitysignin> listSign);
}