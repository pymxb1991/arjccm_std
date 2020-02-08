package com.arjjs.ccm.modules.flat.userBindingDevice.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.SpringContextHolder;
import com.arjjs.ccm.modules.flat.userBindingDevice.dao.UserBindingDeviceDao;
import com.arjjs.ccm.modules.flat.userBindingDevice.entity.UserBindingDevice;
import com.arjjs.ccm.tool.CacheTableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserBindingDeviceService extends CrudService<UserBindingDeviceDao, UserBindingDevice>{

	@Autowired
	private UserBindingDeviceDao userBindingDeviceDao;
	
	public UserBindingDevice get(String id) {
		return super.get(id);
	}
	
	public List<UserBindingDevice> findList(UserBindingDevice userBindingDevice) {
		return super.findList(userBindingDevice);
	}
	
	public Page<UserBindingDevice> findPage(Page<UserBindingDevice> page, UserBindingDevice userBindingDevice) {
		return super.findPage(page, userBindingDevice);
	}
	
	@Transactional(readOnly = false)
	public void save(UserBindingDevice userBindingDevice) {
		super.save(userBindingDevice);
		try {
			CacheTableData cacheTableBean = SpringContextHolder.getBean("cacheTableData");
			cacheTableBean.getUserDevData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(UserBindingDevice userBindingDevice) {
		super.delete(userBindingDevice);
		try {
			CacheTableData cacheTableBean = SpringContextHolder.getBean("cacheTableData");
			cacheTableBean.getUserDevData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Page<UserBindingDevice> finduserBindingDeviceList(Page<UserBindingDevice> page, UserBindingDevice userBindingDevice){
		// 设置分页参数
		userBindingDevice.setPage(page);
		// 执行分页查询
		page.setList(userBindingDeviceDao.finduserBindingDeviceList(userBindingDevice));
		return page;
	}

	public List<UserBindingDevice> findListAllCode(UserBindingDevice userBindingDevice){
		return userBindingDeviceDao.finduserBindingDeviceList(userBindingDevice);
	}

	public String findDeviceByPolicePhoneCode(String deviceId) {
		return  userBindingDeviceDao.findDeviceByPolicePhoneCode(deviceId);
	}

    @Transactional(readOnly = false)
	public void updateBinding(String userBindId,String userId) {
		userBindingDeviceDao.updateBinding(userBindId,userId);
	}
}
