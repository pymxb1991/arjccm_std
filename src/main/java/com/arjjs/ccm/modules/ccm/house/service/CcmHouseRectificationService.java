/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmUploadLog;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmUploadLogService;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventIncidentService;
import com.arjjs.ccm.modules.ccm.house.dao.CcmHouseRectificationDao;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseRectification;
import com.arjjs.ccm.modules.ccm.house.web.CcmHouseRmqController;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.SysConfigInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 社区矫正人员Service
 * 
 * @author arj
 * @version 2018-01-04
 */
@Service
@Transactional(readOnly = true)
public class CcmHouseRectificationService extends CrudService<CcmHouseRectificationDao, CcmHouseRectification> {
	
	@Autowired
	private CcmHouseRectificationDao CcmHouseRectificationDao;
	//上传上级平台记录
	@Autowired
	private CcmUploadLogService ccmUploadLogService;
	@Autowired
	private CcmEventIncidentService ccmEventIncidentService;
		
		

	public CcmHouseRectification get(String id) {
		return super.get(id);
	}

	public List<CcmHouseRectification> findList(CcmHouseRectification ccmHouseRectification) {
		return super.findList(ccmHouseRectification);
	}

	public Page<CcmHouseRectification> findPage(Page<CcmHouseRectification> page,
			CcmHouseRectification ccmHouseRectification) {
		return super.findPage(page, ccmHouseRectification);
	}

	@Transactional(readOnly = false)
	public void save(CcmHouseRectification ccmHouseRectification, CcmPeople ccmPeople) {
		boolean isNew = false;
		if (ccmHouseRectification.getId() == null || "".equals(ccmHouseRectification.getId())) {//无主键ID，则是新记录
			isNew = true;
		}
		if (ccmHouseRectification.getIsNewRecord()) {//指定了是新增记录，则算新记录
			isNew = true;
		}
		super.save(ccmHouseRectification);

		//标记特殊人员，负责该人所在网格的网格员弹出提示
		CcmHouseRmqController.commonsendMessage(ccmPeople);
		//标记特殊人员与事件进行联动
		ccmEventIncidentService.specialPersonEvent(ccmPeople);

		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			if (isNew) {//新增数据
				uploadLog.setOperateType("1");
				uploadLog.setRemarks("新增社区矫正人员数据：" + ccmHouseRectification.getPeopleId());
			} else {//编辑数据
				uploadLog.setOperateType("2");
				uploadLog.setRemarks("编辑社区矫正人员数据：" + ccmHouseRectification.getPeopleId());
			}
			uploadLog.setTableName("ccm_house_rectification");
			uploadLog.setDataId(ccmHouseRectification.getId());
			uploadLog.setUploadStatus("1");
			User user = UserUtils.getUser();
			if (user == null || StringUtils.isBlank(user.getId())){
				uploadLog.setCreateBy(new User("1"));
				uploadLog.setUpdateBy(new User("1"));
			}
			ccmUploadLogService.save(uploadLog);
		}
	}

	@Transactional(readOnly = false)
	public void delete(CcmHouseRectification ccmHouseRectification) {
		super.delete(ccmHouseRectification);

		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			uploadLog.setOperateType("3");
			uploadLog.setRemarks("删除社区矫正人员数据：" + ccmHouseRectification.getPeopleId());
			uploadLog.setTableName("ccm_house_rectification");
			uploadLog.setDataId(ccmHouseRectification.getId());
			uploadLog.setUploadStatus("1");
			User user = UserUtils.getUser();
			if (user == null || StringUtils.isBlank(user.getId())){
				uploadLog.setCreateBy(new User("1"));
				uploadLog.setUpdateBy(new User("1"));
			}
			ccmUploadLogService.save(uploadLog);
		}
	}

	// 获取 所有信息根据 id
	public CcmHouseRectification getPeopleALL(String id) {
		return CcmHouseRectificationDao.getItemByPeopleId(id);
	}
}