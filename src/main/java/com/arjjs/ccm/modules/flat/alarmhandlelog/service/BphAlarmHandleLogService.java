/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.alarmhandlelog.service;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.rest.entity.AlarmHandleUserStatus;
import com.arjjs.ccm.modules.ccm.rest.web.CcmRestEvent;
import com.arjjs.ccm.modules.flat.alarm.entity.BphAlarmInfo;
import com.arjjs.ccm.modules.flat.alarm.service.BphAlarmInfoService;
import com.arjjs.ccm.modules.flat.alarmhandlelog.dao.BphAlarmHandleLogDao;
import com.arjjs.ccm.modules.flat.alarmhandlelog.entity.BphAlarmHandleLog;
import com.arjjs.ccm.modules.flat.alarmhandlelog.entity.BphNoticeContent;
import com.arjjs.ccm.modules.flat.alarmhandlelog.entity.BphNoticeData;
import com.arjjs.ccm.modules.flat.alarmnotify.entity.BphAlarmNotify;
import com.arjjs.ccm.modules.flat.alarmnotify.service.BphAlarmNotifyService;
import com.arjjs.ccm.modules.oa.dao.OaNotifyRecordDao;
import com.arjjs.ccm.modules.oa.service.OaNotifyService;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.RabbitMQTools;
import com.arjjs.ccm.tool.Tool;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

/**
 * 处警过程记录Service
 * @author wangyikai
 * @version 2018-11-22
 */
@Service
@Transactional(readOnly = true)
public class BphAlarmHandleLogService extends CrudService<BphAlarmHandleLogDao, BphAlarmHandleLog> {

	@Autowired
	private OaNotifyService oaNotifyService;
	@Autowired
	private OaNotifyRecordDao oaNotifyRecordDao;

	@Autowired
	private BphAlarmInfoService bphAlarmInfoService;

	@Autowired
	private BphAlarmNotifyService bphAlarmNotifyService;

	public BphAlarmHandleLog get(String id) {
		return super.get(id);
	}

	public List<BphAlarmHandleLog> findList(BphAlarmHandleLog bphAlarmHandleLog) {
		return super.findList(bphAlarmHandleLog);
	}

	public Page<BphAlarmHandleLog> findPage(Page<BphAlarmHandleLog> page, BphAlarmHandleLog bphAlarmHandleLog) {
		return super.findPage(page, bphAlarmHandleLog);
	}

	@Transactional(readOnly = false)
	public void save(BphAlarmHandleLog bphAlarmHandleLog) {
		super.save(bphAlarmHandleLog);
	}

	@Transactional(readOnly = false)
	public int insert(String alarmId, String operateDesc) {
		BphAlarmHandleLog bphAlarmHandleLog = new BphAlarmHandleLog();
		bphAlarmHandleLog.setAlarmId(alarmId);
		bphAlarmHandleLog.setOperateDesc(operateDesc);
		bphAlarmHandleLog.setUser(UserUtils.getUser());
		bphAlarmHandleLog.setOperateTime(new Date());
		int i = dao.insert(bphAlarmHandleLog);
		return i;
	}

	@Transactional(readOnly = false)
	public void delete(BphAlarmHandleLog bphAlarmHandleLog) {
		super.delete(bphAlarmHandleLog);
	}

	@Transactional(readOnly = false)
	public List<BphAlarmHandleLog> findHandleLog(BphAlarmHandleLog bphAlarmHandleLog) {
		return this.dao.findHandleLog(bphAlarmHandleLog);
	}

	// 将数据发送到App
	public String sendMessageApp(JSONObject paramData) {
		List<String> list = (List<String>) paramData.get("userId");
		paramData.put("type","BphAlarmInfoTag");
		paramData.put("name","您有一条新的警情消息,请注意签收!");
		try {
			for(int i=0; i<list.size(); i++){
				String id = list.get(i);
				paramData.put("id",id);
				RabbitMQTools.sendMessage(id, paramData.toString());
			}
		} catch (Exception e){
			return "0";
		}

		return "0";
	}

	@Transactional(readOnly = false)
	public boolean sendMessage(String contactId, String contactMessageId, String alarmId, String operateDesc) throws IOException {
		BphNoticeData bphNoticeData = new BphNoticeData();
		BphNoticeContent bphNoticeContent = new BphNoticeContent();
		List<String> lists = Arrays.asList(contactId.split(","));
		/*OaNotify oaNotify = new OaNotify();
		OaNotifyRecord oaNotifyRecord = new OaNotifyRecord();
		User user = new User();
		oaNotify.setType("6");
		oaNotify.setTitle("消息推送");
		oaNotify.setContent(contactMessageId);
		oaNotify.setStatus("1");
		oaNotifyService.save(oaNotify);
		oaNotify.getId();
		for (String userId : lists) {
			user.setId(userId);
			UUID uuid=UUID.randomUUID();
			oaNotifyRecord.setId(uuid.toString());
			oaNotifyRecord.setReadFlag("0");
			oaNotifyRecord.setOaNotify(oaNotify);
			oaNotifyRecord.setUser(user);
			oaNotifyRecordDao.insert(oaNotifyRecord);
		}
		BphAlarmHandleLog bphAlarmHandleLog = new BphAlarmHandleLog();
		bphAlarmHandleLog.setUser(UserUtils.getUser());
		// 获取当前的日期
		Date date = new Date();
		bphAlarmHandleLog.setOperateTime(date);
		bphAlarmHandleLog.setAlarmId(alarmId);
		bphAlarmHandleLog.setOperateDesc(operateDesc);
		save(bphAlarmHandleLog);*/

		//添加警情通知内容
		BphAlarmInfo bphAlarmInfo = bphAlarmInfoService.get(alarmId);
		if (bphAlarmInfo != null) {
			BphAlarmNotify bphAlarmNotify = new BphAlarmNotify();
			bphAlarmNotify.setId(UUID.randomUUID().toString());
			bphAlarmNotify.setAlarmId(alarmId);
			bphAlarmNotify.setReceiveUserId(lists.get(0));
			bphAlarmNotify.setType(bphAlarmInfo.getTypeCode());
			bphAlarmNotify.setTitle("警情通知");
			bphAlarmNotify.setContent(contactMessageId);
			bphAlarmNotify.setStatus("0");
			bphAlarmNotify.setDelFlag("0");
			User user2 = UserUtils.getUser();
			bphAlarmNotify.setCreateBy(UserUtils.getUser());
			bphAlarmNotify.setCreateDate(new Date());
			bphAlarmNotify.setUpdateBy(UserUtils.getUser());
			bphAlarmNotify.setUpdateDate(new Date());
			bphAlarmNotify.setRemarks(bphAlarmInfo.getRemarks());
			bphAlarmNotify.setIsNewRecord(true);
			bphAlarmNotifyService.save(bphAlarmNotify);
		}
		List<String> dataList = Lists.newArrayList();
		dataList.add(contactId);
		dataList.add(contactMessageId);
		dataList.add(alarmId);
		bphNoticeData.setUserId(lists);
		bphNoticeData.setType("1");
		bphNoticeContent.setTitle("消息推送");
		bphNoticeContent.setMessage(contactMessageId);
		bphNoticeData.setContent(bphNoticeContent);
		JSONObject jsonObject = JSONObject.fromObject(bphNoticeData);
		String retCode = sendMessageApp(jsonObject);
		if ("0".equals(retCode)) {
			return true;
		}
		return false;
	}

	public List<AlarmHandleUserStatus> queryAlarmHandleStatusUsers(String alarmId) {
		return dao.queryAlarmHandleStatusUsers(alarmId);
	}
}