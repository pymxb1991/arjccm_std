/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.map.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.map.entity.BindCount;
import com.arjjs.ccm.modules.pbs.map.entity.PbsGeographical;

/**
 * 地图信息表DAO接口
 * @author lc
 * @version 2018-04-02
 */
@MyBatisDao
public interface PbsGeographicalDao extends CrudDao<PbsGeographical> {
	// 获取所有党支部关系数量
	List<BindCount> getPartymembindCount();
	
	//根据类型和关联id 判断是否被标记过
	int findByTypeAndKey(PbsGeographical pbsGeographical);
	
	//查询每种类型在地图上面的数量
	List<Integer> getNumByType();
}