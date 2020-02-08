/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgDevice;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgDeviceInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 机构设置绑定DAO接口
 * @author maoxb
 * @version 2019-08-29
 */
@MyBatisDao
public interface CcmOrgDeviceDao extends CrudDao<CcmOrgDevice> {

    public CcmOrgDeviceInfo queryDeviceByOrgDeviceId(@Param("orgId") String orgId);

    public void deleteOrgDevice(String orgId);
}