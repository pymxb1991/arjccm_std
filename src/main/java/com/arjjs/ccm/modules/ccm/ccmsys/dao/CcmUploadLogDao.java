/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmUploadLog;

/**
 * 待上传上级平台记录管理DAO接口
 * @author pengjianqiang
 * @version 2018-05-12
 */
@MyBatisDao
public interface CcmUploadLogDao extends CrudDao<CcmUploadLog> {
	
}