/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseSchoolrim;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgComprehensive;

/**
 * 综治机构DAO接口
 * @author liang
 * @version 2018-01-12
 */
@MyBatisDao
public interface CcmOrgComprehensiveDao extends CrudDao<CcmOrgComprehensive> {
	/**
	 * 新填officeId查询
	 * @param  
	 * @return
	 */
	public CcmOrgComprehensive findOfficeId(String officeId);
	// 更新点位坐标信息
	public int updateCoordinates(CcmOrgComprehensive ccmOrgComprehensive);
	//新添点位坐标信息
	public int insertCoordinates(CcmOrgComprehensive ccmOrgComprehensive);
}