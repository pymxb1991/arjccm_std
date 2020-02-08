/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.group.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.group.entity.CcmGroupMember;

/**
 * 群成员管理DAO接口
 * @author liuyongjian
 * @version 2019-08-07
 */
@MyBatisDao
public interface CcmGroupMemberDao extends CrudDao<CcmGroupMember> {
	
}