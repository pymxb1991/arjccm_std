/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHousePsychogeny;

/**
 * 肇事肇祸等严重精神障碍患者DAO接口
 * @author arj
 * @version 2018-01-05
 */
@MyBatisDao
public interface CcmHousePsychogenyDao extends CrudDao<CcmHousePsychogeny> {
	/**
	 *  
	 * @param  
	 * @return
	 */
	public CcmHousePsychogeny getItemByPeopleId(String id);
}