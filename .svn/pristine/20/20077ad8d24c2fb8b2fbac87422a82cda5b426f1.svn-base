package com.arjjs.ccm.modules.flat.userBindingDevice.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.flat.userBindingDevice.entity.UserBindingDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface UserBindingDeviceDao extends CrudDao<UserBindingDevice>{
	List<UserBindingDevice> finduserBindingDeviceList(UserBindingDevice userBindingDevice);
	String findDeviceByPolicePhoneCode(@Param("phoneCode") String phoneCode);
	void updateBinding(@Param("userBindId") String userBindId, @Param("userId") String userId);
}
