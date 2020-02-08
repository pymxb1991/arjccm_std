/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgCommonality;
import com.arjjs.ccm.tool.SearchTabMore;

/**
 * 公共机构管理DAO接口
 * @author liang
 * @version 2018-02-24
 */
@MyBatisDao
public interface CcmOrgCommonalityDao extends CrudDao<CcmOrgCommonality> {
	/**
	 * 生成公共机构地图信息-区域图
	 * @param  
	 * @return
	 */
	public List<CcmOrgCommonality> findCommonalityList(CcmOrgCommonality ccmOrgCommonality);
	
	public int updateCoordinates(CcmOrgCommonality ccmOrgCommonality);

	public SearchTabMore getCountInfo(CcmOrgCommonality ccmOrgCommonality);
	 
}