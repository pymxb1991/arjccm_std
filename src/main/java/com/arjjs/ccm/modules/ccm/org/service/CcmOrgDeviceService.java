/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.org.dao.CcmOrgDeviceDao;
import com.arjjs.ccm.modules.ccm.org.entity.CcmDeviceVo;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgDevice;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgDeviceInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 机构设置绑定Service
 * @author maoxb
 * @version 2019-08-29
 */
@Service
@Transactional(readOnly = true)
public class CcmOrgDeviceService extends CrudService<CcmOrgDeviceDao, CcmOrgDevice> {

	public CcmOrgDevice get(String id) {
		return super.get(id);
	}

	public List<CcmOrgDevice> findList(CcmOrgDevice ccmOrgDevice) {
		return super.findList(ccmOrgDevice);
	}

	public Page<CcmOrgDevice> findPage(Page<CcmOrgDevice> page, CcmOrgDevice ccmOrgDevice) {
		return super.findPage(page, ccmOrgDevice);
	}

	@Transactional(readOnly = false)
	public void save(CcmOrgDevice ccmOrgDevice) {
		ccmOrgDevice.setId(UUID.randomUUID().toString());
		ccmOrgDevice.setIsNewRecord(true);
		super.save(ccmOrgDevice);
	}

	@Transactional(readOnly = false)
	public void delete(CcmOrgDevice ccmOrgDevice) {
		super.delete(ccmOrgDevice);
	}

    public CcmOrgDeviceInfo queryDeviceByOrgDeviceId(String id) {
		return dao.queryDeviceByOrgDeviceId(id);
    }
	@Transactional(readOnly = false)
	public void deleteOrgDevice(String orgId) {
		dao.deleteOrgDevice(orgId);
	}

    public List<CcmDeviceVo> dealDeviceInfo(String id) {
		CcmOrgDeviceInfo ccmDevice =  queryDeviceByOrgDeviceId(id);
		List<CcmDeviceVo> deviceList= new ArrayList<>();
		if (ccmDevice != null){
			deviceList = ccmDevice.getDeviceList();
		}
		 //= ccmDevice.getDeviceList();
//		List<Map<String, String>> devMap
//		Map<String, String> map_V = new HashMap<String, String>();
//		if (ccmDevice != null){
//			if (ccmDevice.getDeviceList().size() > 0){
//				map_V.put("code", "编号");
//				map_V.put("param", "参数");
//				map_V.put("ip", "IP");
//				map_V.put("port", "端口");
//				map_V.put("username","用户名");
//				map_V.put("password", "密码");
//				devMap.add(map_V);
//			}
//		}
		return deviceList;
    }
}