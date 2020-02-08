/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.actionuser.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.flat.actionuser.entity.BphActionUser;

/**
 * 动作执行人员关联表DAO接口
 * @author liu
 * @version 2018-11-15
 */
@MyBatisDao
public interface BphActionUserDao extends CrudDao<BphActionUser> {
	int deleteBphActionUser(BphActionUser bphActionUser);
	List<BphActionUser> getIdList(BphActionUser bphActionUser);
}