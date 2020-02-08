/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.cms.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.cms.entity.CcmFontUser;

/**
 * 居民用户管理DAO接口
 * @author liuxue
 * @version 2018-10-15
 */
@MyBatisDao
public interface CcmFontUserDao extends CrudDao<CcmFontUser> {
	CcmFontUser getByLoginName(CcmFontUser ccmFontUser);
}