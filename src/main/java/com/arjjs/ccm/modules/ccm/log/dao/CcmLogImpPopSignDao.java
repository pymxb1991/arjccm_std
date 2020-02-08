/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.log.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogImpPopSign;

/**
 * 重点人员签到记录DAO接口
 * @author pengjianqiang
 * @version 2019-03-05
 */
@MyBatisDao
public interface CcmLogImpPopSignDao extends CrudDao<CcmLogImpPopSign> {
	
}