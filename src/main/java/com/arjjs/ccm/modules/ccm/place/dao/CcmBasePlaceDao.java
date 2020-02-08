/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.place.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.place.entity.CcmBasePlace;
import com.arjjs.ccm.tool.EchartType;

import java.util.List;

/**
 * 场所基本信息表DAO接口
 * @author lgh
 * @version 2019-04-23
 */
@MyBatisDao
public interface CcmBasePlaceDao extends CrudDao<CcmBasePlace> {

	// 更新楼栋坐标点信息
	public boolean updateCoordinates(CcmBasePlace c);

    public List<EchartType> getImportByKeyPoint();

	/**
	 * 场所类型统计
	 * @return
	 */
	public List<EchartType> getcountbyplaceType();

}