/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.handle.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.rest.entity.*;
import com.arjjs.ccm.modules.dma.eventheme.entity.CountOfficeEventEntity;
import com.arjjs.ccm.modules.flat.alarm.entity.BphAlarmInfo;
import com.arjjs.ccm.modules.flat.alarm.service.BphAlarmInfoService;
import com.arjjs.ccm.modules.flat.alarmhandlelog.entity.BphAlarmHandleInfo;
import com.arjjs.ccm.modules.flat.alarmhandlelog.entity.BphAlarmHandleLog;
import com.arjjs.ccm.modules.flat.alarmhandlelog.entity.BphTaskContent;
import com.arjjs.ccm.modules.flat.alarmhandlelog.entity.BphTaskData;
import com.arjjs.ccm.modules.flat.alarmhandlelog.service.BphAlarmHandleLogService;
import com.arjjs.ccm.modules.flat.alarmnotify.entity.BphAlarmNotify;
import com.arjjs.ccm.modules.flat.alarmnotify.service.BphAlarmNotifyService;
import com.arjjs.ccm.modules.flat.handle.dao.BphAlarmHandleDao;
import com.arjjs.ccm.modules.flat.handle.entity.*;
import com.arjjs.ccm.modules.flat.tree.dao.FlatTreeDao;
import com.arjjs.ccm.modules.sys.dao.UserDao;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.CretaCode;
import com.arjjs.ccm.tool.gcj02_wgs84.Gps;
import com.arjjs.ccm.tool.gcj02_wgs84.PositionUtil;
import com.google.common.collect.Lists;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 出警信息Service
 *
 * @author liu
 * @version 2018-11-22
 */
@Service
@Transactional(readOnly = true)
public class BphAlarmHandleService extends CrudService<BphAlarmHandleDao, BphAlarmHandle> {

	private final static Logger log = LoggerFactory.getLogger(BphAlarmHandleService.class);

	@Autowired
	UserDao userDao;
	@Autowired
	FlatTreeDao flatTreeDao;
	@Autowired
	BphAlarmHandleLogService bphAlarmHandleLogService;
	@Autowired
	BphAlarmInfoService bphAlarmInfoService;
	@Autowired
	private BphAlarmNotifyService bphAlarmNotifyService;

	public BphAlarmHandle get(String id) {
		return super.get(id);
	}

	public List<BphAlarmHandle> findList(BphAlarmHandle bphAlarmHandle) {
		return super.findList(bphAlarmHandle);
	}

	public Page<BphAlarmHandle> findPage(Page<BphAlarmHandle> page, BphAlarmHandle bphAlarmHandle) {
		return super.findPage(page, bphAlarmHandle);
	}

	@Transactional(readOnly = false)
	public void save(BphAlarmHandle bphAlarmHandle) {
		super.save(bphAlarmHandle);
	}

	@Transactional(readOnly = false)
	public void delete(BphAlarmHandle bphAlarmHandle) {
		super.delete(bphAlarmHandle);
	}

