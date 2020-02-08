/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildmanageUnit;

/**
 * 楼栋单元户信息排列DAO接口
 * @author liu
 * @version 2019-04-24
 */
@MyBatisDao
public interface CcmHouseBuildmanageUnitDao extends CrudDao<CcmHouseBuildmanageUnit> {
	List<CcmHouseBuildmanageUnit> findListByBuildmanageId (@Param(value = "buildmanageId") String buildmanageId);
	int saveData(List<CcmHouseBuildmanageUnit> ccmHouseBuildmanageUnit);
	int deleteHouseBuildmanageUnit(@Param(value = "buildmanageId") String buildmanageId);
}