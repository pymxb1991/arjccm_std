/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.email.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.email.entity.PlmWorkEmailReply;

/**
 * 内部邮件回复DAO接口
 * @author fu
 * @version 2018-08-02
 */
@MyBatisDao
public interface PlmWorkEmailReplyDao extends CrudDao<PlmWorkEmailReply> {
	
}