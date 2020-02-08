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
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgSocialorg;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.SysConfigInit;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmUploadLog;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmUploadLogService;
import com.arjjs.ccm.modules.ccm.org.dao.CcmOrgSocialorgDao;

/**
 * 社会组织Service
 * @author fuxinshuang
 * @version 2018-01-26
 */
@Service
@Transactional(readOnly = true)
public class CcmOrgSocialorgService extends CrudService<CcmOrgSocialorgDao, CcmOrgSocialorg> {

	//上传上级平台记录
	@Autowired
	private CcmUploadLogService ccmUploadLogService;
	
	
	
	
	
	public CcmOrgSocialorg get(String id) {
		return super.get(id);
	}
	
	public List<CcmOrgSocialorg> findList(CcmOrgSocialorg ccmOrgSocialorg) {
		return super.findList(ccmOrgSocialorg);
	}
	
	public Page<CcmOrgSocialorg> findPage(Page<CcmOrgSocialorg> page, CcmOrgSocialorg ccmOrgSocialorg) {
		return super.findPage(page, ccmOrgSocialorg);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmOrgSocialorg ccmOrgSocialorg) {
		boolean isNew = false;
		if (ccmOrgSocialorg.getId() == null || "".equals(ccmOrgSocialorg.getId())) {//无主键ID，则是新记录
			isNew = true;
		}
		if (ccmOrgSocialorg.getIsNewRecord()) {//指定了是新增记录，则算新记录
			isNew = true;
		}
		super.save(ccmOrgSocialorg);

		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			if (isNew) {//新增数据
				uploadLog.setOperateType("1");
				uploadLog.setRemarks("新增社会组织数据：" + ccmOrgSocialorg.getOrgName());
			} else {//编辑数据
				uploadLog.setOperateType("2");
				uploadLog.setRemarks("编辑社会组织数据：" + ccmOrgSocialorg.getOrgName());
			}
			uploadLog.setTableName("ccm_org_socialorg");
			uploadLog.setDataId(ccmOrgSocialorg.getId());
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
	public void delete(CcmOrgSocialorg ccmOrgSocialorg) {
		super.delete(ccmOrgSocialorg);

		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			uploadLog.setOperateType("3");
			uploadLog.setRemarks("删除社会组织数据：" + ccmOrgSocialorg.getOrgName());
			uploadLog.setTableName("ccm_org_socialorg");
			uploadLog.setDataId(ccmOrgSocialorg.getId());
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