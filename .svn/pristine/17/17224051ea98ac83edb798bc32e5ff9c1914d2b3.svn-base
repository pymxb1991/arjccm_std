/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmDevice;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmAreaPoint;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmAreaPointVo;
import com.arjjs.ccm.modules.ccm.videoData.entity.CcmTiandyOnlineStatus;
import com.arjjs.ccm.tool.EchartType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备管理DAO接口
 * 
 * @author arj
 * @version 2018-01-25
 */
@MyBatisDao
public interface CcmDeviceDao extends CrudDao<CcmDevice> {
	public int updateCoordinates(CcmDevice ccmDevice);
	
	public int updateDeviceArea(CcmDevice ccmDevice);

	public CcmDevice getByIp(String ip);
	
	public CcmDevice getByCode(String code);

	public List<EchartType> selectByInstallType();

	public List<EchartType> selectByCreateDate();

	public List<EchartType> selectByType();

	public List<EchartType> selectDeviceByArea();

	public List<EchartType> selectDeviceByAreaYa();

    public List<CcmAreaPoint> selectByAreaGIdAndName(CcmAreaPointVo areaPointVo);

	List<CcmTiandyOnlineStatus> findIdAndStatus();

	public int updateDevStatus(@Param("id") String id, @Param("status")String status);
}