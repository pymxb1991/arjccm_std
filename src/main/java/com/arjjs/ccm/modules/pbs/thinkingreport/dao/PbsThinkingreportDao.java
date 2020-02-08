/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.thinkingreport.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.thinkingreport.entity.PbsThinkingreport;

/**
 * 思想汇报信息DAO接口
 * @author lc
 * @version 2018-05-28
 */
@MyBatisDao
public interface PbsThinkingreportDao extends CrudDao<PbsThinkingreport> {
	
	List<PbsThinkingreport> findListByTransmem(PbsThinkingreport p);
}