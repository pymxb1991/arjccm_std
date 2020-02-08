/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.home.dao.PlmPortalDetailDao;
import com.arjjs.ccm.modules.plm.home.dao.PlmPortalPlanDao;
import com.arjjs.ccm.modules.plm.home.entity.PlmPortalDetail;
import com.arjjs.ccm.modules.plm.home.entity.PlmPortalPlan;

/**
 * 门户方案Service
 * @author liuxue
 * @version 2018-07-12
 */
@Service
@Transactional(readOnly = true)
public class PlmPortalPlanService extends CrudService<PlmPortalPlanDao, PlmPortalPlan> {

	@Autowired
	private PlmPortalDetailDao plmPortalDetailDao;
	
	public PlmPortalPlan get(String id) {
		PlmPortalPlan plmPortalPlan = super.get(id);
		/*plmPortalPlan.setPlmPortalDetailList(plmPortalDetailDao.findList(new PlmPortalDetail(plmPortalPlan)));*/
		return plmPortalPlan;
	}
	
	public List<PlmPortalPlan> findList(PlmPortalPlan plmPortalPlan) {
		return super.findList(plmPortalPlan);
	}
	
	public Page<PlmPortalPlan> findPage(Page<PlmPortalPlan> page, PlmPortalPlan plmPortalPlan) {
		return super.findPage(page, plmPortalPlan);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmPortalPlan plmPortalPlan) {
		super.save(plmPortalPlan);
		/*for (PlmPortalDetail plmPortalDetail : plmPortalPlan.getPlmPortalDetailList()){
			plmPortalDetail.setType(plmPortalPlan.getType());
			
			if (plmPortalDetail.getId() == null){
				continue;
			}
			if (PlmPortalDetail.DEL_FLAG_NORMAL.equals(plmPortalDetail.getDelFlag())){
				if (StringUtils.isBlank(plmPortalDetail.getId())){
					plmPortalDetail.setParent(plmPortalPlan);
					plmPortalDetail.preInsert();
					plmPortalDetailDao.insert(plmPortalDetail);
				}else{
					plmPortalDetail.preUpdate();
					plmPortalDetailDao.update(plmPortalDetail);
				}
			}else{
				plmPortalDetailDao.delete(plmPortalDetail);
			}
		}*/
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmPortalPlan plmPortalPlan) {
		super.delete(plmPortalPlan);
		plmPortalDetailDao.delete(new PlmPortalDetail(plmPortalPlan));
	}
	
}