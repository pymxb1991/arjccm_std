package com.arjjs.ccm.modules.ccm.house.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arjjs.ccm.common.utils.SpringContextHolder;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.house.entity.CcmExpireArea;
import com.arjjs.ccm.modules.ccm.house.entity.CcmExpireUser;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseSetting;
import com.arjjs.ccm.modules.ccm.house.entity.CcmIntervalPeople;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseEmphasisService;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.modules.ccm.message.entity.CcmMessage;
import com.arjjs.ccm.modules.ccm.message.service.CcmMessageService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.rest.web.CcmRestEvent;
import com.arjjs.ccm.modules.ccm.sys.entity.SysConfig;
import com.arjjs.ccm.modules.ccm.sys.service.SysConfigService;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.CommUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 方法描述：重点人员模块
 * @author liu
 * @version 2019-4-10
 */
@Controller
@RequestMapping(value="${adminPath}/house/ccmHouseEmphasis")
public class CcmHouseEmphasisController {
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private CcmMessageService ccmMessageService;
	@Autowired
	private CcmHouseEmphasisService ccmHouseEmphasisService;
	/**
	 * 方法描述：
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "tableName")
	public Object findTableNameInDict(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmHouseEmphasisService emphasisService = SpringContextHolder.getBean("CcmHouseEmphasisService");
		return emphasisService.findTableName();
	}
	
	/**
	 * 方法描述：根据数据库table名称获取列表数据
	 * @param tableType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="emphasis")
	public Object findEmphasisDataByTableName(CcmPeople ccmPeople) {
		CcmHouseEmphasisService emphasisService = SpringContextHolder.getBean("CcmHouseEmphasisService");
		return emphasisService.findEmphasisDataByTableName(ccmPeople);
	}
	/**
	 * 描述：走访记录
	 * @param ccmLogTail
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "visitRecord")
	public Object findVisitRecord(CcmLogTail ccmLogTail) {
		CcmHouseEmphasisService emphasisService = SpringContextHolder.getBean("CcmHouseEmphasisService");
		return emphasisService.findVisitRecord(ccmLogTail);
	}
	
	@RequestMapping(value = "listSet")
	public String listSet(String type, Model model) {
		if(StringUtils.isBlank(type)) {
			type = "ccmHouseKym";
		}
		SysConfig sysConfig = sysConfigService.get("key_personnel_notice_info_setting");
		CcmHouseSetting ccmHouseSetting = new CcmHouseSetting();
		ccmHouseSetting.setType(type);
		String sendInfo = null;
		if(sysConfig != null) {
			String paramStr = sysConfig.getParamStr();
			if(StringUtils.isBlank(paramStr)) {
				paramStr = "{}";
			}
			if(!CommUtil.isJsonObject(paramStr)) {
				paramStr = "{}";
			}
			JSONObject paramObject = JSONObject.parseObject(paramStr);
			if(paramObject != null && paramObject.containsKey(type)) {
				JSONObject jsonObject = paramObject.getJSONObject(type);
				if(jsonObject.containsKey("sendInfo")) {
					sendInfo = jsonObject.getString("sendInfo");
				}
				if(jsonObject.containsKey("timeInterval")) {
					String timeInterval = jsonObject.getString("timeInterval");
					ccmHouseSetting.setTimeInterval(timeInterval);
				}
			}
			ccmHouseSetting.setParamStr(paramStr);
			ccmHouseSetting.setId(sysConfig.getId());
			ccmHouseSetting.setRemarks(sysConfig.getRemarks());
		}else {
			ccmHouseSetting.setParamStr("{}");
			ccmHouseSetting.setId("key_personnel_notice_info_setting");
			ccmHouseSetting.setRemarks("");
		}
		if(StringUtils.isBlank(sendInfo)) {
			sendInfo = "2";
		}
		ccmHouseSetting.setSendInfo(sendInfo);
		model.addAttribute("ccmHouseSetting", ccmHouseSetting);
		return "ccm/house/emphasis/ccmHosueSetting";
	}
	@RequestMapping(value = "listSetTest")
	public List<CcmExpireArea> listSetTest() {
		CcmHouseEmphasisService emphasisService = SpringContextHolder.getBean("CcmHouseEmphasisService");
		List<CcmExpireArea> userList = emphasisService.findExpireUser();
		return userList;
	}
	
	public void testList() {
		System.out.println("测试===>>>"+new Date());
	}
	
	public void listSetting() {
		SysConfig sysConfig = sysConfigService.get("key_personnel_notice_info_setting");
		if(sysConfig != null) {
			JSONObject paramObject = JSONObject.parseObject(sysConfig.getParamStr());
			Iterator<String> it = paramObject.keySet().iterator();
			Map<String,List<CcmIntervalPeople>> mapList = Maps.newHashMap();
			while (it.hasNext()) {
				String type = it.next();
				JSONObject jsonObject = paramObject.getJSONObject(type);
				if(jsonObject.containsKey("sendInfo")) {
					String sendInfo = jsonObject.getString("sendInfo");
					if(StringUtils.isNotBlank(sendInfo) && sendInfo.equals("1")) {	
						if(jsonObject.containsKey("timeInterval")) {
							String timeInterval = jsonObject.getString("timeInterval");
							if(StringUtils.isNotBlank(timeInterval)) {								
								CcmPeople ccmPeople = new CcmPeople();
								int interval = Integer.parseInt(timeInterval); 
								ccmPeople.setTimeInterval(interval);
								List<CcmIntervalPeople> list = Lists.newArrayList();
								String typeNmae = null;
								String tableName = null;
								if(type.equals("ccmHouseAids")) {
									typeNmae = "艾滋病患者";
									tableName = "ccm_house_aids";
								}else if(type.equals("ccmHouseRelease")) {
									typeNmae = "安置帮教";
									tableName = "ccm_house_release";
								}else if(type.equals("ccmHouseDangerous")) {
									typeNmae = "危险品从业者";
									tableName = "ccm_house_dangerous";
								}else if(type.equals("ccmSeriousCriminalOffense")) {
									typeNmae = "严重刑事犯罪活动嫌疑";
									tableName = "ccm_serious_criminal_offense";
								}else if(type.equals("ccmHarmNationalSecurity")) {
									typeNmae = "危害国家安全活动嫌疑";
									tableName = "ccm_harm_national_security";
								}else if(type.equals("ccmHouseKym")) {
									typeNmae = "重点青少年";
									tableName = "ccm_house_kym";
								}else if(type.equals("ccmHouseDeliberatelyIllegal")) {
									typeNmae = "故意违法刑释不足5年";
									tableName = "ccm_house_deliberately_illegal";
								}else if(type.equals("ccmHouseDispute")) {
									typeNmae = "闹事行凶报复嫌疑";
									tableName = "ccm_house_dispute";
								}else if(type.equals("ccmHouseDrugs")) {
									typeNmae = "吸毒人员";
									tableName = "ccm_house_drugs";
								}else if(type.equals("ccmHousePetition")) {
									typeNmae = "重点上访";
									tableName = "ccm_house_petition";
								}else if(type.equals("ccmHousePsychogeny")) {
									typeNmae = "肇事肇祸等严重精神障碍患者";
									tableName = "ccm_house_psychogeny";
								}else if(type.equals("ccmHouseRectification")) {
									typeNmae = "社区矫正";
									tableName = "ccm_house_rectification";
								}else if(type.equals("ccmHouseHeresy")) {
									typeNmae = "涉教人员";
									tableName = "ccm_house_heresy";
								}else {
									continue;
								}
								ccmPeople.setTableName(tableName);
								list = ccmHouseEmphasisService.findExpirePeople(ccmPeople);
								if(list.size() > 0) {
									for (CcmIntervalPeople ccmIntervalPeople : list) {
										String gridId = ccmIntervalPeople.getGridId();
										ccmIntervalPeople.setTypeName(typeNmae);
										ccmIntervalPeople.setTableName(tableName);
										if(mapList.containsKey(gridId)) {
											mapList.get(gridId).add(ccmIntervalPeople);
										}else {
											List<CcmIntervalPeople> datalist = Lists.newArrayList();
											datalist.add(ccmIntervalPeople);
											mapList.put(gridId, datalist);
										}
									}
								}
							}
						}
					}
				}
			}
			Iterator<String> itMap = mapList.keySet().iterator();
			while (itMap.hasNext()) {
				String gridId = itMap.next();
				List<CcmIntervalPeople> resultlist = mapList.get(gridId);
				if(resultlist.size() > 0) {
					List<CcmMessage> list = new ArrayList<CcmMessage>();
					for (CcmIntervalPeople ccmIntervalPeople : resultlist) {
						CcmPeople ccmPeople = new CcmPeople();
						ccmPeople.setId(ccmIntervalPeople.getPeopleId());
						List<CcmExpireUser> CcmExpireUserList = ccmHouseEmphasisService.findUserByPeople(ccmPeople);
						for (CcmExpireUser ccmExpireUser : CcmExpireUserList) {							
							CcmMessage ccmMessage = new CcmMessage();
							User user = new User("1");
							ccmMessage.setCreateBy(user);
							ccmMessage.setUpdateBy(user);
							ccmMessage.setType("23");//通知消息
							Date createDate = new Date();
							String str = "yyyy-MM-dd";
							SimpleDateFormat sdf = new SimpleDateFormat(str);
							ccmMessage.setContent(sdf.format(createDate)+"："+"寻访通知："+ccmIntervalPeople.getPeopleName()+"["+ccmIntervalPeople.getTypeName()+"]");
							ccmMessage.setReadFlag("0");//未读
							ccmMessage.setObjId(ccmIntervalPeople.getKeyId());
							ccmMessage.preInsert();
							ccmMessage.setUserId(ccmExpireUser.getUserId());
							list.add(ccmMessage);
						}						
						ccmHouseEmphasisService.updateIntervalDate(ccmIntervalPeople.getKeyId(),ccmIntervalPeople.getTableName());
					}
					if(list.size() > 0) {						
						//批量添加
						ccmMessageService.insertEventAll(list);
						//发送mq
						CcmRestEvent.sendMessageToMq(list);
					}
				}
			}
		}
	}
}
