/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.pop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPopBehind;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.SysConfigInit;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmUploadLog;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmUploadLogService;
import com.arjjs.ccm.modules.ccm.pop.dao.CcmPopBehindDao;

/**
 * 留守人员Service
 * 
 * @author arj
 * @version 2018-01-06
 */
@Service
@Transactional(readOnly = true)
public class CcmPopBehindService extends CrudService<CcmPopBehindDao, CcmPopBehind> {

	@Autowired
	private CcmPopBehindDao ccmPopBehindDao;
	//上传上级平台记录
	@Autowired
	private CcmUploadLogService ccmUploadLogService;
		
		
		
	public CcmPopBehind get(String id) {
		return super.get(id);
	}

	public List<CcmPopBehind> findList(CcmPopBehind ccmPopBehind) {
		return super.findList(ccmPopBehind);
	}

	public Page<CcmPopBehind> findPage(Page<CcmPopBehind> page, CcmPopBehind ccmPopBehind) {
		return super.findPage(page, ccmPopBehind);
	}

	@Transactional(readOnly = false)
	public void save(CcmPopBehind ccmPopBehind) {
		boolean isNew = false;
		if (ccmPopBehind.getId() == null || "".equals(ccmPopBehind.getId())) {//无主键ID，则是新记录
			isNew = true;
		}
		if (ccmPopBehind.getIsNewRecord()) {//指定了是新增记录，则算新记录
			isNew = true;
		}
		super.save(ccmPopBehind);

		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			if (isNew) {//新增数据
				uploadLog.setOperateType("1");
				uploadLog.setRemarks("新增留守人员数据：" + ccmPopBehind.getPeopleId());
			} else {//编辑数据
				uploadLog.setOperateType("2");
				uploadLog.setRemarks("编辑留守人员数据：" + ccmPopBehind.getPeopleId());
			}
			uploadLog.setTableName("ccm_pop_behind");
			uploadLog.setDataId(ccmPopBehind.getId());
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
	public void delete(CcmPopBehind ccmPopBehind) {
		super.delete(ccmPopBehind);

		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			uploadLog.setOperateType("3");
			uploadLog.setRemarks("删除留守人员数据：" + ccmPopBehind.getPeopleId());
			uploadLog.setTableName("ccm_pop_behind");
			uploadLog.setDataId(ccmPopBehind.getId());
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
	public CcmPopBehind getPeopleALL(String id) {
		return ccmPopBehindDao.getItemByPeopleId(id);
	}

}