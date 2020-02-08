package com.arjjs.ccm.modules.flat.rest.service;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

import com.arjjs.ccm.modules.flat.realtimeSituation.entity.PeopleData;
import com.arjjs.ccm.modules.flat.userBindingDevice.entity.UserBindingDevice;
import com.arjjs.ccm.tool.CacheTableData;

import net.sf.json.JSONObject;

@Service
public class FlatRestService {
	public static ConcurrentMap<String, PeopleData> peoPleMap = new ConcurrentHashMap<String, PeopleData>();
	
	public void receiveData(String param) {
		JSONObject jsonObject = JSONObject.fromObject(param);
		String devCode = "";
		if(jsonObject.containsKey("devCode")) {
			devCode = jsonObject.getString("devCode");
		}
		for(Entry<String, UserBindingDevice> entry : CacheTableData.USER_DEV_CODE_NAME.entrySet()) {
			PeopleData peopleData = new PeopleData();
			if("0".equals(entry.getValue().getDefualtDevice())) {//警务通
				if(devCode.equals(entry.getValue().getPolicePhoneCode())) {
					peopleData.setCode(devCode);
					peopleData.setParam(entry.getValue().getParam());
					if(jsonObject.containsKey("devType")) {
						peopleData.setDevType(jsonObject.getString("devType"));
					}
					if(jsonObject.containsKey("x")) {
						peopleData.setX(jsonObject.getString("x"));
					}
					if(jsonObject.containsKey("y")) {
						peopleData.setY(jsonObject.getString("y"));
					}
					if(jsonObject.containsKey("timeOccurs")) {
						peopleData.setTimeOccurs(jsonObject.getString("timeOccurs"));
					}
					peopleData.setUserId(entry.getValue().getUserId());
					peopleData.setName(entry.getValue().getName());
					peopleData.setPhone(entry.getValue().getPhone());
					peopleData.setMobile(entry.getValue().getMobile());
					peopleData.setOfficeName(entry.getValue().getOffice().getName());
					peoPleMap.put(entry.getKey(), peopleData);
				}
			}else if("1".equals(entry.getValue().getDefualtDevice())) {//执法记录仪
				if(devCode.equals(entry.getValue().getActionRecoderCode())) {
					peopleData.setCode(devCode);
					if(jsonObject.containsKey("devType")) {
						peopleData.setDevType(jsonObject.getString("devType"));
					}
					if(jsonObject.containsKey("x")) {
						peopleData.setX(jsonObject.getString("x"));
					}
					if(jsonObject.containsKey("y")) {
						peopleData.setY(jsonObject.getString("y"));
					}
					if(jsonObject.containsKey("timeOccurs")) {
						peopleData.setTimeOccurs(jsonObject.getString("timeOccurs"));
					}
					peopleData.setUserId(entry.getValue().getUserId());
					peopleData.setName(entry.getValue().getName());
					peopleData.setPhone(entry.getValue().getPhone());
					peopleData.setMobile(entry.getValue().getMobile());
					peopleData.setOfficeName(entry.getValue().getOffice().getName());
					peoPleMap.put(entry.getKey(), peopleData);
				}
			}else if("2".equals(entry.getValue().getDefualtDevice())) {//对讲
				if(devCode.equals(entry.getValue().getInterPhoneCode())) {
					peopleData.setCode(devCode);
					if(jsonObject.containsKey("devType")) {
						peopleData.setDevType(jsonObject.getString("devType"));
					}
					if(jsonObject.containsKey("x")) {
						peopleData.setX(jsonObject.getString("x"));
					}
					if(jsonObject.containsKey("y")) {
						peopleData.setY(jsonObject.getString("y"));
					}
					if(jsonObject.containsKey("timeOccurs")) {
						peopleData.setTimeOccurs(jsonObject.getString("timeOccurs"));
					}
					peopleData.setUserId(entry.getValue().getUserId());
					peopleData.setName(entry.getValue().getName());
					peopleData.setPhone(entry.getValue().getPhone());
					peopleData.setMobile(entry.getValue().getMobile());
					peopleData.setOfficeName(entry.getValue().getOffice().getName());
					peoPleMap.put(entry.getKey(), peopleData);
				}
			}
		}
	}
}
