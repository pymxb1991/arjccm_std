/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventKacc;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.SysConfigInit;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmUploadLog;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmUploadLogService;
import com.arjjs.ccm.modules.ccm.event.dao.CcmEventKaccDao;

/**
 * 重点地区排查整治Service
 * @author arj
 * @version 2018-01-04
 */
@Service
@Transactional(readOnly = true)
public class CcmEventKaccService extends CrudService<CcmEventKaccDao, CcmEventKacc> {

	@Autowired
	private CcmEventKaccDao ccmEventKaccDao;
	//上传上级平台记录
	@Autowired
	private CcmUploadLogService ccmUploadLogService;
	
	
	public CcmEventKacc get(String id) {
		return super.get(id);
	}
	
	public List<CcmEventKacc> findList(CcmEventKacc ccmEventKacc) {
		return super.findList(ccmEventKacc);
	}
	
	public Page<CcmEventKacc> findPage(Page<CcmEventKacc> page, CcmEventKacc ccmEventKacc) {
		return super.findPage(page, ccmEventKacc);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmEventKacc ccmEventKacc) {
		boolean isNew = false;
		if (ccmEventKacc.getId() == null || "".equals(ccmEventKacc.getId())) {//无主键ID，则是新记录
			isNew = true;
		}
		if (ccmEventKacc.getIsNewRecord()) {//指定了是新增记录，则算新记录
			isNew = true;
		}
		super.save(ccmEventKacc);

		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			if (isNew) {//新增数据
				uploadLog.setOperateType("1");
				uploadLog.setRemarks("新增重点地区排查整治数据：" + ccmEventKacc.getSecuPlace());
			} else {//编辑数据
				uploadLog.setOperateType("2");
				uploadLog.setRemarks("编辑重点地区排查整治数据：" + ccmEventKacc.getSecuPlace());
			}
			uploadLog.setTableName("ccm_event_kacc");
			uploadLog.setDataId(ccmEventKacc.getId());
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
	public void delete(CcmEventKacc ccmEventKacc) {
		super.delete(ccmEventKacc);

		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			uploadLog.setOperateType("3");
			uploadLog.setRemarks("删除重点地区排查整治数据：" + ccmEventKacc.getSecuPlace());
			uploadLog.setTableName("ccm_event_kacc");
			uploadLog.setDataId(ccmEventKacc.getId());
			uploadLog.setUploadStatus("1");
			User user = UserUtils.getUser();
			if (user == null || StringUtils.isBlank(user.getId())){
				uploadLog.setCreateBy(new User("1"));
				uploadLog.setUpdateBy(new User("1"));
			}
			ccmUploadLogService.save(uploadLog);
		}
	}

	//治安突出问题统计
	public List<EchartType> findSafePage(CcmEventKacc ccmEventKacc) {
		return ccmEventKaccDao.findSafePage(ccmEventKacc);
	}

	//涉及区域类型统计
	public List<EchartType> findAreaPage(CcmEventKacc ccmEventKacc) {
		return ccmEventKaccDao.findAreaPage(ccmEventKacc);
	}

	//效果评估统计
	public List<EchartType> findAssessPage(CcmEventKacc ccmEventKacc) {
		return ccmEventKaccDao.findAssessPage(ccmEventKacc);
	}

	//总数统计
	public List<EchartType> findLinePage(CcmEventKacc ccmEventKacc) {
		return ccmEventKaccDao.findLinePage(ccmEventKacc);
	}
	
}