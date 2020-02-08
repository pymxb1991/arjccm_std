/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.service.entity.CcmServiceWechat;

/**
 * 公众信息上报DAO接口
 * @author fuxinshuang
 * @version 2018-03-17
 */
@MyBatisDao
public interface CcmServiceWechatDao extends CrudDao<CcmServiceWechat> {
	
}