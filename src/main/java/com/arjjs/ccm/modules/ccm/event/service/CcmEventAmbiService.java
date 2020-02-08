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
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventAmbi;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventCasedeal;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.SysConfigInit;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmUploadLog;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmUploadLogService;
import com.arjjs.ccm.modules.ccm.event.dao.CcmEventAmbiDao;
import com.arjjs.ccm.modules.ccm.event.dao.CcmEventCasedealDao;

/**
 * 矛盾纠纷排查化解Service
 * @author wwh
 * @version 2018-01-30
 */
@Service
@Transactional(readOnly = true)
public class CcmEventAmbiService extends CrudService<CcmEventAmbiDao, CcmEventAmbi> {

	@Autowired
	private CcmEventAmbiDao ccmEventAmbiDao;
	@Autowired
	private CcmEventCasedealDao casedealDao;
	//上传上级平台记录
	@Autowired
	private CcmUploadLogService ccmUploadLogService;
		
	
	public CcmEventAmbi get(String id) {
		return super.get(id);
	}
	
	public List<CcmEventAmbi> findList(CcmEventAmbi ccmEventAmbi) {
		return super.findList(ccmEventAmbi);
	}
	
	public Page<CcmEventAmbi> findPage(Page<CcmEventAmbi> page, CcmEventAmbi ccmEventAmbi) {
		return super.findPage(page, ccmEventAmbi);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmEventAmbi ccmEventAmbi) {
		boolean isNew = false;
		if (ccmEventAmbi.getId() == null || "".equals(ccmEventAmbi.getId())) {//无主键ID，则是新记录
			isNew = true;
		}
		if (ccmEventAmbi.getIsNewRecord()) {//指定了是新增记录，则算新记录
			isNew = true;
		}
		super.save(ccmEventAmbi);

		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			if (isNew) {//新增数据
				uploadLog.setOperateType("1");
				uploadLog.setRemarks("新增矛盾纠纷数据：" + ccmEventAmbi.getName());
			} else {//编辑数据
				uploadLog.setOperateType("2");
				uploadLog.setRemarks("编辑矛盾纠纷数据：" + ccmEventAmbi.getName());
			}
			uploadLog.setTableName("ccm_event_ambi");
			uploadLog.setDataId(ccmEventAmbi.getId());
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
	public void delete(CcmEventAmbi ccmEventAmbi) {
		super.delete(ccmEventAmbi);

		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			uploadLog.setOperateType("3");
			uploadLog.setRemarks("删除矛盾纠纷数据：" + ccmEventAmbi.getName());
			uploadLog.setTableName("ccm_event_ambi");
			uploadLog.setDataId(ccmEventAmbi.getId());
			uploadLog.setUploadStatus("1");
			User user = UserUtils.getUser();
			if (user == null || StringUtils.isBlank(user.getId())){
				uploadLog.setCreateBy(new User("1"));
				uploadLog.setUpdateBy(new User("1"));
			}
			ccmUploadLogService.save(uploadLog);
		}
	}

	//化解是否成功统计
	public List<EchartType> findSuccessMap(CcmEventAmbi ccmEventAmbi) {
		return ccmEventAmbiDao.findSuccessMap(ccmEventAmbi);
	}

	//矛盾纠纷规模统计
	public List<EchartType> findScaleMap(CcmEventAmbi ccmEventAmbi) {
		return ccmEventAmbiDao.findScaleMap(ccmEventAmbi);
	}

	//处理状态统计
	public List<EchartType> findStatusMap(CcmEventAmbi ccmEventAmbi) {
		return ccmEventAmbiDao.findStatusMap(ccmEventAmbi);
	}

	//总数统计
	public List<EchartType> findLineMap(CcmEventAmbi ccmEventAmbi) {
		return ccmEventAmbiDao.findLineMap(ccmEventAmbi);
	}

	public List<EchartType> findAreaMap(CcmEventAmbi ccmEventAmbi) {
		return ccmEventAmbiDao.findAreaMap(ccmEventAmbi);
	}
	
	//查询事件处理
	public List<CcmEventCasedeal> findCasedealList(String id) {
		// TODO Auto-generated method stub
		return casedealDao.listWithPID(id);
	}

	//累计受理纠纷总数
	public int findListNum() {
		return ccmEventAmbiDao.findListNum();
	}
	
	
	
	
	
	
	
	
}