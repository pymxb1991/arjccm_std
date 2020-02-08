/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.sys.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.sys.entity.SysConfig;

/**
 * 系统信息配置DAO接口
 * @author liang
 * @version 2018-05-10
 */
@MyBatisDao
public interface SysConfigDao extends CrudDao<SysConfig> {
	
}