/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.citycomponents.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.citycomponents.entity.CcmCityComponents;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmAreaPoint;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmAreaPointVo;
import com.arjjs.ccm.modules.flat.analyst.entity.Count;
import com.arjjs.ccm.tool.EchartType;

import java.util.List;

/**
 * 城市部件DAO接口
 * @author pengjianqiang
 * @version 2018-03-06
 */
@MyBatisDao
public interface CcmCityComponentsDao extends CrudDao<CcmCityComponents> {

	List<Count> getByCount();

	public int updateCoordinates(CcmCityComponents ccmCityComponents);

	//公用设施
	public List<EchartType> getCityTypeGY(CcmCityComponents ccmCityComponentsCY);

	//城市部件数量
	public int findListNum();

	public List<CcmAreaPoint> selectByAreaGIdAndName(CcmAreaPointVo areaPointVo);
}