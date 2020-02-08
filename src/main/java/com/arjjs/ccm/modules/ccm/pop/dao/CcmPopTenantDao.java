/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.pop.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPopTenant;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmAreaPointVo;
import com.arjjs.ccm.modules.flat.analyst.entity.Count;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.SearchTab;
import com.arjjs.ccm.tool.SearchTabMM;
import com.arjjs.ccm.tool.SearchTabMore;

import java.util.List;

/**
 * 房屋DAO接口
 * 
 * @author wwh
 * @version 2018-01-10
 */
@MyBatisDao
public interface CcmPopTenantDao extends CrudDao<CcmPopTenant> {
	/**
	 * 绩效统计接口
	 * @return
	 */
	List<Count> getByCount();

	List<CcmPopTenant> findAreaBuildTenantList(CcmPopTenant ccmPopTenant, String type);
	// 楼栋房屋弹框
	List<CcmPopTenant> getHouseList(CcmPopTenant ccmPopTenant);
	// 楼栋内重点人员房屋字符串
	List<String> getStringSpecial(CcmPopTenant ccmPopTenant);
	//首页社区弹框:出租屋总数
	List<CcmPopTenant> findHouseRentArea(CcmPopTenant ccmPopTenant);
	//报表:楼栋房屋
	List<SearchTabMore> findHouseAndBuild(SearchTabMore searchTabMore);
	//app接口使用，通过roomId查对应社区及网格id
	CcmPeople findAreaByRoom(CcmPopTenant ccmPopTenant);
	//街道信息-大屏-滨海新区社会网格化管理信息平台
	List<SearchTab> findListAllData(CcmPopTenant ccmPopTenant);
	//报表:房屋类型
	List<EchartType> findHouseType();
	//房屋总数
	int findListNum(CcmPopTenant ccmPopTenant);
	//房屋安全隐患统计
	List<EchartType> getHouseHazard();
	//出租房数量-社区
	int findListNumCommunity(CcmPopTenant ccmPopTenant);
	//重点人员More
	List<SearchTabMM> getStringSpecialMore(CcmPopTenant ccmPopTenantDto);
	//首页统计实有房屋数量
	List<CcmPopTenant> findCountByHouseType();
	//首页统计出租房屋数量
	/*List<CcmPopTenant> findCountByHousePrup();*/
	List<CcmPopTenant> findCountByRentPur();

	public List<String> selectByBuildIdAndName(CcmAreaPointVo areaPointVo);

	/**
	 * 根据单元id查询单元下房屋数量
	 * @param tranceId
	 * @return
	 */
	List<CcmPopTenant> findByTranceId(String tranceId);

	//查询按照单元号 楼门号排序
	List<CcmPopTenant> findListOrderby(CcmPopTenant ccmPopTenant);

	//查询房屋id
	List<CcmPopTenant> findListId(CcmPopTenant ccmPopTenant);

	//查询房屋列表
	List<CcmPopTenant> findList_V2(CcmPopTenant ccmPopTenant);

}