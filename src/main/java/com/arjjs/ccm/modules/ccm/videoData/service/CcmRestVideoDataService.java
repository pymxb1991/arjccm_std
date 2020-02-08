package com.arjjs.ccm.modules.ccm.videoData.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.ccmsys.dao.CcmDeviceDao;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmDevice;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmDeviceArea;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgArea;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgAreaService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.ccm.sys.entity.SysConfig;
import com.arjjs.ccm.modules.ccm.sys.service.SysConfigService;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.Tool;
import com.google.common.collect.Lists;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;

@Service
@Transactional(readOnly = true)
public class CcmRestVideoDataService {
	@Autowired
	private SysConfigService sysConfigService;
	
	@Autowired
	private CcmDeviceDao ccmDeviceDao;
	
	@Autowired
	private CcmOrgAreaService ccmOrgAreaService;
	
	public CcmRestResult getHIKCameras() {
		CcmRestResult result = new CcmRestResult();
		SysConfig sysConfig = sysConfigService.get("hikvison_video_ocx_play");
		//解JSON
		JSONObject jsonObject = JSONObject.parseObject(sysConfig.getParamStr());
		String apiUrl = null;
		if(jsonObject.containsKey("apiUrl")) {			
			apiUrl = jsonObject.getString("apiUrl");
		}
		String appKey = null;
		if(jsonObject.containsKey("appKey")) {
			appKey = jsonObject.getString("appKey");
		}
		String appSecet = null;
		if(jsonObject.containsKey("appSecet")) {			
			appSecet = jsonObject.getString("appSecet");
		}
		if(StringUtils.isNotEmpty(apiUrl) && StringUtils.isNotEmpty(appKey) && StringUtils.isNotEmpty(appSecet)) {			
			if(isStopThread) {	
				HIKCamerasInsertThread hikCamerasInsertThread = new HIKCamerasInsertThread();
				hikCamerasInsertThread.start();
			}
			result.setCode(CcmRestType.OK);
			result.setResult(null);
		}else {
			result.setCode(CcmRestType.ERROR_PARAM);
			result.setResult(null);
		}
		return result;
	}
	
	private static boolean isStopThread = true;
	
	/**
	 * 能力开放平台的网站路径
	 * TODO 路径不用修改，就是/artemis
	 */
	private static final String ARTEMIS_PATH = "/artemis";
	
