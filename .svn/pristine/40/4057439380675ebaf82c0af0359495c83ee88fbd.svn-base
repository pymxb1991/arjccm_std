/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolPlan;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolPointsort;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolUser;
import com.arjjs.ccm.modules.ccm.patrol.dao.CcmPatrolPlanDao;
import com.arjjs.ccm.modules.ccm.patrol.dao.CcmPatrolPointsortDao;
import com.arjjs.ccm.modules.ccm.patrol.dao.CcmPatrolUserDao;

/**
 * 巡逻计划Service
 * 
 * @author arj
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly = true)
public class CcmPatrolPlanService extends CrudService<CcmPatrolPlanDao, CcmPatrolPlan> {

	// 巡逻计划
	@Autowired
	private CcmPatrolUserDao ccmPatrolUserDao;
	// 巡逻计划点位顺序
	@Autowired
	private CcmPatrolPointsortDao ccmPatrolPointsortDao;

	public CcmPatrolPlan get(String id) {
		return super.get(id);
	}

	public List<CcmPatrolPlan> findList(CcmPatrolPlan ccmPatrolPlan) {
		return super.findList(ccmPatrolPlan);
	}

	public Page<CcmPatrolPlan> findPage(Page<CcmPatrolPlan> page, CcmPatrolPlan ccmPatrolPlan) {
		return super.findPage(page, ccmPatrolPlan);
	}

	@Transactional(readOnly = false)
	public void save(CcmPatrolPlan ccmPatrolPlan) {
		super.save(ccmPatrolPlan);
		// 对于用户信息进行添加 ，1 先删除 所有的 计划内容 用信息 后添加
		ccmPatrolUserDao.deleteByPatrolUserId(ccmPatrolPlan.getId());
		// 添加所有的用户
		if (ccmPatrolPlan.getCcmPatrolUserList().size() > 0) {
			for (CcmPatrolUser ccmuser : ccmPatrolPlan.getCcmPatrolUserList()) {
				ccmuser.preInsert();
			}
			ccmPatrolUserDao.insertAll(ccmPatrolPlan.getCcmPatrolUserList());
		}
		// 添加所有的点位信息 顺序 内容
		ccmPatrolPointsortDao.deleteBypointId(ccmPatrolPlan.getId());
		// 添加所有的点位顺序 信息
		if (ccmPatrolPlan.getCcmPatrolPointSortList().size() > 0) {
			for (CcmPatrolPointsort ccmuser : ccmPatrolPlan.getCcmPatrolPointSortList()) {
				ccmuser.preInsert();
			}
			ccmPatrolPointsortDao.insertAll(ccmPatrolPlan.getCcmPatrolPointSortList());
		}
	}

	@Transactional(readOnly = false)
	public void delete(CcmPatrolPlan ccmPatrolPlan) {
		super.delete(ccmPatrolPlan);
	}

}