/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrollog.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.patrollog.dao.CcmPatrolLogDao;
import com.arjjs.ccm.modules.ccm.patrollog.entity.CcmPatrolLog;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 巡检日志Service
 * @author 刘永建
 * @version 2019-07-15
 */
@Service
@Transactional(readOnly = true)
public class CcmPatrolLogService extends CrudService<CcmPatrolLogDao, CcmPatrolLog> {

	@Autowired
	private CcmPatrolLogDao ccmPatrolLogDao;
	
	public CcmPatrolLog get(String id) {
		return super.get(id);
	}
	
	public List<CcmPatrolLog> findList(CcmPatrolLog ccmPatrolLog) {
		return super.findList(ccmPatrolLog);
	}
	
	public Page<CcmPatrolLog> findPage(Page<CcmPatrolLog> page, CcmPatrolLog ccmPatrolLog) {
		User user = UserUtils.getUser();
		if(user==null){
			return new Page<CcmPatrolLog>();
		}
		ccmPatrolLog.getSqlMap().put("dsf", dataScopeFilter(ccmPatrolLog.getCurrentUser(), "o", "u"));
		return super.findPage(page, ccmPatrolLog);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPatrolLog ccmPatrolLog) {
		String image = ccmPatrolLog.getImage();
		if (!"".equals(image)){
		   image =image.substring(1,image.length());
		   ccmPatrolLog.setImage(image);
		}
		super.save(ccmPatrolLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPatrolLog ccmPatrolLog) {
		super.delete(ccmPatrolLog);
	}
	@Transactional(readOnly = false)
	public List<CcmPatrolLog> findListByUser(String id){
		return ccmPatrolLogDao.findListByUser(id);
	}

}