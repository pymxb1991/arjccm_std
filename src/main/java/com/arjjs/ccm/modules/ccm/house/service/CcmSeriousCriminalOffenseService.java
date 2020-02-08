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
import com.arjjs.ccm.modules.ccm.house.dao.CcmSeriousCriminalOffenseDao;
import com.arjjs.ccm.modules.ccm.house.entity.CcmSeriousCriminalOffense;
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
 * 严重刑事犯罪Service
 * @author liuxue
 * @version 2018-09-26
 */
@Service
@Transactional(readOnly = true)
public class CcmSeriousCriminalOffenseService extends CrudService<CcmSeriousCriminalOffenseDao, CcmSeriousCriminalOffense> {

	//上传上级平台记录
	@Autowired
	private CcmUploadLogService ccmUploadLogService;
	@Autowired
	private CcmEventIncidentService ccmEventIncidentService;

	public CcmSeriousCriminalOffense get(String id) {
		return super.get(id);
	}
	// 获取 所有信息根据 id
	public CcmSeriousCriminalOffense getPeopleALL(String id) {
		return dao.getItemByPeopleId(id);
	}
	public List<CcmSeriousCriminalOffense> findList(CcmSeriousCriminalOffense ccmSeriousCriminalOffense) {
		return super.findList(ccmSeriousCriminalOffense);
	}
	
	public Page<CcmSeriousCriminalOffense> findPage(Page<CcmSeriousCriminalOffense> page, CcmSeriousCriminalOffense ccmSeriousCriminalOffense) {
		return super.findPage(page, ccmSeriousCriminalOffense);
	}
	
	
	@Transactional(readOnly = false)
	public void save(CcmSeriousCriminalOffense ccmSeriousCriminalOffense, CcmPeople ccmPeople) {
		boolean isNew = false;
		if (ccmSeriousCriminalOffense.getId() == null || "".equals(ccmSeriousCriminalOffense.getId())) {//无主键ID，则是新记录
			isNew = true;
		}
		if (ccmSeriousCriminalOffense.getIsNewRecord()) {//指定了是新增记录，则算新记录
			isNew = true;
		}
		super.save(ccmSeriousCriminalOffense);

		//标记特殊人员，负责该人所在网格的网格员弹出提示
		CcmHouseRmqController.commonsendMessage(ccmPeople);
		//标记特殊人员与事件进行联动
		ccmEventIncidentService.specialPersonEvent(ccmPeople);

		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			if (isNew) {//新增数据
				uploadLog.setOperateType("1");
				uploadLog.setRemarks("新增严重刑事犯罪嫌疑人员数据：" + ccmSeriousCriminalOffense.getPeopleId());
			} else {//编辑数据
				uploadLog.setOperateType("2");
				uploadLog.setRemarks("编辑严重刑事犯罪嫌疑人员数据：" + ccmSeriousCriminalOffense.getPeopleId());
			}
			uploadLog.setTableName("ccm_serious_criminal_offense");
			uploadLog.setDataId(ccmSeriousCriminalOffense.getId());
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
	public void delete(CcmSeriousCriminalOffense ccmSeriousCriminalOffense) {
		super.delete(ccmSeriousCriminalOffense);

		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			uploadLog.setOperateType("3");
			uploadLog.setRemarks("删除严重刑事犯罪嫌疑人员数据：" + ccmSeriousCriminalOffense.getPeopleId());
			uploadLog.setTableName("ccm_serious_criminal_offense");
			uploadLog.setDataId(ccmSeriousCriminalOffense.getId());
			uploadLog.setUploadStatus("1");
			User user = UserUtils.getUser();
			if (user == null || StringUtils.isBlank(user.getId())){
				uploadLog.setCreateBy(new User("1"));
				uploadLog.setUpdateBy(new User("1"));
			}
			ccmUploadLogService.save(uploadLog);
		}
	}
	
}