	class HIKCamerasInsertThread extends Thread {
		public void run() {
			isStopThread = false;
			SysConfig sysConfig = sysConfigService.get("hikvison_video_ocx_play");
			//解JSON
			JSONObject jsonObject = JSONObject.parseObject(sysConfig.getParamStr());
			String apiUrl = jsonObject.getString("apiUrl");
			String appKey = jsonObject.getString("appKey");
			String appSecet = jsonObject.getString("appSecet");
			int i = 0;
			boolean bool = false;
			try {
				while(!bool) {
					bool = this.getArtemis(apiUrl,appKey,appSecet,String.valueOf(i));
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				System.out.println("获取监控点结束");
				isStopThread = true;
			}
		}
		private List<CcmDeviceArea> areaList = Lists.newArrayList();
		public boolean getArtemis(String apiUrl,String appKey,String appSecet,String page) {
			boolean bool = false;
			ArtemisConfig.host = apiUrl; // 代理API网关nginx服务器ip端口
			ArtemisConfig.appKey = appKey;  // 秘钥appkey
			ArtemisConfig.appSecret = appSecet;// 秘钥appSecret
			final String getCamsApi = ARTEMIS_PATH + "/api/common/v1/remoteCameraInfoRestService/findCameraInfoPage";
			Map<String,String> querys = new HashMap<String,String>();//get请求的查询参数
	        querys.put("start", page);
	        querys.put("size", "20");
	        querys.put("order", "desc");
	        querys.put("orderby", "createTime");
	        Map<String, String> path = new HashMap<String, String>(2){
	        	{
	        		put("https://", getCamsApi);
	        	}
	        };
	        try {
	        	String result = ArtemisHttpUtil.doGetArtemis(path, querys,null,null,null);
	        	System.out.println(" ===== >>> " + result);
	        	JSONObject resJson = JSONObject.parseObject(result);
	        	if(resJson.containsKey("data") && StringUtils.isNotBlank(resJson.getString("data"))) {
	        		JSONArray dataJson = JSONArray.parseArray(resJson.getString("data"));
	        		if (dataJson.size() > 0) {
	        			areaList.clear();
						List<CcmOrgArea> orgAreaList = ccmOrgAreaService.getAreaMap(new CcmOrgArea());
						this.sortList(areaList, orgAreaList, "0", true);
	        			for (int i = 0; i < dataJson.size(); i++) {
	        				JSONObject deviceJson = dataJson.getJSONObject(i);
	        				String code = null;
	        				if(deviceJson.containsKey("indexCode")) {
	        					code = deviceJson.getString("indexCode");
	        				}
	        				if(StringUtils.isNotBlank(code)) {
	        					CcmDevice ccmDevice = ccmDeviceDao.getByCode(code);
	        					if(ccmDevice == null || "".equals(ccmDevice.getId())) {//通过code没有取到对应设备，再通过名称去取设备（现场网关对接会经常改code，故增加名称的更新）
	        						if(deviceJson.containsKey("name") && StringUtils.isNotBlank(deviceJson.getString("name"))) {
										CcmDevice ccmDeviceFrom = new CcmDevice();
										ccmDeviceFrom.setName(deviceJson.getString("name"));
										List<CcmDevice> ccmDeviceList = ccmDeviceDao.findList(ccmDeviceFrom);
										if (ccmDeviceList != null && ccmDeviceList.size() == 1) {//通过名称取到一条设备数据，则进行数据更新操作
											this.updateDevice(deviceJson, ccmDeviceList.get(0));
										} else {//code没有取到，名称没有取到，则新增数据
											this.insertDevice(deviceJson);
										}
									}
	        					}else {
	        						this.updateDevice(deviceJson, ccmDevice);
	        					}
	        				}
	        			}
	        		}else {
	        			bool = true;
	        		}
	        	}else {
	        		bool = true;
	        	}
	        }catch (Exception e) {
				e.printStackTrace();
				bool = true;
			}
	        return bool;
		}
		public void insertDevice(JSONObject deviceJson) {
			CcmDevice ccmDevice = new CcmDevice();
			ccmDevice.setId(UUID.randomUUID().toString());
			if(deviceJson.containsKey("indexCode")) {
				ccmDevice.setCode(deviceJson.getString("indexCode"));
			}
			if(deviceJson.containsKey("decodetag")) {
				ccmDevice.setCompanyId(deviceJson.getString("decodetag"));
			}
			if(deviceJson.containsKey("name")) {
				ccmDevice.setName(deviceJson.getString("name"));
			}
			if(deviceJson.containsKey("isOnline")) {
				ccmDevice.setStatus(deviceJson.getString("isOnline"));
			}
			String longitude = null;
			if(deviceJson.containsKey("longitude")) {
				longitude = deviceJson.getString("longitude");
			}
			String latitude = null;
			if(deviceJson.containsKey("latitude")) {
				latitude = deviceJson.getString("latitude");
			}
			String areaId = null;
			if(StringUtils.isNotBlank(longitude) && StringUtils.isNotBlank(latitude)) {	        				
				String coordinate = longitude + "," + latitude;
				ccmDevice.setCoordinate(coordinate);
				List<String> pointList = Lists.newArrayList();
				areaId = this.getDeviceAreaId(areaList, pointList, Double.parseDouble(longitude), Double.parseDouble(latitude));
			}
			if(deviceJson.containsKey("extraField") && StringUtils.isNotBlank(deviceJson.getString("extraField"))) {
				JSONObject extraField = deviceJson.getJSONObject("extraField");
				if(extraField.containsKey("chanNum")) {	        					
					ccmDevice.setChannelNum(extraField.getString("chanNum"));
				}
			}
			ccmDevice.setTypeId("003");
			ccmDevice.setImagePath("video.png");
			ccmDevice.setTypeVidicon("1");
			Area areaTmp = new Area();
			if(StringUtils.isBlank(areaId)) {
				areaId = Global.getConfig("DOCKING_MONITORING_POINT");
				if(StringUtils.isBlank(areaId)) {
					areaId = "0ac94bc554e241e9abeedcb982000003";
				}
			}
			areaTmp.setId(areaId);
			ccmDevice.setArea(areaTmp);
			User userTmp = new User();
			userTmp.setId("1");
			ccmDevice.setCreateBy(userTmp);
			ccmDevice.setCreateDate(new Date());
			ccmDevice.setUpdateBy(userTmp);
			ccmDevice.setUpdateDate(new Date());
			ccmDeviceDao.insert(ccmDevice);
		}
		public void updateDevice(JSONObject deviceJson,CcmDevice ccmDevice) {
			if(deviceJson.containsKey("indexCode") && StringUtils.isNotBlank(deviceJson.getString("indexCode"))) {
				ccmDevice.setCode(deviceJson.getString("indexCode"));
			}
			if(deviceJson.containsKey("decodetag") && StringUtils.isNotBlank(deviceJson.getString("decodetag"))) {
				ccmDevice.setCompanyId(deviceJson.getString("decodetag"));
			}
			if(deviceJson.containsKey("name") && StringUtils.isNotBlank(deviceJson.getString("name"))) {
				ccmDevice.setName(deviceJson.getString("name"));
			}
			if(deviceJson.containsKey("isOnline") && StringUtils.isNotBlank(deviceJson.getString("isOnline"))) {
				ccmDevice.setStatus(deviceJson.getString("isOnline"));
			}
			String longitude = null;
			if(deviceJson.containsKey("longitude")) {
				longitude = deviceJson.getString("longitude");
			}
			String latitude = null;
			if(deviceJson.containsKey("latitude")) {
				latitude = deviceJson.getString("latitude");
			}
			String areaId = null;
			if(StringUtils.isNotBlank(longitude) && StringUtils.isNotBlank(latitude)) {	        				
				String coordinate = longitude + "," + latitude;
				ccmDevice.setCoordinate(coordinate);
				List<String> pointList = Lists.newArrayList();
				areaId = this.getDeviceAreaId(areaList, pointList, Double.parseDouble(longitude), Double.parseDouble(latitude));
			}
			if(StringUtils.isBlank(areaId)) {
				areaId = Global.getConfig("DOCKING_MONITORING_POINT");
				if(StringUtils.isBlank(areaId)) {
					areaId = "0ac94bc554e241e9abeedcb982000003";
				}
			}
			if(deviceJson.containsKey("extraField") && StringUtils.isNotBlank(deviceJson.getString("extraField"))) {
				JSONObject extraField = deviceJson.getJSONObject("extraField");
				if(extraField.containsKey("chanNum") && StringUtils.isNotBlank(extraField.getString("chanNum"))) {	        					
					ccmDevice.setChannelNum(extraField.getString("chanNum"));
				}
			}
			if(StringUtils.isNotBlank(areaId)) {
				Area areaTmp = new Area();
				areaTmp.setId(areaId);
				ccmDevice.setArea(areaTmp);
			}
			ccmDevice.setTypeId("003");
			ccmDevice.setImagePath("video.png");
			ccmDevice.setTypeVidicon("1");
			User userTmp = new User();
			userTmp.setId("1");
			ccmDevice.setUpdateBy(userTmp);
			ccmDevice.setUpdateDate(new Date());
			ccmDeviceDao.update(ccmDevice);
		}
		
		public void sortList(List<CcmDeviceArea> list, List<CcmOrgArea> sourcelist, String parentId, boolean cascade) {
			for(int i = 0; i < sourcelist.size(); ++i) {
				CcmOrgArea e = (CcmOrgArea)sourcelist.get(i);
				if (e.getAreaParentId() != null && e.getAreaParentId().equals(parentId)) {
					CcmDeviceArea ccmDeviceArea = new CcmDeviceArea();
					ccmDeviceArea.setId(e.getAreaId());
					ccmDeviceArea.setAreaMap(e.getAreaMap());
					List<CcmDeviceArea> childrenList = Lists.newArrayList();
					ccmDeviceArea.setChildrenList(childrenList);
					list.add(ccmDeviceArea);
					if (cascade) {
						for(int j = 0; j < sourcelist.size(); ++j) {
							CcmOrgArea child = (CcmOrgArea)sourcelist.get(j);
							if (child.getAreaParentId() != null && child.getAreaParentId().equals(e.getAreaId())) {
								sortList(childrenList, sourcelist, e.getAreaId(), true);
								break;
							}
						}
					}
				}
			}
		}
		
		public String getDeviceAreaId(List<CcmDeviceArea> resultList, List<String> pointList, double lat, double lon) {
			String areaId = null;
			for (int i = 0; i < resultList.size(); i++) {
				CcmDeviceArea ccmDeviceArea = resultList.get(i);
				if(ccmDeviceArea != null) {
					List<CcmDeviceArea> childrenList = ccmDeviceArea.getChildrenList();
					if(childrenList.size() > 0) {
						areaId = getDeviceAreaId(childrenList,pointList,lat,lon);
						if(StringUtils.isBlank(areaId)) {
							areaId = isInPolygon(pointList, ccmDeviceArea, lat, lon);
						}
					}else {
						areaId = isInPolygon(pointList, ccmDeviceArea, lat, lon);
					}
					if(StringUtils.isNotBlank(areaId)) {
						break;
					}
				}
			}
			return areaId;
		}
		
		public String isInPolygon(List<String> pointList,CcmDeviceArea ccmDeviceArea, double lat, double lon) {
			boolean flag = false;
			String areaId = null;
			String areaMap = ccmDeviceArea.getAreaMap();
			if(StringUtils.isNotBlank(areaMap)) {
				String[] point = areaMap.split(";");
				pointList.addAll(Arrays.asList(point));
				double[] latList = new double[pointList.size()];
				double[] lonList = new double[pointList.size()];
				for(int i = 0; i < pointList.size(); i++) {
					String[] pointInfo = pointList.get(i).split(",");
					latList[i] = Double.valueOf(pointInfo[0]);
					lonList[i] = Double.valueOf(pointInfo[1]);
				}
				flag = Tool.isInPolygon(lon, lat, lonList, latList);
				if(flag) {
					areaId = ccmDeviceArea.getId();
				}
			}
			return areaId;
		}
	}
}
