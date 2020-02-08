/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.ccmsys.dao.CcmDeviceDao;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmDevice;
import com.arjjs.ccm.modules.ccm.org.service.SysAreaService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmAreaPoint;
import com.arjjs.ccm.modules.ccm.sys.dao.SysConfigDao;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmAreaPointVo;
import com.arjjs.ccm.modules.ccm.sys.entity.SysConfig;
import com.arjjs.ccm.modules.ccm.videoData.entity.CcmTiandyOnlineStatus;
import com.arjjs.ccm.tool.EchartType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 设备管理Service
 * @author arj
 * @version 2018-01-25
 */
@Service
@Transactional(readOnly = true)
public class CcmDeviceService extends CrudService<CcmDeviceDao, CcmDevice> {

	@Autowired
	private CcmDeviceDao ccmDeviceDao;

	@Autowired
	private SysConfigDao sysConfigDao;
	@Autowired
	private SysAreaService sysAreaService;

	public CcmDevice get(String id) {
		return super.get(id);
	}
	
	public List<CcmDevice> findList(CcmDevice ccmDevice) {
		return super.findList(ccmDevice);
	}
	
	public Page<CcmDevice> findPage(Page<CcmDevice> page, CcmDevice ccmDevice) {
		return super.findPage(page, ccmDevice);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmDevice ccmDevice) {
		super.save(ccmDevice);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmDevice ccmDevice) {
		super.delete(ccmDevice);
	}
	
	@Transactional(readOnly = false)
	public boolean updateCoordinates(CcmDevice ccmDevice){
		return ccmDeviceDao.updateCoordinates(ccmDevice)>0;
	}
	
	@Transactional(readOnly = false)
	public boolean updateDeviceArea(CcmDevice ccmDevice){
		return ccmDeviceDao.updateDeviceArea(ccmDevice)>0;
	}

	public CcmDevice getByIp(String ip) {
		return ccmDeviceDao.getByIp(ip);
	}
	
	public CcmDevice getByCode(String cameraCode) {
		return ccmDeviceDao.getByCode(cameraCode);
	}

	//<!-- 视频安装范围分类 -->
	public List<EchartType> selectByInstallType(){
		return dao.selectByInstallType();
	}
	//	<!-- 视频每年布控趋势 -->
	public List<EchartType> selectByCreateDate(){
		return  dao.selectByCreateDate();
	}
	//<!-- 监控设备类型 -->
	public List<EchartType> selectByType(){
		return  dao.selectByType();
	}
	// <!-- 视频区域分布-->
	public List<EchartType> selectDeviceByArea(){

		SysConfig system_level = sysConfigDao.get("system_level");
		//系统应用级别：1街道；2区县；3市
		String paramStr = system_level.getParamStr();
		List<EchartType> resultEchar = new ArrayList<>();
		List<String> diffrent= new ArrayList<>();
		if ("1".equals(paramStr)){//地级市
			List<EchartType> echartTypeList = dao.selectDeviceByArea();
			List<String> sysAreas = sysAreaService.selectAreaByType("6");
			List<String> newStr= new ArrayList<>();
			if (echartTypeList.size() != sysAreas.size()){
				for (EchartType echartType:  echartTypeList) {
					resultEchar.add(echartType);
					newStr.add(echartType.getType());
				}
				diffrent  = getDiffrent(newStr,sysAreas);
				System.out.println(diffrent);

			}
		}
		if ("2".equals(paramStr)){//地级市
			List<EchartType> echartTypeList = dao.selectDeviceByAreaYa();
			List<String> sysAreas = sysAreaService.selectAreaByType("5");
			List<String> newStr= new ArrayList<>();
			if (echartTypeList.size() != sysAreas.size()){
				for (EchartType echartType:  echartTypeList) {
					resultEchar.add(echartType);
					newStr.add(echartType.getType());
				}
				diffrent  = getDiffrent(newStr,sysAreas);
				System.out.println(diffrent);

			}
		}
		for (String str:diffrent) {
			EchartType echartType = new EchartType();
			echartType.setType(str);
			echartType.setValue("0");
			resultEchar.add(echartType);
		}
		return  resultEchar;
	}
	private static List<String> getDiffrent(List<String> list1, List<String> list2) {
		long st = System.nanoTime();
		Map<String,Integer> map = new HashMap<String,Integer>(list1.size()+list2.size());
		List<String> diff = new ArrayList<String>();
		for (String string : list1) {
			map.put(string, 1);
		}
		for (String string : list2) {
			Integer cc = map.get(string);
			if(cc!=null){
				map.put(string, ++cc);
				continue;
			}
			map.put(string, 1);
		}
		for(Map.Entry<String, Integer> entry:map.entrySet()){
			if(entry.getValue()==1){
				diff.add(entry.getKey());
			}
		}
		return diff;
	}

	public List<CcmAreaPoint> selectByAreaGIdAndName(CcmAreaPointVo areaPointVo) {
		return dao.selectByAreaGIdAndName(areaPointVo);
	}
	List<CcmTiandyOnlineStatus> findIdAndStatus() {
		return dao.findIdAndStatus();
	}
}