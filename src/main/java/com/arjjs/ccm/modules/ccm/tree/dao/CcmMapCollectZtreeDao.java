/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.tree.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.tree.entity.CcmMapCollectZtree;

/**
 * 首页地图收藏树DAO接口
 * @author liuxue
 * @version 2018-09-14
 */
@MyBatisDao
public interface CcmMapCollectZtreeDao extends CrudDao<CcmMapCollectZtree> {
	CcmMapCollectZtree	getZId (CcmMapCollectZtree ccmMapCollectZtree);
}