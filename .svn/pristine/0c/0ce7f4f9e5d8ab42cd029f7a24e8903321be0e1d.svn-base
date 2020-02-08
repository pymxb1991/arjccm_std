/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventIncident;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmAreaPoint;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmAreaPointVo;
import com.arjjs.ccm.modules.dma.eventheme.entity.CountEventByReportTypeVo;
import com.arjjs.ccm.modules.dma.eventheme.entity.EventIncidentVo;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.EchartForce;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.SearchTab;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 案事件登记DAO接口
 * 
 * @author arj
 * @version 2018-01-10
 */
@MyBatisDao
public interface CcmEventIncidentDao extends CrudDao<CcmEventIncident> {

	// 查询相关案事件
	List<CcmEventIncident> findOtherId(CcmEventIncident ccmEventIncident);
	
	List<CcmEventIncident> getIncidents(CcmEventIncident ccmEventIncident);
	
	List<String> getIncidentPlace(CcmEventIncident ccmEventIncident);
	
	void deleteIncidentPlace(CcmEventIncident ccmEventIncident);
	
	void saveIncidentPlace(CcmEventIncident ccmEventIncident);

	/**
	 * @see 根据案事件性质统计情况。
	 * @return 
	 */
	List<EchartType> getItemByProperty();
	
	/**
	 * @see 根据案事件性质统计学校周边情况。
	 * @return 
	 */
	List<EchartType> getItemByPropertyec();

	/**
	 * @see /根据案事件分级统计情况
	 * @return
	 */
	List<EchartType> getItemByScale();


	/**
	 * @see 查询出所有的 案事件根据地区以及分级数量
	 * @param ccmEventIncident 案事件实体
	 * @return
	 */
	List<EchartType> getItemByScaleTable(CcmEventIncident ccmEventIncident);

	/**
	 * @see 案事件统计 是否解决
	 * @return
	 */
	List<EchartType> getItemBySum();

	/**
	 * @see 前10的地区破案率 
	 * @return
	 */
	List<EchartType> findSolveByArea();

	/**
	 * @see 按月统计的案事件数  
	 * @return
	 */
	List<EchartType> findSumByMon(CcmEventIncident ccmEventIncident);
 
	/**
	 * @see 按月统计的处理率 
	 * @return
	 */
	List<EchartType> findSolveByMon();

	/**
	 * @see 最新案事件
	 * @return
	 */
	List<SearchTab> findSumByCondition();

	/**
	 * @see 案事件类型统计
	 * @return
	 */
	List<EchartType> findSumByEventType();
	
	//线路相关案事件
	public List<CcmEventIncident> findPageLine(CcmEventIncident ccmEventIncident);
	
	//师生相关案事件
	public List<CcmEventIncident> findPageStudent(CcmEventIncident ccmEventIncident);

	//命案案事件
	public List<CcmEventIncident> findPageMurder(CcmEventIncident ccmEventIncident);

	// 获取一周内的数量
	public List<EchartType> findSumByEventWeek();

	//今日案事件统计
	List<EchartType> getItemBySumToday();

	//今日前10条案事件
	List<CcmEventIncident> getListToday();

	//安全事故分布
	List<EchartType> getSafeDisData(CcmEventIncident ccmEventIncident);

	//事故类型
	List<EchartType> getSafeTypeData(CcmEventIncident ccmEventIncident);

	//事故级别
	List<EchartType> getSafeLevelData(CcmEventIncident ccmEventIncident);
	
	//安全生产事故类型
	List<EchartType> getSafeAnalysisType(CcmEventIncident ccmEventIncident);

	//安全生产事故级别
	List<EchartType> getSafeAnalysisLevel(CcmEventIncident ccmEventIncident);

	//事件分类
	List<EchartType> findEventFenLeiData();
	
	//事件规模统计
	List<EchartType> findEventGuiMoData();
	//事件规模统计总数
	EchartType findAllEventNum();

	//事件查询Line
	CcmEventIncident getLine(String id);

	//本月事件发生前十
	List<EchartType> findEventMonthMap();

	//近一年事件发生/重点人员帮扶趋势图
	List<SearchTab> findEventYearMap();
	
	//首页统计护线护路数量
	List<CcmEventIncident> findCountLineProtect();
	
	//首页校园师生安全
	List<CcmEventIncident> findCountSchoolPeople();
	

	List<CcmEventIncident> queryAlarmInfoByDateAndAlarmType(CcmEventIncident ccmEventIncident);
	
	List<CcmEventIncident> queryAlarmInfoByDateAndAlarmTypeToFourColor(CcmEventIncident ccmEventIncident);
	
	int incident(CcmEventIncident ccmEventIncident);
	
	public int insert(CcmEventIncident ccmEventIncident);

	public List<EchartType> getHomicideByArea(CcmEventIncident ccmEventIncident);
	// 根据上报类型统计各类型案件数量
	public List<CountEventByReportTypeVo> countEventPreviewByReportType(@Param("beginHappenDate") String beginHappenDate, @Param("endHappenDate") String endHappenDate);
	// 实际发生各类型案件数量
	public List<CountEventByReportTypeVo> countEventByReportType(@Param("beginHappenDate") String beginHappenDate, @Param("endHappenDate") String endHappenDate);

	// 查询所有事件
	List<EventIncidentVo> queryAll(@Param("beginHappenDate") String beginHappenDate, @Param("endHappenDate") String endHappenDate);

	public EchartForce getEventForForce(CcmEventIncident ccmEventIncident);

	//查询事件数量  12个月
	List<EchartType> getcountevent(String num);

	List<Office> findOfficeList(User user);

    public List<CcmAreaPoint> selectByAreaIdAndName(CcmAreaPointVo areaPointVo);

	public List<CcmEventIncident> findDispatch(CcmEventIncident ccmEventIncident);

	public List<CcmEventIncident> findArchive(CcmEventIncident ccmEventIncident);
}