/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.payment.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.payment.entity.PbsPayment;

/**
 * 党员缴费信息管理DAO接口
 * @author qixuesong
 * @version 2018-09-05
 */
@MyBatisDao
public interface PbsPaymentDao extends CrudDao<PbsPayment> {
	
}