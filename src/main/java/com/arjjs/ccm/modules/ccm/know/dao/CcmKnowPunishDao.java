/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowPunish;

/**
 * 城管行政处罚DAO接口
 * @author liang
 * @version 2018-06-02
 */
@MyBatisDao
public interface CcmKnowPunishDao extends CrudDao<CcmKnowPunish> {

	//行政处罚
	List<Integer> getType(CcmKnowPunish ccmKnowPunish2);
	
}