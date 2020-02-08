/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildentrance;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildentranceVo;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildmanage;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPopTenantVo;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmAreaPointVo;
import com.arjjs.ccm.tool.SearchTab;
import com.arjjs.ccm.tool.SearchTabMore;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 楼栋DAO接口
 * 
 * @author wwh
 * @version 2018-01-05
 */
@MyBatisDao
public interface CcmHouseBuildmanageDao extends CrudDao<CcmHouseBuildmanage> {

	// 返回 特殊人群 OR 出租屋 的楼栋接口
	public List<String> findBuildListBySpecilPop(CcmPeople ccmPeople);

	// 返回 出租房屋的楼栋接口 （减少查询成本）
	public List<String> findBuildListByPrup();

	// 返回 基础 Map 用于 楼栋查询结果
	@MapKey("id")
	public Map<String, CcmHouseBuildmanage> findMap(CcmHouseBuildmanage c);

	// 更新楼栋坐标点信息
	public boolean updateCoordinates(CcmHouseBuildmanage c);

	// 获取流动人员的热力图信息
	public List<CcmHouseBuildmanage> getBuildByImmigration(SearchTabMore searchTabMore);

	//街道信息-大屏-滨海新区社会网格化管理信息平台
	public List<SearchTab> findListAllData(CcmHouseBuildmanage ccmHouseBuildmanage);

	//楼栋总数
	public int findListNum(CcmHouseBuildmanage ccmHouseBuildmanage);

	//默认全部的重点人员类型
	public List<String> findBuildListBySpecilPopUNION(CcmPeople cmPeopleDto);

	//单元
	public int findListNumDanYuan(CcmHouseBuildmanage ccmHouseBuildmanage);
	
	//首页统计地址库信息
	List<CcmHouseBuildmanage> findCountAddressLibrary();
	
	//根据建筑id获取单元数据
	public List<CcmHouseBuildentrance> findBuildentrance(CcmHouseBuildentrance ccmHouseBuildentrance);

	//列表不关联区域楼幢集合
	public List<CcmHouseBuildmanage> queryList(CcmHouseBuildmanage ccmHouseBuildmanage);

	//根据建筑id获取单元数据列表
	public List<CcmHouseBuildentranceVo> selectBuildentranceById(@Param("buildId") String buildId,@Param("tranceId")String tranceId);

	public List<CcmPopTenantVo> selectPopTenantByBuildAndDoorNum(@Param("buildId") String buildId, @Param("doorNum")String doorNum);

	public List<CcmHouseBuildmanage> selectByAreaGIdAndName(CcmAreaPointVo areaPointVo);

	public List<CcmHouseBuildmanage> selectByAreaId(@Param("areaId") String areaId);

	//查询楼栋id
	List<CcmHouseBuildmanage> findListId(CcmHouseBuildmanage ccmHouseBuildmanage);

	//查询楼栋列表
	List<CcmHouseBuildmanage> findList_V2(CcmHouseBuildmanage ccmHouseBuildmanage);
}