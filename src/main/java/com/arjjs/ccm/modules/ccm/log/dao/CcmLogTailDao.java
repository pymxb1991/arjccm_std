/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.log.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.modules.flat.analyst.entity.Count;

/**
 * 跟踪信息DAO接口
 * @author arj
 * @version 2018-01-23
 */
@MyBatisDao
public interface CcmLogTailDao extends CrudDao<CcmLogTail> {
	
	
	List<CcmLogTail> findListByObject(CcmLogTail ccmLogTail);
	
	int insertLogTail(List<CcmLogTail> list);

	/**
	 * 绩效统计接口
	 * @return
	 */
	List<Count> getByCount();
}