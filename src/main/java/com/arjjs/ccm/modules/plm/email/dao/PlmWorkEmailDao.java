/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.plm.email.dao;


import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.email.entity.PlmWorkEmail;

/**
 * 工作日志DAO接口
 * @author arj
 * @version 2018-03-08
 */
@MyBatisDao
public interface PlmWorkEmailDao extends CrudDao<PlmWorkEmail> {
	
	/**
	 * 获取工作日志数目
	 * @param ccmWorkReport
	 * @return
	 */
	public Long findCount(PlmWorkEmail ccmWorkReport);
	/**
	 * 查询已删除
	 * @param plmWorkEmail
	 * @return
	 */
	public List<PlmWorkEmail> findDefList(PlmWorkEmail plmWorkEmail);
	/**
	 * 已删除后彻底删除，def_flag改为2
	 * @param plmWorkEmail
	 */
	public void delete2(PlmWorkEmail plmWorkEmail);
	/**
	 * 查星标邮件
	 * @param plmWorkEmail
	 * @return
	 */
	public List<PlmWorkEmail> findStarList(PlmWorkEmail plmWorkEmail);
	/**
	 * 修改星标
	 * @param plmWorkEmail
	 */
	public void star(PlmWorkEmail plmWorkEmail);
	/**
	 * 查收件箱总数量
	 * @param plmWorkEmailNew
	 * @return 
	 */
	public List<Integer> findReceiveCount(PlmWorkEmail plmWorkEmailNew);


}