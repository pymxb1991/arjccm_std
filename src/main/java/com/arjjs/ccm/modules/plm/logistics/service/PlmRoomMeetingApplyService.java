/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.logistics.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.act.service.ActTaskService;
import com.arjjs.ccm.modules.act.service.ActUtConfigService;
import com.arjjs.ccm.modules.iot.warning.entity.CcmEarlyWarning;
import com.arjjs.ccm.modules.plm.act.service.PlmActService;
import com.arjjs.ccm.modules.plm.calendar.service.PlmCalendarService;
import com.arjjs.ccm.modules.plm.logistics.dao.PlmRoomApplyDao;
import com.arjjs.ccm.modules.plm.logistics.dao.PlmRoomAttendeeDao;
import com.arjjs.ccm.modules.plm.logistics.entity.PlmRoomApply;
import com.arjjs.ccm.modules.plm.logistics.entity.PlmRoomApplyResource;
import com.arjjs.ccm.modules.plm.logistics.entity.PlmRoomAttendee;
import com.arjjs.ccm.modules.sys.service.SysCodesService;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.PlmTypes;
import com.google.common.collect.Maps;

/**
 * 会议申请Service
 * @author fu
 * @version 2018-06-26
 */
@Service
@Transactional(readOnly = true)
public class PlmRoomMeetingApplyService extends CrudService<PlmRoomApplyDao, PlmRoomApply> {
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private PlmRoomAttendeeDao plmRoomAttendeeDao;
	@Autowired	
	private PlmActService plmActService;
	@Autowired
	private ActUtConfigService<PlmRoomApply> actUtConfigService;
	@Autowired
	private SysCodesService sysCodesService;
	@Autowired
	private PlmCalendarService plmCalendarService;
	@Autowired
	private PlmRoomApplyDao plmRoomApplyDao;
	
	public PlmRoomApply getByProcInsId(String procInsId) {
		return dao.getByProcInsId(procInsId);
	}
	
	
	public PlmRoomApply get(String id) {
		return super.get(id);
	}
	
	public List<PlmRoomApply> findList(PlmRoomApply plmRoomApply) {
		return super.findList(plmRoomApply);
	}
	
	public Page<PlmRoomApply> findPage(Page<PlmRoomApply> page, PlmRoomApply plmRoomApply) {
		return super.findPage(page, plmRoomApply);
	}
	@Transactional(readOnly = false)
	public void saveBase(PlmRoomApply plmRoomApply) {
		if (StringUtils.isBlank(plmRoomApply.getCode())) {
			plmRoomApply.setCode(sysCodesService.getCode(PlmRoomApply.class.getName(), 1).get(0));
		}
		//如果已保存业务表，则删除原参会人员记录
		if(StringUtils.isNotBlank(plmRoomApply.getId())){
			plmRoomAttendeeDao.deleteByPlmRoomApplyId(plmRoomApply.getId());	
		}		
		super.save(plmRoomApply);
		
		// 保存参会人员记录
		if (plmRoomApply.getRoomAttendeeList().size() > 0){
			plmRoomAttendeeDao.insertAll(plmRoomApply.getRoomAttendeeList());
		}
	}	
	@Transactional(readOnly = false)
	public void save(PlmRoomApply plmRoomApply) {
		saveBase(plmRoomApply);
		//parameter ： 1、业务流程主表  2、流程配置id 3、业务表
		plmActService.save(plmRoomApply.getPlmAct(),PlmTypes.ROOM_APPLY_MEETING_ID,plmRoomApply.getId());
	}
	
	@Transactional(readOnly = false)
	public void saveMeet(PlmRoomApply plmRoomApply) {
		super.save(plmRoomApply);
	}
	
