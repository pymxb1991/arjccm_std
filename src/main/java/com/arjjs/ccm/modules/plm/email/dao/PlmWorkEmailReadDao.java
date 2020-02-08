/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.plm.email.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.email.entity.PlmWorkEmailRead;

/**
 * 工作日志记录DAO接口
 * @author arj
 * @version 2018-03-08
 */
@MyBatisDao
public interface PlmWorkEmailReadDao extends CrudDao<PlmWorkEmailRead> {
	
	/**
	 * 插入工作记录
	 * @param oaNotifyRecordList
	 * @return
	 */
	public int insertAll(List<PlmWorkEmailRead> plmWorkEmailReadList);
	
	/**
	 * 根据通知ID删除工作记录
	 * @param oaNotifyId 通知ID
	 * @return
	 */
	public int deleteByWorkReportId(String plmWorkEmailId);

	public void insertCaoGaoAll(List<PlmWorkEmailRead> plmWorkEmailReadList);

	public void insertFaSongAll(List<PlmWorkEmailRead> plmWorkEmailSReadList);

	public void selfDelete(PlmWorkEmailRead plmWorkEmailRead);


	public void selfStar(PlmWorkEmailRead plmWorkEmailRead);

}