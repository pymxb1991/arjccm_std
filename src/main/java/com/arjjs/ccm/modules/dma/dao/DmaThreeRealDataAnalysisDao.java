package com.arjjs.ccm.modules.dma.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.BaseDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.dma.entity.ResidentStatisticsCount;
import com.arjjs.ccm.tool.EchartType;

/**
 * 三实数据分析数据访问层/持久层
 * @author jiaopanyu
 * @version 2019-09-04
 */
@MyBatisDao
public interface DmaThreeRealDataAnalysisDao extends BaseDao {
	/**
	 * 各区域人口类型统计
	 * @param dateYear
	 * @return
	 */
	public List<ResidentStatisticsCount> findResidentData(String dateYear);
	/**
	 * 各区域人口性别统计
	 * @param dateYear
	 * @return
	 */
	public List<ResidentStatisticsCount> findSexData(String dateYear);
	/**
	 * 房屋产权类别分布统计
	 * @param type
	 * @return
	 */
	public List<EchartType> findHousePrupData(String type);
	/**
	 * 房屋建筑用途分布统计
	 * @param type
	 * @return
	 */
	public List<EchartType> findHousePropertyData(String type);
	/**
	 * 房屋建成年限统计
	 * @param type
	 * @return
	 */
	public List<EchartType> findHouseYearData(String type);
	/**
	 * 房屋区域分布统计
	 * @param dateYear
	 * @return
	 */
	public List<ResidentStatisticsCount> findHouseAreaData(String dateYear);
	/**
	 * 房屋结构类型统计
	 * @param type
	 * @return
	 */
	public List<EchartType> findHouseStructureData(String type);
}
