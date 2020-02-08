package com.arjjs.ccm.tool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgArea;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgAreaService;
import com.arjjs.ccm.modules.flat.alarm.service.BphAlarmInfoService;
import com.arjjs.ccm.modules.flat.realtimeSituation.entity.PeopleData;
import com.arjjs.ccm.modules.flat.rest.service.FlatRestService;
import com.arjjs.ccm.modules.flat.userBindingDevice.entity.UserBindingDevice;
import com.arjjs.ccm.modules.flat.userBindingDevice.service.UserBindingDeviceService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.google.common.collect.Maps;

public class CacheTableData {

	@Autowired
	private UserBindingDeviceService  userBindingDeviceService;
	@Autowired
	private CcmOrgAreaService ccmOrgAreaService;
	@Autowired
	private BphAlarmInfoService bphAlarmInfoService;
	public static Map<String, UserBindingDevice> USER_DEV_CODE_NAME = new HashMap<String, UserBindingDevice>();
	public static Map<String, String> AREA_ID_POINTS = Maps.newConcurrentMap();
	public static Map<String, Office> AREA_ID_OFFICE = Maps.newConcurrentMap();
	
	/**
	 * 
	 * @throws Exception
	 * @作者及日起 刘聪  2018-11-21
	 */
	
	public void runAllMethod() throws Exception {
		getUserDevData();
		getAllAreaPoints();
		getAllAreaIdOfficeId();
	}
	
	public void getUserDevData() throws Exception{
		for(Entry<String, PeopleData> entry : FlatRestService.peoPleMap.entrySet()) {
			if(!USER_DEV_CODE_NAME.containsKey(entry.getKey())) {
				FlatRestService.peoPleMap.remove(entry.getKey());
			}
		}
		List<UserBindingDevice>  userDevList = userBindingDeviceService.findListAllCode(new UserBindingDevice());
		USER_DEV_CODE_NAME.clear();
		for(UserBindingDevice userBindingDevice : userDevList) {
			if(StringUtils.isNotBlank(userBindingDevice.getUserId())) {
				if("0".equals(userBindingDevice.getDefualtDevice())) {
					USER_DEV_CODE_NAME.put(userBindingDevice.getUserId(), userBindingDevice);
				}
				if("1".equals(userBindingDevice.getDefualtDevice())) {
					USER_DEV_CODE_NAME.put(userBindingDevice.getUserId(), userBindingDevice);
				}
				if("2".equals(userBindingDevice.getDefualtDevice())) {
					USER_DEV_CODE_NAME.put(userBindingDevice.getUserId(), userBindingDevice);
				}
			}
		}
	}
	
	public void getAllAreaPoints() throws Exception{
		List<CcmOrgArea> findList = ccmOrgAreaService.findList(new CcmOrgArea());
		AREA_ID_POINTS.clear();
		for(CcmOrgArea area : findList) {
			if(StringUtils.isNotBlank(area.getAreaId()) && StringUtils.isNotBlank(area.getAreaMap())) {				
				AREA_ID_POINTS.put(area.getAreaId(), area.getAreaMap());
			}
		}
	}
	
	public void getAllAreaIdOfficeId() throws Exception{
		List<Office> findList = bphAlarmInfoService.findOfficeAllList("2");
		AREA_ID_OFFICE.clear();
		for(Office office : findList) {
			AREA_ID_OFFICE.put(office.getArea().getId(), office);
		}
	}
}
