/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventStakeholder;

/**
 * 案事件干系人DAO接口
 * @author pengjianqiang
 * @version 2018-01-30
 */
@MyBatisDao
public interface CcmEventStakeholderDao extends CrudDao<CcmEventStakeholder> {
	
}