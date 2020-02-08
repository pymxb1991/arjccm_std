/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.place.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.iot.place.entity.CcmPlaceControl;

/**
 * 场所布控DAO接口
 * @author yiqingxuan
 * @version 2019-07-25
 */
@MyBatisDao
public interface CcmPlaceControlDao extends CrudDao<CcmPlaceControl> {
	
}