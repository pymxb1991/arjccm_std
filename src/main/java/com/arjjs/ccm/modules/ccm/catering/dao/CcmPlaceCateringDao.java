/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.catering.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.catering.entity.CcmPlaceCatering;

/**
 * 餐饮场所DAO接口
 * @author ljd
 * @version 2019-04-29
 */
@MyBatisDao
public interface CcmPlaceCateringDao extends CrudDao<CcmPlaceCatering> {
	
}