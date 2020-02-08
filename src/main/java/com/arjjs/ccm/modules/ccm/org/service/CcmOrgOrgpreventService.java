/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgOrgprevent;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgSyncentre;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.SysConfigInit;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmUploadLog;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmUploadLogService;
import com.arjjs.ccm.modules.ccm.org.dao.CcmOrgAreaDao;
import com.arjjs.ccm.modules.ccm.org.dao.CcmOrgOrgpreventDao;

/**
 * 群防群治组织Service
 * @author liang
 * @version 2018-01-13
 */
@Service
@Transactional(readOnly = true)
public class CcmOrgOrgpreventService extends CrudService<CcmOrgOrgpreventDao, CcmOrgOrgprevent> {

	@Autowired
	private CcmOrgOrgpreventDao ccmOrgOrgpreventDao;
	//上传上级平台记录
	@Autowired
	private CcmUploadLogService ccmUploadLogService;
	
	public CcmOrgOrgprevent get(String id) {
		return super.get(id);
	}
	
	public List<CcmOrgOrgprevent> findList(CcmOrgOrgprevent ccmOrgOrgprevent) {
		return super.findList(ccmOrgOrgprevent);
	}
	
	public Page<CcmOrgOrgprevent> findPage(Page<CcmOrgOrgprevent> page, CcmOrgOrgprevent ccmOrgOrgprevent) {
		return super.findPage(page, ccmOrgOrgprevent);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmOrgOrgprevent ccmOrgOrgprevent) {
		boolean isNew = false;
		if (ccmOrgOrgprevent.getId() == null || "".equals(ccmOrgOrgprevent.getId())) {//无主键ID，则是新记录
			isNew = true;
		}
		
		super.save(ccmOrgOrgprevent);
		
		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			if (isNew) {//新增数据
				uploadLog.setOperateType("1");
				uploadLog.setRemarks("新增群防群治组织数据：" + ccmOrgOrgprevent.getName());
			} else {//编辑数据
				uploadLog.setOperateType("2");
				uploadLog.setRemarks("编辑群防群治组织数据：" + ccmOrgOrgprevent.getName());
			}
			uploadLog.setTableName("ccm_org_orgprevent");
			uploadLog.setDataId(ccmOrgOrgprevent.getId());
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
	public void delete(CcmOrgOrgprevent ccmOrgOrgprevent) {
		super.delete(ccmOrgOrgprevent);
		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			uploadLog.setOperateType("3");
			uploadLog.setRemarks("删除群防群治组织数据：" + ccmOrgOrgprevent.getName());
			uploadLog.setTableName("ccm_org_orgprevent");
			uploadLog.setDataId(ccmOrgOrgprevent.getId());
			uploadLog.setUploadStatus("1");
			User user = UserUtils.getUser();
			if (user == null || StringUtils.isBlank(user.getId())){
				uploadLog.setCreateBy(new User("1"));
				uploadLog.setUpdateBy(new User("1"));
			}
			ccmUploadLogService.save(uploadLog);
		}
	}

	//群防群治组织结构-大屏-滨海新区社会网格化管理信息平台
	public List<EchartType> findOrgpreventComTypeType(CcmOrgSyncentre ccmOrgSyncentre) {
		return ccmOrgOrgpreventDao.findOrgpreventComTypeType(ccmOrgSyncentre);
	}


	/**
	 * @see 根据群防群治组织类型统计情况。
	 * @return
	 */
	public List<EchartType> getByOrgpreventComType() {
		return dao.getByOrgpreventComType();
	}
	
}