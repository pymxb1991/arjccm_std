/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.device.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.iot.device.entity.CcmDeviceControl;

import java.util.List;
import java.util.Map;

/**
 * 探针布控DAO接口
 * @author lhf
 * @version 2019-07-23
 */
@MyBatisDao
public interface CcmDeviceControlDao extends CrudDao<CcmDeviceControl> {

    public void deleteByIdent(CcmDeviceControl ccmDeviceControl);

    public List<Map<String, String>> getCount(CcmDeviceControl ccmDeviceControl);
}