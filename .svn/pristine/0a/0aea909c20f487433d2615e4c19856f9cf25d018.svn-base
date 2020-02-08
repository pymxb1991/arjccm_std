/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.action.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.flat.action.entity.BphActionInfo;

/**
 * 执行动作配置DAO接口
 * @author liu
 * @version 2018-11-14
 */
@MyBatisDao
public interface BphActionInfoDao extends CrudDao<BphActionInfo> {
	List<BphActionInfo> findAllListBphActionInfo(BphActionInfo bphActionInfo);
	List<BphActionInfo> planActionTree(BphActionInfo bphActionInfo);
}