	@Transactional(readOnly = false)
	public boolean saveHandle(BphAlarmHandleReceive bphAlarmHandleReceive) {
		List<String> lists = Arrays.asList(bphAlarmHandleReceive.getUserId().split(","));
		User user = new User();
		BphAlarmHandleLog bphAlarmHandleLog = new BphAlarmHandleLog();
		bphAlarmHandleLog.setAlarmId(bphAlarmHandleReceive.getAlarmId());
		bphAlarmHandleLog.setUser(UserUtils.getUser());
		Date date = new Date();
		date.setTime(date.getTime() + 1000);
		bphAlarmHandleLog.setOperateTime(date);
		bphAlarmHandleLog.setOperateDesc(bphAlarmHandleReceive.getOptionDesc());
		bphAlarmHandleLogService.save(bphAlarmHandleLog);

		if ("1".equals(bphAlarmHandleReceive.getType())) {// 通知
			for (String s : lists) {
				//添加警情通知内容
				BphAlarmInfo bphAlarmInfo = bphAlarmInfoService.get(bphAlarmHandleReceive.getAlarmId());
				if ("0".equals(bphAlarmInfo.getState()) || null == bphAlarmInfo.getState()) {
					bphAlarmInfo.setState("1");
					bphAlarmInfo.setOptionDesc("发送出警信息");
					bphAlarmInfoService.updateAlarmInfo(bphAlarmInfo);
				}
				if (bphAlarmInfo != null) {
					BphAlarmNotify bphAlarmNotify = new BphAlarmNotify();
					bphAlarmNotify.setId(UUID.randomUUID().toString());
					bphAlarmNotify.setAlarmId(bphAlarmHandleReceive.getAlarmId());
					bphAlarmNotify.setReceiveUserId(s);
					bphAlarmNotify.setType(bphAlarmInfo.getTypeCode());
					bphAlarmNotify.setTitle("警情通知");
					bphAlarmNotify.setContent(bphAlarmHandleReceive.getContent());
					bphAlarmNotify.setStatus("0");
					bphAlarmNotify.setDelFlag("0");
					bphAlarmNotify.setCreateBy(UserUtils.getUser());
					bphAlarmNotify.setCreateDate(new Date());
					bphAlarmNotify.setUpdateBy(UserUtils.getUser());
					bphAlarmNotify.setUpdateDate(new Date());
					bphAlarmNotify.setRemarks(bphAlarmInfo.getRemarks());
					bphAlarmNotify.setIsNewRecord(true);
					bphAlarmNotify.setPlanId(bphAlarmHandleReceive.getPlanId());
					bphAlarmNotify.setStepId(bphAlarmHandleReceive.getStepId());
					bphAlarmNotify.setActionId(bphAlarmHandleReceive.getActionId());
					bphAlarmNotifyService.save(bphAlarmNotify);
				}
				return true;
			}
		} else if ("2".equals(bphAlarmHandleReceive.getType())) {// 任务类
			BphTaskData bphTaskData = new BphTaskData();//返回的对象
			bphTaskData.setType("2");
			BphTaskContent bphTaskContent = new BphTaskContent();
			bphTaskContent.setRetCode("0");
			bphTaskContent.setRetMessage("SUCCESS");
			List<BphAlarmHandleInfo> list = Lists.newArrayList();
			BphAlarmInfo bphAlarmInfo = bphAlarmInfoService.get(bphAlarmHandleReceive.getAlarmId());
			BphAlarmHandleInfo bphAlarmHandleInfo = new BphAlarmHandleInfo();
			bphAlarmHandleInfo.setHandleTask(bphAlarmHandleReceive.getTask());
			bphAlarmHandleInfo.setHandleDestinyX(bphAlarmHandleReceive.getDestinyX() + "");
			bphAlarmHandleInfo.setHandleDestinyY(bphAlarmHandleReceive.getDestinyY() + "");
			bphAlarmHandleInfo.setAlarmContent(bphAlarmHandleReceive.getContent());
			bphAlarmHandleInfo.setAlarmId(bphAlarmHandleReceive.getAlarmId());
			bphAlarmHandleInfo.setAlarmOrderNum(bphAlarmInfo.getOrderNum());
			bphAlarmHandleInfo.setAlarmPoliceNum(bphAlarmInfo.getPoliceNum());
			bphAlarmHandleInfo.setAlarmPoliceName(bphAlarmInfo.getPoliceName());
			bphAlarmHandleInfo.setAlarmManName(bphAlarmInfo.getManName());
			bphAlarmHandleInfo.setAlarmManSex(bphAlarmInfo.getManSex());
			bphAlarmHandleInfo.setAlarmManTel(bphAlarmInfo.getManTel());
			bphAlarmHandleInfo.setAlarmPlace(bphAlarmInfo.getPlace());
			bphAlarmHandleInfo.setAlarmX(bphAlarmInfo.getX() + "");
			bphAlarmHandleInfo.setAlarmY(bphAlarmInfo.getY() + "");
			bphAlarmHandleInfo.setAlarmZ(bphAlarmInfo.getZ() + "");
			bphAlarmHandleInfo.setAlarmTypeCode(bphAlarmInfo.getTypeCode());
			bphAlarmHandleInfo.setAlarmGenreCode(bphAlarmInfo.getGenreCode());
			bphAlarmHandleInfo.setAlarmClassCode(bphAlarmInfo.getClassCode());
			bphAlarmHandleInfo.setAlarmOfficeId(bphAlarmInfo.getOfficeId());
			bphAlarmHandleInfo.setAlarmFrom(bphAlarmInfo.getAlarmFrom());
			bphAlarmHandleInfo.setAlarmTime(bphAlarmInfo.getAlarmTime().getTime() + "");
			bphAlarmHandleInfo.setAlarmRecord(bphAlarmInfo.getAlarmRecord());
			bphAlarmHandleInfo.setAlarmIsImportant(bphAlarmInfo.getIsImportant());
			bphAlarmHandleInfo.setAlarmState(bphAlarmInfo.getState());
			Office office = bphAlarmInfo.getOffice();
			if (office!=null ){
				String officeName = bphAlarmInfo.getOffice().getName();
				if (!officeName.isEmpty()) {
					bphAlarmHandleInfo.setOfficeName(bphAlarmInfo.getOffice().getName());
				}
			}
			//bphAlarmHandleInfo.setOfficeName(bphAlarmInfo.getOffice().getName());
			bphAlarmHandleInfo.setTypeName(DictUtils.getDictLabel(bphAlarmInfo.getTypeCode(), "bph_alarm_info_typecode", ""));
			list.add(bphAlarmHandleInfo);
			bphTaskContent.setResultList(list);
			bphTaskData.setUserId(lists);
			bphTaskData.setContent(bphTaskContent);
			JSONObject paramData = JSONObject.fromObject(bphTaskData);
			String retCode = bphAlarmHandleLogService.sendMessageApp(paramData);
			if ("0".equals(retCode)) {
				if ("0".equals(bphAlarmInfo.getState()) || null == bphAlarmInfo.getState()) {
					bphAlarmInfo.setState("1");
					bphAlarmInfo.setOptionDesc("发送出警信息");
					bphAlarmInfoService.updateAlarmInfo(bphAlarmInfo);
				}
				for (String s : lists) {
					BphAlarmHandle bphalarmHandle = new BphAlarmHandle();
					user = userDao.get(s);
					bphalarmHandle.setHandleCode(CretaCode.getCreateCode(user.getLoginName()));
					bphalarmHandle.setHandlePoliceId(s);
					bphalarmHandle.setAlarmId(bphAlarmHandleReceive.getAlarmId());
					bphalarmHandle.setTask(bphAlarmHandleReceive.getTask());
					bphalarmHandle.setDestinyX(Double.valueOf(bphAlarmHandleReceive.getDestinyX()));
					bphalarmHandle.setDestinyY(Double.valueOf(bphAlarmHandleReceive.getDestinyY()));
					bphalarmHandle.setPlanId(bphAlarmHandleReceive.getPlanId());
					bphalarmHandle.setStepId(bphAlarmHandleReceive.getStepId());
					bphalarmHandle.setActionId(bphAlarmHandleReceive.getActionId());
					bphalarmHandle.setStatus("0");
					bphalarmHandle.setDispatchTime(date);
					save(bphalarmHandle);
				}
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public Object fingListByAlarmId(String alarmId) {
		BphAlarmHandle bphAlarmHandle = new BphAlarmHandle();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("area") || name.equals("createBy") || name.equals("updateBy")
						|| name.equals("currentUser") || name.equals("page") || name.equals("sqlMap")
						|| name.equals("global") || name.equals("updateDate") || name.equals("createDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		bphAlarmHandle.setAlarmId(alarmId);
		BphAlarmNotify bphAlarmNotify = new BphAlarmNotify();
		bphAlarmNotify.setAlarmId(alarmId);
		List<BphAlarmNotify> list1 = bphAlarmNotifyService.findList(bphAlarmNotify);
		List<BphAlarmHandle> list = dao.fingListByAlarmId(bphAlarmHandle);
		List<BphAlarmHandle> resultPlanInfo = new ArrayList<>();
		list1.forEach(alarmNotify->{
			BphAlarmHandle alarmHandle = new BphAlarmHandle();
			//UUID.randomUUID().toString()
			if("1".equals(alarmNotify.getStatus())){
				alarmNotify.setStatus("5");
			}else{
				alarmNotify.setStatus("4");
			}
			BeanUtils.copyProperties(alarmNotify,alarmHandle);
			resultPlanInfo.add(alarmHandle);
		});
		if (list.size() > 0){
			resultPlanInfo.addAll(list);
		}
		return JSONArray.fromObject(resultPlanInfo, jsonConfig);
	}

	public List<BphAlarmHandleTimeCount> findHandleTimeCount() {
		List<BphAlarmHandle> list = dao.findHandleTimeCount();
		List<BphAlarmHandleTimeCount> lists = Lists.newArrayList();
		for (int i = 0; i < list.size(); i++) {
			BphAlarmHandleTimeCount bphAlarmHandleTimeCount = new BphAlarmHandleTimeCount();
			bphAlarmHandleTimeCount.setOfficeId(list.get(i).getOfficeId());
			bphAlarmHandleTimeCount.setOfficeName(list.get(i).getOfficeName());
			bphAlarmHandleTimeCount.setVALUE0(list.get(i).getVALUE0());
			bphAlarmHandleTimeCount.setVALUE1(list.get(i).getVALUE1());
			bphAlarmHandleTimeCount.setVALUE2(list.get(i).getVALUE2());
			bphAlarmHandleTimeCount.setVALUE3(list.get(i).getVALUE3());
			bphAlarmHandleTimeCount.setVALUE4(list.get(i).getVALUE4());
			bphAlarmHandleTimeCount.setVALUE5(list.get(i).getVALUE5());
			bphAlarmHandleTimeCount.setVALUE6(list.get(i).getVALUE6());
			lists.add(bphAlarmHandleTimeCount);
		}
		return lists;
	}

	public Object planManagerDetails(String actionId, String alarmId) {
		BphAlarmHandle bphAlarmHandle = new BphAlarmHandle();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("area") || name.equals("createBy") || name.equals("updateBy")
						|| name.equals("currentUser") || name.equals("page") || name.equals("sqlMap")
						|| name.equals("global") || name.equals("updateDate") || name.equals("createDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		bphAlarmHandle.setActionId(actionId);
		bphAlarmHandle.setAlarmId(alarmId);
		List<BphAlarmHandle> list = dao.planManagerDetails(bphAlarmHandle);
		return JSONArray.fromObject(list, jsonConfig);
	}

	public List<BphAlarmHandle> findAlarmHandlePlanStepAction(String id) {
		return dao.findAlarmHandlePlanStepAction(id);
	}

	@Transactional
	public boolean sendAlarmInfo(BphAlarmHandleReceive bphAlarmHandleReceive) {
		BphTaskContent bphTaskContent = new BphTaskContent();
		BphTaskData bphTaskData = new BphTaskData();
		BphAlarmInfo bphAlarmInfo = bphAlarmInfoService.get(bphAlarmHandleReceive.getAlarmId());
		if ("0".equals(bphAlarmInfo.getState()) || null == bphAlarmInfo.getState()) {
			bphAlarmInfo.setState("0");
			bphAlarmInfo.setOptionDesc("发送出警信息");
			bphAlarmInfoService.updateAlarmInfo(bphAlarmInfo);
		}
		List<String> lists = Arrays.asList(bphAlarmHandleReceive.getUserId().split(","));
//		List<User> userList = new ArrayList<User>();
//		for (String userId : lists) {
//			User user = UserUtils.get(userId);
//			if (user!=null) {
//				userList.add(user);
//			}
//		}
//		if (userList.size() > 0 ) {
//			bphTaskData.setUserList(userList);
//		}
		User user = new User();
		bphTaskContent.setRetCode("0");
		bphTaskContent.setRetMessage("SUCCESS");
		List<BphAlarmHandleInfo> list = Lists.newArrayList();
		BphAlarmHandleInfo bphAlarmHandleInfo = new BphAlarmHandleInfo();
		list.add(bphAlarmHandleInfo);
		bphTaskContent.setResultList(list);
		List<String> reslist = Lists.newArrayList();
		bphTaskData.setType("2");
		bphTaskData.setContent(bphTaskContent);
		boolean flag = false;
		for (String s : lists) {
			log.debug("当前警情接收人员为s -----"+ s);
			BphAlarmHandle bphalarmHandle = new BphAlarmHandle();
			user = userDao.get(s);
			if (user != null) {
				bphalarmHandle.setHandleCode(CretaCode.getCreateCode(user.getLoginName()));
			}
			bphalarmHandle.setHandlePoliceId(s);
			bphalarmHandle.setAlarmId(bphAlarmHandleReceive.getAlarmId());
			bphalarmHandle.setTask(bphAlarmHandleReceive.getTask());
			bphalarmHandle.setDestinyX(Double.valueOf(bphAlarmHandleReceive.getDestinyX()));
			bphalarmHandle.setDestinyY(Double.valueOf(bphAlarmHandleReceive.getDestinyY()));
			bphalarmHandle.setPlanId(bphAlarmHandleReceive.getPlanId());
			bphalarmHandle.setStepId(bphAlarmHandleReceive.getStepId());
			bphalarmHandle.setActionId(bphAlarmHandleReceive.getActionId());
			bphalarmHandle.setStatus("0");
			bphalarmHandle.setDispatchTime(new Date());
			bphalarmHandle.setCreateBy(new User("1"));
			bphalarmHandle.setCreateDate(new Date());
			bphalarmHandle.setUpdateBy(new User("1"));
			bphalarmHandle.setUpdateDate(new Date());
			bphalarmHandle.setDelFlag("0");
            reslist.add(s);
            bphTaskData.setUserId(lists);
            save(bphalarmHandle);
		}
		bphAlarmHandleInfo.setHandleTask(bphAlarmHandleReceive.getTask());
		bphAlarmHandleInfo.setHandleDestinyX(bphAlarmHandleReceive.getDestinyX() + "");
		bphAlarmHandleInfo.setHandleDestinyY(bphAlarmHandleReceive.getDestinyY() + "");
		bphAlarmHandleInfo.setAlarmContent(bphAlarmHandleReceive.getContent());
		bphAlarmHandleInfo.setAlarmId(bphAlarmHandleReceive.getAlarmId());
		bphAlarmHandleInfo.setAlarmOrderNum(bphAlarmInfo.getOrderNum());
		bphAlarmHandleInfo.setAlarmPoliceNum(bphAlarmInfo.getPoliceNum());
		bphAlarmHandleInfo.setAlarmPoliceName(bphAlarmInfo.getPoliceName());
		bphAlarmHandleInfo.setAlarmManName(bphAlarmInfo.getManName());
		bphAlarmHandleInfo.setAlarmManSex(bphAlarmInfo.getManSex());
		bphAlarmHandleInfo.setAlarmManTel(bphAlarmInfo.getManTel());
		bphAlarmHandleInfo.setAlarmPlace(bphAlarmInfo.getPlace());
		bphAlarmHandleInfo.setAlarmX(bphAlarmInfo.getX() + "");
		bphAlarmHandleInfo.setAlarmY(bphAlarmInfo.getY() + "");
		bphAlarmHandleInfo.setAlarmZ(bphAlarmInfo.getZ() + "");
		bphAlarmHandleInfo.setAlarmTypeCode(bphAlarmInfo.getTypeCode());
		bphAlarmHandleInfo.setAlarmGenreCode(bphAlarmInfo.getGenreCode());
		bphAlarmHandleInfo.setAlarmClassCode(bphAlarmInfo.getClassCode());
		bphAlarmHandleInfo.setAlarmOfficeId(bphAlarmInfo.getOfficeId());
		bphAlarmHandleInfo.setAlarmFrom(bphAlarmInfo.getAlarmFrom());
		bphAlarmHandleInfo.setAlarmTime(bphAlarmInfo.getAlarmTime().getTime() + "");
		bphAlarmHandleInfo.setAlarmRecord(bphAlarmInfo.getAlarmRecord());
		bphAlarmHandleInfo.setAlarmIsImportant(bphAlarmInfo.getIsImportant());
		bphAlarmHandleInfo.setAlarmState(bphAlarmInfo.getState());
		if(bphAlarmInfo.getOffice() != null) {
			String officeName = bphAlarmInfo.getOffice().getName();
			if (StringUtils.isNotBlank(officeName)) {
				bphAlarmHandleInfo.setOfficeName(bphAlarmInfo.getOffice().getName());
			}
		}
		bphAlarmHandleInfo.setTypeName(DictUtils.getDictLabel(bphAlarmInfo.getTypeCode(), "bph_alarm_info_typecode", ""));
		JSONObject paramData = JSONObject.fromObject(bphTaskData);
		String retCode = bphAlarmHandleLogService.sendMessageApp(paramData);
		if ("0".equals(retCode)) {
			return true;
		}else {
			return false;
		}
	}

	public List<BphAlarmHandleUserState> findUserState(BphAlarmHandleUserState bphAlarmHandleUserState) {
		return dao.findUserState(bphAlarmHandleUserState);
	}

	/**
	 * 查询 警员处警状态
	 * @return
	 */
	public List<CountOfficeEventEntity> queryPoliceAlarm(){
		return dao.queryPoliceAlarm();
	}
	/**
	 * 查询部门名称
	 * @return
	 */
	public List<String> queryOfficeInfo(){
		return dao.queryOfficeInfo();
	}


	@Transactional
	public String sendAlarmInfo1(BphAlarmHandleReceive bphAlarmHandleReceive) {
		BphTaskContent bphTaskContent = new BphTaskContent();
		BphTaskData bphTaskData = new BphTaskData();
		BphAlarmInfo bphAlarmInfo = bphAlarmInfoService.get(bphAlarmHandleReceive.getAlarmId());
		if ("0".equals(bphAlarmInfo.getState()) || null == bphAlarmInfo.getState()) {
			bphAlarmInfo.setState("0");
			bphAlarmInfo.setOptionDesc("发送出警信息");
			bphAlarmInfoService.updateAlarmInfo(bphAlarmInfo);
		}
		List<String> lists = Arrays.asList(bphAlarmHandleReceive.getUserId().split(","));
//		List<User> userList = new ArrayList<User>();
//		for (String userId : lists) {
//			User user = UserUtils.get(userId);
//			if (user!=null) {
//				userList.add(user);
//			}
//		}
//		if (userList.size() > 0 ) {
//			bphTaskData.setUserList(userList);
//		}
		User user = new User();
		bphTaskContent.setRetCode("0");
		bphTaskContent.setRetMessage("SUCCESS");
		List<BphAlarmHandleInfo> list = Lists.newArrayList();
		BphAlarmHandleInfo bphAlarmHandleInfo = new BphAlarmHandleInfo();
		list.add(bphAlarmHandleInfo);
		bphTaskContent.setResultList(list);
		List<String> reslist = Lists.newArrayList();
		bphTaskData.setType("2");
		bphTaskData.setContent(bphTaskContent);
		boolean flag = false;
		for (String s : lists) {
			log.debug("当前警情接收人员为s -----"+ s);
			BphAlarmHandle bphalarmHandle = new BphAlarmHandle();
			user = userDao.get(s);
			if (user != null) {
				bphalarmHandle.setHandleCode(CretaCode.getCreateCode(user.getLoginName()));
			}
			bphalarmHandle.setHandlePoliceId(s);
			bphalarmHandle.setAlarmId(bphAlarmHandleReceive.getAlarmId());
			bphalarmHandle.setTask(bphAlarmHandleReceive.getTask());
			bphalarmHandle.setDestinyX(Double.valueOf(bphAlarmHandleReceive.getDestinyX()));
			bphalarmHandle.setDestinyY(Double.valueOf(bphAlarmHandleReceive.getDestinyY()));
			bphalarmHandle.setPlanId(bphAlarmHandleReceive.getPlanId());
			bphalarmHandle.setStepId(bphAlarmHandleReceive.getStepId());
			bphalarmHandle.setActionId(bphAlarmHandleReceive.getActionId());
			bphalarmHandle.setStatus("0");
			bphalarmHandle.setDispatchTime(new Date());
			bphalarmHandle.setCreateBy(new User("1"));
			bphalarmHandle.setCreateDate(new Date());
			bphalarmHandle.setUpdateBy(new User("1"));
			bphalarmHandle.setUpdateDate(new Date());
			bphalarmHandle.setDelFlag("0");
			reslist.add(s);
			bphTaskData.setUserId(lists);
			save(bphalarmHandle);
		}
		bphAlarmHandleInfo.setHandleTask(bphAlarmHandleReceive.getTask());
		bphAlarmHandleInfo.setHandleDestinyX(bphAlarmHandleReceive.getDestinyX() + "");
		bphAlarmHandleInfo.setHandleDestinyY(bphAlarmHandleReceive.getDestinyY() + "");
		bphAlarmHandleInfo.setAlarmContent(bphAlarmHandleReceive.getContent());
		bphAlarmHandleInfo.setAlarmId(bphAlarmHandleReceive.getAlarmId());
		bphAlarmHandleInfo.setAlarmOrderNum(bphAlarmInfo.getOrderNum());
		bphAlarmHandleInfo.setAlarmPoliceNum(bphAlarmInfo.getPoliceNum());
		bphAlarmHandleInfo.setAlarmPoliceName(bphAlarmInfo.getPoliceName());
		bphAlarmHandleInfo.setAlarmManName(bphAlarmInfo.getManName());
		bphAlarmHandleInfo.setAlarmManSex(bphAlarmInfo.getManSex());
		bphAlarmHandleInfo.setAlarmManTel(bphAlarmInfo.getManTel());
		bphAlarmHandleInfo.setAlarmPlace(bphAlarmInfo.getPlace());
		bphAlarmHandleInfo.setAlarmX(bphAlarmInfo.getX() + "");
		bphAlarmHandleInfo.setAlarmY(bphAlarmInfo.getY() + "");
		bphAlarmHandleInfo.setAlarmZ(bphAlarmInfo.getZ() + "");
		bphAlarmHandleInfo.setAlarmTypeCode(bphAlarmInfo.getTypeCode());
		bphAlarmHandleInfo.setAlarmGenreCode(bphAlarmInfo.getGenreCode());
		bphAlarmHandleInfo.setAlarmClassCode(bphAlarmInfo.getClassCode());
		bphAlarmHandleInfo.setAlarmOfficeId(bphAlarmInfo.getOfficeId());
		bphAlarmHandleInfo.setAlarmFrom(bphAlarmInfo.getAlarmFrom());
		bphAlarmHandleInfo.setAlarmTime(bphAlarmInfo.getAlarmTime().getTime() + "");
		bphAlarmHandleInfo.setAlarmRecord(bphAlarmInfo.getAlarmRecord());
		bphAlarmHandleInfo.setAlarmIsImportant(bphAlarmInfo.getIsImportant());
		bphAlarmHandleInfo.setAlarmState(bphAlarmInfo.getState());
		if(bphAlarmInfo.getOffice() != null) {
			String officeName = bphAlarmInfo.getOffice().getName();
			if (StringUtils.isNotBlank(officeName)) {
				bphAlarmHandleInfo.setOfficeName(bphAlarmInfo.getOffice().getName());
			}
		}
		bphAlarmHandleInfo.setTypeName(DictUtils.getDictLabel(bphAlarmInfo.getTypeCode(), "bph_alarm_info_typecode", ""));
		JSONObject paramData = JSONObject.fromObject(bphTaskData);
		String retCode = bphAlarmHandleLogService.sendMessageApp(paramData);
		if ("0".equals(retCode)) {
			return "0";
		}else {
			return "1";
		}
	}

	/**
	 *  接处警（查询用户当天的警情列表）
	 * @param receiveAlarmVo
	 * @return
	 */
	public List<AlarmHandleDayInfo> queryDayAlarmList(CcmBphReceiveAlarmVo receiveAlarmVo) {
		return dao.queryDayAlarmList(receiveAlarmVo);
	}

	/**
	 * 接处警 更新处警记录
	 * @param bphAlarmHandle
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateAlarmHandleById(BphAlarmHandle bphAlarmHandle) {
		return dao.updateAlarmHandleById(bphAlarmHandle);
	}
	/**
	 * 接处警 查询当前部门所有人处警记录
	 * @param alarmId
	 * @param userId
	 * @param offId
	 * @return
	 */
	public List<CurrentOffAlarmHandleInfo> queryCurrentOffHandleByAlarmIdAndOffId(String alarmId, String userId, String offId) {
		return dao.queryCurrentOffHandleByAlarmIdAndOffId(alarmId,userId,offId);
	}
	/**
	 * 接处警 查询处警详情
	 * @param alarmId
	 * @param handlePoliceId
	 * @return
	 */
	public AlarmHandleInfo queryAlarmHandleInfo(String alarmId,String handleId, String handlePoliceId) {
		// 查询处警记录的详情，详情只能有一个
		AlarmHandleInfo alarmHandleInfo = dao.queryAlarmHandleInfo(alarmId,handleId,handlePoliceId);
		Map<String,List<AlarmHandleUserStatus>> alarmHandleUserStatusMap = new HashMap<String,List<AlarmHandleUserStatus>>();
		List<AlarmHandleUserStatus>  qStatus = new ArrayList<AlarmHandleUserStatus>();
		List<AlarmHandleUserStatus>  dStatus = new ArrayList<AlarmHandleUserStatus>();
		List<AlarmHandleUserStatus>  fStatus = new ArrayList<AlarmHandleUserStatus>();
		if (alarmHandleInfo != null ){
			List<AlarmHandleUserStatus> alarmHandleUserStatuses = bphAlarmHandleLogService.queryAlarmHandleStatusUsers(alarmId);
			for (AlarmHandleUserStatus handleUserStatus : alarmHandleUserStatuses ) {
				handleUserStatus.setCreateDate(handleUserStatus.getCreateDate().substring(11, 16));
				if("1".equals(handleUserStatus.getStatus())){
					qStatus.add(handleUserStatus);
				}
				if("2".equals(handleUserStatus.getStatus())){
					dStatus.add(handleUserStatus);
				}
				if("3".equals(handleUserStatus.getStatus())){
					fStatus.add(handleUserStatus);
				}
			}
			Collections.sort(qStatus, new Comparator<AlarmHandleUserStatus>() {
				@Override
				public int compare(AlarmHandleUserStatus o1, AlarmHandleUserStatus o2) {
					return o1.getCreateDate().compareTo(o2.getCreateDate());
				}
			});
			Collections.sort(dStatus, new Comparator<AlarmHandleUserStatus>() {
				@Override
				public int compare(AlarmHandleUserStatus o1, AlarmHandleUserStatus o2) {
					return o1.getCreateDate().compareTo(o2.getCreateDate());
				}
			});
			Collections.sort(fStatus, new Comparator<AlarmHandleUserStatus>() {
				@Override
				public int compare(AlarmHandleUserStatus o1, AlarmHandleUserStatus o2) {
					return o1.getCreateDate().compareTo(o2.getCreateDate());
				}
			});
			if (qStatus != null && qStatus.size() > 0)
				alarmHandleUserStatusMap.put("receiveAlarmHandle",qStatus);
			if (dStatus != null && dStatus.size() > 0)
				alarmHandleUserStatusMap.put("arriveAlarmHandle",dStatus);
			if (fStatus != null && fStatus.size() > 0)
				alarmHandleUserStatusMap.put("feedbackAlarmHandle",fStatus);

			alarmHandleInfo.setAlarmHandleUserStatusMap(alarmHandleUserStatusMap);

			double alarmX = alarmHandleInfo.getAlarmX();
			double alarmY = alarmHandleInfo.getAlarmY();
			Gps gpsAlarm1 = PositionUtil.gps84_To_Gcj02(alarmY,alarmX);
			alarmHandleInfo.setAlarmX(gpsAlarm1.getWgLon());
			alarmHandleInfo.setAlarmY(gpsAlarm1.getWgLat());
			double handleDestinyX = alarmHandleInfo.getHandleDestinyX();
			double handleDestinyY = alarmHandleInfo.getHandleDestinyY();
			Gps gpsAlarm2 = PositionUtil.gps84_To_Gcj02( handleDestinyY,handleDestinyX);
			alarmHandleInfo.setHandleDestinyX(gpsAlarm2.getWgLon());
			alarmHandleInfo.setHandleDestinyY(gpsAlarm2.getWgLat());


		}
		return alarmHandleInfo;
	}

	/**
	 *  查询处警人的所有处警警情记录
	 * @param alarmId
	 * @param handlePoliceId
	 * @return
	 */
	public List<AlarmHandle> selectAlarmHandleByAlarmIdAndhandlePoliceId(String alarmId, String handlePoliceId) {
		return dao.selectAlarmHandleByAlarmIdAndhandlePoliceId(alarmId,handlePoliceId);
	}

	/**
	 * 根据处警ID，查询警情对应的反馈详情
	 * @param handleId
	 * @return
	 */

	public AlarmHandleFeedBack alarmHandleFeedback(String handleId) {
		return dao.alarmHandleFeedback(handleId);
	}

	/**
	 *  查询警情列表 (近一周，一个月，三个月 ，某个时间段，)
	 * @param  --crite （日期条件 近一周，一个月，三个月 ）
	 * @param  --alarmStartTime （开始时间）
	 * @param  --alarmEndTime   （结果时间）
	 * @param  handlePoliceId  （处警人）
	 * @return
	 */
	public List<AlarmHandlEntity> queryAlarmList(CcmBphQueryAlarmVo queryAlarmVo) {
		//1:近一周；2：近一月；3：近三月
        if (!StringUtils.isBlank(queryAlarmVo.getCrite())){
			switch (queryAlarmVo.getCrite()){
				case "1":
					queryAlarmVo.setCrite(" INTERVAL 7 DAY ");
					break;
				case "2":
					queryAlarmVo.setCrite(" INTERVAL 1 MONTH");
					break;
				case "3":
					queryAlarmVo.setCrite(" INTERVAL 3 MONTH");
					break;
			}
		}
		if (StringUtils.isBlank(queryAlarmVo.getCriteOrderBy())){
			queryAlarmVo.setCriteOrderBy("DESC");
		}
		return dao.queryAlarmList(queryAlarmVo);

	}

    public int queryAlarmCount(String handlePoliceId,List<String> status) {
        return dao.queryAlarmCount(handlePoliceId,status);
    }
    
    public int selectAlarmHandleByHandleIdAndPoiceId(CcmBphUpdateAlarmVo updateAlarmVo) {
		return dao.selectAlarmHandleByHandleIdAndPoiceId(updateAlarmVo);
	}
    
	//事件未读信息查询
	public int queryEventCount(String userId) {
		return dao.selectEventCount(userId);
	}
	//我的消息未读信息查询
	public int queryNewsCount(String userId) {
		return dao.selectNewsCount(userId);
	}
}