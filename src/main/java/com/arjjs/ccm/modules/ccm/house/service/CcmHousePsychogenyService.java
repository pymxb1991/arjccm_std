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
import com.arjjs.ccm.modules.ccm.house.dao.CcmHousePsychogenyDao;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHousePsychogeny;
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
 * 肇事肇祸等严重精神障碍患者Service
 * 
 * @author arj
 * @version 2018-01-05
 */
@Service
@Transactional(readOnly = true)
public class CcmHousePsychogenyService extends CrudService<CcmHousePsychogenyDao, CcmHousePsychogeny> {

	@Autowired
	private CcmHousePsychogenyDao ccmHousePsychogenyDao;
	//上传上级平台记录
	@Autowired
	private CcmUploadLogService ccmUploadLogService;
	@Autowired
	private CcmEventIncidentService ccmEventIncidentService;
	

	public CcmHousePsychogeny get(String id) {
		return super.get(id);
	}

	public List<CcmHousePsychogeny> findList(CcmHousePsychogeny ccmHousePsychogeny) {
		return super.findList(ccmHousePsychogeny);
	}

	public Page<CcmHousePsychogeny> findPage(Page<CcmHousePsychogeny> page, CcmHousePsychogeny ccmHousePsychogeny) {
		return super.findPage(page, ccmHousePsychogeny);
	}

	@Transactional(readOnly = false)
	public void save(CcmHousePsychogeny ccmHousePsychogeny, CcmPeople ccmPeople) {
		boolean isNew = false;
		if (ccmHousePsychogeny.getId() == null || "".equals(ccmHousePsychogeny.getId())) {//无主键ID，则是新记录
			isNew = true;
		}
		if (ccmHousePsychogeny.getIsNewRecord()) {//指定了是新增记录，则算新记录
			isNew = true;
		}
		super.save(ccmHousePsychogeny);

		//标记特殊人员，负责该人所在网格的网格员弹出提示
		CcmHouseRmqController.commonsendMessage(ccmPeople);
		//标记特殊人员与事件进行联动
		ccmEventIncidentService.specialPersonEvent(ccmPeople);

		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			if (isNew) {//新增数据
				uploadLog.setOperateType("1");
				uploadLog.setRemarks("新增肇事肇祸等严重精神障碍患者人口数据：" + ccmHousePsychogeny.getPeopleId());
			} else {//编辑数据
				uploadLog.setOperateType("2");
				uploadLog.setRemarks("编辑肇事肇祸等严重精神障碍患者人口数据：" + ccmHousePsychogeny.getPeopleId());
			}
			uploadLog.setTableName("ccm_house_psychogeny");
			uploadLog.setDataId(ccmHousePsychogeny.getId());
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
	public void delete(CcmHousePsychogeny ccmHousePsychogeny) {
		super.delete(ccmHousePsychogeny);

		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			uploadLog.setOperateType("3");
			uploadLog.setRemarks("删除肇事肇祸等严重精神障碍患者人口数据：" + ccmHousePsychogeny.getPeopleId());
			uploadLog.setTableName("ccm_house_psychogeny");
			uploadLog.setDataId(ccmHousePsychogeny.getId());
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
	public CcmHousePsychogeny getPeopleALL(String id) {
		return ccmHousePsychogenyDao.getItemByPeopleId(id);
	}

}