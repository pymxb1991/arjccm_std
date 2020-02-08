/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.appversion.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.appversion.entity.AppVersion;

/**
 * app版本DAO接口
 * @author lijiupeng
 * @version 2019-08-13
 */
@MyBatisDao
public interface AppVersionDao extends CrudDao<AppVersion> {
	
}