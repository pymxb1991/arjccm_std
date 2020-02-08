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
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgSyncentre;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.SysConfigInit;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmUploadLog;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmUploadLogService;
import com.arjjs.ccm.modules.ccm.org.dao.CcmOrgSyncentreDao;

/**
 * 综治中心Service
 * @author fu
 * @version 2018-01-18
 */
@Service
@Transactional(readOnly = true)
public class CcmOrgSyncentreService extends CrudService<CcmOrgSyncentreDao, CcmOrgSyncentre> {

	//上传上级平台记录
	@Autowired
	private CcmUploadLogService ccmUploadLogService;
		
	public CcmOrgSyncentre get(String id) {
		return super.get(id);
	}
	
	public List<CcmOrgSyncentre> findList(CcmOrgSyncentre ccmOrgSyncentre) {
		return super.findList(ccmOrgSyncentre);
	}
	
	public Page<CcmOrgSyncentre> findPage(Page<CcmOrgSyncentre> page, CcmOrgSyncentre ccmOrgSyncentre) {
		return super.findPage(page, ccmOrgSyncentre);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmOrgSyncentre ccmOrgSyncentre) {
		boolean isNew = false;
		if (ccmOrgSyncentre.getId() == null || "".equals(ccmOrgSyncentre.getId())) {//无主键ID，则是新记录
			isNew = true;
		}
		super.save(ccmOrgSyncentre);
		
		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			if (isNew) {//新增数据
				uploadLog.setOperateType("1");
				uploadLog.setRemarks("新增综治中心数据：" + ccmOrgSyncentre.getCentreName());
			} else {//编辑数据
				uploadLog.setOperateType("2");
				uploadLog.setRemarks("编辑综治中心数据：" + ccmOrgSyncentre.getCentreName());
			}
			uploadLog.setTableName("ccm_org_syncentre");
			uploadLog.setDataId(ccmOrgSyncentre.getId());
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
	public void delete(CcmOrgSyncentre ccmOrgSyncentre) {
		super.delete(ccmOrgSyncentre);
		
		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			uploadLog.setOperateType("3");
			uploadLog.setRemarks("删除综治中心数据：" + ccmOrgSyncentre.getCentreName());
			uploadLog.setTableName("ccm_org_syncentre");
			uploadLog.setDataId(ccmOrgSyncentre.getId());
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