	@Transactional(readOnly = false)
	public void apply(PlmRoomApply plmRoomApply) {
		saveBase(plmRoomApply);
		if (StringUtils.isBlank(plmRoomApply.getProcInsId())) {
			//2、开启流程，获取流程实例ID和title
			Map<String, String> returnMap = actUtConfigService.getProcInsId(PlmTypes.ROOM_APPLY_MEETING_ID, plmRoomApply, plmRoomApply.getId());
			//3、更新业务表流程实例ID
			plmRoomApply.setProcInsId(returnMap.get("procInsId"));
			dao.updateProcInsId(plmRoomApply);
			//4、保存业务流程主表
			plmRoomApply.getPlmAct().setTitle(returnMap.get("title"));
			plmActService.save(plmRoomApply.getPlmAct(),PlmTypes.ROOM_APPLY_MEETING_ID,plmRoomApply.getId(),plmRoomApply.getProcInsId());
		} else {
			plmRoomApply.getAct().setComment(("yes".equals(plmRoomApply.getAct().getFlag())?"[重申] ":"[撤销] ")
					+ plmRoomApply.getAct().getComment());
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("pass", "yes".equals(plmRoomApply.getAct().getFlag())? "1" : "0");
			actTaskService.complete(plmRoomApply.getAct().getTaskId(), plmRoomApply.getAct().getProcInsId(),
					plmRoomApply.getAct().getComment(), plmRoomApply.getPlmAct().getTitle(), vars);
			//如果销毁，将业务流程主表状态置位“已销毁”
			if(!"yes".equals(plmRoomApply.getAct().getFlag())){
				plmActService.updateStatusToDestory(plmRoomApply.getPlmAct());
			}
		}
	}		
	
	
	@Transactional(readOnly = false)
	public void auditSave(PlmRoomApply plmRoomApply) {
		
		// 设置意见
		plmRoomApply.getAct().setComment(("yes".equals(plmRoomApply.getAct().getFlag())?"[同意] ":"[驳回] ") + plmRoomApply.getAct().getComment());
		
		plmRoomApply.preUpdate();
		
		// 对不同环节的业务逻辑进行操作
		String taskDefKey = plmRoomApply.getAct().getTaskDefKey();

		// 最后一步流程且   需要审核
		if ("auditEnd".equals(taskDefKey)) {							
			// 若为最后一步审核，通过，将业务流程主表状态置位“已结束”
			if ("yes".equals(plmRoomApply.getAct().getFlag())) {
				plmActService.updateStatusToEnd(plmRoomApply.getPlmAct());      
			}
			//3、向流程的参与人添加一条日程
			PlmRoomApply plmRoomApplyDB = get(plmRoomApply.getId());
			List<PlmRoomAttendee> list = plmRoomAttendeeDao.findList(new PlmRoomAttendee(plmRoomApply));
			for (PlmRoomAttendee plmRoomAttendee : list) {
				plmCalendarService.quickAdd(plmRoomApplyDB.getStartTime(), plmRoomApplyDB.getEndTime(), plmRoomApplyDB.getSubject(), plmRoomAttendee.getUser(),"1");
			}
		}
		// 最后一步流程  不需要审核
		else if ("processEnd".equals(taskDefKey)) {				
			// 将业务流程主表状态置位“已结束”			
				plmActService.updateStatusToEnd(plmRoomApply.getPlmAct());      
			
				//3、向流程的参与人添加一条日程
				PlmRoomApply plmRoomApplyDB = get(plmRoomApply.getId());
				List<PlmRoomAttendee> list = plmRoomAttendeeDao.findList(new PlmRoomAttendee(plmRoomApply));
				for (PlmRoomAttendee plmRoomAttendee : list) {
					plmCalendarService.quickAdd(plmRoomApplyDB.getStartTime(), plmRoomApplyDB.getEndTime(), plmRoomApplyDB.getSubject(), plmRoomAttendee.getUser(),"1");
				}
		}
		// 未知环节，直接返回
		else if (StringUtils.isBlank(taskDefKey)) {
			return;
		}
		
		//针对所有具有添加督办的环节，若为isSup字段不为空，添加督办信息
		if(StringUtils.isNotBlank(plmRoomApply.getPlmAct().getIsSup())){
			plmActService.updateSup(plmRoomApply.getPlmAct());
		}
		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(plmRoomApply.getAct().getFlag())? "1" : "0");
		actTaskService.complete(plmRoomApply.getAct().getTaskId(), plmRoomApply.getAct().getProcInsId(), plmRoomApply.getAct().getComment(), vars);

	}
	
	
	@Transactional(readOnly = false)
	public void delete(PlmRoomApply plmRoomApply) {
		super.delete(plmRoomApply);
	}
	//查找参会人员列表
	public PlmRoomApply findAttendeeList(PlmRoomApply plmRoomApply) {
		plmRoomApply.setRoomAttendeeList(plmRoomAttendeeDao.findList(new PlmRoomAttendee(plmRoomApply)));
		return plmRoomApply;
	}

	public	List<Map<String, Object>> findApplyCount(Map<String, Object> map){
		//map : type(01 接待  02会议)  qmonth（前几个月）
		return dao.findApplyCount(map);
	};

	public List<EchartType> roomUseCount (Map<String, Object> map){
		//map : type(01 接待  02会议) 
		return dao.roomUseCount(map);
	};
	
	public Page<PlmRoomApply> getroombyuserId(Page<PlmRoomApply> page,PlmRoomApply plyRoomApply){
		plyRoomApply.setPage(page);
		page.setList(plmRoomApplyDao.getroombyuserId(plyRoomApply));
		return page;
	}
	
	public List<PlmRoomApply> getroombyuserIdlist(PlmRoomApply plyRoomApply){
		return plmRoomApplyDao.getroombyuserId(plyRoomApply);
	}
}