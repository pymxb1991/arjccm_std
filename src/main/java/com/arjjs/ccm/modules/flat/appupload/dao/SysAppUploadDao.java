/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.appupload.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.flat.appupload.entity.SysAppUpload;

/**
 * App 上传记录表DAO接口
 * @author maoxb
 * @version 2019-05-16
 */
@MyBatisDao
public interface SysAppUploadDao extends CrudDao<SysAppUpload> {

	public SysAppUpload getAppByMd5(String md5);
	public SysAppUpload getAppInfo();
	
}