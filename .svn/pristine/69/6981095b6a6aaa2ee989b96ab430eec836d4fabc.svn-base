/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.dangerous.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.iot.dangerous.entity.DangerousCarControl;
import com.arjjs.ccm.modules.iot.dangerous.dao.DangerousCarControlDao;

/**
 * 危化品车辆布控实体类Service
 * @author lgh
 * @version 2019-06-05
 */
@Service
@Transactional(readOnly = true)
public class DangerousCarControlService extends CrudService<DangerousCarControlDao, DangerousCarControl> {

	public DangerousCarControl get(String id) {
		return super.get(id);
	}
	
	public List<DangerousCarControl> findList(DangerousCarControl dangerousCarControl) {
		return super.findList(dangerousCarControl);
	}
	
	public Page<DangerousCarControl> findPage(Page<DangerousCarControl> page, DangerousCarControl dangerousCarControl) {
		return super.findPage(page, dangerousCarControl);
	}
	
	@Transactional(readOnly = false)
	public void save(DangerousCarControl dangerousCarControl) {
		super.save(dangerousCarControl);
	}
	
	@Transactional(readOnly = false)
	public void delete(DangerousCarControl dangerousCarControl) {
		super.delete(dangerousCarControl);
	}
	
}