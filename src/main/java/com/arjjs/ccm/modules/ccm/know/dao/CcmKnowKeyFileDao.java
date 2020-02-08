/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowKeyFile;

/**
 * 重要文件DAO接口
 * @author liang
 * @version 2018-03-23
 */
@MyBatisDao
public interface CcmKnowKeyFileDao extends CrudDao<CcmKnowKeyFile> {
	
}